package modele;


import java.sql.SQLException;
import java.util.ArrayList;

import KClass.*;

import net.ko.creator.KernelCreator;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;


public class bdd {
		
	public static KDBMysql connexion(){
		
		KDBMysql db = new KDBMysql("localhost","admin","admin","autoecole");
		
		try {
			db.connect();
			System.out.println("Connexion a "+db.getBaseName() + " reussie.");
		} catch (Exception e){}
		
		return db;
	}
}


