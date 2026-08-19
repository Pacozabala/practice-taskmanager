# Project Architecture
```[text]
    1. HTTP Request/Client
    2. Controller
    3. Service
    4. Repository
    5. Database
```

## Controller Layer
- receives HTTP requests from clients
- responsible for reading request data, calling the appropriate service, and return the response
```[text]
    GET /tasks -> TaskController -> taskService.getAllTasks()
```

## Service Layer
- contains app's business logic
- ex. creates/validates tasks, marks tasks complete, prevents duplicate names
```[text]
    TaskController -> TaskService -> TaskRepository
```

## Repository Layer
- repository talks to the DB
- this is where Spring Data JPA comes in

## Entity Layer
- represents table in the DB
- uses annotations like `@Entity`, `@Table`, `@Id`