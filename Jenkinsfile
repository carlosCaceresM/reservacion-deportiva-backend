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

        AMBIENTE_DEV = 'dev'
        NOMBRE_IMAGEN_DOKER = 'reservacion-deportiva-backend'
        NOMBRE_DB_DOKER = 'reservacion-deportiva-db'
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
                echo "------------>IP Servidor<------------"
                sh 'hostname -I'

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
                sh "docker build -t ${NOMBRE_IMAGEN_DOKER}-${AMBIENTE_DEV} . --build-arg AMBIENTE=${AMBIENTE_DEV}"
            }
        }

        stage('Run Database') {
            steps {
                sh "docker stop ${NOMBRE_DB_DOKER}"
                sh "docker rm ${NOMBRE_DB_DOKER}"
                sh "docker run --rm -d --name ${NOMBRE_DB_DOKER} --network network_reservacion_deportiva -p 3306:3306 -e MYSQL_ROOT_PASSWORD=ceiba mysql:8.0"
            }
        }

        stage('Deploy DEV') {
            steps {
                 sh "docker stop ${NOMBRE_IMAGEN_DOKER}-${AMBIENTE_DEV}"
                 sh "docker rm ${NOMBRE_IMAGEN_DOKER}-${AMBIENTE_DEV}"
                sh "docker run --rm -d --name ${NOMBRE_IMAGEN_DOKER}-${AMBIENTE_DEV} --network network_reservacion_deportiva -p 8081:8080 ${NOMBRE_IMAGEN_DOKER}-${AMBIENTE_DEV}"
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