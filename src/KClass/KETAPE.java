package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KETAPE
*/
public class KETAPE extends KObject {
private String LIBELLE_ETAPE;
public KETAPE() {
	super();
	keyFields="id";
	tableName="ETAPE";
	//

}
public String getLIBELLE_ETAPE(){
	//return the value of LIBELLE_ETAPE
	return this.LIBELLE_ETAPE;
}

public void setLIBELLE_ETAPE(String aLIBELLE_ETAPE){
	//set the value of LIBELLE_ETAPE
	this.LIBELLE_ETAPE=aLIBELLE_ETAPE;
}
@Override
public String toString() {
	return " [LIBELLE_ETAPE] = " + LIBELLE_ETAPE;
}
}