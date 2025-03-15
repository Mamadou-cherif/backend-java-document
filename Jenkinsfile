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
            when { branch 'develop' }
            steps {
                script{
                    def dockerImage = "${DOCKER_USER}/spring-app"
                    echo "________________________ Build docker image : ${dockerImage}__________________"
                    sh "docker build -f Dockerfile -t ${dockerImage} ."
                }
            }
        }

        stage('Docker Publish on docker with jenkins') {
            when { branch 'develop' }

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
            when { branch 'develop' }

            steps {
                    script {
                        def postgresExists = sh(script: "docker ps -a --filter 'name=postgres-db' --format '{{.Names}}'", returnStdout: true).trim()
                        def postgresRunning = sh(script: "docker ps --filter 'name=postgres-db' --format '{{.Names}}'", returnStdout: true).trim()
                        def springRunning = sh(script: "docker ps --filter 'name=spring-app' --format '{{.Names}}'", returnStdout: true).trim()

                        if (postgresExists) {
                            if (!postgresRunning) {
                                echo "Le conteneur postgres-db existe mais est arrêté. Redémarrage..."
                                sh "docker start postgres-db"
                            } else {
                                echo "Le conteneur postgres-db est déjà actif."
                            }
                        } else {
                            echo "Le conteneur postgres-db n'existe pas. Lancement complet..."
                            sh "docker compose up -d"
                        }

                        if (!springRunning) {
                            echo "Le conteneur spring-app n'est pas en cours d'exécution. Lancement..."
                            sh "docker compose up -d spring-app"
                        } else {
                            echo "Le conteneur spring-app tourne déjà."
                        }
                    }
                }
//             steps {
//                 script{
//                     sh """
//                     docker compose down
//                     docker compose up -d
//                     """
//                 }
//             }
        }






    }
}
