
package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KQuestions_synthese
 */
@SuppressWarnings("serial")
public class KQuestions_synthese extends KObject
{
	private String					LIBELLE_QUESTION_SYNTHESE;
	private int						idTHEME_SYNTHESE;
	private KListObject<KReponse>	reponses;
	private KThemes_synthese		themes_synthese;
	
	public KQuestions_synthese()
	{
		super();
		keyFields = "id";
		tableName = "questions_synthese";
		// belongsTo(KThemes_synthese.class);hasMany(KReponse.class);
		
	}
	
	public String getLIBELLE_QUESTION_SYNTHESE()
	{
		// return the value of LIBELLE_QUESTION_SYNTHESE
		return this.LIBELLE_QUESTION_SYNTHESE;
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
	
	public KThemes_synthese getThemes_synthese()
	{
		// return the value of themes_synthese
		return this.themes_synthese;
	}
	
	public void setLIBELLE_QUESTION_SYNTHESE(String aLIBELLE_QUESTION_SYNTHESE)
	{
		// set the value of LIBELLE_QUESTION_SYNTHESE
		this.LIBELLE_QUESTION_SYNTHESE = aLIBELLE_QUESTION_SYNTHESE;
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
	
	public void setThemes_synthese(KThemes_synthese aThemes_synthese)
	{
		// set the value of themes_synthese
		this.themes_synthese = aThemes_synthese;
	}
	
	@Override
	public String toString()
	{
		return " [LIBELLE_QUESTION_SYNTHESE] = " + LIBELLE_QUESTION_SYNTHESE + " [idTHEME_SYNTHESE] = "
				+ idTHEME_SYNTHESE + " [reponses] = " + reponses + " [themes_synthese] = " + themes_synthese;
	}
}