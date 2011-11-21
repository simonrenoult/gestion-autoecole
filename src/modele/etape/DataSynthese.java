
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
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataSynthese(KDBMysql connexion, Integer numEtape)
	{
		this.db = connexion;
		
		initSyntheses(numEtape);
	}
	
	// ----------------------------------------- //
	// -------------INITIALISEURS--------------- //
	// ----------------------------------------- //
	
	private void initSyntheses(Integer numEtape)
	{
		this.syntheses = chargerSynthese(numEtape);
		
		for (KSynthese ks : syntheses)
		{
			ks.setThemes_syntheses(chargerThemesSynthese(Integer.parseInt(ks.getId().toString())));
			
			for (KThemes_synthese kts : ks.getThemes_syntheses())
				kts.setQuestions_syntheses(chargerQuestionsSynthese(Integer.parseInt(kts.getId().toString())));
		}		
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	public KListObject<KSynthese> chargerSynthese(int numEtape)
	{
		KListObject<KSynthese> tmp = new KListObject<KSynthese>(KSynthese.class);
		tmp.loadFromDb(db , "select * from synthese where idETAPE = '" + numEtape + "'");
		
		return tmp;
	}
	
	public KListObject<KThemes_synthese> chargerThemesSynthese(int numSynthese)
	{
		KListObject<KThemes_synthese> tmp = new KListObject<KThemes_synthese>(KThemes_synthese.class);
		tmp.loadFromDb(db , "select * from themes_synthese where idSYNTHESE = '" + numSynthese + "'");
		
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
	
	public KListObject<KThemes_synthese> getThemesSynthese(Integer idSynthese)
	{
		return 	syntheses.get(idSynthese).getThemes_syntheses();
	}
	
	public KListObject<KQuestions_synthese> getQtsSynthese(Integer idSynthese, Integer idThemeSynthese)
	{
		return 	syntheses.get(idSynthese).getThemes_syntheses().get(idThemeSynthese).getQuestions_syntheses();
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public void setSyntheses(KListObject<KSynthese> syntheses)
	{
		this.syntheses = syntheses;
	}
	
	public void setQuestionsSyn(Integer idSynthese, KListObject<KThemes_synthese> questionsSyn)
	{
		syntheses.get(idSynthese).setThemes_syntheses(questionsSyn);
	}
	
	public void setThemesSyn(Integer idSynthese, Integer idThemeSynthese, KListObject<KQuestions_synthese> qtsSynthese)
	{
		syntheses.get(idSynthese).getThemes_syntheses().get(idThemeSynthese).setQuestions_syntheses(qtsSynthese);
	}
}
