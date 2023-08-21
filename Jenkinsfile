/* Requires the Docker Pipeline plugin  */
pipeline {   
	agent any
	tools {
		maven 'Jenkins-Maven'
	}
	
	parameters {
	  booleanParam description: 'my description', name: 'RUN_RELEASE'
	}
	
    stages {
        stage('build') {
            steps {
            	echo 'running maven build'
				script {
					def version = bat script: '@mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
					echo 'built version=' + version
					def commitMessage = "Deploying ${version} to QA"
					withCredentials([gitUsernamePassword(credentialsId: 'e3e154ed-3807-4bf1-aa5b-d0fbad7b0e86')]) {
						 bat 'git config --global user.name shalomshachne '
						 bat 'git config --global user.email shalomshachne@gmail.com" '
                         bat "git tag -a ${version} -m  \"${commitMessage}\" "
                         bat "git push --tags"
					}

				}
            }
        }
       stage('ssh-git-test') {
            steps {
            	echo 'running maven build with ssh'
				script {
					def version = bat script: '@mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
					version = version + "-2"
					echo 'built version (ssh)=' + version
					def commitMessage = "Deploying ${version} to QA"
					withCredentials([sshUserPrivateKey(credentialsId: 'git-push-access', keyFileVariable: 'key')]) {
						//bat 'git config --global user.name build@ezxinc.com '
						bat 'git config --global user.email build@ezxinc.com '
						bat 'set GIT_SSH_COMMAND = "ssh -i $key"'
                       	bat "git tag -a ${version} -m  \"${commitMessage}\" "
                        bat "git push --tags"
					}

				}
            }
        }		
        stage('release') {
        	when {
        	    expression {
        	        return params.RUN_RELEASE
        	        
        	    }				        	    
        	}
			steps {				    
			   echo "running release= " + params.RUN_RELEASE
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

