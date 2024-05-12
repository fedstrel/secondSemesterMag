pipeline {
    agent any

    stages {
        stage("CleanPackage") {
            steps {
                bat 'mvn clean package'
            }
        }
        stage("RunTestsOnFeature") {
            when {
                branch 'feature-*'
            }
            steps {
                bat 'mvn test'
            }
        }
        stage("Checkstyle") {
            when {
                branch 'develop'
            }
            steps {
                bat 'mvn checkstyle:check'
            }
        }
        stage("ReportCoverage") {
            when {
                branch 'feature-*'
            }
            steps {
                junit testResults: '**/surefire-reports/*.xml'
                jacoco()
            }
        }
        stage("Install") {
            steps {
                bat 'mvn install'
            }
        }
        stage("Publish") {
            steps {
                bat 'copy "UsageModule\\target\\UsageModule-1.0-SNAPSHOT.jar" "C:\\taskNumberOne-1.0.jar"'
            }
        }
    }
}