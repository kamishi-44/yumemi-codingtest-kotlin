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
        return
    }
    // ファイルの変換、内容チェック
    try {
        val importer = FileImporter(args)
        val entryPlayers: List<EntryPlayer> = importer.fileToEntryPlayer()
        val playLogs: List<PlayLog> = importer.fileToPlayLog()
        if (entryPlayers.isNotEmpty() && playLogs.isNotEmpty()){
            println("取り込み成功")
        } else {
            println("取り込み失敗")
        }
        return
    } catch (e: IOException) {
        println(e.message)
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

