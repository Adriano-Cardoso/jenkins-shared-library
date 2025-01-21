def call() {
    // Log the start of the application build process using Maven
    echo "Building the application with Maven"

    // Execute the Maven clean and install commands to build the application
    sh 'mvn clean install'
}
