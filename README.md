# 🚀 Smart Inventory Management System

A production-style **Inventory Management System** built using **Spring Boot Microservices**, **Spring Cloud**, **Docker**, **JWT Authentication**, and **MySQL**.

The project demonstrates how a traditional monolithic application can be decomposed into independent microservices that communicate through an API Gateway and Service Discovery.

---

#  Features

* Microservices Architecture
* Spring Cloud API Gateway
* Netflix Eureka Service Discovery
* JWT Authentication & Authorization
* Role-Based Access Control (ADMIN / MANAGER / EMPLOYEE)
* RESTful CRUD APIs
* Spring Data JPA & Hibernate
* MySQL Database
* Docker & Docker Compose Support
* Individual Database per Microservice
* Secure API Gateway Routing

---

# Microservice Architecture

```
                    Client / Postman
                           │
                           ▼
                    API Gateway (8080)
                           │
        ┌────────────┬────────────┬────────────┐
        │            │            │            │
        ▼            ▼            ▼            ▼
 Auth Service   Product Service Customer Service Supplier Service
        │                                         │
        └───────────────┬─────────────────────────┘
                        ▼
                  Order Service
                        │
                        ▼
                  MySQL Databases

                Eureka Server (8761)
```

---

# 🛠 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Spring Cloud Gateway
* Netflix Eureka
* JWT Authentication

### Database

* MySQL 8

### Build Tool

* Maven

### Containerization

* Docker
* Docker Compose

---

#  Microservices

| Service          | Port | Description                   |
| ---------------- | ---- | ----------------------------- |
| Eureka Server    | 8761 | Service Discovery             |
| API Gateway      | 8080 | Single Entry Point            |
| Auth Service     | 8081 | Authentication & JWT          |
| Product Service  | 8082 | Product & Category Management |
| Customer Service | 8083 | Customer Management           |
| Supplier Service | 8084 | Supplier Management           |
| Order Service    | 8085 | Order Management              |

---

#  Authentication

The application uses **JWT (JSON Web Token)** authentication.

Workflow:

```
Register
    │
    ▼
Login
    │
    ▼
Receive JWT Token
    │
    ▼
Use Authorization Header

Bearer <JWT_TOKEN>
```

---

#  User Roles

| Role     | Permissions                     |
| -------- | ------------------------------- |
| ADMIN    | Full Access                     |
| MANAGER  | Inventory Management            |
| EMPLOYEE | Read Operations + Create Orders |

---

#  REST APIs

## Authentication

```
POST /api/auth/register
POST /api/auth/login
```

---

## Categories

```
GET
POST
PUT
DELETE
/api/categories
```

---

## Products

```
GET
POST
PUT
DELETE
/api/products

GET /api/products/search
GET /api/products/filter/category
GET /api/products/filter/status
GET /api/products/filter/price
```

---

## Customers

```
GET
POST
PUT
DELETE
/api/customers
```

---

## Suppliers

```
GET
POST
PUT
DELETE
/api/suppliers
```

---

## Orders

```
GET
POST
DELETE
/api/orders

PUT /api/orders/{id}/status
```

---

# 🗄 Databases

Each microservice maintains its own database.

```
auth_db

product_db

customer_db

supplier_db

order_db
```

---

#  Running with Docker

Clone the repository.

```
git clone <repository-url>
```

Move inside the project.

```
cd smartinventory
```

Start all services.

```
docker compose up --build
```

Stop all services.

```
docker compose down
```

Delete containers and database volumes.

```
docker compose down -v
```

---

# Running Without Docker

Start the services in the following order:

1. Eureka Server
2. Auth Service
3. Product Service
4. Customer Service
5. Supplier Service
6. Order Service
7. API Gateway

Visit:

```
http://localhost:8761
```

Verify that every service is registered and running.

---

#  Testing

1. Register a user.
2. Login to receive a JWT.
3. Add the token to the Authorization header:

```
Bearer <token>
```

4. Test all CRUD APIs using Postman.

---

# 📁 Project Structure

```
smartinventory
│
├── api-gateway
├── auth-service
├── customer-service
├── eureka-server
├── order-service
├── product-service
├── supplier-service
├── docker-compose.yml
└── README.md
```



#  Author

**Vedant Kavhar**

---

# ⭐ If you found this project useful, consider giving it a Star!
