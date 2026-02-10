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
                 bat './mvnw test'
                 junit 'target/surefire-reports/*.xml'
                 cucumber   reportTitle: 'My report',
                           fileIncludePattern: 'target/example-report.json'

           }
        }

    }
}