import java.io.File

/**
 * コマンドライン引数のチェッカークラスです。
 * フィールドにはコマンドライン引数を持ちます。
 */
class ArgsChecker(
    /** コマンドライン引数 */
    private val commandLineArgs: Array<String>
) {

    /** エラーメッセージ */
    private var errorCode: ErrorCode = ErrorCode.VALID

    companion object {
        /** コマンドライン引数に指定できる有効な数 */
        private const val VALID_ARGS: Int = 2
    }

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
            errorCode = ErrorCode.INVALID_ARGS_SIZE
            return false
        }
        // ファイルの存在チェック
        for (filePath: String in commandLineArgs) {
            val file = File(filePath)
            if (!file.exists() || file.isDirectory) {
                errorCode = ErrorCode.NOT_FOUND
                return false
            }
        }
        return true
    }

    /**
     * チェックで発生したエラーに該当するエラーメッセージを
     * 返します。
     */
    fun errorMessage(): String {
        return errorCode.message
    }

    /**
     * 引数のチェック時に発生するエラーコードのEnumです。
     */
    enum class ErrorCode(val message: String) {
        INVALID_ARGS_SIZE("入力引数の数が不正です。"),
        NOT_FOUND("ログファイルが存在しません。"),
        INVALID_HEADER("ログファイルのヘッダーが不正です。"),
        VALID("")
    }
}