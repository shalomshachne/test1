/* Requires the Docker Pipeline plugin */
pipeline {   
	agent any
	tools {
		maven 'Jenkins-Maven'
	}
    stages {
        stage('build') {
            steps {
                bat 'mvn --version'
            }
        }
    }
}

