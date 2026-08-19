# CRUD API
| HTTP Method | Purpose |
| --- | --- |
| GET | Read data |
| POST | Create new data |
| PUT | Replace update existing data |
| DELETE | Remove data |

## ResponseEntity
- instead of returning only data, sometimes you need to control status code/headers/response body
Examples:
```[Java]
// 200 OK
return ResponseEntity.ok(task);

// 404 Not Found
ResponseEntity.notFound().build();

// 204 No Content
ResponseEntity.noContent().build()
```
