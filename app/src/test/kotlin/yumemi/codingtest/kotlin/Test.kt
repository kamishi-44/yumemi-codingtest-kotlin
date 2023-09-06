package yumemi.codingtest.kotlin

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class Test : StringSpec({
    "1 + 1は2" {
        1 + 1 shouldBe 2
    }
})