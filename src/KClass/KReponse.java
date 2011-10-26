package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KREPONSE
*/
public class KReponse extends KObject {
private long ID_SYNTHESE;
private long ID_ELEVE;
private int ID_QUESTIONS_SYNTHESE;
private boolean ETAT_REPONSE;
private int ID_ETAT_CHAMPS;
public KReponse() {
	super();
	keyFields="ID_ELEVE,ID_SYNTHESE,ID_ETAT_CHAMPS,ID_QUESTIONS_SYNTHESE";
	tableName="REPONSE";
	//

}
public long getID_SYNTHESE(){
	//return the value of ID_SYNTHESE
	return this.ID_SYNTHESE;
}

public long getID_ELEVE(){
	//return the value of ID_ELEVE
	return this.ID_ELEVE;
}

public int getID_QUESTIONS_SYNTHESE(){
	//return the value of ID_QUESTIONS_SYNTHESE
	return this.ID_QUESTIONS_SYNTHESE;
}

public boolean getETAT_REPONSE(){
	//return the value of ETAT_REPONSE
	return this.ETAT_REPONSE;
}

public int getID_ETAT_CHAMPS(){
	//return the value of ID_ETAT_CHAMPS
	return this.ID_ETAT_CHAMPS;
}

public void setID_SYNTHESE(long aID_SYNTHESE){
	//set the value of ID_SYNTHESE
	this.ID_SYNTHESE=aID_SYNTHESE;
}
public void setID_ELEVE(long aID_ELEVE){
	//set the value of ID_ELEVE
	this.ID_ELEVE=aID_ELEVE;
}
public void setID_QUESTIONS_SYNTHESE(int aID_QUESTIONS_SYNTHESE){
	//set the value of ID_QUESTIONS_SYNTHESE
	this.ID_QUESTIONS_SYNTHESE=aID_QUESTIONS_SYNTHESE;
}
public void setETAT_REPONSE(boolean aETAT_REPONSE){
	//set the value of ETAT_REPONSE
	this.ETAT_REPONSE=aETAT_REPONSE;
}
public void setID_ETAT_CHAMPS(int aID_ETAT_CHAMPS){
	//set the value of ID_ETAT_CHAMPS
	this.ID_ETAT_CHAMPS=aID_ETAT_CHAMPS;
}
@Override
public String toString() {
	return " [ID_SYNTHESE] = " + ID_SYNTHESE+" [ID_ELEVE] = " + ID_ELEVE+" [ID_QUESTIONS_SYNTHESE] = " + ID_QUESTIONS_SYNTHESE+" [ETAT_REPONSE] = " + ETAT_REPONSE+" [ID_ETAT_CHAMPS] = " + ID_ETAT_CHAMPS;
}
}