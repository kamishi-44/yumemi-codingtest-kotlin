import constant.ErrorCode
import model.Player
import model.RankerList

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
        val players: List<Player> = importer.import()
        val aggregator = ScoreAggregator(players)
        aggregator.aggregate().outputRanker()
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}

