def call(String dockerImage, String commitHash) {
    echo "Running Docker container"
    def customImage = docker.build("${dockerImage}:${commitHash}")

    // Remove any previous container with the same name, if exists
    sh """
    docker ps -a -q --filter "name=demo-echo-server-container" | grep -q . && docker rm -f demo-echo-server-container || echo "No container to remove"
    """

    // Run the Docker container without the host network flag
    customImage.run("-d --name demo-echo-server-container -p 8081:8081")

    // Wait for the container to start
    sleep(10)
}
