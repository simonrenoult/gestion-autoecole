
package modele.etape;

import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import KClass.KQuestions_synthese;
import KClass.KSynthese;
import KClass.KThemes_synthese;

public class DataSynthese
{
	// ----------------------------------------- //
	// ----------------ATRIBUTS----------------- //
	// ----------------------------------------- //
	
	private KDBMysql							db;
	
	private KListObject<KSynthese>				syntheses;
	private KListObject<KQuestions_synthese>	questionsSyn;
	private KListObject<KThemes_synthese>		themesSyn;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataSynthese(KDBMysql connexion, Integer numEtape)
	{
		this.db = connexion;
	}
	
	// ----------------------------------------- //
	// -------------INITIALISEURS--------------- //
	// ----------------------------------------- //
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	public KListObject<KSynthese> chargerSynthese(int numEtape)
	{
		KListObject<KSynthese> tmp = new KListObject<KSynthese>(KSynthese.class);
		tmp.loadFromDb(db , "select * from synthese where idETAPE = '" + numEtape + "'");
		
		return tmp;
	}
	
	public KListObject<KThemes_synthese> chargerThemeSynthese(int numSynthese)
	{
		KListObject<KThemes_synthese> tmp = new KListObject<KThemes_synthese>(KThemes_synthese.class);
		tmp.loadFromDb(db , "select * from theme_synthese where idSYNTHESE = '" + numSynthese + "'");
		
		return tmp;
	}
	
	public KListObject<KQuestions_synthese> chargerQuestionsSynthese(int numThemeSynthese)
	{
		KListObject<KQuestions_synthese> tmp = new KListObject<KQuestions_synthese>(KQuestions_synthese.class);
		tmp.loadFromDb(db , "select * from questions_synthese where idTHEME_SYNTHESE = '" + numThemeSynthese + "'");
		
		return tmp;
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public KListObject<KSynthese> getSyntheses()
	{
		return syntheses;
	}
	
	public KListObject<KQuestions_synthese> getQuestionsSyn()
	{
		return questionsSyn;
	}
	
	public KListObject<KThemes_synthese> getThemesSyn()
	{
		return themesSyn;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public void setSyntheses(KListObject<KSynthese> syntheses)
	{
		this.syntheses = syntheses;
	}
	
	public void setQuestionsSyn(KListObject<KQuestions_synthese> questionsSyn)
	{
		this.questionsSyn = questionsSyn;
	}
	
	public void setThemesSyn(KListObject<KThemes_synthese> themesSyn)
	{
		this.themesSyn = themesSyn;
	}
}
