package vue;


import controleur.*;
import javax.swing.*;
import modele.bdd;
import java.awt.*;

public class FenetrePrincipale extends JFrame {

	
	private static final long serialVersionUID = 1L;
	bdd bdd = new bdd();
	//Classes
	
	private FicheEleve ficheEleve = new FicheEleve();
	private Etape[] etape = new Etape[4];
	private InteroOrale intero = new InteroOrale();
	private ExamenBlanc examB = new ExamenBlanc();

	//Menu
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu eleve = new JMenu("Eleves");
	private JMenu moniteur = new JMenu("Moniteurs");
	private JMenuItem importerLivret = new JMenuItem("Importer un livret");
	private JMenuItem exporterLivret = new JMenuItem(
			"Exporter le livret actuel");
	private JMenuItem imprimerLivret = new JMenuItem(
			"Imprimer le livret actuel");
	private JMenuItem quitter = new JMenuItem("Quitter");
	private JMenuItem gestionM = new JMenuItem("Gestion");

	//Composant Panel droite.
	private JButton BoutonFicheE = new JButton("Fiche Eleve");
	private JButton BoutonEtape1 = new JButton("Etape 1");
	private JButton BoutonEtape2 = new JButton("Etape 2");
	private JButton BoutonEtape3 = new JButton("Etape 3");
	private JButton BoutonEtape4 = new JButton("Etape 4");
	private JButton BoutonIntero = new JButton("Interrogation orale");
	private JButton BoutonExamB = new JButton("Examen Blanc");
	private JButton BoutonValider = new JButton("Ajouter");
	private JButton BoutonSupprimer = new JButton("Supprimer");
	private JButton boutonAjouterEleve = new JButton("Nouveau");
	private JTextField rechercheE = new JTextField("Rechercher...");
	private String tabEleve[];
	private JList JlisteEleves;
	
	
	//VUE(modele)
	//controleur (modele,vue).
	////////////////////////////////////////////////////CONSTRUCTEURS

	public FenetrePrincipale() {

		this.setTitle("Auto-Ecole");
		this.setSize(1200, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creation des étapes.
		for (int i = 0; i < 4; i++) {
			etape[i] = new Etape(i);
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
	 * Permet d'écouter les objets à la classe EcouteurPrincipale 
	 */
	public void ajouterActionListener() {
		EcouteurPrincipale ecouteurPrincipale = new EcouteurPrincipale(this);
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
		fichier.add(imprimerLivret);
		fichier.add(quitter);
		moniteur.add(gestionM);
		menuBar.add(fichier);
		menuBar.add(eleve);
		menuBar.add(moniteur);
		setJMenuBar(menuBar);
		
	}

	/*
	 * création du panel comportant les différents panel (etape, fiche eleve, etc.)
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
	 *création du panel comportant les différents boutons (etape, fiche eleve, etc.)
	 */
	public JPanel CreationPanelGauche() {
		
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
		
		//Creation du scroll lié à la Jlist
		JScrollPane scrollListeE = new JScrollPane(JlisteEleves);
		scrollListeE.setPreferredSize(new Dimension(196, 295));
		
		//creation du panel ListeEleve
		JPanel containerListeEleves = new JPanel();
		containerListeEleves.setPreferredSize(new Dimension(200, 350));
		containerListeEleves.add(texteEleves);
		containerListeEleves.add(boutonAjouterEleve);
		containerListeEleves.add(rechercheE);
		containerListeEleves.add(scrollListeE);
		
		//creation du panel final contenant les 2 panels créés dessus
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 640));
		panel.add(containerListeEleves);
		panel.add(containerBoutons);
		
		return panel;
		
	}

	
	////////////////////////////////////////////////////////GETTERS/SETTERS
	
	public Etape[] getEtape(){
		return etape;
	}
	
	public Etape getEtape1() {
		return etape[0];
	}

	public Etape getEtape2() {
		return etape[1];
	}

	public Etape getEtape3() {
		return etape[2];
	}

	public Etape getEtape4() {
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

	public JButton getBoutonEtape1() {
		return BoutonEtape1;
	}

	public JButton getBoutonEtape2() {
		return BoutonEtape2;
	}

	public JButton getBoutonEtape3() {
		return BoutonEtape3;
	}

	public JButton getBoutonEtape4() {
		return BoutonEtape4;
	}

	public JButton getBoutonExamB() {
		return BoutonExamB;
	}

	public JButton getBoutonFicheE() {
		return BoutonFicheE;
	}

	public JButton getBoutonIntero() {
		return BoutonIntero;
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
}