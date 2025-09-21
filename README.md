# 📚 Bookstore Spring Boot Backend

This is the backend API for the **Bookstore Application**, built with Spring Boot.
It provides secure RESTful APIs for user authentication and book management, and is designed to integrate with the [Bookstore Angular Frontend](https://github.com/tharamomani/bookstore_angular).

---

## ✅ Features

* User authentication with JWT
* CORS support for Angular frontend
* CRUD operations on books
* RESTful API design
* Swagger documentation enabled

---

## 🚀 Getting Started

### Prerequisites

* **Java 21+**
* **Maven 3.9+**
* **MySQL** (or another database, configurable in `application.properties`)

### Clone the Repository

```bash
git clone https://github.com/tharamomani/bookstore_springBoot.git
cd bookstore_springBoot/demo
```

### Configure the Database

Edit `src/main/resources/application.properties` and update with your DB credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Run the Application

```bash
mvn spring-boot:run
```

The backend will start at:
👉 `http://localhost:8080`

---

## 🔗 API Endpoints

### Auth

* `POST /auth/login` – authenticate user
* `POST /auth/register` – register new user

### Books

* `GET /books` – list all books
* `POST /books` – add new book
* `PUT /books/{id}` – update book
* `DELETE /books/{id}` – delete book

---

## 📖 Swagger Docs

Once the backend is running, you can explore the API at:
👉 `http://localhost:8080/swagger-ui.html`

---

## 🛠 Build & Test

### Build

```bash
mvn clean package
```

### Run Unit Tests

```bash
mvn test
```

---

## 🔗 Integration with Frontend

This backend is designed to be used with the [Bookstore Angular Frontend](https://github.com/tharamomani/bookstore_angular).
Make sure to update the Angular `environment.ts` with the backend URL:

```ts
apiUrl: 'http://localhost:8080/api'
```
