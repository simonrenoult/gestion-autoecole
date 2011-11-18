package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KTheme_synthese
*/
public class KTheme_synthese extends KObject {
private String LIBELLE_THEME_SYNTHSE;
private long idSYNTHESE;
private KListObject<KQuestions_synthese> questions_syntheses;
private KSynthese synthese;
public KTheme_synthese() {
	super();
	keyFields="id";
	tableName="theme_synthese";
	//hasMany(KQuestions_synthese.class);belongsTo(KSynthese.class);

}
public String getLIBELLE_THEME_SYNTHSE(){
	//return the value of LIBELLE_THEME_SYNTHSE
	return this.LIBELLE_THEME_SYNTHSE;
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

public void setLIBELLE_THEME_SYNTHSE(String aLIBELLE_THEME_SYNTHSE){
	//set the value of LIBELLE_THEME_SYNTHSE
	this.LIBELLE_THEME_SYNTHSE=aLIBELLE_THEME_SYNTHSE;
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
	return " [LIBELLE_THEME_SYNTHSE] = " + LIBELLE_THEME_SYNTHSE+" [idSYNTHESE] = " + idSYNTHESE+" [questions_syntheses] = " + questions_syntheses+" [synthese] = " + synthese;
}
}