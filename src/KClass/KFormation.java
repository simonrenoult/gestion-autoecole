package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KFormation
*/
public class KFormation extends KObject {
private KListObject<KEleve> eleves;
private KListObject<KContenir> contenirs;
private String LIBELLE_FORMATION;
public KFormation() {
	super();
	keyFields="id";
	tableName="formation";
	//hasMany(KEleve.class);hasMany(KContenir.class);

}
public KListObject<KEleve> getEleves(){
	//return the value of eleves
	return this.eleves;
}

public KListObject<KContenir> getContenirs(){
	//return the value of contenirs
	return this.contenirs;
}

public String getLIBELLE_FORMATION(){
	//return the value of LIBELLE_FORMATION
	return this.LIBELLE_FORMATION;
}

public void setEleves(KListObject<KEleve> aEleves){
	//set the value of eleves
	this.eleves=aEleves;
}
public void setContenirs(KListObject<KContenir> aContenirs){
	//set the value of contenirs
	this.contenirs=aContenirs;
}
public void setLIBELLE_FORMATION(String aLIBELLE_FORMATION){
	//set the value of LIBELLE_FORMATION
	this.LIBELLE_FORMATION=aLIBELLE_FORMATION;
}
@Override
public String toString() {
	return " [eleves] = " + eleves+" [contenirs] = " + contenirs+" [LIBELLE_FORMATION] = " + LIBELLE_FORMATION;
}
}