package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KETAPE
*/
public class KEtape extends KObject {
private String LIBELLE_ETAPE;
private int ID_ETAPE;
public KEtape() {
	super();
	keyFields="ID_ETAPE";
	tableName="ETAPE";
	//

}
public String getLIBELLE_ETAPE(){
	//return the value of LIBELLE_ETAPE
	return this.LIBELLE_ETAPE;
}

public int getID_ETAPE(){
	//return the value of ID_ETAPE
	return this.ID_ETAPE;
}

public void setLIBELLE_ETAPE(String aLIBELLE_ETAPE){
	//set the value of LIBELLE_ETAPE
	this.LIBELLE_ETAPE=aLIBELLE_ETAPE;
}
public void setID_ETAPE(int aID_ETAPE){
	//set the value of ID_ETAPE
	this.ID_ETAPE=aID_ETAPE;
}
@Override
public String toString() {
	return " [LIBELLE_ETAPE] = " + LIBELLE_ETAPE+" [ID_ETAPE] = " + ID_ETAPE;
}
}