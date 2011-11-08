SCRIPTAUTOECOLEV3.sql : 
script génré par winDesign' (sans changer le nom des clés primaires et étrangères).

SCRIPTAUTOECOLEV3-modifie.sql : 
script que j'ai modifié manuellement (j'ai renommé les clés primaires et étrangères). 
L'auto incrementest absent. script fonctgionel.

ScriptAvecAutoIncrement.sql : 
script généré par MySQL avec autoIncrement (à utiliser en premier).

ScriptRemplissageBDD.sql : 
Script contenant les requêtes d'insertion (à utiliser après avoir importer ScriptAvecAutoIncrement.sql).

J'ai testé respectivement criptAvecAutoIncrement.sql puis ScriptRemplissageBDD.sql. Aucune erreur ne s'est produite.
Le fichier ScriptRemplissageBDD.sql est à remplir au fur et à mesure de l'avancement du projet.


Cette version du 08_11_11 est donc celle à reprendre en cas de modification.
