pipeline {
    agent any
    stages{
        stage('SCM') {
            steps {
            	git branch: "${BRANCH_NAME}",
                url: "https://github.com/hsik0225/notion-adapter.git"
            }
        }

        stage('Test with coverage') {
            steps {
				sh './gradlew testCoverage'
            }
        }

        stage('SonarCloud PR analysis') {
            steps {
                withSonarQubeEnv('SonarCloud') {
					sh "./gradlew --info sonarqube \
						-Dsonar.projectKey=hsik0225_notion-adapter \
						-Dsonar.organization=seed \
						-Dsonar.pullrequest.provider=GitHub \
						-Dsonar.pullrequest.github.repository=hsik0225/notion-adapter \
						-Dsonar.pullrequest.key=${CHANGE_ID} \
						-Dsonar.pullrequest.base=dev \
						-Dsonar.pullrequest.branch=${BRANCH_NAME}"
                }
            }
        }
    }
}
