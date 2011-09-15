package modele;

import KClass.KOBJECTIF;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataEtape {

	private KDBMysql db = bdd.connexion();

	public DataEtape(){}

	public void connexion(){


		try {
			db.connect();
			System.out.println("Connexion a "+db.getBaseName());
		} catch (Exception e){}
	}
	
	public KListObject<KOBJECTIF> chargerObjectif(int numEtape){
		KListObject<KOBJECTIF> objEtape = new KListObject<KOBJECTIF>(KOBJECTIF.class);
		objEtape.loadFromDb(db, "select * from OBJECTIF where idEtape = '"+numEtape+"'");
		
		return objEtape;
	}

}              