def call() {
    pipeline {
        agent any

        environment {
            XYZ = 'ITI ITI ITI'
            IMAGE_NAME = 'ayaahmed123/jenkins-library'
        }

       stages {
        stage('Build Java & Docker') {
            steps {
                echo 'Building Java app and Docker image...'
                buildJavaApp(env.IMAGE_NAME)
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
