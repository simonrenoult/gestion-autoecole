package KClass.tests;

import java.sql.SQLException;

import KClass.*;
import net.ko.cache.KCache;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.kobject.KSession;
import net.ko.ksql.KDBMysql;
import net.ko.utils.KScriptTimer;

public class Test_autoecole_KSuperviser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KObject.useCache=true;
		KCache.loadAllCache();
		KSession ks=new KSession();
		try {
			ks.connect(new KDBMysql("localhost", "admin", "admin", "autoecole"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KScriptTimer.start();
		KScriptTimer.start("KSuperviser");
		KListObject<KSuperviser> supervisers=(KListObject<KSuperviser>) ks.kloadFromDb(KSuperviser.class);
		KScriptTimer.stop("KSuperviser");
		System.out.println(supervisers);
		
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