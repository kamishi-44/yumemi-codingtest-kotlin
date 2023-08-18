import model.*

/**
 * プレイログを元にスコアの集計を行うクラスです。
 */
class ScoreAggregator(
    /** プレイヤー一覧 */
    private val players: List<Player>
) {

    companion object {
        /** 上位ランカーの上限数 */
        private const val MAX_RANKERS: Int = 10
    }

    /**
     * プレイログの内容を元に集計を行います。
     *
     * @return 上位10名のプレイヤー一覧
     */
    fun aggregate(): RankerList {
        // プレイログを元に順位付けする
        val sortedPlayers: List<Player> = players.sortedDescending()

        return extractTop10(sortedPlayers)
    }

    /**
     * 上位１０名のプレイヤーを抽出します。
     *
     * @param[sortedPlayers] スコア降順でソートしたプレイヤー
     * @return 上位10名のプレイヤー一覧
     */
    private fun extractTop10(sortedPlayers: List<Player>): RankerList {
        // エントリープレイヤーはハンドルネーム取得用
        // IDからハンドルネームを特定する
        // エントリープレイヤーに存在しないIDの場合はスキップする
        val rankerList: MutableList<Ranker> = mutableListOf()
        var rank = 1
        var lastScore = 0

        for (player: Player in sortedPlayers) {
            // 存在しないプレイヤーはスキップ
            if (player.handleName.isEmpty()) {
                continue
            }

            val score: Int = player.score
            // 前回と違うスコアであれば順位更新
            if (score != lastScore) {
                rank = determineNextRank(rank, rankerList.size)
            }
            rankerList.add(Ranker(rank, player.playerId, player.handleName, player.score))
            lastScore = score
            // ランカーが10人を超えた時点で終了
            if (rankerList.size > MAX_RANKERS) {
                break
            }
            // TODO: 10人超えても次のプレイヤーのスコアが同点だった場合の考慮が不足
            // TODO: ハンドルネームの取得

        }
        return RankerList(rankerList)
    }

    /**
     * 現在の順位と既に決定しているランカーの数から
     * 次の順位を決定します。
     *
     * @param[nowRank] 現在の順位
     * @param[determinedRankers] 既に決定している上位プレイヤー
     * @return 次の順位
     */
    private fun determineNextRank(nowRank: Int, determinedRankers: Int): Int {
        if (determinedRankers == 0) {
            return nowRank
        }
        return determinedRankers + 1
    }
}