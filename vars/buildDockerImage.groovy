def call(String dockerImage, String commitHash) {
    echo "Building Docker image with commit hash"
    def customImage = docker.build("${dockerImage}:${commitHash}")
    customImage.push()
}
