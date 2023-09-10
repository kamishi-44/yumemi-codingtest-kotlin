package yumemi.codingtest.kotlin

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ArgsCheckerTest : FunSpec() {
    init {
        context("ArgsChecker test") {
            context("OK") {
                1 + 1 shouldBe 2
            }
            context("NG") {
                1 + 1 shouldNotBe 3
            }
        }
    }
}