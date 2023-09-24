package yumemi.codingtest.kotlin.model

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

        /** 改行コード(LF) */
        private const val LF: String = "\n"
    }

    /**
     * コンソールに出力する集計結果を作成します。
     * 各項目はカンマ区切りのCSV形式でです。
     *
     * @return コンソールに出力する集計結果
     */
    fun createResult(): String {
        val result: StringBuilder = StringBuilder()
        result.append(resultFields.joinToString(separator = ",") + LF)
        for (ranker: Ranker in rankers) {
            result.append(ranker.toString() + LF)
        }
        return result.toString()
    }
}