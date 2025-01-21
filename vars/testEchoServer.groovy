def call(String message) {
    echo "Testing with message: ${message}"

    // Envia a mensagem ao Echo Server usando curl
    def response = sh(
        script: "curl -X POST -d '${message}' -H 'Content-Type: text/plain' http://localhost:8081",
        returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${response}"

    // Extrai apenas o corpo da resposta HTTP para validação
    def responseBody = response.split("\r\n\r\n")[-1].trim()

    // Verifica se o corpo da resposta contém a mensagem esperada
    if (responseBody == "Message Received: ${message}") {
        echo "Message received correctly by the Echo Server!"
    } else {
        // Falha no pipeline caso a mensagem não tenha sido recebida como esperado
        error "Message was not received correctly by the Echo Server!"
    }
}
