package modele;

import KClass.KObjectif;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataEtape
{

	// --------------------------------------//
	// --------------ATTRIBUTES--------------//
	// --------------------------------------//
	
	private KDBMysql db = bdd.connexion();
	
	// -------------------------------------//
	// -------------CONSTRUCTOR-------------//
	// -------------------------------------//
	
	public DataEtape(){}

	// -------------------------------------//
	// ----------------METHODS--------------//
	// -------------------------------------//
	
	public void connexion()
	{
		try
		{
			db.connect();
		}
		catch (Exception e)
		{}
	}
	
	public KListObject<KObjectif> chargerObjectif(int numEtape)
	{
		KListObject<KObjectif> objEtape = new KListObject<KObjectif>(KObjectif.class);
		objEtape.loadFromDb(db, "select * from OBJECTIF where ID_ETAPE = '"+numEtape+"'");
		
		return objEtape;
	}

}              