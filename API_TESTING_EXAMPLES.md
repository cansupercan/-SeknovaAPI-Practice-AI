# API 測試範例

本文檔提供了所有 API 端點的測試範例，包括請求和預期響應。

## 認證 API（不需要 Token）

### 1. 用戶註冊

**端點:** `POST /api/auth/register`

**請求示例:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123",
    "country": "Taiwan"
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Registration successful",
  "data": null
}
```

**失敗響應 - Email 已存在 (200):**
```json
{
  "status": "not",
  "message": "Email already registered",
  "data": null
}
```

---

### 2. 用戶登入

**端點:** `POST /api/auth/login`

**請求示例:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Login successful",
  "data": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzMzMzk0MDAwLCJleHAiOjE3MzM0ODA0MDB9.xxxx"
}
```

**失敗響應 - 用戶不存在 (200):**
```json
{
  "status": "not",
  "message": "Login failed: User not found",
  "data": null
}
```

**失敗響應 - 密碼錯誤 (200):**
```json
{
  "status": "not",
  "message": "Invalid password",
  "data": null
}
```

---

## 用戶信息 API（需要 Token）

### 3. 設定用戶信息

**端點:** `POST /api/profile/information`

**請求示例:**
```bash
curl -X POST http://localhost:8080/api/profile/information \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "birthDay": "1990-01-15",
    "phone": "09123456789",
    "address": "123 Main St, Taipei",
    "gender": "Male",
    "height": 175,
    "weight": 70,
    "race": "Asian",
    "liquor": "Moderate",
    "smoke": false,
    "check": true,
    "phoneVerified": true
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "User information updated successfully",
  "data": null
}
```

---

### 4. 獲取用戶信息

**端點:** `GET /api/profile/information`

**請求示例:**
```bash
curl -X GET http://localhost:8080/api/profile/information \
  -H "Authorization: Bearer <token>"
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "User information retrieved successfully",
  "data": {
    "id": 1,
    "userId": 1,
    "firstName": "John",
    "lastName": "Doe",
    "birthDay": "1990-01-15",
    "phone": "09123456789",
    "address": "123 Main St, Taipei",
    "gender": "Male",
    "height": 175,
    "weight": 70,
    "race": "Asian",
    "liquor": "Moderate",
    "smoke": false,
    "check": true,
    "phoneVerified": true
  }
}
```

---

### 5. 獲取用戶認證信息

**端點:** `GET /api/auth/UserAuth`

**請求示例:**
```bash
curl -X GET http://localhost:8080/api/auth/UserAuth \
  -H "Authorization: Bearer <token>"
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "User auth retrieved successfully",
  "data": {
    "userId": 1,
    "email": "user@example.com",
    "password": "$2a$10$...",
    "country": "Taiwan",
    "isFirst": true
  }
}
```

---

## 事件 API（需要 Token）

### 6. 獲取所有事件

**端點:** `GET /api/event/Eventall`

**請求示例:**
```bash
curl -X GET http://localhost:8080/api/event/Eventall \
  -H "Authorization: Bearer <token>"
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Events retrieved successfully",
  "data": [
    {
      "id": 1,
      "userId": 1,
      "dateTime": "2025-12-05T10:00:00",
      "displayTime": "2025-12-05 10:00",
      "eventAttribute": ["attr1", "attr2"],
      "eventId": 101,
      "eventValue": 50,
      "note": "Test event",
      "check": true
    },
    {
      "id": 2,
      "userId": 1,
      "dateTime": "2025-12-05T11:00:00",
      "displayTime": "2025-12-05 11:00",
      "eventAttribute": ["attr3"],
      "eventId": 102,
      "eventValue": 60,
      "note": "Another event",
      "check": false
    }
  ]
}
```

---

### 7. 新增事件

**端點:** `POST /api/event/add`

**必填字段:** `DateTime`, `EventId`, `EventValue`

**請求示例:**
```bash
curl -X POST http://localhost:8080/api/event/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "dateTime": "2025-12-05T10:00:00",
    "displayTime": "2025-12-05 10:00",
    "eventAttribute": ["attr1", "attr2"],
    "eventId": 101,
    "eventValue": 50,
    "note": "Test event",
    "check": true
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Event added successfully",
  "data": null
}
```

---

### 8. 修改事件

**端點:** `PATCH /api/event/change?id=<id>`

**必填參數:** `id` (查詢參數)

**請求示例:**
```bash
curl -X PATCH http://localhost:8080/api/event/change?id=1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "eventValue": 100,
    "note": "Updated event"
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Event updated successfully",
  "data": null
}
```

---

### 9. 刪除事件

**端點:** `DELETE /api/event/delete?id=<id>`

**必填參數:** `id` (查詢參數)

**請求示例:**
```bash
curl -X DELETE http://localhost:8080/api/event/delete?id=1 \
  -H "Authorization: Bearer <token>"
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Event deleted successfully",
  "data": null
}
```

---

## 記錄 API（需要 Token）

### 10. 獲取所有記錄

**端點:** `GET /api/record/Recordall`

**請求示例:**
```bash
curl -X GET http://localhost:8080/api/record/Recordall \
  -H "Authorization: Bearer <token>"
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Records retrieved successfully",
  "data": [
    {
      "id": 1,
      "userId": 1,
      "count": 5,
      "recordTime": "2025-12-05T10:00:00",
      "displayTime": "2025-12-05 10:00",
      "temperature": 36,
      "adc1": 100,
      "battery": 80,
      "check": true,
      "calibrated": true
    }
  ]
}
```

---

### 11. 新增記錄

**端點:** `POST /api/record/add`

**必填字段:** `DateTime`, `EventId`, `EventValue`, `Count`

**請求示例:**
```bash
curl -X POST http://localhost:8080/api/record/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "count": 5,
    "recordTime": "2025-12-05T10:00:00",
    "displayTime": "2025-12-05 10:00",
    "temperature": 36,
    "adc1": 100,
    "battery": 80,
    "check": true,
    "calibrated": true
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Record added successfully",
  "data": null
}
```

---

### 12. 修改記錄

**端點:** `PATCH /api/record/change?id=<id>`

**必填參數:** `id` (查詢參數)

**請求示例:**
```bash
curl -X PATCH http://localhost:8080/api/record/change?id=1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "temperature": 37,
    "battery": 75
  }'
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Record updated successfully",
  "data": null
}
```

---

### 13. 刪除記錄

**端點:** `DELETE /api/record/delete?id=<id>`

**必填參數:** `id` (查詢參數)

**請求示例:**
```bash
curl -X DELETE http://localhost:8080/api/record/delete?id=1 \
  -H "Authorization: Bearer <token>"
```

**成功響應 (200):**
```json
{
  "status": "ok",
  "message": "Record deleted successfully",
  "data": null
}
```

---

## 使用 Postman 進行測試

### 1. 導入集合
可以將上述 cURL 命令導入到 Postman 中。

### 2. 設置環境變量
```
{
  "base_url": "http://localhost:8080",
  "token": "your_jwt_token_here"
}
```

### 3. 使用 Authorization Header
在 Postman 中，設置 Authorization 類型為 `Bearer Token`，並輸入從登入 API 獲得的 token。

---

## 常見錯誤和解決方案

| 錯誤 | 原因 | 解決方案 |
|------|------|--------|
| `Invalid token` | Token 無效或過期 | 重新登入獲取新的 token |
| `User not found` | 用戶不存在 | 檢查 email 是否正確 |
| `Email already registered` | Email 已被註冊 | 使用不同的 email 或登入 |
| `Invalid password` | 密碼錯誤 | 檢查密碼是否正確 |
| `Event not found` / `Record not found` | ID 不存在 | 確保 ID 正確 |

---

## 測試流程範例

```
1. 註冊新用戶
   POST /api/auth/register

2. 登入獲取 Token
   POST /api/auth/login

3. 設定用戶信息
   POST /api/profile/information

4. 獲取用戶信息
   GET /api/profile/information

5. 新增事件
   POST /api/event/add

6. 獲取所有事件
   GET /api/event/Eventall

7. 修改事件
   PATCH /api/event/change?id=1

8. 新增記錄
   POST /api/record/add

9. 獲取所有記錄
   GET /api/record/Recordall

10. 修改記錄
    PATCH /api/record/change?id=1

11. 刪除事件
    DELETE /api/event/delete?id=1

12. 刪除記錄
    DELETE /api/record/delete?id=1
```

---

## 注意事項

- 所有需要 Token 的請求都必須在 `Authorization` 頭中包含 `Bearer <token>`
- Token 的有效期為 24 小時（默認配置）
- 所有請求都應使用 `Content-Type: application/json`
- 修改和刪除操作都需要提供資源的 ID
- 新增事件和記錄的某些字段是必填的，其他字段是可選的

