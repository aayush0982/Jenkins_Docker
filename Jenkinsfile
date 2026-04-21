pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK17'
    }
    environment {
        IMAGE_NAME = "saxenaaatush9000/traineeapi"
        CONTAINER_NAME = "traineeapi-container"
        FULL_IMAGE = "saxenaaatush9000/traineeapi:latest"
    }
    stages {
        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }
        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-cred',
                    usernameVariable: 'USER',
                    passwordVariable: 'PASS'
                )]) {
                    bat '''
                        docker logout
                        docker login -u %USER% -p %PASS%
                    '''
                }
            }
        }
        stage('Tag Image') {
            steps {
                bat 'docker tag %IMAGE_NAME% %FULL_IMAGE%'
            }
        }
        stage('Push Image') {
            steps {
                bat 'docker push %FULL_IMAGE%'
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                bat '''
                    docker compose down -v
                    docker compose up -d --build
                '''
            }
        }
    }
}
