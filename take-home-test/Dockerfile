FROM maven:3.6.0-jdk-8-alpine

COPY . /usr/app/

WORKDIR /usr/app/

RUN sh -c 'mvn clean install'

WORKDIR /usr/app/target/

RUN sh -c 'touch take-home-test-0.0.1-SNAPSHOT.jar'

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "take-home-test-0.0.1-SNAPSHOT.jar"]