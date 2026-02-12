pipeline{
    agent any
    stages{
        /* stage('init'){
            steps {
                bat './mvnw clean'
            }
        } */

        stage('test'){
           steps {
                 bat 'mvn test'
                 junit 'target/surefire-reports/*.xml'
                 cucumber   reportTitle: 'My report',
                           fileIncludePattern: 'target/example-report.json'

                 recordCoverage(tools: [[parser: 'JACOCO']],
                         id: 'jacoco', name: 'JaCoCo Coverage',
                         sourceCodeRetention: 'EVERY_BUILD',
                         qualityGates: [
                                 [threshold: 80.0, metric: 'LINE', baseline: 'PROJECT', unstable: true],
                                 [threshold: 80.0, metric: 'BRANCH', baseline: 'PROJECT', unstable: true]])
           }
        }
        stage('Documentation'){
            steps{
                bat '''
                               if not exist doc mkdir doc
                               xcopy target\\site\\* doc\\ /E /I /Y
                               powershell Compress-Archive -Path doc\\* -DestinationPath doc.zip -Force
                               '''
                               archiveArtifacts 'doc.zip'
                publishHTML ([
                                                 allowMissing: false,
                                                 alwaysLinkToLastBuild: true,
                                                 keepAll: true,
                                                 reportDir: 'target/site/apidocs',
                                                 reportFiles: 'index.html',
                                                 reportName: 'Documentation'
                                                 ])

            }
        }
        stage('build'){
            steps{
                 bat 'mvn package'
                 archiveArtifacts 'target/*.jar'
            }
        }
        stage('deploy'){
            steps {
                 bat 'mvn deploy'
            }
            /* steps {
                bat 'docker-compose up --build -d'
            } */
           /*  post{
                            failure{
                                    mail(subject: "Build echec:",
                                            body:"Le build a réussi.",
                                            to: "rina.ra.1804@gmail.com"
                                            )
                            }
                            success{
                                    mail(subject: "Build réussi:",
                                                body:"Le build a réussi.",
                                                to: "rina.ra.1804@gmail.com"
                                                )
                            }
                        }  */
        }
        stage('slack'){
            steps{
                powershell """
                       \$body = @{
                           text = "deploy completed succesfully!"
                       } | ConvertTo-Json

                       Invoke-RestMethod -Uri "${slackUrl}" `
                                         -Method Post `
                                         -ContentType "application/json" `
                                         -Body \$body
"""
            }
        }
    }
}