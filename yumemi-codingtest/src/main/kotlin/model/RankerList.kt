package model

/**
 * 上位10名のプレイヤーを保持するクラスです。
 */
class RankerList(
    /** ランカーのリスト*/
    private val rankers: List<Ranker>
) {
    companion object {
        /** 結果出力のフィールド */
        private val resultFields: List<String> = listOf("rank", "player_id", "handle_name", "score")
    }

    /**
     * 上位プレイヤーの情報を出力します。
     * 各項目はカンマ区切りのCSV形式で出力します。
     */
    fun outputRanker() {
        // フィールドの出力
        println(resultFields.joinToString(separator = ","))
        for (ranker: Ranker in rankers) {
            println(ranker.toString())
        }
    }
}