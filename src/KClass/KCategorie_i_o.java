package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCategorie_i_o
*/
public class KCategorie_i_o extends KObject {
private KListObject<KObjectif> objectifs;
private String LIBELLE_CATEGORIE;
public KCategorie_i_o() {
	super();
	keyFields="id";
	tableName="categorie_i_o";
	//hasMany(KObjectif.class);

}
public KListObject<KObjectif> getObjectifs(){
	//return the value of objectifs
	return this.objectifs;
}

public String getLIBELLE_CATEGORIE(){
	//return the value of LIBELLE_CATEGORIE
	return this.LIBELLE_CATEGORIE;
}

public void setObjectifs(KListObject<KObjectif> aObjectifs){
	//set the value of objectifs
	this.objectifs=aObjectifs;
}
public void setLIBELLE_CATEGORIE(String aLIBELLE_CATEGORIE){
	//set the value of LIBELLE_CATEGORIE
	this.LIBELLE_CATEGORIE=aLIBELLE_CATEGORIE;
}
@Override
public String toString() {
	return " [objectifs] = " + objectifs+" [LIBELLE_CATEGORIE] = " + LIBELLE_CATEGORIE;
}
}