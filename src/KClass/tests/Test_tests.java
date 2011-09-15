package KClass.tests;

import java.sql.SQLException;

import KClass.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.kobject.KSession;
import net.ko.ksql.KDBMysql;
import net.ko.utils.KScriptTimer;

public class Test_tests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KObject.useCache=true;
		KCache.loadAllCache();
		KSession ks=new KSession();
		try {
			ks.connect(new KDBMysql("localhost", "admin", "admin", "tests"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KScriptTimer.start();
		KScriptTimer.start("KDroit");
		KListObject<KDroit> droits=(KListObject<KDroit>) ks.kloadFromDb(KDroit.class);
		KScriptTimer.stop("KDroit");
		System.out.println(droits);
		KScriptTimer.start("KProjet");
		KListObject<KProjet> projets=(KListObject<KProjet>) ks.kloadFromDb(KProjet.class);
		KScriptTimer.stop("KProjet");
		System.out.println(projets);
		KScriptTimer.start("KUtilisateur");
		KListObject<KUtilisateur> utilisateurs=(KListObject<KUtilisateur>) ks.kloadFromDb(KUtilisateur.class);
		KScriptTimer.stop("KUtilisateur");
		System.out.println(utilisateurs);
		KScriptTimer.start("KUtilisateur_droit");
		KListObject<KUtilisateur_droit> utilisateur_droits=(KListObject<KUtilisateur_droit>) ks.kloadFromDb(KUtilisateur_droit.class);
		KScriptTimer.stop("KUtilisateur_droit");
		System.out.println(utilisateur_droits);
		
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