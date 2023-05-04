/* Requires the Docker Pipeline plugin */
pipeline {   
	agent any
	tools {
		maven 'Jenkins-Maven'
	}
    stages {
        stage('build') {
            steps {
            	echo 'running maven build'
                bat 'mvn --version'
                bat 'mvn test'
            }
        }
    }
    
	
	post {
	
		always {
		 
		  junit '**/TEST*.xml'
		    
		}
		failure {
		     
		    emailext to: 'support@ezxinc.com',
		        subject: '$DEFAULT_SUBJECT',
		        body: '$DEFAULT_CONTENT'
		    
		}
		changed {
		        // TODO: try to refactor the email using the def emailsettings = []
		            echo 'Things were different before...'
		        emailext to: 'support@ezxinc.com',
		            subject: '$DEFAULT_SUBJECT',
		            body: '$DEFAULT_CONTENT'                            
		}
		 
	}    
}

