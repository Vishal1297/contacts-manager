FROM openjdk:8-jdk-alpine
WORKDIR /opt/app
COPY /build/libs/ContactsManager-0.0.1-SNAPSHOT.jar ContactsManager-0.0.1.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar ContactsManager-0.0.1.jar