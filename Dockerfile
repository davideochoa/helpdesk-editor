FROM eclipse-temurin:17-jre
COPY target/*.jar HelpDeskTI.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/HelpDeskTI.jar"]
