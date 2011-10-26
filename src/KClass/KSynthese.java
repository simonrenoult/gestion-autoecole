package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSYNTHESE
*/
public class KSynthese extends KObject {
private long ID_SYNTHESE;
private int ID_ETAPE;
private String LIBELLE_SYNTHESE;
public KSynthese() {
	super();
	keyFields="ID_SYNTHESE";
	tableName="SYNTHESE";
	//

}
public long getID_SYNTHESE(){
	//return the value of ID_SYNTHESE
	return this.ID_SYNTHESE;
}

public int getID_ETAPE(){
	//return the value of ID_ETAPE
	return this.ID_ETAPE;
}

public String getLIBELLE_SYNTHESE(){
	//return the value of LIBELLE_SYNTHESE
	return this.LIBELLE_SYNTHESE;
}

public void setID_SYNTHESE(long aID_SYNTHESE){
	//set the value of ID_SYNTHESE
	this.ID_SYNTHESE=aID_SYNTHESE;
}
public void setID_ETAPE(int aID_ETAPE){
	//set the value of ID_ETAPE
	this.ID_ETAPE=aID_ETAPE;
}
public void setLIBELLE_SYNTHESE(String aLIBELLE_SYNTHESE){
	//set the value of LIBELLE_SYNTHESE
	this.LIBELLE_SYNTHESE=aLIBELLE_SYNTHESE;
}
@Override
public String toString() {
	return " [ID_SYNTHESE] = " + ID_SYNTHESE+" [ID_ETAPE] = " + ID_ETAPE+" [LIBELLE_SYNTHESE] = " + LIBELLE_SYNTHESE;
}
}