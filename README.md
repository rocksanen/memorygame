# Memory Game

![image](https://user-images.githubusercontent.com/99963437/223095654-01d4936d-4383-40e1-a820-9a414e12abe9.png)

### Game where you flip cards to find pairs. Made with JavaFX.
By Otto Oksanen, Eetu Soronen, Hasan Safdari & Samu Oksala
## Table of Contents
* [Instructions](#instructions)
* [Getting Started](#getting-started)
* [About The Project](#about-the-project)
* [Vision](#vision)
* [Builds](#builds)
* [Jenkins](#jenkins)
* [GitHub Actions](#github-actions)
* [Building](#building)


## Instructions
To get started, build the game in Intellij with Java version 17 or higher.

## Getting Started
After building the game, you can select one of three difficulties and get started immediately. Database server not included in the repo.

### FOR TEACHER ONLY
Insert the META-INF folder that was included in the "Tehtävä: 08 - Sovelluksen dokumentaatio: README.md, käyttöohje" return box (Samu Oksala submission) inside the projects resources folder.

## About The Project
This project was made for ohjelmistotuotantoprojekti 1 course at Metropolia UAS.
The project is a simple memory game designed for anyone who wants to improve their memory.

## Vision
Our vision was to create a fun memory game that's easily accessible. The game was made to improve the players mental capabilities.

## Builds
Jar builds are available in the releases section. Java 17 or higher is required to run the game.

## Jenkins
To get tests running through Jenkins, first build and run the Docker image with the following command:
```
docker build -t moby-dick ./ && docker run -d -p 8083:8080 moby-dick
```
Then go to localhost:8083 and you should see the Jenkins page.

To get the jenkins password run the following command:
```
docker exec -it <container id> cat /var/jenkins_home/secrets/initialAdminPassword
```
Then paste the password to the jenkins page, and you should be able to create an admin account and access the jenkins dashboard.

Create a pipeline job with the Jenkinsfile contents. 
Remember replace the placeholder environment variables first or the server tests will fail!

Note the surefire reports have no styling by default. This is due to Jenkins security policy. The feature can be disabled by running the following in the Jenkins script console:
```
System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "")
```

## Github Actions

Runs every time a pull request is made. You can download the surefire reports from the Actions tab on GitHub. Runs the same unit tests as Jenkins.

## Building
Windows builds and Jar files are available  in the releases section. To build the app yourself, first generate a JAR file with Maven, then run the "build.ps1" file to generate a .msi installer. MacOS and Linux have to run the JAR File (Java 17+ required).
