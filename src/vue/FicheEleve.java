package vue;

import javax.swing.*;
import javax.swing.event.ChangeListener;

import vue.JTableAssurerLecon.JTableAssurerLecon;



import controleur.EcouteurFicheEleve;
import modele.DataFicheEleve1;
import modele.TableauLecon;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class FicheEleve extends JPanel {

	private DataFicheEleve1 dataFiche1 = new DataFicheEleve1();
	private TableauLecon tableauLecon = new TableauLecon();
	private int idEleve=0;
	private int idMoniteur=0;

	//declaration Jpanel.
	private JTabbedPane onglet ;
	private JPanel containerSous1 = new JPanel();
	private JPanel containerSous2 = new JPanel();
	private JPanel containerEvaluaD = new JPanel();
	private JPanel containerIdEleve = new JPanel();
	private JPanel containerIdEleveS1 = new JPanel();
	private JPanel containerIdEleveS2 = new JPanel();
	private JPanel containerDate = new JPanel();
	private JPanel containerTestVue = new JPanel();
	private JPanel containerNumLivret = new JPanel();
	private JPanel containerResponsable = new JPanel();
	private JPanel containerTabForma = new JPanel();
	private JTableAssurerLecon containertableauLecon = new JTableAssurerLecon(tableauLecon);
	
	//onglet fiche eleve
	private JLabel texteTitre = new JLabel();
	private JTextField nom = new JTextField();
	private JTextField prenom = new JTextField();
	private JTextField adresse = new JTextField();
	private JTextField codePostal = new JTextField();
	private JTextField communeEleve = new JTextField();
	private JTextField telephone = new JTextField();
	private JTextField eMail = new JTextField();
	private JTextField profession = new JTextField();
	private JTextField numLivret = new JTextField();
	private JComboBox resultatEva = new JComboBox();
	private JComboBox formaTheo = new JComboBox();
	private JComboBox formaPra = new JComboBox();
	//combobox remplies avec la date du jour.
	private JComboBox dateNaissJ= new JComboBox();
	private JComboBox dateNaissM= new JComboBox();
	private JComboBox dateNaissA= new JComboBox();
	private JComboBox dateEvaJ= new JComboBox();
	private JComboBox dateEvaM= new JComboBox();
	private JComboBox dateEvaA= new JComboBox();
	private JComboBox dateInscriJ= new JComboBox();
	private JComboBox dateInscriM= new JComboBox();
	private JComboBox dateInscriA= new JComboBox();
	private JComboBox dateEnregiJ= new JComboBox();
	private JComboBox dateEnregiM= new JComboBox();
	private JComboBox dateEnregiA= new JComboBox();
	private JComboBox responsableForma = new JComboBox();
	private JComboBox formateur= new JComboBox();
	private JButton parcourir = new JButton("Parcourir");
	private JLabel labelPhoto =new JLabel();
	private String chemin = new String();
	private JRadioButton testVueO = new JRadioButton("Oui");
	private JRadioButton testVueN = new JRadioButton("Non");
	private ButtonGroup groupTestVue = new ButtonGroup();
	private JTextArea areaTestVue = new JTextArea();
	private JScrollPane scrollTestVue = new JScrollPane(areaTestVue);
	private ImageIcon imageEleve =  null;
	
	//onglet tableau des leçons
	//private JButton boutonAjouter = new JButton("Ajouter");
	
	
	
	////////////////////////////////////////////////////CONSTRUCTEURS
	
	public FicheEleve() {
		
		initElementGraphique();
		this.setVisible(true);
		this.repaint();
	}

	/////////////////////////////////////////////////////METHODES
	
	

	/*
	 * Rend tous les champs graphiques de type combobox blancs.
	 * et les autres types ???
	 */
	public void couleurDefaultChamps() {
		this.getNom().setBackground(Color.white);
		this.getPrenom().setBackground(Color.white);
		this.getCommuneEleve().setBackground(Color.white);
		this.getCodePostal().setBackground(Color.white);
		this.geteMail().setBackground(Color.white);
		this.getTelephone().setBackground(Color.white);
		this.getAdresse().setBackground(Color.white);
		this.getProfession().setBackground(Color.white);
		this.getNumLivret().setBackground(Color.white);
		
		this.getDateNaissJ().setBackground(Color.white);
		this.getDateNaissM().setBackground(Color.white);
		this.getDateNaissA().setBackground(Color.white);
		this.getDateEvaJ().setBackground(Color.white);
		this.getDateEvaM().setBackground(Color.white);
		this.getDateEvaA().setBackground(Color.white);
		this.getDateInscriJ().setBackground(Color.white);
		this.getDateInscriM().setBackground(Color.white);
		this.getDateInscriA().setBackground(Color.white);
		this.getDateEnregiJ().setBackground(Color.white);
		this.getDateEnregiM().setBackground(Color.white);
		this.getDateEnregiA().setBackground(Color.white);
		this.getResponsableForma().setBackground(Color.white);
		this.getFormateur().setBackground(Color.white);
		this.getResultatEva().setBackground(Color.white);
		this.getFormaTheo().setBackground(Color.white);
		this.getFormaPra().setBackground(Color.white);
	}
	
	/*
	 * Fonction principale pour générer le graphique this.
	 */
	public void initElementGraphique() {
		this.setPreferredSize(new Dimension(900, 605));
		
		creerPanelEleve();
		
		creerBordurePanelGaphique();
		parametreGraphiquePanel();
		creerSystemeOngletGraphique();
		
		StructurationPanel();
		creerPanelDate();
		creerPanelTestVue();
		creerPanelFormateurNeph();
		AjoutGraphiqueListener();
		

		
	}

	/*
	 * Permet de faire écouter les élements gra	phiques
	 */
	private void AjoutGraphiqueListener() {
		EcouteurFicheEleve ecouteur = new EcouteurFicheEleve(this);
		nom.addFocusListener(ecouteur);
		prenom.addFocusListener(ecouteur);
		adresse.addFocusListener(ecouteur);
		codePostal.addFocusListener(ecouteur);
		communeEleve.addFocusListener(ecouteur);
		telephone.addFocusListener(ecouteur);
		eMail.addFocusListener(ecouteur);
		profession.addFocusListener(ecouteur);
		numLivret.addFocusListener(ecouteur);
		
		
		parcourir.addActionListener(ecouteur);
		formateur.addActionListener(ecouteur);
		//onglet.addChangeListener(ecouteur);
		
	}

	/*
	 * Permet de constiutuer le panel containerIdEleve, panel principal
	 */
	private void StructurationPanel() {
		containerSous1.add(containerEvaluaD);
		containerSous1.add(containerDate);
		containerSous2.add(containerNumLivret);
		containerSous2.add(containerResponsable);
		containerIdEleve.add(containerIdEleveS1);
		containerIdEleve.add(containerIdEleveS2);
		
	}

	/*On créé les les paneaux "Evaluation de départ" et "Inscription"
	 * 
	 */
	private void creerPanelDate() {
		Font f = new Font(null, Font.BOLD, 13);
		JLabel labelTitreEvalua = new JLabel("Evaluation de depart");
		labelTitreEvalua.setFont(f);
		labelTitreEvalua.setPreferredSize(new Dimension(210, 40));

		new Date();
		new SimpleDateFormat("dd/MM/yyyy");

		JLabel labelDate = new JLabel("Date* :");
		//parametrerJComboBoxDate(dateEvaJ, dateEvaM, dateEvaA);

		JLabel labelResultat = new JLabel("Resultat* :");
		labelResultat.setFont(f);
		resultatEva = new JComboBox();
		for (int i = 0; i <= 30; i++) {
			resultatEva.addItem(i);
		}

		JLabel labelVoluF = new JLabel("Volume de formation prevu :");
		labelVoluF.setFont(f);
		labelVoluF.setPreferredSize(new Dimension(210, 20));

		JLabel labelFormaTheo = new JLabel("Theorique* :");
		labelFormaTheo.setFont(f);
		formaTheo = new JComboBox();
		for (int i = 0; i <= 50; i++) {
			formaTheo.addItem(i);
		}
		labelFormaTheo.setPreferredSize(new Dimension(100, 20));

		JLabel labelFormaPra = new JLabel("Pratique* :");
		labelFormaPra.setFont(f);
		formaPra = new JComboBox();
		for (int i = 0; i <= 50; i++) {
			formaPra.addItem(i);
		}
		labelFormaPra.setPreferredSize(new Dimension(100, 20));

		containerEvaluaD.add(labelTitreEvalua);
		containerEvaluaD.add(labelDate);
		containerEvaluaD.add(dateEvaJ);
		containerEvaluaD.add(dateEvaM);
		containerEvaluaD.add(dateEvaA);
		containerEvaluaD.add(labelResultat);
		containerEvaluaD.add(resultatEva);
		containerEvaluaD.add(labelVoluF);
		containerEvaluaD.add(labelFormaTheo);
		containerEvaluaD.add(formaTheo);
		containerEvaluaD.add(labelFormaPra);
		containerEvaluaD.add(formaPra);

		JLabel labelInscription = new JLabel("Inscription*");
		labelInscription.setFont(f);
		labelInscription.setPreferredSize(new Dimension(200, 20));
		
		//parametrerJComboBoxDate(dateInscriJ, dateInscriM, dateInscriA);

		JLabel labelEnregistrement = new JLabel("Enregistrement*");
		labelEnregistrement.setFont(f);
		labelEnregistrement.setPreferredSize(new Dimension(200, 20));
		
		//parametrerJComboBoxDate(dateEnregiJ, dateEnregiM, dateEnregiA);

		containerDate.add(labelInscription);
		containerDate.add(dateInscriJ);
		containerDate.add(dateInscriM);
		containerDate.add(dateInscriA);
		containerDate.add(labelEnregistrement);
		containerDate.add(dateEnregiJ);
		containerDate.add(dateEnregiM);
		containerDate.add(dateEnregiA);
		
	}
	
	/*
	 * On créé le panneau du test de la vue
	 */
	private void creerPanelTestVue() {
		Font f = new Font(null, Font.BOLD, 13);
		JLabel labelTestVue = new JLabel("Test de la vue* :");
		labelTestVue.setFont(f);

		scrollTestVue.setPreferredSize(new Dimension(380, 90));

		
		testVueN.setSelected(true);
		testVueO.setBackground(Color.white);
		testVueN.setBackground(Color.white);

		groupTestVue.add(testVueO);
		groupTestVue.add(testVueN);

		containerTestVue.add(labelTestVue);
		containerTestVue.add(testVueO);
		containerTestVue.add(testVueN);
		containerTestVue.add(scrollTestVue);
		
	}

	/*
	 * On créé le panneau de renseignement tel que le num NEPH, liste moniteur, etc..
	 */
	private void creerPanelFormateurNeph() {
		Font f = new Font(null, Font.BOLD, 13);
		JLabel labelNumLivret = new JLabel("Livret d'apprentissage ne N.E.P.H*");
		this.parametrerJLabelEtJTextField(labelNumLivret, f, numLivret, 250, 20);

		containerNumLivret.add(labelNumLivret);
		containerNumLivret.add(numLivret);

		JLabel labelResponsableForma = new JLabel(
				"Responsable de la formation* :");
		labelResponsableForma.setFont(f);
		labelResponsableForma.setPreferredSize(new Dimension(200, 20));
		responsableForma.addItem("JOLLY Didier");
		responsableForma.setPreferredSize(new Dimension(200, 20));

		JLabel labelFormateur = new JLabel("Formateur* :");
		labelFormateur.setFont(f);
		labelFormateur.setPreferredSize(new Dimension(200, 20));
		formateur.setPreferredSize(new Dimension(200, 20));
		
		
		containerResponsable.add(labelResponsableForma);
		containerResponsable.add(responsableForma);
		containerResponsable.add(labelFormateur);
		containerResponsable.add(formateur);

		JLabel labelFormaPratique = new JLabel("Formation Pratique");
		labelFormaPratique.setFont(new Font(null, Font.BOLD, 20));
		labelFormaPratique.setPreferredSize(new Dimension(780, 40));

		containerTabForma.add(labelFormaPratique);
		containerTabForma.add(containertableauLecon);
	}

	/*
	 * Fixe les tailles des panneaux avec leur couleur de fonds
	 */
	private void parametreGraphiquePanel() {
		this.parametrerJPanel(containerEvaluaD, 250, 200, Color.white);
		this.parametrerJPanel(containerIdEleve, 600, 320, Color.white);
		this.parametrerJPanel(containerDate, 250, 115, Color.white);
		this.parametrerJPanel(containerTestVue, 400, 130, Color.white);
		this.parametrerJPanel(containerNumLivret, 460, 60, Color.white);
		this.parametrerJPanel(containerResponsable, 460, 80, Color.white);
		this.parametrerJPanel(containerTabForma, 900, 700, Color.white);
		this.parametrerJPanel(containertableauLecon, 900,500 , Color.white);
		this.parametrerJPanel(containerSous1, 250, 330, Color.white);
		this.parametrerJPanel(containerSous2, 450, 140, Color.white);
		this.parametrerJPanel(containerIdEleveS1, 380, 289, Color.white);
		this.parametrerJPanel(containerIdEleveS2, 200, 289, Color.white);

	}

	/*
	 * On créé les bordures sans titre (== titre de la balise fieldset en HTML)
	 */
	private void creerBordurePanelGaphique() {
		containerEvaluaD.setBorder(BorderFactory.createTitledBorder(""));
		containerIdEleve.setBorder(BorderFactory.createTitledBorder(""));
		containerDate.setBorder(BorderFactory.createTitledBorder(""));
		containerTestVue.setBorder(BorderFactory.createTitledBorder(""));
		
	}

	/*
	 * On créé l'objet graphique gérant les onglets
	 */
	private void creerSystemeOngletGraphique() {
		
		JPanel containerAjouter = new JPanel();
		containerAjouter.setPreferredSize(new Dimension(400, 40));
		containerAjouter.setBackground(Color.white);
		//boutonAjouter.setPreferredSize(new Dimension(80, 20));
		
		
		onglet = new JTabbedPane();
		JPanel partie1 = new JPanel();
		JPanel partie2 = new JPanel();
		this.parametrerJPanel(partie1, 880, 565, Color.white);
		this.parametrerJPanel(partie2, 880, 565, Color.white);
		partie1.add(texteTitre);
		partie1.add(containerSous1);
		partie1.add(containerIdEleve);
		partie1.add(containerTestVue);
		partie1.add(containerSous2);
		partie2.add(containerTabForma);
		partie2.add(containerAjouter);
		
		
		onglet.add(partie1, "Donnees Personnelles");
		onglet.add(partie2, "Tableau des lecons");
		this.add(onglet);
		
	}

	/*
	 * On créé le panel eleve comporatnt les informations relatives à l'élèves, photo.
	 */
	private void creerPanelEleve() {
		texteTitre = new JLabel("Fiche de suivi PERMIS B");
		texteTitre.setPreferredSize(new Dimension(800, 50));
		texteTitre.setFont(new Font(null, Font.BOLD, 20));
		

		Font f = new Font(null, Font.BOLD, 13);
		JLabel labelNom = new JLabel("Nom* : ");
		this.parametrerJLabelEtJTextField(labelNom, f, nom, 300, 20);
		JLabel labelPrenom = new JLabel("Prenom* :");
		this.parametrerJLabelEtJTextField(labelPrenom, f, prenom, 280, 20);
		JLabel labelDateNaiss = new JLabel("Date de naissance* :");
		labelDateNaiss.setFont(f);
		labelDateNaiss.setPreferredSize(new Dimension(150, 20));
		//parametrerJComboBoxDate(dateNaissJ, dateNaissM, dateNaissA);
		
		JLabel labelEMail = new JLabel("E-Mail :");
		this.parametrerJLabelEtJTextField(labelEMail, f, eMail, 290, 20);

		JLabel labelTelephoneM = new JLabel("Numero de Telephone* :");
		this.parametrerJLabelEtJTextField(labelTelephoneM, f, telephone, 180,
				20);

		JLabel labelAdresse = new JLabel("Adresse* :");
		this.parametrerJLabelEtJTextField(labelAdresse, f, adresse, 283, 20);

		JLabel labelCodePostal = new JLabel("Code Postal* :");
		this.parametrerJLabelEtJTextField(labelCodePostal, f, codePostal, 40,
				20);

		JLabel labelCommune = new JLabel("Commune* :");
		this.parametrerJLabelEtJTextField(labelCommune, f, communeEleve, 135,
				20);

		JLabel labelProfession = new JLabel("Profession :");
		this.parametrerJLabelEtJTextField(labelProfession, f, profession, 268,
				20);
		
		containerIdEleveS1.setLayout(new FlowLayout(1, 5, 15));
		containerIdEleveS1.add(labelNom);
		containerIdEleveS1.add(nom);
		containerIdEleveS1.add(labelPrenom);
		containerIdEleveS1.add(prenom);
		containerIdEleveS1.add(labelDateNaiss);
		containerIdEleveS1.add(dateNaissJ);
		containerIdEleveS1.add(dateNaissM);
		containerIdEleveS1.add(dateNaissA);
		containerIdEleveS1.add(labelEMail);
		containerIdEleveS1.add(eMail);
		containerIdEleveS1.add(labelTelephoneM);
		containerIdEleveS1.add(telephone);
		containerIdEleveS1.add(labelAdresse);
		containerIdEleveS1.add(adresse);
		containerIdEleveS1.add(labelCodePostal);
		containerIdEleveS1.add(codePostal);
		containerIdEleveS1.add(labelCommune);
		containerIdEleveS1.add(communeEleve);
		containerIdEleveS1.add(labelProfession);
		containerIdEleveS1.add(profession);
		labelPhoto = new JLabel("");
		labelPhoto.setBorder(BorderFactory.createTitledBorder(""));
		labelPhoto.setPreferredSize(new Dimension(140, 180));

		
		containerIdEleveS2.add(labelPhoto);
		containerIdEleveS2.add(parcourir);
		
	}

	/*
	 * fonction permettant de parametrer les panels
	 */
	private void parametrerJPanel(JPanel p, int l, int h, Color c) {
		p.setBackground(c);
		p.setPreferredSize(new Dimension(l, h));
	}

	/*
	 * Fonction permettant de parametrer les objets Jlabel et Jtextfield
	 */
	private void parametrerJLabelEtJTextField(JLabel la, Font f, JTextField tf,
			int h, int l) {
		la.setFont(f);
		tf.setPreferredSize(new Dimension(h, l));
	}

	/*
	 *Permet de selectionner la date actuelle pour les couple de combobox (DD/MM/YYYY).
	 * Cette méthode est commune aux 2 contrôleurs (EcouteurFicheEleve et EcouteurPrincipale) afin d'éviter
	 * la redondance de code (de cette fonction) dans ces derniers.
	 */
	public void parametrerJComboBoxDate(JComboBox j, JComboBox m, JComboBox a) {
		Date date = new Date();
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 1; i < 32; i++) {
			j.addItem(i);
			
			if (dateStandard.format(date).substring(0, 2).equals("0" + i + ""))
				j.setSelectedIndex(i - 1);
		}
		j.setPreferredSize(new Dimension(50, 20));
		for (int i = 1; i < 13; i++) {
			m.addItem(i);
			if ((dateStandard.format(date).substring(3, 5).equals("0" + i + ""))
					|| (dateStandard.format(date).substring(3, 5).equals("" + i
							+ "")))
				m.setSelectedIndex(i - 1);
		}
		m.setPreferredSize(new Dimension(50, 20));
		for (int i = 1900; i <= 2011; i++) {
			a.addItem(i);
			if (dateStandard.format(date).substring(6, 10).equals("" + i + ""))
				a.setSelectedIndex(i - 1900);

		}
		a.setPreferredSize(new Dimension(60, 20));

	}
	
	////////////////////////////////////////////////////////GETTERS/SETTERS	

	/**
	 * @return the dataFiche1
	 */
	public DataFicheEleve1 getDataFiche1() {
		return dataFiche1;
	}

	/**
	 * @param dataFiche1 the dataFiche1 to set
	 */
	public void setDataFiche1(DataFicheEleve1 dataFiche1) {
		this.dataFiche1 = dataFiche1;
	}

	/**
	 * @return the idEleve
	 */
	public int getIdEleve() {
		return idEleve;
	}

	/**
	 * @param idEleve the idEleve to set
	 */
	public void setIdEleve(int idEleve) {
		this.idEleve = idEleve;
	}

	/**
	 * @return the containerSous1
	 */
	public JPanel getContainerSous1() {
		return containerSous1;
	}

	/**
	 * @param containerSous1 the containerSous1 to set
	 */
	public void setContainerSous1(JPanel containerSous1) {
		this.containerSous1 = containerSous1;
	}

	/**
	 * @return the containerSous2
	 */
	public JPanel getContainerSous2() {
		return containerSous2;
	}

	/**
	 * @param containerSous2 the containerSous2 to set
	 */
	public void setContainerSous2(JPanel containerSous2) {
		this.containerSous2 = containerSous2;
	}

	/**
	 * @return the containerEvaluaD
	 */
	public JPanel getContainerEvaluaD() {
		return containerEvaluaD;
	}

	/**
	 * @param containerEvaluaD the containerEvaluaD to set
	 */
	public void setContainerEvaluaD(JPanel containerEvaluaD) {
		this.containerEvaluaD = containerEvaluaD;
	}

	/**
	 * @return the containerIdEleve
	 */
	public JPanel getContainerIdEleve() {
		return containerIdEleve;
	}

	/**
	 * @param containerIdEleve the containerIdEleve to set
	 */
	public void setContainerIdEleve(JPanel containerIdEleve) {
		this.containerIdEleve = containerIdEleve;
	}

	/**
	 * @return the containerIdEleveS1
	 */
	public JPanel getContainerIdEleveS1() {
		return containerIdEleveS1;
	}

	/**
	 * @param containerIdEleveS1 the containerIdEleveS1 to set
	 */
	public void setContainerIdEleveS1(JPanel containerIdEleveS1) {
		this.containerIdEleveS1 = containerIdEleveS1;
	}

	/**
	 * @return the containerIdEleveS2
	 */
	public JPanel getContainerIdEleveS2() {
		return containerIdEleveS2;
	}

	/**
	 * @param containerIdEleveS2 the containerIdEleveS2 to set
	 */
	public void setContainerIdEleveS2(JPanel containerIdEleveS2) {
		this.containerIdEleveS2 = containerIdEleveS2;
	}

	/**
	 * @return the containerDate
	 */
	public JPanel getContainerDate() {
		return containerDate;
	}

	/**
	 * @param containerDate the containerDate to set
	 */
	public void setContainerDate(JPanel containerDate) {
		this.containerDate = containerDate;
	}

	/**
	 * @return the containerTestVue
	 */
	public JPanel getContainerTestVue() {
		return containerTestVue;
	}

	/**
	 * @param containerTestVue the containerTestVue to set
	 */
	public void setContainerTestVue(JPanel containerTestVue) {
		this.containerTestVue = containerTestVue;
	}

	/**
	 * @return the containerNumLivret
	 */
	public JPanel getContainerNumLivret() {
		return containerNumLivret;
	}

	/**
	 * @param containerNumLivret the containerNumLivret to set
	 */
	public void setContainerNumLivret(JPanel containerNumLivret) {
		this.containerNumLivret = containerNumLivret;
	}

	/**
	 * @return the containerResponsable
	 */
	public JPanel getContainerResponsable() {
		return containerResponsable;
	}

	/**
	 * @param containerResponsable the containerResponsable to set
	 */
	public void setContainerResponsable(JPanel containerResponsable) {
		this.containerResponsable = containerResponsable;
	}

	/**
	 * @return the containerTabForma
	 */
	/*public JPanel getContainerTabForma() {
		return containerTabForma;
	}

	/**
	 * @param containerTabForma the containerTabForma to set
	 */
	/*public void setContainerTabForma(JPanel containerTabForma) {
		this.containerTabForma = containerTabForma;
	}

	/**
	 * @return the texteTitre
	 */
	public JLabel getTexteTitre() {
		return texteTitre;
	}

	/**
	 * @param texteTitre the texteTitre to set
	 */
	public void setTexteTitre(JLabel texteTitre) {
		this.texteTitre = texteTitre;
	}

	/**
	 * @return the nom
	 */
	public JTextField getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(JTextField nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public JTextField getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the adresse
	 */
	public JTextField getAdresse() {
		return adresse;
	}

	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(JTextField adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the codePostal
	 */
	public JTextField getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(JTextField codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the communeEleve
	 */
	public JTextField getCommuneEleve() {
		return communeEleve;
	}

	/**
	 * @param communeEleve the communeEleve to set
	 */
	public void setCommuneEleve(JTextField communeEleve) {
		this.communeEleve = communeEleve;
	}

	/**
	 * @return the telephone
	 */
	public JTextField getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(JTextField telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the eMail
	 */
	public JTextField geteMail() {
		return eMail;
	}

	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(JTextField eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the profession
	 */
	public JTextField getProfession() {
		return profession;
	}

	/**
	 * @param profession the profession to set
	 */
	public void setProfession(JTextField profession) {
		this.profession = profession;
	}

	/**
	 * @return the numLivret
	 */
	public JTextField getNumLivret() {
		return numLivret;
	}

	/**
	 * @param numLivret the numLivret to set
	 */
	public void setNumLivret(JTextField numLivret) {
		this.numLivret = numLivret;
	}

	/**
	 * @return the resultatEva
	 */
	public JComboBox getResultatEva() {
		return resultatEva;
	}

	/**
	 * @param resultatEva the resultatEva to set
	 */
	public void setResultatEva(JComboBox resultatEva) {
		this.resultatEva = resultatEva;
	}

	/**
	 * @return the formaTheo
	 */
	public JComboBox getFormaTheo() {
		return formaTheo;
	}

	/**
	 * @param formaTheo the formaTheo to set
	 */
	public void setFormaTheo(JComboBox formaTheo) {
		this.formaTheo = formaTheo;
	}

	/**
	 * @return the formaPra
	 */
	public JComboBox getFormaPra() {
		return formaPra;
	}

	/**
	 * @param formaPra the formaPra to set
	 */
	public void setFormaPra(JComboBox formaPra) {
		this.formaPra = formaPra;
	}

	/**
	 * @return the dateNaissJ
	 */
	public JComboBox getDateNaissJ() {
		return dateNaissJ;
	}

	/**
	 * @param dateNaissJ the dateNaissJ to set
	 */
	public void setDateNaissJ(JComboBox dateNaissJ) {
		this.dateNaissJ = dateNaissJ;
	}

	/**
	 * @return the dateNaissM
	 */
	public JComboBox getDateNaissM() {
		return dateNaissM;
	}

	/**
	 * @param dateNaissM the dateNaissM to set
	 */
	public void setDateNaissM(JComboBox dateNaissM) {
		this.dateNaissM = dateNaissM;
	}

	/**
	 * @return the dateNaissA
	 */
	public JComboBox getDateNaissA() {
		return dateNaissA;
	}

	/**
	 * @param dateNaissA the dateNaissA to set
	 */
	public void setDateNaissA(JComboBox dateNaissA) {
		this.dateNaissA = dateNaissA;
	}

	/**
	 * @return the dateEvaJ
	 */
	public JComboBox getDateEvaJ() {
		return dateEvaJ;
	}

	/**
	 * @param dateEvaJ the dateEvaJ to set
	 */
	public void setDateEvaJ(JComboBox dateEvaJ) {
		this.dateEvaJ = dateEvaJ;
	}

	/**
	 * @return the dateEvaM
	 */
	public JComboBox getDateEvaM() {
		return dateEvaM;
	}

	/**
	 * @param dateEvaM the dateEvaM to set
	 */
	public void setDateEvaM(JComboBox dateEvaM) {
		this.dateEvaM = dateEvaM;
	}

	/**
	 * @return the dateEvaA
	 */
	public JComboBox getDateEvaA() {
		return dateEvaA;
	}

	/**
	 * @param dateEvaA the dateEvaA to set
	 */
	public void setDateEvaA(JComboBox dateEvaA) {
		this.dateEvaA = dateEvaA;
	}

	/**
	 * @return the dateInscriJ
	 */
	public JComboBox getDateInscriJ() {
		return dateInscriJ;
	}

	/**
	 * @param dateInscriJ the dateInscriJ to set
	 */
	public void setDateInscriJ(JComboBox dateInscriJ) {
		this.dateInscriJ = dateInscriJ;
	}

	/**
	 * @return the dateInscriM
	 */
	public JComboBox getDateInscriM() {
		return dateInscriM;
	}

	/**
	 * @param dateInscriM the dateInscriM to set
	 */
	public void setDateInscriM(JComboBox dateInscriM) {
		this.dateInscriM = dateInscriM;
	}

	/**
	 * @return the dateInscriA
	 */
	public JComboBox getDateInscriA() {
		return dateInscriA;
	}

	/**
	 * @param dateInscriA the dateInscriA to set
	 */
	public void setDateInscriA(JComboBox dateInscriA) {
		this.dateInscriA = dateInscriA;
	}

	/**
	 * @return the dateEnregiJ
	 */
	public JComboBox getDateEnregiJ() {
		return dateEnregiJ;
	}

	/**
	 * @param dateEnregiJ the dateEnregiJ to set
	 */
	public void setDateEnregiJ(JComboBox dateEnregiJ) {
		this.dateEnregiJ = dateEnregiJ;
	}

	/**
	 * @return the dateEnregiM
	 */
	public JComboBox getDateEnregiM() {
		return dateEnregiM;
	}

	/**
	 * @param dateEnregiM the dateEnregiM to set
	 */
	public void setDateEnregiM(JComboBox dateEnregiM) {
		this.dateEnregiM = dateEnregiM;
	}

	/**
	 * @return the dateEnregiA
	 */
	public JComboBox getDateEnregiA() {
		return dateEnregiA;
	}

	/**
	 * @param dateEnregiA the dateEnregiA to set
	 */
	public void setDateEnregiA(JComboBox dateEnregiA) {
		this.dateEnregiA = dateEnregiA;
	}

	/**
	 * @return the responsableForma
	 */
	public JComboBox getResponsableForma() {
		return responsableForma;
	}

	/**
	 * @param responsableForma the responsableForma to set
	 */
	public void setResponsableForma(JComboBox responsableForma) {
		this.responsableForma = responsableForma;
	}

	/**
	 * @return the formateur
	 */
	public JComboBox getFormateur() {
		return formateur;
	}

	/**
	 * @param formateur the formateur to set
	 */
	public void setFormateur(JComboBox formateur) {
		this.formateur = formateur;
	}

	/**
	 * @return the parcourir
	 */
	public JButton getParcourir() {
		return parcourir;
	}

	/**
	 * @param parcourir the parcourir to set
	 */
	public void setParcourir(JButton parcourir) {
		this.parcourir = parcourir;
	}

	/**
	 * @return the labelPhoto
	 */
	public JLabel getLabelPhoto() {
		return labelPhoto;
	}

	/**
	 * @param labelPhoto the labelPhoto to set
	 */
	public void setLabelPhoto(JLabel labelPhoto) {
		this.labelPhoto = labelPhoto;
	}

	/**
	 * @return the chemin
	 */
	public String getChemin() {
		return chemin;
	}

	/**
	 * @param chemin the chemin to set
	 */
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	/**
	 * @return the testVueO
	 */
	public JRadioButton getTestVueO() {
		return testVueO;
	}

	/**
	 * @param testVueO the testVueO to set
	 */
	public void setTestVueO(JRadioButton testVueO) {
		this.testVueO = testVueO;
	}

	/**
	 * @return the testVueN
	 */
	public JRadioButton getTestVueN() {
		return testVueN;
	}

	/**
	 * @param testVueN the testVueN to set
	 */
	public void setTestVueN(JRadioButton testVueN) {
		this.testVueN = testVueN;
	}

	/**
	 * @return the groupTestVue
	 */
	public ButtonGroup getGroupTestVue() {
		return groupTestVue;
	}

	/**
	 * @param groupTestVue the groupTestVue to set
	 */
	public void setGroupTestVue(ButtonGroup groupTestVue) {
		this.groupTestVue = groupTestVue;
	}

	/**
	 * @return the areaTestVue
	 */
	public JTextArea getAreaTestVue() {
		return areaTestVue;
	}

	/**
	 * @param areaTestVue the areaTestVue to set
	 */
	public void setAreaTestVue(JTextArea areaTestVue) {
		this.areaTestVue = areaTestVue;
	}

	/**
	 * @return the scrollTestVue
	 */
	public JScrollPane getScrollTestVue() {
		return scrollTestVue;
	}

	/**
	 * @param scrollTestVue the scrollTestVue to set
	 */
	public void setScrollTestVue(JScrollPane scrollTestVue) {
		this.scrollTestVue = scrollTestVue;
	}

	/**
	 * @return the boutonAjouter
	 */
	/*public JButton getBoutonAjouter() {
		return boutonAjouter;
	}

	/**
	 * @param boutonAjouter the boutonAjouter to set
	 */
	/*public void setBoutonAjouter(JButton boutonAjouter) {
		this.boutonAjouter = boutonAjouter;
	}

	/**
	 * @return the imageEleve
	 */
	public ImageIcon getImageEleve() {
		return imageEleve;
	}

	/**
	 * @param imageEleve the imageEleve to set
	 */
	public void setImageEleve(ImageIcon imageEleve) {
		this.imageEleve = imageEleve;
	}

	/**
	 * @return the idMoniteur
	 */
	public int getIdMoniteur() {
		return idMoniteur;
	}

	/**
	 * @param idMoniteur the idMoniteur to set
	 */
	public void setIdMoniteur(int idMoniteur) {
		this.idMoniteur = idMoniteur;
	}

	/**
	 * @return the tableauLecon
	 */
	public TableauLecon getTableauLecon() {
		return tableauLecon;
	}

	/**
	 * @param tableauLecon the tableauLecon to set
	 */
	public void setTableauLecon(TableauLecon tableauLecon) {
		this.tableauLecon = tableauLecon;
	}

	/**
	 * @return the onglet
	 */
	public JTabbedPane getOnglet() {
		return onglet;
	}

	/**
	 * @param onglet the onglet to set
	 */
	public void setOnglet(JTabbedPane onglet) {
		this.onglet = onglet;
	}

	/**
	 * @return the containertableauLecon
	 */
	public JTableAssurerLecon getContainertableauLecon() {
		return containertableauLecon;
	}

	/**
	 * @param containertableauLecon the containertableauLecon to set
	 */
	public void setContainertableauLecon(JTableAssurerLecon containertableauLecon) {
		this.containertableauLecon = containertableauLecon;
	}

	/**
	 * @return the originalbackgrdTextfield
	 */

}	