# Common Spring Annotations

## Web
`@SpringBootApplication`
- tells Spring that this is the main application, and to automatically configure everything.
    - start app, create all necessary objects, start embedded web server, listen on port 8080.

`@RestController`
- tells spring that the class contains REST endpoints.

`@GetMapping`
- maps the HTTP request `GET /hello` to `hello()` method.

`@PathVariable <Type> <name>`
- extracts info from URL, stores into variable
example:
```[Java]
@GetMapping("/{id}")
public Task getTask(@PathVariable Long id) {}
```
`@RequestBody`
- Spring can automatically convert JSON received by API into Java object
Example:
```
POST /tasks
Body = {
    "title": "Study Spring",
    "description": "Finish CRUD API",
    "completed": false,
    "dueDate": "2026-08-01"
}

// converts the JSON into task object
@RequestBody Task task
```

`@ControllerAdvice`
- allows exception handling logic to be shared across multiple controllers

`@RestControllerAdvice`
- allows handling of all expetions globally across all controllers
- automatically serializes error responses into JSON/XML

`@ExceptionHandler(Exception.class)`
- designates function to handle declared exception type

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

## Validation
`@NotBlank(message="")`
- rejects empty strings, whitespace strings, and null inputs.

`@Size(max=optional, min=optional, message="")`
- establishes a minimum or maximum length for Strings.

`@FutureOrPresent(message="")`
- ensures passed date is not in the past.

`@Valid`
- triggers automatic validation on request payload or bean properties.