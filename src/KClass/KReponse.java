package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KReponse
*/
public class KReponse extends KObject {
private KEleve eleve;
private int idQUESTION_SYNTHESE;
private long idELEVE;
private int ETAT_REPONSE_1;
private int ETAT_REPONSE_2;
private KQuestion_synthese question_synthese;
public KReponse() {
	super();
	keyFields="idELEVE,idQUESTION_SYNTHESE";
	tableName="reponse";
	//belongsTo(KQuestion_synthese.class);belongsTo(KEleve.class);

}
public KEleve getEleve(){
	//return the value of eleve
	return this.eleve;
}

public int getIdQUESTION_SYNTHESE(){
	//return the value of idQUESTION_SYNTHESE
	return this.idQUESTION_SYNTHESE;
}

public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public int getETAT_REPONSE_1(){
	//return the value of ETAT_REPONSE_1
	return this.ETAT_REPONSE_1;
}

public int getETAT_REPONSE_2(){
	//return the value of ETAT_REPONSE_2
	return this.ETAT_REPONSE_2;
}

public KQuestion_synthese getQuestion_synthese(){
	//return the value of question_synthese
	return this.question_synthese;
}

public void setEleve(KEleve aEleve){
	//set the value of eleve
	this.eleve=aEleve;
}
public void setIdQUESTION_SYNTHESE(int aIdQUESTION_SYNTHESE){
	//set the value of idQUESTION_SYNTHESE
	this.idQUESTION_SYNTHESE=aIdQUESTION_SYNTHESE;
}
public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setETAT_REPONSE_1(int aETAT_REPONSE_1){
	//set the value of ETAT_REPONSE_1
	this.ETAT_REPONSE_1=aETAT_REPONSE_1;
}
public void setETAT_REPONSE_2(int aETAT_REPONSE_2){
	//set the value of ETAT_REPONSE_2
	this.ETAT_REPONSE_2=aETAT_REPONSE_2;
}
public void setQuestion_synthese(KQuestion_synthese aQuestion_synthese){
	//set the value of question_synthese
	this.question_synthese=aQuestion_synthese;
}
@Override
public String toString() {
	return " [eleve] = " + eleve+" [idQUESTION_SYNTHESE] = " + idQUESTION_SYNTHESE+" [idELEVE] = " + idELEVE+" [ETAT_REPONSE_1] = " + ETAT_REPONSE_1+" [ETAT_REPONSE_2] = " + ETAT_REPONSE_2+" [question_synthese] = " + question_synthese;
}
}