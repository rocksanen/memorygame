# Memory Game

![image](https://user-images.githubusercontent.com/99963437/223095654-01d4936d-4383-40e1-a820-9a414e12abe9.png)

### Game where you flip cards to find pairs. Made with JavaFX.
By Otto Oksanen, Eetu Soronen, Hasan Safdari & Samu Oksala
## Table of Contents
* [Instructions](#instructions)
* [Getting Started](#getting-started)
* [About The Project](#about-the-project)
* [Vision](#vision)
* [Jenkins](#jenkins)


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

## Jenkins
First build and run the Docker image with the following command:
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
Remember to set the environment variables in the Jenkinsfile or the server tests will fail!