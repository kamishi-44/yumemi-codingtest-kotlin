package yumemi.codingtest.kotlin.model

/**
 * 上位プレイヤー１人の情報を持つデータクラスです。
 * CSVに出力する情報のみ保持します。
 */
data class Ranker(
    /** 順位 */
    val rank: Int,
    /** プレイヤーID */
    val playerId: String,
    /** ハンドルネーム */
    val handleName: String,
    /** スコア */
    val score: Int
) {
    /**
     * Ranker の String を返します。
     *
     * @return 各フィールドをカンマ区切りにした文字列
     */
    override fun toString(): String {
        return "$rank,$playerId,$handleName,$score"
    }
}