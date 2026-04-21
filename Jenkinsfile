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

        stage('Login DockerHub') {
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

        stage('Push Image') {
            steps {
                sh 'docker push $FULL_IMAGE'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh '''
                    docker compose down -v || true
                    docker compose up -d --build
                '''
            }
        }
    }
}
