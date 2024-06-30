# Building Machine Rental
<!-- ABOUT THE PROJECT -->
## 🎓 About The Project 🎓
Building Machine Rental, which is a REST API with hexagonal architecture, developed using Spring Framework. During creation of the application I did my best to follow both Clean Code and SOLID principles, and have used design patterns and technologies that constitute the backbone of modern web development. These includes Spring Boot, Spring Data JPA, Lombok. JUnit and Mockito were used for testing. The application is connected to the PostgreSQL, which serves as a database. It is managed by Liquibase.
App enables renting and returning machines, browsing machines with details and much more. This version is also containerised, saving precious time wasted for installation and configuration of the environment and database. Testcontainers is used for integration testing purpose. 

<!-- Build With -->
## 🔨 Built With 🔨
* Java 21
* Maven
* PostgreSQL Database

<!-- TECHNOLOGIES USED -->
## ⚙️ Technologies used ⚙️
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [PostgreSQL](https://www.postgresql.org/)
* [JUnit 5](https://junit.org/junit5/)
* [Testcontainers](https://testcontainers.com/)
* [Lombok](https://projectlombok.org/)
* [Docker](https://www.docker.com/)
* [Liquibase](https://www.liquibase.com/)

  <!-- INSTALLATION AND USAGE -->
## 🧭 Installation and Usage 🧭
There are three distinct ways to get the application running. 
First start off with cloning the repository using:

`git clone https://github.com/k-bielecki/Building-Machine-Rental.git`
### First way:
Just open the project via Your IDE. You need installed and set-up version of PostgreSQL on Your system. Once it is configured and working on port 5432, it needs tables. Don't worry Liquibase will handle that for you, when You will run project by your IDE.

### Second way:
In this case we will need Docker installed. Once it is set up, simply go to the folder where the files are located and use command:

`docker-compose up`

It takes some time to build the images and run containers, but once it's done application is ready to be used. Volume is mounted for the database container, so data will be persisted after shutting the container down.  

## 🎯 Functionalities 🎯
In current state of the app, all endpoints are accessible via HTTP requests on localhost:8080.
For your convenience, the application includes built-in Swagger, which allows you to access all the endpoints.
Additionally, the database is filled with sample data upon startup.

`http://localhost:8080/swagger-ui/index.html`

