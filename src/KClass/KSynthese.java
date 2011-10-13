package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSynthese
*/
public class KSynthese extends KObject {
private int idETAPE;
private String LIBELLE_SYNTHESE;
public KSynthese() {
	super();
	keyFields="id";
	tableName="synthese";
	//

}
public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public String getLIBELLE_SYNTHESE(){
	//return the value of LIBELLE_SYNTHESE
	return this.LIBELLE_SYNTHESE;
}

public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setLIBELLE_SYNTHESE(String aLIBELLE_SYNTHESE){
	//set the value of LIBELLE_SYNTHESE
	this.LIBELLE_SYNTHESE=aLIBELLE_SYNTHESE;
}
@Override
public String toString() {
	return " [idETAPE] = " + idETAPE+" [LIBELLE_SYNTHESE] = " + LIBELLE_SYNTHESE;
}
}