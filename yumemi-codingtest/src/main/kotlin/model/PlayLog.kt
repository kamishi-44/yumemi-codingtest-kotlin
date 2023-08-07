package model

import java.time.LocalDateTime

/**
 * プレイログファイルのデータクラスです。
 */
data class PlayLog(
    /** ゲームのプレイ日時 */
    val createTime: LocalDateTime,
    /** プレイヤーID */
    val playerId: String,
    /** スコア */
    val score: Int
) : Comparable<PlayLog> {

    override fun compareTo(other: PlayLog): Int {
        return if (score == other.score) {
            0
        } else {
            score - other.score
        }
    }
}