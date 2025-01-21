def call(String dockerImage, String commitHash) {
    // Log the start of the Docker image build process
    echo "Building Docker image with commit hash"

    // Build the Docker image with the specified image name and commit hash as the tag
    def customImage = docker.build("${dockerImage}:${commitHash}")

    // Push the built Docker image to the Docker registry
    customImage.push()
}
