pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "traineeapi"
        DOCKERHUB_USER = "saxenaaayush9000"
        FULL_IMAGE = "saxenaaayush9000/traineeapi:latest"
        CONTAINER_NAME = "traineeapi-container"
    }

    stages {

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
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
                        echo $PASS | docker login -u $USER --password-stdin
                    '''
                }
            }
        }

        stage('Tag Image') {
            steps {
                sh 'docker tag $IMAGE_NAME $FULL_IMAGE'
            }
        }

        stage('Push to DockerHub') {
            steps {
                sh 'docker push $FULL_IMAGE'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                    docker rm -f $CONTAINER_NAME || true
                    docker run -d -p 8085:8085 --name $CONTAINER_NAME $FULL_IMAGE
                '''
            }
        }
    }
}
