package KClass.tests;

import java.sql.SQLException;

import KClass.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.kobject.KSession;
import net.ko.ksql.KDBMysql;
import net.ko.utils.KScriptTimer;

public class Test_modele_logique {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KObject.useCache=true;
		KCache.loadAllCache();
		KSession ks=new KSession();
		try {
			ks.connect(new KDBMysql("localhost", "admin", "admin", "modele_logique"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KScriptTimer.start();
		KScriptTimer.start("KAssurer_lecon");
		KListObject<KAssurer_lecon> assurer_lecons=(KListObject<KAssurer_lecon>) ks.kloadFromDb(KAssurer_lecon.class);
		KScriptTimer.stop("KAssurer_lecon");
		System.out.println(assurer_lecons);
		KScriptTimer.start("KCategorie_i_o");
		KListObject<KCategorie_i_o> categorie_i_os=(KListObject<KCategorie_i_o>) ks.kloadFromDb(KCategorie_i_o.class);
		KScriptTimer.stop("KCategorie_i_o");
		System.out.println(categorie_i_os);
		KScriptTimer.start("KChamps");
		KListObject<KChamps> champss=(KListObject<KChamps>) ks.kloadFromDb(KChamps.class);
		KScriptTimer.stop("KChamps");
		System.out.println(champss);
		KScriptTimer.start("KCompte");
		KListObject<KCompte> comptes=(KListObject<KCompte>) ks.kloadFromDb(KCompte.class);
		KScriptTimer.stop("KCompte");
		System.out.println(comptes);
		KScriptTimer.start("KContenir");
		KListObject<KContenir> contenirs=(KListObject<KContenir>) ks.kloadFromDb(KContenir.class);
		KScriptTimer.stop("KContenir");
		System.out.println(contenirs);
		KScriptTimer.start("KEleve");
		KListObject<KEleve> eleves=(KListObject<KEleve>) ks.kloadFromDb(KEleve.class);
		KScriptTimer.stop("KEleve");
		System.out.println(eleves);
		KScriptTimer.start("KEtape");
		KListObject<KEtape> etapes=(KListObject<KEtape>) ks.kloadFromDb(KEtape.class);
		KScriptTimer.stop("KEtape");
		System.out.println(etapes);
		KScriptTimer.start("KEvaluation_controle");
		KListObject<KEvaluation_controle> evaluation_controles=(KListObject<KEvaluation_controle>) ks.kloadFromDb(KEvaluation_controle.class);
		KScriptTimer.stop("KEvaluation_controle");
		System.out.println(evaluation_controles);
		KScriptTimer.start("KFormation");
		KListObject<KFormation> formations=(KListObject<KFormation>) ks.kloadFromDb(KFormation.class);
		KScriptTimer.stop("KFormation");
		System.out.println(formations);
		KScriptTimer.start("KFranchir");
		KListObject<KFranchir> franchirs=(KListObject<KFranchir>) ks.kloadFromDb(KFranchir.class);
		KScriptTimer.stop("KFranchir");
		System.out.println(franchirs);
		KScriptTimer.start("KMoniteur");
		KListObject<KMoniteur> moniteurs=(KListObject<KMoniteur>) ks.kloadFromDb(KMoniteur.class);
		KScriptTimer.stop("KMoniteur");
		System.out.println(moniteurs);
		KScriptTimer.start("KObjectif");
		KListObject<KObjectif> objectifs=(KListObject<KObjectif>) ks.kloadFromDb(KObjectif.class);
		KScriptTimer.stop("KObjectif");
		System.out.println(objectifs);
		KScriptTimer.start("KPasser");
		KListObject<KPasser> passers=(KListObject<KPasser>) ks.kloadFromDb(KPasser.class);
		KScriptTimer.stop("KPasser");
		System.out.println(passers);
		KScriptTimer.start("KQuestions_synthese");
		KListObject<KQuestions_synthese> questions_syntheses=(KListObject<KQuestions_synthese>) ks.kloadFromDb(KQuestions_synthese.class);
		KScriptTimer.stop("KQuestions_synthese");
		System.out.println(questions_syntheses);
		KScriptTimer.start("KRealiser");
		KListObject<KRealiser> realisers=(KListObject<KRealiser>) ks.kloadFromDb(KRealiser.class);
		KScriptTimer.stop("KRealiser");
		System.out.println(realisers);
		KScriptTimer.start("KReponse");
		KListObject<KReponse> reponses=(KListObject<KReponse>) ks.kloadFromDb(KReponse.class);
		KScriptTimer.stop("KReponse");
		System.out.println(reponses);
		KScriptTimer.start("KSe_rapporter");
		KListObject<KSe_rapporter> se_rapporters=(KListObject<KSe_rapporter>) ks.kloadFromDb(KSe_rapporter.class);
		KScriptTimer.stop("KSe_rapporter");
		System.out.println(se_rapporters);
		KScriptTimer.start("KSuperviser");
		KListObject<KSuperviser> supervisers=(KListObject<KSuperviser>) ks.kloadFromDb(KSuperviser.class);
		KScriptTimer.stop("KSuperviser");
		System.out.println(supervisers);
		KScriptTimer.start("KSynthese");
		KListObject<KSynthese> syntheses=(KListObject<KSynthese>) ks.kloadFromDb(KSynthese.class);
		KScriptTimer.stop("KSynthese");
		System.out.println(syntheses);
		
		KScriptTimer.stop();	
		KObject.cacheShutdown();
		try {
			ks.getDb().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}