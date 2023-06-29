import java.io.File

/**
 * メイン関数
 *
 * @param[args] ファイル名([0]:ゲームのエントリーファイルのファイル名
 *                 [1]:ゲームのプレイログのファイル名)
 */
fun main(args: Array<String>) {
    // 引数の正当性チェック
    // ファイルの存在チェック
    if (!validArgs(args)) {
        // エラー処理
    }
    // 引数ファイルの内容チェック
}

/**
 * コマンドラインより渡ってきた引数が有効であるかを判定します。
 * 以下全てを満たしている場合、有効と判定し true を返します。
 * - 引数の配列の要素数が 2 である
 * - 引数の各要素のファイルパスが存在し、ファイルである
 *
 * @param[args] コマンドライン引数
 * @return コマンドライン引数の各要素が存在するファイルの場合 true
 */
private fun validArgs(args: Array<String>): Boolean {
    // 引数の数が妥当か
    if (args.size != 2) {
        return false
    }
    // ファイルの存在チェック
    for (filePath in args) {
        val file: File = File(filePath)
        if (!file.exists() || file.isDirectory) {
            return false
        }
    }
    return true
}