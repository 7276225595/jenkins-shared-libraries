// vars/deploy.groovy
def call(Map config = [:]) {
    def imageName = config.imageName ?: error("Image name is required")
    def containerName = config.containerName ?: 'notes-app-container'
    def hostPort = config.hostPort ?: '8000'
    def containerPort = config.containerPort ?: '8000'
    def restartPolicy = config.restartPolicy ?: 'unless-stopped'
    
    echo "Deploying application with image: ${imageName}"
    
    sh """
        # Stop and remove existing container
        docker stop ${containerName} || true
        docker rm ${containerName} || true
        
        # Run new container
        docker run -d \
          --name ${containerName} \
          -p ${hostPort}:${containerPort} \
          --restart ${restartPolicy} \
          ${imageName}
        
        # Verify container is running
        if docker ps | grep -q ${containerName}; then
            echo "✅ Application deployed successfully on port ${hostPort}"
        else
            echo "❌ Deployment failed"
            exit 1
        fi
    """
}
