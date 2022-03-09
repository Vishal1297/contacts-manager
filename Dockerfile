FROM adoptopenjdk/openjdk8-openj9
WORKDIR /opt/app
COPY build/libs/ContactsManager-0.0.1-SNAPSHOT.jar ContactsManager-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "ContactsManager-0.0.1.jar"]