package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSuperviser
*/
public class KSuperviser extends KObject {
private KEleve eleve;
private long idELEVE;
private KEtape etape;
private int idETAPE;
private boolean RESULTAT_PASSAGE;
private int idMONITEUR;
private KMoniteur moniteur;
private java.sql.Date DATE_PASSAGE;
public KSuperviser() {
	super();
	keyFields="idELEVE,idETAPE";
	tableName="superviser";
	belongsTo(KMoniteur.class);belongsTo(KEtape.class);belongsTo(KEleve.class);

}
public KEleve getEleve(){
	//return the value of eleve
	return this.eleve;
}

public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public KEtape getEtape(){
	//return the value of etape
	return this.etape;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public boolean getRESULTAT_PASSAGE(){
	//return the value of RESULTAT_PASSAGE
	return this.RESULTAT_PASSAGE;
}

public int getIdMONITEUR(){
	//return the value of idMONITEUR
	return this.idMONITEUR;
}

public KMoniteur getMoniteur(){
	//return the value of moniteur
	return this.moniteur;
}

public java.sql.Date getDATE_PASSAGE(){
	//return the value of DATE_PASSAGE
	return this.DATE_PASSAGE;
}

public void setEleve(KEleve aEleve){
	//set the value of eleve
	this.eleve=aEleve;
}
public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setEtape(KEtape aEtape){
	//set the value of etape
	this.etape=aEtape;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setRESULTAT_PASSAGE(boolean aRESULTAT_PASSAGE){
	//set the value of RESULTAT_PASSAGE
	this.RESULTAT_PASSAGE=aRESULTAT_PASSAGE;
}
public void setIdMONITEUR(int aIdMONITEUR){
	//set the value of idMONITEUR
	this.idMONITEUR=aIdMONITEUR;
}
public void setMoniteur(KMoniteur aMoniteur){
	//set the value of moniteur
	this.moniteur=aMoniteur;
}
public void setDATE_PASSAGE(java.sql.Date aDATE_PASSAGE){
	//set the value of DATE_PASSAGE
	this.DATE_PASSAGE=aDATE_PASSAGE;
}
@Override
public String toString() {
	return " [eleve] = " + eleve+" [idELEVE] = " + idELEVE+" [etape] = " + etape+" [idETAPE] = " + idETAPE+" [RESULTAT_PASSAGE] = " + RESULTAT_PASSAGE+" [idMONITEUR] = " + idMONITEUR+" [moniteur] = " + moniteur+" [DATE_PASSAGE] = " + DATE_PASSAGE;
}
}