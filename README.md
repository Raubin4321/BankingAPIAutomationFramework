# Banking API Automation Framework

This project is an **End-to-End API Automation Framework** built to test a Banking Application's REST APIs using **Java, Rest Assured, and TestNG**.

It focuses on validating **authentication workflows**, **user profile management**, and **account management APIs** with a **scalable, layered, and maintainable design**.

---

## Tech Stack

| Tool | Purpose |
|---|---|
| **Java** | Core programming language |
| **Rest Assured** | API automation library |
| **TestNG** | Test framework and execution |
| **Maven** | Build and dependency management |
| **ExtentReports** | HTML test reporting |
| **Log4j2** | Logging framework |
| **Jackson Databind** | JSON serialization / deserialization |

---

## Framework Highlights

- **Layered Architecture** — BaseService → Service Layer → Test Layer, keeping responsibilities cleanly separated
- **Builder Pattern** — Used in request POJOs (`SignUpRequest`, `ProfileRequest`, `UpdateProfileRequest`) for readable and flexible object construction
- **Custom Filter** — `LoggingFilter` intercepts every request and response, logging details to both Log4j2 and ExtentReports automatically
- **ExtentReports Integration** — Auto-generated HTML report
- **ThreadLocal Test Management** — `ExtentManager` uses `ThreadLocal<ExtentTest>` ensuring thread-safe reporting, ready for parallel execution
- **Token Reuse via `@BeforeClass`** — Login executed once per test class; auth token shared across all tests to avoid redundant API calls

---

## API Modules Covered

### Authentication — `/api/auth`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/signup` | Register new user |
| POST | `/api/auth/login` | User login and token generation |
| POST | `/api/auth/forgot-password` | Password recovery |

### User Management — `/api/users`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/users/profile` | Fetch user profile |
| PUT | `/api/users/profile` | Full profile update |
| PATCH | `/api/users/profile` | Partial profile update |
| PUT | `/api/users/change-password` | Change user password |

### Account Management — `/api/accounts`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/accounts` | Create new bank account |
| GET | `/api/accounts/{accountNumber}` | Fetch account by account number |

---

## Test Scenarios Covered

### AuthServiceTest
- User registration with valid data
- Login with valid credentials and token validation
- Forgot password API validation

### UserProfileManagementServiceTest
- Fetch profile with valid auth token
- Full profile update via PUT
- Partial profile update via PATCH
- Change password
  
### AccountServiceTest
- Create new bank account and validate response fields
- Fetch account by account number

---

## Project Structure

```
APIAutomationFramework
│
├── src/test/java
│   │
│   ├── com.api.base                    # Base & Service classes
│   │   ├── BaseService.java            
│   │   ├── AuthService.java            
│   │   ├── UserProfileManagementService.java
│   │   └── AccountService.java
│   │
│   ├── com.api.filters                 # Request/Response interception
│   │   └── LoggingFilter.java          # Logs to Log4j2 + ExtentReports
│   │
│   ├── com.api.listeners               # TestNG Listeners
│   │   ├── ExtentManager.java          # Manages ExtentReports
│   │   └── TestListener.java           
│   │
│   ├── com.api.models.request          # Request POJOs
│   │   ├── LoginRequest.java
│   │   ├── SignUpRequest.java           
│   │   ├── ProfileRequest.java         
│   │   ├── UpdateProfileRequest.java  
│   │   ├── ChangePasswordRequest.java
│   │   └── AccountRequest.java
│   │
│   ├── com.api.models.response         # Response POJOs
│   │   ├── LoginResponse.java
│   │   ├── UserProfileResponse.java
│   │   └── AccountResponse.java
│   │
│   └── com.api.tests                   # Test Classes
│       ├── AuthServiceTest.java
│       ├── UserProfileManagementServiceTest.java
│       └── AccountServiceTest.java
│
├── src/test/resources
│   └── log4j2.xml                      # Log4j2 configuration
│
├── reports
│   └── ExtentReport.html               # Auto-generated after test run
│
├── suite.xml                          # Test suite configuration
└── pom.xml                             # Maven dependencies
```

---

## How to Run

```bash
# Clone the repository
git clone https://github.com/Raubin4321/BankingAPIAutomationFramework.git

# Navigate to project
cd BankingAPIAutomationFramework

# Run full test suite
mvn test

# Run specific test class
mvn test -Dtest=AuthServiceTest

# Run via testng.xml
mvn test -DsuiteXmlFile=suite.xml
```

---

## Test Report

After execution, an HTML report is auto-generated at:

```
reports/ExtentReport.html
```

