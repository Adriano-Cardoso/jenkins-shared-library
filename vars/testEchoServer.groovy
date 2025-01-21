def call(String message) {
    echo "Testing with message: ${message}"

    // Envia a mensagem para o Echo Server via curl
    def response = sh(
        script: "curl -X POST -d '${message}' http://localhost:8081",
        returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${response}"

    // Extrai a parte relevante da resposta (assumindo que o Echo Server responde com "Message Received: <mensagem>")
    def messageReceived = response.split("\n").last().trim()

    // Valida a resposta do Echo Server
    if (messageReceived == "Message Received: ${message}") {
        echo "Message received correctly by the Echo Server!"
    } else {
        error "Message was not received correctly by the Echo Server!"
    }
}
