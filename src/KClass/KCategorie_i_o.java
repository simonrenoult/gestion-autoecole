package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCATEGORIE_I_O
*/
public class KCategorie_i_o extends KObject {
private int ID_CATEGORIE;
private String LIBELLE_CATEGORIE;
public KCategorie_i_o() {
	super();
	keyFields="ID_CATEGORIE";
	tableName="CATEGORIE_I_O";
	//

}
public int getID_CATEGORIE(){
	//return the value of ID_CATEGORIE
	return this.ID_CATEGORIE;
}

public String getLIBELLE_CATEGORIE(){
	//return the value of LIBELLE_CATEGORIE
	return this.LIBELLE_CATEGORIE;
}

public void setID_CATEGORIE(int aID_CATEGORIE){
	//set the value of ID_CATEGORIE
	this.ID_CATEGORIE=aID_CATEGORIE;
}
public void setLIBELLE_CATEGORIE(String aLIBELLE_CATEGORIE){
	//set the value of LIBELLE_CATEGORIE
	this.LIBELLE_CATEGORIE=aLIBELLE_CATEGORIE;
}
@Override
public String toString() {
	return " [ID_CATEGORIE] = " + ID_CATEGORIE+" [LIBELLE_CATEGORIE] = " + LIBELLE_CATEGORIE;
}
}