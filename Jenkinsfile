pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK 21'
    }

    environment {
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub_Trung'
        DOCKERHUB_REPO = 'viettrung21/sep2-w1-inclass-shoppingcart'
        DOCKER_IMAGE_TAG = 'latest_v1'
        // Set PATH explicitly for Jenkins
        PATH = "/usr/local/bin:$PATH"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch:"main",url:'https://github.com/viettrung2103/sep2-w1-inclass-shoppingcart-v3.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Docker Login') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID,
                                                     usernameVariable: 'DOCKERHUB_USER',
                                                     passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh '''
                            echo "$DOCKERHUB_PASSWORD" | docker login -u "$DOCKERHUB_USER" --password-stdin
                        '''
                    }
                }
            }
        }
        stage('Build Docker Image') {
             steps {
                 script {
                     sh """
                         docker buildx use mybuilder || docker buildx create --use --name mybuilder
                         docker buildx build --platform=linux/amd64 -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} --load .
                     """
                 }
             }
         }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    sh "docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                }
            }
        }
   }
}