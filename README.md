# practice-taskmanager
A basic to-do list API built with Spring Boot. A project developed for learning Java, Spring Boot, REST APIs, JPA, and PostgreSQL.

## Setup
### 1. Clone the repository
Clone the repository and open the project folder:
```text
git clone <repository-url> 
cd practice-taskmanager
```

### 2. Configure PostgreSQL
Make sure PostgreSQL is installed and running. Create a PostgreSQL database named `taskmanager`:
```text
CREATE DATABASE taskmanager;
```

The application expects the following environment variables:
- `DB_URL`: PostgreSQL JDBC connection URL
- `DB_USERNAME`: PostgreSQL username
- `DB_PASSWORD`: PostgreSQL password

**PowerShell**
Set the variables for your current PowerShell session:
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/taskmanager" $env:DB_USERNAME="postgres" 
$env:DB_PASSWORD="your_actual_password"
```
These variables need to be set before running the app. Environment variables set this way only apply to the current PowerShell session.

**VS Code**
If you use VS Code, you can also configure the environment variables through a Run Configuration.

Please do not commit credentials or other secrets to the repository.

### 3. Run application
From the `taskmanager/` folder:
Powershell:
```powershell
./mvnw spring-boot:run
```
Windows cmd:
```cmd
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

## Testing

Run the test suite from `taskmanager/`:
```powershell
./mvnw test
```

On Windows Command Prompt:
```cmd
mvnw.cmd test
```

## API
Once the application is running, the API is available at:

```text
http://localhost:8080
```

## Project Structure

The main Java packages are located at:
```text
src/main/java/com/pacozabala/taskmanager
```

| Package      | Purpose                          |
| ------------ | -------------------------------- |
| `controller` | REST API endpoints               |
| `service`    | Business logic                   |
| `repository` | Spring Data JPA repositories     |
| `model`      | JPA entities                     |
| `dto`        | Data Transfer Objects            |
| `config`     | Spring/application configuration |
| `exception`  | Custom exception classes         |

## Database

The project currently uses **PostgreSQL** as its database.

Database configuration is supplied through environment variables rather than being stored directly in `application.properties`.

## Development Notes

This project is developed incrementally as a learning exercise. Each phase introduces additional Spring Boot and Java concepts, including:

* REST APIs
* Dependency injection
* Service and repository layers
* Validation
* Exception handling
* DTOs
* Filtering and searching
* PostgreSQL and JPA

