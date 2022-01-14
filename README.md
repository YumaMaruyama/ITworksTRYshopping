# ITworksTRYshopping
![iOS の画像New](https://user-images.githubusercontent.com/83486993/149527184-521387c2-e880-41c9-b7a1-deb86ec6c55c.png)


## [アプリ概要]
管理者がオンラインでPCを販売できるシステムです。  
会員登録を済ませた会員ユーザーが買い物をすることができます。  

アプリに必要な新規ユーザー登録につきましては適当な架空のものを入力していただいてアプリを触っていただけます。  
  [サンプル]  
 * メールアドレス[ログインに使用]　○○○○@testmailcom　○のところに適当な半角英数字を入力して下さい。
 * パスワード[ログインに使用]　　○○○○testpass　○のところに適当な半角英数字を入力して下さい。
 * ユーザー名　アプリ太郎
 * 生年月日　適当な日にちを選択
 * 商品受け取り住所　大阪府アプリ市確認町1丁目2番地9号

### アプリURL： http://133.167.92.108:8082/login

## [使用技術]
* Java8
* HTML5
* CSS3
* spring boot 2.4.2
* thymeleaf 3.0.4
* bootstrap 4.2.1
* jQuery 3.5.1

## [動作環境]
* MariaDB 10.3.28
* apache 2.4.37

 ## [設計書類]
 
 
### 1.[DB設計書(MySql)]  

* #### DB設計書(Googleスプレッドシート): https://docs.google.com/spreadsheets/d/1nZIzJ4DxDax34mMemlDDlxrMyzPACwVXFYjqFpDzCVo/edit?usp=sharing

### 2.[要件定義書(XMind)]  
  
* #### 要件定義書: https://user-images.githubusercontent.com/83486993/135418067-ed2f9605-ec3a-4c18-aadd-f737787f550e.jpg

### 3.[画面設計図(draw.io)]  

* #### ログイン画面:  https://user-images.githubusercontent.com/83486993/135964539-2284b77c-23a6-431e-ada1-a0d7b3f92aa7.jpg
* #### 新規ユーザー登録画面: https://user-images.githubusercontent.com/83486993/135964366-345714b8-f62c-4a92-b705-4f14f9c42d3c.jpg
* #### 商品一覧画面: https://user-images.githubusercontent.com/83486993/135966112-00a6de49-7095-4b28-bebf-cfd305d0c47c.jpg
* #### 商品詳細画面: https://user-images.githubusercontent.com/83486993/135965041-e2296cea-c468-4e58-8424-33a6c85e63fb.jpg
* #### カート画面: https://user-images.githubusercontent.com/83486993/135965547-99b9b620-49b0-4a70-b326-0c53a65b5172.jpg
* #### 決済画面: https://user-images.githubusercontent.com/83486993/135965606-ad78fc07-c19d-48be-9d8d-d371a3fe6385.jpg
* #### 最終確認画面: https://user-images.githubusercontent.com/83486993/135965720-30eaee43-b8e5-413b-94ce-588c53ae9bfb.jpg
* #### 購入後詳細画面: https://user-images.githubusercontent.com/83486993/135965761-cd47b116-eb67-4cb1-a69c-dcbb04e7951c.jpg



### 4.[画面遷移図(draw.io)]  

* #### 遷移図: https://user-images.githubusercontent.com/83486993/134855522-eab3cebd-279c-41f1-b992-8786d43c81b3.jpg
* #### 遷移図詳細書: https://user-images.githubusercontent.com/83486993/134855490-9ebac49c-8863-416c-bf2e-845dc2ce8615.jpg  



## [アプリ説明] ##
  
* アプリURL：http://133.167.92.108:8082/login

1.URLにアクセスするとログイン前画面が表示されます。サイドバーの新規ユーザー登録からユーザー登録をしてから、登録したIDとパスワードでログインができます。
![ログイン前画面-crop](https://user-images.githubusercontent.com/83486993/134625852-d02dbba7-68af-40fc-a1b8-d996f31eee8a.png)

2.ログインすると商品一覧画面が表示され、さまざまな機能が利用可能になります。
![ログイン後画面](https://user-images.githubusercontent.com/83486993/134626218-54cfcd64-e41a-443d-ab76-281a2c2fd6b5.png)

※管理者専用画面は会員様には表示されません。


3.管理者用IDとパスワードでログインすると管理者専用画面が表示されます。通常のログイン画面に、管理者のみが閲覧、登録可能な機能を付与した画面になります。
![管理者専用画面](https://user-images.githubusercontent.com/83486993/135576676-e0ea6d51-dd0e-4ffa-9efa-4123fc922f05.png)



3.1 ユーザー管理機能
　会員ユーザーの情報が確認できます。購入した商品や、退会させることが可能です。

3.2 商品追加機能
　商品一覧に表示される商品を追加することができます。

3.3 クーポン追加機能
　クーポン一覧画面に表示されるクーポンを発行できます。

3.4 ポイント倍率変更機能
　購入時ポイント付与倍率の変更ができます。1％から9％までに対応しています。

3.5 お問い合わせ内容管理機能
　ログイン前とログイン後のユーザーからのお問い合わせが確認できます。返信や削除が可能です。









