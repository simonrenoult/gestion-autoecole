package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KQUESTIONS_SYNTHESE
*/
public class KQUESTIONS_SYNTHESE extends KObject {
private String LIBELLE_QUESTION_SYNTHESE;
public KQUESTIONS_SYNTHESE() {
	super();
	keyFields="id";
	tableName="QUESTIONS_SYNTHESE";
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