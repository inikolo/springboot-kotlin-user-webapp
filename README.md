# User Web App as a Spring Boot Kotlin Maven Project

## A simple web application for storing and retrieving users written in Kotlin by using Spring Boot and Maven

This project is written in Kotlin by using Spring Boot as a server-side framework and Maven as a build automation tool. It is a simple web application for storing and retrieving users from an in-memory H2 database. It contains 2 API endpoints:

### 1. Create user
`POST http://localhost:8080/users`

Request Body (JSON):
```json
{
  "name": "John Doe",
  "email": "test@users.com",
  "password": "any-random-string"
}
```
There are 2 possible outcomes:
#### 1.1 User created successfully
Response HTTP Status Code: 202 Accepted

Response Body: Empty

#### 1.2 User creation failed due to duplicate email in the system
Response HTTP Status Code: 400 Bad Request

Response Body (JSON):
```json
{ "error": "Duplicate e-mail: <entered_email>" }
```

### 2. Get limited number of users whose name starts with a case-insensitive prefix
`GET http://localhost:8080/users?query={namePrefix}&limit={numOfUsers}`

Default values for query parameters, if not provided explicitly:

`query = "" (empty string)`

`limit = 10`

Response HTTP Status Code: 200 OK

Response Body (JSON):
```json
{
  "users": [
    {"email": "test@users.co", "name": "John Doe"},
    {"email": "test1@users.co", "name": "John Senna"}
  ],
  "totalCount": 42
}
```
where `totalCount` is the total number of users with the specified name prefix.

## How to build the project
1. Open terminal or command prompt
2. Go to the root directory of the project
3. Run `.\mvnw clean install`

This will create a jar file, e.g. `user-0.0.1-SNAPSHOT.jar` in the `<root_dir>\target` dir that can be run to start the application.

## How to start the application
1. Open terminal or command prompt
2. Go to the `<root_dir>\target` dir where the application jar file was created after building the project
3. Run `java -jar <app_jar_file.jar>`, e.g. `java -jar user-0.0.1-SNAPSHOT.jar`

The application is running on `http://localhost:8080/` and its healthcheck can be accessed on `http://localhost:8080/actuator/health`