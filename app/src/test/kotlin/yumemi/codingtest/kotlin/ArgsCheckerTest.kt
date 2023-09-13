package yumemi.codingtest.kotlin

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ArgsCheckerTest : FunSpec() {
    private val args: Array<String> = arrayOf("./parameter/game_entry_log.csv")
    private val checker: ArgsChecker = ArgsChecker(args)

    init {
        context("validArgs() test") {
            context("OK") {
                test("引数の数が2,パスがファイルを指している場合 true が返る") {
                    val executer =
                        TestExecuter(arrayOf("../parameter/game_entry_log.csv", "../parameter/game_score_log.csv"))
                    executer.validArgsTest() shouldBe true
                }
            }
            context("NG") {
                test("引数の数が1の場合 false が返る") {
                    val executer =
                        TestExecuter(arrayOf("../parameter/game_entry_log.csv"))
                    executer.validArgsTest() shouldBe false
                }
                test("存在しないファイルの場合 false が返る") {
                    val executer =
                        TestExecuter(
                            arrayOf(
                                "../parameter/test_game_entry_log.csv",
                                "../parameter/test_game_score_log.csv"
                            )
                        )
                    executer.validArgsTest() shouldBe false
                }
            }
        }
    }
}

/**
 * ArgsCheckerのテスト実行クラス
 */
private class TestExecuter(
    /** コマンドライン引数 */
    private val args: Array<String>
) {
    // 引数チェッカー
    private val checker: ArgsChecker = ArgsChecker(args)

    /**
     * ArgsChecker#validArgs() を実行します。
     *
     * @return ArgsChecker#validArgs() の実行結果
     */
    fun validArgsTest(): Boolean {
        return checker.validArgs()
    }
}