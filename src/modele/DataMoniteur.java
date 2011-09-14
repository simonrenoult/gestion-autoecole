package modele;

import java.sql.SQLException;

import net.ko.creator.KernelCreator;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import KClass.*;

public class DataMoniteur {

	private KDBMysql db = bdd.connexion();
	
	public DataMoniteur(){}
	
public KListObject<KMONITEUR> recupererListe() {
		
		KListObject<KMONITEUR> Kliste = new KListObject<KMONITEUR>(KMONITEUR.class);
		Kliste.loadFromDb(db);
		
		return Kliste;
		
	}

public KMONITEUR recupererProfilMoniteur (int id){
	
	KMONITEUR moniteur = new KMONITEUR();
	moniteur.setId(id);
	try {
		moniteur.loadOne(db);
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return moniteur;
}
	
	public KMONITEUR ajouterMoniteur (String nom, String Prenom, int id) {
		
		KMONITEUR moniteur = new KMONITEUR();
		moniteur.setId(id);
		moniteur.setPRENOM_MONITEUR(Prenom);
		moniteur.setNOM_MONITEUR(nom);
		moniteur.add(db);
		
		return moniteur;
		
	}
	
	public void supprimerMoniteur(Object id) {
		
		KMONITEUR moniteur = new KMONITEUR();
		moniteur.setId(id);
	
		try {
			moniteur.loadOne(db);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(moniteur);
		moniteur.delete(db);
		
		
		
	}



	public KMONITEUR majMoniteur(String nom,String prenom, Object id) {
		
		KMONITEUR moniteur = new KMONITEUR();
		moniteur.setId(id);
	
		try {
			moniteur.loadOne(db);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moniteur.setNOM_MONITEUR(nom);
		moniteur.setPRENOM_MONITEUR(prenom);
		moniteur.update(db);
		
		return moniteur;
		
	}
	
}              