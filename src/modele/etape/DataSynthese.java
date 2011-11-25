package modele.etape;

import KClass.KEvaluation_synthese;
import KClass.KPasser;
import KClass.KQuestion_synthese;
import KClass.KReponse;
import KClass.KTheme_synthese;
import modele.BDD;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataSynthese
{
	// ----------------------------------------- //
	// --------------- ATRIBUTS ---------------- //
	// ----------------------------------------- //

	private KDBMysql							db	= BDD.db;

	private KListObject<KTheme_synthese>		themesSynthese;
	private KListObject<KReponse>				resThemeSynthese;

	private KListObject<KEvaluation_synthese>	evaluationSynthese;
	private KListObject<KPasser>				resEvaluationSynthese;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //

	public DataSynthese(Integer numEtape)
	{
		initThemesSynthese(numEtape);
		initQtsSynthese(numEtape);
		initEvaluationSynthese(numEtape);
	}

	// ----------------------------------------- //
	// ------------ INITIALISEURS -------------- //
	// ----------------------------------------- //

	private void initThemesSynthese(Integer numEtape)
	{
		this.themesSynthese = chargerThemesSynthese(numEtape);
	}

	private void initQtsSynthese(Integer numEtape)
	{
		for (int i = 0; i < themesSynthese.count(); i++)
			themesSynthese.get(i).setQuestion_syntheses(
					chargerQtsSynthese(Integer.parseInt(themesSynthese.get(i).getId().toString())));
	}

	private void initEvaluationSynthese(Integer numEtape)
	{
		this.evaluationSynthese = chargerEvalSynthese(numEtape);
	}

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	public KListObject<KTheme_synthese> chargerThemesSynthese(Integer numEtape)
	{
		KListObject<KTheme_synthese> tmp = new KListObject<KTheme_synthese>(KTheme_synthese.class);

		tmp.loadFromDb(db, "SELECT * FROM theme_synthese WHERE idETAPE = '" + numEtape + "'");

		return tmp;
	}

	public KListObject<KQuestion_synthese> chargerQtsSynthese(Integer numTheme)
	{
		KListObject<KQuestion_synthese> tmp = new KListObject<KQuestion_synthese>(KQuestion_synthese.class);

		tmp.loadFromDb(db, "SELECT * FROM question_synthese WHERE idTHEME_SYNTHESE = '" + numTheme + "'");

		return tmp;
	}

	public KListObject<KEvaluation_synthese> chargerEvalSynthese(Integer numEtape)
	{
		KListObject<KEvaluation_synthese> tmp = new KListObject<KEvaluation_synthese>(KEvaluation_synthese.class);

		tmp.loadFromDb(db, "SELECT * FROM evaluation_synthese WHERE idETAPE = '" + numEtape + "'");

		return tmp;
	}
	
	public KListObject<KPasser> chargerResEvalSynthese(Integer numEleve, Integer numSynthese)
	{
		KListObject<KPasser> tmp = new KListObject<KPasser>(KPasser.class);
		
		tmp.loadFromDb(db, "SELECT * FROM passer WHERE idELEVE ='"+numEleve+"' ");
		
		return tmp;
	}
	
	public String toString()
	{
		return "Themes synthese : "+ this.themesSynthese+'\n'+
				"Evaluation synthese : "+this.evaluationSynthese;
	}

	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	public KDBMysql getDb()
	{
		return db;
	}

	public KListObject<KTheme_synthese> getThemesSynthese()
	{
		return themesSynthese;
	}

	public KListObject<KEvaluation_synthese> getEvaluationSynthese()
	{
		return evaluationSynthese;
	}

	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //

	public void setDb(KDBMysql db)
	{
		this.db = db;
	}

	public void setThemesSynthese(KListObject<KTheme_synthese> themesSynthese)
	{
		this.themesSynthese = themesSynthese;
	}

	public void setEvaluationSynthese(KListObject<KEvaluation_synthese> evaluationSynthese)
	{
		this.evaluationSynthese = evaluationSynthese;
	}
}
