package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSUPERVISER
*/
public class KSuperviser extends KObject {
private long ID_ELEVE;
private boolean RESULTAT_PASSAGE;
private int MONITEUR_ID_MONITEUR;
private int ID_ETAPE;
private java.sql.Date DATE_PASSAGE;
public KSuperviser() {
	super();
	keyFields="ID_ELEVE,ID_ETAPE";
	tableName="SUPERVISER";
	//

}
public long getID_ELEVE(){
	//return the value of ID_ELEVE
	return this.ID_ELEVE;
}

public boolean getRESULTAT_PASSAGE(){
	//return the value of RESULTAT_PASSAGE
	return this.RESULTAT_PASSAGE;
}

public int getMONITEUR_ID_MONITEUR(){
	//return the value of MONITEUR_ID_MONITEUR
	return this.MONITEUR_ID_MONITEUR;
}

public int getID_ETAPE(){
	//return the value of ID_ETAPE
	return this.ID_ETAPE;
}

public java.sql.Date getDATE_PASSAGE(){
	//return the value of DATE_PASSAGE
	return this.DATE_PASSAGE;
}

public void setID_ELEVE(long aID_ELEVE){
	//set the value of ID_ELEVE
	this.ID_ELEVE=aID_ELEVE;
}
public void setRESULTAT_PASSAGE(boolean aRESULTAT_PASSAGE){
	//set the value of RESULTAT_PASSAGE
	this.RESULTAT_PASSAGE=aRESULTAT_PASSAGE;
}
public void setMONITEUR_ID_MONITEUR(int aMONITEUR_ID_MONITEUR){
	//set the value of MONITEUR_ID_MONITEUR
	this.MONITEUR_ID_MONITEUR=aMONITEUR_ID_MONITEUR;
}
public void setID_ETAPE(int aID_ETAPE){
	//set the value of ID_ETAPE
	this.ID_ETAPE=aID_ETAPE;
}
public void setDATE_PASSAGE(java.sql.Date aDATE_PASSAGE){
	//set the value of DATE_PASSAGE
	this.DATE_PASSAGE=aDATE_PASSAGE;
}
@Override
public String toString() {
	return " [ID_ELEVE] = " + ID_ELEVE+" [RESULTAT_PASSAGE] = " + RESULTAT_PASSAGE+" [MONITEUR_ID_MONITEUR] = " + MONITEUR_ID_MONITEUR+" [ID_ETAPE] = " + ID_ETAPE+" [DATE_PASSAGE] = " + DATE_PASSAGE;
}
}