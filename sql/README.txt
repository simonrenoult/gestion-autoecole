SCRIPTAUTOECOLEV3.sql : 
script g�nr� par winDesign' (sans changer le nom des cl�s primaires et �trang�res).

SCRIPTAUTOECOLEV3-modifie.sql : 
script que j'ai modifi� manuellement (j'ai renomm� les cl�s primaires et �trang�res). 
L'auto incrementest absent. script fonctgionel.

ScriptAvecAutoIncrement.sql : 
script g�n�r� par MySQL avec autoIncrement (� utiliser en premier).

ScriptRemplissageBDD.sql : 
Script contenant les requ�tes d'insertion (� utiliser apr�s avoir importer ScriptAvecAutoIncrement.sql).

J'ai test� respectivement criptAvecAutoIncrement.sql puis ScriptRemplissageBDD.sql. Aucune erreur ne s'est produite.
Le fichier ScriptRemplissageBDD.sql est � remplir au fur et � mesure de l'avancement du projet.


Cette version du 08_11_11 est donc celle � reprendre en cas de modification.
