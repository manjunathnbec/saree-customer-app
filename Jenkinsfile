pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('BUILD') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew assemble'
            }

        }
        stage('TEST') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew test'
            }

        }

    }

}