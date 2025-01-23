def deployToKubernetes(String kubeConfig, String yamlFile) {
    echo "Applying Kubernetes deployment and service..."
    sh """
        kubectl --kubeconfig=${kubeConfig} apply -f ${yamlFile}
        kubectl --kubeconfig=${kubeConfig} wait --for=condition=ready pod -l app=echo-server --timeout=60s
        echo 'Deployment and service applied successfully!'
    """
}
