# Use a base image with the desired version of Java
FROM openjdk:17-jdk-slim as builder

# Set the working directory within the container
WORKDIR application
ADD maven/${project.build.finalName}.jar ${project.build.finalName}.jar
RUN java -Djarmode=layertools -jar ${project.build.finalName}.jar extract

FROM openjdk:17-jdk-slim
LABEL PROJECT_NAME=${project.artifactId} \
      PROJECT=${project.id}

# Expose the port your application is listening on (if applicable)
EXPOSE 8080

WORKDIR application

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.launch.JarLauncher"]