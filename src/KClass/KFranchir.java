package kernel.tests;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KFranchir
*/
public class KFranchir extends KObject {
private long idELEVE;
private int idETAPE;
private java.sql.Date DATE_FRANCHISSEMENT_ETAPE;
public KFranchir() {
	super();
	keyFields="idETAPE,idELEVE";
	tableName="franchir";
	//

}
public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public java.sql.Date getDATE_FRANCHISSEMENT_ETAPE(){
	//return the value of DATE_FRANCHISSEMENT_ETAPE
	return this.DATE_FRANCHISSEMENT_ETAPE;
}

public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setDATE_FRANCHISSEMENT_ETAPE(java.sql.Date aDATE_FRANCHISSEMENT_ETAPE){
	//set the value of DATE_FRANCHISSEMENT_ETAPE
	this.DATE_FRANCHISSEMENT_ETAPE=aDATE_FRANCHISSEMENT_ETAPE;
}
@Override
public String toString() {
	return " [idELEVE] = " + idELEVE+" [idETAPE] = " + idETAPE+" [DATE_FRANCHISSEMENT_ETAPE] = " + DATE_FRANCHISSEMENT_ETAPE;
}
}