package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KREPONSE
*/
public class KREPONSE extends KObject {
private long idELEVE;
private boolean ETAT_REPONSE;
private int idQUESTIONS_SYNTHESE;
private long idSYNTHESE;
private int idETAT_CHAMPS;
public KREPONSE() {
	super();
	keyFields="idELEVE,idSYNTHESE,idETAT_CHAMPS,idQUESTIONS_SYNTHESE";
	tableName="REPONSE";
	//

}
public long getIdELEVE(){
	//return the value of idELEVE
	return this.idELEVE;
}

public boolean getETAT_REPONSE(){
	//return the value of ETAT_REPONSE
	return this.ETAT_REPONSE;
}

public int getIdQUESTIONS_SYNTHESE(){
	//return the value of idQUESTIONS_SYNTHESE
	return this.idQUESTIONS_SYNTHESE;
}

public long getIdSYNTHESE(){
	//return the value of idSYNTHESE
	return this.idSYNTHESE;
}

public int getIdETAT_CHAMPS(){
	//return the value of idETAT_CHAMPS
	return this.idETAT_CHAMPS;
}

public void setIdELEVE(long aIdELEVE){
	//set the value of idELEVE
	this.idELEVE=aIdELEVE;
}
public void setETAT_REPONSE(boolean aETAT_REPONSE){
	//set the value of ETAT_REPONSE
	this.ETAT_REPONSE=aETAT_REPONSE;
}
public void setIdQUESTIONS_SYNTHESE(int aIdQUESTIONS_SYNTHESE){
	//set the value of idQUESTIONS_SYNTHESE
	this.idQUESTIONS_SYNTHESE=aIdQUESTIONS_SYNTHESE;
}
public void setIdSYNTHESE(long aIdSYNTHESE){
	//set the value of idSYNTHESE
	this.idSYNTHESE=aIdSYNTHESE;
}
public void setIdETAT_CHAMPS(int aIdETAT_CHAMPS){
	//set the value of idETAT_CHAMPS
	this.idETAT_CHAMPS=aIdETAT_CHAMPS;
}
@Override
public String toString() {
	return " [idELEVE] = " + idELEVE+" [ETAT_REPONSE] = " + ETAT_REPONSE+" [idQUESTIONS_SYNTHESE] = " + idQUESTIONS_SYNTHESE+" [idSYNTHESE] = " + idSYNTHESE+" [idETAT_CHAMPS] = " + idETAT_CHAMPS;
}
}