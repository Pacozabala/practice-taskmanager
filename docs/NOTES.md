# Annotations
`@SpringBootApplication`
- tells Spring that this is the main application, and to automatically configure everything.
    - start app, create all necessary objects, start embedded web server, listen on port 8080.

`@RestController`
- tells spring that the class contains REST endpoints.

`@GetMapping`
- maps the HTTP request `GET /hello` to `hello()` method.

# Controllers
- answers incoming HTTP requests
- Browser -> Controller -> Java Code -> Response