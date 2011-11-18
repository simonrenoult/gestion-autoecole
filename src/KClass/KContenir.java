package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KContenir
*/
public class KContenir extends KObject {
private int idFORMATION;
private KEtape etape;
private int idETAPE;
private KFormation formation;
public KContenir() {
	super();
	keyFields="idETAPE,idFORMATION";
	tableName="contenir";
	belongsTo(KFormation.class);belongsTo(KEtape.class);

}
public int getIdFORMATION(){
	//return the value of idFORMATION
	return this.idFORMATION;
}

public KEtape getEtape(){
	//return the value of etape
	return this.etape;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public KFormation getFormation(){
	//return the value of formation
	return this.formation;
}

public void setIdFORMATION(int aIdFORMATION){
	//set the value of idFORMATION
	this.idFORMATION=aIdFORMATION;
}
public void setEtape(KEtape aEtape){
	//set the value of etape
	this.etape=aEtape;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setFormation(KFormation aFormation){
	//set the value of formation
	this.formation=aFormation;
}
@Override
public String toString() {
	return " [idFORMATION] = " + idFORMATION+" [etape] = " + etape+" [idETAPE] = " + idETAPE+" [formation] = " + formation;
}
}