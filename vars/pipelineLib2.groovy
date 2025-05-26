def call() {
    pipeline {
        agent any

        environment {
            XYZ = 'ITI ITI ITI'
            IMAGE_NAME = 'mariamhossam00/jenkins-library'
        }

        stages {
            stage('Build & Dockerize') {
                steps {
                    echo 'Building Docker image for Python app...'
                    sh 'docker build -t my-python-app .'
                }
            }

            stage('Push Docker Image') {
                steps {
                    echo 'Pushing Docker image to DockerHub'
                    pushDockerImage(env.IMAGE_NAME)
                }
            }
        }
    }
}
