
package modele;

import controleur.EcouteurPrincipale;
import KClass.KCategorie_i_o;
import KClass.KObjectif;
import KClass.KRealiser;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class DataInterroOrale
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private KDBMysql					db	= bdd.connexion();
	private KListObject<KCategorie_i_o>	listCategories;
	KListObject<KObjectif>				listObjectifs[];
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	@SuppressWarnings("unchecked")
	public DataInterroOrale()
	{
		listCategories = new KListObject<KCategorie_i_o>(KCategorie_i_o.class);
		
		listCategories.loadFromDb(db, "select * from categorie_i_o order by id");
		listObjectifs = new KListObject[listCategories.count()];
		
		for (int i = 0; i < listObjectifs.length; i++)
			this.ajouterObjectifs(i, (Integer) getCategorie(i).getId());
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	private void ajouterObjectifs(int i,int categorie)
	{
		listObjectifs[i] = new KListObject<KObjectif>(KObjectif.class);
		listObjectifs[i].loadFromDb(db, "select * from objectif where idEtape = 5 and idCATEGORIE_I_O = " + categorie
				+ " order by id");
	}
	
	public int[] chargerReponsesEleve(int cat)
	{
		KListObject<KObjectif> objectifs = getCategorie(cat).getObjectifs();
		int idObj,idEleve = Integer.parseInt(EcouteurPrincipale.Eleve.getId().toString());
		int reponses[] = new int[objectifs.count()];
		
		KListObject<KRealiser> obj = new KListObject<KRealiser>(KRealiser.class);
		
		for (int i = 0; i < reponses.length; i++)
		{
			idObj = Integer.parseInt(objectifs.get(i).getId().toString());
			obj = new KListObject<KRealiser>(KRealiser.class);
			obj.loadFromDb(db, "SELECT * FROM realiser WHERE idELEVE = " + idEleve + " and idOBJECTIF = " + idObj);
			if (obj.count() > 0) reponses[i] = obj.get(0).getETAT_OBJECTIF();
			else reponses[i] = 0;
		}
		
		return reponses;
	}
	
	// Insertions en BDD
	public boolean insertBDD(int categorie,int numobj,int etat)
	{
		// On recupere l'eleve en cours
		int idEleve = Integer.parseInt(EcouteurPrincipale.Eleve.getId().toString());
		
		// On recupere la liste des objectifs de la categorie
		KListObject<KObjectif> obj = new KListObject<KObjectif>(KObjectif.class);
		obj.loadFromDb(db, "select * from objectif where idCATEGORIE_I_O=" + (categorie + 1));
		// L'id de l'objectif dans la table
		if (obj.count() == 0) return false;
		int objectif = Integer.parseInt(obj.get(numobj).getId().toString());
		
		// On regarde si l'eleve a deja une réponse.
		KListObject<KRealiser> last = new KListObject<KRealiser>(KRealiser.class);
		last.loadFromDb(db, "select * from realiser where idELEVE=" + idEleve + " and idOBJECTIF=" + objectif);
		
		// Si l'eleve n'a pas de reponse, on la cree
		if (last.count() == 0 && etat != 0)
		{
			KRealiser realiser = new KRealiser();
			realiser.setIdELEVE(idEleve);
			realiser.setIdOBJECTIF(objectif);
			realiser.setETAT_OBJECTIF(etat);
			realiser.add(db);
			return true;
		}
		// Sinon, on met a jour
		else
		{
			last.get(0).setETAT_OBJECTIF(etat);
			last.get(0).update(db);
		}
		
		return false;
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public KCategorie_i_o getCategorie(int i)
	{
		return listCategories.get(i);
	}
	
	public KListObject<KCategorie_i_o> getCategories()
	{
		return listCategories;
	}
	
	public KListObject<KObjectif> getObjectifsForCategorie(int categorie)
	{
		return listObjectifs[categorie];
	}
	
}