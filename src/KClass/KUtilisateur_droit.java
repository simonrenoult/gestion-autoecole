package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KUtilisateur_droit
*/
public class KUtilisateur_droit extends KObject {
private int idDroit;
private int idUtilisateur;
private KUtilisateur utilisateur;
private KDroit droit;
public KUtilisateur_droit() {
	super();
	keyFields="idDroit,idUtilisateur";
	tableName="utilisateur_droit";
	//belongsTo(KUtilisateur.class);belongsTo(KDroit.class);

}
public int getIdDroit(){
	//return the value of idDroit
	return this.idDroit;
}

public int getIdUtilisateur(){
	//return the value of idUtilisateur
	return this.idUtilisateur;
}

public KUtilisateur getUtilisateur(){
	//return the value of utilisateur
	return this.utilisateur;
}

public KDroit getDroit(){
	//return the value of droit
	return this.droit;
}

public void setIdDroit(int aIdDroit){
	//set the value of idDroit
	this.idDroit=aIdDroit;
}
public void setIdUtilisateur(int aIdUtilisateur){
	//set the value of idUtilisateur
	this.idUtilisateur=aIdUtilisateur;
}
public void setUtilisateur(KUtilisateur aUtilisateur){
	//set the value of utilisateur
	this.utilisateur=aUtilisateur;
}
public void setDroit(KDroit aDroit){
	//set the value of droit
	this.droit=aDroit;
}
@Override
public String toString() {
	return " [idDroit] = " + idDroit+" [idUtilisateur] = " + idUtilisateur+" [utilisateur] = " + utilisateur+" [droit] = " + droit;
}
}