
package modele;

import java.sql.SQLException;
import net.ko.creator.KernelCreator;
import net.ko.ksql.KDBMysql;

public class bdd
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	public static KDBMysql	db;

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	public static KDBMysql connexion()
	{
		db = new KDBMysql("localhost" , "admin" , "admin" , "autoecole1");
		
		try
		{
			db.connect();
			// genererClasses();
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de l'établisement de la connexion à la base de données.");
			System.out.println("Pensez à vérifier que :");
			System.out.println('\t'+"1 - Votre serveur est bien allumé ;");
			System.out.println('\t'+"2 - Les identifiants de connexion sont exacts ;");
			System.out.println('\t'+"3 - Le nom de la base de donnée est le bon ;");
		}
		
		return db;
	}
	
	// ----------------------------------------- //
	// -----------------KCLASSES---------------- //
	// ----------------------------------------- //
	
	public static void genererClasses()
	{
		try
		{
			db.connect();
			KernelCreator knl = new KernelCreator();
			knl.connect(db);
			knl.createClasses();
			knl.saveAs("KClass");
		}
		catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
			System.out.println("ï¿½a marche pas");
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
			System.out.println("ï¿½a marche pas");
		}
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	public static KDBMysql getDb()
	{
		return db;
	}

	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public static void setDb(KDBMysql db)
	{
		bdd.db = db;
	}
}
