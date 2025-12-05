# UserAuth
- **UserId**：long
- **email**：String 
- **password**：String 
- **country**：String 
- **isFirst**：boolean(true)
- **UserInformation**：UserInformation (one to one)
- **Event**：Event (one to many)
- **Record**：Record (one to many)


# UserInformation
- **Id**：long (主鍵)
- **UserId**：long (外鍵)
- **FirstName**：String  
- **LastName**：String  
- **Birthady**：String    
- **Phone**：String  
- **Address**：String  
- **Gender**：String  
- **Height**：Int  
- **Weight**：Int  
- **Race**：String  
- **Liquor**：String  
- **Smoke**：Bool  
- **Check**：Bool  
- **Phone_Verified**：Bool  

# Event

- **Id**：long (主鍵)
- **UserId**：long (外鍵)
- **DateTime**：String  
- **DisplayTime**：String  
- **EventAttribute**：[String]  
- **EventId**：Int  
- **EventValue**：Int  
- **Note**：String  
- **Check**：Bool  

# Record
- **Id**：long (主鍵)
- **UserId**：long (外鍵)
- **Count**：Int  
- **RecordTime**：String  
- **DisplayTime**：String  
- **Tempertaute**：Int  
- **Adc1**：Int  
- **Battery**：Int  
- **Check**：Bool  
- **Calibrated**：Bool  

# 使用技術
- loombook
- spring web
- spring Data JPA
- postgresql driver
- spring security

# postgresql設定
```properties
spring.application.name=SeknovaAPI-Practice

spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.username=postgres
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
```

# ＡＰＩ（不需token）
## /auth/register(post)
功能：註冊用戶 自動生成ＩＤ
必填需求資料:email(string) password(string) country(String)
返回：status(string)[ok,not] message(string)
## /auth/login (post)
功能：用戶登入 自動生成JWT 用token
必填需求資料:email(string) password(string)
返回：status(string)[ok,not] message(string) token(string)

# ＡＰＩ（需token）
## /profile/Information(post)
功能：設定UserInformation資料 
選填輸入資料: FirstName*：String  LastName：String  Birthady：String    Phone：String  Address：String  Gender：String  Height：Int  Weight：Int  Race：String  Liquor：String  Smoke：Bool  Check：Bool  Phone_Verified：Bool  
返回：status(string)[ok,not] message(string)
## /profile/Information(get)
功能：取得UserInformation資料 
返回：status(string)[ok,not] message(string) UserInformation(UserInformation)
## /auth/UserAuth(get)
功能：取得UserAuth資料 
返回：status(string)[ok,not] message(string) UserAuth(UserAuth)
## /event/Eventall(get)
功能：取得帳號的所有的Event資料 
返回：status(string)[ok,not] message(string) Event(Event)
## /record/Recordall(get)
功能：取得帳號的所有的record資料 
返回：status(string)[ok,not] message(string) Record(Record)
## /event/add(post)
功能：新增event
必填需求資料: DateTime：String  EventId：Int  EventValue：Int
選填輸入資料: DisplayTime：String  EventAttribute：[String]  Note：String  Check：Bool  
返回：status(string)[ok,not] message(string) 
## /record/add(post)
功能：新增Record
必填需求資料: DateTime：String  EventId：Int  EventValue：Int Count：Int 
選填輸入資料:  RecordTime：String  DisplayTime：String Tempertaute：Int  Adc1：Int  Battery：Int  Check：Bool  Calibrated：Bool      
返回：status(string)[ok,not] message(string) 
## /event/change(patch)
功能：修改event
必填需求資料: ID(long) 
選填輸入資料: DateTime：String  EventId：Int  EventValue：Int DisplayTime：String  EventAttribute：[String]  Note：String  Check：Bool  
返回：status(string)[ok,not] message(string) 
## /record/change(patch)
功能：修改Record
必填需求資料: ID(long) 
選填輸入資料: Count：Int  RecordTime：String  DisplayTime：String Tempertaute：Int  Adc1：Int  Battery：Int  Check：Bool  Calibrated：Bool   
返回：status(string)[ok,not] message(string) 
## /event/delete(delete)
功能：刪除event
必填需求資料: ID(long) 
返回：status(string)[ok,not] message(string) 
## /record/delete(delete)
功能：刪除Record
必填需求資料: ID(long) 
返回：status(string)[ok,not] message(string) 