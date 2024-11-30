def buildNodeApp() {
    echo "Building the application..."
    dir('app') {
        sh 'npm install'
    }
}

def runTests() {
    echo "Running unit tests..."
    dir('app') {
        sh 'npm test'  // This will generate the JUnit-compatible XML report
    }
}

def buildImage() {
    echo "Build & Push docker image..."
    withCredentials([usernamePassword(credentialsId: 'Dockeraut', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t marwasa/depi-gp:latest ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push marwasa/depi-gp:latest"
    }
}

def deployApp() {
    echo "Deploying the application to EKS..."

    Use AWS credentials
    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'jenkins-aws-access']]) {
        //Uncomment if you have a kubeconfig file
        withCredentials([file(credentialsId: 'your-kubeconfig-id', variable: 'KUBECONFIG_FILE')]) {
            // Set the KUBECONFIG environment variable
            sh 'export KUBECONFIG=$KUBECONFIG_FILE'

        Change to the directory containing your deployment and service files
        dir('/home/nour/depi/Final-DEPI-Project/') {
            sh 'kubectl apply -f k8s-deployment.yaml'
            sh 'kubectl apply -f k8s-service.yaml'
        }
        } // Uncomment this closing bracket if you are using a kubeconfig file
    }
}

return this
