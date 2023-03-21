FROM gradle:jdk17-jammy

COPY . /sources
RUN  cd /sources && gradle build

#run the spring boot application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/sources/build/libs/backend-0.0.1-SNAPSHOT.jar"]