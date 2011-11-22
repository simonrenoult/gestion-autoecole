package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KQuestion_synthese
 */
@SuppressWarnings("serial")
public class KQuestion_synthese extends KObject
{
	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	
	private String					LIBELLE_QUESTION_SYNTHESE;
	private KEtape					etape;
	private int						idETAPE;
	private int						idTHEME_SYNTHESE;
	private KListObject<KReponse>	reponses;
	private KTheme_synthese			theme_synthese;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public KQuestion_synthese()
	{
		super();
		keyFields = "id";
		tableName = "question_synthese";
		// belongsTo(KTheme_synthese.class);hasMany(KReponse.class);belongsTo(KEtape.class);

	}
	
	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //

	public String getLIBELLE_QUESTION_SYNTHESE()
	{
		// return the value of LIBELLE_QUESTION_SYNTHESE
		return this.LIBELLE_QUESTION_SYNTHESE;
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

	public int getIdTHEME_SYNTHESE()
	{
		// return the value of idTHEME_SYNTHESE
		return this.idTHEME_SYNTHESE;
	}

	public KListObject<KReponse> getReponses()
	{
		// return the value of reponses
		return this.reponses;
	}

	public KTheme_synthese getTheme_synthese()
	{
		// return the value of theme_synthese
		return this.theme_synthese;
	}

	// ----------------------------------------- //
	// --------------- MUTATEURS --------------- //
	// ----------------------------------------- //
	
	public void setLIBELLE_QUESTION_SYNTHESE(String aLIBELLE_QUESTION_SYNTHESE)
	{
		// set the value of LIBELLE_QUESTION_SYNTHESE
		this.LIBELLE_QUESTION_SYNTHESE = aLIBELLE_QUESTION_SYNTHESE;
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

	public void setIdTHEME_SYNTHESE(int aIdTHEME_SYNTHESE)
	{
		// set the value of idTHEME_SYNTHESE
		this.idTHEME_SYNTHESE = aIdTHEME_SYNTHESE;
	}

	public void setReponses(KListObject<KReponse> aReponses)
	{
		// set the value of reponses
		this.reponses = aReponses;
	}

	public void setTheme_synthese(KTheme_synthese aTheme_synthese)
	{
		// set the value of theme_synthese
		this.theme_synthese = aTheme_synthese;
	}

	@Override
	public String toString()
	{
		return " [LIBELLE_QUESTION_SYNTHESE] = " + LIBELLE_QUESTION_SYNTHESE + " [etape] = " + etape + " [idETAPE] = "
				+ idETAPE + " [idTHEME_SYNTHESE] = " + idTHEME_SYNTHESE + " [reponses] = " + reponses
				+ " [theme_synthese] = " + theme_synthese;
	}
}