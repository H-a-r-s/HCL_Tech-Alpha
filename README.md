# Retail Ordering Platform – Frontend

Modern React-based frontend application for Retail Ordering Platform.

---

# Overview

This frontend allows users to:

- Register / Login
- Browse products
- Add items to cart
- Place orders
- View order history
- Quickly reorder previous purchases

It integrates with the Spring Boot microservices backend via API Gateway.

---

# Tech Stack

- React (Vite)
- React Router DOM
- Axios
- Context API (Auth Management)
- JavaScript (ES6+)
- CSS / Tailwind (optional)

---

# Project Structure

```
src/
│
├── api/ 
├── context/ 
├── components/ 
├── pages/ 
├── App.jsx
└── main.jsx
```

---

# Authentication Flow

1. User logs in
2. Backend returns JWT token
3. Token stored in localStorage
4. Axios interceptor attaches token to requests
5. Protected routes guarded using PrivateRoute

---

# API Integration

All API requests routed through:

```
http://localhost:8080
```

Using centralized Axios instance:

- Automatically attaches JWT
- Handles authentication headers

---

# Running the Frontend

### Install Dependencies

```
npm install
```

### Start Development Server

```
npm run dev
```

Default:
```
http://localhost:5173
```

# Pages Implemented

- Cart.jsx
- Home.jsx
- Login.jsx
- OrderConfirmation.jsx
- OrderHistory.jsx
- Products.jsx
- Register.jsx
- UserDashboard.jsx

# Order Flow (Frontend)

- User adds products to cart
- Reviews cart
- Clicks Place Order
- Redirects to confirmation
- Order appears in Order History
- User can click Reorder

# State Management

- AuthContext handles login/logout
-  LocalStorage stores JWT
- React hooks manage local component state


# Environment Variables

- Create .env:
```
VITE_API_BASE_URL=http://localhost:8080
```



# Retail Ordering Platform – Backend

Enterprise-grade microservices backend built using Spring Boot for a Retail Ordering Website.

## Overview

This backend powers a secure and scalable retail ordering system where customers can:

- Browse brands, categories, and products
- Add products to cart
- Place orders
- Automatically update inventory
- Receive email order confirmations
- View order history
- Quickly reorder past purchases

The architecture follows a Microservices pattern with API Gateway and JWT-based security.

---

# Architecture

```
Frontend (React)
│
▼
API Gateway (JWT Validation + Routing)
│
├── Auth Service
├── Product Service
├── Cart Service
├── Order Service
├── Inventory Service
└── Notification Service
```

---

# Tech Stack

- Java 21
- Spring Boot 3.2.4
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- Railway(DB)
- JavaMailSender (Email)
- Swagger (OpenAPI)
- Maven

---

# Microservices Overview

## API Gateway 

- Central entry point
- JWT validation
- Request routing

## Auth Service 

- User Registration
- Login
- JWT Token Generation
- Role-based access (CUSTOMER / ADMIN)

## Product Service 

- Manage brands
- Manage categories
- Manage products

## Cart Service 

- Add to cart
- Update quantity
- Remove item
- Quick reorder

## Order Service 

- Place order
- Order history
- Order summary
- Status update

## Inventory Service 

- Stock management
- Stock validation
- Auto decrement on order

## Notification Service 

- Email order confirmation
- HTML email template using Thymeleaf

---

# Security

- JWT-based stateless authentication
- Role-based authorization
- Gateway-level token validation
- Secure password hashing (BCrypt)

---

# Order Placement Flow

1. User places order
2. Cart fetched
3. Inventory validated
4. Order + OrderItems stored (snapshot pricing)
5. Inventory reduced
6. Cart cleared
7. Email sent
8. Confirmation returned

---

# Database Schema

Tables:

- brands
- cart_items
- cart_service
- categories
- inventory
- orders
- order_items
- product_service
- products
- users

---

# Email Configuration

Configure SMTP in `application.yml`:

````
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
    properties:
      mail.smtp.auth: true
      mail.smtp.starttls.enable: true
      ```
````

# Running the backend

## Clone Repository

```
git clone https://github.com/H-a-r-s/HCL_Tech-Alpha
cd backend
```

## Build

```
mvn clean install
```

## Run Services

- Each microservice can be run individually:
```
mvn spring-boot:run
```

# API Documentation

Swagger available at:
```
http://localhost:8080/swagger-ui.html
```

# Sample Endpoints

```
| Method | Endpoint          | Description   |
| ------ | ----------------- | ------------- |
| POST   | /auth/register    | Register user |
| POST   | /auth/login       | Login         |
| GET    | /products         | Get products  |
| POST   | /cart/add         | Add to cart   |
| POST   | /orders/place     | Place order   |
| GET    | /orders/user/{id} | Order history |
```

# Key Highlights

- Microservice architecture
- Snapshot-based order persistence
- Inventory consistency validation
- Email confirmation system
- Quick reorder feature
- Clean layered architecture (Controller → Service → Repository)
- Production-ready design principles

# Future Improvements

- Docker containerization
- Kubernetes deployment
- Redis caching
- Payment gateway integration
- Circuit breaker (Resilience4j)
- Observability (Zipkin, Prometheus)
