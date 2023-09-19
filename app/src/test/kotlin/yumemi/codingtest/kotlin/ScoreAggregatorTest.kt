package yumemi.codingtest.kotlin

import io.kotest.core.spec.style.FunSpec

/**
 * ScoreAggregator のテストクラスです。
 */
class ScoreAggregatorTest: FunSpec() {
    init {
        context("OK") {
            test("同点スコアが無い場合、順位は1~10の順となる。") {

            }
            test("同点スコアがある場合、順位が同じ行が複数できる。") {

            }
            test("ランキング入賞者が10人以上存在する場合、10行以上の結果が出力される。") {

            }
        }
    }
}