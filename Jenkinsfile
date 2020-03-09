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

/*       stage('Checkout') {
         steps {
           checkout([$class: 'GitSCM', branches: [[name: '*//* master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/mlamlu/calc.git']]])
         }
      } */

/*       stage('Get info from POM') {
          steps {
            script {
                pom = readMavenPom file: 'pom.xml'
                groupId = pom.groupId
                artifactId = pom.artifactId
                packaging = pom.packaging
                version = pom.version
                filepath = "target/${artifactId}-${version}.jar"
                isSnapshot = version.endsWith("-SNAPSHOT")
            }
            echo groupId
            echo artifactId
            echo packaging
            echo version
            echo filepath
            echo "isSnapshot: ${isSnapshot}"
          }
      } */

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

/*
       stage("publish to nexus") {
             steps {
                nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: 'target/calc-0.0.2-SNAPSHOT-jar-with-dependencies.jar']], mavenCoordinate: [artifactId: 'calc', groupId: 'fr.mlamlu', packaging: 'jar', version: '1.0-RELEASE']]]
             }
       }
 */

/*       stage('Push RELEASE to Nexus') {
          steps {
            nexusPublisher nexusInstanceId: 'nexus_localhost', nexusRepositoryId: 'maven-releases', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', filePath: "${filepath}"]], mavenCoordinate: [artifactId: "${artifactId}", groupId: "${groupId}", packaging: "${packaging}", version: "${version}"]]]
          }
        } */
}


    post {
        always {
               junit 'target/surefire-reports/*.xml'

      /*       recordIssues enabledForFailure: true, tool: mavenConsole(), referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tools: [java(), javaDoc()], sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: checkStyle(pattern: 'target/checkstyle-result.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: cpd(pattern: 'target/cpd.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: pmdParser(pattern: 'target/pmd.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
            recordIssues enabledForFailure: true, tool: spotBugs(pattern: 'target/spotbugsXml.xml'), sourceCodeEncoding: 'UTF-8', referenceJobName: 'Plugins/warnings-ng-plugin/master'
 */        }
     }
}









/*     post {
            always {
                       junit '**//* surefire-reports *//*.xml'
                    }
         } */
