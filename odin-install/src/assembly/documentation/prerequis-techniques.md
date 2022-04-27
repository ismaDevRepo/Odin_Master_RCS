### Prérequis pour installer ${application.name} version ${project.parent.version}

***

1. **Apache HTTPD** pour déployer la partie web de l'application.
    - Le front est une application Angular.
    - Version recommandée : >= **2.4**
 
2. **Apache Tomcat** pour déployer les services Java.
    - Les services sont des applications Spring Boot.
    - Version recommandée : >= **9.0.0**
    - Configuration spécifique : 
        
        1. Augmenter la taille maximale de téléchargement pour le Manager (utile pour le déploiement des .war) :  
            Editer le fichier `<TOMCAT_HOME>/webapps/manager/WEB-INF/web.xml`
            
                <multipart-config>
                   <max-file-size>52428800</max-file-size>
                   <max-request-size>52428800</max-request-size>
                   <file-size-threshold>0</file-size-threshold>
                </multipart-config>
            
            Modifier la valeur `max-file-size` & `max-request-size` par **104857600** (100 MB)
    
3. **Oracle Java** nécessaire pour les services déployés sur le Tomcat
    - Version recommandée : >= **12.0.0**

4. **Une base de données** pour stocker les données de l'application.
    - La base de données utilisée est une base **MySQL**.
    - Version recommandée : >= **8.0.0**
    - Configuration spécifique : 
    
        1. [Pour comparer le nom des tables sans prendre en compte les majuscules](https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html#sysvar_lower_case_table_names) :

                lower_case_table_names = 1
            
        2. Pour avoir les droits de créer un **trigger**.  
            Se connecter sur la base MySQL et passer la requête suivante :
            
                SET GLOBAL log_bin_trust_function_creators = 1;

5. **Elasticsearch** pour indexer les résultats de variant et effectuer des recherches rapides
    - Version recommandée : >= **7.2.0**

6. **Logstash** pour transférer les données de la base de données de variants vers l'index Elasticsearch
    - Version recommandée : >= **7.2.0**
