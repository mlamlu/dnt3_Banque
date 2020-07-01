
/* *** Détail du projet, récupéré dans le pipeline en lisant le pom.xml *** */
def groupId = ''
def artefactId = ''
def filePath = ''
def packaging = ''
def version = ''
def verCode = UUID.randomUUID().toString()

pipeline {
    agent any

    tools {

    maven "maven_3_6_3"
    }


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


        stage('clean') {
            steps {
            	sh 'mvn clean'
            }
        }

        stage('Test') {
            steps {
            	sh 'mvn test'
            }
        }

        stage('compile') {
            steps {
            	sh 'mvn compile'
            }
        }
        stage('Analyse') {
            steps {
            	sh 'mvn checkstyle:checkstyle'
                sh 'mvn spotbugs:spotbugs'
                sh 'mvn pmd:cpd'
                sh 'mvn pmd:pmd'
            }
        }
        stage('JavaDoc') {
            steps {
            	sh 'mvn javadoc:javadoc'

            }
        }

        stage('Publish') {
            steps {
                archiveArtifacts 'target/*.jar'

            }

        }


        stage("publish to nexus") {
             steps {
                 script {
                                        pom = readMavenPom file: 'pom.xml'
                                        groupId = pom.groupId
                                        artifactId = pom.artifactId
                                        packaging = pom.packaging
                                        version = pom.version
                                        filepath = "target/${artifactId}-${version}.jar"
                  }
               nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${filepath}"]], mavenCoordinate: [artifactId: "${artifactId}", groupId: "${groupId}", packaging: "${packaging}", version: "${version}-${verCode}"]]]

             }
       }

       job('example') {
           publishers {
               archiveJavadoc {
                   javadocDir('target/site/apidocs')
               }
           }
       }




    }


   post {
      success {

         archiveArtifacts '**/target/*.jar'
         archiveArtifacts '**/target/*.xml'

      }
      always{
        recordIssues enabledForFailure : true, tools: [mavenConsole(), java(), javaDoc()]
        recordIssues enabledForFailure : true, tool: checkStyle()
        recordIssues enabledForFailure : true, tool: pmdParser(pattern: '**/target/pmd.xml')
        recordIssues enabledForFailure : true, tool: cpd(pattern: '**/target/cpd.xml')
        junit healthScaleFactor: 1, testResults: '**/target/surefire-reports/*.xml'
      }
   }

}









/*     post {
            always {
                       junit '**//* surefire-reports *//*.xml'
                    }
         } */
