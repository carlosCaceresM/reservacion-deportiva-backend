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
        PROJECT_PATH_BACK = 'microservicio'
    }

    tools {
        jdk 'JDK8_Centos'
    }

    stages{
        stage('Checkout') {
            steps {
                echo '------------>Checkout desde Git Microservicio<------------'
                gitCheckout(
                    urlProject:'https://github.com/carlosCaceresM/reservacion-deportiva-backend.git',
                    branchProject: '${BRANCH_NAME}',
                )

                dir("${PROJECT_PATH_BACK}"){
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean'
                }
            }
        }

            stage('Compilacion y Test Unitarios'){
                parallel {
                    stage('Test- Backend'){
                        steps {
                            echo '------------>Test Backend<------------'
                            dir("${PROJECT_PATH_BACK}"){
                                sh './gradlew --stacktrace test'
                            }
                        }
                        post{
                            always {
                                junit '**/build/test-results/test/*.xml'
                            }
                        }
                    }
                }
            }

		stage('Static Code Analysis') {
			steps{
				sonarqubeMasQualityGates(
				sonarKey:'',
				sonarName:'',
				sonarPathProperties:'./sonar-project.properties')
			}
		}

        stage('Build'){
            parallel {
                stage('construcción Backend'){
                    steps{
                        echo "------------>Compilación backend<------------"
                        dir("${PROJECT_PATH_BACK}"){
                            sh './gradlew build -x test'
                        }
                    }
                }
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
