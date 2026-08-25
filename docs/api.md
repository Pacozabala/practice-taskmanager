# Task Manager API

REST API documentation for the Task Manager application.

The API runs locally at:

```text
http://localhost:8080
```

## Endpoints

| Method   | Endpoint                      | Description                       |
| -------- | ----------------------------- | --------------------------------- |
| `POST`   | `/tasks`                      | Create a new task                 |
| `GET`    | `/tasks`                      | Get all tasks                     |
| `GET`    | `/tasks/{id}`                 | Get a task by ID                  |
| `PUT`    | `/tasks/{id}`                 | Update a task                     |
| `DELETE` | `/tasks/{id}`                 | Delete a task                     |
| `GET`    | `/tasks?completed={boolean}`  | Filter tasks by completion status |
| `GET`    | `/tasks/search?title={title}` | Search tasks by title             |
| `GET`    | `/tasks/sort/due-date`        | Sort tasks by due date            |
| `GET`    | `/tasks/sort/created`         | Sort tasks by creation date       |

---

## Task Object

A task contains the following fields:

| Field         | Type            | Description                         |
| ------------- | --------------- | ----------------------------------- |
| `id`          | `Long`          | Unique task identifier              |
| `title`       | `String`        | Task title                          |
| `description` | `String`        | Task description                    |
| `completed`   | `Boolean`       | Whether the task has been completed |
| `createdAt`   | `LocalDateTime` | Date and time the task was created  |
| `dueDate`     | `LocalDate`     | Date the task is due                |

`id`, `completed`, and `createdAt` are generated or managed by the application and do not need to be supplied when creating a task.


# Create a Task

### `POST /tasks`

Creates a new task.

### Request Body

```json
{
  "title": "Finish documentation",
  "description": "Complete the API documentation",
  "dueDate": "2026-10-30"
}
```

### Example Response

```json
{
  "id": 1,
  "title": "Finish documentation",
  "description": "Complete the API documentation",
  "completed": false,
  "createdAt": "2026-08-25T16:30:00",
  "dueDate": "2026-10-30"
}
```

### Status Codes

| Status            | Description                   |
| ----------------- | ----------------------------- |
| `201 Created`     | Task was successfully created |
| `400 Bad Request` | Request failed validation     |

---

# Get All Tasks

### `GET /tasks`

Returns all tasks.

### Example Response

```json
[
  {
    "id": 1,
    "title": "Finish documentation",
    "description": "Complete the API documentation",
    "completed": false,
    "createdAt": "2026-08-25T16:30:00",
    "dueDate": "2026-10-30"
  },
  {
    "id": 2,
    "title": "Review tests",
    "description": "Review the Phase 12 test suite",
    "completed": true,
    "createdAt": "2026-08-25T16:35:00",
    "dueDate": "2026-09-01"
  }
]
```

### Status Codes

| Status   | Description                       |
| -------- | --------------------------------- |
| `200 OK` | Tasks were successfully retrieved |


# Get Task by ID

### `GET /tasks/{id}`

Returns a single task using its ID.

### Example Request

```text
GET /tasks/1
```

### Example Response

```json
{
  "id": 1,
  "title": "Finish documentation",
  "description": "Complete the API documentation",
  "completed": false,
  "createdAt": "2026-08-25T16:30:00",
  "dueDate": "2026-10-30"
}
```

### Status Codes

| Status          | Description                               |
| --------------- | ----------------------------------------- |
| `200 OK`        | Task was found                            |
| `404 Not Found` | Task with the specified ID does not exist |


# Update a Task

### `PUT /tasks/{id}`

Updates an existing task.

### Example Request

```text
PUT /tasks/1
```

### Request Body

```json
{
  "title": "Finish API documentation",
  "description": "Complete and review the API documentation",
  "dueDate": "2026-11-01"
}
```

### Example Response

```json
{
  "id": 1,
  "title": "Finish API documentation",
  "description": "Complete and review the API documentation",
  "completed": false,
  "createdAt": "2026-08-25T16:30:00",
  "dueDate": "2026-11-01"
}
```

### Status Codes

| Status            | Description                               |
| ----------------- | ----------------------------------------- |
| `200 OK`          | Task was successfully updated             |
| `400 Bad Request` | Request failed validation                 |
| `404 Not Found`   | Task with the specified ID does not exist |


# Delete a Task

### `DELETE /tasks/{id}`

Deletes a task using its ID.

### Example Request

```text
DELETE /tasks/1
```

### Status Codes

| Status           | Description                               |
| ---------------- | ----------------------------------------- |
| `204 No Content` | Task was successfully deleted             |
| `404 Not Found`  | Task with the specified ID does not exist |


# Filter by Completion Status

### `GET /tasks?completed={boolean}`

Returns tasks based on their completion status.

### Example Requests

Get completed tasks:

```text
GET /tasks?completed=true
```

Get incomplete tasks:

```text
GET /tasks?completed=false
```

### Example Response

```json
[
  {
    "id": 2,
    "title": "Review tests",
    "description": "Review the Phase 12 test suite",
    "completed": true,
    "createdAt": "2026-08-25T16:35:00",
    "dueDate": "2026-09-01"
  }
]
```

### Status Codes

| Status            | Description                       |
| ----------------- | --------------------------------- |
| `200 OK`          | Tasks were successfully retrieved |
| `400 Bad Request` | Invalid completion value          |


# Search Tasks by Title

### `GET /tasks/search?title={title}`

Searches for tasks based on their title.

### Example Request

```text
GET /tasks/search?title=documentation
```

### Example Response

```json
[
  {
    "id": 1,
    "title": "Finish documentation",
    "description": "Complete the API documentation",
    "completed": false,
    "createdAt": "2026-08-25T16:30:00",
    "dueDate": "2026-10-30"
  }
]
```

### Status Codes

| Status   | Description                   |
| -------- | ----------------------------- |
| `200 OK` | Search completed successfully |

# Sort by Due Date

### `GET /tasks/sort/due-date`

Returns tasks sorted by their due date.

### Example Request

```text
GET /tasks/sort/due-date
```

### Example Response

```json
[
  {
    "id": 2,
    "title": "Review tests",
    "description": "Review the Phase 12 test suite",
    "completed": true,
    "createdAt": "2026-08-25T16:35:00",
    "dueDate": "2026-09-01"
  },
  {
    "id": 1,
    "title": "Finish documentation",
    "description": "Complete the API documentation",
    "completed": false,
    "createdAt": "2026-08-25T16:30:00",
    "dueDate": "2026-10-30"
  }
]
```

### Status Codes

| Status   | Description                       |
| -------- | --------------------------------- |
| `200 OK` | Tasks were successfully retrieved |

# Sort by Creation Date

### `GET /tasks/sort/created`

Returns tasks sorted by their creation date.

### Example Request

```text
GET /tasks/sort/created
```

### Example Response

```json
[
  {
    "id": 1,
    "title": "Finish documentation",
    "description": "Complete the API documentation",
    "completed": false,
    "createdAt": "2026-08-25T16:30:00",
    "dueDate": "2026-10-30"
  },
  {
    "id": 2,
    "title": "Review tests",
    "description": "Review the Phase 12 test suite",
    "completed": true,
    "createdAt": "2026-08-25T16:35:00",
    "dueDate": "2026-09-01"
  }
]
```

### Status Codes

| Status   | Description                       |
| -------- | --------------------------------- |
| `200 OK` | Tasks were successfully retrieved |

# Validation

Task requests are validated before being processed.

The API validates fields such as:

* `title` must not be blank
* `title` must satisfy the configured length requirements
* `dueDate` must be today or a future date

### Example Invalid Request

```json
{
  "title": "",
  "description": "Invalid task",
  "dueDate": "2020-01-01"
}
```

### Example Error Response

```json
{
  "timestamp": "2026-08-25T16:40:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed"
}
```

The exact validation error message may vary depending on which validation rules were violated.

# Error Handling

The API uses centralized exception handling for common errors.

### Common Error Responses

| Status                      | Description                           |
| --------------------------- | ------------------------------------- |
| `400 Bad Request`           | Invalid request or validation failure |
| `404 Not Found`             | Requested task does not exist         |
| `500 Internal Server Error` | Unexpected server error               |

Error responses include information about the timestamp, HTTP status, error type, and message.

---

## Running the API

See the main [`README.md`](../README.md) for installation, PostgreSQL configuration, environment variables, and instructions for running the application.
