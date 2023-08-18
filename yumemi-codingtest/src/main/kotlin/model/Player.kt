package model

import java.time.LocalDateTime

/**
 * プレイヤーのデータクラスです。
 */
data class Player(
    /** プレイヤーID */
    val playerId: String,
    /** ハンドルネーム */
    val handleName: String,
    /** スコア */
    val score: Int,
    /** ゲームのプレイ日時 */
    val createTime: LocalDateTime
) : Comparable<Player> {
    override fun compareTo(other: Player): Int {
        return if (score == other.score) {
            0
        } else {
            score - other.score
        }
    }
}