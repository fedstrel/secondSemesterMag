
pipeline {
    agent any
    
    environment {
        M2_HOME = "/Users/f.strelnikov/Documents/apache-maven-3.9.6"
        PATH = "${M2_HOME}/bin:${PATH}"
    }

    stages {
        stage("Build") {
            steps {
                sh 'mvn clean package'
            }
        }
        stage("Tests") {
            when {
                branch 'feature-jenkins-*'
            }
            steps {
                sh 'mvn test'
            }
        }
        stage("Checkstyle") {
            when {
                branch 'develop'
            }
            steps {
                sh 'mvn checkstyle:check'
            }
        }
        stage("Report") {
            when {
                branch 'feature-jenkins-*'
            }
            steps {
                junit testResults: '**/surefire-reports/*.xml'
                jacoco()
            }
        }
        stage("Install") {
            steps {
                sh 'mvn install'
            }
        }
        stage("Publish") {
            steps {
                sh 'cp UsageModule/target/UsageModule-1.0-SNAPSHOT.jar /Users/f.strelnikov/Documents/publicationVsu'
            }
        }
    }
}
