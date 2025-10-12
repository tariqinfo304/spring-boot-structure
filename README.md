# Spring Boot User & Product CRUD API

## Overview
This project is a Spring Boot RESTful API for managing users and products. It demonstrates a clean, modular structure using Spring Data JPA, validation, exception handling, and an in-memory H2 database. The API supports basic CRUD operations for both users and products.

## Features
- User CRUD (Create, Read, Update, Delete)
- Product CRUD (Create, Read, Delete)
- Input validation with meaningful error messages
- Global exception handling
- In-memory H2 database (with web console)
- OpenAPI/Swagger UI for API documentation

## Project Structure

```
src/main/java/com/app1/src/
	├── Application.java                # Main Spring Boot application entry point
	├── GlobalExceptionHandler.java     # Handles validation and JSON errors globally
	├── user/
	│     ├── controller/UserController.java   # REST endpoints for User
	│     ├── model/User.java                 # User entity with validation
	│     └── repository/UserRepository.java  # JPA repository for User
	└── product/
				├── controller/ProductController.java   # REST endpoints for Product
				├── model/Product.java                 # Product entity with validation
				└── repository/ProductRepository.java  # JPA repository for Product
src/main/resources/
	└── application.properties           # DB and JPA configuration
```

## Main Components

### Application.java
Bootstraps the Spring Boot application.

### GlobalExceptionHandler.java
Handles validation errors (e.g., from `@Valid`) and malformed JSON, returning structured error responses.

### User Domain
- **User.java**: Entity with fields: id, name, email, username, phone. Uses validation annotations.
- **UserRepository.java**: Extends `JpaRepository<User, Long>` for DB access.
- **UserController.java**: REST endpoints:
	- `GET /api/users` - List all users
	- `GET /api/users/{id}` - Get user by ID
	- `POST /api/users` - Create user (validates input)
	- `PUT /api/users/{id}` - Update user
	- `DELETE /api/users/{id}` - Delete user

### Product Domain
- **Product.java**: Entity with fields: id, name, price. Uses validation annotations.
- **ProductRepository.java**: Extends `JpaRepository<Product, Long>` for DB access.
- **ProductController.java**: REST endpoints:
	- `GET /api/product` - List all products
	- `GET /api/product/{id}` - Get product by ID
	- `POST /api/product` - Create product (validates input)
	- `DELETE /api/product/{id}` - Delete product

## Configuration
- **application.properties**: Configures H2 DB, JPA, and enables H2 web console at `/h2-console`.

## Running the Application
You can use the following Maven commands to build and run the project:

### Build and Clean
- After adding or updating dependencies in `pom.xml`, clean and build the project:
	```bash
	mvn clean install
	```

### Run the Application
- To start the Spring Boot application:
	```bash
	mvn spring-boot:run
	```

### Access the API and Tools
- API: `http://localhost:8080/api/users` and `http://localhost:8080/api/product`
- H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`)
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Dependencies
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Springdoc OpenAPI (Swagger)

## Example API Usage

**Create User**
```http
POST /api/users
Content-Type: application/json
{
	"name": "John Doe",
	"email": "john@example.com",
	"username": "johndoe",
	"phone": "1234567890"
}
```

**Create Product**
```http
POST /api/product
Content-Type: application/json
{
	"name": "Laptop",
	"price": 1200.0
}
```

## License
MIT

