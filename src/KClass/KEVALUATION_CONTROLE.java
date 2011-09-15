package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KEVALUATION_CONTROLE
*/
public class KEVALUATION_CONTROLE extends KObject {
private String LIBELLE_EVALUATION_CONTROLE;
public KEVALUATION_CONTROLE() {
	super();
	keyFields="id";
	tableName="EVALUATION_CONTROLE";
	//

}
public String getLIBELLE_EVALUATION_CONTROLE(){
	//return the value of LIBELLE_EVALUATION_CONTROLE
	return this.LIBELLE_EVALUATION_CONTROLE;
}

public void setLIBELLE_EVALUATION_CONTROLE(String aLIBELLE_EVALUATION_CONTROLE){
	//set the value of LIBELLE_EVALUATION_CONTROLE
	this.LIBELLE_EVALUATION_CONTROLE=aLIBELLE_EVALUATION_CONTROLE;
}
@Override
public String toString() {
	return " [LIBELLE_EVALUATION_CONTROLE] = " + LIBELLE_EVALUATION_CONTROLE;
}
}