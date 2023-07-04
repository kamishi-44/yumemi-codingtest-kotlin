import java.io.File

/** コマンドライン引数に指定できる有効な数 */
private const val VALID_ARGS: Int = 2

/**
 * コマンドライン引数のチェッカークラスです。
 * フィールドにはコマンドライン引数を持ちます。
 */
class ArgsChecker(private val commandLineArgs: Array<String>) {
    /**
     * コマンドラインより渡ってきた引数が有効であるかを判定します。
     * 以下全てを満たしている場合、有効と判定し true を返します。
     * - 引数の配列の要素数が 2 である
     * - 引数の各要素のファイルパスが存在し、ファイルである
     *
     * @return コマンドライン引数の各要素が存在するファイルの場合 true
     */
    fun validArgs(): Boolean {
        // 引数の数が妥当か
        if (commandLineArgs.size != VALID_ARGS) {
            return false
        }
        // ファイルの存在チェック
        for (filePath: String in commandLineArgs) {
            val file = File(filePath)
            if (!file.exists() || file.isDirectory) {
                return false
            }
        }
        return true
    }
}