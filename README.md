# 🛒 簡易ECサイトAPI（Spring Boot + JWT）

---

## 📌 概要

本プロジェクトは、JWT認証を用いた簡易ECサイトのバックエンドAPIです。
ユーザー認証から商品閲覧、カート管理、注文、注文履歴取得まで、一連のEC機能を実装しています。

---

## 🚀 使用技術

* Java（Spring Boot）
* Spring Security（JWT認証）
* Spring Data JPA / Hibernate
* MySQL
* Maven
* Postman（API動作確認）

---

## 🎯 機能一覧

### 🔐 認証
*ユーザー登録（POST /users）
* ログイン（JWTトークン発行）

### 📦 商品機能

* 商品一覧取得（GET /products）
* 商品詳細取得（GET /products/{id}）

### 🛒 カート機能

* カート追加（POST /cart）
* カート一覧取得（GET /cart）

### 🧾 注文機能

* 注文実行（POST /orders）
* 注文履歴取得（GET /orders）
* 合計金額計算機能

---

## 📡 API仕様

---

### 👤 ユーザー登録
```
POST /users
```
#### Request

```json
{
  "name": "Domi",
  "email": "domi@example.com",
  "password": "password"
}
```

#### Response

```json
{
  "id": 1,
  "name": "Domi",
  "email": "domi@example.com"
}
```

---

### 🔐 ログイン

```
POST /login
```

#### Request

```json
{
  "email": "test@example.com",
  "password": "password"
}
```

#### Response

```json
{
  "token": "JWT_TOKEN"
}
```

---

### 📦 商品一覧取得

```
GET /products
```

---

### 📦 商品詳細取得

```
GET /products/{id}
```

---

### 🛒 カート追加

```
POST /cart
```

#### Header

```
Authorization: Bearer {JWT}
```

#### Request

```json
{
  "productId": 1,
  "quantity": 2
}
```

---

### 🛒 カート一覧取得

```
GET /cart
```

#### Header

```
Authorization: Bearer {JWT}
```

---

### 🧾 注文実行

```
POST /orders
```

#### Header

```
Authorization: Bearer {JWT}
```

---

### 🧾 注文履歴取得

```
GET /orders
```

#### Header

```
Authorization: Bearer {JWT}
```

#### Response例

```json
[
  {
    "orderId": 1,
    "orderDate": "2026-04-08T12:00:00",
    "items": [
      {
        "productId": 1,
        "productName": "iPhone",
        "price": 120000,
        "quantity": 1
      }
    ],
    "totalAmount": 120000
  }
]
```

---

## 🧱 ER図（簡易）

```
User
 ├── Cart
 │    └── CartItem
 │         └── Product
 │
 └── Order
      └── OrderItem
           └── Product
```

---

## 🛠 実行方法

### ① リポジトリをクローン

```
git clone <https://github.com/HaruyoshiDomi/UserAPI.git>
```

---

### ② データベース設定

application.properties または application.yml にDB設定を記載

（例：H2使用時）

```
spring.datasource.url=jdbc:h2:mem:testdb
```

---

### ③ アプリ起動

```
mvn spring-boot:run
```

---

### ④ API実行手順（Postman推奨）

1. `/users` でユーザー登録
2. `/login` でJWT取得
3. `/products` で商品確認
4. `/cart` で商品追加
5. `/orders` で注文
6. `/orders` で履歴確認

---

## 💡 工夫した点

* JWTによる認証・認可の実装
* ユーザーごとのデータ分離（カート・注文）
* DTOを用いたレスポンス設計
* 注文時の合計金額計算ロジック実装
* レイヤードアーキテクチャ（Controller / Service / Repository）

---

## 🚀 今後の改善点

* 在庫管理機能の追加
* ページング対応（Pageable）
* 例外ハンドリングの強化（@ControllerAdvice）
* 決済機能の追加
* フロントエンド（Reactなど）との連携

---

## 👤 作成者

* 名前：道見治好
* スキル：Java / Spring Boot / C++

---

## 📌 補足

本プロジェクトは学習目的で作成した簡易的なECサイトAPIです。
実務を想定した設計・実装を意識して開発しています。

---
