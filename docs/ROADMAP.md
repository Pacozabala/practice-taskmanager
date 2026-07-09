# Task Manager Roadmap

## Goal

Build a RESTful Task Manager application using Java and Spring Boot while learning the core Spring ecosystem and backend development concepts.

**Project Goals**

- Learn Java Spring Boot fundamentals
- Understand REST API design
- Learn Spring Data JPA
- Practice clean project architecture
- Gain experience with relational databases
- Learn backend testing
- Produce a portfolio-quality project

---

# Phase 0 – Project Setup

## Objectives

- [ ] Create Spring Boot project
- [ ] Learn Maven project structure
- [ ] Understand `src/main/java`
- [ ] Understand `src/main/resources`
- [ ] Run application successfully

## Dependencies

- [ ] Spring Web
- [ ] Spring Data JPA
- [ ] Validation
- [ ] H2 Database
- [ ] Lombok (optional)

**Milestone**

Application starts successfully.

---

# Phase 1 – Spring MVC Basics

## Learn

- [ ] `@SpringBootApplication`
- [ ] `@RestController`
- [ ] `@GetMapping`
- [ ] Returning JSON
- [ ] HTTP requests

## Tasks

- [ ] Create health endpoint
- [ ] Create hello endpoint
- [ ] Return Java objects as JSON

**Milestone**

Understand how Spring serves REST endpoints.

---

# Phase 2 – Project Architecture

## Learn

- [ ] Controller layer
- [ ] Service layer
- [ ] Repository layer
- [ ] Entity layer

## Create Packages

- [ ] controller
- [ ] service
- [ ] repository
- [ ] model
- [ ] dto
- [ ] config
- [ ] exception

**Milestone**

Understand request flow through the application.

---

# Phase 3 – Task Entity

## Create Task Model

Fields

- [ ] id
- [ ] title
- [ ] description
- [ ] completed
- [ ] createdAt
- [ ] dueDate

## Learn

- [ ] `@Entity`
- [ ] `@Id`
- [ ] `@GeneratedValue`
- [ ] Column mapping

**Milestone**

Database entity is complete.

---

# Phase 4 – Database Integration

## Learn

- [ ] Spring Data JPA
- [ ] Repository pattern
- [ ] H2 configuration

## Tasks

- [ ] Create TaskRepository
- [ ] Save task
- [ ] Read tasks
- [ ] Open H2 Console

**Milestone**

Application persists data.

---

# Phase 5 – CRUD API

## Endpoints

### Create

- [ ] POST /tasks

### Read

- [ ] GET /tasks
- [ ] GET /tasks/{id}

### Update

- [ ] PUT /tasks/{id}

### Delete

- [ ] DELETE /tasks/{id}

## Learn

- [ ] Path variables
- [ ] Request body
- [ ] ResponseEntity

**Milestone**

Complete CRUD application.

---

# Phase 6 – Service Layer

## Refactor

Move business logic from controllers into services.

## Learn

- [ ] Dependency Injection
- [ ] `@Service`
- [ ] Constructor injection

## Tasks

- [ ] Create TaskService
- [ ] Move CRUD logic
- [ ] Keep controllers lightweight

**Milestone**

Proper layered architecture.

---

# Phase 7 – Validation

## Learn

- [ ] `@Valid`
- [ ] `@NotBlank`
- [ ] `@Size`
- [ ] `@FutureOrPresent`

## Tasks

- [ ] Validate task title
- [ ] Validate due dates
- [ ] Return useful validation errors

**Milestone**

API rejects invalid requests.

---

# Phase 8 – Exception Handling

## Learn

- [ ] Custom exceptions
- [ ] `@ControllerAdvice`
- [ ] `@ExceptionHandler`

## Tasks

- [ ] TaskNotFoundException
- [ ] Global exception handler
- [ ] Consistent error responses

**Milestone**

Professional API error handling.

---

# Phase 9 – DTOs

## Learn

- [ ] Request DTO
- [ ] Response DTO

## Tasks

- [ ] TaskRequest
- [ ] TaskResponse
- [ ] Map Entity ↔ DTO

**Milestone**

Entities are no longer exposed directly.

---

# Phase 10 – Filtering & Searching

## Features

- [ ] Filter completed tasks
- [ ] Search by title
- [ ] Sort by due date
- [ ] Sort by creation date

## Learn

- [ ] Query parameters
- [ ] Repository query methods

**Milestone**

API supports searching and filtering.

---

# Phase 11 – PostgreSQL Migration

## Learn

- [ ] PostgreSQL setup
- [ ] JDBC URL
- [ ] Database configuration

## Tasks

- [ ] Replace H2
- [ ] Verify CRUD still works

**Milestone**

Production-ready database.

---

# Phase 12 – Testing

## Learn

- [ ] JUnit
- [ ] Spring Boot Test
- [ ] MockMvc

## Tests

- [ ] Create task
- [ ] Update task
- [ ] Delete task
- [ ] Task not found
- [ ] Validation failures

**Milestone**

Core functionality is tested.

---

# Phase 13 – Documentation

## Tasks

- [ ] Write README
- [ ] Document setup
- [ ] Document API endpoints
- [ ] Add screenshots
- [ ] Clean repository

**Milestone**

Portfolio-ready project.

---

# Stretch Goals

## Intermediate

- [ ] Priority levels
- [ ] Categories
- [ ] Pagination
- [ ] Due date reminders
- [ ] Task statistics

## Advanced

- [ ] Spring Security authentication
- [ ] JWT login
- [ ] User accounts
- [ ] Docker support
- [ ] CI/CD pipeline
- [ ] Deployment (Render, Railway, or another cloud provider)

---

# Learning Checklist

## Java

- [ ] Classes
- [ ] Interfaces
- [ ] Enums
- [ ] Exceptions
- [ ] Collections
- [ ] Streams
- [ ] LocalDateTime

## Spring

- [ ] Dependency Injection
- [ ] Spring MVC
- [ ] REST APIs
- [ ] Spring Data JPA
- [ ] Validation
- [ ] Exception Handling
- [ ] Testing

## Database

- [ ] SQL basics
- [ ] JPA
- [ ] Entity relationships
- [ ] Repository pattern

---

# Final Deliverable

A portfolio-quality Task Manager REST API featuring:

- Complete CRUD operations
- Layered architecture
- Validation
- Global exception handling
- DTOs
- PostgreSQL integration
- Automated tests
- Clear documentation
- Clean Git history