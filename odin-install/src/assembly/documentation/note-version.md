### Notes de version

***

##### Version ${project.parent.version} - Publiée le ${timestamp}
###### Nouveautés
- YouTrack [ODIN-7](http://vms-forge-test.intra.igr.fr:8083/issue/ODIN-7) : Faire un rollback quand l'import n'a pas fonctionné
- YouTrack [ODIN-17](http://vms-forge-test.intra.igr.fr:8083/issue/ODIN-17) : Séparation de l'étape de recherche des variants une fois l'import correct

###### Corrections
- YouTrack [ODIN-5](http://vms-forge-test.intra.igr.fr:8083/issue/ODIN-5) : Le rafraîchissement des colonnes correspondant aux annotations ne se fait pas tout le temps sur l'écran Résultat
- YouTrack [ODIN-25](http://vms-forge-test.intra.igr.fr:8083/issue/ODIN-25) : \[FontAwesome] Global icon library is deprecated

***

##### Version 1.0.0-alpha-2 - Publiée le 26/07/2019
###### Nouveautés
- ${add} Information de la non compatibilité avec Internet Explorer
- ${add} Lors de la création d'une annotation, on peut maintenant la qualifier soit de type **Annotation** soit de type **Génotype**.
- ${add} Possibilité de filtrer sur les résultats
- ${improvement} Ajout d'exemples pour la création d'une annotation et d'un groupe d'annotations
- ${improvement} Messages d'erreur pour l'agent d'importation
- ${modification} Utilisation du moteur de recherche Elasticsearch pour l'affichage et le filtrage des résultats
- ${modification} Fichier de propriétés de l'agent d'importation : les propriétés `odin.agent.importation.template.url.api` et `odin.agent.importation.result.url.api` sont remplacées par l'adresse du service variant : `odin.agent.importation.service.variant.url.api`

***

##### Version 1.0.0-alpha-1 - Publiée le 08/07/2019
###### Nouveauté
- ${add} Système d'importation des fichiers **.vcf**
