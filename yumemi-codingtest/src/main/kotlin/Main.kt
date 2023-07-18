import model.EntryPlayer
import model.PlayLog
import java.io.IOException

/**
 * メイン関数
 *
 * @param[args] ファイル名([0]:ゲームのエントリーファイルのファイル名
 *                 [1]:ゲームのプレイログのファイル名)
 */
fun main(args: Array<String>) {
    // 引数の正当性チェック
    // ファイルの存在チェック
    val checker = ArgsChecker(args)
    if (!checker.validArgs()) {
        // エラー処理
        println(checker.errorMessage())
    }
    // ファイルの変換、内容チェック
    try {
        val importer = FileImporter(args)
        // インデックス直接指定はあまりしたくないが、ひとまず
        val EntryPlayers: List<EntryPlayer> = importer.fileToEntryPlayer()
        val PlayLogs: List<PlayLog> = importer.fileToPlayLog()
        return
    } catch (e: IOException) {

    } catch (e: IllegalArgumentException) {

    }
}

