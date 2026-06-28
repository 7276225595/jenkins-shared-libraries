def call(String imageName, String tag, String dockerHubUser) {

    echo "Building Docker Image"

    sh """
        whoami
        docker build -t ${imageName}:${tag} .
    """

    sh """
        docker image tag ${imageName}:${tag} ${dockerHubUser}/${imageName}:${tag}
    """
}
