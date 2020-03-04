def repo = "herokuapp"

pipeline {
    agent { label 'master'}
    environment {
    PATH = "/usr/local/bin:$PATH"
    LC_CTYPE = "en_US.UTF-8"
  }

  parameters {
                choice(choices: ["chrome", "firefox"], description: 'Which Browser to Run tests on', name: 'browser')
                string(defaultValue: "master", description: 'Which Branch to run', name: 'branch')
            }

    stages {
            stage ('Environment Info') {
                steps {
                    script {
                        sh 'hostname'
                        sh 'whoami'
                        sh 'java -version'
                    }
                }
            }
            stage('Checkout code') {
                steps {
                    deleteDir()
                    script {
                            currentBuild.displayName = repo + "-" + "${params.branch}" + "-" + "${params.browser}"
                        }
                    git url: "https://github.com/deckymcdoo7/herokuapp.git", branch: "${env.branch}"
                }
            }
            stage('Install Dependencies') {
                steps {
                    script {
                        sh 'mvn install -Dmaven.test.skip=true'
                    }
                }
            }

            stage('Run Tests') {
                steps {
                    script {
                        sh "mvn test -Dbrowser='${params.browser}'"
                    }
                }
            }
        }

        post {
            always {
                script{

             publishHTML (target: [
                            allowMissing: false,
                            alwaysLinkToLastBuild: false,
                            keepAll: true,
                            reportDir: "reports",
                            reportFiles: 'index.html',
                            reportName: "Report"
                ])
            }
        }
    }

}
