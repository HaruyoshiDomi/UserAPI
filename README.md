# 🛒 簡易ECサイトAPI

Spring Boot + JWT認証 + MySQL + Docker を利用して作成した、
簡易ECサイトのWebアプリケーションです。

ログイン認証、商品一覧、カート機能、注文機能を実装し、
フロントエンドからREST APIを呼び出す構成で開発しました。

---

# 📌 概要

SES待機期間中のJava学習アウトプットとして開発しました。

Spring Boot を用いた REST API 開発だけでなく、
JWT認証・DB設計・Docker・JavaScriptによる画面連携まで、
実務を意識した構成で実装しています。

---

# 🚀 使用技術

## バックエンド

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

## フロントエンド

- HTML
- JavaScript
- Fetch API
- Thymeleaf

## DB / ミドルウェア

- MySQL
- Maven

## インフラ / 開発環境

- Docker
- Docker Compose
- Postman

---

# 🎯 実装機能

## 🔐 認証機能

- ユーザー登録
- ログイン
- JWTトークン発行
- JWT認証 / 認可

## 📦 商品機能

- 商品一覧取得
- 商品詳細取得

## 🛒 カート機能

- カート追加
- カート一覧表示
- 数量増減
- カート削除
- 合計金額計算

## 🧾 注文機能

- 注文実行
- 注文履歴取得

---

# 🖥 フロントエンド実装

- HTML / JavaScript による画面作成
- Fetch API を用いた REST API 非同期通信
- JWTトークンを localStorage に保存
- Authorization Bearer Token による認証付き通信
- DOM操作による動的画面更新
- 数量変更時のリアルタイム再描画
- 金額表示のカンマ区切り対応（toLocaleString）

---

# 📡 API一覧

| Method | URL | 内容 |
|---|---|---|
| POST | /users | ユーザー登録 |
| POST | /auth/login | ログイン |
| GET | /products | 商品一覧 |
| GET | /products/{id} | 商品詳細 |
| POST | /cart | カート追加 |
| GET | /cart | カート一覧 |
| PUT | /cart/{id}/increase | 数量増加 |
| PUT | /cart/{id}/decrease | 数量減少 |
| DELETE | /cart/{id} | カート削除 |
| POST | /orders | 注文 |
| GET | /orders | 注文履歴 |

---

# 🧱 アーキテクチャ

レイヤードアーキテクチャを採用。

```plaintext
Controller
  ↓
Service
  ↓
Repository
  ↓
MySQL
```

DTOを利用し、
Entityを直接返却しない構成で実装しています。

---

# 🧱 ER図（簡易）

```plaintext
User
 ├── Cart
 │    └── Product
 │
 └── Order
      └── OrderItem
           └── Product
```

---

# 🐳 起動方法

## Docker起動

```bash
docker-compose up --build
```

## アクセス

```plaintext
http://localhost:8080/login-page
```

---

# 🔑 API利用手順

1. ユーザー登録
2. ログイン
3. JWT取得
4. 商品一覧取得
5. カート追加
6. 注文実行
7. 注文履歴取得

---

# 💡 工夫した点

- JWTによる認証・認可実装
- ユーザーごとのカート分離
- カート追加時の数量加算対応
- DTOによるレスポンス設計
- RESTful API設計
- Dockerによる開発環境構築
- SPAライクな画面動作

---

# 📚 学んだこと

- Spring Security を用いた JWT認証
- REST API設計
- Fetch API による非同期通信
- Dockerを利用した開発環境構築
- フロントとバックエンドの連携方法
- JPAによるDB操作
- レイヤードアーキテクチャ設計

---

# 🚀 今後追加したい機能

- 商品検索
- 商品画像表示
- 管理者画面
- 在庫管理
- Pageable対応
- 例外ハンドリング強化
- React化

---

# 👤 作成者

- 道見治好
- Java / Spring Boot / C++

---

# 📌 補足

本プロジェクトは学習目的で作成した個人開発アプリです。
実務を意識した設計・実装を行っています。