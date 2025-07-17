# 📬 Event Notification System

A Java Spring Boot-based system to asynchronously process `EMAIL`, `SMS`, and `PUSH` notifications using dedicated FIFO queues and multithreaded processing. Includes support for callback URLs, simulated failures, graceful shutdown, and Docker-based deployment.

---

## 🛠️ Features

- ✅ REST API to submit events
- ⏳ FIFO queuing for EMAIL, SMS, and PUSH events
- 🚦 Asynchronous processing using separate threads
- 🔁 Callback URL support for event status reporting
- ⚠️ Random failure simulation
- 🧼 Graceful shutdown with queue drain
- 🐳 Docker & Docker Compose setup
- 📖 Swagger UI for API documentation

---

## 📦 Tech Stack

- Java 21
- Spring Boot 3.5
- Maven
- Docker / Docker Compose
- Swagger (Springdoc OpenAPI)
- Lombok
- JUnit

---

## 🚀 Running the Application

### 🧪 Option 1: Run Locally with Maven

```bash
./mvnw clean package
java -jar target/event-notification-service-0.0.1-SNAPSHOT.jar


```
### 🐳 Option 2: Run with Docker Compose

```bash
docker-compose up --build
```

## 📤 API Endpoint

### ➕ Submit Event

**POST** `/api/events`

#### Request Body Format
```json
{
  "eventType": "EMAIL | SMS | PUSH",
  "payload": { ... },
  "callbackUrl": "http://your-callback-url.com/notify"
}
```

✅ SMS Payload
```json
{
  "eventType": "SMS",
  "payload": {
    "phoneNumber": "+919876543210",
    "message": "Your OTP is 123456"
  },
  "callbackUrl": "http://example.com/sms-callback"
}
```


✅ EMAIL Payload
```json
{
  "eventType": "EMAIL",
  "payload": {
    "recipient": "john.doe@example.com",
    "message": "Your invoice is ready"
  },
  "callbackUrl": "http://example.com/email-callback"
}
```

✅ PUSH Payload
```json
{
  "eventType": "PUSH",
  "payload": {
    "deviceId": "device-abc-123",
    "message": "You have a new alert"
  },
  "callbackUrl": "http://example.com/push-callback"
}
```


## 🔍 Swagger UI

Access Swagger UI at:  
👉 [swagger](http://localhost:8081/swagger-ui/index.html)

