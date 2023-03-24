pipeline {
    agent any

    environment {
        MEMORYMAZE_DB_URL = 'jdbc:sqlserver://tietokanta.database.windows.net:1433;database=memorizer;user=memorizee@tietokanta;password={MietiMietiMieti3};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;'
        MEMORYMAZE_DB_USERNAME = 'memorizee'
        MEMORYMAZE_DB_PASSWORD = 'MietiMietiMieti3'
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
            }
        }
    }
}
