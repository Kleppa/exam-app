FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
CMD ['java', "-jar" ,'/app.jar']
#ENTRYPOINT ["java","-jar","/app.jar"]
#Not using alpine because of Bcrypt
#FROM node:10 as builder
#RUN mkdir -p /node
#ADD ./package.json /node
#WORKDIR /node
#RUN npm i
#CMD ['node', './src/server/server.js']