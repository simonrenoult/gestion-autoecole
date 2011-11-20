package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KThemes_synthese
*/
@SuppressWarnings("serial")
public class KThemes_synthese extends KObject {
private long ID_SYNTHESE;
private String LIBELLE_THEMES_SYNTHESE;
private int ID;
private KListObject<KQuestions_synthese> questions_syntheses;
private KSynthese synthese;
public KThemes_synthese() {
	super();
	keyFields="ID";
	tableName="themes_synthese";
	//hasMany(KQuestions_synthese.class);belongsTo(KSynthese.class);

}
public long getID_SYNTHESE(){
	//return the value of ID_SYNTHESE
	return this.ID_SYNTHESE;
}

public String getLIBELLE_THEMES_SYNTHESE(){
	//return the value of LIBELLE_THEMES_SYNTHESE
	return this.LIBELLE_THEMES_SYNTHESE;
}

public int getID(){
	//return the value of ID
	return this.ID;
}

public KListObject<KQuestions_synthese> getQuestions_syntheses(){
	//return the value of questions_syntheses
	return this.questions_syntheses;
}

public KSynthese getSynthese(){
	//return the value of synthese
	return this.synthese;
}

public void setID_SYNTHESE(long aID_SYNTHESE){
	//set the value of ID_SYNTHESE
	this.ID_SYNTHESE=aID_SYNTHESE;
}
public void setLIBELLE_THEMES_SYNTHESE(String aLIBELLE_THEMES_SYNTHESE){
	//set the value of LIBELLE_THEMES_SYNTHESE
	this.LIBELLE_THEMES_SYNTHESE=aLIBELLE_THEMES_SYNTHESE;
}
public void setID(int aID){
	//set the value of ID
	this.ID=aID;
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
	return " [ID_SYNTHESE] = " + ID_SYNTHESE+" [LIBELLE_THEMES_SYNTHESE] = " + LIBELLE_THEMES_SYNTHESE+" [ID] = " + ID+" [questions_syntheses] = " + questions_syntheses+" [synthese] = " + synthese;
}
}