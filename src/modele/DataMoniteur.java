
package modele;

import java.sql.SQLException;
import java.util.regex.Pattern;
import net.ko.kobject.KListObject;
import KClass.*;

public class DataMoniteur
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private Boolean []	TableauChampSaisieOk	= null;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataMoniteur()
	{
		TableauChampSaisieOk = new Boolean [2];
		for (int i = 0; i < TableauChampSaisieOk.length; i++)
		{
			TableauChampSaisieOk[i] = false;
		}
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	/*
	 * permet de recuprer la liste des moniteur selon leur nom par ordre
	 */
	public KListObject<KMoniteur> recupererListe()
	{
		KListObject<KMoniteur> Kliste = new KListObject<KMoniteur>(KMoniteur.class);
		Kliste.loadFromDb(bdd.db , "Select * from moniteur order by nom_moniteur asc");
		
		return Kliste;		
	}
	
	/*
	 * recupere un profil de moniteur
	 */
	public KMoniteur recupererProfilMoniteur(int id)
	{
		KMoniteur moniteur = new KMoniteur();
		moniteur.setId(id);
		try
		{
			moniteur.loadOne(bdd.db);
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return moniteur;
	}
	
	/*
	 * Ajout d'un moniteur en BDD
	 */
	public boolean ajouterMoniteur(KMoniteur moniteur, int id)
	{
		
		moniteur.setId(id);
		return moniteur.add(bdd.db);
	}
	
	/*
	 * suppression d'un moniteur en BDD
	 */
	public void supprimerMoniteur(Object id)
	{
		
		KMoniteur moniteur = new KMoniteur();
		moniteur.setId(id);
		
		try
		{
			moniteur.loadOne(bdd.db);
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		moniteur.delete(bdd.db);		
	}
	
	/*
	 * MAJ d'un moniteur en BDD
	 */
	public boolean majMoniteur(KMoniteur moniteur)
	{
		return moniteur.update(bdd.db);	
	}
	
	/*
	 * Verfie que tous les champs sont au bon format
	 */
	public boolean tableauChampSaisieTrue()
	{
		boolean b = true;
		
		for (int i = 0; i < TableauChampSaisieOk.length; i++)
		{
			if (!TableauChampSaisieOk[i])
			{
				b = false;
			}
		}
		
		return b;
	}
	
	/*
	 * Message renvoyée lors d'une erreur
	 */
	public Object messageRenvoyeeUI(int num)
	{
		switch (num)
		{
			case 5:
				return "-Echec de l'opération : mise à jour d'un moniteur dans base de donnée\n";
				
			case 4:
				return "-Succès de l'opération : mise à jour d'un moniteur dans la base de donnée\n";
				
			case 3:
				return "-Echec de l'opération : ajout d'un moniteur en base de donnée\n";
				
			case 2:
				return "-Succès de l'opération : ajout d'un moniteur en base de donnée\n";
				
			case 1:
				return "";
				
			case -5:
				return "-Tous les champs n'ont pas été rempli correctement. Tous les champs symbolisé par * doivent être rempli\n";
		}
		
		return "";
	}
	
	// ----------------------------------------- //
	// ------------------REGEXP----------------- //
	// ----------------------------------------- //
	
	public boolean regexTraitementNom(String chaine)
	{
		return Pattern.matches("^[A-Za-z'-|\\s]{0,19}+$" , chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementPrenom(String chaine)
	{
		return Pattern.matches("^[A-Za-z'-|\\s]{0,19}+$" , chaine) && (!chaine.isEmpty());
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	/**
	 * @return the tableauChampSaisieOk
	 */
	public Boolean [] getTableauChampSaisieOk()
	{
		return TableauChampSaisieOk;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	/**
	 * @param tableauChampSaisieOk
	 *            the tableauChampSaisieOk to set
	 */
	public void setTableauChampSaisieOk(Boolean [] tableauChampSaisieOk)
	{
		TableauChampSaisieOk = tableauChampSaisieOk;
	}
	
}