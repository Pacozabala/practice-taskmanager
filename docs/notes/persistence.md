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