package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KASSURER_LECON
*/
public class KASSURER_LECON extends KObject {
private int DUREE_LECON;
private long idELEVE;
private java.sql.Date DATE_LECON;
private int idMONITEUR;
private int NUM_LECON;
private String OBSERVATION_LECON;
private int HEURE_LECON;
public KASSURER_LECON() {
	super();
	keyFields="idMONITEUR,idELEVE";
	tableName="ASSURER_LECON";
	//

}
public int getDUREE_LECON(){
	//return the value of DUREE_LECON
	return this.DUREE_LECON;
}

public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public java.sql.Date getDATE_LECON(){
	//return the value of DATE_LECON
	return this.DATE_LECON;
}

public int getIdMONITEUR(){
	//return the value of idMONITEUR
	return this.idMONITEUR;
}

public int getNUM_LECON(){
	//return the value of NUM_LECON
	return this.NUM_LECON;
}

public String getOBSERVATION_LECON(){
	//return the value of OBSERVATION_LECON
	return this.OBSERVATION_LECON;
}

public int getHEURE_LECON(){
	//return the value of HEURE_LECON
	return this.HEURE_LECON;
}

public void setDUREE_LECON(int aDUREE_LECON){
	//set the value of DUREE_LECON
	this.DUREE_LECON=aDUREE_LECON;
}
public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setDATE_LECON(java.sql.Date aDATE_LECON){
	//set the value of DATE_LECON
	this.DATE_LECON=aDATE_LECON;
}
public void setIdMONITEUR(int aIdMONITEUR){
	//set the value of idMONITEUR
	this.idMONITEUR=aIdMONITEUR;
}
public void setNUM_LECON(int aNUM_LECON){
	//set the value of NUM_LECON
	this.NUM_LECON=aNUM_LECON;
}
public void setOBSERVATION_LECON(String aOBSERVATION_LECON){
	//set the value of OBSERVATION_LECON
	this.OBSERVATION_LECON=aOBSERVATION_LECON;
}
public void setHEURE_LECON(int aHEURE_LECON){
	//set the value of HEURE_LECON
	this.HEURE_LECON=aHEURE_LECON;
}
@Override
public String toString() {
	return " [DUREE_LECON] = " + DUREE_LECON+" [idELEVE] = " + idELEVE+" [DATE_LECON] = " + DATE_LECON+" [idMONITEUR] = " + idMONITEUR+" [NUM_LECON] = " + NUM_LECON+" [OBSERVATION_LECON] = " + OBSERVATION_LECON+" [HEURE_LECON] = " + HEURE_LECON;
}
}