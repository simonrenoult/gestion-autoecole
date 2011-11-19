
package modele.etape;

import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import KClass.KObjectif;
import KClass.KRealiser;

public class DataObjectifs
{
	// ----------------------------------------- //
	// ----------------ATRIBUTS----------------- //
	// ----------------------------------------- //
	
	private KDBMysql				db;
	
	private KListObject<KObjectif>	objectifs;
	private KListObject<KRealiser>	obsEtEtats;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataObjectifs(KDBMysql connexion, Integer numEtape)
	{
		this.db = connexion;
		this.objectifs = chargerIntitulesObjectifs(numEtape);
	}
	
	// ----------------------------------------- //
	// -------------INITIALISEURS--------------- //
	// ----------------------------------------- //
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	private KListObject<KObjectif> chargerIntitulesObjectifs(int numEtape)
	{
		KListObject<KObjectif> tmp = new KListObject<KObjectif>(KObjectif.class);
		tmp.loadFromDb(db , "select * from objectif where idETAPE = '" + numEtape + "'");
		
		return tmp;
	}
	
	public KListObject<KRealiser> chargerObsEtEtat(int numEleve)
	{
		KListObject<KRealiser> tmp = new KListObject<KRealiser>(KRealiser.class);
		tmp.loadFromDb(db , "SELECT * FROM realiser WHERE idELEVE = '" + numEleve + "' ");
		
		return tmp;
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public KListObject<KObjectif> getObjectifs()
	{
		return objectifs;
	}
	
	public KListObject<KRealiser> getObsEtEtats()
	{
		return obsEtEtats;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public void setObjectifs(KListObject<KObjectif> objectifs)
	{
		this.objectifs = objectifs;
	}
	
	public void setObsEtEtats(KListObject<KRealiser> obsEtEtats)
	{
		this.obsEtEtats = obsEtEtats;
	}
}
