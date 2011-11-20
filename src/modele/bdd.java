
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
			System.out.println("Erreur lors de l'�tablisement de la connexion � la base de donn�es.");
			System.out.println("Pensez � v�rifier que :");
			System.out.println('\t'+"1 - Votre serveur est bien allum� ;");
			System.out.println('\t'+"2 - Les identifiants de connexion sont exacts ;");
			System.out.println('\t'+"3 - Le nom de la base de donn�e est le bon ;");
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
			System.out.println("�a marche pas");
		}
		catch (SQLException e1)
		{
			e1.printStackTrace();
			System.out.println("�a marche pas");
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
