pipeline {
    agent any

    environment {
        XYZ = 'ITI ITI ITI'
        IMAGE_NAME = 'hendsiam/jenkins'
    }

    stages {
        stage('Build & Dockerize') {
            steps {
                echo 'Building Docker image for Python app...'
                sh "docker build -t ${IMAGE_NAME}:latest ."
            }
        }
        stage('Push Docker Image') {
            steps {
                echo 'Pushing Docker image to DockerHub'
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
                    sh "docker push ${IMAGE_NAME}:latest"
                }
            }
        }
    }
}
