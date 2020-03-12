
/* *** Détail du projet, récupéré dans le pipeline en lisant le pom.xml *** */
def groupId = ''
def artefactId = ''
def filePath = ''
def packaging = ''
def version = ''

pipeline {
    agent {

        label "master"

    }

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

/*
      stage('Checkout') {
         steps {
          withCredentials([usernameColonPassword(credentialsId: 'mlamlujenkins')]) {
           checkout([$class: 'GitSCM', branches: [[name: '*//*
master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/mlamlu/calc.git'],[credentialsId:'mlamlujenkins']]])
         }
         }
      }
 */


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


/*        stage("publish to nexus") {
             steps {
                nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'target/calc-0.0.2-SNAPSHOT-jar-with-dependencies.jar']], mavenCoordinate: [artifactId: 'calc', groupId: 'fr.mlamlu', packaging: 'jar', version: '1.1-RELEASE']]]
             }
       } */





        stage("publish to nexus") {
            steps {
                script {
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                                   pom = readMavenPom file: 'pom.xml'
                                   groupId = pom.groupId
                                   artifactId = pom.artifactId
                                   packaging = pom.packaging
                                   version = pom.version
                                   filepath = "target/${artifactId}-${version}.jar"
                                               echo groupId
                                               echo artifactId
                                               echo packaging
                                               echo version
                                               echo filepath

       nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${filepath}"]], mavenCoordinate: [artifactId: "${artifactId}", groupId: "${groupId}", packaging: "${packaging}", version: "${version}"]]]


                }

            }

        }
    }


   post {
      success {
         junit '**/target/surefire-reports/*.xml'
         archiveArtifacts '**/target/*.jar'
         archiveArtifacts '**/target/*.xml'
      }
/*       always{
 *//*         recordIssues enabledForFailure : true, tools: [mavenConsole(), java(), javaDoc()]
      //  recordIssues enabledForFailure : true, tools: checkStyle()
        recordIssues enabledForFailure : true, tools: pmdParser(pattern: '**//*  *//* target/pmd.xml')
        recordIssues enabledForFailure : true, tools: cpd(pattern: '**//*  *//* target/cpd.xml') *//*
      } */
   }
/*     post {
        always {
               junit 'target/surefire-reports *//*.xml'

                        recordIssues enabledForFailure: true, tools: [mavenConsole(), java(), javaDoc()]
                       recordIssues enabledForFailure: true, tools: checkStyle()
                       recordIssues enabledForFailure: true, tools: spotbugs()
                       recordIssues enabledForFailure: true, tools: cpd(pattern: '**//* target/cpd.xml')
                       recordIssues enabledForFailure: true, tools: pmdParser(pattern: '**//* target/pmd.xml')
       }
     } */
}









/*     post {
            always {
                       junit '**//* surefire-reports *//*.xml'
                    }
         } */
