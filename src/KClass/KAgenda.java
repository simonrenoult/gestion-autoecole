package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KAgenda
*/
public class KAgenda extends KObject {
private String LIBELLE_AGENDA;
private KListObject<KAssurer_lecon> assurer_lecons;
public KAgenda() {
	super();
	keyFields="id";
	tableName="agenda";
	//hasMany(KAssurer_lecon.class);

}
public String getLIBELLE_AGENDA(){
	//return the value of LIBELLE_AGENDA
	return this.LIBELLE_AGENDA;
}

public KListObject<KAssurer_lecon> getAssurer_lecons(){
	//return the value of assurer_lecons
	return this.assurer_lecons;
}

public void setLIBELLE_AGENDA(String aLIBELLE_AGENDA){
	//set the value of LIBELLE_AGENDA
	this.LIBELLE_AGENDA=aLIBELLE_AGENDA;
}
public void setAssurer_lecons(KListObject<KAssurer_lecon> aAssurer_lecons){
	//set the value of assurer_lecons
	this.assurer_lecons=aAssurer_lecons;
}
@Override
public String toString() {
	return " [LIBELLE_AGENDA] = " + LIBELLE_AGENDA+" [assurer_lecons] = " + assurer_lecons;
}
}