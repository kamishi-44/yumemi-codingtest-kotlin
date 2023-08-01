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
)