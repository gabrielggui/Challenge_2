# Class Registration API

Challenge proposed by the Compass UOL internship program, whose goal was to develop a RESTful API for registering classes in the Compass Internship Program, capable of handling the basic operations of the four HTTP verbs: GET, POST, PUT, and DELETE. For the development of the API, the Java programming language was used in conjunction with the Spring Boot framework and the MySQL database. Additionally, other relevant dependencies were incorporated for specific purposes, such as testing and documentation, ensuring a comprehensive solution that adheres to RESTful standards.

## Implemented features

* CRUD (Create, Read, Update, Delete, Patch)  
* OpenAPI (using Swagger)  
* ProblemDetails [(RFC 7807)](https://datatracker.ietf.org/doc/html/rfc7807)  
* HATEOAS  
* Unit Tests (JUnit 5, Mockito)  
* Integration Tests (MockMvc)  

### Update (20230823)
* Implemented Flyway
* Added **created_at** and **updated_at** columns in database tables
* DTO implementation using Class Record ("Student" entity only)

## Modeling
### Class diagram
![Class diagram](https://raw.githubusercontent.com/kia735/Challenge_2/main/docs/class_diagram.png)

### Database diagram
![Database diagram](https://raw.githubusercontent.com/kia735/Challenge_2/main/docs/database_diagram.png)

## API documentation
To access the API documentation, it is necessary to start the application and then access the specified endpoint: **/swagger-ui/index.html.** This route will provide an interactive interface that details all relevant information about the API, including available endpoints, accepted parameters, and response formats.
