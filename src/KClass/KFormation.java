package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KFormation
*/
public class KFormation extends KObject {
private String LIBELLE_FORMATION;
public KFormation() {
	super();
	keyFields="id";
	tableName="formation";
	//

}
public String getLIBELLE_FORMATION(){
	//return the value of LIBELLE_FORMATION
	return this.LIBELLE_FORMATION;
}

public void setLIBELLE_FORMATION(String aLIBELLE_FORMATION){
	//set the value of LIBELLE_FORMATION
	this.LIBELLE_FORMATION=aLIBELLE_FORMATION;
}
@Override
public String toString() {
	return " [LIBELLE_FORMATION] = " + LIBELLE_FORMATION;
}
}