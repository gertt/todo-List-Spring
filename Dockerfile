FROM openjdk:17

ADD . /home
WORKDIR /home
ADD target/to-do-list.jar home/to-do-list.jar

EXPOSE 9191
ENTRYPOINT ["java", "-jar", "home/to-do-list.jar"]

