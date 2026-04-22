pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        IMAGE_NAME = "saxenaaayush9000/traineeapi"
        FULL_IMAGE = "saxenaaayush9000/traineeapi:latest"
        CONTAINER_NAME = "spring-app"
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
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
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

        stage('Deploy') {
            steps {
                sh '''
                    echo "Stopping old container (if exists)..."
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true

                    echo "Pulling latest image..."
                    docker pull $FULL_IMAGE

                    echo "Running new container..."
                    docker run -d \
                      --name $CONTAINER_NAME \
                      -p 8085:8085 \
                      -e SPRING_DATASOURCE_URL=jdbc:postgresql://mappingdb.c1kqmqkg80ri.eu-north-1.rds.amazonaws.com:5432/mappingdb \
                      -e SPRING_DATASOURCE_USERNAME=postgres \
                      -e SPRING_DATASOURCE_PASSWORD=1234567890 \
                      -e SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver \
                      $FULL_IMAGE
                '''
            }
        }
    }
}
