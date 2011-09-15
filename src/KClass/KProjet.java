package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KProjet
*/
public class KProjet extends KObject {
private java.sql.Date dateCreation;
private int idUtilisateur;
private KUtilisateur utilisateur;
private String nom;
public KProjet() {
	super();
	keyFields="";
	tableName="projet";
	//belongsTo(KUtilisateur.class);

}
public java.sql.Date getDateCreation(){
	//return the value of dateCreation
	return this.dateCreation;
}

public int getIdUtilisateur(){
	//return the value of idUtilisateur
	return this.idUtilisateur;
}

public KUtilisateur getUtilisateur(){
	//return the value of utilisateur
	return this.utilisateur;
}

public String getNom(){
	//return the value of nom
	return this.nom;
}

public void setDateCreation(java.sql.Date aDateCreation){
	//set the value of dateCreation
	this.dateCreation=aDateCreation;
}
public void setIdUtilisateur(int aIdUtilisateur){
	//set the value of idUtilisateur
	this.idUtilisateur=aIdUtilisateur;
}
public void setUtilisateur(KUtilisateur aUtilisateur){
	//set the value of utilisateur
	this.utilisateur=aUtilisateur;
}
public void setNom(String aNom){
	//set the value of nom
	this.nom=aNom;
}
@Override
public String toString() {
	return " [dateCreation] = " + dateCreation+" [idUtilisateur] = " + idUtilisateur+" [utilisateur] = " + utilisateur+" [nom] = " + nom;
}
}