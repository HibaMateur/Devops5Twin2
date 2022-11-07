pipeline {
    agent any
   
    stages {
        stage('Checkout GIT'){
            steps {
                echo 'Pulling';
                 git branch : 'master',
                 url : 'https://github.com/HibaMateur/Devops5Twin2',
                 credentialsId:'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'
            }
        }
        stage('Maven version'){
            steps{
                echo "Mavin version ...";
                sh "mvn -version"
            }
        }
        stage('Clean Maven install'){
            steps {
                sh 'mvn clean install'

            }

        }
              stage('Compile Project'){
            steps {
                sh 'mvn compile  -DskipTests'
            }
        }
        stage('MVN SONARQUBE'){
                steps {
                    sh """mvn sonar:sonar \
                          -Dsonar.projectKey=hibamateur \
                          -Dsonar.host.url=http://192.168.1.18:9000 \
                          -Dsonar.login=317ea18bfa56a61425a506fecf0b58322d240b13


                    """
                }

            }
        stage('Nexus') {
                steps {
                    sh 'mvn deploy -DskipTests'
      }
    }
        stage('Junit/Mockito'){
            steps{
                sh "mvn test"
            }
        }
        stage("Building Docker Image") {
                steps{
                    sh 'docker build -t hibamateur/spring .'
                }
        }
        stage("Login to DockerHub") {
                steps{

                    sh 'docker login -u hibamateur -p HibaMateur123'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push hibamateur/spring'
                }
        }
        stage("Docker-compose") {
                steps{
                    sh 'docker-compose up -d'
                }
        }
    }
}