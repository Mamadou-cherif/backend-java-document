pipeline {
    agent any

    tools{
        maven 'M3'
    }

    environment {
        DOCKER_USER = credentials('docker_user')
        DOCKER_TOKEN = credentials('docker_token')
    }
    //git status." 
    stages {
        stage('checkout') {
            steps {
                echo "____________________________CHECKOUT MAIN ____________________________________"
                // Get some code from a GitHub repository
              // git branch: 'feat/docker', url: 'https://github.com/Mamadou-cherif/backend-java-document.git'
                echo "checked out ${env.BRANCH_NAME}"
            }
        }
         stage('Build') {
            steps {
                sh "mvn clean package -DskipTests"
            }
            post{
                success{
                    archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
                }
            }
        } 
        stage('Down postgres-db before starting'){
            steps{
                sh "docker stop postgres-db";
            }
        }
        stage('Dockerize develop only') {
            when {
              branch 'develop'
            }
            steps{
                 sh "docker stop postgres-db";
            }
            steps {
                script{
                    def dockerImage = "${DOCKER_USER}/spring-app:${env.BRANCH_NAME}"
                    echo "________________________ Build docker image : ${dockerImage}__________________"
                    sh "docker build -f Dockerfile -t ${dockerImage} ."
                }
            }
        }

        stage('Docker Publish develop') {
            when {
              branch 'develop'
            }

            steps {
                script{
                    def dockerImage = "${DOCKER_USER}/spring-app:${env.BRANCH_NAME}"
                    sh """
                    echo ${DOCKER_TOKEN} | docker login --username ${DOCKER_USER} --password-stdin
                    docker push ${dockerImage}
                    """
                }
            }
        }

      stage('Docker Compose') {
          when {
            branch 'develop'
          }

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
