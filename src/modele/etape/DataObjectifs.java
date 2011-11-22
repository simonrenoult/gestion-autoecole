package modele.etape;

import modele.BDD;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import KClass.KObjectif;
import KClass.KRealiser;

public class DataObjectifs
{
	// ----------------------------------------- //
	// ----------------ATRIBUTS----------------- //
	// ----------------------------------------- //

	private KDBMysql				db	= BDD.db;

	private KListObject<KObjectif>	objectifs;
	private KListObject<KRealiser>	obsEtEtats;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public DataObjectifs(Integer numEtape)
	{
		this.objectifs = chargerObjectifs(numEtape);
	}

	// ----------------------------------------- //
	// -------------INITIALISEURS--------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	private KListObject<KObjectif> chargerObjectifs(int numEtape)
	{
		KListObject<KObjectif> tmp = new KListObject<KObjectif>(KObjectif.class);
		
		tmp.loadFromDb(db, "SELECT * FROM objectif WHERE idETAPE = '" + numEtape + "'");

		return tmp;
	}

	public KListObject<KRealiser> chargerObsEtEtat(int numEleve)
	{
		KListObject<KRealiser> tmp = new KListObject<KRealiser>(KRealiser.class);
		tmp.loadFromDb(db, "SELECT * FROM realiser WHERE idELEVE = '" + numEleve + "' ");

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
