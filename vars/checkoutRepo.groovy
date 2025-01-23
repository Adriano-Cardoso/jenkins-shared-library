def call(String repoUrl, String branch, String credentialsId) {
  echo "Starting repository checkout"
  try {
    git credentialsId: credentialsId,
      url: repoUrl,
      branch: branch
    echo 'Repository checked out successfully!'
  } catch (err) {
    echo "Error checking out repository: ${err.message}"
    // You can also notify the user or perform other actions upon error
  }
}