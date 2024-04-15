# Deploying a Spring Boot application with Render

## Create a Render account and authorize Github

-   Go to [Render dashboard](https://dashboard.render.com/register?next=%2F) and create an account
-   Once singed in, click on the down arrow in the top right corner and navigate to `Account settings`
-   Click `Connect Github` button and follow the steps to finalise it

![Connecting Github Account](./images/render_connect_github.png)

## Create a Postgres database in Render

-   Click `New +` button in the top navbar and then `PostgreSQL`

![New PostgreSQL db](./images/render_add_new_postgres.png)

-   Fill out all the required fields

![Database form](./images/render_db_details.png)

**If you don't want to get charged make sure to select Free Instance Type**

-   You should end up on a page that has all the database details, including `Connection details`

![Connection details](./images/render_postgres_connection_details.png)

## Migrate your Spring app from **MySQL** to **Postgres** database

-   Remove MySQL connector dependency and add Postgres dependency

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

Database connection details can be found in `Dashboard` -> `<Your db name>`

For example, if your `External Database URL` looks like this:

```
postgres://recipes_sgrkgjh_user:n1B5i8ZJqOLuAXrLz5KOqHhjbdfbeg589KZ@dpg-csbjgbbktjm4es789rricg-a.singapore-postgres.render.com/recipes_Iobfue
```

the value of `spring.datasource.url` should be:

```
jdbc:postgresql://n1B5i8ZJqOLuAXrLz5KOqHhjbdfbeg589KZ@dpg-csbjgbbktjm4es789rricg-a.singapore-postgres.render.com:5432/recipes_Iobfue
```

Test your endpoints with Postman so schema gets created

## Move sensitive data to `.env` file

-   Create `.env` in the root project folder
-   Use all uppercase snake case convention for naming your variables, for example

```js
DB_PASSWORD = somePassword;
```

-   Add the following line of code to the top of `application.properties` file to make sure values of environment variables load correctly:

```
spring.config.import=optional:file:.env[.properties]
```

-   Import your variables in `application.properties` like this

```
spring.datasource.password=${DB_PASSWORD}
```

Don't forget to add `.env` to `.gitignore` file

## Create a Dockerfile

Render does not support Java environment by default, so you will need to use a Dockerfile to deploy your project

-   Create a file called `Dockerfile` in the root folder of your project and add the follwoing content:

```Dockerfile
FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/<you_app_name>-0.0.1-SNAPSHOT.jar <your_app_name>.jar
EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "<your_app_name>.jar" ]
```

Push your updated code to Github

## Deploy code from Github on Render

-   Go back to Render `Dashboard` and select `New +` -> `Web Service`

![Creating new Web Service](./images/)

-   Select `Build and deploy from Git repository`

![From Git repository](./images/render_deploy_from_git.png)

-   Select a repository you want to deploy from the list or paste a url to a public repository if not on the list

![Repository list](./images/render_select_repository_to_deploy_from.png)

-   Fill out the form, make sure to choose `Docker` as runtime

![Web Service details](./images/render_webservice_details.png)

Just like with the database setup, make sure to select **Free Instance Type** if you don't want to get charged

-   Add environment variables - before you click `Create Web Service`, make sure to add your environment variables, otherwise your app won't be able to connect to the database

-   Choose the option to add variables from an env file - this will be the quickest way, you will be able to just copy and paste the contents of your local `.env`

![Adding data from .env file](./images/render_add_env_variables_btn.png)
![Pasting data from `env` file](./images/render_adding_env_variables.png)

-   **IMPORTANT!** At this point you will need to update the value of the `DB_DATASOURCE` variable. Both the app and the database will be running on Render which means you should be using the INTERNAL DATABASE URL instead of the EXTERNAL one

Your internal url should look something like this:

```
postgres://recipes_i0pn_user:n1B5i8ZJqOLuAXrLz5KOqjeljtlRZ@dpg-cobmbktjm4esfkej6879ricg-a/recipes_Iobfue
```

The value of `DB_DATASOURCE` should look like this:

```
jdbc:postgresql://dpg-cobmbktjm4esfkej6879ricg-a:5432/recipes_Iobfue
```

so it should match the format `jdbc:postgresql://<hostname>:<host>/db_name`

-   Once all your variables are added, select `Create Web Service`

-   Deployment will start automatically

![Deployment](./images/render_deployment_started.png)

-   Once it's finished, `Building...` will be replaced with `Live`, you will also see a url to access your API

![Deployment successful](./images/render_deployment_successful.png)

You can test your API in Postman, just make sure to add your endpoint to the URL, for example

```
https://spring-boot-postgres-render-deployment.onrender.com/recipes
```

## Deployment failed - what should you do

-   In a situation where your deployment is not successful, the most important thing would be to check the logs and read error messages

![Failed deployment](./images/render_deployment_failed.png)

-   Use the regular bug fixing strategies - research, time-boxing, reaching out to the coaches and your peers for help

-   Once your code is fixed, push it to Github, then manually redeploy from the latest commit

![Manual redeploy](./images/render_redeploy.png)

You can also look into setting up CI/CD
