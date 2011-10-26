package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KPASSER
*/
public class KPasser extends KObject {
private int NB_H_PRATIQUE;
private long ID_ELEVE;
private long ID_SYNTHESE;
private int RESULTAT;
private java.sql.Date DATE_PASSAGE_SYNTHESE;
private int NB_H_TH;
public KPasser() {
	super();
	keyFields="ID_SYNTHESE,ID_ELEVE";
	tableName="PASSER";
	//

}
public int getNB_H_PRATIQUE(){
	//return the value of NB_H_PRATIQUE
	return this.NB_H_PRATIQUE;
}

public long getID_ELEVE(){
	//return the value of ID_ELEVE
	return this.ID_ELEVE;
}

public long getID_SYNTHESE(){
	//return the value of ID_SYNTHESE
	return this.ID_SYNTHESE;
}

public int getRESULTAT(){
	//return the value of RESULTAT
	return this.RESULTAT;
}

public java.sql.Date getDATE_PASSAGE_SYNTHESE(){
	//return the value of DATE_PASSAGE_SYNTHESE
	return this.DATE_PASSAGE_SYNTHESE;
}

public int getNB_H_TH(){
	//return the value of NB_H_TH
	return this.NB_H_TH;
}

public void setNB_H_PRATIQUE(int aNB_H_PRATIQUE){
	//set the value of NB_H_PRATIQUE
	this.NB_H_PRATIQUE=aNB_H_PRATIQUE;
}
public void setID_ELEVE(long aID_ELEVE){
	//set the value of ID_ELEVE
	this.ID_ELEVE=aID_ELEVE;
}
public void setID_SYNTHESE(long aID_SYNTHESE){
	//set the value of ID_SYNTHESE
	this.ID_SYNTHESE=aID_SYNTHESE;
}
public void setRESULTAT(int aRESULTAT){
	//set the value of RESULTAT
	this.RESULTAT=aRESULTAT;
}
public void setDATE_PASSAGE_SYNTHESE(java.sql.Date aDATE_PASSAGE_SYNTHESE){
	//set the value of DATE_PASSAGE_SYNTHESE
	this.DATE_PASSAGE_SYNTHESE=aDATE_PASSAGE_SYNTHESE;
}
public void setNB_H_TH(int aNB_H_TH){
	//set the value of NB_H_TH
	this.NB_H_TH=aNB_H_TH;
}
@Override
public String toString() {
	return " [NB_H_PRATIQUE] = " + NB_H_PRATIQUE+" [ID_ELEVE] = " + ID_ELEVE+" [ID_SYNTHESE] = " + ID_SYNTHESE+" [RESULTAT] = " + RESULTAT+" [DATE_PASSAGE_SYNTHESE] = " + DATE_PASSAGE_SYNTHESE+" [NB_H_TH] = " + NB_H_TH;
}
}