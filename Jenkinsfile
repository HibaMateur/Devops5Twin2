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
                              -Dsonar.login=352949f338e0fc45b7ea61f29716781f72ae060b
                    """
                }

            }
    }
}