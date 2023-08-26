package constant

/**
 * 引数のチェック時に発生するエラーコードのEnumです。
 */
enum class ErrorCode(val message: String) {
    /** 引数の数が不正 */
    INVALID_ARGS_SIZE("入力引数の数が不正です。"),
    /** ログファイルが存在しない */
    NOT_FOUND("ログファイルが存在しません。"),
    /** ログファイルのヘッダーが不正 */
    INVALID_HEADER("ログファイルのヘッダーが不正です。"),
    /** エントリーファイルのデータが0件 */
    EMPTY_PLAYER_FILE("エントリーファイルにデータが存在しません。"),
    /** プレイログファイルのデータが0件 */
    EMPTY_PLAY_LOG_FILE("プレイログファイルにデータが存在しません。"),
    /** 正常 */
    VALID("")
}