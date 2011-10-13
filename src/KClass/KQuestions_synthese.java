package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KQuestions_synthese
*/
public class KQuestions_synthese extends KObject {
private String LIBELLE_QUESTION_SYNTHESE;
public KQuestions_synthese() {
	super();
	keyFields="id";
	tableName="questions_synthese";
	//

}
public String getLIBELLE_QUESTION_SYNTHESE(){
	//return the value of LIBELLE_QUESTION_SYNTHESE
	return this.LIBELLE_QUESTION_SYNTHESE;
}

public void setLIBELLE_QUESTION_SYNTHESE(String aLIBELLE_QUESTION_SYNTHESE){
	//set the value of LIBELLE_QUESTION_SYNTHESE
	this.LIBELLE_QUESTION_SYNTHESE=aLIBELLE_QUESTION_SYNTHESE;
}
@Override
public String toString() {
	return " [LIBELLE_QUESTION_SYNTHESE] = " + LIBELLE_QUESTION_SYNTHESE;
}
}