### Mise à jour de ${application.name} version ${project.parent.version}
![${application.name} version ${project.version}](https://img.shields.io/badge/${application.name}-${project.version}-brightgreen.svg "${application.name} version ${project.version}")
![Java version ${java.version}](https://img.shields.io/badge/Java->=_${java.version}-blue.svg "Java version ${java.version}")
![Apache Tomcat version 9](https://img.shields.io/badge/Apache_Tomcat->=_9-yellow.svg "Apache Tomcat version 9")

### Phase alpha
Pendant les versions alphas, les bases de données sont supprimées car le modèle de données évolue en permanence

#### Prérequis
[Télécharger](${project.url}/releases/download/v${project.parent.version}/${project.artifactId}-${project.parent.version}.zip) le dossier d'installation.

Ce dossier contient :

- Un dossier **agents** qui contient un agent
- Un dossier **logstash** qui contient différents éléments pour lancer Logstash
- Un dossier **README** qui reprend le site de documentation
- Un dossier **services** qui contient tous les services sous forme de .war
- Un dossier **web** contenant le site web à déployer

***

#### Mise à jour de la base de données
1. Se connecter à la base de données de variants

2. Supprimer le schéma `odin_variant`

***

#### Mise à jour de l'application
##### Depuis le manager de Tomcat
1. Se connecter sur le manager du Tomcat

2. Retirer les anciens services (arrêter puis retirer)

3. Mettre à jour le fichier de configuration de l'agent `odin-variant.properties` avec la nouvelle version.  
Ajouter les nouvelles propriétés pour Elasticsearch en remplaçant par les bonnes valeurs :

        # ===============================
        # Elasticsearch
        # ===============================
        elasticsearch.hostname = <HOSTNAME>
        elasticsearch.port = <PORT>
        #elasticsearch.username = <USERNAME>
        #elasticsearch.password = <PASSWORD>
        
        # Par défaut
        # elasticsearch.index = odin*

4. Déployer les 2 nouveaux .war (situés dans le dossier `/services`), sans les renommer :

    a. **${odin-service-interpretation.webapp.packaging.finalName}.war**  
    b. **${odin-service-variant.webapp.packaging.finalName}.war**  

5. Vérifier que les deux applications sont fonctionnelles

***

#### Mise à jour de la partie web
1. Supprimer l'ancienne version du site : supprimer tous les fichiers sauf le fichier `.htaccess`.

2. Depuis le dossier `/web` du package d'installation, copier les fichiers du dossier `/angular` et les coller sur le serveur Apache HTTPD où vous avez choisi de déposer le site

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

#### Mise à jour de l'agent d'importation
1. L'agent est disponible dans le dossier `/agents` du package d'installation.

2. Arrêter l'ancienne version

3. Mettre à jour le fichier de configuration de l'agent `odin-agent-importation.properties` avec la nouvelle version.  

    - Les propriétés `odin.agent.importation.template.url.api` et `odin.agent.importation.result.url.api` sont remplacées par l'adresse du service variant : `odin.agent.importation.service.variant.url.api`

4. Remplacer l'agent avec la nouvelle version

5. Redémarrer l'agent (penser à créer votre template d'importation avant de démarrer l'agent) 
