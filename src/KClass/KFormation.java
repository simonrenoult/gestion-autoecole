package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KFORMATION
*/
public class KFormation extends KObject {
private int ID_FORMATION;
private String LIBELLE_FORMATION;
public KFormation() {
	super();
	keyFields="ID_FORMATION";
	tableName="FORMATION";
	//

}
public int getID_FORMATION(){
	//return the value of ID_FORMATION
	return this.ID_FORMATION;
}

public String getLIBELLE_FORMATION(){
	//return the value of LIBELLE_FORMATION
	return this.LIBELLE_FORMATION;
}

public void setID_FORMATION(int aID_FORMATION){
	//set the value of ID_FORMATION
	this.ID_FORMATION=aID_FORMATION;
}
public void setLIBELLE_FORMATION(String aLIBELLE_FORMATION){
	//set the value of LIBELLE_FORMATION
	this.LIBELLE_FORMATION=aLIBELLE_FORMATION;
}
@Override
public String toString() {
	return " [ID_FORMATION] = " + ID_FORMATION+" [LIBELLE_FORMATION] = " + LIBELLE_FORMATION;
}
}