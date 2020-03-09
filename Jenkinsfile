pipeline {
    agent {

        label "master"

    }

    tools {

        // Note: this should match with the tool name configured in your jenkins instance (JENKINS_URL/configureTools/)

        maven "Maven 3.6.1"

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


        stage("publish to nexus") {
            steps {
                script {
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                    pom = readMavenPom file: "pom.xml";
                    // Find built artifact under target folder
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    // Print some info from the artifact found
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extract the path from the File found
                    artifactPath = filesByGlob[0].path;
                    // Assign to a boolean response verifying If the artifact name exists
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                // Artifact generated such as .jar, .ear and .war files.
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                // Lets upload the pom.xml file for additional information for Transitive dependencies
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }

                }

            }

        }
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
