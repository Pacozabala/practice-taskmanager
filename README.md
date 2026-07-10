# practice-taskmanager
A basic to-do list app made using Spring. A project developed for learning RESTful Spring and Java development.

## Commands
Make sure to run these commands in the taskmanager folder, unless otherwise stated.
- Running the application
    - `./mvnw spring-boot:run`: powershell
    - `mvnw.cmd spring-boot:run`: Windows Command Line

## Production Links
- `http://localhost:8080`: Access endpoint
- `http://localhost:8080/h2-console`: H2 console; to be used once we start creating entities.

## Packages
The following packages are located at `taskmanager/src/main/java/com/pacozabala/taskmanager`
- `controller`: contains REST endpoints
- `service`: contains business logic
- `repository`: contains spring data repositories
- `model`: contains entities
- `dto`: contains Data Transfer Objects
- `config`: contains classes for Spring setup