package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KMONITEUR
*/
public class KMoniteur extends KObject {
private String NOM_MONITEUR;
private String PRENOM_MONITEUR;
private int ID_MONITEUR;
public KMoniteur() {
	super();
	keyFields="ID_MONITEUR";
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

public int getID_MONITEUR(){
	//return the value of ID_MONITEUR
	return this.ID_MONITEUR;
}

public void setNOM_MONITEUR(String aNOM_MONITEUR){
	//set the value of NOM_MONITEUR
	this.NOM_MONITEUR=aNOM_MONITEUR;
}
public void setPRENOM_MONITEUR(String aPRENOM_MONITEUR){
	//set the value of PRENOM_MONITEUR
	this.PRENOM_MONITEUR=aPRENOM_MONITEUR;
}
public void setID_MONITEUR(int aID_MONITEUR){
	//set the value of ID_MONITEUR
	this.ID_MONITEUR=aID_MONITEUR;
}
@Override
public String toString() {
	return " [NOM_MONITEUR] = " + NOM_MONITEUR+" [PRENOM_MONITEUR] = " + PRENOM_MONITEUR+" [ID_MONITEUR] = " + ID_MONITEUR;
}
}