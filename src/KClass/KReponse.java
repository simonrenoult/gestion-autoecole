package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KReponse
*/
public class KReponse extends KObject {
private KQuestions_synthese questions_synthese;
private KEleve eleve;
private long idELEVE;
private int ETAT_REPONSE_1;
private int ETAT_REPONSE_2;
private int idQUESTIONS_SYNTHESE;
public KReponse() {
	super();
	keyFields="idELEVE,idQUESTIONS_SYNTHESE";
	tableName="reponse";
	//belongsTo(KQuestions_synthese.class);belongsTo(KEleve.class);

}
public KQuestions_synthese getQuestions_synthese(){
	//return the value of questions_synthese
	return this.questions_synthese;
}

public KEleve getEleve(){
	//return the value of eleve
	return this.eleve;
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

public int getIdQUESTIONS_SYNTHESE(){
	//return the value of idQUESTIONS_SYNTHESE
	return this.idQUESTIONS_SYNTHESE;
}

public void setQuestions_synthese(KQuestions_synthese aQuestions_synthese){
	//set the value of questions_synthese
	this.questions_synthese=aQuestions_synthese;
}
public void setEleve(KEleve aEleve){
	//set the value of eleve
	this.eleve=aEleve;
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
public void setIdQUESTIONS_SYNTHESE(int aIdQUESTIONS_SYNTHESE){
	//set the value of idQUESTIONS_SYNTHESE
	this.idQUESTIONS_SYNTHESE=aIdQUESTIONS_SYNTHESE;
}
@Override
public String toString() {
	return " [questions_synthese] = " + questions_synthese+" [eleve] = " + eleve+" [idELEVE] = " + idELEVE+" [ETAT_REPONSE_1] = " + ETAT_REPONSE_1+" [ETAT_REPONSE_2] = " + ETAT_REPONSE_2+" [idQUESTIONS_SYNTHESE] = " + idQUESTIONS_SYNTHESE;
}
}