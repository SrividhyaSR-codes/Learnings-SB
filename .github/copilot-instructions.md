# Daily Routines App - Workspace Instructions

This workspace contains a Spring Boot application for managing daily routines.

## Project Overview

A REST API-based Spring Boot application with the following components:
- Entity: Routine (database model)
- Repository: RoutineRepository (data access)
- Service: RoutineService (business logic)
- Controller: RoutineController (REST endpoints)
- DTO: RoutineDTO (data transfer)

## Getting Started

1. **Build the project**:
   ```
   mvn clean install
   ```

2. **Run the application**:
   ```
   mvn spring-boot:run
   ```

3. **Access the application**:
   - API: http://localhost:8080/api/routines
   - H2 Console: http://localhost:8080/h2-console

## Key Features

- Create, read, update, delete daily routines
- Filter routines by date and category
- Track routine completion status
- Automatic timestamp management

## Database

Uses H2 in-memory database by default. Can be configured to use MySQL, PostgreSQL, or other databases by modifying `application.properties`.

## Testing

Run tests with:
```
mvn test
```

## Code Structure

- `entity/`: JPA entity classes
- `repository/`: Spring Data repositories
- `service/`: Business logic layer
- `controller/`: REST API endpoints
- `dto/`: Data transfer objects
- `resources/`: Configuration files

## Configuration

Most settings are in `src/main/resources/application.properties`. Key configurations:
- Server port: 8080
- Database: H2 in-memory
- JPA/Hibernate: auto-update schema
- Logging: INFO level (DEBUG for com.dailyroutines)
