package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KAgenda
*/
public class KAgenda extends KObject {
private java.sql.Date DATE_AGENDA;
private java.sql.Time HEURE_AGENDA;
private KListObject<KAssurer_lecon> assurer_lecons;
public KAgenda() {
	super();
	keyFields="id";
	tableName="agenda";
	hasMany(KAssurer_lecon.class);

}
public java.sql.Date getDATE_AGENDA(){
	//return the value of DATE_AGENDA
	return this.DATE_AGENDA;
}

public java.sql.Time getHEURE_AGENDA(){
	//return the value of HEURE_AGENDA
	return this.HEURE_AGENDA;
}

public KListObject<KAssurer_lecon> getAssurer_lecons(){
	//return the value of assurer_lecons
	return this.assurer_lecons;
}

public void setDATE_AGENDA(java.sql.Date aDATE_AGENDA){
	//set the value of DATE_AGENDA
	this.DATE_AGENDA=aDATE_AGENDA;
}
public void setHEURE_AGENDA(java.sql.Time aHEURE_AGENDA){
	//set the value of HEURE_AGENDA
	this.HEURE_AGENDA=aHEURE_AGENDA;
}
public void setAssurer_lecons(KListObject<KAssurer_lecon> aAssurer_lecons){
	//set the value of assurer_lecons
	this.assurer_lecons=aAssurer_lecons;
}
@Override
public String toString() {
	return " [DATE_AGENDA] = " + DATE_AGENDA+" [HEURE_AGENDA] = " + HEURE_AGENDA+" [assurer_lecons] = " + assurer_lecons;
}
}