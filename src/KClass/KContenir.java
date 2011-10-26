package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCONTENIR
*/
public class KContenir extends KObject {
private int ID_FORMATION;
private int ID_ETAPE;
public KContenir() {
	super();
	keyFields="ID_ETAPE,ID_FORMATION";
	tableName="CONTENIR";
	//

}
public int getID_FORMATION(){
	//return the value of ID_FORMATION
	return this.ID_FORMATION;
}

public int getID_ETAPE(){
	//return the value of ID_ETAPE
	return this.ID_ETAPE;
}

public void setID_FORMATION(int aID_FORMATION){
	//set the value of ID_FORMATION
	this.ID_FORMATION=aID_FORMATION;
}
public void setID_ETAPE(int aID_ETAPE){
	//set the value of ID_ETAPE
	this.ID_ETAPE=aID_ETAPE;
}
@Override
public String toString() {
	return " [ID_FORMATION] = " + ID_FORMATION+" [ID_ETAPE] = " + ID_ETAPE;
}
}