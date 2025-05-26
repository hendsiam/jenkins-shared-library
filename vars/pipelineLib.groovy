def buildDockerImage() {
    echo "🔨 Building Docker image..."
    def imageName = "hendsiam/jenkins"
    sh "docker build -t ${imageName}:v${env.BUILD_NUMBER} ."
}

def pushDockerImage() {
    echo "🚀 Pushing Docker image to DockerHub..."
    def imageName = "hendsiam/jenkins"
    withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
        sh "docker push ${imageName}:v${env.BUILD_NUMBER}"
    }
}

def buildAndPushDockerImage() {
    buildDockerImage()
    pushDockerImage()
}

