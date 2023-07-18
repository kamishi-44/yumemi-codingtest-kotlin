import model.EntryPlayer
import model.PlayLog
import java.io.File

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
     * エントリーファイルのCSVファイルを EntryPlayer の List に変換します。
     */
    fun fileToEntryPlayer(): List<EntryPlayer> {
        val lines: List<String> = readFile(entryPlayerPath)
        val headers: List<String> = lines.first().split(",")
        if (validHeader(headers, ENTRY_PLAYER_FIELDS)) {
            // エラー処理
        }
        // ファイルからデータクラスに変換
        return listOf()
    }

    /**
     * プレイログのCSVファイルを PlayLog の List に変換します。
     */
    fun fileToPlayLog(): List<PlayLog> {
        val lines: List<String> = readFile(playLogPath)
        val headers: List<String> = lines.first().split(",")
        if (validHeader(headers, PLAY_LOG_FIELDS)) {
            // エラー処理
        }
        // ファイルからデータクラスに変換
        return listOf()
    }

    /**
     * ファイルの読み込みを行います。
     *
     * @param[filepath] 読み込むファイルのパス
     */
    private fun readFile(filepath: String): List<String> {
        val file = File(filepath)
        val lines: List<String> = file.readLines()

        return lines.ifEmpty {
            emptyList()
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
}