package yumemi.codingtest.kotlin

import yumemi.codingtest.kotlin.model.Player
import yumemi.codingtest.kotlin.model.Ranker
import yumemi.codingtest.kotlin.model.RankerList


/**
 * プレイログを元にスコアの集計を行うクラスです。
 */
class ScoreAggregator(
    /** プレイヤー一覧 */
    private val players: List<Player>
) {

    companion object {
        /** 上位ランカーの上限数 */
        private const val MAX_RANKERS: Int = 9
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
        val rankerList: MutableList<Ranker> = mutableListOf()
        val rankedPlayerList: MutableList<String> = mutableListOf()
        var rank = 1
        var lastScore = 0

        for (player: Player in sortedPlayers) {
            // 存在しないプレイヤーはスキップ
            if (player.handleName.isEmpty()) {
                continue
            }
            // リストに追加済みの場合はスキップ
            val playerId: String = player.playerId
            if (rankedPlayerList.contains(playerId)) {
                continue
            }

            val score: Int = player.score
            // ランカーが10人を超えた時点で終了
            // スコアが前回と同じであれば10人を超えても結果に含める
            if (lastScore != score && rankerList.size > MAX_RANKERS) {
                break
            }
            // 前回と違うスコアであれば順位更新
            if (score != lastScore) {
                rank = determineNextRank(rank, rankerList.size)
            }
            rankerList.add(Ranker(rank, playerId, player.handleName, player.score))
            rankedPlayerList.add(playerId)
            lastScore = score
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