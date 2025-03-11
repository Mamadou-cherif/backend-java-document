pipeline {
    agent any

    tools{
        maven 'M3'
    }

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
    }
}
