package vue;


import controleur.*;
import javax.swing.*;
import vue.etape.VueEtape;
import modele.BDD;
import java.awt.*;

public class FenetrePrincipale extends JFrame {

	
	private static final long serialVersionUID = 1L;
	BDD bdd = new BDD();
	//Classes
	
	private FicheEleve ficheEleve = new FicheEleve();
	private VueEtape[] etape = new VueEtape[4];
	private InteroOrale intero = new InteroOrale();
	private ExamenBlanc examB = new ExamenBlanc();

	//Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("PeriGest");
	private JMenu eleve = new JMenu("?");
	private JMenu moniteur = new JMenu("Moniteurs");
	private JMenuItem importerLivret = new JMenuItem("Importer un livret");
	private JMenuItem exporterLivret = new JMenuItem(
			"Exporter le livret actuel");
	private JMenuItem exporterLivrets = new JMenuItem(
	"Exporter les livrets de tous les �l�ves");
	private JMenuItem imprimerLivret = new JMenuItem(
			"Imprimer le livret actuel");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem gestionM = new JMenuItem("Gestion");

	//Composant Panel droite.
	private BoutonGroupe BoutonFicheE = new BoutonGroupe("Fiche Eleve");
	private BoutonGroupe BoutonEtape1 = new BoutonGroupe("Etape 1");
	private BoutonGroupe BoutonEtape2 = new BoutonGroupe("Etape 2");
	private BoutonGroupe BoutonEtape3 = new BoutonGroupe("Etape 3");
	private BoutonGroupe BoutonEtape4 = new BoutonGroupe("Etape 4");
	private BoutonGroupe BoutonIntero = new BoutonGroupe("Interrogation orale");
	private BoutonGroupe BoutonExamB = new BoutonGroupe("Examen Blanc");
	private JButton BoutonValider = new JButton("Ajouter");
	private JButton BoutonSupprimer = new JButton("Supprimer");
	private JButton boutonAjouterEleve = new JButton("Nouveau");
	private ButtonGroup groupeBoutonTheme = new ButtonGroup();
	private JTextField rechercheE = new JTextField("Rechercher...");
	private String tabEleve[];
	private JList JlisteEleves;
	private EcouteurPrincipale ecouteurPrincipale = null;
	
	
	//VUE(modele)
	//controleur (modele,vue).
	////////////////////////////////////////////////////CONSTRUCTEURS

	public FenetrePrincipale() {

		this.setTitle("Auto-Ecole");
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creation des �tapes.
		for (int i = 0; i < 4; i++) {
			etape[i] = new VueEtape(i);
		}

		JlisteEleves = new JList();
		creationMenu();
		
		//Creation du panel principal
		JPanel containerP = new JPanel();
		JScrollPane scroll = new JScrollPane(containerP);
		containerP.setLayout(new BorderLayout());
		containerP.add(CreationPanelGauche(), BorderLayout.WEST);
		containerP.add(CreationPanelDroite(), BorderLayout.CENTER);
		//Ajout d'objets a ActionListener
		ajouterActionListener();
		

		this.setContentPane(scroll);
		this.setVisible(true);
	}
////////////////////////////////////////////////////////METHODES
	
	/*
	 * Permet d'�couter les objets � la classe EcouteurPrincipale 
	 */
	public void ajouterActionListener() {
		ecouteurPrincipale = new EcouteurPrincipale(this);
		EcouteurBoutonValidationSuppression ecouteurBouton = new EcouteurBoutonValidationSuppression(this);
		BoutonFicheE.addActionListener(ecouteurPrincipale);
		BoutonEtape1.addActionListener(ecouteurPrincipale);
		BoutonEtape2.addActionListener(ecouteurPrincipale);
		BoutonEtape3.addActionListener(ecouteurPrincipale);
		BoutonEtape4.addActionListener(ecouteurPrincipale);
		BoutonIntero.addActionListener(ecouteurPrincipale);
		BoutonExamB.addActionListener(ecouteurPrincipale);
		JlisteEleves.addMouseListener(ecouteurPrincipale);
		rechercheE.addMouseListener(ecouteurPrincipale);
		rechercheE.addKeyListener(ecouteurPrincipale);
		importerLivret.addActionListener(ecouteurPrincipale);
		exporterLivret.addActionListener(ecouteurPrincipale);
		exporterLivrets.addActionListener(ecouteurPrincipale);
		imprimerLivret.addActionListener(ecouteurPrincipale);
		quitter.addActionListener(ecouteurPrincipale);
		gestionM.addActionListener(ecouteurPrincipale);
		boutonAjouterEleve.addActionListener(ecouteurPrincipale);
		
		BoutonValider.addActionListener(ecouteurBouton);
		BoutonSupprimer.addActionListener(ecouteurBouton);
		
		ficheEleve.getOnglet().addChangeListener(ecouteurPrincipale);
		
		this.addWindowListener(ecouteurPrincipale);
	}

	/*
	 * Creation du menu graphique
	 */
	public void creationMenu() {
		fichier.add(importerLivret);
		fichier.add(exporterLivret);
		fichier.add(exporterLivrets);
		fichier.add(imprimerLivret);
		fichier.add(quitter);
		moniteur.add(gestionM);
		menuBar.add(fichier);
		menuBar.add(moniteur);
		menuBar.add(eleve);
		setJMenuBar(menuBar);
		
	}

	/*
	 * cr�ation du panel comportant les diff�rents panel (etape, fiche eleve, etc.)
	 */
	public JPanel CreationPanelDroite() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(520,630));
		
		//ajout des sous panel le constituant (un pannel par bouton)
		panel.add(ficheEleve,BorderLayout.NORTH);
		for (int i = 0; i < 4; i++)
			panel.add(etape[i],BorderLayout.NORTH);
		panel.add(intero,BorderLayout.NORTH);
		panel.add(examB,BorderLayout.NORTH);
		//ajout des boutons de confimation et annulation
		JPanel containerBoutonsConfirm = new JPanel();
		containerBoutonsConfirm.setPreferredSize(new Dimension(400, 50));
		containerBoutonsConfirm.add(BoutonValider);
		containerBoutonsConfirm.add(BoutonSupprimer);
		
		panel.add(containerBoutonsConfirm,BorderLayout.SOUTH);
		
		return panel;
	}
	
	/*
	 *cr�ation du panel comportant les diff�rents boutons (etape, fiche eleve, etc.)
	 */
	public JPanel CreationPanelGauche() {
		
		groupeBoutonTheme.add(BoutonFicheE);
		groupeBoutonTheme.add(BoutonEtape1);
		groupeBoutonTheme.add(BoutonEtape2);
		groupeBoutonTheme.add(BoutonEtape3);
		groupeBoutonTheme.add(BoutonEtape4);
		groupeBoutonTheme.add(BoutonIntero);
		
		//Creation panel des boutons
		JPanel containerBoutons = new JPanel();
		containerBoutons.setPreferredSize(new Dimension(200, 250));
		containerBoutons.setLayout(new GridLayout(6, 1));
		containerBoutons.add(BoutonFicheE);
		containerBoutons.add(BoutonEtape1);
		containerBoutons.add(BoutonEtape2);
		containerBoutons.add(BoutonEtape3);
		containerBoutons.add(BoutonEtape4);
		containerBoutons.add(BoutonIntero);
		
		//Creation composant de recherche et du libelle "Eleve"
		JLabel texteEleves = new JLabel("ELEVES");
		texteEleves.setPreferredSize(new Dimension(80, 20));
		texteEleves.setFont(new Font(null, Font.BOLD, 13));
		rechercheE.setPreferredSize(new Dimension(196, 20));
		boutonAjouterEleve.setPreferredSize(new Dimension(90, 20));
		
		//Creation du scroll li� � la Jlist
		JScrollPane scrollListeE = new JScrollPane(JlisteEleves);
		scrollListeE.setPreferredSize(new Dimension(196, 295));
		
		//creation du panel ListeEleve
		JPanel containerListeEleves = new JPanel();
		containerListeEleves.setPreferredSize(new Dimension(200, 350));
		containerListeEleves.add(texteEleves);
		containerListeEleves.add(boutonAjouterEleve);
		containerListeEleves.add(rechercheE);
		containerListeEleves.add(scrollListeE);
		
		//creation du panel final contenant les 2 panels cr��s dessus
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 640));
		panel.add(containerListeEleves);
		panel.add(containerBoutons);
		
		return panel;
		
	}

	
	////////////////////////////////////////////////////////GETTERS/SETTERS
	
	public VueEtape[] getEtape(){
		return etape;
	}
	
	public VueEtape getEtape1() {
		return etape[0];
	}

	public VueEtape getEtape2() {
		return etape[1];
	}

	public VueEtape getEtape3() {
		return etape[2];
	}

	public VueEtape getEtape4() {
		return etape[3];
	}

	public ExamenBlanc getExamB() {
		return examB;
	}

	public FicheEleve getFicheEleve() {
		return ficheEleve;
	}

	public InteroOrale getIntero() {
		return intero;
	}

	
	public JButton getBoutonSupprimer() {
		return BoutonSupprimer;
	}

	public JButton getBoutonValider() {
		return BoutonValider;
	}

	public JList getListeEleves() {
		return JlisteEleves;
	}

	public JTextField getRechercheE() {
		return rechercheE;
	}

	public String[] getTabEleve() {
		return tabEleve;
	}

	public void setListeEleves(JList listeEleves) {
		this.JlisteEleves = listeEleves;
	}

	public JMenuItem getQuitter() {
		return quitter;
	}

	public void setFicheEleve(FicheEleve ficheEleve) {
		this.ficheEleve = ficheEleve;
	}

	public JMenuItem getMoniteur() {
		return moniteur;
	}

	public JButton getBoutonAjouterEleve() {
		return boutonAjouterEleve;
	}

	public JMenuItem getGestionM() {
		return gestionM;
	}




	/**
	 * @return the jlisteEleves
	 */
	public JList getJlisteEleves() {
		return JlisteEleves;
	}




	/**
	 * @param jlisteEleves the jlisteEleves to set
	 */
	public void setJlisteEleves(JList jlisteEleves) {
		JlisteEleves = jlisteEleves;
	}

	/**
	 * @return the ecouteurPrincipale
	 */
	public EcouteurPrincipale getEcouteurPrincipale() {
		return ecouteurPrincipale;
	}

	/**
	 * @param ecouteurPrincipale the ecouteurPrincipale to set
	 */
	public void setEcouteurPrincipale(EcouteurPrincipale ecouteurPrincipale) {
		this.ecouteurPrincipale = ecouteurPrincipale;
	}

	/**
	 * @return the groupeBoutonTheme
	 */
	public ButtonGroup getGroupeBoutonTheme() {
		return groupeBoutonTheme;
	}

	/**
	 * @param groupeBoutonTheme the groupeBoutonTheme to set
	 */
	public void setGroupeBoutonTheme(ButtonGroup groupeBoutonTheme) {
		this.groupeBoutonTheme = groupeBoutonTheme;
	}

	/**
	 * @return the boutonFicheE
	 */
	public BoutonGroupe getBoutonFicheE() {
		return BoutonFicheE;
	}

	/**
	 * @param boutonFicheE the boutonFicheE to set
	 */
	public void setBoutonFicheE(BoutonGroupe boutonFicheE) {
		BoutonFicheE = boutonFicheE;
	}

	/**
	 * @return the boutonEtape1
	 */
	public BoutonGroupe getBoutonEtape1() {
		return BoutonEtape1;
	}

	/**
	 * @param boutonEtape1 the boutonEtape1 to set
	 */
	public void setBoutonEtape1(BoutonGroupe boutonEtape1) {
		BoutonEtape1 = boutonEtape1;
	}

	/**
	 * @return the boutonEtape2
	 */
	public BoutonGroupe getBoutonEtape2() {
		return BoutonEtape2;
	}

	/**
	 * @param boutonEtape2 the boutonEtape2 to set
	 */
	public void setBoutonEtape2(BoutonGroupe boutonEtape2) {
		BoutonEtape2 = boutonEtape2;
	}

	/**
	 * @return the boutonEtape3
	 */
	public BoutonGroupe getBoutonEtape3() {
		return BoutonEtape3;
	}

	/**
	 * @param boutonEtape3 the boutonEtape3 to set
	 */
	public void setBoutonEtape3(BoutonGroupe boutonEtape3) {
		BoutonEtape3 = boutonEtape3;
	}

	/**
	 * @return the boutonEtape4
	 */
	public BoutonGroupe getBoutonEtape4() {
		return BoutonEtape4;
	}

	/**
	 * @param boutonEtape4 the boutonEtape4 to set
	 */
	public void setBoutonEtape4(BoutonGroupe boutonEtape4) {
		BoutonEtape4 = boutonEtape4;
	}

	/**
	 * @return the boutonIntero
	 */
	public BoutonGroupe getBoutonIntero() {
		return BoutonIntero;
	}

	/**
	 * @param boutonIntero the boutonIntero to set
	 */
	public void setBoutonIntero(BoutonGroupe boutonIntero) {
		BoutonIntero = boutonIntero;
	}

	/**
	 * @return the boutonExamB
	 */
	public BoutonGroupe getBoutonExamB() {
		return BoutonExamB;
	}

	/**
	 * @param boutonExamB the boutonExamB to set
	 */
	public void setBoutonExamB(BoutonGroupe boutonExamB) {
		BoutonExamB = boutonExamB;
	}

	/**
	 * @return the importerLivret
	 */
	public JMenuItem getImporterLivret() {
		return importerLivret;
	}

	/**
	 * @param importerLivret the importerLivret to set
	 */
	public void setImporterLivret(JMenuItem importerLivret) {
		this.importerLivret = importerLivret;
	}

	/**
	 * @return the exporterLivret
	 */
	public JMenuItem getExporterLivret() {
		return exporterLivret;
	}

	/**
	 * @param exporterLivret the exporterLivret to set
	 */
	public void setExporterLivret(JMenuItem exporterLivret) {
		this.exporterLivret = exporterLivret;
	}

	/**
	 * @return the exporterLivrets
	 */
	public JMenuItem getExporterLivrets() {
		return exporterLivrets;
	}

	/**
	 * @param exporterLivrets the exporterLivrets to set
	 */
	public void setExporterLivrets(JMenuItem exporterLivrets) {
		this.exporterLivrets = exporterLivrets;
	}

	/**
	 * @return the imprimerLivret
	 */
	public JMenuItem getImprimerLivret() {
		return imprimerLivret;
	}

	/**
	 * @param imprimerLivret the imprimerLivret to set
	 */
	public void setImprimerLivret(JMenuItem imprimerLivret) {
		this.imprimerLivret = imprimerLivret;
	}

	/**
	 * @param moniteur the moniteur to set
	 */
	public void setMoniteur(JMenu moniteur) {
		this.moniteur = moniteur;
	}
}