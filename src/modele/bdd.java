package modele;

import java.sql.SQLException;

import net.ko.creator.KernelCreator;
import net.ko.ksql.KDBMysql;


public class bdd {
		
	public static KDBMysql db;
	
	public static KDBMysql connexion(){
		
		db = new KDBMysql("localhost","admin","admin","autoecole.0.2");
		
		try {
			db.connect();
			//genererClasses();
			
			//System.out.println("Connexion a "+db.getBaseName() + " reussie.");
		} catch (Exception e){}
		
		return db;
	}
	
	
	
	public static void genererClasses(){
		
		
		try {
			db.connect();
			KernelCreator knl = new KernelCreator();
			knl.connect(db);
			knl.createClasses();
			knl.saveAs("KClass");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("ça marche pas");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("ça marche pas");
		}
	}
}


