# Testing
Testing uses 3 tools:
- **JUnit**: testing framework, provides `@Test`, assertions
- **Spring Boot Test**: starts parts/all of Spring app for tests
- **MockMvc**: enables sending of fake HTTP requests to controllers w/o running the app on real server.

```
Mock HTTP request
       ↓
TaskController
       ↓
TaskService
       ↓
Repository
       ↓
Response
       ↓
Assertions
```