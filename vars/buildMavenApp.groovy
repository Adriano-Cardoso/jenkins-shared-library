def call() {
    echo "Building the application with Maven"
    sh 'mvn clean install'
}
