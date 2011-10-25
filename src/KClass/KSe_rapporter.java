package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KSe_rapporter
*/
public class KSe_rapporter extends KObject {
private int idETAPE;
private int idEVALUATION_CONTROLE;
public KSe_rapporter() {
	super();
	keyFields="idETAPE,idEVALUATION_CONTROLE";
	tableName="SE_RAPPORTER";
	//

}
public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public int getIdEVALUATION_CONTROLE(){
	//return the value of idEVALUATION_CONTROLE
	return this.idEVALUATION_CONTROLE;
}

public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setIdEVALUATION_CONTROLE(int aIdEVALUATION_CONTROLE){
	//set the value of idEVALUATION_CONTROLE
	this.idEVALUATION_CONTROLE=aIdEVALUATION_CONTROLE;
}
@Override
public String toString() {
	return " [idETAPE] = " + idETAPE+" [idEVALUATION_CONTROLE] = " + idEVALUATION_CONTROLE;
}
}