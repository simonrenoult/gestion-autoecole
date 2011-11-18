package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KQuestions_synthese
*/
public class KQuestions_synthese extends KObject {
private String LIBELLE_QUESTION_SYNTHESE;
private KListObject<KReponse> reponses;
private long idSYNTHESE;
private KSynthese synthese;
public KQuestions_synthese() {
	super();
	keyFields="id";
	tableName="questions_synthese";
	belongsTo(KSynthese.class);hasMany(KReponse.class);

}
public String getLIBELLE_QUESTION_SYNTHESE(){
	//return the value of LIBELLE_QUESTION_SYNTHESE
	return this.LIBELLE_QUESTION_SYNTHESE;
}

public KListObject<KReponse> getReponses(){
	//return the value of reponses
	return this.reponses;
}

public long getIdSYNTHESE(){
	//return the value of idSYNTHESE
	return this.idSYNTHESE;
}

public KSynthese getSynthese(){
	//return the value of synthese
	return this.synthese;
}

public void setLIBELLE_QUESTION_SYNTHESE(String aLIBELLE_QUESTION_SYNTHESE){
	//set the value of LIBELLE_QUESTION_SYNTHESE
	this.LIBELLE_QUESTION_SYNTHESE=aLIBELLE_QUESTION_SYNTHESE;
}
public void setReponses(KListObject<KReponse> aReponses){
	//set the value of reponses
	this.reponses=aReponses;
}
public void setIdSYNTHESE(long aIdSYNTHESE){
	//set the value of idSYNTHESE
	this.idSYNTHESE=aIdSYNTHESE;
}
public void setSynthese(KSynthese aSynthese){
	//set the value of synthese
	this.synthese=aSynthese;
}
@Override
public String toString() {
	return " [LIBELLE_QUESTION_SYNTHESE] = " + LIBELLE_QUESTION_SYNTHESE+" [reponses] = " + reponses+" [idSYNTHESE] = " + idSYNTHESE+" [synthese] = " + synthese;
}
}