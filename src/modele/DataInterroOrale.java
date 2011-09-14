package modele;

import KClass.KCATEGORIE_I_O;
import KClass.KOBJECTIF;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataInterroOrale {
	
	private KDBMysql db = bdd.connexion();
	
	public DataInterroOrale(){}
	
	public KListObject<KCATEGORIE_I_O> getCategories(){
		KListObject<KCATEGORIE_I_O> list = new KListObject<KCATEGORIE_I_O>(KCATEGORIE_I_O.class);
		
		list.loadFromDb(db, "select * from CATEGORIE_I_O order by id");
		
		return list;
	}

	public KListObject<KOBJECTIF> getObjectifsForCategorie(int categorie) {
		
		KListObject<KOBJECTIF> list = new KListObject<KOBJECTIF>(KOBJECTIF.class);
		
		list.loadFromDb(db, "select * from OBJECTIF where idEtape = 5 and idCATEGORIE = "+categorie+" order by id");
		System.out.println(list);
		return list;
	}
	
}              