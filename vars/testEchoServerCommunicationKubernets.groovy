def testEchoServerCommunication() {
  script {
    echo 'Testing communication with the Echo Server...'
    def podName = sh(
      script: """
          kubectl --kubeconfig=${env.KUBE_CONFIG} get pod -l app=echo-server -o jsonpath='{.items[0].metadata.name}'
      """,
      returnStdout: true
    ).trim()

    echo "Pod Name: ${podName}"

    def curlResponse = sh(
      script: """
          kubectl --kubeconfig=${env.KUBE_CONFIG} exec ${podName} -- curl -X POST -d "${params.ECHO_MESSAGE}" http://localhost:8081
      """,
      returnStdout: true
    ).trim()

    echo "Response from Echo Server: ${curlResponse}"
    if (curlResponse.contains("Message Received: ${params.ECHO_MESSAGE}")) {
      echo "Message received correctly by the Echo Server!"
    } else {
      error "Message was not received correctly by the Echo Server!"
    }
  }
}