FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/recipe_app-0.0.1-SNAPSHOT.jar recipe_app.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "recipe_app.jar" ]
