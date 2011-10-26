package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KQUESTIONS_SYNTHESE
*/
public class KQuestions_synthese extends KObject {
private String LIBELLE_QUESTION_SYNTHESE;
private int ID_QUESTIONS_SYNTHESE;
public KQuestions_synthese() {
	super();
	keyFields="ID_QUESTIONS_SYNTHESE";
	tableName="QUESTIONS_SYNTHESE";
	//

}
public String getLIBELLE_QUESTION_SYNTHESE(){
	//return the value of LIBELLE_QUESTION_SYNTHESE
	return this.LIBELLE_QUESTION_SYNTHESE;
}

public int getID_QUESTIONS_SYNTHESE(){
	//return the value of ID_QUESTIONS_SYNTHESE
	return this.ID_QUESTIONS_SYNTHESE;
}

public void setLIBELLE_QUESTION_SYNTHESE(String aLIBELLE_QUESTION_SYNTHESE){
	//set the value of LIBELLE_QUESTION_SYNTHESE
	this.LIBELLE_QUESTION_SYNTHESE=aLIBELLE_QUESTION_SYNTHESE;
}
public void setID_QUESTIONS_SYNTHESE(int aID_QUESTIONS_SYNTHESE){
	//set the value of ID_QUESTIONS_SYNTHESE
	this.ID_QUESTIONS_SYNTHESE=aID_QUESTIONS_SYNTHESE;
}
@Override
public String toString() {
	return " [LIBELLE_QUESTION_SYNTHESE] = " + LIBELLE_QUESTION_SYNTHESE+" [ID_QUESTIONS_SYNTHESE] = " + ID_QUESTIONS_SYNTHESE;
}
}