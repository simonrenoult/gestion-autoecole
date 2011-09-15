package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KDroit
*/
public class KDroit extends KObject {
private KListObject<KUtilisateur_droit> utilisateur_droits;
private String libelle;
public KDroit() {
	super();
	keyFields="id";
	tableName="droit";
	//hasMany(KUtilisateur_droit.class);

}
public KListObject<KUtilisateur_droit> getUtilisateur_droits(){
	//return the value of utilisateur_droits
	return this.utilisateur_droits;
}

public String getLibelle(){
	//return the value of libelle
	return this.libelle;
}

public void setUtilisateur_droits(KListObject<KUtilisateur_droit> aUtilisateur_droits){
	//set the value of utilisateur_droits
	this.utilisateur_droits=aUtilisateur_droits;
}
public void setLibelle(String aLibelle){
	//set the value of libelle
	this.libelle=aLibelle;
}
@Override
public String toString() {
	return " [utilisateur_droits] = " + utilisateur_droits+" [libelle] = " + libelle;
}
}