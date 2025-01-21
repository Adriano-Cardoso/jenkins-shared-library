def call(String message) {
    echo "Testing with message: ${message}"

    // Envia a mensagem ao Echo Server usando curl
    def response = sh(
        script: "curl -X POST -d '${message}' -H 'Content-Type: text/plain' http://localhost:8081",
        returnStdout: true
    ).trim()

    echo "Raw response from Echo Server: ${response}"

    // Utiliza uma regex para capturar apenas o corpo da resposta HTTP
    def responseBody = (response =~ /(?:\r?\n){2}(.*)/)[0][1]?.trim()

    echo "Extracted Response Body: ${responseBody}"

    // Verifica se o corpo da resposta contém a mensagem esperada
    if (responseBody == "Message Received: ${message}") {
        echo "Message received correctly by the Echo Server!"
    } else {
        // Falha no pipeline caso a mensagem não tenha sido recebida como esperado
        error "Message was not received correctly by the Echo Server!"
    }
}
