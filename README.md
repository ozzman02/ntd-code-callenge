# NTD CALCULATOR API

This API has been created following a microservice architecture using Java 17, Maven 3.9.6, Spring Boot 3.3.3, JWT authentication and MySQL database that work in conjunction with Flyway migrations seeds 

## Microservices

- NTD Eureka Server: All microservices are registered as eureka clients allowing service discovery.
- NTD Api Gateway: All requests are submitted to the api gateway.
- NTD Authorization Service: This service generates secure JWT tokens.
- NTD User Service: This service handles user information.
- NTD Calculator Service: This service generates and process mathematical expressions. 

## Technical Notes about MySQL tables

### Flyway migrations
Migrations seeds are executed by the ntd-user-service. They can be found inside the resources/db/migration folder. Once the application is built the following tables are going to be created and populated:

- Users table: Stores user information

|Field|Description|
|----|----|
|Id|user id|
|Username|user email address|
|Password|Encrypted user password|
|Status|User status, active or inactive|
|Balance|User's credit to perform calculations|
|Created date|Record created date|

- Operations table: Operation types that are processed by the calculator 

|Field|Description|
|----|----|
|Id|Operation id|
|Type|Standard, complex, special or invalid|
|Cost|Operation cost/charge that is deducted from user's balance|
|Created date|Record created date|

- Records table: Historical user calculations record

|Field|Description|
|----|----|
|Id|Record id|
|Operation id|A reference to the operation table id|
|User id|A reference to the user table id|
|Amount|Operation cost/charge|
|User balance|Historical balance info|
|Operation value|Mathematical expression|
|Operation response|Calculation result|
|Created date|record created date|


## Swagger UI and API endpoints

### Authorization Service
The swagger ui is available for this service at _http://localhost:8081/swagger-ui/index.html_

![AuthServiceSwagger](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/swagger/swagger-auth-service.png)

### API Endpoints
|HTTP Method|Request Mapping|Description|
|----|----|----|
|POST|/api/auth/login|Auth endpoint|

### User Service
The swagger ui is available for this service at _http://localhost:8082/swagger-ui/index.html_

![UserServiceSwagger](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/swagger/swagger-user-service.png)

### API Endpoints
|HTTP Method|Request Mapping|Description|
|----|----|----|
|GET|/api/user/getUserByUsername/{username}|Finds a user by username|
|GET|/api/user/getUserByUsername/{username}/{status}|Finds a user by username and status|

### Calculator Service
The swagger ui is available for this service at _http://localhost:8083/swagger-ui/index.html_

![CalculatorServiceSwagger](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/swagger/swagger-calculator-service.png)

### API Endpoints
|HTTP Method|Request Mapping|Description|
|----|----|----|
|POST|/api/calculator|Process a mathematial expression|
|GET|/api/calculator/generate|Generates a random mathematical expression from www.random.org|
|GET|/api/calculator/records/{id}/page/{page}|Paginated user's record|

## Postman Collection
The GBH.postman_collection.json file can be found inside the files/postman directory.

