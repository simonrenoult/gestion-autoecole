package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSynthese
*/
@SuppressWarnings("serial")
public class KSynthese extends KObject {
private KListObject<KPasser> passers;
private KEtape etape;
private int idETAPE;
private KListObject<KQuestions_synthese> questions_syntheses;
private String LIBELLE_SYNTHESE;
public KSynthese() {
	super();
	keyFields="id";
	tableName="synthese";
	hasMany(KQuestions_synthese.class);hasMany(KPasser.class);belongsTo(KEtape.class);

}
public KListObject<KPasser> getPassers(){
	//return the value of passers
	return this.passers;
}

public KEtape getEtape(){
	//return the value of etape
	return this.etape;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public KListObject<KQuestions_synthese> getQuestions_syntheses(){
	//return the value of questions_syntheses
	return this.questions_syntheses;
}

public String getLIBELLE_SYNTHESE(){
	//return the value of LIBELLE_SYNTHESE
	return this.LIBELLE_SYNTHESE;
}

public void setPassers(KListObject<KPasser> aPassers){
	//set the value of passers
	this.passers=aPassers;
}
public void setEtape(KEtape aEtape){
	//set the value of etape
	this.etape=aEtape;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setQuestions_syntheses(KListObject<KQuestions_synthese> aQuestions_syntheses){
	//set the value of questions_syntheses
	this.questions_syntheses=aQuestions_syntheses;
}
public void setLIBELLE_SYNTHESE(String aLIBELLE_SYNTHESE){
	//set the value of LIBELLE_SYNTHESE
	this.LIBELLE_SYNTHESE=aLIBELLE_SYNTHESE;
}
@Override
public String toString() {
	return " [passers] = " + passers+" [etape] = " + etape+" [idETAPE] = " + idETAPE+" [questions_syntheses] = " + questions_syntheses+" [LIBELLE_SYNTHESE] = " + LIBELLE_SYNTHESE;
}
}