package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
/**
* Classe KObjectif
*/
public class KObjectif extends KObject {
private KEtape etape;
private KListObject<KRealiser> realisers;
private int idCATEGORIE_I_O;
private KCategorie_i_o categorie_i_o;
private int idETAPE;
private String LIBELLE_OBJECTIF;
public KObjectif() {
	super();
	keyFields="id";
	tableName="objectif";
	hasMany(KRealiser.class);belongsTo(KEtape.class);belongsTo(KCategorie_i_o.class);

}
public KEtape getEtape(){
	//return the value of etape
	return this.etape;
}

public KListObject<KRealiser> getRealisers(){
	//return the value of realisers
	return this.realisers;
}

public int getIdCATEGORIE_I_O(){
	//return the value of idCATEGORIE_I_O
	return this.idCATEGORIE_I_O;
}

public KCategorie_i_o getCategorie_i_o(){
	//return the value of categorie_i_o
	return this.categorie_i_o;
}

public int getIdETAPE(){
	//return the value of idETAPE
	return this.idETAPE;
}

public String getLIBELLE_OBJECTIF(){
	//return the value of LIBELLE_OBJECTIF
	return this.LIBELLE_OBJECTIF;
}

public void setEtape(KEtape aEtape){
	//set the value of etape
	this.etape=aEtape;
}
public void setRealisers(KListObject<KRealiser> aRealisers){
	//set the value of realisers
	this.realisers=aRealisers;
}
public void setIdCATEGORIE_I_O(int aIdCATEGORIE_I_O){
	//set the value of idCATEGORIE_I_O
	this.idCATEGORIE_I_O=aIdCATEGORIE_I_O;
}
public void setCategorie_i_o(KCategorie_i_o aCategorie_i_o){
	//set the value of categorie_i_o
	this.categorie_i_o=aCategorie_i_o;
}
public void setIdETAPE(int aIdETAPE){
	//set the value of idETAPE
	this.idETAPE=aIdETAPE;
}
public void setLIBELLE_OBJECTIF(String aLIBELLE_OBJECTIF){
	//set the value of LIBELLE_OBJECTIF
	this.LIBELLE_OBJECTIF=aLIBELLE_OBJECTIF;
}
@Override
public String toString() {
	return " [etape] = " + etape+" [realisers] = " + realisers+" [idCATEGORIE_I_O] = " + idCATEGORIE_I_O+" [categorie_i_o] = " + categorie_i_o+" [idETAPE] = " + idETAPE+" [LIBELLE_OBJECTIF] = " + LIBELLE_OBJECTIF;
}
}