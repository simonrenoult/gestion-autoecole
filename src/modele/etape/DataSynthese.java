package modele.etape;

import KClass.KEvaluation_synthese;
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

	private KListObject<KEvaluation_synthese>	evaluationSynthese;
	private KListObject<KTheme_synthese>		themesSynthese;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //

	public DataSynthese(Integer numEtape)
	{
		chargerThemesSynthese(numEtape);
	}

	// ----------------------------------------- //
	// ------------ INITIALISEURS -------------- //
	// ----------------------------------------- //

	private void initThemesSynthese(Integer numEtape)
	{
		
	}

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	public KListObject<KTheme_synthese> chargerThemesSynthese(int numEtape)
	{
		KListObject<KTheme_synthese> tmp = new KListObject<KTheme_synthese>(KTheme_synthese.class);
		
		tmp.loadFromDb(db, "SELECT * FROM theme_synthese WHERE idETAPE = '"+numEtape+"'");
		System.out.println(tmp);
		
		return tmp;
	}

	public void chargerQuestionsSynthese(int numThemeSynthese)
	{

	}

	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //

}
