pipeline {
agent any

    environment {

        // This can be nexus3 or nexus2

        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "localhost:8081"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "maven-snapshots"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "nexus_localhost"
    }
        stages {


        stage('Package') {
            steps {
            	sh 'mvn clean'
                sh 'mvn package'
            }
        }
        stage('Analyse') {
            steps {
            	sh 'mvn checkstyle:checkstyle'
                sh 'mvn spotbugs:spotbugs'
                sh 'mvn pmd:pmd'
            }
        }

        stage('Publish') {
            steps {
                archiveArtifacts 'target/*.jar'
            }
        }
    }

    post {
        always {
               junit 'target/surefire-reports/*.xml'

            recordIssues enabledForFailure: true, tool: mavenConsole(), referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tools: [java(), javaDoc()], sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: checkStyle(pattern: 'target/checkstyle-result.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: cpd(pattern: 'target/cpd.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: pmdParser(pattern: 'target/pmd.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: spotBugs(pattern: 'target/spotbugsXml.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master' 
        }
     }
}


/*             stage("publish to nexus") {

                steps {

                    nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'target *//*.jar']], mavenCoordinate: [artifactId: 'calc', groupId: 'fr.mlamlu', packaging: 'jar', version: '1.0-RELEASE']]]

                    }
            } */






/*     post {
            always {
                       junit '**//* surefire-reports *//*.xml'
                    }
         } */
