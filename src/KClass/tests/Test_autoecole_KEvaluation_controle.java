package KClass.tests;

import java.sql.SQLException;

import KClass.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.kobject.KSession;
import net.ko.ksql.KDBMysql;
import net.ko.utils.KScriptTimer;

public class Test_autoecole_KEvaluation_controle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KObject.useCache=true;
		KCache.loadAllCache();
		KSession ks=new KSession();
		try {
			ks.connect(new KDBMysql("localhost", "root", "", "autoecole"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KScriptTimer.start();
		KScriptTimer.start("KEvaluation_controle");
		KListObject<KEvaluation_controle> evaluation_controles=(KListObject<KEvaluation_controle>) ks.kloadFromDb(KEvaluation_controle.class);
		KScriptTimer.stop("KEvaluation_controle");
		System.out.println(evaluation_controles);
		
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