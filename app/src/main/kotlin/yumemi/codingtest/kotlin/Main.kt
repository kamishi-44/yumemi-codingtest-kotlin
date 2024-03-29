package yumemi.codingtest.kotlin


import yumemi.codingtest.kotlin.constant.ProcessStatus
import yumemi.codingtest.kotlin.model.Player
import kotlin.system.exitProcess

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
        exitProcess(ProcessStatus.INVALID_ARGS.status)
    }
    // ファイルの変換、内容チェック
    try {
        val importer = FileImporter(args)
        val players: List<Player> = importer.import()
        val aggregator = ScoreAggregator(players)
        print(aggregator.aggregate().createResult())
    } catch (e: IllegalArgumentException) {
        println(e.message)
        exitProcess(ProcessStatus.INVALID_IMPORT.status)
    }
}