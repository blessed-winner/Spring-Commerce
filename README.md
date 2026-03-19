# Store API

This is a Spring Boot RESTful API for a simple store application.

## Technologies Used

*   **Java:** 21
*   **Spring Boot:** 3.4.1
*   **Database:**
    *   Spring Data JPA
    *   MySQL
    *   Flyway for database migrations
*   **Security:**
    *   Spring Security
    *   JSON Web Tokens (JWT)
*   **API Documentation:**
    *   SpringDoc OpenAPI (Swagger)
*   **Development Tools:**
    *   Lombok
    *   MapStruct
*   **Templating:**
    *   Thymeleaf

## Getting Started

### Prerequisites

*   JDK 21 or later
*   Maven 3.2+
*   MySQL server

### Configuration

1.  **Database:**
    The database connection properties are configured to be loaded from a `.env` file in the project root. Create a `.env` file with the following properties:

    ```env
    DB_URL=jdbc:mysql://localhost:3306/store?CreateDatabaseIfNotExist=true
    DB_USERNAME=root
    DB_PASSWORD=your_password
    ```

    Alternatively, you can configure these properties in `src/main/resources/application.properties`.

2.  **Database Migration:**
    This project uses Flyway to manage database schema changes. The migrations are located in `src/main/resources/db/migration`.

    To apply the migrations, you can run the following Maven command:
    ```bash
    mvn flyway:migrate
    ```

### Running the Application

You can run the application using the Spring Boot Maven plugin:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Documentation

Once the application is running, you can access the Swagger UI for API documentation and testing at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
