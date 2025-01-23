def testKubernetesCommunication(String kubeConfig, String echoMessage) {
    echo 'Testing communication with the Echo Server...'
    
    def podName = sh(
        script: """
            kubectl --kubeconfig=${kubeConfig} get pod -l app=echo-server -o jsonpath='{.items[0].metadata.name}'
        """,
        returnStdout: true
    ).trim()

    echo "Pod Name: ${podName}"

    def curlResponse = sh(
        script: """
            kubectl --kubeconfig=${kubeConfig} exec ${podName} -- curl -X POST -d "${echoMessage}" http://localhost:8081
        """,
        returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${curlResponse}"
    if (curlResponse.contains("Message Received: ${echoMessage}")) {
        echo "Message received correctly by the Echo Server!"
    } else {
        error "Message was not received correctly by the Echo Server!"
    }
}