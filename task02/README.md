# このプログラムについて
## 動作確認したOSおよびバージョン
```
ProductName:	macOS
ProductVersion:	11.4
BuildVersion:	20F71
```

## 使用したプログラミング言語およびバージョン
### プログラミング言語
Java

### バージョン
```
openjdk version "17.0.1" 2021-10-19
OpenJDK Runtime Environment Homebrew (build 17.0.1+0)
OpenJDK 64-Bit Server VM Homebrew (build 17.0.1+0, mixed mode, sharing)
```

## コマンドラインからのビルド方法（ビルドが必要な場合）
1. `build.sh`を実行します。
```
$ bash build.sh
```
2. コンソールを確認し、`build.sh`が終了する事を確認します。
```
2020-01-01 12:34:56 [INFO] Start build. output=rss-reader.jar
・・・
2020-01-01 12:34:56 [INFO] Finish build. output=rss-reader.jar
```
3. カレントディレクトリに`rss-reader.jar`を出力する事を確認します。<br>
実行対象のプログラムは`rss-reader.jar`です。
```
$ ls rss-reader.jar
rss-reader.jar
```

## コマンドラインからの実行・動作確認方法
### ヘルプ
```
$ java -jar rss-reader.jar -h
2021-11-28 12:39:22.017 [pid:67461] [INFO] Help
----------------------------------------------------------------
Name:
    RssReader
Description:
    It takes an RSS feed or file, performs some conversions, and outputs it.
Options:
    -h: Outputs a description of this function.
    -i: Specify the URL of the RSS feed or the path of the text file.
    -c: Specify the conversion options of the imported RSS. type=cut,convert
    -o: Specify the output type of the imported RSS or the path of the output file. type=stdout
----------------------------------------------------------------
```
