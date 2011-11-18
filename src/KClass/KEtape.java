package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KEtape
*/
public class KEtape extends KObject {
private KListObject<KContenir> contenirs;
private KListObject<KSuperviser> supervisers;
private String LIBELLE_ETAPE;
private KListObject<KObjectif> objectifs;
private KListObject<KSynthese> syntheses;
public KEtape() {
	super();
	keyFields="id";
	tableName="etape";
	//hasMany(KSynthese.class);hasMany(KSuperviser.class);hasMany(KObjectif.class);hasMany(KContenir.class);

}
public KListObject<KContenir> getContenirs(){
	//return the value of contenirs
	return this.contenirs;
}

public KListObject<KSuperviser> getSupervisers(){
	//return the value of supervisers
	return this.supervisers;
}

public String getLIBELLE_ETAPE(){
	//return the value of LIBELLE_ETAPE
	return this.LIBELLE_ETAPE;
}

public KListObject<KObjectif> getObjectifs(){
	//return the value of objectifs
	return this.objectifs;
}

public KListObject<KSynthese> getSyntheses(){
	//return the value of syntheses
	return this.syntheses;
}

public void setContenirs(KListObject<KContenir> aContenirs){
	//set the value of contenirs
	this.contenirs=aContenirs;
}
public void setSupervisers(KListObject<KSuperviser> aSupervisers){
	//set the value of supervisers
	this.supervisers=aSupervisers;
}
public void setLIBELLE_ETAPE(String aLIBELLE_ETAPE){
	//set the value of LIBELLE_ETAPE
	this.LIBELLE_ETAPE=aLIBELLE_ETAPE;
}
public void setObjectifs(KListObject<KObjectif> aObjectifs){
	//set the value of objectifs
	this.objectifs=aObjectifs;
}
public void setSyntheses(KListObject<KSynthese> aSyntheses){
	//set the value of syntheses
	this.syntheses=aSyntheses;
}
@Override
public String toString() {
	return " [contenirs] = " + contenirs+" [supervisers] = " + supervisers+" [LIBELLE_ETAPE] = " + LIBELLE_ETAPE+" [objectifs] = " + objectifs+" [syntheses] = " + syntheses;
}
}