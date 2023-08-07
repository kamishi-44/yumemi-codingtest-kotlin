import model.EntryPlayer
import model.PlayLog
import model.RankerList

/**
 * プレイログを元にスコアの集計を行うクラスです。
 */
class ScoreAggregator(
    /** エントリープレイヤー一覧 */
    private val entryPlayers: List<EntryPlayer>,
    /** プレイログ一覧 */
    private val playLogs: List<PlayLog>
) {

    /**
     * プレイログの内容を元に集計を行います。
     *
     * @return 上位10名のプレイヤー一覧
     */
    fun aggregate(): RankerList {
        // プレイログを元に順位付けする
        val sortedPlayLogs: List<PlayLog> = playLogs.sortedDescending()

        return extractTop10()
    }

    /**
     * 上位１０名のプレイヤーを抽出します。
     *
     * @param[sortedPlayLogs] スコア降順でソートしたプレイログ
     * @return 上位10名のプレイヤー一覧
     */
    private fun extractTop10(sortedPlayLogs: List<PlayLog>): RankerList {
        // エントリープレイヤーはハンドルネーム取得用
        // IDからハンドルネームを特定する
        // エントリープレイヤーに存在しないIDの場合はスキップする
        return RankerList(listOf())
    }
}