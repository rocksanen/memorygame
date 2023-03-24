FROM jenkins/jenkins:jdk17-preview

USER root

RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/* \

RUN jenkins-plugin-cli --plugins "maven-plugin htmlpublisher"




EXPOSE 8080

USER jenkins