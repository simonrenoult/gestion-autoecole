package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCHAMPS
*/
public class KCHAMPS extends KObject {
private String LIBELLE_ETAT_CHAMPS;
public KCHAMPS() {
	super();
	keyFields="id";
	tableName="CHAMPS";
	//

}
public String getLIBELLE_ETAT_CHAMPS(){
	//return the value of LIBELLE_ETAT_CHAMPS
	return this.LIBELLE_ETAT_CHAMPS;
}

public void setLIBELLE_ETAT_CHAMPS(String aLIBELLE_ETAT_CHAMPS){
	//set the value of LIBELLE_ETAT_CHAMPS
	this.LIBELLE_ETAT_CHAMPS=aLIBELLE_ETAT_CHAMPS;
}
@Override
public String toString() {
	return " [LIBELLE_ETAT_CHAMPS] = " + LIBELLE_ETAT_CHAMPS;
}
}