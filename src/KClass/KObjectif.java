package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KOBJECTIF
*/
public class KObjectif extends KObject {
private int ID_ETAPE;
private int ID_CATEGORIE;
private long ID_OBJECTIF;
private String LIBELLE_OBJECTIF;
public KObjectif() {
	super();
	keyFields="ID_OBJECTIF";
	tableName="OBJECTIF";
	//

}
public int getID_ETAPE(){
	//return the value of ID_ETAPE
	return this.ID_ETAPE;
}

public int getID_CATEGORIE(){
	//return the value of ID_CATEGORIE
	return this.ID_CATEGORIE;
}

public long getID_OBJECTIF(){
	//return the value of ID_OBJECTIF
	return this.ID_OBJECTIF;
}

public String getLIBELLE_OBJECTIF(){
	//return the value of LIBELLE_OBJECTIF
	return this.LIBELLE_OBJECTIF;
}

public void setID_ETAPE(int aID_ETAPE){
	//set the value of ID_ETAPE
	this.ID_ETAPE=aID_ETAPE;
}
public void setID_CATEGORIE(int aID_CATEGORIE){
	//set the value of ID_CATEGORIE
	this.ID_CATEGORIE=aID_CATEGORIE;
}
public void setID_OBJECTIF(long aID_OBJECTIF){
	//set the value of ID_OBJECTIF
	this.ID_OBJECTIF=aID_OBJECTIF;
}
public void setLIBELLE_OBJECTIF(String aLIBELLE_OBJECTIF){
	//set the value of LIBELLE_OBJECTIF
	this.LIBELLE_OBJECTIF=aLIBELLE_OBJECTIF;
}
@Override
public String toString() {
	return " [ID_ETAPE] = " + ID_ETAPE+" [ID_CATEGORIE] = " + ID_CATEGORIE+" [ID_OBJECTIF] = " + ID_OBJECTIF+" [LIBELLE_OBJECTIF] = " + LIBELLE_OBJECTIF;
}
}