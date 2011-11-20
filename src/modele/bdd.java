
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
		db = new KDBMysql("localhost" , "admin" , "admin" , "autoecole");
		
		try
		{
			db.connect();
			// genererClasses();
		}
		catch (Exception e)
		{
			System.out.println("Pensez à allumer votre serveur !");
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
