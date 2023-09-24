package yumemi.codingtest.kotlin

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import yumemi.codingtest.kotlin.model.Player

/**
 * ScoreAggregator のテストクラスです。
 */
class ScoreAggregatorTest : FunSpec() {
    init {
        context("OK") {
            test("同点スコアが無い場合、順位は1~10の順となる。") {
                val resultSb = StringBuilder()
                resultSb.append("rank,player_id,handle_name,score\n")
                resultSb.append("1,EFGHI75310,liam_7,150\n")
                resultSb.append("2,QRSTU86420,lucas_14,140\n")
                resultSb.append("3,BCDEF86420,mia_11,130\n")
                resultSb.append("4,PQRST98765,evelyn_4,120\n")
                resultSb.append("5,FGHIJ67890,olivia_2,110\n")
                resultSb.append("6,ZABCD86420,sophia_6,100\n")
                resultSb.append("7,ABCDE12345,alexander_1,90\n")
                resultSb.append("8,LMNOP95162,amelia_13,80\n")
                resultSb.append("9,QRSTUV75309,benjamin_9,70\n")
                resultSb.append("10,GHIJK75309,james_12,60\n")

                val testArrays: Array<String> =
                    arrayOf(
                        "../parameter/test-no-tie/game_entry_log_no_tie.csv",
                        "../parameter/test-no-tie/game_score_log_no_tie.csv"
                    )
                val importer = FileImporter(testArrays)
                val players: List<Player> = importer.import()
                val aggregator = ScoreAggregator(players)

                aggregator.aggregate().createResult() shouldBe resultSb.toString()
            }
            test("同点スコアがある場合、順位が同じ行が複数できる。") {
                val resultSb = StringBuilder()
                resultSb.append("rank,player_id,handle_name,score\n")
                resultSb.append("1,EFGHI75310,liam_7,150\n")
                resultSb.append("1,LMNOP95162,amelia_13,150\n")
                resultSb.append("3,QRSTU86420,lucas_14,140\n")
                resultSb.append("4,BCDEF86420,mia_11,130\n")
                resultSb.append("5,PQRST98765,evelyn_4,120\n")
                resultSb.append("5,UVWXY24680,william_5,120\n")
                resultSb.append("7,FGHIJ67890,olivia_2,110\n")
                resultSb.append("8,ZABCD86420,sophia_6,100\n")
                resultSb.append("9,ABCDE12345,alexander_1,90\n")
                resultSb.append("10,QRSTUV75309,benjamin_9,70\n")

                val testArrays: Array<String> =
                    arrayOf(
                        "../parameter/test-tie/game_entry_log_tie.csv",
                        "../parameter/test-tie/game_score_log_tie.csv"
                    )
                val importer = FileImporter(testArrays)
                val players: List<Player> = importer.import()
                val aggregator = ScoreAggregator(players)

                aggregator.aggregate().createResult() shouldBe resultSb.toString()
            }
            test("ランキング入賞者が10人以上存在する場合、10行以上の結果が出力される。") {
                val resultSb = StringBuilder()
                resultSb.append("rank,player_id,handle_name,score\n")
                resultSb.append("1,EFGHI75310,liam_7,150\n")
                resultSb.append("2,QRSTU86420,lucas_14,140\n")
                resultSb.append("3,BCDEF86420,mia_11,130\n")
                resultSb.append("4,PQRST98765,evelyn_4,120\n")
                resultSb.append("5,FGHIJ67890,olivia_2,110\n")
                resultSb.append("6,ZABCD86420,sophia_6,100\n")
                resultSb.append("7,ABCDE12345,alexander_1,90\n")
                resultSb.append("8,LMNOP95162,amelia_13,80\n")
                resultSb.append("9,QRSTUV75309,benjamin_9,70\n")
                resultSb.append("10,GHIJK75309,james_12,60\n")
                resultSb.append("10,JKLMNOP15926,charlotte_8,60\n")
                resultSb.append("10,WXYZA95162,ava_10,60\n")

                val testArrays: Array<String> =
                    arrayOf(
                        "../parameter/test-over-10/game_entry_log_over_10.csv",
                        "../parameter/test-over-10/game_score_log_over_10.csv"
                    )
                val importer = FileImporter(testArrays)
                val players: List<Player> = importer.import()
                val aggregator = ScoreAggregator(players)

                aggregator.aggregate().createResult() shouldBe resultSb.toString()
            }
        }
    }
}