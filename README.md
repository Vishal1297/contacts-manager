# contacts-manager
### A Spring Boot REST CRUD API for managing contacts.

### Steps to Setup

**1. Clone the application**

```bash
https://github.com/Vishal1297/contacts-manager.git
```

**2. Create Mysql database**
```bash
create database contacts
```
**Note: MySQL database installation is required.**

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using gradle**

```bash
gradle wrapper

gradlew build

java -jar build/libs/ContactsManager-0.0.1-SNAPSHOT.jar

```

The app will start running at <http://localhost:8080>.

### Explore Rest APIs

The app defines following CRUD APIs.

    POST /contact/v1/contact

    - Used to save or update contact

    GET /contact/v1/contacts

    - Get all existing contacts

    GET /contact/v1/contacts/{postalCode}

    - Get all contacts filtered by postal code

    GET /contact/v1/contact/{uuid}

    - Get contact by uuid

    DELETE /contact/v1/contact/{uuid}

    - Delete contact by uuid

### Additional Links

These additional references should also help you:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.3/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#production-ready)
