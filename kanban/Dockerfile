FROM maven:3.9.4-eclipse-temurin-21-alpine as MAVEN_BUILD

COPY . /build/

WORKDIR /build/

RUN mvn clean install -DskipTests=true




FROM alpine/java:21-jdk as JDK

WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/kanban-api.jar /app/kanban-api.jar
EXPOSE 8080
ENTRYPOINT [  "java",                                                                       \
              "-jar",                                                                       \
              "/app/kanban-api.jar"                                                         \
           ]
