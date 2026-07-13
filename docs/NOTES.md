# Notes and Learnings
This markdown file is used for my notes and stuff I learn while developing this project.

## Annotations

### Spring Web Annotations 
`@SpringBootApplication`
- tells Spring that this is the main application, and to automatically configure everything.
    - start app, create all necessary objects, start embedded web server, listen on port 8080.

`@RestController`
- tells spring that the class contains REST endpoints.

`@GetMapping`
- maps the HTTP request `GET /hello` to `hello()` method.

### Database Annotations
`@Entity`
- tells spring that this class should become a DB table

`@Id`
- indicates the entity's unique identifier.

`@GeneratedValue(strategy = GenerationType.STRATEGY)`
- auto-generates primary keys based on a specific strategy.

`@Column(name = "column_string")`
- each field normally becomes a DB column automatically
    - private String title; --> column title
- if wanting to customize, use `@Column` to customize name

---

## Overall Project Architecture
```[text]
    1. HTTP Request
    2. Controller
    3. Service
    4. Repository
    5. Database
```

### Controller Layer
- receives HTTP requests from clients
- responsible for reading request data, calling the appropriate service, and return the response
```[text]
    GET /tasks -> TaskController -> taskService.getAllTasks()
```

### Service Layer
- contains app's business logic
- ex. creates/validates tasks, marks tasks complete, prevents duplicate names
```[text]
    TaskController -> TaskService -> TaskRepository
```

### Repository Layer
- repository talks to the DB
- this is where Spring Data JPA comes in

### Entity Layer
- represents table in the DB
- uses annotations like `@Entity`, `@Table`, `@Id`

---

## Request Flow
```[text]
    1. Browser
    2. GET /tasks
    3. TaskController
    4. TaskService
    5. TaskRepository
    6. Database
    7. TaskRepository
    8. TaskService
    9. TaskController
    10. JSON Response
```
- Controllers never talk directly to the database
- Services do not receive HTTP requests
- Repos only access data

Each layer has 1 job. This is **Separation of concerns**, organizing code so that each part has a single clear responsibility.

## Data Transfer Objects (DTO)
- objects for transferring data between client and app
- instead of exposing db entity directly, send/receive a simplified object like a JSON response.
- Advantages: hides internal fields, allows diff req/res formats, keeps API flexible.

An example:
1. Client sends:
```[json]
    {
        "title": "Buy groceries"
    }
```
2. Instead of directly creating a `Task` entity, you could receive a `TaskCreateRequest`.

## Configuration
- config classes define how Spring should set up parts of the app
- ex. CORS settings, security, custom beans, JSON serialization

## Exception handling
- errors while app runs
- ex. Task not found, Invalid input, DB unavailable
- instead of letting the app crash, Spring catches these exceptions to return HTTP responses like a JSON for a 404.

