package model

import java.time.LocalDateTime

/**
 * プレイログファイルのデータクラスです。
 */
data class PlayLog (val createTime: LocalDateTime, val playerId: String, val score: Int)