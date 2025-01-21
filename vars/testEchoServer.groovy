def call(String message) {
    echo "Testing with message: ${message}"

    // Envia a mensagem para o Echo Server
    def response = sh(
        script: "echo '${message}' | nc localhost 8081",
        returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${response}"

    // Valida a resposta do Echo Server
    if (response == "Message Received: ${message}") {
        echo "Message received correctly by the Echo Server!"
    } else {
        error "Message was not received correctly by the Echo Server!"
    }
}
