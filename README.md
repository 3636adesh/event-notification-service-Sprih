# 📬 Event Notification System

A Java Spring Boot-based system to asynchronously process `EMAIL`, `SMS`, and `PUSH` notifications using dedicated FIFO queues and multithreaded processing. Includes support for callback URLs, simulated failures, graceful shutdown, and Docker-based deployment.

---

## 🛠️ Features

- ✅ REST API to submit events (`POST /api/events`)
- ⏳ FIFO queuing for EMAIL (5s), SMS (3s), and PUSH (2s) events
- 🚦 Asynchronous processing with separate threads per event type
- 🔁 Callback URL support for event status reporting (success/failure)
- ⚠️ 10% random failure simulation for realistic processing
- 🧼 Graceful shutdown with queue draining
- 🐳 Docker and Docker Compose setup
- 📖 Comprehensive unit tests with JUnit
- 🛡️ Input validation and error handling for robust APIs

---

## 📦 Tech Stack

- Java 17
- Spring Boot 3.1.5
- Maven
- Docker / Docker Compose
- Lombok
- JUnit 5
- Spring Web (for REST APIs)
- In-memory `LinkedBlockingQueue` for queuing

---

## 🚀 Running the Application

### 🧪 Option 1: Run Locally with Maven

1. Clone the repository:

   ```bash
   git clone https://github.com/3636adesh/event-notification-service-Sprih.git

   ```
2. Build the project:

   ```bash
   ./mvnw clean package
   ```
3. Run the application:

   ```bash
   java -jar target/event-notification-system-1.0-SNAPSHOT.jar
   ```

The API will be available at `http://localhost:8081/api/events`.

### 🐳 Option 2: Run with Docker Compose

1. Ensure Docker and Docker Compose are installed.
2. Build and run:

   ```bash
   docker-compose up --build
   ```

The API will be available at `http://localhost:8081/api/events`.

---

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

#### ✅ EMAIL Payload

```json
{
  "eventType": "EMAIL",
  "payload": {
    "recipient": "john.doe@example.com",
    "message": "Welcome to our service!",
    "callbackUrl": "http://example.com/email-callback"
  },
  "callbackUrl": "http://example.com/email-callback"
}
```

#### ✅ SMS Payload

```json
{
  "eventType": "SMS",
  "payload": {
    "phoneNumber": "+919876543210",
    "message": "Your OTP is 123456",
    "callbackUrl": "http://example.com/sms-callback"
  },
  "callbackUrl": "http://example.com/sms-callback"
}
```

#### ✅ PUSH Payload

```json
{
  "eventType": "PUSH",
  "payload": {
    "deviceId": "device-abc-123",
    "message": "Your order has been shipped!",
    "callbackUrl": "http://example.com/push-callback"
  },
  "callbackUrl": "http://example.com/push-callback"
}
```

#### Response Example

```json
{
  "eventId": "e123",
  "message": "Event accepted for processing."
}
```

#### Error Responses

- **400 Bad Request**: Invalid `eventType` or missing/invalid payload fields.
- **500 Internal Server Error**: System is shutting down and not accepting new events.

---

## 🔍 Testing

Run unit tests to validate core functionality:

```bash
./mvnw test
```

### Test Coverage

- Event creation and queue assignment
- FIFO processing with simulated delays
- Random failure simulation (10% failure rate)
- Graceful shutdown behavior
- API input validation and error handling
- Thread termination on shutdown

---

## 📝 Assumptions and Notes

- **Queue Implementation**: Uses in-memory `LinkedBlockingQueue` for FIFO processing.
- **Processing Times**: Simulated delays of 5s (EMAIL), 3s (SMS), and 2s (PUSH).
- **Callback Handling**: HTTP callbacks are sent using `RestTemplate` (mocked in unit tests).
- **Failure Simulation**: 10% of events fail randomly, with appropriate callback status.
- **Graceful Shutdown**: Stops accepting new events and processes existing queue before termination.
- **Docker Setup**: Exposes API on port 8080, built with `openjdk:17-jdk-slim`.

For further details, see the source code and unit tests in the repository.