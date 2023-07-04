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
    }
    // 引数ファイルの内容チェック
}
