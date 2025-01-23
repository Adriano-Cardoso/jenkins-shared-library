def call(String message) {
    echo "Testing with message: ${message}"

    // Send the message to the Echo Server via curl
    def response = sh(
        script: "curl -X POST -d '${message}' http://localhost:8081",
        returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${response}"

    // Extract the relevant part of the response (assuming the Echo Server responds with "Message Received: <message>")
    def messageReceived = response.split("\n").last().trim()

    if (messageReceived == "Message Received: ${message}") {
        echo "Message received correctly by the Echo Server!"
    } else {
        error "Message was not received correctly by the Echo Server!"
    }
}
