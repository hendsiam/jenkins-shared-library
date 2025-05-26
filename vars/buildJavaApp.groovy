def call(String imageName) {
    echo "Running Maven build..."
    sh 'mvn clean package'

    echo "Building Docker image..."
    sh "docker build -t ${imageName} ."
}
