# SeknovaAPI-Practice

這是一個根據 `setting.md` 規範實現的 Spring Boot REST API 項目。

## 項目結構

```
SeknovaAPI/
├── src/main/java/com/seknova/api/
│   ├── entity/              # 實體類
│   ├── repository/          # 數據訪問層
│   ├── service/             # 業務邏輯層
│   ├── controller/          # 控制層
│   ├── dto/                 # 數據傳輸對象
│   ├── config/              # 配置類
│   └── SeknovaApiApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## 技術棧

- Spring Boot 3.1.5
- Spring Data JPA
- Spring Security
- PostgreSQL
- JWT (JSON Web Token)
- Lombok
- Maven

## 先決條件

- Java 17 或更高版本
- Maven 3.6+
- PostgreSQL 12+

## 數據庫設置

1. 創建 PostgreSQL 數據庫：
```sql
CREATE DATABASE testdb;
```

2. 配置 `application.properties` 中的數據庫連接信息

## 構建和運行

### 使用 Maven 構建

```bash
mvn clean install
```

### 運行應用程序

```bash
mvn spring-boot:run
```

應用程序將在 `http://localhost:8080` 上運行

## API 端點

### 認證相關（不需要 Token）

- `POST /api/auth/register` - 用戶註冊
- `POST /api/auth/login` - 用戶登入

### 用戶相關（需要 Token）

- `POST /api/profile/information` - 設定用戶信息
- `GET /api/profile/information` - 獲取用戶信息
- `GET /api/auth/UserAuth` - 獲取用戶認證信息

### 事件相關（需要 Token）

- `GET /api/event/Eventall` - 獲取所有事件
- `POST /api/event/add` - 添加事件
- `PATCH /api/event/change?id=<id>` - 修改事件
- `DELETE /api/event/delete?id=<id>` - 刪除事件

### 記錄相關（需要 Token）

- `GET /api/record/Recordall` - 獲取所有記錄
- `POST /api/record/add` - 添加記錄
- `PATCH /api/record/change?id=<id>` - 修改記錄
- `DELETE /api/record/delete?id=<id>` - 刪除記錄

## 認證

需要 Token 的 API 應在請求頭中包含：
```
Authorization: Bearer <token>
```

Token 通過登入 API 獲得。

## API 響應格式

所有 API 都返回以下格式的 JSON：

```json
{
  "status": "ok|not",
  "message": "說明信息",
  "data": {}
}
```

## 配置

### JWT 配置

編輯 `application.properties`：

```properties
jwt.secret=your-secret-key-here-change-in-production
jwt.expiration=86400000
```

- `jwt.secret`: JWT 簽名密鑰（建議生成環境中改為更安全的密鑰）
- `jwt.expiration`: Token 過期時間（毫秒），默認為 24 小時

## 開發

### 項目符合設定

此項目完全按照 `setting.md` 中的規範實現：

- ✅ 所有實體類定義匹配
- ✅ 所有 API 端點實現
- ✅ 正確的 HTTP 方法（GET, POST, PATCH, DELETE）
- ✅ JWT 認證實現
- ✅ PostgreSQL 數據庫配置

## 相關文檔

- [API 規範](../setting.md) - 項目的 API 規範定義
- [API 測試範例](./SeknovaAPI/API_TESTING_EXAMPLES.md) - 完整的 API 測試範例和 cURL 命令

## 許可證

MIT License
