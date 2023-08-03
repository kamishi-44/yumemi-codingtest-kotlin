import model.EntryPlayer
import model.PlayLog
import model.RankerList

/**
 * プレイログを元にスコアの集計を行うクラスです。
 */
class ScoreAggregator(
    /** エントリープレイヤー一覧 */
    val entryPlayers: List<EntryPlayer>,
    /** プレイログ一覧 */
    val playLogs: List<PlayLog>
) {

    /**
     * プレイログの内容を元に集計を行います。
     *
     * @return 上位10名のプレイヤー一覧
     */
    fun aggregate(): RankerList {
        return RankerList(listOf())
    }

    /**
     * スコアを元に降順でソートします。
     */
    private fun sortByScore() {

    }

    /**
     * 上位１０名のプレイヤーを抽出します。
     *
     * @return 上位10名のプレイヤー一覧
     */
    private fun extractTop10(): RankerList {
        return RankerList(listOf())
    }
}