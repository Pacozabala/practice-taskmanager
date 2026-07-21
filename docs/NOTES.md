# Notes and Learnings
Personal notes from building the Spring Task Manager Project.

## Table of Contents
- [Spring Fundamentals](#spring-fundamentals)
- [Project Architecture](#project-architecture)
- [Persistence](#persistence)
- [Request Flow](#request-flow)
- [Common Spring Annotations](#common-spring-annotations)
- [Design Principles](#design-principles)

# Spring Fundamentals
What is Spring Boot?
- opinionated framework for building java apps quickly.
- provides auto-config
- starts embedded web server
- managers app components

---

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

---

# Persistence
- data continues to exist after program stops.

## Java Persistence API
JPA is the standard Java specs for mapping Java objects to database tables. Instead of writing SQL, use Java objects:
```[Java]
taskRepository.save(task);
```
Spring Data JPA is in charge of generating SQL.

## Spring Data JPA
- provides repo interfaces like `JpaRepository` (built-in Spring interface, provides common DB operations)
- useful methods: `save()`, `findById()`, `findAll()`, `deleteById()`, `count()`

## Hibernate
- Spring Boot's Default JPA implementation
- converts Java objects to SQL statements
- process: Task object -> Hibernate -> SQL -> H2 Database

---

# Request Flow
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

---

# Common Spring Annotations

## Web
`@SpringBootApplication`
- tells Spring that this is the main application, and to automatically configure everything.
    - start app, create all necessary objects, start embedded web server, listen on port 8080.

`@RestController`
- tells spring that the class contains REST endpoints.

`@GetMapping`
- maps the HTTP request `GET /hello` to `hello()` method.

## Database
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

