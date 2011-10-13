package modele;

import java.sql.SQLException;

import net.ko.creator.KernelCreator;
import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;
import net.ko.ksql.KDBMysql;
import KClass.*;


public class DataFicheEleve1 {

	private KDBMysql db = bdd.connexion();

	public DataFicheEleve1() {}

	public KListObject<KEleve> recupererListe() {

		KListObject<KEleve> Kliste = new KListObject<KEleve>(KEleve.class);
		Kliste.loadFromDb(db);

		return Kliste;

	}
	
	public KEleve recupererProfil(int id) {
	
		KEleve Eleve = new KEleve();
		Eleve.setId(id+1);
	
		try {
			Eleve.loadOne(db);
		} catch (Exception e) { e.printStackTrace(); }
		
		System.out.println(Eleve);
		
		return Eleve;
		
	}
	
	
	
	
	

}
