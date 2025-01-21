def call(String repoUrl, String branch, String credentialsId) {
    echo "Starting repository checkout"
    git credentialsId: credentialsId,
        url: repoUrl,
        branch: branch
}
