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