# Smart Inventory Microservices

Smart Inventory has been split from the original monolith into small Spring Boot services.

## Services

| Service | Port | Purpose |
|---|---:|---|
| Eureka Server | 8761 | Service registry |
| API Gateway | 8080 | Single API entry point and JWT checks |
| Auth Service | 8081 | Registration, login, JWT generation |
| Product Service | 8082 | Products and categories |
| Customer Service | 8083 | Customers |
| Supplier Service | 8084 | Suppliers |
| Order Service | 8085 | Orders |

All API requests should go through the API Gateway: `http://localhost:8080`.

## Prerequisites

- Java 17 or newer
- MySQL running locally
- MySQL user: `root`
- MySQL password: `****`

Each service creates its own database automatically:

```text
auth_db, product_db, customer_db, supplier_db, order_db
```

## Run the project

Start every service in a separate PowerShell terminal, in this order:

1. `eureka-server`
2. `auth-service`
3. `product-service`, `customer-service`, `supplier-service`, `order-service`
4. `api-gateway`

The included Maven wrappers are incomplete, so use the locally cached Maven command in each terminal:

```powershell
$Maven = "C:\Users\vedan\.m2\wrapper\dists\apache-maven-3.9.16\0daed3be3ebd1c706f0e69e8b07c6b73f5cc4ea3dfce72a8d0ec2e849ca2ddb0\bin\mvn.cmd"
```

For example, to run the customer service:

```powershell
cd D:\Downloads\smartinventory\customer-service
& $Maven "-Dmaven.repo.local=C:\Users\vedan\.m2\repository" spring-boot:run
```

Open the Eureka dashboard at `http://localhost:8761` and confirm all services are `UP`.

### Run with Docker

If Docker Desktop is running, start the complete system from the project root:

```powershell
docker compose up --build
```

Stop the containers with:

```powershell
docker compose down
```

Use `docker compose down -v` only when you also want to delete the MySQL data volume.

## Authentication

Register a user:

```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json
```

```json
{
  "username": "admin",
  "password": "admin123",
  "email": "admin@example.com",
  "role": "ADMIN"
}
```

Log in:

```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json
```

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Copy the returned token into Postman for every non-auth request:

```text
Authorization: Bearer <token>
```

## Roles

| Role | Access |
|---|---|
| `ADMIN` | Full access |
| `MANAGER` | Full access to the current inventory APIs |
| `EMPLOYEE` | All `GET` requests and `POST /api/orders` only |

Requests with no valid token return `401 Unauthorized`. Requests blocked by a role rule return `403 Forbidden`.

## Main API endpoints

```text
POST /api/auth/register
POST /api/auth/login

GET, POST, PUT, DELETE /api/categories
GET, POST, PUT, DELETE /api/products
GET /api/products/search?name=mouse
GET /api/products/filter/category?categoryId=1
GET /api/products/filter/status?status=ACTIVE
GET /api/products/filter/price?minPrice=100&maxPrice=1000

GET, POST, PUT, DELETE /api/customers
GET, POST, PUT, DELETE /api/suppliers
GET, POST, DELETE /api/orders
PUT /api/orders/{id}/status?status=COMPLETED
```

Create a category before creating a product. Create a customer before creating an order.
