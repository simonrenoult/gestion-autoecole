package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KTheme_synthese
 */
@SuppressWarnings("serial")
public class KTheme_synthese extends KObject
{
	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	
	private String							LIBELLE_THEME_SYNTHESE;
	private KListObject<KQuestion_synthese>	question_syntheses;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public KTheme_synthese()
	{
		super();
		keyFields = "id";
		tableName = "theme_synthese";
		// hasMany(KQuestion_synthese.class);

	}

	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //
	
	public String getLIBELLE_THEME_SYNTHESE()
	{
		// return the value of LIBELLE_THEME_SYNTHESE
		return this.LIBELLE_THEME_SYNTHESE;
	}

	public KListObject<KQuestion_synthese> getQuestion_syntheses()
	{
		// return the value of question_syntheses
		return this.question_syntheses;
	}

	// ----------------------------------------- //
	// --------------- MUTATEURS --------------- //
	// ----------------------------------------- //
	
	public void setLIBELLE_THEME_SYNTHESE(String aLIBELLE_THEME_SYNTHESE)
	{
		// set the value of LIBELLE_THEME_SYNTHESE
		this.LIBELLE_THEME_SYNTHESE = aLIBELLE_THEME_SYNTHESE;
	}

	public void setQuestion_syntheses(KListObject<KQuestion_synthese> aQuestion_syntheses)
	{
		// set the value of question_syntheses
		this.question_syntheses = aQuestion_syntheses;
	}

	@Override
	public String toString()
	{
		return " [LIBELLE_THEME_SYNTHESE] = " + LIBELLE_THEME_SYNTHESE + " [question_syntheses] = "
				+ question_syntheses;
	}
}