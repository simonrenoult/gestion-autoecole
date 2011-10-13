package modele;

import KClass.KCategorie_i_o;
import KClass.KObjectif;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataInterroOrale {

	private KDBMysql db = bdd.connexion();

	public DataInterroOrale() {
	}

	public KListObject<KCategorie_i_o> getCategories() {
		KListObject<KCategorie_i_o> list = new KListObject<KCategorie_i_o>(
				KCategorie_i_o.class);

		list.loadFromDb(db, "select * from categorie_i_o order by id");

		return list;
	}

	public KListObject<KObjectif> getObjectifsForCategorie(int categorie) {

		KListObject<KObjectif> list = new KListObject<KObjectif>(
				KObjectif.class);

		list.loadFromDb(db,
				"select * from objectif where idEtape = 5 and idCATEGORIE = "
						+ categorie + " order by id");
		System.out.println(list);
		return list;
	}

}