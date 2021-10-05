# ITworksTRYshopping

## [アプリ概要]
管理者がオンラインでPCを販売できるシステムです。  
会員登録を済ませた会員ユーザーがショッピングを楽しめます。  
* アプリURL： ********************************

## [主な使用技術]
##### 「プログラミング」
* Java8
* HTML5
* CSS3
* spring boot 2.4.2
* thymeleaf 3.0.4
* bootstrap 4.2.1
* jQuery 3.5.1
##### 「作成環境」
* Eclipse 4.20.0
* MySql 8.0.25
* GitHub 2.27.0
##### 「機能」
* tomcat 9.0.41
* apache 4.0.0
* springsecurity5
* lombok 1.2.3
* Devtools 2.4.2
* JDBC 5.3.3
* web 5.3.3
* font-awesome 4.7.0



 ## [設計書類]
 
 
### 1.[DB設計書(MySql)]  
アプリのDB情報が確認できます。

* #### DB設計書(Googleスプレッドシート): https://docs.google.com/spreadsheets/d/1nZIzJ4DxDax34mMemlDDlxrMyzPACwVXFYjqFpDzCVo/edit?usp=sharing

### 2.[要件定義書(XMind)]  
プログラム前に必要なデータや機能を考え作成しました。  
現在のアプリは要件定義書の状態から、修正を加えているためデータや機能が追加されています。  
* #### 要件定義書: https://user-images.githubusercontent.com/83486993/135418067-ed2f9605-ec3a-4c18-aadd-f737787f550e.jpg

### 3.[画面設計図(draw.io)]  
プログラム前にログイン画面から購入までの流れの画面設計図を作成しました。  
現在のアプリは設計図の状態から、修正を加えているため変化しています。
* #### ログイン画面:  https://user-images.githubusercontent.com/83486993/135964539-2284b77c-23a6-431e-ada1-a0d7b3f92aa7.jpg
* #### 新規ユーザー登録画面: https://user-images.githubusercontent.com/83486993/135964366-345714b8-f62c-4a92-b705-4f14f9c42d3c.jpg
* #### 商品一覧画面: https://user-images.githubusercontent.com/83486993/135964749-a724d55c-d237-4304-b64a-d2d08720ab69.jpg
* #### 商品詳細画面: https://user-images.githubusercontent.com/83486993/135965041-e2296cea-c468-4e58-8424-33a6c85e63fb.jpg
* #### カート画面: https://user-images.githubusercontent.com/83486993/135965547-99b9b620-49b0-4a70-b326-0c53a65b5172.jpg
* #### 決済画面: https://user-images.githubusercontent.com/83486993/135965606-ad78fc07-c19d-48be-9d8d-d371a3fe6385.jpg
* #### 最終確認画面: https://user-images.githubusercontent.com/83486993/135965720-30eaee43-b8e5-413b-94ce-588c53ae9bfb.jpg
* #### 購入後詳細画面: https://user-images.githubusercontent.com/83486993/135965761-cd47b116-eb67-4cb1-a69c-dcbb04e7951c.jpg








### 4.[画面遷移図(draw.io)]  
プログラム前にログイン画面から購入までの流れの画面遷移図を作成しました。  
現在のアプリは画面遷移図の状態から、修正を加えているため変化している所もあります。
* #### 遷移図: https://user-images.githubusercontent.com/83486993/134855522-eab3cebd-279c-41f1-b992-8786d43c81b3.jpg
* #### 遷移図詳細書: https://user-images.githubusercontent.com/83486993/134855490-9ebac49c-8863-416c-bf2e-845dc2ce8615.jpg  



## [アプリ説明] ##

管理者が商品を追加し、ユーザー登録をした会員ユーザーが商品を購入できるシステムです。  
実際にユーザー登録をして、機能を堪能することが可能です。  
* アプリURL：*****************************  

1.URLにアクセスするとログイン前画面が表示されます。サイドバーの新規ユーザー登録からユーザー登録をしてから、登録したIDとパスワードでログインができます。
![ログイン前画面-crop](https://user-images.githubusercontent.com/83486993/134625852-d02dbba7-68af-40fc-a1b8-d996f31eee8a.png)

2.ログインすると商品一覧画面が表示され、さまざまな機能が利用可能になります。
![ログイン後画面](https://user-images.githubusercontent.com/83486993/134626218-54cfcd64-e41a-443d-ab76-281a2c2fd6b5.png)

※管理者専用画面は会員様には表示されません。


3.管理者専用画面は開発者専用IDとパスワードで表示されます。管理者のみが可能な機能や、閲覧可能な情報があります。
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









