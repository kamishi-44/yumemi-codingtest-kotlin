package yumemi.codingtest.kotlin

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import yumemi.codingtest.kotlin.model.Player

/**
 * FileImporter のテストクラスです。
 */
class FileImporterTest : FunSpec() {
    init {
        context("import() test") {
            context("OK") {
                test("正しいファイルパスの配列でインスタンス生成した場合、サイズが1以上のリストが返る。") {
                    val testArrays: Array<String> =
                        arrayOf("../parameter/game_entry_log.csv", "../parameter/game_score_log.csv")
                    val importer = FileImporter(testArrays)
                    val result: List<Player> = importer.import()
                    result.isEmpty() shouldBe false
                }
            }
            context("NG") {
                test("エントリーファイルのヘッダーが不正の場合、例外が発生する。") {
                    val testArrays: Array<String> =
                        arrayOf("../parameter/game_entry_log_invalid_header.csv", "../parameter/game_score_log.csv")
                    val importer = FileImporter(testArrays)
                    shouldThrow<IllegalArgumentException> {
                        importer.import()
                    }
                }
                test("プレイログのヘッダーが不正の場合、例外が発生する。") {
                    val testArrays: Array<String> =
                        arrayOf("../parameter/game_entry_log.csv", "../parameter/game_score_log_invalid_header.csv")
                    val importer = FileImporter(testArrays)
                    shouldThrow<IllegalArgumentException> {
                        importer.import()
                    }
                }
            }
        }
    }
}