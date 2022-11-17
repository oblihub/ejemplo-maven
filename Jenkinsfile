pipeline {
    agent any
     environment {
        NEXUS_INSTANCE_ID = "nexus"
        NEXUS_REPOSITORY = "devops-usach-nexus"
    }
    stages {
        stage('compile') {
            steps {
                echo 'Source code compilation in progress.....'
                script {
                    if(isUnix()) {
                        echo 'Unix OS'
                        sh './mvnw clean compile -e'
                    } else {
                        echo 'Windows OS'
                        bat 'mvnw clean compile -e'
                    }
                }
                echo '.....Source code compilation completed'
            }
        }
        stage('test') {
            steps {
                echo 'Source code testing in progress.....'
                script {
                    if(isUnix()) {
                        echo 'Unix OS'
                        sh './mvnw clean test -e'
                    } else {
                        echo 'Windows OS'
                        bat 'mvnw clean test -e'
                    }
                }
                echo '.....Source code testing completed'
            }
        }
        stage('package') {
            steps {
                echo 'Source code packaging in progress.....'
                script {
                    if(isUnix()) {
                        echo 'Unix OS'
                        sh './mvnw clean package -e'
                    } else {
                        echo 'Windows OS'
                        bat 'mvnw clean package -e'
                    }
                }
                echo '.....Source code packaging completed'
            }
        }
        stage('sonar') {
            steps {
                echo 'Sonar scan in progress.....'
                withSonarQubeEnv(credentialsId: 'SonarQbToken2', installationName: 'SonarServer') {
                    script {
                        if(isUnix()) {
                            echo 'Unix OS'
                                sh './mvnw clean verify sonar:sonar \
                                     -Dsonar.projectKey=example-maven2'
                        } else {
                            echo 'Windows OS'
                                bat 'mvnw clean verify sonar:sonar \
                                    -Dsonar.projectKey=example-maven2'
                        }
                        echo '.....Sonar scan completed'
                    }
                }
            }
        }
        stage("uploadNexus") {
            steps {
                echo 'Uploading to nexus in progress.....'
                script {
                            nexusPublisher nexusInstanceId: 'nxs01', nexusRepositoryId: 'devops-usach-nexus', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${WORKSPACE}/build/DevOpsUsach2020-0.0.1.jar"]], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
                    }
                }
        }
        stage('download & test') {
            steps {
                script {
                    echo "Downloading artifact from nexus"
                    ls -ltr
                }
            }
        }
    }
 }
