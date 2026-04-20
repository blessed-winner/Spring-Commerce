# Luxe API

A premium e-commerce RESTful service engineered with Spring Boot, focused on scalability and robust security.

## Overview

Luxe provides a streamlined backend for high-end commerce platforms. It handles product catalogs, shopping carts, and user authentication with a focus on performance and developer experience.

## Core Features

*   Secure authentication using Stateless JWT.
*   Automated database schema versioning with Flyway.
*   Data persistence via Spring Data JPA and MySQL.
*   Standardized DTO mapping using MapStruct.
*   Self-documenting API with OpenAPI/Swagger.
*   Server-side rendering support with Thymeleaf.

## Technical Stack

*   Language: Java 21
*   Framework: Spring Boot 3.4.1
*   Security: Spring Security
*   Persistence: MySQL, Hibernate
*   Migration: Flyway
*   Utility: Lombok, MapStruct

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 21
*   Maven 3.2+
*   MySQL Instance

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/blessed-winner/Luxe.git
   cd Luxe
   ```

2. Configure environment variables:
   Create a `.env` file in the root directory:
   ```env
   DATABASE_URL=jdbc:mysql://localhost:3306/luxe?CreateDatabaseIfNotExist=true
   DB_USERNAME=your_username
   DB_PASSWORD=your_password
   JWT_SECRET=your_base64_encoded_secret_key
   ```

3. Initialize the database:
   ```bash
   mvn flyway:migrate
   ```

4. Launch the application:
   ```bash
   mvn spring-boot:run
   ```

## Documentation

The API documentation is automatically generated and can be accessed via Swagger UI when the application is running:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
