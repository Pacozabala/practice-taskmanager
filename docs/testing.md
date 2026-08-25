# Testing

The Task Manager API uses **JUnit**, **Spring Boot Test**, and **MockMvc** to test the REST API.

Tests are located in:

```text
src/test/java/com/pacozabala/taskmanager/TaskControllerTest.java
```

The test suite focuses on verifying API behavior through HTTP requests rather than testing individual service methods directly.

## Running the Tests

Run the complete test suite from the project root:

### PowerShell

```powershell
./mvnw test
```

### Windows Command Prompt

```cmd
mvnw.cmd test
```

A successful test run should finish with:

```text
BUILD SUCCESS
```

---

# Testing Tools

| Tool                 | Purpose                                                       |
| -------------------- | ------------------------------------------------------------- |
| **JUnit**            | Test framework used to define and run tests                   |
| **Spring Boot Test** | Loads the Spring application context for integration testing  |
| **MockMvc**          | Sends simulated HTTP requests to the API                      |
| **ObjectMapper**     | Converts JSON responses into Java objects                     |
| **Spring Data JPA**  | Provides access to the test database through `TaskRepository` |

---

# Test Categories

The test suite currently covers:

* CRUD operations
* Error handling
* Request validation
* Filtering
* Searching
* Sorting

## Test Summary

| Category       | Tests                                              |
| -------------- | -------------------------------------------------- |
| CRUD           | Create, update, delete                             |
| Error handling | Task not found                                     |
| Validation     | Empty title, excessively long title, past due date |
| Filtering      | Completed tasks                                    |
| Searching      | Search by title                                    |
| Sorting        | Due date, creation date                            |

---

# CRUD Tests

## Create Task

`createTask()`

Tests that a valid task can be created through:

```text
POST /tasks
```

The test verifies that:

* The request is accepted
* The API returns a successful response
* The returned task contains the expected title

---

## Update Task

`updateTask()`

Tests the task update workflow.

The test:

1. Creates a task through `POST /tasks`
2. Extracts the generated task ID from the response
3. Sends an update request through `PUT /tasks/{id}`
4. Verifies that the update succeeds

This also demonstrates using the output of one API request as the input for another request.

---

## Delete Task

`deleteTask()`

Tests task deletion through:

```text
DELETE /tasks/{id}
```

The test first creates a task, extracts its generated ID, and then deletes it.

The expected response is:

```text
204 No Content
```

---

# Error Handling Tests

## Task Not Found

`taskNotFound()`

Tests what happens when the API receives a request for a task that does not exist.

```text
GET /tasks/999999
```

The test verifies that the API returns:

```text
404 Not Found
```

It also verifies that the error response contains:

* A `status` value of `404`
* An error `message`

This verifies that the application's centralized exception handling is working correctly.

---

# Validation Tests

The API validates incoming task requests before creating or updating tasks.

## Empty Title

`createTaskNoTitle()`

Sends a task with an empty title.

The request is expected to fail with:

```text
400 Bad Request
```

This verifies the title's `@NotBlank` validation.

---

## Excessively Long Title

`createTaskLongTitle()`

Sends a task with a title exceeding the configured maximum length.

The API is expected to return:

```text
400 Bad Request
```

This verifies the title's size validation.

---

## Past Due Date

`createTaskPastDate()`

Sends a task with a due date in the past.

The API is expected to return:

```text
400 Bad Request
```

This verifies the due date validation.

---

# Filtering Tests

## Filter Completed Tasks

`shouldFilterCompletedTasks()`

Tests:

```text
GET /tasks?completed=true
```

The test creates a known set of tasks and verifies that only completed tasks are returned.

Expected result:

```text
1 task
Buy groceries
```

---

# Searching Tests

## Search by Title

`shouldSearchTasksByTitle()`

Tests:

```text
GET /tasks/search?title=report
```

The test creates three tasks and searches for `"report"`.

Two tasks contain the search term in their title, so the test verifies that the API returns two results.

---

# Sorting Tests

## Sort by Due Date

`shouldSortByDueDate()`

Tests:

```text
GET /tasks/sort/due-date
```

The test verifies that tasks are returned in ascending order by due date.

Expected order:

```text
Buy groceries
Finish report
Write report
```

---

## Sort by Creation Date

`shouldSortByCreationDate()`

Tests:

```text
GET /tasks/sort/created
```

The test verifies the configured creation-date sorting behavior.

Expected order:

```text
Write report
Buy groceries
Finish report
```

---

# Test Data

The filtering, searching, and sorting tests use a helper method:

```java
createTestTasks()
```

This method clears the repository and creates three predictable tasks:

| Task          | Completed | Due Date   | Created    |
| ------------- | --------- | ---------- | ---------- |
| Finish report | `false`   | 2026-08-20 | 2026-08-10 |
| Buy groceries | `true`    | 2026-08-18 | 2026-08-12 |
| Write report  | `false`   | 2026-08-25 | 2026-08-15 |

Using predictable test data makes it possible to verify filtering, searching, and sorting results consistently.

---

# Testing Approach

The tests use `MockMvc` to simulate HTTP requests to the application's controllers.

For example:

```java
mockMvc.perform(get("/tasks"))
    .andExpect(status().isOk());
```

This allows the tests to verify the API from an HTTP perspective, including:

* Request paths
* HTTP methods
* Request parameters
* JSON request bodies
* HTTP status codes
* JSON response values

The tests also use `TaskRepository` to prepare controlled test data for filtering, searching, and sorting scenarios.

---

# Future Testing Improvements

The current test suite focuses primarily on controller/API behavior. Possible future improvements include:

* Add tests for updating a non-existent task
* Add validation tests for update requests
* Add tests for empty search results
* Add tests for empty task lists
* Add tests for invalid request JSON
* Add service-layer unit tests
* Add repository tests
* Separate controller tests from integration tests
* Add automated test execution through a CI/CD pipeline

The current suite is intended to provide a practical level of coverage for the project's core API functionality while keeping the testing approach appropriate for a learning and portfolio project.
