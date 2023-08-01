package model

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
)