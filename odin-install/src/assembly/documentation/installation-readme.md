### Installation de ${application.name} version ${project.parent.version}
![${application.name} version ${project.version}](https://img.shields.io/badge/${application.name}-${project.version}-brightgreen.svg "${application.name} version ${project.version}")
![Java version ${java.version}](https://img.shields.io/badge/Java->=_${java.version}-blue.svg "Java version ${java.version}")
![Apache Tomcat version 9](https://img.shields.io/badge/Apache_Tomcat->=_9-yellow.svg "Apache Tomcat version 9")

#### Prérequis
1. Voir les [prérequis](./prerequis-techniques.html)

2. [Télécharger](${project.url}/releases/download/v${project.parent.version}/${project.artifactId}-${project.parent.version}.zip) le dossier d'installation.

    Ce dossier contient :
    
    - Un dossier **agents** qui contient un agent
    - Un dossier **logstash** qui contient différents éléments pour lancer Logstash
    - Un dossier **README** qui reprend le site de documentation
    - Un dossier **services** qui contient tous les services sous forme de .war
    - Un dossier **web** contenant le site web à déployer

***

#### Installation de la partie Elasticsearch
Vérifier qu'Elasticsearch est opérationnel

***

#### Installation de la partie Logstash
1. Depuis le dossier `/logstash` du package d'installation, copier le contenu pour le déposer où l'instance Logstash est installée

2. Remplacer les valeurs du fichier de configuration de Logstash : `odin-logstash.conf`

3. Tester le fichier de configuration avec la commande suivante :

        cd logstash-x.x.x
        bin/logstash -f /path/absolute/to/odin-logstash.conf --config.test_and_exit
        
    Si OK :
    
    ![Vérification Logstash](images/logstash-verification.png "Vérification Logstash")

4. Lancer la commande suivante pour démarrer Logstash avec le fichier de configuration.

        bin/logstash -f /path/absolute/to/odin-logstash.conf
        
    > Par défaut, Logstash se lancera toutes les 5 minutes.  
    > Cette valeur peut être modifiée dans le fichier de configuration `odin-logstash.conf` : input > jdbc > schedule 

***

#### Installation des services   
1. Depuis le dossier `/services` du package d'installation, copier les fichiers suivants et les coller dans le répertoire Tomcat : `<TOMCAT PATH>/conf/Catalina/localhost/`
    - `/services/odin.properties` : propriétés globales pour les services
    - `/services/odin-interpretation.properties` : propriétés spécifiques au service comme l'accès à la base de données
    - `/services/odin-variant.properties` : propriétés spécifiques au service comme l'accès à la base de données

2. Remplacer par les bonnes valeurs dans les trois fichiers de propriétés.
    
##### Depuis le manager de Tomcat
1. Se connecter sur le manager du Tomcat

2. Déployer les 2 nouveaux .war (situés dans le dossier `/services`), sans les renommer :

    a. **${odin-service-interpretation.webapp.packaging.finalName}.war**  
    b. **${odin-service-variant.webapp.packaging.finalName}.war**  

3. Vérifier que les deux applications sont fonctionnelles

***

#### Installation de la partie web
1. Depuis le dossier `/web` du package d'installation, copier le fichier `.htaccess` pour mettre en place la réécriture d'URL pour le site web Angular.  
Puis le déposer dans le dossier sur le serveur Apache où la partie front sera localisée.

2. Copier les fichiers du dossier `/angular` et les coller sur le serveur Apache HTTPD où vous avez choisi de déposer le site

***

#### Installation de l'agent d'importation (facultatif)
L'agent d'importation permet d'importer des fichiers au [format](https://samtools.github.io/hts-specs/VCFv4.3.pdf) **.vcf** pour les intégrer dans la base de données de variants.

Il est autonome et peut être lancé directement en ligne de commande.  
C'est un service Spring Boot en Java qui utilise un Apache Tomcat embarqué.

L'agent est disponible dans le dossier `/agents` du package d'installation.

##### Pour lancer l'agent

- avec l'emplacement du fichier de configuration
 

        java -jar "/path/to/jar/${odin-agent-importation.webapp.packaging.finalName}.jar"
            --spring.config.location="/path/to/config/file/${odin-agent-importation.webapp.name}.properties"
    
- avec les arguments en ligne de commande 
    
        java -jar "/path/to/jar/${odin-agent-importation.webapp.packaging.finalName}.jar"
            --odin.agent.importation.service.variant.url.api="<URL>"
            --odin.agent.importation.template.id="<ID>"
            --odin.agent.importation.path.file.done="<PATH>"
            --odin.agent.importation.path.file.out="<PATH>"
            --odin.agent.importation.path.file.error="<PATH>"

##### Exemple de fichier de configuration disponible dans le dossier `/agents`

    ##### ODIN (agent d'importation) properties file #####
    ##### https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html
    
    # ===============================
    # Serveur Tomcat Embedded
    # ===============================
    # Par défaut
    # server.port = 9000
    
    # ===============================
    # ODIN Service Variant
    # ===============================
    # URL vers l'API le service Variant
    # Exemple : http://localhost:8080/odin-service-variant/api
    odin.agent.importation.service.variant.url.api = <URL>
    
    # ID du template qui va servir à parser le fichier .vcf
    # Exemple : 1
    odin.agent.importation.template.id = <ID>
    
    # ===============================
    # Localisation des fichiers à importer
    # ===============================
    # Chemin où seront déposés les fichiers traités
    # Exemple : file:///C:/Users/j_diemer/Documents/Projets/ODIN/importation/done
    odin.agent.importation.path.file.done = <PATH>
    
    # Chemin vers la localisation des fichiers à importer
    # Exemple : file:///C:/Users/j_diemer/Documents/Projets/ODIN/importation/out
    odin.agent.importation.path.file.out = <PATH>
    
    # Chemin où seront déposés les fichiers en erreur
    # Exemple : file:///C:/Users/j_diemer/Documents/Projets/ODIN/importation/error
    odin.agent.importation.path.file.error = <PATH>

***

#### Paramétrage du Hub pour l'authentification OAuth2
- Ajout de l'accès pour les services

        INSERT INTO hub.oauth_client_details (CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE)
        VALUES ('odin', null, '{bcrypt}$2a$10$ifzuYIL2Q2NQoHYT2r2fj.2evj0PuSPTAgFwWN89Pew6zF/xJJ4Ei', 'read,write,foo,bar', 'password,authorization_code,refresh_token,client_credentials', '', null, null, null, null, '');
        
- Ajout de l'accès pour le front
        
        INSERT INTO hub.oauth_client_details (CLIENT_ID, RESOURCE_IDS, CLIENT_SECRET, SCOPE, AUTHORIZED_GRANT_TYPES, WEB_SERVER_REDIRECT_URI, AUTHORITIES, ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY, ADDITIONAL_INFORMATION, AUTOAPPROVE)
        VALUES ('odin-front', null, '', 'read,write,foo,bar', 'implicit', 'http://localhost:4201', null, null, null, null, 'true');
    
