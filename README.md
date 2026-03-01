# Daily Routines App

A Spring Boot application for managing daily routines. This application allows users to create, update, delete, and track daily routines with various categories and completion statuses.

## Features

- **Create Routines**: Add new daily routines with title, description, category, and time
- **Update Routines**: Modify existing routines
- **Delete Routines**: Remove routines from the system
- **Track Completion**: Mark routines as completed or pending
- **Filter by Date**: View routines scheduled for a specific date
- **Filter by Category**: Organize routines by categories (exercise, work, personal, etc.)
- **Track Status**: Get completed and pending routines
- **Real-time Updates**: Automatic timestamp tracking for creation and updates

## Technology Stack

- **Java 17**: Programming language
- **Spring Boot 3.2**: Framework for building production-ready applications
- **Spring Data JPA**: Object-relational mapping for database operations
- **H2 Database**: In-memory database (easily replaceable with MySQL, PostgreSQL, etc.)
- **Lombok**: Reducing boilerplate code
- **Maven**: Build and dependency management

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/dailyroutines/
│   │       ├── DailyRoutinesApplication.java  # Main Spring Boot application
│   │       ├── controller/
│   │       │   └── RoutineController.java     # REST API endpoints
│   │       ├── service/
│   │       │   └── RoutineService.java        # Business logic
│   │       ├── repository/
│   │       │   └── RoutineRepository.java     # Data access layer
│   │       ├── entity/
│   │       │   └── Routine.java               # Database entity
│   │       └── dto/
│   │           └── RoutineDTO.java            # Data transfer object
│   └── resources/
│       └── application.properties              # Application configuration
└── test/
    └── java/com/dailyroutines/                # Unit tests
```

## API Endpoints

### Get All Routines
```
GET /api/routines
```

### Get Routine by ID
```
GET /api/routines/{id}
```

### Create New Routine
```
POST /api/routines
Body: {
  "title": "Morning Exercise",
  "description": "30 minutes of jogging",
  "category": "Exercise",
  "time": "06:00",
  "scheduledDate": "2026-02-28"
}
```

### Update Routine
```
PUT /api/routines/{id}
Body: {
  "title": "Updated Title",
  "description": "Updated description",
  ...
}
```

### Delete Routine
```
DELETE /api/routines/{id}
```

### Get Routines by Date
```
GET /api/routines/by-date/{scheduledDate}
```

### Get Routines by Category
```
GET /api/routines/by-category/{category}
```

### Get Completed Routines
```
GET /api/routines/completed
```

### Get Pending Routines
```
GET /api/routines/pending
```

### Get Routines by Date and Status
```
GET /api/routines/by-date-and-status/{scheduledDate}/{completed}
```

### Mark Routine as Completed
```
PATCH /api/routines/{id}/mark-completed
```

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build the Project
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Access H2 Console (Development)
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave empty)
```

## Running Tests
```bash
mvn test
```

## Configuration

Edit `src/main/resources/application.properties` to customize:
- Server port
- Database configuration
- Logging levels
- Hibernate DDL strategy

## Database Configuration

### Using H2 (Default - In-Memory)
Already configured in `application.properties`

### Using MySQL
1. Add dependency to `pom.xml`:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency>
```

2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/daily_routines
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### Using PostgreSQL
1. Add dependency to `pom.xml`:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```

2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/daily_routines
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL10Dialect
```

## Example Usage with cURL

### Create a routine
```bash
curl -X POST http://localhost:8080/api/routines \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Morning Meditation",
    "description": "20 minutes of meditation",
    "category": "Health",
    "time": "06:30",
    "scheduledDate": "2026-02-28"
  }'
```

### Get all routines
```bash
curl http://localhost:8080/api/routines
```

### Mark routine as completed
```bash
curl -X PATCH http://localhost:8080/api/routines/1/mark-completed
```

## Future Enhancements

- User authentication and authorization
- Recurring routines
- Reminders and notifications
- Analytics and progress tracking
- Mobile app support
- Calendar integration
- Export routines to CSV/PDF

## License

This project is open source and available under the MIT License.

## Author

Created as a learning project for Spring Boot development.
