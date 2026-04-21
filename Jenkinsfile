pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "traineeapi"
        DOCKERHUB_USER = "axenaaayush9000"
        CONTAINER_NAME = "traineeapi-container"
    }

    stages {

        stage('Build JAR') {
            steps {
                dir('Jenkins_Docker') {
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('Jenkins_Docker') {
                    sh 'docker build -t traineeapi .'
                }
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-cred',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    sh '''
                        docker logout || true
                        echo $PASS | docker login -u $USER --password-stdin
                    '''
                }
            }
        }

        stage('Tag Image') {
            steps {
                sh 'docker tag traineeapi $DOCKERHUB_USER/traineeapi:latest'
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh 'docker push $DOCKERHUB_USER/traineeapi:latest'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                    docker rm -f traineeapi-container || true
                    docker run -d -p 8085:8085 --name traineeapi-container traineeapi
                '''
            }
        }
    }
}
