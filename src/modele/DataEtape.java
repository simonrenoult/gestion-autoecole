
package modele;

import KClass.KObjectif;
import KClass.KQuestions_synthese;
import KClass.KRealiser;
import KClass.KSynthese;
import KClass.KTheme_synthese;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataEtape
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private KDBMysql							db	= bdd.connexion();
	private KListObject<KObjectif>				objectifs;
	private KListObject<KRealiser>				obsEtEtats;
	
	private KListObject<KSynthese>				syntheses;
	private KListObject<KQuestions_synthese>	questionsSyn;
	private KListObject<KTheme_synthese>		themesSyn;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataEtape(int numEtape)
	{
		objectifs = chargerObjectifs(numEtape);
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	private KListObject<KObjectif> chargerObjectifs(int numEtape)
	{
		KListObject<KObjectif> tmp = new KListObject<KObjectif>(KObjectif.class);
		tmp.loadFromDb(db, "select * from objectif where idETAPE = '" + numEtape + "'");
		
		return tmp;
	}
	
	public KListObject<KRealiser> chargerObsEtEtat(int numEleve)
	{
		KListObject<KRealiser> tmp = new KListObject<KRealiser>(KRealiser.class);
		tmp.loadFromDb(db, "SELECT * FROM realiser WHERE idELEVE = '" + numEleve + "' ");
				
		return tmp;
	}
	
	public KListObject<KSynthese> chargerSynthese(int numEtape)
	{
		KListObject<KSynthese> tmp = new KListObject<KSynthese>(KSynthese.class);
		tmp.loadFromDb(db, "select * from synthese where idETAPE = '" + numEtape + "'");
		
		return tmp;
	}
	
	public KListObject<KTheme_synthese> chargerThemeSynthese(int numSynthese)
	{
		KListObject<KTheme_synthese> tmp = new KListObject<KTheme_synthese>(KTheme_synthese.class);
		tmp.loadFromDb(db, "select * from theme_synthese where idSYNTHESE = '" + numSynthese + "'");
		
		return tmp;
	}
	
	public KListObject<KQuestions_synthese> chargerQuestionsSynthese(int numThemeSynthese)
	{
		KListObject<KQuestions_synthese> tmp = new KListObject<KQuestions_synthese>(
				KQuestions_synthese.class);
		tmp.loadFromDb(db, "select * from questions_synthese where idTHEME_SYNTHESE = '"
				+ numThemeSynthese + "'");
		
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

	public KListObject<KSynthese> getSyntheses()
	{
		return syntheses;
	}

	public KListObject<KQuestions_synthese> getQuestionsSyn()
	{
		return questionsSyn;
	}

	public KListObject<KTheme_synthese> getThemesSyn()
	{
		return themesSyn;
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

	public void setSyntheses(KListObject<KSynthese> syntheses)
	{
		this.syntheses = syntheses;
	}

	public void setQuestionsSyn(KListObject<KQuestions_synthese> questionsSyn)
	{
		this.questionsSyn = questionsSyn;
	}

	public void setThemesSyn(KListObject<KTheme_synthese> themesSyn)
	{
		this.themesSyn = themesSyn;
	}
	
}