package vue;

import javax.swing.*;

import modele.DataMoniteur;

import java.awt.*;
import controleur.*;

public class FenetreMoniteur extends JFrame
{

	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	
	private static final long	serialVersionUID	= 1L;

	private DataMoniteur		dataMoniteur		= new DataMoniteur();

	private JTextField			rechercheM			= new JTextField("Rechercher...");
	private JButton				nouveauMoniteur		= new JButton("Nouveau");
	private JPanel				principal			= new JPanel();
	private JPanel				rechercheJlist		= new JPanel();
	private JPanel				regroupementBouton	= new JPanel();
	private JPanel				identiteMoniteur	= new JPanel(new BorderLayout());
	private JPanel				BoutonMoniteur		= new JPanel();
	private JButton				ajouter				= new JButton("Ajouter");
	private JButton				effacer				= new JButton("Supprimer");
	private JTextField			nom					= new JTextField();
	private JTextField			prenom				= new JTextField();
	private JLabel				texteMoniteur		= new JLabel("Moniteur");
	private JScrollPane			scrollListeM		= null;
	private JLabel				texteNom			= new JLabel("Nom :      ");
	private JLabel				textePrenom			= new JLabel("Prenom :");
	private JPanel				saisieNom			= new JPanel();
	private JPanel				saisiePrenom		= new JPanel();
	private JList				listeMoniteur		= new JList();
	private JLabel information 					= new JLabel();
	private JPanel informationM 				= new JPanel();


	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public FenetreMoniteur()
	{

		this.setTitle("Ajout / modification Moniteurs");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		creerPanelRechrcheJlist();
		creerPanelIdentiteMoniteur();
		creerPanelBouton();
		creerPanelInformation();
		creerPanelIdentiteBouton();
		CreationPanelPrincipal();

		AjoutEcouteur();

		this.setContentPane(principal);
		this.setVisible(true);
	}

	// ----------------------------------------- //
	// ---------------- METHODES --------------- //
	// ----------------------------------------- //
	
	private void creerPanelInformation() {
		informationM.setPreferredSize(new Dimension(380,60));
		//informationM.setBackground(Color.red);
		information.setPreferredSize(new Dimension(380,20));
		//information.setBackground(Color.yellow);
		JLabel warning = new JLabel("Information :");
		warning.setPreferredSize(new Dimension(380,20));
		information.setText("-Toute modification de moniteurs nécessitera le redémarrage de l'application.");
		informationM.add(warning);
		informationM.add(information);
		
	}
	
	private void creerPanelIdentiteBouton()
	{
		BoutonMoniteur.setPreferredSize(new Dimension(380, 400));
		// BoutonMoniteur.setBackground(Color.yellow);
		BoutonMoniteur.add(identiteMoniteur);
		BoutonMoniteur.add(regroupementBouton);
		BoutonMoniteur.add(informationM);

	}

	private void creerPanelRechrcheJlist()
	{

		texteMoniteur.setPreferredSize(new Dimension(80, 20));
		texteMoniteur.setFont(new Font(null, Font.BOLD, 13));
		rechercheM.setPreferredSize(new Dimension(196, 20));
		nouveauMoniteur.setPreferredSize(new Dimension(90, 20));

		scrollListeM = new JScrollPane(listeMoniteur);
		scrollListeM.setPreferredSize(new Dimension(196, 295));

		// creation du panel ListeEleve
		rechercheJlist.setPreferredSize(new Dimension(200, 400));
		// rechercheJlist.setBackground(Color.blue);
		rechercheJlist.add(texteMoniteur);
		rechercheJlist.add(nouveauMoniteur);
		rechercheJlist.add(rechercheM);
		rechercheJlist.add(scrollListeM);

	}

	private void CreationPanelPrincipal()
	{
		principal.add(rechercheJlist);
		principal.add(BoutonMoniteur);
	}

	private void creerPanelBouton()
	{
		effacer.setEnabled(false);
		regroupementBouton.setPreferredSize(new Dimension(300, 50));
		// regroupementBouton.setBackground(Color.black);
		regroupementBouton.add(ajouter);
		regroupementBouton.add(effacer);

	}

	private void creerPanelIdentiteMoniteur()
	{

		identiteMoniteur.setPreferredSize(new Dimension(300, 120));
		// identiteMoniteur.setBackground(Color.red);
		identiteMoniteur.setBorder(BorderFactory.createTitledBorder(""));

		nom.setPreferredSize(new Dimension(100, 20));
		prenom.setPreferredSize(new Dimension(100, 20));

		saisieNom.setPreferredSize(new Dimension(200, 50));
		// saisieNom.setBackground(Color.gray);
		saisieNom.add(texteNom);
		saisieNom.add(nom);

		saisiePrenom.setPreferredSize(new Dimension(200, 50));
		// saisiePrenom.setBackground(Color.green);
		saisiePrenom.add(textePrenom);
		saisiePrenom.add(prenom);

		identiteMoniteur.add(saisieNom, BorderLayout.NORTH);
		identiteMoniteur.add(saisiePrenom);

	}

	private void AjoutEcouteur()
	{
		EcouteurMoniteur ecouteur = new EcouteurMoniteur(this);

		prenom.addActionListener(ecouteur);
		nom.addActionListener(ecouteur);
		ajouter.addActionListener(ecouteur);
		effacer.addActionListener(ecouteur);
		nouveauMoniteur.addActionListener(ecouteur);

		listeMoniteur.addMouseListener(ecouteur);
		rechercheM.addMouseListener(ecouteur);
		rechercheM.addKeyListener(ecouteur);

		nom.addFocusListener(ecouteur);
		prenom.addFocusListener(ecouteur);

		this.addWindowListener(ecouteur);

	}

	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //
	
	/**
	 * @return the rechercheM
	 */
	public JTextField getRechercheM()
	{
		return rechercheM;
	}

	/**
	 * @param rechercheM
	 *            the rechercheM to set
	 */
	public void setRechercheM(JTextField rechercheM)
	{
		this.rechercheM = rechercheM;
	}

	/**
	 * @return the nouveauMoniteur
	 */
	public JButton getNouveauMoniteur()
	{
		return nouveauMoniteur;
	}

	/**
	 * @param nouveauMoniteur
	 *            the nouveauMoniteur to set
	 */
	public void setNouveauMoniteur(JButton nouveauMoniteur)
	{
		this.nouveauMoniteur = nouveauMoniteur;
	}

	/**
	 * @return the principal
	 */
	public JPanel getPrincipal()
	{
		return principal;
	}

	/**
	 * @param principal
	 *            the principal to set
	 */
	public void setPrincipal(JPanel principal)
	{
		this.principal = principal;
	}

	/**
	 * @return the rechercheJlist
	 */
	public JPanel getRechercheJlist()
	{
		return rechercheJlist;
	}

	/**
	 * @param rechercheJlist
	 *            the rechercheJlist to set
	 */
	public void setRechercheJlist(JPanel rechercheJlist)
	{
		this.rechercheJlist = rechercheJlist;
	}

	/**
	 * @return the regroupementBouton
	 */
	public JPanel getRegroupementBouton()
	{
		return regroupementBouton;
	}

	/**
	 * @param regroupementBouton
	 *            the regroupementBouton to set
	 */
	public void setRegroupementBouton(JPanel regroupementBouton)
	{
		this.regroupementBouton = regroupementBouton;
	}

	/**
	 * @return the identiteMoniteur
	 */
	public JPanel getIdentiteMoniteur()
	{
		return identiteMoniteur;
	}

	/**
	 * @param identiteMoniteur
	 *            the identiteMoniteur to set
	 */
	public void setIdentiteMoniteur(JPanel identiteMoniteur)
	{
		this.identiteMoniteur = identiteMoniteur;
	}

	/**
	 * @return the boutonMoniteur
	 */
	public JPanel getBoutonMoniteur()
	{
		return BoutonMoniteur;
	}

	/**
	 * @param boutonMoniteur
	 *            the boutonMoniteur to set
	 */
	public void setBoutonMoniteur(JPanel boutonMoniteur)
	{
		BoutonMoniteur = boutonMoniteur;
	}

	/**
	 * @return the ajouter
	 */
	public JButton getAjouter()
	{
		return ajouter;
	}

	/**
	 * @param ajouter
	 *            the ajouter to set
	 */
	public void setAjouter(JButton ajouter)
	{
		this.ajouter = ajouter;
	}

	/**
	 * @return the effacer
	 */
	public JButton getEffacer()
	{
		return effacer;
	}

	/**
	 * @param effacer
	 *            the effacer to set
	 */
	public void setEffacer(JButton effacer)
	{
		this.effacer = effacer;
	}

	/**
	 * @return the nom
	 */
	public JTextField getNom()
	{
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(JTextField nom)
	{
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public JTextField getPrenom()
	{
		return prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(JTextField prenom)
	{
		this.prenom = prenom;
	}

	/**
	 * @return the texteMoniteur
	 */
	public JLabel getTexteMoniteur()
	{
		return texteMoniteur;
	}

	/**
	 * @param texteMoniteur
	 *            the texteMoniteur to set
	 */
	public void setTexteMoniteur(JLabel texteMoniteur)
	{
		this.texteMoniteur = texteMoniteur;
	}

	/**
	 * @return the scrollListeM
	 */
	public JScrollPane getScrollListeM()
	{
		return scrollListeM;
	}

	/**
	 * @param scrollListeM
	 *            the scrollListeM to set
	 */
	public void setScrollListeM(JScrollPane scrollListeM)
	{
		this.scrollListeM = scrollListeM;
	}

	/**
	 * @return the texteNom
	 */
	public JLabel getTexteNom()
	{
		return texteNom;
	}

	/**
	 * @param texteNom
	 *            the texteNom to set
	 */
	public void setTexteNom(JLabel texteNom)
	{
		this.texteNom = texteNom;
	}

	/**
	 * @return the textePrenom
	 */
	public JLabel getTextePrenom()
	{
		return textePrenom;
	}

	/**
	 * @param textePrenom
	 *            the textePrenom to set
	 */
	public void setTextePrenom(JLabel textePrenom)
	{
		this.textePrenom = textePrenom;
	}

	/**
	 * @return the saisieNom
	 */
	public JPanel getSaisieNom()
	{
		return saisieNom;
	}

	/**
	 * @param saisieNom
	 *            the saisieNom to set
	 */
	public void setSaisieNom(JPanel saisieNom)
	{
		this.saisieNom = saisieNom;
	}

	/**
	 * @return the saisiePrenom
	 */
	public JPanel getSaisiePrenom()
	{
		return saisiePrenom;
	}

	/**
	 * @param saisiePrenom
	 *            the saisiePrenom to set
	 */
	public void setSaisiePrenom(JPanel saisiePrenom)
	{
		this.saisiePrenom = saisiePrenom;
	}

	/**
	 * @return the listeMoniteur
	 */
	public JList getListeMoniteur()
	{
		return listeMoniteur;
	}

	/**
	 * @param listeMoniteur
	 *            the listeMoniteur to set
	 */
	public void setListeMoniteur(JList listeMoniteur)
	{
		this.listeMoniteur = listeMoniteur;
	}

	/**
	 * @return the dataMoniteur
	 */
	public DataMoniteur getDataMoniteur()
	{
		return dataMoniteur;
	}

	/**
	 * @param dataMoniteur
	 *            the dataMoniteur to set
	 */
	public void setDataMoniteur(DataMoniteur dataMoniteur)
	{
		this.dataMoniteur = dataMoniteur;
	}

	/**
	 * @return the information
	 */
	public JLabel getInformation() {
		return information;
	}

	/**
	 * @param information the information to set
	 */
	public void setInformation(JLabel information) {
		this.information = information;
	}

	/**
	 * @return the informationM
	 */
	public JPanel getInformationM() {
		return informationM;
	}

	/**
	 * @param informationM the informationM to set
	 */
	public void setInformationM(JPanel informationM) {
		this.informationM = informationM;
	}

}
