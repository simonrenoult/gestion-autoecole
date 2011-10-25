package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KContenir
*/
public class KContenir extends KObject {
private int idFORMATION;
private int idETAPE;
public KContenir() {
	super();
	keyFields="idETAPE,idFORMATION";
	tableName="CONTENIR";
	//

}
public int getIdFORMATION(){
	//return the value of idFORMATION
	return this.idFORMATION;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public void setIdFORMATION(int aIdFORMATION){
	//set the value of idFORMATION
	this.idFORMATION=aIdFORMATION;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
@Override
public String toString() {
	return " [idFORMATION] = " + idFORMATION+" [idETAPE] = " + idETAPE;
}
}