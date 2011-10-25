package kernel.tests;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KAGENDA
*/
public class KAGENDA extends KObject {
private java.sql.Date DATE_AGENDA;
private java.sql.Time HEURE;
public KAGENDA() {
	super();
	keyFields="HEURE,DATE_AGENDA";
	tableName="AGENDA";
	//

}
public java.sql.Date getDATE_AGENDA(){
	//return the value of DATE_AGENDA
	return this.DATE_AGENDA;
}

public java.sql.Time getHEURE(){
	//return the value of HEURE
	return this.HEURE;
}

public void setDATE_AGENDA(java.sql.Date aDATE_AGENDA){
	//set the value of DATE_AGENDA
	this.DATE_AGENDA=aDATE_AGENDA;
}
public void setHEURE(java.sql.Time aHEURE){
	//set the value of HEURE
	this.HEURE=aHEURE;
}
@Override
public String toString() {
	return " [DATE_AGENDA] = " + DATE_AGENDA+" [HEURE] = " + HEURE;
}
}