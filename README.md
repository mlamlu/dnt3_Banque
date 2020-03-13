# calc

Jenkins file avec test et publication dans NEXUS
Le jenkins file génère un uuid inscrit dans le jar publié sur nexus pour éviter les erreurs de double publication





        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "localhost:8081"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "maven-snapshots"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "nexus_localhost"
        
        
Par Lucien LAMARQUE, Jeremy Gilger, Clement TRICART 