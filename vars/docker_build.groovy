def call(String imageName, String tag, String dockerUser) {
    sh "docker build -t ${dockerUser}/${imageName}:${tag} ."
}
