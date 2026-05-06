# Banking API Automation Framework

This project is an **End-to-End API Automation Framework** built to test a Banking Application’s REST APIs using **Java, Rest Assured, and TestNG**.

It focuses on validating **authentication workflows** and **user profile management APIs** with a **scalable and maintainable design**.

---

## Project Overview

The framework automates critical banking functionalities such as:

- User Authentication (Login, Signup, Password Management)
- User Profile Management
- Secure API validation with authorization

It is designed using:
- Modular architecture  
- Reusable components  
- Centralized configurations  

---

## Tech Stack

- **Language:** Java  
- **API Automation:** Rest Assured  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Reporting:** Logs / Custom Reporting  
- **CI/CD:** Jenkins  

---

## API Modules Covered

### Authentication APIs

- `POST /api/auth/signup` → Register new user  
- `POST /api/auth/login` → User login  
- `POST /api/auth/forgot-password` → Password recovery    

### User Management APIs

- `GET /api/users/profile` → Fetch user profile  
- `PUT /api/users/profile` → Update user profile  

---

## Test Scenarios Covered

### Authentication

- User registration with valid/invalid data  
- Login with valid & invalid credentials  
- Token generation and validation  
- Forgot password validation  

### User Management

- Fetch profile with valid token  
- Update profile details  

---

## Project Structure

```bash
APIAutomationFramework
│
├── src/main/java
│   └── resources                # Configuration files
│
├── src/test/java
│   ├── com.api.base             # Base & Service classes
│   │   ├── BaseService.java
│   │   ├── AuthService.java
│   │   └── UserProfileManagementService.java
│   │
│   ├── com.api.filters          # Logging
│   │   └── LoggingFilter.java
│   │
│   ├── com.api.listeners        # TestNG Listeners
│   │   └── TestListener.java
│   │
│   ├── com.api.models.request   # Request POJOs
│   │   ├── LoginRequest.java
│   │   ├── SignUpRequest.java
│   │   └── ProfileRequest.java
│   │
│   ├── com.api.models.response  # Response POJOs
│   │   ├── LoginResponse.java
│   │   └── UserProfileResponse.java
│   │
│   ├── com.api.tests            # Test Classes
│       ├── AccountCreationTest.java
│       ├── LoginAPITest.java
│       ├── ForgotPasswordTest.java
│       ├── GetProfileRequestTest.java
│       ├── UpdateProfileTest.java
│       ├── LoginAPITest2.java
│       └── LoginAPITest3.java
│
├── src/test/resources
│   └── log4j2.xml               # Logging configuration
│
├── testng.xml                   # Test suite
├── pom.xml                      # Dependencies

```

## How to Run
```bash
git clone https://github.com/Raubin4321/APIAutomationFramework.git
cd APIAutomationFramework
mvn clean test

```
