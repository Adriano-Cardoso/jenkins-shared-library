def deployToKubernetes() {
  echo 'Applying the Kubernetes deployment and service...'
  sh """
      kubectl --kubeconfig=${env.KUBE_CONFIG} apply -f echo-server-deployment.yaml
      kubectl --kubeconfig=${env.KUBE_CONFIG} wait --for=condition=ready pod -l app=echo-server --timeout=60s
      echo 'Echo server deployed successfully!'
  """
}