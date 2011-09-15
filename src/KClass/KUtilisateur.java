package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KUtilisateur
*/
public class KUtilisateur extends KObject {
private KListObject<KProjet> projets;
private String prenom;
private java.sql.Date dateInscription;
private KListObject<KUtilisateur_droit> utilisateur_droits;
private int age;
private String nom;
public KUtilisateur() {
	super();
	keyFields="id";
	tableName="utilisateur";
	//hasMany(KUtilisateur_droit.class);hasMany(KProjet.class);

}
public KListObject<KProjet> getProjets(){
	//return the value of projets
	return this.projets;
}

public String getPrenom(){
	//return the value of prenom
	return this.prenom;
}

public java.sql.Date getDateInscription(){
	//return the value of dateInscription
	return this.dateInscription;
}

public KListObject<KUtilisateur_droit> getUtilisateur_droits(){
	//return the value of utilisateur_droits
	return this.utilisateur_droits;
}

public int getAge(){
	//return the value of age
	return this.age;
}

public String getNom(){
	//return the value of nom
	return this.nom;
}

public void setProjets(KListObject<KProjet> aProjets){
	//set the value of projets
	this.projets=aProjets;
}
public void setPrenom(String aPrenom){
	//set the value of prenom
	this.prenom=aPrenom;
}
public void setDateInscription(java.sql.Date aDateInscription){
	//set the value of dateInscription
	this.dateInscription=aDateInscription;
}
public void setUtilisateur_droits(KListObject<KUtilisateur_droit> aUtilisateur_droits){
	//set the value of utilisateur_droits
	this.utilisateur_droits=aUtilisateur_droits;
}
public void setAge(int aAge){
	//set the value of age
	this.age=aAge;
}
public void setNom(String aNom){
	//set the value of nom
	this.nom=aNom;
}
@Override
public String toString() {
	return " [projets] = " + projets+" [prenom] = " + prenom+" [dateInscription] = " + dateInscription+" [utilisateur_droits] = " + utilisateur_droits+" [age] = " + age+" [nom] = " + nom;
}
}