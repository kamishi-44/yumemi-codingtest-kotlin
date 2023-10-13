# yumemi-codingtest-kotlin
## 概要
Kotlin の学習のために作成したCLIアプリケーションです。  
大まかな仕様については株式会社ゆめみ様のサーバーサイドコーディング試験を参考に作成しました。  

https://qiita.com/taruhachi/items/56085228fe17537cc0d8

## 開発環境
- 言語：Kotlin 1.9.0
- テストフレームワーク：Kotest 4.6.0
- ビルドツール：Gradle 8.3

## 実装方針
処理の入り口は main 関数で、そこから引数のチェック、ファイルのインポート、スコア集計、結果出力の  
各処理を呼び出しています。

## テスト
ユニットテストは Kotest を使用しており、基本的には条件網羅しています。  
private 関数については public 関数経由でテストを実施しており、private 関数の品質を担保してています。
また、GitHub Actions を導入し、リポジトリへの push が発生した際に自動テストが実行するようにしています。
