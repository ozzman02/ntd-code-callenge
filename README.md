# NTD CALCULATOR UI

This application has been created using React 18.3.1 and Bootstrap 5 and it works as the UI for the NTD Calculator API. This is a simple UI that has the following features:

- Generates a random mathematical expression to be calculated.
- Allows the user to type a mathematical expression and calculate its result.
- Visualize user's historical record of operations and calculations in a paginated table.

## Instructions

- The user can type any text in the _Enter a valid mathematical expression_ field but the only valid operators are _+, -, /, *,_ and _sqrt()_. You can use parenthesis _()_ as well.
- Initial user balance has been set to _$5000_.
- If the length of the math exp is _&gt; 1 and &lt; 20_ the operation type will be a _Standard_ math expression. The cost is _$1_.
- If the length of the math exp is _&gt; 20 and &lt; 100_ the operation type will be a _Complex_ math expression. The cost is _$5_.
- If the length of the math exp is _&gt;= 100_ the operation type will be an _Special_ math expression. The cost is _$10_.
- If an operation contains unsupported operators or throws an error the result will be an _Invalid_ math expression. There is no charge for this operation type.
- There is an implicit multiplication operation when using the <b><i>sqrt()</i></b> operation next to another operand. For example: _10+10sqrt(25)_ is equivalent to _10+10*sqrt(25)_.
- _User Mathematical Operations History_ will be populated when there is an invalid or successful result.

### Login
![UI-Login](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/ui/login.png)

User1 credentials:
```sh
user: oscar.santamaria@ntdsoftware.com
password: user1pwd
```
User2 credentials:
```sh
user: karen.navarro@ntdsoftware.com
password: user2pwd
```

### Home Page
![UI-HomePage](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/ui/home-no-data.png)

### Instructions
![UI-HomePage](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/ui/instructions.png)

### Generate Mathematical Expression
![UI-GenMathExp](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/ui/generate-math-exp.png)

### Calculate result
![UI-Calculate](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/ui/calculate.png)

### Paginated table
![UI-Pagination](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/ui/pagination-1.png)


## Sample Mathematical Operations
Feel free to modify this expressions or create yours !

Standard:
```sh
Expression: "895+62+sqrt(30)*100"
Result: 1504.7225575051662
```
Complex:
```sh
Expression: "820+683*744*195/664/769-376/73+319*83-940/551/761-821/740"
Result: 27484.796632746715
```
Special:
```sh
Expression: "895+62+sqrt(30)*100+895+62+sqrt(30)*100+895+62+sqrt(30)*100+895+62+sqrt(30)*100+895+62+sqrt(30)*100+895+62+sqrt(30)*100"
Result: 9028.335345030999
```


# NTD CALCULATOR API

This API has been created following a microservice architecture using Java 17, Maven 3.9.6, Spring Boot 3.3.3, JWT authentication and MySQL database that work in conjunction with Flyway migrations seeds. Mathematical expressions are process with the help of the exp4j library. 

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

## Eureka Service Registry
![EurekaRegistry](https://github.com/ozzman02/ntd-code-challenge/blob/main/files/eureka/eureka.png)


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
|GET|/api/calculator/generate|Generates a random math expression from www.random.org|
|GET|/api/calculator/records/{id}/page/{page}|Paginated user's record|

## Docker images for services
The application is using the _Maven Fabric8 Plugin_ to build and push docker images. Images for services can be found according to the table below:

|Service|Path|Image name|
|----|----|----|
|ntd-eureka-server|/src/main/docker/|osantamaria/ntd-eureka-server|
|ntd-api-gateway|/src/main/docker/|osantamaria/ntd-api-gateway|
|ntd-authorization-service|/src/main/docker/|osantamaria/ntd-authorization-service|
|ntd-user-service|/src/main/docker/|osantamaria/ntd-user-service|
|ntd-calculator-service|/src/main/docker/|osantamaria/ntd-calculator-service|

From the root folder of each project run the following command to build the images:
```sh
mvn clean compile package docker:build
```

## Docker compose
From the ./services folder run:
```sh
docker-compose up -d
```

## Postman Collection
The GBH.postman_collection.json file can be found inside the files/postman directory.

