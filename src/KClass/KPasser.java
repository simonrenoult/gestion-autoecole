package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KPasser
*/
public class KPasser extends KObject {
private KEleve eleve;
private long idELEVE;
private int RESULTAT;
private java.sql.Date DATE_PASSAGE_SYNTHESE;
private long idSYNTHESE;
private int NB_H_TH;
private KSynthese synthese;
public KPasser() {
	super();
	keyFields="idSYNTHESE,idELEVE";
	tableName="passer";
	//belongsTo(KSynthese.class);belongsTo(KEleve.class);

}
public KEleve getEleve(){
	//return the value of eleve
	return this.eleve;
}

public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public int getRESULTAT(){
	//return the value of RESULTAT
	return this.RESULTAT;
}

public java.sql.Date getDATE_PASSAGE_SYNTHESE(){
	//return the value of DATE_PASSAGE_SYNTHESE
	return this.DATE_PASSAGE_SYNTHESE;
}

public long getIdSYNTHESE(){
	//return the value of idSYNTHESE
	return this.idSYNTHESE;
}

public int getNB_H_TH(){
	//return the value of NB_H_TH
	return this.NB_H_TH;
}

public KSynthese getSynthese(){
	//return the value of synthese
	return this.synthese;
}

public void setEleve(KEleve aEleve){
	//set the value of eleve
	this.eleve=aEleve;
}
public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setRESULTAT(int aRESULTAT){
	//set the value of RESULTAT
	this.RESULTAT=aRESULTAT;
}
public void setDATE_PASSAGE_SYNTHESE(java.sql.Date aDATE_PASSAGE_SYNTHESE){
	//set the value of DATE_PASSAGE_SYNTHESE
	this.DATE_PASSAGE_SYNTHESE=aDATE_PASSAGE_SYNTHESE;
}
public void setIdSYNTHESE(long aIdSYNTHESE){
	//set the value of idSYNTHESE
	this.idSYNTHESE=aIdSYNTHESE;
}
public void setNB_H_TH(int aNB_H_TH){
	//set the value of NB_H_TH
	this.NB_H_TH=aNB_H_TH;
}
public void setSynthese(KSynthese aSynthese){
	//set the value of synthese
	this.synthese=aSynthese;
}
@Override
public String toString() {
	return " [eleve] = " + eleve+" [idELEVE] = " + idELEVE+" [RESULTAT] = " + RESULTAT+" [DATE_PASSAGE_SYNTHESE] = " + DATE_PASSAGE_SYNTHESE+" [idSYNTHESE] = " + idSYNTHESE+" [NB_H_TH] = " + NB_H_TH+" [synthese] = " + synthese;
}
}