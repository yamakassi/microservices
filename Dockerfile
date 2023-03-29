# Уровень 1: сборка проекта Gradle
#FROM gradle:7.6 as builder
#WORKDIR /home/gradle
#COPY build.gradle settings.gradle gradlew ./
#COPY gradle ./gradle
#RUN gradle --no-daemon dependencies
#COPY src ./src

#RUN gradle --no-daemon bootJar


# Уровень 2: запуск приложения в контейнере
FROM openjdk:17 as myapp2
VOLUME /tmp



COPY  /build/libs/*.jar application.jar
ENTRYPOINT ["java","-jar","application.jar"]
