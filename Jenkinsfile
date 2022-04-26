@Library('ceiba-jenkins-library@master') _
pipeline{

    agent {
        label 'Slave_Induccion'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    environment {
        NAME_DEV_DOCKER_COMPOSE = 'dev.docker-compose.yml'
        NAME_BASE_DOCKER_COMPOSE = 'base.docker-compose.yml'
    }

    tools {
        jdk 'JDK8_Centos'
    }

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                checkout scm
            }
        }

        stage('Compilacion y Test Unitarios'){

            steps{

                echo "------------>prueba<------------"
                 sh 'sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose'

                echo "------------>Clean Tests<------------"

                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle clean'

                echo "------------>compile & Unit Tests<------------"

                sh 'chmod +x ./microservicio/gradlew'
                sh './microservicio/gradlew --b ./microservicio/build.gradle test'
            }

        }

		stage('Static Code Analysis') {
			steps{

                echo '------------>Análisis de código estático<------------'

				sonarqubeMasQualityGatesP(
                    sonarKey:'co.com.ceiba:adn:reservacion-deportiva-backend.carlos.caceres',
                    sonarName:'''"CeibaADN-ReservacionDeportivaBackend(carlos.caceres)"''',
                    sonarPathProperties:'./sonar-project.properties'
                )
			}
		}

        stage('Build DEV') {
            steps {
                sh "docker-compose -f ./${NAME_DEV_DOCKER_COMPOSE} build"
            }
        }

        stage('Run Database') {
            steps {
                sh "docker-compose -f ./${NAME_BASE_DOCKER_COMPOSE} up -d"
            }
        }

        stage('Deploy DEV') {
            steps {
                sh "docker-compose -f ./${NAME_DEV_DOCKER_COMPOSE} up -d"
            }
        }
    }
    post {
        failure {
            mail(
                to: 'carlos.caceres@ceiba.com.co',
                body:"Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/nPlease go to ${env.BUILD_URL} and verify the build",
                subject: "ERROR CI: ${env.JOB_NAME}"
            )
            updateGitlabCommitStatus name: 'IC Jenkins', state: 'failed'
        }
        success {
            updateGitlabCommitStatus name: 'IC Jenkins', state: 'success'
        }
    }
}