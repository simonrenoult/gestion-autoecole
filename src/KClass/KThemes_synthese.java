package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KThemes_synthese
*/
public class KThemes_synthese extends KObject {
private String LIBELLE_THEMES_SYNTHESE;
private long idSYNTHESE;
private KListObject<KQuestions_synthese> questions_syntheses;
private KSynthese synthese;
public KThemes_synthese() {
	super();
	keyFields="id";
	tableName="themes_synthese";
	//hasMany(KQuestions_synthese.class);belongsTo(KSynthese.class);

}
public String getLIBELLE_THEMES_SYNTHESE(){
	//return the value of LIBELLE_THEMES_SYNTHESE
	return this.LIBELLE_THEMES_SYNTHESE;
}

public long getIdSYNTHESE(){
	//return the value of idSYNTHESE
	return this.idSYNTHESE;
}

public KListObject<KQuestions_synthese> getQuestions_syntheses(){
	//return the value of questions_syntheses
	return this.questions_syntheses;
}

public KSynthese getSynthese(){
	//return the value of synthese
	return this.synthese;
}

public void setLIBELLE_THEMES_SYNTHESE(String aLIBELLE_THEMES_SYNTHESE){
	//set the value of LIBELLE_THEMES_SYNTHESE
	this.LIBELLE_THEMES_SYNTHESE=aLIBELLE_THEMES_SYNTHESE;
}
public void setIdSYNTHESE(long aIdSYNTHESE){
	//set the value of idSYNTHESE
	this.idSYNTHESE=aIdSYNTHESE;
}
public void setQuestions_syntheses(KListObject<KQuestions_synthese> aQuestions_syntheses){
	//set the value of questions_syntheses
	this.questions_syntheses=aQuestions_syntheses;
}
public void setSynthese(KSynthese aSynthese){
	//set the value of synthese
	this.synthese=aSynthese;
}
@Override
public String toString() {
	return " [LIBELLE_THEMES_SYNTHESE] = " + LIBELLE_THEMES_SYNTHESE+" [idSYNTHESE] = " + idSYNTHESE+" [questions_syntheses] = " + questions_syntheses+" [synthese] = " + synthese;
}
}