# ENAA Skills Management API

A Spring Boot REST API to manage competences and sub-competences of learners. Built with Docker, tested with JUnit, documented with Swagger.

---

## Features

- ✅ Create, update, delete, list of competences
- ✅ Associate sub-competences with validation tracking
- ✅ Automatic competence validation status
- ✅ Swagger UI for easy API testing
- ✅ Docker Compose support (Spring Boot + MySQL)

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Swagger (SpringDoc OpenAPI)
- Docker & Docker Compose
- JUnit + Mockito

---

## Run with Docker Compose

### 1. Build your JAR file

```bash
mvn clean package
```
### 2. Start services

````bash
docker-compose up --build
````

### 3. Stop service
````bash
docker-compose down
````

### 4. View running containers
````bash
docker-compose ps
````
### 5. View logs
````bash
docker-compose logs -f
````

## API Documentation (Swagger)
### Once the app is running, visit:
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/swagger-ui.html

## Running Tests
````bash
mvn test
````