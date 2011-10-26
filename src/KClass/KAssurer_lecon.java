package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KASSURER_LECON
*/
public class KAssurer_lecon extends KObject {
private int DUREE_LECON;
private long ID_ELEVE;
private java.sql.Date DATE_AGENDA;
private int NUM_LECON;
private int ID_MONITEUR;
private java.sql.Time HEURE;
private String OBSERVATION_LECON;
public KAssurer_lecon() {
	super();
	keyFields="HEURE,DATE_AGENDA,ID_MONITEUR,ID_ELEVE";
	tableName="ASSURER_LECON";
	//

}
public int getDUREE_LECON(){
	//return the value of DUREE_LECON
	return this.DUREE_LECON;
}

public long getID_ELEVE(){
	//return the value of ID_ELEVE
	return this.ID_ELEVE;
}

public java.sql.Date getDATE_AGENDA(){
	//return the value of DATE_AGENDA
	return this.DATE_AGENDA;
}

public int getNUM_LECON(){
	//return the value of NUM_LECON
	return this.NUM_LECON;
}

public int getID_MONITEUR(){
	//return the value of ID_MONITEUR
	return this.ID_MONITEUR;
}

public java.sql.Time getHEURE(){
	//return the value of HEURE
	return this.HEURE;
}

public String getOBSERVATION_LECON(){
	//return the value of OBSERVATION_LECON
	return this.OBSERVATION_LECON;
}

public void setDUREE_LECON(int aDUREE_LECON){
	//set the value of DUREE_LECON
	this.DUREE_LECON=aDUREE_LECON;
}
public void setID_ELEVE(long aID_ELEVE){
	//set the value of ID_ELEVE
	this.ID_ELEVE=aID_ELEVE;
}
public void setDATE_AGENDA(java.sql.Date aDATE_AGENDA){
	//set the value of DATE_AGENDA
	this.DATE_AGENDA=aDATE_AGENDA;
}
public void setNUM_LECON(int aNUM_LECON){
	//set the value of NUM_LECON
	this.NUM_LECON=aNUM_LECON;
}
public void setID_MONITEUR(int aID_MONITEUR){
	//set the value of ID_MONITEUR
	this.ID_MONITEUR=aID_MONITEUR;
}
public void setHEURE(java.sql.Time aHEURE){
	//set the value of HEURE
	this.HEURE=aHEURE;
}
public void setOBSERVATION_LECON(String aOBSERVATION_LECON){
	//set the value of OBSERVATION_LECON
	this.OBSERVATION_LECON=aOBSERVATION_LECON;
}
@Override
public String toString() {
	return " [DUREE_LECON] = " + DUREE_LECON+" [ID_ELEVE] = " + ID_ELEVE+" [DATE_AGENDA] = " + DATE_AGENDA+" [NUM_LECON] = " + NUM_LECON+" [ID_MONITEUR] = " + ID_MONITEUR+" [HEURE] = " + HEURE+" [OBSERVATION_LECON] = " + OBSERVATION_LECON;
}
}