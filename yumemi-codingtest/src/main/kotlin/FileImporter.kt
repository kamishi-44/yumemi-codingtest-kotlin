import constant.ErrorCode
import model.EntryPlayer
import model.PlayLog
import model.Player
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 以下のファイルを取り込むクラスです。
 * - エントリーファイル
 * - プレイログファイル
 */
class FileImporter(
    /** ファイルパス一覧 */
    filepathArray: Array<String>
) {

    /** エントリーファイルのパス */
    private val entryPlayerPath: String = filepathArray[ENTRY_PLAYER_PATH_INDEX]

    /** プレイログファイルのパス */
    private val playLogPath: String = filepathArray[PLAY_LOG_PATH_INDEX]

    companion object {
        /** エントリーファイルのインデックス */
        private const val ENTRY_PLAYER_PATH_INDEX = 0

        /** プレイログのインデックス */
        private const val PLAY_LOG_PATH_INDEX = 1

        /** エントリーファイルのフィールド */
        private val ENTRY_PLAYER_FIELDS = listOf("player_id", "handle_name")

        /** プレイログのフィールド */
        private val PLAY_LOG_FIELDS = listOf("create_timestamp", "player_id", "score")
    }

    /**
     * 以下のファイルを読み込み、プレイヤーのリストを返します。
     * - エントリーファイル
     * - プレイログ
     *
     * @return プレイヤーのリスト
     */
    fun import(): List<Player> {
        // 2ファイルの読み込み
        val idAndNameMap: Map<String, String> = fileToIdAndNameMap()
        val playLogLines: MutableList<String> = importPlayLog()

        val players: MutableList<Player> = mutableListOf()
        // プレイログを1行ずつ読み込みプレイヤー情報を作成
        for (line: String in playLogLines) {
            val splitLine: List<String> = line.split(",")
            val playerId: String = splitLine[1]
            players.add(Player(
                playerId = playerId,
                handleName = idAndNameMap[playerId] ?: "",
                score = splitLine[2].toInt(),
                createTime = stringToLocalDateTime(splitLine[0])
            ))
        }

        return players.toList()
    }
    /**
     * エントリーファイルのCSVファイルを Map に変換します。
     *
     * @return エントリープレイヤーのマップ(エントリーID : ハンドルネーム)
     * @throws[IllegalArgumentException] 読み込んだファイルのヘッダーが不正の場合
     */
    private fun fileToIdAndNameMap(): MutableMap<String, String> {
        val lines: MutableList<String> = readFile(entryPlayerPath)
        // 後続処理ではデータ部だけにしておきたいのでremoveする
        val headers: List<String> = lines.removeAt(0).split(",")
        if (!validHeader(headers, ENTRY_PLAYER_FIELDS)) {
            throw IllegalArgumentException(ErrorCode.INVALID_HEADER.message)
        }
        // ファイルからデータクラスに変換
        val entryPlayerMap: MutableMap<String, String> = mutableMapOf()
        for (line: String in lines) {
            val splitLine: List<String> = line.split(",")
            val entryPlayer = EntryPlayer(playerId = splitLine[0], handleName = splitLine[1])
            entryPlayerMap[splitLine[0]] = splitLine[1]
        }

        return entryPlayerMap
    }

    /**
     * プレイログのCSVファイルを PlayLog の List に変換します。
     *
     * @return プレイログのリスト
     * @throws[IllegalArgumentException] 読み込んだファイルのヘッダーが不正の場合
     */
    private fun importPlayLog(): MutableList<String> {
        val lines: MutableList<String> = readFile(playLogPath)
        // 後続処理ではデータ部だけにしておきたいのでremoveする
        val headers: List<String> = lines.removeAt(0).split(",")
        if (!validHeader(headers, PLAY_LOG_FIELDS)) {
            throw IllegalArgumentException(ErrorCode.INVALID_HEADER.message)
        }

        return lines
    }

    /**
     * ファイルの読み込みを行います。
     *
     * @param[filepath] 読み込むファイルのパス
     */
    private fun readFile(filepath: String): MutableList<String> {
        val file = File(filepath)
        val lines: MutableList<String> = file.readLines().toMutableList()

        return lines.ifEmpty {
            emptyList<String>().toMutableList()
        }
    }

    /**
     * 読み込んだファイルのヘッダー内容が正しいか判定します。
     *
     * @param[readFileHeaders] 読み込んだファイルのヘッダー
     * @param[fileHeader] ファイルのヘッダー(エントリーファイル/プレイログ)
     * @return ファイルのヘッダー内容が正しい場合 true
     */
    private fun validHeader(readFileHeaders: List<String>, fileHeader: List<String>): Boolean {
        for (i: Int in fileHeader.indices) {
            if (readFileHeaders[i] != fileHeader[i]) {
                return false
            }
        }
        return true
    }

    /**
     * String の日付時刻を LocalDateTime に変換します。
     *
     * @param[dateString] 日付時刻の文字列
     * @return LocalDateTime に変換した dateString
     */
    private fun stringToLocalDateTime(dateString: String): LocalDateTime {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(dateString, formatter)
    }
}