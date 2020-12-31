# servicemanual-java
Pretask given by Etteplan MORE

This application is for keeping records of maintenance jobs for factory devices.

Application supports finding all or a specific factory device(s), adding, editing and deleting factory devices and maintenance jobs for devices as well as finding all or a specific maintenance job(s) which are sorted first by criticality and then by entry date.

**Added in December 2020:**
- Operation for loading data to FactoryDevice table from seeddata.csv on startup
- Pageable endpoints for both factory devices and maintenances


## How to run this application

First create schema to MySQL called servicemanual and change username and password in resources/application.properties to right values. Then
```
mvn spring-boot:run
```

## Built with
* Maven
* Spring Boot
* MySql database


## Tools for testing

**Postman:** https://www.getpostman.com/collections/3c42b822070ab924fe92

**Swagger:** http://localhost:8080/swagger-ui.html

