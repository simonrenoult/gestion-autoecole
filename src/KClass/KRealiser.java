package KClass;

import net.ko.kobject.KObject;
/**
* Classe KRealiser
*/
@SuppressWarnings("serial")
public class KRealiser extends KObject {
private KEleve eleve;
private long idELEVE;
private long idOBJECTIF;
private java.sql.Date DATE_REALISATION_OBJECTIF;
private int ETAT_OBJECTIF;
private KObjectif objectif;
private String OBSERVATION_OBJECTIF;
public KRealiser() {
	super();
	keyFields="idOBJECTIF,idELEVE";
	tableName="realiser";
	belongsTo(KObjectif.class);belongsTo(KEleve.class);

}
public KEleve getEleve(){
	//return the value of eleve
	return this.eleve;
}

public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public long getIdOBJECTIF(){
	//return the value of idOBJECTIF
	return this.idOBJECTIF;
}

public java.sql.Date getDATE_REALISATION_OBJECTIF(){
	//return the value of DATE_REALISATION_OBJECTIF
	return this.DATE_REALISATION_OBJECTIF;
}

public int getETAT_OBJECTIF(){
	//return the value of ETAT_OBJECTIF
	return this.ETAT_OBJECTIF;
}

public KObjectif getObjectif(){
	//return the value of objectif
	return this.objectif;
}

public String getOBSERVATION_OBJECTIF(){
	//return the value of OBSERVATION_OBJECTIF
	return this.OBSERVATION_OBJECTIF;
}

public void setEleve(KEleve aEleve){
	//set the value of eleve
	this.eleve=aEleve;
}
public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setIdOBJECTIF(long aIdOBJECTIF){
	//set the value of idOBJECTIF
	this.idOBJECTIF=aIdOBJECTIF;
}
public void setDATE_REALISATION_OBJECTIF(java.sql.Date aDATE_REALISATION_OBJECTIF){
	//set the value of DATE_REALISATION_OBJECTIF
	this.DATE_REALISATION_OBJECTIF=aDATE_REALISATION_OBJECTIF;
}
public void setETAT_OBJECTIF(int aETAT_OBJECTIF){
	//set the value of ETAT_OBJECTIF
	this.ETAT_OBJECTIF=aETAT_OBJECTIF;
}
public void setObjectif(KObjectif aObjectif){
	//set the value of objectif
	this.objectif=aObjectif;
}
public void setOBSERVATION_OBJECTIF(String aOBSERVATION_OBJECTIF){
	//set the value of OBSERVATION_OBJECTIF
	this.OBSERVATION_OBJECTIF=aOBSERVATION_OBJECTIF;
}
@Override
public String toString() {
	return " [eleve] = " + eleve+" [idELEVE] = " + idELEVE+" [idOBJECTIF] = " + idOBJECTIF+" [DATE_REALISATION_OBJECTIF] = " + DATE_REALISATION_OBJECTIF+" [ETAT_OBJECTIF] = " + ETAT_OBJECTIF+" [objectif] = " + objectif+" [OBSERVATION_OBJECTIF] = " + OBSERVATION_OBJECTIF;
}
}