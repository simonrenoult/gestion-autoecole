package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCompte
*/
public class KCompte extends KObject {
private String MOT_DE_PASSE_UTILISATEUR;
public KCompte() {
	super();
	keyFields="id";
	tableName="compte";
	//

}
public String getMOT_DE_PASSE_UTILISATEUR(){
	//return the value of MOT_DE_PASSE_UTILISATEUR
	return this.MOT_DE_PASSE_UTILISATEUR;
}

public void setMOT_DE_PASSE_UTILISATEUR(String aMOT_DE_PASSE_UTILISATEUR){
	//set the value of MOT_DE_PASSE_UTILISATEUR
	this.MOT_DE_PASSE_UTILISATEUR=aMOT_DE_PASSE_UTILISATEUR;
}
@Override
public String toString() {
	return " [MOT_DE_PASSE_UTILISATEUR] = " + MOT_DE_PASSE_UTILISATEUR;
}
}