FROM maven:3.3-jdk-8-alpine
MAINTAINER Tsundzukani Mokgalaka (tsundzukani.mokgalaka@bcx.co.za)
ENV MAVEN_HOME /usr/share/maven
COPY pom.xml /usr/local/service/pom.xml
COPY testng.xml /usr/local/service/testng.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service/
CMD mvn clean test
