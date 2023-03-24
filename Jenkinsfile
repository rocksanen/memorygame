pipeline {
    agent any

    environment {
        MEMORYMAZE_DB_URL = 'url'
        MEMORYMAZE_DB_USERNAME = 'username'
        MEMORYMAZE_DB_PASSWORD = 'password'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'eetu']],
                doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
                userRemoteConfigs: [[url: 'https://github.com/rocksanen/memorygame.git']]])
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
                sh 'mvn surefire-report:report'
            }
        }
        stage('Publish Report') {
            steps {
                publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/site/',
                    reportFiles: 'surefire-report.html',
                    reportName: 'Test Report',
                    reportTitles: 'My Test Results'])
            }
        }
    }
}
