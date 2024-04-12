# Deploying a Spring Boot application with Render

### Migrating from **mySQL** to **Postgress** database

-   Remove mySql connector dependency and add postgres dependency

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```

-   Update `application.properties` file

```
spring.datasource.url=jdbc:postgresql://<external-database-url-no-user>:<host>/<db-name>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.hibernate.ddl-auto=update
```

Test your endpoints with Postman so schema gets created

## Before you push your code to Github, it is a good idea to put sensitive data in `.env` file

-   Create `.env` in the root project folder
-   Use all uppercase snake case convention for naming your variables, for example

```js
DB_PASSWORD = somePassword;
```

-   Add the following line of code to the top of `application.properties` file:

```
spring.config.import=optional:file:.env[.properties]
```

-   Import your variables in `application.properties` like this

```
spring.datasource.password=${DB_PASSWORD}
```

Don't forget to add `.env` to `.gitignore` file

## Create a Dockerfile

Before deploying, you will need to remove the last line from application.properties - `spring.jpa.hibernate.ddl-auto=update`
