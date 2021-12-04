# 内容
## 動作確認したOSおよびバージョン
```
ProductName:	Mac OS X
ProductVersion:	10.14.6
BuildVersion:	18G4032
```

## 使用したプログラミング言語およびバージョン
### プログラミング言語
Java

### バージョン
```
openjdk version "1.8.0_222"
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_222-b10)
OpenJDK 64-Bit Server VM (AdoptOpenJDK)(build 25.222-b10, mixed mode)
```

## コマンドラインからのビルド方法（ビルドが必要な場合）
1. `build.sh`を実行します。
```
$ bash build.sh
```
2. コンソールを確認し、`build.sh`が終了する事を確認します。
```
2020-01-01 12:34:56 [INFO] Start build. output=simple-tasks.jar
・・・
2020-01-01 12:34:56 [INFO] Finish build. output=simple-tasks.jar
```
3. カレントディレクトリに`simple-tasks.jar`を出力する事を確認します。<br>
実行対象のプログラムは`simple-tasks.jar`です。
```
$ ls simple-tasks.jar
simple-tasks.jar
```

## コマンドラインからの実行・動作確認方法
### 課題A(順列の問題)の内容を出力する場合
第一引数に'a'を指定し、第二引数に処理対象のASCII印字可能文字列を指定してください。<br>
処理結果は`[INFO] Print... result=`ログに出力します。

#### 入出力の例
```
$ java -jar simple-tasks.jar a AB
2020-03-31 20:31:55.649 [INFO] Enter the simple task. args=[a,AB]
2020-03-31 20:31:55.745 [INFO] Output the answer of task A.
2020-03-31 20:31:55.748 [INFO] Generates and prints a permutation from the argument string. arg=AB
2020-03-31 20:31:55.754 [INFO] Print the generated permutation. result=AB
2020-03-31 20:31:55.754 [INFO] Print the generated permutation. result=BA
2020-03-31 20:31:55.755 [INFO] Exit the simple task.
```

### 課題B(検索の問題)の内容を出力する場合
第一引数に'b'を指定し、第二引数以降に任意の数のドメインを指定し、また標準入力でドメインの一覧が改行区切りで記載されたファイルを指定してください。<br>
処理結果は`[INFO] Prints... result=`ログに出力します。

#### 入出力の例
```
$ java -jar simple-tasks.jar b simple.simple.jp < domains.txt
2020-03-31 21:07:17.094 [INFO] Enter the simple task. args=[b,simple.simple.jp]
2020-03-31 21:07:17.187 [INFO] Output the answer of task B.
2020-03-31 21:07:17.195 [INFO] Search for the domain of the argument that matches the end of the domain list of the standard input. arg=simple.simple.jp
2020-03-31 21:07:17.196 [INFO] Prints a list of standard input that matches the end of the argument domain. result=simple.simple.jp:simple.jp
2020-03-31 21:07:17.197 [INFO] Exit the simple task.
```

## 各プログラムの処理内容（データ構造・アルゴリズム等）
### プログラムの構成
```
task
├── Main.java
├── answer
│   ├── AnswerBase.java
│   ├── DomainSearch.java
│   └── Permutation.java
├── type
│   └── AnswerType.java
└── util
    ├── CollectionUtils.java
    └── Logger.java
```

### 処理内容
#### 共通処理(Main.java)
1. 第一引数に指定した課題の解答区分に該当する処理を呼び出します。
2. 引数に解答区分の指定が無い場合、解答区分の値が想定外の場合は異常終了します。

#### 課題A:順列の問題(Permutation.java)
1. 引数に指定したASCII印字可能文字列の順列を標準出力します。
2. 文字列を1文字毎の配列に分割し、先頭の要素を順次取得し、順次取得した要素を残りの配列の先頭に追加する処理を再帰的に呼び出す事により、文字列の順列を生成します。
3. 引数に文字列の指定が無い場合、文字列にASCII印字可能文字列以外が含まれる場合は異常終了します。

#### 課題B:検索の問題(DomainSearch.java)
1. 引数に指定したドメインを順次取得し、標準入力したファイルのドメイン一覧に末尾が一致するものがあればコロン(:)区切りで標準出力し、無い場合は`ドメイン名:NONE`を標準出力します。
2. `java.util.List#indexOf()`で末尾が等しいものを抽出するため、型パラメータの`EndMatchObject`クラスを定義し、`java.util.Objects#equals()`をオーバーライドしています。
3. 標準入力したファイルの内容が空の場合、1つ以上の引数が無い場合は異常終了します。

### 補足
#### 解答
##### 課題A: 順列の問題
###### 実行例1
```
$ java -jar simple-tasks.jar a AB
2020-03-31 22:04:08.215 [INFO] Enter the simple task. args=[a,AB]
2020-03-31 22:04:08.336 [INFO] Output the answer of task A.
2020-03-31 22:04:08.340 [INFO] Generates and prints a permutation from the argument string. arg=AB
2020-03-31 22:04:08.349 [INFO] Print the generated permutation. result=AB
2020-03-31 22:04:08.350 [INFO] Print the generated permutation. result=BA
2020-03-31 22:04:08.351 [INFO] Exit the simple task.
```

###### 実行例2
```
$ java -jar simple-tasks.jar a ABC
2020-03-31 22:05:11.811 [INFO] Enter the simple task. args=[a,ABC]
2020-03-31 22:05:11.908 [INFO] Output the answer of task A.
2020-03-31 22:05:11.913 [INFO] Generates and prints a permutation from the argument string. arg=ABC
2020-03-31 22:05:11.918 [INFO] Print the generated permutation. result=ABC
2020-03-31 22:05:11.919 [INFO] Print the generated permutation. result=ACB
2020-03-31 22:05:11.919 [INFO] Print the generated permutation. result=BAC
2020-03-31 22:05:11.920 [INFO] Print the generated permutation. result=BCA
2020-03-31 22:05:11.920 [INFO] Print the generated permutation. result=CAB
2020-03-31 22:05:11.921 [INFO] Print the generated permutation. result=CBA
2020-03-31 22:05:11.921 [INFO] Exit the simple task.
```

###### 実行例3
```
$ java -jar simple-tasks.jar a ABB
2020-03-31 22:05:51.221 [INFO] Enter the simple task. args=[a,ABB]
2020-03-31 22:05:51.314 [INFO] Output the answer of task A.
2020-03-31 22:05:51.318 [INFO] Generates and prints a permutation from the argument string. arg=ABB
2020-03-31 22:05:51.324 [INFO] Print the generated permutation. result=ABB
2020-03-31 22:05:51.325 [INFO] Print the generated permutation. result=BAB
2020-03-31 22:05:51.325 [INFO] Print the generated permutation. result=BBA
2020-03-31 22:05:51.326 [INFO] Exit the simple task.
```

###### 実行例4
```
$ java -jar simple-tasks.jar a ABCC
2020-03-31 22:06:18.275 [INFO] Enter the simple task. args=[a,ABCC]
2020-03-31 22:06:18.394 [INFO] Output the answer of task A.
2020-03-31 22:06:18.398 [INFO] Generates and prints a permutation from the argument string. arg=ABCC
2020-03-31 22:06:18.404 [INFO] Print the generated permutation. result=ABCC
2020-03-31 22:06:18.405 [INFO] Print the generated permutation. result=ACBC
2020-03-31 22:06:18.406 [INFO] Print the generated permutation. result=ACCB
2020-03-31 22:06:18.407 [INFO] Print the generated permutation. result=BACC
2020-03-31 22:06:18.407 [INFO] Print the generated permutation. result=BCAC
2020-03-31 22:06:18.408 [INFO] Print the generated permutation. result=BCCA
2020-03-31 22:06:18.409 [INFO] Print the generated permutation. result=CABC
2020-03-31 22:06:18.410 [INFO] Print the generated permutation. result=CACB
2020-03-31 22:06:18.410 [INFO] Print the generated permutation. result=CBAC
2020-03-31 22:06:18.411 [INFO] Print the generated permutation. result=CBCA
2020-03-31 22:06:18.411 [INFO] Print the generated permutation. result=CCAB
2020-03-31 22:06:18.412 [INFO] Print the generated permutation. result=CCBA
2020-03-31 22:06:18.413 [INFO] Exit the simple task.
```

##### 課題B: 検索の問題
###### 実行例1
```
$ java -jar simple-tasks.jar b <domains.txt simple.simple.jp
2020-03-31 22:07:18.202 [INFO] Enter the simple task. args=[b,simple.simple.jp]
2020-03-31 22:07:18.313 [INFO] Output the answer of task B.
2020-03-31 22:07:18.322 [INFO] Search for the domain of the argument that matches the end of the domain list of the standard input. arg=simple.simple.jp
2020-03-31 22:07:18.324 [INFO] Prints a list of standard input that matches the end of the argument domain. result=simple.simple.jp:simple.jp
2020-03-31 22:07:18.324 [INFO] Exit the simple task.
```

###### 実行例2
```
$ java -jar simple-tasks.jar b <domains.txt simple.jp example.jp
2020-03-31 22:08:00.895 [INFO] Enter the simple task. args=[b,simple.jp,example.jp]
2020-03-31 22:08:00.990 [INFO] Output the answer of task B.
2020-03-31 22:08:00.998 [INFO] Search for the domain of the argument that matches the end of the domain list of the standard input. arg=simple.jp,example.jp
2020-03-31 22:08:01.000 [INFO] Prints a list of standard input that matches the end of the argument domain. result=simple.jp:simple.jp
2020-03-31 22:08:01.000 [INFO] No match was found at the end of the argument domain from the standard input list. result=example.jp:NONE
2020-03-31 22:08:01.001 [INFO] Exit the simple task.
```

###### 実行例3
```
$ java -jar simple-tasks.jar b <domains.txt simple-inc.jp vvv.www.example.com simple.co.jp.com
2020-03-31 22:08:45.247 [INFO] Enter the simple task. args=[b,simple-inc.jp,vvv.www.example.com,simple.co.jp.com]
2020-03-31 22:08:45.351 [INFO] Output the answer of task B.
2020-03-31 22:08:45.359 [INFO] Search for the domain of the argument that matches the end of the domain list of the standard input. arg=simple-inc.jp,vvv.www.example.com,simple.co.jp.com
2020-03-31 22:08:45.360 [INFO] No match was found at the end of the argument domain from the standard input list. result=simple-inc.jp:NONE
2020-03-31 22:08:45.361 [INFO] Prints a list of standard input that matches the end of the argument domain. result=vvv.www.example.com:example.com
2020-03-31 22:08:45.361 [INFO] No match was found at the end of the argument domain from the standard input list. result=simple.co.jp.com:NONE
2020-03-31 22:08:45.361 [INFO] Exit the simple task.
```

#### エラーパターン
##### 共通処理(Main.java)
###### 解答区分の指定が無い場合
```
$ java -jar simple-tasks.jar
2020-03-31 20:31:33.365 [INFO] Enter the simple task. args=[]
2020-03-31 20:31:33.481 [ERROR] Please specify the answer type in the first argument. type=[a,b]
```

###### 解答区分の指定が想定外の場合
```
$ java -jar simple-tasks.jar c
2020-03-31 20:31:44.285 [INFO] Enter the simple task. args=[c]
2020-03-31 20:31:44.400 [ERROR] Please specify the answer type in the first argument. type=[a,b]
```

##### 課題A:順列の問題(Permutation.java)
###### 引数の指定が無い場合
```
$ java -jar simple-tasks.jar a
2020-03-31 20:31:46.931 [INFO] Enter the simple task. args=[a]
2020-03-31 20:31:47.026 [INFO] Output the answer of task A.
2020-03-31 20:31:47.027 [ERROR] Please specify an ASCII printable character string as an argument.
2020-03-31 20:31:47.028 [ERROR] Argument is invalid.
```

###### 引数にASCII印字可能文字列以外が含まれる場合
```
$ java -jar simple-tasks.jar a ABあ
2020-03-31 21:49:18.949 [INFO] Enter the simple task. args=[a,ABあ]
2020-03-31 21:49:19.041 [INFO] Output the answer of task A.
2020-03-31 21:49:19.045 [ERROR] Please specify only an ASCII printable character string as an argument. arg=ABあ
2020-03-31 21:49:19.045 [ERROR] Argument is invalid.
```

##### 課題B:検索の問題(DomainSearch.java)
###### 引数の指定が無い場合
```
$ java -jar simple-tasks.jar b < domains.txt
2020-03-31 20:32:37.226 [INFO] Enter the simple task. args=[b]
2020-03-31 20:32:37.320 [INFO] Output the answer of task B.
2020-03-31 20:32:37.328 [ERROR] Please specify one or more domain names in the argument.
2020-03-31 20:32:37.328 [ERROR] Argument is invalid.
```

###### 標準入力の指定が無い場合
```
$ java -jar simple-tasks.jar b simple.simple.jp
2020-03-31 20:32:52.786 [INFO] Enter the simple task. args=[b,simple.simple.jp]
2020-03-31 20:32:52.880 [INFO] Output the answer of task B.
2020-03-31 20:32:52.882 [ERROR] Please specify the file that lists the domain names with standard input.
2020-03-31 20:32:52.882 [ERROR] Argument is invalid.
```