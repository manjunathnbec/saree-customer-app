pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('BUILD') {
            steps {
                sh './gradlew assemble'
            }

        }
        stage('TEST') {
            steps {
                sh './gradlew test'
            }

        }

    }

}