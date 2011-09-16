package kernel.tests;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSuperviser
*/
public class KSuperviser extends KObject {
private boolean RESULTAT_PASSAGE;
private int idMONITEUR;
private int idEVALUATION_CONTROLE;
private java.sql.Date DATE_PASSAGE;
public KSuperviser() {
	super();
	keyFields="idEVALUATION_CONTROLE,idMONITEUR";
	tableName="superviser";
	//

}
public boolean getRESULTAT_PASSAGE(){
	//return the value of RESULTAT_PASSAGE
	return this.RESULTAT_PASSAGE;
}

public int getIdMONITEUR(){
	//return the value of idMONITEUR
	return this.idMONITEUR;
}

public int getIdEVALUATION_CONTROLE(){
	//return the value of idEVALUATION_CONTROLE
	return this.idEVALUATION_CONTROLE;
}

public java.sql.Date getDATE_PASSAGE(){
	//return the value of DATE_PASSAGE
	return this.DATE_PASSAGE;
}

public void setRESULTAT_PASSAGE(boolean aRESULTAT_PASSAGE){
	//set the value of RESULTAT_PASSAGE
	this.RESULTAT_PASSAGE=aRESULTAT_PASSAGE;
}
public void setIdMONITEUR(int aIdMONITEUR){
	//set the value of idMONITEUR
	this.idMONITEUR=aIdMONITEUR;
}
public void setIdEVALUATION_CONTROLE(int aIdEVALUATION_CONTROLE){
	//set the value of idEVALUATION_CONTROLE
	this.idEVALUATION_CONTROLE=aIdEVALUATION_CONTROLE;
}
public void setDATE_PASSAGE(java.sql.Date aDATE_PASSAGE){
	//set the value of DATE_PASSAGE
	this.DATE_PASSAGE=aDATE_PASSAGE;
}
@Override
public String toString() {
	return " [RESULTAT_PASSAGE] = " + RESULTAT_PASSAGE+" [idMONITEUR] = " + idMONITEUR+" [idEVALUATION_CONTROLE] = " + idEVALUATION_CONTROLE+" [DATE_PASSAGE] = " + DATE_PASSAGE;
}
}