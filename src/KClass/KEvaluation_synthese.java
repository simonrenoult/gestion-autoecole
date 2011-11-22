package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KEvaluation_synthese
 */
@SuppressWarnings("serial")
public class KEvaluation_synthese extends KObject
{
	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	
	private KListObject<KPasser>	passers;
	private KEtape					etape;
	private int						idETAPE;
	private String					LIBELLE_EVALUATION_SYNTHESE;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public KEvaluation_synthese()
	{
		super();
		keyFields = "id";
		tableName = "evaluation_synthese";
		// hasMany(KPasser.class);belongsTo(KEtape.class);

	}

	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //
	
	public KListObject<KPasser> getPassers()
	{
		// return the value of passers
		return this.passers;
	}

	public KEtape getEtape()
	{
		// return the value of etape
		return this.etape;
	}

	public int getIdETAPE()
	{
		// return the value of idETAPE
		return this.idETAPE;
	}

	public String getLIBELLE_EVALUATION_SYNTHESE()
	{
		// return the value of LIBELLE_EVALUATION_SYNTHESE
		return this.LIBELLE_EVALUATION_SYNTHESE;
	}
	
	// ----------------------------------------- //
	// --------------- MUTATEURS --------------- //
	// ----------------------------------------- //

	public void setPassers(KListObject<KPasser> aPassers)
	{
		// set the value of passers
		this.passers = aPassers;
	}

	public void setEtape(KEtape aEtape)
	{
		// set the value of etape
		this.etape = aEtape;
	}

	public void setIdETAPE(int aIdETAPE)
	{
		// set the value of idETAPE
		this.idETAPE = aIdETAPE;
	}

	public void setLIBELLE_EVALUATION_SYNTHESE(String aLIBELLE_EVALUATION_SYNTHESE)
	{
		// set the value of LIBELLE_EVALUATION_SYNTHESE
		this.LIBELLE_EVALUATION_SYNTHESE = aLIBELLE_EVALUATION_SYNTHESE;
	}

	// ----------------------------------------- //
	// ---------------- DIVERS ----------------- //
	// ----------------------------------------- //
	
	@Override
	public String toString()
	{
		return " [passers] = " + passers + " [etape] = " + etape + " [idETAPE] = " + idETAPE
				+ " [LIBELLE_EVALUATION_SYNTHESE] = " + LIBELLE_EVALUATION_SYNTHESE;
	}
}