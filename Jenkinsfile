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


            stage('Hello') {
                 steps {
                    echo 'Hello World'
                 }
              }
            stage('Build') {
                steps {
                    sh 'mvn clean compile'
                }
            }
            stage('test') {
                steps {
                    sh 'mvn test'
                }
            }


            stage('package') {
                steps {
                    sh 'mvn package'
                }
            }
            stage('Deploy') {
                steps {
                        archiveArtifacts 'target/*.jar'
                }
            }



/*             stage("publish to nexus") {

                steps {

                    nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'target/calculatrice-1.0-RELEASE-jar-with-dependencies.jar']], mavenCoordinate: [artifactId: 'calculatrice', groupId: 'fr.mlamlu', packaging: 'jar', version: '1.0-RELEASE']]]

                    }
            } */
    }


/*     post {
            always {
                       junit '**//* surefire-reports *//*.xml'
                    }
         } */
}