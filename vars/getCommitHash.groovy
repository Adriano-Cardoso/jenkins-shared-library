def call() {
    // Execute the Git command to retrieve the current commit hash
    // The 'git rev-parse HEAD' command fetches the SHA-1 hash of the latest commit
    // The 'returnStdout: true' ensures the command output is captured
    // The 'trim()' removes any leading or trailing whitespace from the output
    return sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
}
