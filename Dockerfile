FROM jenkins/jenkins:jdk17

USER root

RUN apt-get update && \
    apt-get install -y maven
RUN jenkins-plugin-cli --plugins "maven-plugin:3.9" htmlpublisher


EXPOSE 8080

USER jenkins