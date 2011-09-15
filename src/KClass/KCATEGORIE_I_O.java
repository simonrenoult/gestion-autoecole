package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCATEGORIE_I_O
*/
public class KCATEGORIE_I_O extends KObject {
private String LIBELLE_CATEGORIE;
public KCATEGORIE_I_O() {
	super();
	keyFields="id";
	tableName="CATEGORIE_I_O";
	//

}
public String getLIBELLE_CATEGORIE(){
	//return the value of LIBELLE_CATEGORIE
	return this.LIBELLE_CATEGORIE;
}

public void setLIBELLE_CATEGORIE(String aLIBELLE_CATEGORIE){
	//set the value of LIBELLE_CATEGORIE
	this.LIBELLE_CATEGORIE=aLIBELLE_CATEGORIE;
}
@Override
public String toString() {
	return " [LIBELLE_CATEGORIE] = " + LIBELLE_CATEGORIE;
}
}