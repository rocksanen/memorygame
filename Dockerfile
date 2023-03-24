FROM jenkins/jenkins:jdk17-preview

USER root

# Install OpenJDK 17 and Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set environment variables
# ENV JAVA_HOME /usr/lib/jvm/java-17-openjdk-amd64/


# ENV MEMORYMAZE_DB_URL=jdbc:sqlserver://tietokanta.database.windows.net:1433;database=memorizer;user=memorizee@tietokanta;password={MietiMietiMieti3};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30; \
#     MEMORYMAZE_DB_PASSWORD=MietiMietiMieti3 \
#     MEMORYMAZE_DB_USERNAME=memorizee



EXPOSE 8080

USER jenkins