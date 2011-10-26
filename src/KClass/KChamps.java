package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCHAMPS
*/
public class KChamps extends KObject {
private String LIBELLE_ETAT_CHAMPS;
private int ID_ETAT_CHAMPS;
public KChamps() {
	super();
	keyFields="ID_ETAT_CHAMPS";
	tableName="CHAMPS";
	//

}
public String getLIBELLE_ETAT_CHAMPS(){
	//return the value of LIBELLE_ETAT_CHAMPS
	return this.LIBELLE_ETAT_CHAMPS;
}

public int getID_ETAT_CHAMPS(){
	//return the value of ID_ETAT_CHAMPS
	return this.ID_ETAT_CHAMPS;
}

public void setLIBELLE_ETAT_CHAMPS(String aLIBELLE_ETAT_CHAMPS){
	//set the value of LIBELLE_ETAT_CHAMPS
	this.LIBELLE_ETAT_CHAMPS=aLIBELLE_ETAT_CHAMPS;
}
public void setID_ETAT_CHAMPS(int aID_ETAT_CHAMPS){
	//set the value of ID_ETAT_CHAMPS
	this.ID_ETAT_CHAMPS=aID_ETAT_CHAMPS;
}
@Override
public String toString() {
	return " [LIBELLE_ETAT_CHAMPS] = " + LIBELLE_ETAT_CHAMPS+" [ID_ETAT_CHAMPS] = " + ID_ETAT_CHAMPS;
}
}