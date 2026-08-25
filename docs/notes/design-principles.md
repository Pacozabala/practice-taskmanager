# Design Principles

## Separation of Concerns
Each layer has 1 job; organizing code so that each part has a single clear responsibility.

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

## Dependency Injection
- instead of creating objects manually, Spring creates the object and gives it to the service
- Spring sees that `TaskController` needs a `TaskRepository` and provides the appropriate repository implementation:
```[Java]
public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
```