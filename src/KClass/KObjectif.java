package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KObjectif
*/
public class KObjectif extends KObject {
private int idCATEGORIE;
private int idETAPE;
private String LIBELLE_OBJECTIF;
public KObjectif() {
	super();
	keyFields="id";
	tableName="OBJECTIF";
	//

}
public int getIdCATEGORIE(){
	//return the value of idCATEGORIE
	return this.idCATEGORIE;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public String getLIBELLE_OBJECTIF(){
	//return the value of LIBELLE_OBJECTIF
	return this.LIBELLE_OBJECTIF;
}

public void setIdCATEGORIE(int aIdCATEGORIE){
	//set the value of idCATEGORIE
	this.idCATEGORIE=aIdCATEGORIE;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setLIBELLE_OBJECTIF(String aLIBELLE_OBJECTIF){
	//set the value of LIBELLE_OBJECTIF
	this.LIBELLE_OBJECTIF=aLIBELLE_OBJECTIF;
}
@Override
public String toString() {
	return " [idCATEGORIE] = " + idCATEGORIE+" [idETAPE] = " + idETAPE+" [LIBELLE_OBJECTIF] = " + LIBELLE_OBJECTIF;
}
}