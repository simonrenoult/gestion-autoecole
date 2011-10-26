package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KREALISER
*/
public class KRealiser extends KObject {
private long ID_ELEVE;
private java.sql.Date DATE_REALISATION_OBJECTIF;
private int ETAT_OBJECTIF;
private String OBSERVATION_OBJECTIF;
private long ID_OBJECTIF;
public KRealiser() {
	super();
	keyFields="ID_OBJECTIF,ID_ELEVE";
	tableName="REALISER";
	//

}
public long getID_ELEVE(){
	//return the value of ID_ELEVE
	return this.ID_ELEVE;
}

public java.sql.Date getDATE_REALISATION_OBJECTIF(){
	//return the value of DATE_REALISATION_OBJECTIF
	return this.DATE_REALISATION_OBJECTIF;
}

public int getETAT_OBJECTIF(){
	//return the value of ETAT_OBJECTIF
	return this.ETAT_OBJECTIF;
}

public String getOBSERVATION_OBJECTIF(){
	//return the value of OBSERVATION_OBJECTIF
	return this.OBSERVATION_OBJECTIF;
}

public long getID_OBJECTIF(){
	//return the value of ID_OBJECTIF
	return this.ID_OBJECTIF;
}

public void setID_ELEVE(long aID_ELEVE){
	//set the value of ID_ELEVE
	this.ID_ELEVE=aID_ELEVE;
}
public void setDATE_REALISATION_OBJECTIF(java.sql.Date aDATE_REALISATION_OBJECTIF){
	//set the value of DATE_REALISATION_OBJECTIF
	this.DATE_REALISATION_OBJECTIF=aDATE_REALISATION_OBJECTIF;
}
public void setETAT_OBJECTIF(int aETAT_OBJECTIF){
	//set the value of ETAT_OBJECTIF
	this.ETAT_OBJECTIF=aETAT_OBJECTIF;
}
public void setOBSERVATION_OBJECTIF(String aOBSERVATION_OBJECTIF){
	//set the value of OBSERVATION_OBJECTIF
	this.OBSERVATION_OBJECTIF=aOBSERVATION_OBJECTIF;
}
public void setID_OBJECTIF(long aID_OBJECTIF){
	//set the value of ID_OBJECTIF
	this.ID_OBJECTIF=aID_OBJECTIF;
}
@Override
public String toString() {
	return " [ID_ELEVE] = " + ID_ELEVE+" [DATE_REALISATION_OBJECTIF] = " + DATE_REALISATION_OBJECTIF+" [ETAT_OBJECTIF] = " + ETAT_OBJECTIF+" [OBSERVATION_OBJECTIF] = " + OBSERVATION_OBJECTIF+" [ID_OBJECTIF] = " + ID_OBJECTIF;
}
}