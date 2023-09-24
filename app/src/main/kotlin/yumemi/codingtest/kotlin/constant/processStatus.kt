package yumemi.codingtest.kotlin.constant

/**
 * エラーが発生して異常終了する際のステータスの Enum です。
 */
enum class ProcessStatus(val status: Int) {
    /** 引数の不正 */
    INVALID_ARGS(-1),
    /** ファイルインポート時の不正 */
    INVALID_IMPORT(-2),
}