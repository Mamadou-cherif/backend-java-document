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
            when {
              branch 'develop'
            }

            steps {
                script{
                    def dockerImage = "${DOCKER_USER}/spring-app"
                    echo "________________________ Build docker image : ${dockerImage}__________________"
                    sh "docker build -f Dockerfile -t ${dockerImage} ."
                }
            }
        }

        stage('Docker Publish on docker with jenkins') {
        when {
          branch 'develop'
        }

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

//             steps {
//                 script{
//                     sh """
//                     docker compose down
//                     docker compose up -d
//                     """
//                 }
//             }
             steps {
                    script {
                        def postgresRunning = sh(script: "docker ps --filter 'name=postgres-db' --format '{{.Names}}'", returnStdout: true).trim()

                        if (postgresRunning) {
                            echo "Le conteneur postgres-db est déjà en cours d'exécution. Aucun redémarrage nécessaire."

                            sh "docker compose up -d spring-app"
                        } else {
                            echo "Le conteneur postgres-db est arrêté ou inexistant. Lancement complet..."
                            sh "docker compose up -d"  // Démarre tout si PostgreSQL n'existe pas
                        }
                    }
                }
        }






    }
}
