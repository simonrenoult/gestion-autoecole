package modele;

import java.sql.SQLException;
import net.ko.creator.KernelCreator;
import net.ko.ksql.KDBMysql;

public class BDD
{

	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //

	public static KDBMysql	db;

	private static String	host	= "localhost";
	private static String	login	= "admin";
	private static String	mdp		= "admin";
	private static String	bdd		= "autoecole3";

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	public static KDBMysql connexion()
	{
		db = new KDBMysql(host, login, mdp, bdd);

		try
		{
			db.connect();
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de l'etablisement de la connexion a la base de donnees.");
			System.out.println("Pensez a verifier que :");
			System.out.println('\t' + "1 - Votre serveur est bien allume ;");
			System.out.println('\t' + "2 - Les identifiants de connexion sont exacts ;");
			System.out.println('\t' + "3 - Le nom de la base de donnee est le bon ;");
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
			System.out.println("Classes non trouvees a la generation des KClasses.");
		}
		catch (SQLException e1)
		{
			System.out.println("Erreur SQL a la generation des KClasses.");
		}
	}

	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	public static KDBMysql getDb()
	{
		return db;
	}

	public static String getHost()
	{
		return host;
	}

	public static String getLogin()
	{
		return login;
	}

	public static String getMdp()
	{
		return mdp;
	}

	public static String getBdd()
	{
		return bdd;
	}

	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //

	public static void setDb(KDBMysql db)
	{
		BDD.db = db;
	}

	public static void setHost(String host)
	{
		BDD.host = host;
	}

	public static void setLogin(String login)
	{
		BDD.login = login;
	}

	public static void setMdp(String mdp)
	{
		BDD.mdp = mdp;
	}

	public static void setBdd(String bdd)
	{
		BDD.bdd = bdd;
	}
}
