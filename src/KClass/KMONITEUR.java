package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KMONITEUR
*/
public class KMONITEUR extends KObject {
private String NOM_MONITEUR;
private String PRENOM_MONITEUR;
public KMONITEUR() {
	super();
	keyFields="id";
	tableName="MONITEUR";
	//

}
public String getNOM_MONITEUR(){
	//return the value of NOM_MONITEUR
	return this.NOM_MONITEUR;
}

public String getPRENOM_MONITEUR(){
	//return the value of PRENOM_MONITEUR
	return this.PRENOM_MONITEUR;
}

public void setNOM_MONITEUR(String aNOM_MONITEUR){
	//set the value of NOM_MONITEUR
	this.NOM_MONITEUR=aNOM_MONITEUR;
}
public void setPRENOM_MONITEUR(String aPRENOM_MONITEUR){
	//set the value of PRENOM_MONITEUR
	this.PRENOM_MONITEUR=aPRENOM_MONITEUR;
}
@Override
public String toString() {
	return " [NOM_MONITEUR] = " + NOM_MONITEUR+" [PRENOM_MONITEUR] = " + PRENOM_MONITEUR;
}
}