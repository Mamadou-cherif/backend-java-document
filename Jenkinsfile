pipeline {
    agent any

    tools{
        maven 'M3'
    }

    environment {
        // DOCKER_USER = credentials('docker_user')
        // DOCKER_TOKEN = credentials('docker_token')
        DOCKER_USER = "cherifbarry"
        DOCKER_TOKEN = "dckr_pat_6WaYgGJhuzsYajZ946AE6RMAwpI"
    }
    //git status."
    stages {
        stage('checkout') {
            steps {
                echo "____________________________CHECKOUT MAIN ____________________________________"
                // Get some code from a GitHub repository
               git branch: 'feat/docker', url: 'https://github.com/Mamadou-cherif/backend-java-document.git'
                echo "checked out ${env.BRANCH_NAME}"
            }
        }

         stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }

            post{

                success{
                    archiveArtifacts artifacts: '**/*.jar', followSymlinks: false
                }
            }
        }

        stage('Dockerize') {
            steps {
                script{
                    def dockerImage = "${DOCKER_USER}/spring-app"
                    echo "________________________ Build docker image : ${dockerImage}__________________"
                    sh "docker build -f Dockerfile -t ${dockerImage} ."
                }
            }
        }

        stage('Docker Publish') {
            steps {
                script{
                    def dockerImage = "${DOCKER_USER}/spring-app"
                    sh """
                    echo ${DOCKER_TOKEN} | docker login --username ${DOCKER_USER} --password-stdin
                    docker push ${dockerImage}
                    """
                }
            }
        }


      stage('Docker Compose') {
            steps {
                script{
                    sh """
                    docker compose down
                    docker compose up -d
                    """
                }
            }
        }


    }
}
