def call(String message) {
    echo "Testing with message: ${message}"

    // Send the message to the Echo Server using curl
    def response = sh(
        script: "curl -X POST -d '${message}' http://localhost:8081",
        returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${response}"

    // Validate the response from the Echo Server
    if (response == "Message Received: ${message}") {
        echo "Message received correctly by the Echo Server!"
    } else {
        // Fail the pipeline if the message was not received as expected
        error "Message was not received correctly by the Echo Server!"
    }
}
