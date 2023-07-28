package constant

/**
 * 引数のチェック時に発生するエラーコードのEnumです。
 */
enum class ErrorCode(val message: String) {
    INVALID_ARGS_SIZE("入力引数の数が不正です。"),
    NOT_FOUND("ログファイルが存在しません。"),
    INVALID_HEADER("ログファイルのヘッダーが不正です。"),
    EMPTY_PLAYER_FILE("エントリーファイルにデータが存在しません。"),
    EMPTY_PLAY_LOG_FILE("プレイログファイルにデータが存在しません。"),
    VALID("")
}