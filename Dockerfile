FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
COPY build/libs/ContactsManager-0.0.1-SNAPSHOT.jar ContactsManager-0.0.1.jar
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005", "-jar", "ContactsManager-0.0.1.jar"]