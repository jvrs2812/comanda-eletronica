FROM maven:3.6.3-jdk-19

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src

RUN mvn package

EXPOSE 8080

CMD ["java", "-jar", "target/app.jar"]