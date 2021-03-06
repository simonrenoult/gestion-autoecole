package controleur;

import vue.*;
import java.awt.Image;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import modele.DataMoniteur;
import net.ko.kobject.KListObject;
import KClass.KAssurer_lecon;
import KClass.KEleve;
import KClass.KMoniteur;

public class EcouteurPrincipal implements ActionListener, MouseListener, KeyListener, ChangeListener, WindowListener
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //

	private FenetrePrincipale	fenetre;
	private int					index;
	private Hashtable			correspondanceEleveRef	= new Hashtable();
	private Hashtable			correspondanceEleveVar	= new Hashtable();
	private Hashtable			correspondanceMoniteur	= new Hashtable();
	private String[]			ListeMoniteur;
	public static KEleve		Eleve;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public EcouteurPrincipal(FenetrePrincipale f)
	{
		fenetre = f;
		chargerListEleve();
		recupererListeMoniteur();
		EtatBoutonDebut();
	}

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	private void recupererListeMoniteur()
	{
		DataMoniteur moniteur = new DataMoniteur();
		KListObject<KMoniteur> KListe = new KListObject<KMoniteur>(KMoniteur.class);
		KListe = moniteur.recupererListe();
		ListeMoniteur = new String[KListe.count()];
		for (int i = 0 ; i < KListe.count() ; i++)
		{
			correspondanceMoniteur.put(i, (Integer) KListe.get(i).getId());
			ListeMoniteur[i] = KListe.get(i).getNOM_MONITEUR().toUpperCase() + " "
					+ KListe.get(i).getPRENOM_MONITEUR().toLowerCase();
		}
		correspondanceEleveVar = correspondanceEleveRef;
	}

	/*
	 * Rempli l'objet Jliste de la liste des �l�ves contenu en base de donn�e �
	 * partir de la liste charg�e.
	 */
	public void chargerListEleve()
	{
		fenetre.getJlisteEleves().setListData(recupererListeEleve().toArray());
	}

	/*
	 * Cr�ation d'une liste contenant nom et pr�nom des �l�ves.
	 */
	private ArrayList<String> recupererListeEleve()
	{
		ArrayList<String> listeEleves = new ArrayList<String>();
		KListObject<KEleve> KListe = new KListObject<KEleve>(KEleve.class);

		KListe = fenetre.getFicheEleve().getDataFiche1().recupererListe();

		for (int i = 0 ; i < KListe.count() ; i++)
		{
			correspondanceEleveRef.put(i, KListe.get(i).getId());
			listeEleves.add(KListe.get(i).getNOM_ELEVE().toUpperCase() + " "
					+ KListe.get(i).getPRENOM_ELEVE().toLowerCase());
		}

		return listeEleves;
	}

	/*
	 * Quand un double clic a �t� effectu�, on charge l'�l�ve et on affiche ses
	 * informations personelles.
	 */
	private void chargerDonneesFicheEleve(int id)
	{
		Eleve = new KEleve();
		Eleve = fenetre.getFicheEleve().getDataFiche1().recupererProfil(id);

		if (Eleve != null)
		{
			fenetre.getFicheEleve().setIdEleve(id);
			fenetre.getFicheEleve().getNom().setText(Eleve.getNOM_ELEVE());
			fenetre.getFicheEleve().getPrenom().setText(Eleve.getPRENOM_ELEVE());
			fenetre.getFicheEleve().getAdresse().setText(Eleve.getADRESSE_ELEVE());
			fenetre.getFicheEleve().getCodePostal().setText(Eleve.getCODE_POSTAL_ELEVE());
			fenetre.getFicheEleve().getCommuneEleve().setText(Eleve.getCOMMUNE_ELEVE());
			fenetre.getFicheEleve().getTelephone().setText(Eleve.getTELEPHONE_ELEVE());
			fenetre.getFicheEleve().geteMail().setText(Eleve.getMAIL_ELEVE());
			fenetre.getFicheEleve().getProfession().setText(Eleve.getPROFESSION_ELEVE());
			fenetre.getFicheEleve().getResultatEva()
					.setSelectedIndex(recupererIndexHeures(0, Eleve.getRESULTAT_ELEVE_ORAL()));
			fenetre.getFicheEleve().getFormaTheo()
					.setSelectedIndex(recupererIndexHeures(0, Eleve.getVOLUME_HORAIRE_TH_ELEVE()));
			fenetre.getFicheEleve().getFormaPra()
					.setSelectedIndex(recupererIndexHeures(0, Eleve.getVOLUME_HORAIRE_PRATIQUE_ELEVE()));
			fenetre.getFicheEleve().getNumLivret().setText(Eleve.getLIVRET_NEPH_ELEVE());
			fenetre.getFicheEleve().getResponsableForma().setSelectedIndex(0);

			// Besoin de recuperer le nom du moniteur a partir de la cle
			// etrangere de l'eleve.
			/*KMoniteur monit = new KMoniteur();
			DataMoniteur DataMoniteur = new DataMoniteur();
			monit = DataMoniteur.recupererProfilMoniteur(Eleve.getIdMONITEUR());*/
			fenetre.getFicheEleve().getFormateur().setSelectedIndex(recuperCle(Eleve.getIdMONITEUR()));
			
			// chargement des dates.
			Date date = new Date();
			date = Eleve.getDATE_DE_NAISS_ELEVE();
			SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");

			fenetre.getFicheEleve()
					.getDateNaissA()
					.setSelectedIndex(
							recupererIndexHeures(1900, Integer.parseInt(dateStandard.format(date).substring(6, 10))));
			fenetre.getFicheEleve()
					.getDateNaissM()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(3, 5))));
			fenetre.getFicheEleve()
					.getDateNaissJ()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(0, 2))));

			date = Eleve.getDATE_EVAL_ELEVE();

			fenetre.getFicheEleve()
					.getDateEvaJ()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(0, 2))));
			fenetre.getFicheEleve()
					.getDateEvaM()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(3, 5))));
			fenetre.getFicheEleve()
					.getDateEvaA()
					.setSelectedIndex(
							recupererIndexHeures(1900, Integer.parseInt(dateStandard.format(date).substring(6, 10))));

			date = Eleve.getDATE_ENREGISTREMENT_ELEVE();

			fenetre.getFicheEleve()
					.getDateEnregiA()
					.setSelectedIndex(
							recupererIndexHeures(1900, Integer.parseInt(dateStandard.format(date).substring(6, 10))));
			fenetre.getFicheEleve()
					.getDateEnregiM()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(3, 5))));
			fenetre.getFicheEleve()
					.getDateEnregiJ()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(0, 2))));

			date = Eleve.getDATE_INSCRIPTION_ELEVE();

			fenetre.getFicheEleve()
					.getDateInscriA()
					.setSelectedIndex(
							recupererIndexHeures(1900, Integer.parseInt(dateStandard.format(date).substring(6, 10))));
			fenetre.getFicheEleve()
					.getDateInscriM()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(3, 5))));
			fenetre.getFicheEleve()
					.getDateInscriJ()
					.setSelectedIndex(
							recupererIndexHeures(1, Integer.parseInt(dateStandard.format(date).substring(0, 2))));

			if (Eleve.getTEST_VU_ELEVE() == 1)
			{
				fenetre.getFicheEleve().getTestVueO().setSelected(true);
			}
			else
			{
				fenetre.getFicheEleve().getTestVueN().setSelected(true);
			}

			fenetre.getFicheEleve().getAreaTestVue().setText(Eleve.getOBSERVATION_VUE_ELEVE());

			String cheminPhoto = System.getProperty("user.dir");
			cheminPhoto = cheminPhoto+"\\photo_identite_eleve\\"+Eleve.getPHOTO_ELEVE();
			
			if(!(Eleve.getPHOTO_ELEVE() == null)){
				ImageIcon image = new ImageIcon(cheminPhoto);
				Image img = image.getImage();  
				Image newimg = img.getScaledInstance(fenetre.getFicheEleve().getLabelPhoto().getWidth(),fenetre.getFicheEleve().getLabelPhoto().getHeight(),  java.awt.Image.SCALE_SMOOTH);  
				ImageIcon newIcon = new ImageIcon(newimg); 
				fenetre.getFicheEleve().setImageEleve(newIcon);
				
				fenetre.getFicheEleve().getLabelPhoto().setIcon( fenetre.getFicheEleve().getImageEleve());
				fenetre.getFicheEleve().repaint();
				fenetre.getFicheEleve().setChemin(cheminPhoto);
				fenetre.getFicheEleve().repaint();
				
				
			}
			else{
				fenetre.getFicheEleve().getLabelPhoto().setIcon( null);
				fenetre.getFicheEleve().repaint();
				fenetre.getFicheEleve().setChemin(cheminPhoto);
				fenetre.getFicheEleve().repaint();

			}

			// On initialise le tableau de booleen � vrai (toutes les donn�es
			// charg�es sont correct)
			for (int i = 0 ; i < fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk().length ; i++)
			{
				fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[i] = true;
			}
		}
	}

	/*
	 * Permet de r�cup�rer l'indice de r�f�rence afin d'afficher la bonne valeur
	 * au sein de la liste (== selected).
	 */
	private int recupererIndexHeures(int depart, int nombre)
	{
		int i = 0;
		while (depart != nombre)
		{
			i++;
			depart++;
		}
		return i;
	}

	/*
	 * Lorque qu'on clique sur "nouveau", l'ensemble des �l�ments graphiques
	 * pr�sentent des champs vierges.
	 */
	private void RendreViergePanelFicheEleve()
	{
		fenetre.getFicheEleve().getNom().setText("");
		fenetre.getFicheEleve().getPrenom().setText("");
		fenetre.getFicheEleve().getAdresse().setText("");
		fenetre.getFicheEleve().getCodePostal().setText("");
		fenetre.getFicheEleve().getCommuneEleve().setText("");
		fenetre.getFicheEleve().getTelephone().setText("");
		fenetre.getFicheEleve().geteMail().setText("");
		fenetre.getFicheEleve().getProfession().setText("");
		fenetre.getFicheEleve().getNumLivret().setText("");
		fenetre.getFicheEleve().getAreaTestVue().setText("");
		fenetre.getFicheEleve().getResultatEva().setSelectedIndex(0);
		fenetre.getFicheEleve().getFormaPra().setSelectedIndex(0);
		fenetre.getFicheEleve().getFormaTheo().setSelectedIndex(0);
		fenetre.getFicheEleve().getFormateur().setSelectedIndex(0);
		fenetre.getFicheEleve().getTestVueN().setSelected(true);
		fenetre.getFicheEleve().getLabelPhoto().setIcon(null);
		//fenetre.getFicheEleve().setIdMoniteur(0);
		fenetre.getFicheEleve().repaint();

		// CHANGER LA COULEUR DES BORDURES.

		parametrerDateDefault();

	}

	/*
	 * Permet de remettre les dates � la date actuelle.
	 */
	private void parametrerDateDefault()
	{

		fenetre.getFicheEleve().getDateEnregiA().removeAllItems();
		fenetre.getFicheEleve().getDateEnregiJ().removeAllItems();
		fenetre.getFicheEleve().getDateEnregiM().removeAllItems();
		fenetre.getFicheEleve().getDateEvaA().removeAllItems();
		fenetre.getFicheEleve().getDateEvaJ().removeAllItems();
		fenetre.getFicheEleve().getDateEvaM().removeAllItems();
		fenetre.getFicheEleve().getDateInscriA().removeAllItems();
		fenetre.getFicheEleve().getDateInscriJ().removeAllItems();
		fenetre.getFicheEleve().getDateInscriM().removeAllItems();
		fenetre.getFicheEleve().getDateNaissA().removeAllItems();
		fenetre.getFicheEleve().getDateNaissJ().removeAllItems();
		fenetre.getFicheEleve().getDateNaissM().removeAllItems();
		
		fenetre.getFicheEleve().parametrerJComboBoxDate(fenetre.getFicheEleve().getDateEnregiJ(),
				fenetre.getFicheEleve().getDateEnregiM(), fenetre.getFicheEleve().getDateEnregiA());
		fenetre.getFicheEleve().parametrerJComboBoxDate(fenetre.getFicheEleve().getDateEvaJ(),
				fenetre.getFicheEleve().getDateEvaM(), fenetre.getFicheEleve().getDateEvaA());
		fenetre.getFicheEleve().parametrerJComboBoxDate(fenetre.getFicheEleve().getDateInscriJ(),
				fenetre.getFicheEleve().getDateInscriM(), fenetre.getFicheEleve().getDateInscriA());
		fenetre.getFicheEleve().parametrerJComboBoxDate(fenetre.getFicheEleve().getDateNaissJ(),
				fenetre.getFicheEleve().getDateNaissM(), fenetre.getFicheEleve().getDateNaissA());

	}

	/*
	 * renvoie l'�tat des diff�rent boutons au lancement de l'apllication. Un
	 * minimum d'�tat doit apparaitre �tant donn� qu'un �l�ve n'a pas encore �t�
	 * selectionn�.
	 */
	private void EtatBoutonDebut()
	{
		fenetre.getBoutonFicheE().setEnabled(true);
		fenetre.getBoutonEtape1().setEnabled(false);
		fenetre.getBoutonEtape2().setEnabled(false);
		fenetre.getBoutonEtape3().setEnabled(false);
		fenetre.getBoutonEtape4().setEnabled(false);
		fenetre.getBoutonIntero().setEnabled(false);
		fenetre.getBoutonExamB().setEnabled(false);
		fenetre.getBoutonValider().setEnabled(true);
		for (int i = 0 ; i < 4 ; i++)
			fenetre.getEtape()[i].setVisible(false);
		fenetre.getFicheEleve().setVisible(true);
		fenetre.getIntero().setVisible(false);
		fenetre.getExamB().setVisible(false);
	}

	/*
	 * Permet d'afficher les noms et pr�noms des �l�ves suivant un filtre
	 * (caract�re tap�s par l'utilisateur).
	 */
	private void filtreRechercheNomPrenomEleve()
	{
		ArrayList<String> listeEleves = new ArrayList<String>();
		listeEleves =  recupererListeEleve();
		//On met � jour la plus r�cente des listes d'�l�ves
		fenetre.getListeEleves().setListData(listeEleves.toArray());
		//on recupere la chaine frapp� dans la barre recherche.
		String chaineRecherche = fenetre.getRechercheE().getText();
		//on recupere la taille de la liste de type JlistModel contenu dans la Jlist.
		int tailleListeEleve =listeEleves.size();
		correspondanceEleveVar = correspondanceEleveRef;
		String tabEleveRecherche[] = new String[tailleListeEleve];
		int cpt = 0;
		for(int i=0; i<tailleListeEleve; i++) {
			// On recupere une a une les chaines de la Jlist
			String chaineListe = fenetre.getListeEleves().getModel().getElementAt(i).toString();
			if(chaineListe.toUpperCase().indexOf(chaineRecherche)!=-1 || 
					chaineListe.toUpperCase().indexOf(chaineRecherche.toUpperCase())!=-1)
			{
				correspondanceEleveVar.put(cpt, correspondanceEleveRef.get(i));
				//System.out.println("cpt : "+cpt+"id :"+correspondanceEleveRef.get(i));
				tabEleveRecherche[cpt] = chaineListe;
				cpt++;
			}
			
		}

		// On redefinit un tableau pour la taille.
		String tabEleve[] = new String[cpt];
		for (int i = 0 ; i < cpt ; i++)
			tabEleve[i] = tabEleveRecherche[i];

		fenetre.getListeEleves().setListData(tabEleve);
	}

	/**
	 * La MAJ graphique de la JTABLE se base sur le Tableau. On efface tout en
	 * rempla�ant par un tableau vide
	 */
	private void RendreViergeTableauLecon()
	{
		Object[][] data = new Object[0][6];
		fenetre.getFicheEleve().getContainertableauLecon().getJModel().setData(data);
		fenetre.getFicheEleve().getContainertableauLecon().getJModel().fireTableDataChanged();
		fenetre.getFicheEleve().getContainertableauLecon().getNombreHeurePratique().setText("");
		fenetre.getFicheEleve().getContainertableauLecon().getNombreLecon().setText("");
	}

	/**
	 * On construit notre tableau d'objet de donnee pour l'injecter dans la
	 * JTable
	 */
	public void chargerDonneesTableauLecon()
	{
		int cpt = 0;
		String supp = "Supprimer le RDV";
		KListObject<KAssurer_lecon> liste = fenetre.getFicheEleve().getTableauLecon().chargerListeRDVEleve(((Long)Eleve.getId()).intValue());
		fenetre.getFicheEleve().getContainertableauLecon().getJModel().setLigneRef(liste.count());
		
		Object[][] data  = new Object[liste.count()][6] ;
		
		for(KAssurer_lecon RDV : liste){
			data[cpt][0] = String.valueOf(RDV.getAgenda().getDATE_AGENDA().toString().substring(0,10));
			data[cpt][1] = String.valueOf(RDV.getAgenda().getHEURE_AGENDA().toString().substring(0,5));
			data[cpt][2] = fenetre.getFicheEleve().getTableauLecon().creerDureeJtable(String.valueOf(RDV.getDUREE_LECON()));
			data[cpt][3] = ListeMoniteur[recuperCle((Integer)RDV.getIdMONITEUR())]; 
			data[cpt][4] = RDV.getOBSERVATION_LECON();
			data[cpt][5] = supp;
			cpt++;
		}
		fenetre.getFicheEleve().getTableauLecon().recupererAgendaRDV(((Long)Eleve.getId()).intValue());
		fenetre.getFicheEleve().getContainertableauLecon().initialiserJlabel();
		fenetre.getFicheEleve().getContainertableauLecon().getCombo().setModel(new DefaultComboBoxModel(ListeMoniteur));
		fenetre.getFicheEleve().getContainertableauLecon().getJModel().setData(data);
		fenetre.getFicheEleve().getContainertableauLecon().getJModel().fireTableDataChanged();
	}

	/**
	 * On recupere la cle d'un moniteur a partir de son id
	 * 
	 * @param id
	 * @return
	 */
	private int recuperCle(int id)
	{
		@SuppressWarnings("rawtypes")
		Enumeration it = correspondanceMoniteur.keys();

		while (it.hasMoreElements())
		{
			Object key = it.nextElement();
			Object value = correspondanceMoniteur.get(key);

			if (id == ((Integer) value))
			{
				return (Integer) key;
			}
		}

		return 0;
	}

	private void changerEtatBouton()
	{
		fenetre.getBoutonValider().setText("Enregistrer");
		fenetre.getBoutonValider().setVisible(true);
	}

	// ----------------------------------------- //
	// ----------------LISTENERS---------------- //
	// ----------------------------------------- //

	public void actionPerformed(ActionEvent e)
	{
		fenetre.getBoutonFicheE().setSelected(false);
		fenetre.getBoutonEtape1().setSelected(false);
		fenetre.getBoutonEtape2().setSelected(false);
		fenetre.getBoutonEtape3().setSelected(false);
		fenetre.getBoutonEtape4().setSelected(false);
		fenetre.getBoutonIntero().setSelected(false);
		fenetre.getBoutonExamB().setSelected(false);
		fenetre.getBoutonFicheE().setSelected(false);
		
		if (e.getSource() == fenetre.getBoutonFicheE())
		{
			fenetre.getFicheEleve().setVisible(true);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(false);
			fenetre.getBoutonFicheE().setSelected(true);

			if (fenetre.getFicheEleve().getOnglet().getSelectedIndex() == 0)
			{
				fenetre.getBoutonValider().setText("Mettre � jour");
			}

		}
		else if (e.getSource() == fenetre.getBoutonEtape1())
		{
			fenetre.getFicheEleve().setVisible(false);
			fenetre.getEtape1().setVisible(true);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(false);
			fenetre.getBoutonEtape1().setSelected(true);
			changerEtatBouton();
			fenetre.getEtape1().getObjectifs().chargerEtatsEtObervations();

		}
		else if (e.getSource() == fenetre.getBoutonEtape2())
		{
			fenetre.getFicheEleve().setVisible(false);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(true);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(false);
			fenetre.getBoutonEtape2().setSelected(true);
			fenetre.getEtape2().getObjectifs().chargerEtatsEtObervations();
			changerEtatBouton();
		}
		else if (e.getSource() == fenetre.getBoutonEtape3())
		{
			fenetre.getFicheEleve().setVisible(false);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(true);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(false);
			fenetre.getEtape3().getObjectifs().chargerEtatsEtObervations();
			changerEtatBouton();
		}
		else if (e.getSource() == fenetre.getBoutonEtape4())
		{
			fenetre.getFicheEleve().setVisible(false);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(true);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(false);
			fenetre.getEtape4().getObjectifs().chargerEtatsEtObervations();
			changerEtatBouton();
		}
		else if (e.getSource() == fenetre.getBoutonIntero())
		{
			fenetre.getFicheEleve().setVisible(false);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(true);
			fenetre.getExamB().setVisible(false);
			changerEtatBouton();
			fenetre.getIntero().chargerReponsesEleve();
		}
		else if (e.getSource() == fenetre.getBoutonExamB())
		{
			fenetre.getFicheEleve().setVisible(false);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(true);
			changerEtatBouton();
		}

		else if (e.getSource() == fenetre.getBoutonAjouterEleve())
		{

			fenetre.getFicheEleve().setVisible(true);
			fenetre.getEtape1().setVisible(false);
			fenetre.getEtape2().setVisible(false);
			fenetre.getEtape3().setVisible(false);
			fenetre.getEtape4().setVisible(false);
			fenetre.getIntero().setVisible(false);
			fenetre.getExamB().setVisible(false);
			fenetre.getBoutonFicheE().setEnabled(true);
			fenetre.getBoutonEtape1().setEnabled(false);
			fenetre.getBoutonEtape2().setEnabled(false);
			fenetre.getBoutonEtape3().setEnabled(false);
			fenetre.getBoutonEtape4().setEnabled(false);
			fenetre.getBoutonIntero().setEnabled(false);
			fenetre.getBoutonExamB().setEnabled(false);
			// TODO : mettre a�"true" si examen blanc utilise !!
			fenetre.getBoutonValider().setEnabled(true);
			fenetre.getFicheEleve().getOnglet().setSelectedIndex(0);

			RendreViergePanelFicheEleve();
			RendreViergeTableauLecon();
			fenetre.getFicheEleve().couleurDefaultChamps();
			parametrerDateDefault();
			index = 0;

			int selectedTab = fenetre.getFicheEleve().getOnglet().getSelectedIndex();
			if (selectedTab == 0){
				if (index != 0){
					fenetre.getBoutonValider().setText("Mettre � jour");
				}
				else{
					fenetre.getBoutonValider().setText("Ajouter");
				}

			}
			else if (selectedTab == 1)
			{
				fenetre.getBoutonValider().setText("Enregistrer");
			}
		}
	}

	// --------MOUSE-------- //

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}

	public void mousePressed(MouseEvent e)
	{

	}

	public void mouseReleased(MouseEvent e)
	{
		if (e.getSource() == fenetre.getListeEleves() && e.getClickCount() == 2)
		{

			@SuppressWarnings("static-access")
			int switchEleve = new JOptionPane().showConfirmDialog(null, "Voulez vous vraiment changer d'�l�ve?\n",
					"Cnger d'�l�ve", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (switchEleve == JOptionPane.OK_OPTION)
			{
				int selectedTab = fenetre.getFicheEleve().getOnglet().getSelectedIndex();
				if (selectedTab == 0)
				{
					fenetre.getBoutonValider().setText("Mettre � jour");
				}
				else if (selectedTab == 1)
				{
					fenetre.getBoutonValider().setText("Enregistrer");
				}

				// On fait la correspondance entre le num�ro de la personne
				// selectionn� et son ID � charger.
				index = fenetre.getListeEleves().getSelectedIndex();

				int IdEleve = ((Long) correspondanceEleveVar.get(index)).intValue();
				fenetre.getFicheEleve().setIdEleve(IdEleve);
				chargerDonneesFicheEleve(IdEleve);
				chargerDonneesTableauLecon();

				fenetre.getFicheEleve().setVisible(true);
				fenetre.getEtape1().setVisible(false);
				fenetre.getEtape2().setVisible(false);
				fenetre.getEtape3().setVisible(false);
				fenetre.getEtape4().setVisible(false);
				fenetre.getIntero().setVisible(false);
				fenetre.getExamB().setVisible(false);
				fenetre.getBoutonFicheE().setEnabled(true);
				fenetre.getBoutonEtape1().setEnabled(true);
				fenetre.getBoutonEtape2().setEnabled(true);
				fenetre.getBoutonEtape3().setEnabled(true);
				fenetre.getBoutonEtape4().setEnabled(true);
				fenetre.getBoutonIntero().setEnabled(true);
				// A mettre � vrai des que l'exam est pret
				fenetre.getBoutonExamB().setEnabled(false);
				fenetre.getBoutonValider().setEnabled(true);
			}
		}
		else if (e.getSource() == fenetre.getRechercheE() && e.getClickCount() == 1)
		{
			fenetre.getRechercheE().setText("");
		}
	}

	// --------KEY-------- //

	public void keyPressed(KeyEvent e)
	{

	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getSource() == fenetre.getRechercheE())
		{
			filtreRechercheNomPrenomEleve();
		}
	}

	public void keyTyped(KeyEvent e)
	{

	}

	// --------STATE-------- //

	@Override
	public void stateChanged(ChangeEvent e)
	{
		if (e.getSource() == fenetre.getFicheEleve().getOnglet())
		{
			int selectedTab = fenetre.getFicheEleve().getOnglet().getSelectedIndex();
			if (selectedTab == 0)
			{
				if (index == 0)
				{// si on n'avait pas selectionn� d'eleve avant
					fenetre.getBoutonValider().setText("Ajouter");
				}
				else
				{
					fenetre.getBoutonValider().setText("Mettre � jour");
				}

			}
			else if (selectedTab == 1)
			{
				fenetre.getBoutonValider().setText("Enregistrer");
			}
		}

	}

	// --------WINDOW-------- //

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		@SuppressWarnings("static-access")
		int option = new JOptionPane().showConfirmDialog(null, "Voulez-vous quitter l'application ?", "Quitter",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.OK_OPTION)
		{
			System.exit(0);
		}
		else
		{
			fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}

	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	public Hashtable getCorrespondanceMoniteur() {
		// TODO Auto-generated method stub
		return correspondanceMoniteur;
	}

}
