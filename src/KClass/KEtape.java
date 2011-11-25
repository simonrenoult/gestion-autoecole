package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KEtape
 */
@SuppressWarnings("serial")
public class KEtape extends KObject
{
	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	
	private KListObject<KEvaluation_synthese>	evaluation_syntheses;
	private KListObject<KContenir>				contenirs;
	private KListObject<KSuperviser>			supervisers;
	private String								LIBELLE_ETAPE;
	private KListObject<KObjectif>				objectifs;
	private KListObject<KQuestion_synthese>		question_syntheses;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public KEtape()
	{
		super();
		keyFields = "id";
		tableName = "etape";
		// hasMany(KSuperviser.class);hasMany(KQuestion_synthese.class);hasMany(KObjectif.class);hasMany(KEvaluation_synthese.class);hasMany(KContenir.class);

	}
	
	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //

	public KListObject<KEvaluation_synthese> getEvaluation_syntheses()
	{
		// return the value of evaluation_syntheses
		return this.evaluation_syntheses;
	}

	public KListObject<KContenir> getContenirs()
	{
		// return the value of contenirs
		return this.contenirs;
	}

	public KListObject<KSuperviser> getSupervisers()
	{
		// return the value of supervisers
		return this.supervisers;
	}

	public String getLIBELLE_ETAPE()
	{
		// return the value of LIBELLE_ETAPE
		return this.LIBELLE_ETAPE;
	}

	public KListObject<KObjectif> getObjectifs()
	{
		// return the value of objectifs
		return this.objectifs;
	}

	public KListObject<KQuestion_synthese> getQuestion_syntheses()
	{
		// return the value of question_syntheses
		return this.question_syntheses;
	}

	// ----------------------------------------- //
	// --------------- MUTATEURS --------------- //
	// ----------------------------------------- //
	
	public void setEvaluation_syntheses(KListObject<KEvaluation_synthese> aEvaluation_syntheses)
	{
		// set the value of evaluation_syntheses
		this.evaluation_syntheses = aEvaluation_syntheses;
	}

	public void setContenirs(KListObject<KContenir> aContenirs)
	{
		// set the value of contenirs
		this.contenirs = aContenirs;
	}

	public void setSupervisers(KListObject<KSuperviser> aSupervisers)
	{
		// set the value of supervisers
		this.supervisers = aSupervisers;
	}

	public void setLIBELLE_ETAPE(String aLIBELLE_ETAPE)
	{
		// set the value of LIBELLE_ETAPE
		this.LIBELLE_ETAPE = aLIBELLE_ETAPE;
	}

	public void setObjectifs(KListObject<KObjectif> aObjectifs)
	{
		// set the value of objectifs
		this.objectifs = aObjectifs;
	}

	public void setQuestion_syntheses(KListObject<KQuestion_synthese> aQuestion_syntheses)
	{
		// set the value of question_syntheses
		this.question_syntheses = aQuestion_syntheses;
	}

	@Override
	public String toString()
	{
		return " [evaluation_syntheses] = " + evaluation_syntheses + " [contenirs] = " + contenirs
				+ " [supervisers] = " + supervisers + " [LIBELLE_ETAPE] = " + LIBELLE_ETAPE + " [objectifs] = "
				+ objectifs + " [question_syntheses] = " + question_syntheses;
	}
}