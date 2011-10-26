package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KELEVE
*/
public class KEleve extends KObject {
private long ID_ELEVE;
private java.sql.Date DATE_ENREGISTREMENT_ELEVE;
private String OBSERVATION_VUE_ELEVE;
private String ADRESSE_ELEVE;
private String PRENOM_ELEVE;
private int CODE_POSTAL_ELEVE;
private int ID_MONITEUR;
private String PROFESSION_ELEVE;
private java.sql.Date DATE_INSCRIPTION_ELEVE;
private int RESULTAT_ELEVE_ORAL;
private String TELEPHONE_ELEVE;
private String COMMUNE_ELEVE;
private int VOLUME_HORAIRE_TH_ELEVE;
private java.sql.Date DATE_EVAL_ELEVE;
private String MAIL_ELEVE;
private int VOLUME_HORAIRE_PRATIQUE_ELEVE;
private int ID_FORMATION;
private java.sql.Date DATE_DE_NAISS_ELEVE;
private String NOM_ELEVE;
private int TEST_VU_ELEVE;
public KEleve() {
	super();
	keyFields="ID_ELEVE";
	tableName="ELEVE";
	//

}
public long getID_ELEVE(){
	//return the value of ID_ELEVE
	return this.ID_ELEVE;
}

public java.sql.Date getDATE_ENREGISTREMENT_ELEVE(){
	//return the value of DATE_ENREGISTREMENT_ELEVE
	return this.DATE_ENREGISTREMENT_ELEVE;
}

public String getOBSERVATION_VUE_ELEVE(){
	//return the value of OBSERVATION_VUE_ELEVE
	return this.OBSERVATION_VUE_ELEVE;
}

public String getADRESSE_ELEVE(){
	//return the value of ADRESSE_ELEVE
	return this.ADRESSE_ELEVE;
}

public String getPRENOM_ELEVE(){
	//return the value of PRENOM_ELEVE
	return this.PRENOM_ELEVE;
}

public int getCODE_POSTAL_ELEVE(){
	//return the value of CODE_POSTAL_ELEVE
	return this.CODE_POSTAL_ELEVE;
}

public int getID_MONITEUR(){
	//return the value of ID_MONITEUR
	return this.ID_MONITEUR;
}

public String getPROFESSION_ELEVE(){
	//return the value of PROFESSION_ELEVE
	return this.PROFESSION_ELEVE;
}

public java.sql.Date getDATE_INSCRIPTION_ELEVE(){
	//return the value of DATE_INSCRIPTION_ELEVE
	return this.DATE_INSCRIPTION_ELEVE;
}

public int getRESULTAT_ELEVE_ORAL(){
	//return the value of RESULTAT_ELEVE_ORAL
	return this.RESULTAT_ELEVE_ORAL;
}

public String getTELEPHONE_ELEVE(){
	//return the value of TELEPHONE_ELEVE
	return this.TELEPHONE_ELEVE;
}

public String getCOMMUNE_ELEVE(){
	//return the value of COMMUNE_ELEVE
	return this.COMMUNE_ELEVE;
}

public int getVOLUME_HORAIRE_TH_ELEVE(){
	//return the value of VOLUME_HORAIRE_TH_ELEVE
	return this.VOLUME_HORAIRE_TH_ELEVE;
}

public java.sql.Date getDATE_EVAL_ELEVE(){
	//return the value of DATE_EVAL_ELEVE
	return this.DATE_EVAL_ELEVE;
}

public String getMAIL_ELEVE(){
	//return the value of MAIL_ELEVE
	return this.MAIL_ELEVE;
}

public int getVOLUME_HORAIRE_PRATIQUE_ELEVE(){
	//return the value of VOLUME_HORAIRE_PRATIQUE_ELEVE
	return this.VOLUME_HORAIRE_PRATIQUE_ELEVE;
}

public int getID_FORMATION(){
	//return the value of ID_FORMATION
	return this.ID_FORMATION;
}

public java.sql.Date getDATE_DE_NAISS_ELEVE(){
	//return the value of DATE_DE_NAISS_ELEVE
	return this.DATE_DE_NAISS_ELEVE;
}

public String getNOM_ELEVE(){
	//return the value of NOM_ELEVE
	return this.NOM_ELEVE;
}

public int getTEST_VU_ELEVE(){
	//return the value of TEST_VU_ELEVE
	return this.TEST_VU_ELEVE;
}

public void setID_ELEVE(long aID_ELEVE){
	//set the value of ID_ELEVE
	this.ID_ELEVE=aID_ELEVE;
}
public void setDATE_ENREGISTREMENT_ELEVE(java.sql.Date aDATE_ENREGISTREMENT_ELEVE){
	//set the value of DATE_ENREGISTREMENT_ELEVE
	this.DATE_ENREGISTREMENT_ELEVE=aDATE_ENREGISTREMENT_ELEVE;
}
public void setOBSERVATION_VUE_ELEVE(String aOBSERVATION_VUE_ELEVE){
	//set the value of OBSERVATION_VUE_ELEVE
	this.OBSERVATION_VUE_ELEVE=aOBSERVATION_VUE_ELEVE;
}
public void setADRESSE_ELEVE(String aADRESSE_ELEVE){
	//set the value of ADRESSE_ELEVE
	this.ADRESSE_ELEVE=aADRESSE_ELEVE;
}
public void setPRENOM_ELEVE(String aPRENOM_ELEVE){
	//set the value of PRENOM_ELEVE
	this.PRENOM_ELEVE=aPRENOM_ELEVE;
}
public void setCODE_POSTAL_ELEVE(int aCODE_POSTAL_ELEVE){
	//set the value of CODE_POSTAL_ELEVE
	this.CODE_POSTAL_ELEVE=aCODE_POSTAL_ELEVE;
}
public void setID_MONITEUR(int aID_MONITEUR){
	//set the value of ID_MONITEUR
	this.ID_MONITEUR=aID_MONITEUR;
}
public void setPROFESSION_ELEVE(String aPROFESSION_ELEVE){
	//set the value of PROFESSION_ELEVE
	this.PROFESSION_ELEVE=aPROFESSION_ELEVE;
}
public void setDATE_INSCRIPTION_ELEVE(java.sql.Date aDATE_INSCRIPTION_ELEVE){
	//set the value of DATE_INSCRIPTION_ELEVE
	this.DATE_INSCRIPTION_ELEVE=aDATE_INSCRIPTION_ELEVE;
}
public void setRESULTAT_ELEVE_ORAL(int aRESULTAT_ELEVE_ORAL){
	//set the value of RESULTAT_ELEVE_ORAL
	this.RESULTAT_ELEVE_ORAL=aRESULTAT_ELEVE_ORAL;
}
public void setTELEPHONE_ELEVE(String aTELEPHONE_ELEVE){
	//set the value of TELEPHONE_ELEVE
	this.TELEPHONE_ELEVE=aTELEPHONE_ELEVE;
}
public void setCOMMUNE_ELEVE(String aCOMMUNE_ELEVE){
	//set the value of COMMUNE_ELEVE
	this.COMMUNE_ELEVE=aCOMMUNE_ELEVE;
}
public void setVOLUME_HORAIRE_TH_ELEVE(int aVOLUME_HORAIRE_TH_ELEVE){
	//set the value of VOLUME_HORAIRE_TH_ELEVE
	this.VOLUME_HORAIRE_TH_ELEVE=aVOLUME_HORAIRE_TH_ELEVE;
}
public void setDATE_EVAL_ELEVE(java.sql.Date aDATE_EVAL_ELEVE){
	//set the value of DATE_EVAL_ELEVE
	this.DATE_EVAL_ELEVE=aDATE_EVAL_ELEVE;
}
public void setMAIL_ELEVE(String aMAIL_ELEVE){
	//set the value of MAIL_ELEVE
	this.MAIL_ELEVE=aMAIL_ELEVE;
}
public void setVOLUME_HORAIRE_PRATIQUE_ELEVE(int aVOLUME_HORAIRE_PRATIQUE_ELEVE){
	//set the value of VOLUME_HORAIRE_PRATIQUE_ELEVE
	this.VOLUME_HORAIRE_PRATIQUE_ELEVE=aVOLUME_HORAIRE_PRATIQUE_ELEVE;
}
public void setID_FORMATION(int aID_FORMATION){
	//set the value of ID_FORMATION
	this.ID_FORMATION=aID_FORMATION;
}
public void setDATE_DE_NAISS_ELEVE(java.sql.Date aDATE_DE_NAISS_ELEVE){
	//set the value of DATE_DE_NAISS_ELEVE
	this.DATE_DE_NAISS_ELEVE=aDATE_DE_NAISS_ELEVE;
}
public void setNOM_ELEVE(String aNOM_ELEVE){
	//set the value of NOM_ELEVE
	this.NOM_ELEVE=aNOM_ELEVE;
}
public void setTEST_VU_ELEVE(int aTEST_VU_ELEVE){
	//set the value of TEST_VU_ELEVE
	this.TEST_VU_ELEVE=aTEST_VU_ELEVE;
}
@Override
public String toString() {
	return " [ID_ELEVE] = " + ID_ELEVE+" [DATE_ENREGISTREMENT_ELEVE] = " + DATE_ENREGISTREMENT_ELEVE+" [OBSERVATION_VUE_ELEVE] = " + OBSERVATION_VUE_ELEVE+" [ADRESSE_ELEVE] = " + ADRESSE_ELEVE+" [PRENOM_ELEVE] = " + PRENOM_ELEVE+" [CODE_POSTAL_ELEVE] = " + CODE_POSTAL_ELEVE+" [ID_MONITEUR] = " + ID_MONITEUR+" [PROFESSION_ELEVE] = " + PROFESSION_ELEVE+" [DATE_INSCRIPTION_ELEVE] = " + DATE_INSCRIPTION_ELEVE+" [RESULTAT_ELEVE_ORAL] = " + RESULTAT_ELEVE_ORAL+" [TELEPHONE_ELEVE] = " + TELEPHONE_ELEVE+" [COMMUNE_ELEVE] = " + COMMUNE_ELEVE+" [VOLUME_HORAIRE_TH_ELEVE] = " + VOLUME_HORAIRE_TH_ELEVE+" [DATE_EVAL_ELEVE] = " + DATE_EVAL_ELEVE+" [MAIL_ELEVE] = " + MAIL_ELEVE+" [VOLUME_HORAIRE_PRATIQUE_ELEVE] = " + VOLUME_HORAIRE_PRATIQUE_ELEVE+" [ID_FORMATION] = " + ID_FORMATION+" [DATE_DE_NAISS_ELEVE] = " + DATE_DE_NAISS_ELEVE+" [NOM_ELEVE] = " + NOM_ELEVE+" [TEST_VU_ELEVE] = " + TEST_VU_ELEVE;
}
}