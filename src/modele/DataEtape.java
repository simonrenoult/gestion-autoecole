package modele;

import KClass.KObjectif;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
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
	
	public KListObject<KObjectif> chargerObjectif(int numEtape){
		KListObject<KObjectif> objEtape = new KListObject<KObjectif>(KObjectif.class);
		objEtape.loadFromDb(db, "select * from objectif where idEtape = '"+numEtape+"'");
		
		return objEtape;
	}

}              