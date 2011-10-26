package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KCOMPTE
*/
public class KCompte extends KObject {
private String MOT_DE_PASSE_UTILISATEUR;
private int ID_COMPTE;
private long ID_COMPTE_UTILISATEUR;
public KCompte() {
	super();
	keyFields="ID_COMPTE";
	tableName="COMPTE";
	//

}
public String getMOT_DE_PASSE_UTILISATEUR(){
	//return the value of MOT_DE_PASSE_UTILISATEUR
	return this.MOT_DE_PASSE_UTILISATEUR;
}

public int getID_COMPTE(){
	//return the value of ID_COMPTE
	return this.ID_COMPTE;
}

public long getID_COMPTE_UTILISATEUR(){
	//return the value of ID_COMPTE_UTILISATEUR
	return this.ID_COMPTE_UTILISATEUR;
}

public void setMOT_DE_PASSE_UTILISATEUR(String aMOT_DE_PASSE_UTILISATEUR){
	//set the value of MOT_DE_PASSE_UTILISATEUR
	this.MOT_DE_PASSE_UTILISATEUR=aMOT_DE_PASSE_UTILISATEUR;
}
public void setID_COMPTE(int aID_COMPTE){
	//set the value of ID_COMPTE
	this.ID_COMPTE=aID_COMPTE;
}
public void setID_COMPTE_UTILISATEUR(long aID_COMPTE_UTILISATEUR){
	//set the value of ID_COMPTE_UTILISATEUR
	this.ID_COMPTE_UTILISATEUR=aID_COMPTE_UTILISATEUR;
}
@Override
public String toString() {
	return " [MOT_DE_PASSE_UTILISATEUR] = " + MOT_DE_PASSE_UTILISATEUR+" [ID_COMPTE] = " + ID_COMPTE+" [ID_COMPTE_UTILISATEUR] = " + ID_COMPTE_UTILISATEUR;
}
}