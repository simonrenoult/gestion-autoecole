
/*
 * Cette classe permet de d'extraire les données de la JTable (conversion d'un
 * tableau d'objet [][] en Liste KAssurerLecon). Cette liste va être ensuite
 * comparée à la liste de référence KlisteRDVRef afin de voir ci des RDV ont été
 * modifié. La table Agenda admet un id numérique et non plus une date, donc les
 * on compare aussi les dates et heures de RDV entre ceux de la Jtable
 * KlisteAgendaGraphique et la liste de reference KlisteAgendaRef (par rapport à
 * un élève). Cette necessité de ces listes provient du fait qu'on ne renseigne
 * pas de dates, ni d'heure dans la liste KlisteRDVRef.
 * 
 * Exemple :
 * 
 * KlisteAgendaRef
 * 
 * 17 2011-08-24 15:30:00 contenue dans la table Agenda
 * 
 * KlisteAgendaGraphique
 * 
 * 17 2011-08-24 15:30:00 similaire celle de la BDD, update en BDD 18 2011-08-25
 * 15:30:00 n'existe pas en BDD, Insert -1 1899-12-31 00:00:00 erreur de format
 * dans le champ grahique du 3eme RDV (3eme ligne de la Jtable), génère un
 * message d'erreur.
 * 
 * TableauChampSaisieOk est un tableau de booléen où chaque champ détermine si
 * le format des données est valide en BDD.
 */

package modele;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import net.ko.kobject.KListObject;
import KClass.KAgenda;
import KClass.KAssurer_lecon;
import KClass.KMoniteur;

public class TableauLecon
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private Boolean []					TableauChampSaisieOk	= null;
	private KListObject<KAssurer_lecon>	KlisteRDVRef			= null;
	private KListObject<KAgenda>		KlisteAgendaRef			= null;
	private KListObject<KAgenda>		KlisteAgendaGraphique	= null;
	public static int					numeroLigne				= 0;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
		
	public TableauLecon()
	{
		TableauChampSaisieOk = new Boolean [5];
		for (int i = 0; i < TableauChampSaisieOk.length; i++)
		{
			TableauChampSaisieOk[i] = false;
		}
		
		KlisteRDVRef = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		KlisteAgendaRef = new KListObject<KAgenda>(KAgenda.class);
		KlisteAgendaGraphique = new KListObject<KAgenda>(KAgenda.class);
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	// --------AGENDA-------- //
	
	/*
	 * Permet d'insérer un objet KAgenda à la table Agenda.
	 */
	public int AjoutAgenda(KAgenda agenda, int idMoniteur)
	{
		
		int id = recupererDerniereCle(idMoniteur);
		
		java.sql.Connection conn;
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/autoecole4" , "admin" , "admin");
		System.out.println(agenda.getId().toString() + "    " + agenda.getDATE_AGENDA() + "    "
					+ agenda.getHEURE_AGENDA());
			System.out.println("INSERT INTO Agenda VALUES (" + id + ",'" + agenda.getDATE_AGENDA() + "','"
					+ agenda.getHEURE_AGENDA() + "' )");
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO Agenda VALUES (" + id + ",'" + agenda.getDATE_AGENDA() + "','"
					+ agenda.getHEURE_AGENDA() + "' )");
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return id;
	}
	
	/*
	 * Operation d'insertion de la liste de cle primaire de la table AGENDA
	 * avant de pouvoir inserer la liste des RDV.
	 */
	public boolean insererListeAgenda(KListObject<KAgenda> Kliste)
	{
		for (int i = 0; i < Kliste.count(); i++)
		{
			Kliste.get(i).add(bdd.db);
		}
		return true;
	}
	
	/*
	 * recupere la liste des dates et horaire des leçons d'un élève.
	 */
	public KListObject<KAgenda> recupererAgendaRDV(int id)
	{
		KListObject<KAgenda> Kliste = new KListObject<KAgenda>(KAgenda.class);
		Kliste.loadFromDb(bdd.db ,
				"select * from agenda where id in (select idAgenda from assurer_lecon where idEleve = " + id + ")");
		KlisteAgendaRef = Kliste;
		return Kliste;
	}
	
	/*
	 * Creer la liste contenant toutes les cles primaires de Agenda non présente
	 * dans la BDD
	 */
	public KListObject<KAgenda> creerClePrimaireTableAgendaEtInsertionRDVnouveau(KListObject<KAssurer_lecon> RDV)
	{
		
		KListObject<KAgenda> KlisteAgenda = new KListObject<KAgenda>(KAgenda.class);
		KAgenda agenda = new KAgenda();
		
		for (int i = 0; i < KlisteAgendaGraphique.count(); i++)
		{
			if ((Integer) KlisteAgendaGraphique.get(i).getId() == -1)
			{
				agenda = new KAgenda();
				agenda.setDATE_AGENDA(KlisteAgendaGraphique.get(i).getDATE_AGENDA());
				agenda.setHEURE_AGENDA(KlisteAgendaGraphique.get(i).getHEURE_AGENDA());
				KlisteAgenda.add(agenda);
			}
			
		}
		
		int cleprimaire;
		for (int i = 0; i < KlisteAgenda.count(); i++)
		{
			cleprimaire = AjoutAgenda(KlisteAgenda.get(i) , RDV.get(i).getIdMONITEUR());
			System.out.println("fait-------------->");
			RDV.get(i).setIdAGENDA(cleprimaire);
			AjoutRDV(RDV.get(i));
		}
		
		return KlisteAgenda;
	}
	
	/*
	 * recupere un nouvel identifiant pour agenda (== max(id)+1 from Agenda)
	 */
	private int recupererDerniereCle(int idMoniteur)
	{
		
		System.out.println("avant requete, idmoniteur vaut : " + idMoniteur);
		KListObject<KAssurer_lecon> agenda = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		
		agenda.loadFromDb(bdd.db , "select * from assurer_lecon where idmoniteur = " + idMoniteur
				+ " order by idAgenda asc");
		
		System.out.println("Liste des RDV : ");
		System.out.println(agenda);
		System.out.println("----------------->");
		
		if (agenda.count() == 0)
		{
			return (Integer.parseInt(idMoniteur + "000"));
		}
		
		return (Integer) agenda.get(agenda.count() - 1).getIdAGENDA() + 1;
	}
	
	/*
	 * Retourne une Date particuliere d'un RDV d'un eleve
	 */
	public java.sql.Date recupererDateAgenda(int id)
	{
		KAgenda agenda = new KAgenda();
		try
		{
			agenda.loadOneById(bdd.db , id);
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
		
		return agenda.getDATE_AGENDA();
	}
	
	/*
	 * Retourne un heure particuliere d'un RDV d'un eleve.
	 */
	public java.sql.Time recupererHoraireAgenda(int id)
	{
		KAgenda agenda = new KAgenda();
		try
		{
			agenda.loadOneById(bdd.db , id);
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
		
		return agenda.getHEURE_AGENDA();
	}
	
	// --------ASSURER_LECON-------- //
	
	/*
	 * Supprimer un RDV d'un eleve
	 */
	public boolean supprimerRDV(KAssurer_lecon RDV)
	{
		System.out.println("Suppression TABLE ASSURERLECON");
		System.out.println("delete : " + RDV);
		RDV.delete(bdd.db);
		// KlisteRef = chargerListeRDVEleve(id);
		// REMETRE A JOUR LA LISTE DE REFERENCE POUR LELEVE !!!!!
		return true;
	}
	
	/*
	 * MAJ de RDV en BDD
	 */
	public boolean MAJRDV(KListObject<KAssurer_lecon> RDV)
	{
		System.out.println("MAJ TABLE ASSURERLECON");
		System.out.println(RDV);
		for (int i = 0; i < RDV.count(); i++)
		{
			RDV.get(i).update(bdd.db);
		}
		
		return true;
	}
	
	/*
	 * Insertion de RDV en BDD.
	 */
	public boolean AjoutRDV(KAssurer_lecon RDV)
	{
		System.out.println("insertion TABLE ASSURERLECON");
		System.out.println(RDV);
		
		RDV.add(bdd.db);
		
		return true;
	}
	
	/*
	 * Recuperer la liste des lecons d'un eleve sans ses dates et horaires.
	 */
	public KListObject<KAssurer_lecon> chargerListeRDVEleve(int id)
	{
		
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		Kliste.loadFromDb(bdd.db , "select * from assurer_lecon where idELEVE = " + id);
		
		KListObject<KAgenda> Kliste1 = new KListObject<KAgenda>(KAgenda.class);
		Kliste1.loadFromDb(bdd.db ,
				"select * from agenda where id in (select idAgenda from assurer_lecon where ideleve =" + id + ") "
						+ "order by date_agenda asc, heure_agenda asc");
		
		KListObject<KAssurer_lecon> Kliste2 = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		for (int i = 0; i < Kliste1.count(); i++)
		{
			for (int j = 0; j < Kliste.count(); j++)
			{
				if ((Integer) Kliste1.get(i).getId() == Kliste.get(j).getIdAGENDA())
				{
					Kliste2.add(Kliste.get(j));
				}
			}
		}
		
		KlisteRDVRef = Kliste2;
		return Kliste2;
	}
	
	/*
	 * Recupere une liste de lecon nouvelle d'un eleve en comparant avec celle
	 * existant dans la BDD pour ce meme eleve. le point de départ est donc
	 * KlisteRDVRef.count() pour recuperer juste les nouvelles leçons.
	 */
	public KListObject<KAssurer_lecon> recupererNouvelleLecon(KListObject<KAssurer_lecon> KlisteEntiere)
	{
		KListObject<KAssurer_lecon> KlisteNouvelle = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		for (int i = KlisteRDVRef.count(); i < KlisteEntiere.count(); i++)
		{
			KlisteNouvelle.add(KlisteEntiere.get(i));
			
		}
		return KlisteNouvelle;
	}
	
	/*
	 * Recupere la liste des RDV d'un eleve pour lequel il y a un equivalent en
	 * BDD. Tout est alors stocké dans KlisteRDVRef.
	 */
	public KListObject<KAssurer_lecon> recupererLeconExistante(KListObject<KAssurer_lecon> KlisteEntiere)
	{
		int nombreRDVexistant = KlisteRDVRef.count();
		KListObject<KAssurer_lecon> KlisteExistant = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		for (int i = 0; i < nombreRDVexistant; i++)
		{
			KlisteExistant.add(KlisteEntiere.get(i));
			
		}
		return KlisteExistant;
	}
	
	/*
	 * Conversion du tableau d'objets de la Jtable en Liste KAssurer_Lecon
	 */
	public KListObject<KAssurer_lecon> convertirTableau(Object [][] Tab, int idEleve)
	{
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		KAssurer_lecon Lecon;
		KAgenda agenda;
		KlisteAgendaGraphique.clear();// efface la collection pour en refaire
										// une
		for (int i = 0; i < Tab.length; i++)
		{
			Lecon = new KAssurer_lecon();
			Lecon.setIdELEVE(idEleve);
			Lecon.setIdAGENDA(creerIndiceAgenda(Tab[i][0] , Tab[i][1]));
			Lecon.setIdMONITEUR(recupererIdMoniteur(String.valueOf(Tab[i][3])));
			Lecon.setOBSERVATION_LECON(String.valueOf(Tab[i][4]));
			Lecon.setDUREE_LECON(verifierDuree(String.valueOf(Tab[i][2])));
			Kliste.add(Lecon);
			
			agenda = new KAgenda();
			agenda.setId(Lecon.getIdAGENDA());
			agenda.setDATE_AGENDA(transtyperDate(creerDate(String.valueOf((Tab[i][0])))));
			agenda.setHEURE_AGENDA(creerHoraire(String.valueOf((Tab[i][1]))));
			KlisteAgendaGraphique.add(agenda);
		}
		
		System.out.println(KlisteAgendaGraphique);
		return Kliste;
	}
	
	/*
	 * Efface un RDV posterieur à la dateActuelle
	 */
	public boolean effacerRDVListe(KListObject<KAssurer_lecon> KlisteGraphique)
	{
		
		/*
		 * KlisteRef a été initialisé des le double clic sur un eleve. On va
		 * donc supprimer en BDD les RDV supprimé graphiquement si la date est
		 * postérieur à la date actuelle (éviter la magouille).
		 */
		int index = 0; // le RDV de KlisteRef n'est pas present dans
						// KlisteGraphique : il a été supprimé.
		int i;
		int j;
		int cpt = 0;
		for (i = 0; i < KlisteRDVRef.count(); i++)
		{
			index = 0;
			cpt = 0;
			for (j = 0; j < KlisteGraphique.count(); j++)
			{
				index = 0;
				// System.out.println("Date Klisteref : "+KlisteRef.get(i).getIdAGENDA());
				// System.out.println("Date ListeGrap : "+KlisteGraphique.get(j).getIdAGENDA());
				
				if (KlisteRDVRef.get(i).getIdAGENDA() == KlisteGraphique.get(j).getIdAGENDA())
				{
					index = 1;
				}
				// System.out.println("index : "+index);
				if (index == 0)
				{
					cpt++;
				}
			}
			if (cpt == KlisteGraphique.count())
			{
				supprimerRDV(KlisteRDVRef.get(i));
			}
			
		}
		
		return true;
	}
	
	/*
	 * teste l'ensemble de la liste "graphique" pour tester les formats de
	 * données, cohérence de données.
	 */
	@SuppressWarnings("deprecation")
	public int gererRDVvide(KListObject<KAssurer_lecon> klisteGraphique)
	{
		
		java.sql.Date dateActuelle = new java.sql.Date(1 , 0 , 0);// 1900-12-31
																	// 00:00:00.0
																	// !!
		
		for (int j = 0; j < klisteGraphique.count(); j++)
		{
			numeroLigne = j;
			System.out.println("datecomparee : " + KlisteAgendaGraphique.get(j).getDATE_AGENDA());
			System.out.println("dateActuelle :" + dateActuelle);
			System.out.println("heurecomparee : " + KlisteAgendaGraphique.get(j).getHEURE_AGENDA());
			
			System.out.println(KlisteAgendaGraphique.get(j).getDATE_AGENDA().compareTo(dateActuelle));
			if (KlisteAgendaGraphique.get(j).getDATE_AGENDA().compareTo(dateActuelle) <= 0)
			{
				return -7;
			}
			if ((KlisteAgendaGraphique.get(j).getHEURE_AGENDA().getHours() == 0)
					&& (KlisteAgendaGraphique.get(j).getHEURE_AGENDA().getMinutes() == 0))
			{
				return -8;
			}
			
			String horaire = String.valueOf(KlisteAgendaGraphique.get(j).getHEURE_AGENDA().getHours()) + ":"
					+ String.valueOf(KlisteAgendaGraphique.get(j).getHEURE_AGENDA().getMinutes());
			int erreur = verifierHoraire(horaire);
			if (erreur != 1)
			{
				return erreur;
			}
			
			if (klisteGraphique.get(j).getDUREE_LECON() == 0)
			{
				return -5;
			}
			
			if (klisteGraphique.get(j).getIdMONITEUR() == -1)
			{
				return -6;
			}
			
		}
		
		return 1;
	}
	
	// ----------------------------------------- //
	// ---------------TRAITEMENTS--------------- //
	// ----------------------------------------- //
	
	/*
	 * retourne le message d'erreur ou de validation
	 */
	public String messageRenvoyeeUI(int num)
	{
		
		switch (num)
		{
			case 5:
				return "-\n";
				
			case 4:
				return "-\n";
				
			case 3:
				return "-\n";
				
			case 2:
				return "-L'ensemble des RDV ont été enregistrés en base de données\n";
				
			case 1:
				return "";
				
			case -1:
				return "-La date de RDV fixée ne peut pas être antérieur à la date actuelle\n";
				
			case -2:
				return "-L'horaire saisie est invalide : les heures sont compris entre 08h et 19h\n";
				
			case -3:
				return "-L'horaire saisie est invalide : les minutes sont compris entre 0 et 59.\n";
				
			case -4:
				return "-Les dates de RDV ne correpondent pas à un ordre chronologique.\n";
				
			case -5:
				return "-Une durée de leçon est invalide (expression absente ou format invalide).\n";
				
			case -6:
				return "-Un moniteur doit être selectionné pour chaque RDV.\n";
				
			case -7:
				return "-Une date de RDV a été modifié de façon invalide.\n";
				
			case -8:
				return "-Un horaire de leçon est invalide (expression absente ou format invalide).\n";
				
		}
		
		return "";
		
	}
	
	/*
	 * Verifie les bornes des heures,minutes
	 */
	public int verifierHoraire(String chaine)
	{
		System.out.println("chaine : " + chaine);
		String tabHoraire[] = chaine.split(":");
		
		if ((Integer.parseInt(tabHoraire[0]) < 8) || (Integer.parseInt(tabHoraire[0]) > 19))
		{
			return -2;
		}
		if ((Integer.parseInt(tabHoraire[1]) < 0) || (Integer.parseInt(tabHoraire[1]) >= 60))
		{
			return -3;
		}
		
		return 1;
	}
	
	/*
	 * verifie la chronologie des dates de RDV
	 */
	public int verifierChronologieDate()
	{
		java.sql.Date dateAvant = null;
		java.sql.Date dateApres = null;
		java.sql.Time heureAvant = null;
		java.sql.Time heureApres = null;
		
		java.sql.Timestamp dateAVANT = null;
		java.sql.Timestamp dateAPRES = null;
		
		int i = 1;
		if (KlisteAgendaGraphique.count() >= 2)
		{
			while (i != KlisteAgendaGraphique.count())
			{
				numeroLigne = i;
				dateAvant = KlisteAgendaGraphique.get(i - 1).getDATE_AGENDA();
				dateApres = KlisteAgendaGraphique.get(i).getDATE_AGENDA();
				heureAvant = KlisteAgendaGraphique.get(i - 1).getHEURE_AGENDA();
				heureApres = KlisteAgendaGraphique.get(i).getHEURE_AGENDA();
				dateAVANT = creerTimeStamp(dateAvant , heureAvant);
				dateAPRES = creerTimeStamp(dateApres , heureApres);
				
				System.out.println(dateAVANT);
				System.out.println(dateAPRES);
				System.out.println("comparaison : " + dateAVANT.compareTo(dateAPRES));
				
				if ((dateAVANT.compareTo(dateAPRES) <= 0))
				{	
					
				}
				else
				{
					return -4;
				}
				i++;
			}
		}
		
		return 1;
	}
	
	/*
	 * Creation d'un objet java.sql.TimeStamp à partir de java.sql.Date et
	 * java.sql.Ime
	 */
	@SuppressWarnings("deprecation")
	public java.sql.Timestamp creerTimeStamp(java.sql.Date date, java.sql.Time temps)
	{
		java.sql.Timestamp dateTimeStamp = new Timestamp(date.getYear() , date.getMonth() , date.getDate() ,
				temps.getHours() , temps.getMinutes() , temps.getSeconds() , 0);
		
		return dateTimeStamp;
	}
	
	/*
	 * creer une date au format SQL a partir dun type String
	 */
	@SuppressWarnings("deprecation")
	public java.util.Date creerDate(String chaine)
	{
		java.util.Date date = null;

		if (regexTraitementDate(chaine))
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				date = (java.util.Date) format.parse(chaine);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			date = new java.util.Date(0 , 0 , 0);// == 1899-12-31
		}
		return date;
		
	}
	
	/*
	 * Creer un temp au format SQL à partir d'un type String
	 */
	@SuppressWarnings("deprecation")
	public java.sql.Time creerHoraire(String chaine)
	{
		// System.out.println("chaine 1 : "+chaine);
		// System.out.println(regexTraitementHoraire(chaine));
		java.sql.Time horaire = new java.sql.Time(0 , 0 , 0);
		
		if (regexTraitementHoraire(chaine))
		{
			int debsequence = 0;
			String tabHoraire[] = chaine.split(":");
			
			if (tabHoraire[0].substring(0 , 1) == "0")
			{
				debsequence = 1;
			}
			
			horaire.setHours(Integer.parseInt(tabHoraire[0].substring(debsequence , 2)));
			debsequence = 0;
			if (tabHoraire[1].substring(0 , 1) == "0")
			{
				debsequence = 1;
			}
			horaire.setMinutes(Integer.parseInt(tabHoraire[1].substring(debsequence , 2)));
		}
		
		return horaire;
	}
	
	/*
	 * Comparaison d'une date avec celle de la date du jour
	 */
	public int verifierDate(java.sql.Timestamp dateComparee)
	{
		
		java.util.Date dateActuelle = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.format(dateActuelle);
		
		if (dateActuelle.compareTo(dateComparee) < 0)
		{	
			
		}
		else
		{
			return -1;
		}
		
		return 1;
	}
	
	/*
	 * transtypage de l'objet java.util.Date vers java.sql.Date
	 */
	@SuppressWarnings("deprecation")
	public java.sql.Date transtyperDate(java.util.Date date)
	{
		return new java.sql.Date(date.getYear() , date.getMonth() , date.getDate());
	}
	
	/*
	 * Retourne un indice pour voir si le format de la date et heure sont valide
	 * (ex : 2011-12-ertl) --> -1.
	 */
	@SuppressWarnings("deprecation")
	public int creerIndiceAgenda(Object date1, Object heure1)
	{
		
		java.util.Date date2 = creerDate(String.valueOf(date1));
		java.sql.Date date = new java.sql.Date(date2.getYear() , date2.getMonth() , date2.getDate());
		java.sql.Time heure = creerHoraire(String.valueOf(heure1));
		int index = -1;
		for (int i = 0; i < KlisteRDVRef.count(); i++)
		{
			
			if ((KlisteAgendaRef.get(i).getDATE_AGENDA().compareTo(date) == 0)
					&& KlisteAgendaRef.get(i).getHEURE_AGENDA().compareTo(heure) == 0)
			{
				index = (Integer) KlisteAgendaRef.get(i).getId();
			}
		}
		return index;
	}
	
	/*
	 * recuperation de l'id du moniteur à partir du tableau d'objet (pas moyen
	 * de trouver comment renvoyer la valeur de la Jcombobox de la Jtable
	 */
	public int recupererIdMoniteur(String chaine)
	{
		
		// System.out.println("chaine : "+chaine);
		KListObject<KMoniteur> Kliste = new DataMoniteur().recupererListe();
		for (KMoniteur moniteur : Kliste)
		{
			String chaineComparree = moniteur.getNOM_MONITEUR().toUpperCase() + " "
					+ moniteur.getPRENOM_MONITEUR().toLowerCase();
			if (chaineComparree.compareTo(chaine) == 0)
			{
				return (Integer) moniteur.getId();
			}
		}
		
		return -1;// pour les tests, soit c'est bon, soit ça plante.
		
	}
	
	/*
	 * retourne le nombre de séance pratique de conduite
	 */
	public int compterSeance()
	{
		// System.out.println("nombre de seance :"+KlisteRef.count());
		return KlisteRDVRef.count();
	}
	
	/*
	 * retourne le nombre d'heure passé à l'autoécole.
	 */
	public String compterHeure()
	{
		String somme;
		int minutes = 0;
		int heures = 0;
		int minutesModulo = 0;
		int minutesDivise = 0;
		int sommeHeure = 0;
		int sommeMinute = 0;
		for (int i = 0; i < KlisteRDVRef.count(); i++)
		{
			// parser en string.
			
			heures = Integer.parseInt(String.valueOf(KlisteRDVRef.get(i).getDUREE_LECON()).substring(0 , 1));
			minutes = Integer.parseInt(String.valueOf(KlisteRDVRef.get(i).getDUREE_LECON()).substring(1 , 3));
			
			// System.out.println("heures : "+heures+"   minutes : "+minutes);
			
			minutesModulo = minutes % 60;
			minutesDivise = minutes / 60;
			
			sommeMinute = sommeMinute + minutesModulo;
			sommeHeure = sommeHeure + heures + minutesDivise;
			
			// System.out.println("somme heure : "+sommeHeure+"    sommeMinutes : "+sommeMinute);
			
		}
		
		sommeHeure = sommeHeure + sommeMinute / 60;
		sommeMinute = sommeMinute % 60;
		if (sommeMinute == 0)
		{
			somme = String.valueOf(sommeHeure) + "h";
		}
		else
		{
			somme = String.valueOf(sommeHeure) + "h" + String.valueOf(sommeMinute);
		}
		
		return somme;
	}
	
	/*
	 * Remplace par zero si la regex n'est pas bonne
	 */
	public int verifierDuree(String chaine)
	{
		if (!regexTraitementDuree(chaine))
		{
			return 0;
		}
		return Integer.parseInt(EnleverCreerDureeJtable(chaine));
	}
	
	/*
	 * Creation graphique de la duree (format 2h30)
	 */
	public String creerDureeJtable(String chaine)
	{
		if (chaine.length() == 1)
		{
			return chaine + "h00";
		}
		else
		{
			return chaine.substring(0 , 1) + "h" + chaine.substring(1 , chaine.length());
		}
		
	}
	
	/*
	 * 2h30 --> 230
	 */
	public String EnleverCreerDureeJtable(String chaine)
	{
		String tabDuree[] = chaine.split("h");
		if (tabDuree[0].startsWith("0"))
		{
			tabDuree[0] = tabDuree[0].substring(1 , 2);
		}
		
		chaine = tabDuree[0] + tabDuree[1];
		// System.out.println(chaine);
		return chaine;
	}
	
	public KAssurer_lecon contrainteintegriteMoniteur(KListObject<KAssurer_lecon> KlisteGraphique)
	{
		
		// doit se faire sur toute la base ne filtrant sur la date et le
		// moniteur
		java.sql.Date date = null;
		java.sql.Time heureDebut = null;
		java.sql.Time heureFin = null;
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		int i = 0;
		
		for (i = 0; i < KlisteGraphique.count(); i++)
		{
			date = recupererDateAgenda(KlisteGraphique.get(i).getIdAGENDA());
			heureDebut = recupererHoraireAgenda(KlisteGraphique.get(i).getIdAGENDA());
			heureFin = creerHeureFin(KlisteGraphique.get(i).getDUREE_LECON() , heureDebut);
			
			Kliste.loadFromDb(bdd.db , "select * from assurer_lecon where idAgenda in (select id from agenda where"
					+ "date_agenda = '" + date + "' and heure_agenda between '" + heureDebut + "' and '" + heureFin
					+ "')" + " idMoniteur = " + KlisteGraphique.get(i).getIdMONITEUR());
			if (Kliste.count() != 0)
			{
				return Kliste.get(0);// On prend chaque RDV qui cloche et on
										// envoie un message d'erreur.
			}
		}
		
		return new KAssurer_lecon();// On retourne un RDV vide, temoin que ça
									// s'est bien passé.
		
		// OU ON RENVOIE UN STRING CONTENANT LA PHRASE POUR LE MESSAGE DERREUR.
	}
	
	@SuppressWarnings("deprecation")
	public java.sql.Time creerHeureFin(int duree_LECON, java.sql.Time temps)
	{
		
		int heure = Integer.parseInt(String.valueOf(duree_LECON).substring(0 , 1));
		int minutes = Integer.parseInt(String.valueOf(duree_LECON).substring(1 , 3));
		System.out.println("temps : " + temps);
		System.out.println("heure : " + heure + "    " + "minutes : " + minutes);
		java.sql.Time heureFin = new java.sql.Time(temps.getHours() + heure , temps.getMinutes() + minutes , 0);
		return heureFin;
	}
	
	public int contrainteintegriteEleve(KListObject<KAssurer_lecon> KlisteGraphique)
	{
		
		java.sql.Date date = null;
		java.sql.Time heureDebut = null;
		java.sql.Time heureFin = null;
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		@SuppressWarnings("unused")
		java.sql.Connection conn;
		int i = 0;
		
		for (i = 0; i < KlisteGraphique.count(); i++)
		{
			
			date = KlisteAgendaGraphique.get(KlisteAgendaRef.count() + i).getDATE_AGENDA();
			heureDebut = KlisteAgendaGraphique.get(KlisteAgendaRef.count() + i).getHEURE_AGENDA();
			heureFin = creerHeureFin(KlisteGraphique.get(i).getDUREE_LECON() , heureDebut);
			
			System.out.println("date  : " + date);
			System.out.println("heure debut : " + heureDebut);
			System.out.println("heure fin : " + heureFin);
			
			System.out.println("select * from assurer_lecon where idAgenda in (select id from agenda where"
					+ "date_agenda = '" + date + "' and heure_agenda between '" + heureDebut + "' and '" + heureFin
					+ "')" + "and idEleve = " + KlisteGraphique.get(i).getIdELEVE());
			// select * from assurer_lecon where idAgenda in (select id from
			// agenda where date_agenda = '2011-11-15' and heure_agenda between
			// '08:00:00' and '09:00:00') and idEleve = 5
			
			Kliste.loadFromDb(bdd.db , "select * from assurer_lecon where idAgenda in (select id from agenda where"
					+ " date_agenda = '" + date + "' and heure_agenda between '" + heureDebut + "' and '" + heureFin
					+ "')" + " and idEleve = " + KlisteGraphique.get(i).getIdELEVE());
			System.out.println("on compte donc : " + Kliste.count());
			if (Kliste.count() != 0)
			{
				System.out.println("");
				return KlisteAgendaRef.count() + i;// numero de ligne où ça
													// cloche.
			}
			
		}
		
		return -1; // L'eleve est disponible par rapport à tous ses RDV.
		
	}
	
	// //////////////////////////////////////////////////////////////
	// REGEX
	// //////////////////////////////////////////////////////////////
	
	public boolean regexTraitementDate(String chaine)
	{
		return Pattern.matches("^[0-9]{4,4}+-+[0-9]{2,2}+-+[0-9]{2,2}+$" , chaine);
	}
	
	public boolean regexTraitementHoraire(String chaine)
	{
		return Pattern.matches("^[0-9]{1,2}+:+[0-9]{1,2}+$" , chaine);
	}
	
	public boolean regexTraitementDuree(String chaine)
	{
		return Pattern.matches("^[0-9]{1,2}+h+[0-9]{1,2}+$" , chaine);
	}
	
	// ///////////////////////////////////////ACCESSEURS
	/**
	 * @return the klisteRef
	 */
	public KListObject<KAssurer_lecon> getKlisteRef()
	{
		return KlisteRDVRef;
	}
	
	/**
	 * @param klisteRef
	 *            the klisteRef to set
	 */
	public void setKlisteRef(KListObject<KAssurer_lecon> klisteRef)
	{
		KlisteRDVRef = klisteRef;
	}
	
}
