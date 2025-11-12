# Warehouse Management System API

This project is a robust backend for a warehouse management system, providing a complete set of RESTful endpoints to manage inventory, products, orders, customers, and more. The application is built using Spring Boot and utilizes Spring Security with JWT for secure, stateless authentication.

## Key Features

-   **Secure Authentication**: Role-based access control and user authentication using Spring Security and JSON Web Tokens (JWT).
-   **Product Management**: Full CRUD (Create, Read, Update, Delete) operations for products and their categories.
-   **Inventory Control**: Track product stock levels across multiple warehouses.
-   **Order Processing**: System to create and manage customer orders, with logic tied to inventory levels.
-   **Customer & Supplier Management**: Maintain records of customers and suppliers.
-   **API Documentation**: Integrated Swagger UI (OpenAPI 3) for easy API exploration and testing.

## Technology Stack

-   **Framework**: Spring Boot
-   **Language**: Java
-   **Security**: Spring Security
-   **Database**: Spring Data JPA / Hibernate with PostgreSQL
-   **Authentication**: JSON Web Tokens (JWT)
-   **Build Tool**: Maven
-   **Utilities**: Lombok
