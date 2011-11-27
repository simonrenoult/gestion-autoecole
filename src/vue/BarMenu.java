package vue;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controleur.EcouteurMenu;

public class BarMenu extends JMenuBar {
	
	// ----------------------------------------- //
	// ----------------ATRIBUTS----------------- //
	// ----------------------------------------- //
	private static final long serialVersionUID = 1L;
	private JMenu aide 					 = new JMenu("Aide");
	private JMenuItem manuel			 = new JMenuItem("Manuel d'aide");
	private JMenuItem aPropos			 = new JMenuItem("à propos");

	private JMenu moniteur 				 = new JMenu("Moniteurs");
	private JMenuItem gestionM 			 = new JMenuItem("Gestion");
	
	private JMenu fichier				 = new JMenu("PeriGest");
	private JMenuItem importerLivret	 = new JMenuItem("Importer un livret");
	private JMenuItem exporterLivret	 = new JMenuItem("Exporter le livret actuel");
	private JMenuItem exporterLivrets	 = new JMenuItem("Exporter les livrets de tous les élèves");
	private JMenuItem imprimerLivret 	 = new JMenuItem("Imprimer le livret actuel");
	private JMenuItem quitter 			 = new JMenuItem("Quitter");
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	@SuppressWarnings("unused")
	public BarMenu(){
		init();
		EcouteurMenu e = new EcouteurMenu(this);
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	private void init() {
		fichier.add(importerLivret);
		fichier.add(exporterLivret);
		fichier.add(exporterLivrets);
		fichier.add(imprimerLivret);
		fichier.add(quitter);
		
		moniteur.add(gestionM);
		
		aide.add(manuel);
		aide.add(aPropos);
		
		this.add(fichier);
		this.add(moniteur);
		this.add(aide);
		
		
	}

	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	/**
	 * @return the aide
	 */
	public JMenu getAide() {
		return aide;
	}

	/**
	 * @param aide the aide to set
	 */
	public void setAide(JMenu aide) {
		this.aide = aide;
	}

	/**
	 * @return the manuel
	 */
	public JMenuItem getManuel() {
		return manuel;
	}

	/**
	 * @param manuel the manuel to set
	 */
	public void setManuel(JMenuItem manuel) {
		this.manuel = manuel;
	}

	/**
	 * @return the aPropos
	 */
	public JMenuItem getaPropos() {
		return aPropos;
	}

	/**
	 * @param aPropos the aPropos to set
	 */
	public void setaPropos(JMenuItem aPropos) {
		this.aPropos = aPropos;
	}

	/**
	 * @return the moniteur
	 */
	public JMenu getMoniteur() {
		return moniteur;
	}

	/**
	 * @param moniteur the moniteur to set
	 */
	public void setMoniteur(JMenu moniteur) {
		this.moniteur = moniteur;
	}

	/**
	 * @return the gestionM
	 */
	public JMenuItem getGestionM() {
		return gestionM;
	}

	/**
	 * @param gestionM the gestionM to set
	 */
	public void setGestionM(JMenuItem gestionM) {
		this.gestionM = gestionM;
	}

	/**
	 * @return the fichier
	 */
	public JMenu getFichier() {
		return fichier;
	}

	/**
	 * @param fichier the fichier to set
	 */
	public void setFichier(JMenu fichier) {
		this.fichier = fichier;
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
	 * @return the quitter
	 */
	public JMenuItem getQuitter() {
		return quitter;
	}

	/**
	 * @param quitter the quitter to set
	 */
	public void setQuitter(JMenuItem quitter) {
		this.quitter = quitter;
	}

	
}
