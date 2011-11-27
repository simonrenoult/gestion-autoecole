
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.JOptionPane;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import modele.TableauLecon;
import KClass.KEleve;
import vue.FenetrePrincipale;

public class EcouteurBoutonValidationSuppression implements ActionListener, MouseListener
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private FenetrePrincipale	fenetre;
	@SuppressWarnings("unused")
	private Hashtable			correspondanceEleve	= new Hashtable();
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public EcouteurBoutonValidationSuppression(FenetrePrincipale f)
	{
		this.fenetre = f;
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	// --------FICHE_ELEVE-------- //
	
	/**
	 * Creation d'un Objet KEleve à partir de l'ensemble des champs graphique.
	 * @return
	 */
	private KEleve creationEleve()
	{
		KEleve eleve = new KEleve();
		
		eleve.setADRESSE_ELEVE(fenetre.getFicheEleve().getAdresse().getText());
		eleve.setCODE_POSTAL_ELEVE(fenetre.getFicheEleve().getCodePostal().getText());
		eleve.setPROFESSION_ELEVE(fenetre.getFicheEleve().getProfession().getText());
		eleve.setMAIL_ELEVE(fenetre.getFicheEleve().geteMail().getText());
		eleve.setPHOTO_ELEVE(fenetre.getFicheEleve().getChemin());
		eleve.setNOM_ELEVE(fenetre.getFicheEleve().getNom().getText());
		eleve.setPRENOM_ELEVE(fenetre.getFicheEleve().getPrenom().getText());
		eleve.setLIVRET_NEPH_ELEVE(fenetre.getFicheEleve().getNumLivret().getText());
		eleve.setCOMMUNE_ELEVE(fenetre.getFicheEleve().getCommuneEleve().getText());
		eleve.setVOLUME_HORAIRE_TH_ELEVE(fenetre.getFicheEleve().getFormaTheo().getSelectedIndex());
		eleve.setVOLUME_HORAIRE_PRATIQUE_ELEVE(fenetre.getFicheEleve().getFormaPra().getSelectedIndex());
		eleve.setRESULTAT_ELEVE_ORAL(fenetre.getFicheEleve().getResultatEva().getSelectedIndex());
		eleve.setOBSERVATION_VUE_ELEVE(fenetre.getFicheEleve().getAreaTestVue().getText());
		eleve.setTELEPHONE_ELEVE(fenetre.getFicheEleve().getTelephone().getText());
		
		eleve.setIdMONITEUR((Integer) fenetre.getEcouteurPrincipale().getCorrespondanceMoniteur().get(fenetre.getFicheEleve().getFormateur().getSelectedIndex()));
		eleve.setIdFORMATION(EcouteurPrincipal.Eleve.getIdFORMATION());
		
		eleve.setDATE_DE_NAISS_ELEVE((java.sql.Date) dateFormat(
				String.valueOf(fenetre.getFicheEleve().getDateNaissJ().getSelectedItem()) ,
				(String.valueOf(fenetre.getFicheEleve().getDateNaissM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateNaissA().getSelectedItem()))));
		
		eleve.setDATE_ENREGISTREMENT_ELEVE((java.sql.Date) dateFormat(
				(String.valueOf(fenetre.getFicheEleve().getDateEnregiJ().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEnregiM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEnregiA().getSelectedItem()))));
		
		eleve.setDATE_EVAL_ELEVE((java.sql.Date) dateFormat(
				(String.valueOf(fenetre.getFicheEleve().getDateEvaJ().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEvaM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEvaA().getSelectedItem()))));
		
		eleve.setDATE_INSCRIPTION_ELEVE((java.sql.Date) dateFormat(
				(String.valueOf(fenetre.getFicheEleve().getDateInscriJ().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateInscriM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateInscriA().getSelectedItem()))));
		
		if (fenetre.getFicheEleve().getTestVueO().isSelected())
			eleve.setTEST_VU_ELEVE(1);
		else
			eleve.setTEST_VU_ELEVE(0);
		
		return eleve;
	}
	
	/**
	 * On vérifie la cohérence des dates avec affichage d'une boite d'erreur si
	 * besoin. Fonction secondaire des opérations : AJOUT et MODIFCATIONS.
	 * @return
	 */
	private int verifierDate()
	{
		
		// IL faut récupérer les dates au format dates pour les comparer
		java.sql.Date dateNaissance = ((java.sql.Date) dateFormat(
				String.valueOf(fenetre.getFicheEleve().getDateNaissJ().getSelectedItem()) ,
				(String.valueOf(fenetre.getFicheEleve().getDateNaissM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateNaissA().getSelectedItem()))));
		java.sql.Date dateEnregistrement = ((java.sql.Date) dateFormat(
				(String.valueOf(fenetre.getFicheEleve().getDateEnregiJ().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEnregiM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEnregiA().getSelectedItem()))));
		
		java.sql.Date dateEvaluation = ((java.sql.Date) dateFormat(
				(String.valueOf(fenetre.getFicheEleve().getDateEvaJ().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEvaM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateEvaA().getSelectedItem()))));
		
		java.sql.Date dateInscription = ((java.sql.Date) dateFormat(
				(String.valueOf(fenetre.getFicheEleve().getDateInscriJ().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateInscriM().getSelectedItem())) ,
				(String.valueOf(fenetre.getFicheEleve().getDateInscriA().getSelectedItem()))));
		// on les compare et on récupère un numéro pour afficher un message
		// d'erreur
		int dateVerif = fenetre.getFicheEleve().getDataFiche1()
				.verifierCoherenceDate(dateNaissance , dateInscription , dateEnregistrement , dateEvaluation);
		
		if ((dateVerif != 1))
			JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(dateVerif) ,
					"Erreur" , JOptionPane.ERROR_MESSAGE);
		
		return dateVerif;
	}
	
	/**
	 * Affiche une boite deialogue pour confirmer l'insertion puis redirige vers
	 * le modele pour l'insertion. Fonction secondaire des opérations : AJOUT et
	 * MODIFCATIONS.
	 * @param eleveAinserer
	 * @return
	 */
	private boolean confirmationEtInsertAjout(KEleve eleveAinserer)
	{
		boolean confirmation = true;
		int option = JOptionPane.showConfirmDialog(null , "Ajouter l'élève " + eleveAinserer.getPRENOM_ELEVE() + " "
				+ eleveAinserer.getNOM_ELEVE() + " à la base de donnée ?" , "Demande de confirmation" ,
				JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
		
		if (option == JOptionPane.OK_OPTION)
		{
			confirmation = fenetre.getFicheEleve().getDataFiche1()
					.MajEtAjoutEleve(eleveAinserer , fenetre.getJlisteEleves().getModel().getSize() , 1);
			if (confirmation)
			{
				JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(2) ,
						"Opération d'ajout d'un élève" , JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(3) ,
						"Erreur" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return confirmation;
	}
	
	/**
	 * Affiche une boite dialogue pour confirmer la MAJ puis redirige vers le
	 * modele pour la MAJ Fonction secondaire des opérations : AJOUT et
	 * MODIFCATIONS.
	 * @param eleveAinserer
	 * @return
	 */
	private boolean confirmationEtInsertMaj(KEleve eleveAinserer)
	{
		boolean confirmation = true;
		int option = JOptionPane.showConfirmDialog(null , "Mettre à jour l'élève " + eleveAinserer.getPRENOM_ELEVE()
				+ " " + eleveAinserer.getNOM_ELEVE() + " dans la base de donnée ?" , "Demande de confirmation" ,
				JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
		
		if (option == JOptionPane.OK_OPTION)
		{
			confirmation = fenetre.getFicheEleve().getDataFiche1()
					.MajEtAjoutEleve(eleveAinserer , fenetre.getFicheEleve().getIdEleve() , 2);
			if (confirmation)
			{
				JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(4) ,
						"Opération de mise à jour d'un élève" , JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(5) ,
						"Erreur" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return confirmation;
	}
	
	/**
	 * Fonction appelée suite au clic "ajouter", fonction principale de
	 * l'opération : AJOUT.
	 * @return
	 */
	private boolean ajoutEleve()
	{
		if (verifierDate() == 1)
		{
			// on met à true les 2 dernieres vérif restantes.
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[9] = true;
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[10] = true;
			
			if (fenetre.getFicheEleve().getDataFiche1().tableauChampSaisieTrue())
			{
				// on finalise le path de la photo
				fenetre.getFicheEleve().setChemin(
						fenetre.getFicheEleve().getDataFiche1().addSlashes(fenetre.getFicheEleve().getChemin()));
				// on créé un nouvel Eleve.
				KEleve eleveAinserer = creationEleve();
				if (confirmationEtInsertAjout(eleveAinserer))
				{
					fenetre.getEcouteurPrincipale().chargerListEleve();
					fenetre.getFicheEleve().couleurDefaultChamps();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(-5) ,
						"Erreur" , JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return true;
	}
	
	/**
	 * Fonction appelée suite au clic "Mettre à Jour", fonction principale de
	 * l'opération : MAJ.
	 * @return
	 */
	private boolean majEleve()
	{
		if (verifierDate() == 1)
		{
			// on met à true les 2 dernieres vérif restantes.
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[9] = true;
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[10] = true;// A
			
			if (fenetre.getFicheEleve().getDataFiche1().tableauChampSaisieTrue())
			{
				// on finalise le path de la photo
				fenetre.getFicheEleve().setChemin(
						fenetre.getFicheEleve().getDataFiche1().addSlashes(fenetre.getFicheEleve().getChemin()));
				// on créé un nouvel Eleve.
				KEleve eleveAinserer = creationEleve();
				if (confirmationEtInsertMaj(eleveAinserer))
				{
					fenetre.getEcouteurPrincipale().chargerListEleve();
					fenetre.getFicheEleve().couleurDefaultChamps();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null , fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(-5) ,
						"Erreur" , JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[9] = false;
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[10] = false;
		}
		
		return true;
	}
	
	/**
	 * Methode de formatage de la date.
	 * 
	 * @param jour
	 * @param mois
	 * @param annee
	 * @return
	 */
	private Date dateFormat(String jour, String mois, String annee)
	{
		@SuppressWarnings("deprecation")
		java.sql.Date date = new java.sql.Date(Integer.parseInt(annee) - 1900 , Integer.parseInt(mois) - 1 ,
				Integer.parseInt(jour));
		
		return date;
	}
	
	// --------TABLEAU_LECON-------- //
	
	private void enregistrerRDV()
	{
		fenetre.getFicheEleve().getContainertableauLecon().getTableau().getSelectionModel().clearSelection();
		Object [][] tableauRDV = fenetre.getFicheEleve().getContainertableauLecon().getJModel().getData();
		TableauLecon enregistrementRDV = new TableauLecon(tableauRDV);
		enregistrementRDV.separerRDV();
		//enregistrementRDV.afficherListeRdvGraphique();
		//enregistrementRDV.afficherListeRdvAnt();
		//enregistrementRDV.afficherListeRdvPost();
		enregistrementRDV.separerRDVEditable(((Long)EcouteurPrincipal.Eleve.getId()).intValue());
		//enregistrementRDV.afficherListeRdvMaj();
		//enregistrementRDV.afficherListeRdvNew();
		//enregistrementRDV.afficherListeRdvsupp();
		int erreur;
		erreur = enregistrementRDV.verifierChampListeMaj();
		if(erreur != 0){
			fenetre.getFicheEleve().getContainertableauLecon().getTableau().getSelectionModel().addSelectionInterval(
					TableauLecon.numeroLigne, TableauLecon.numeroLigne);
			AfficherMessageErreurValidation(erreur,JOptionPane.ERROR_MESSAGE,"Erreur",enregistrementRDV);
		}
		else{
			erreur = enregistrementRDV.verifierChampListeNouveau();
			if(erreur != 0){
				fenetre.getFicheEleve().getContainertableauLecon().getTableau().getSelectionModel().addSelectionInterval(
						TableauLecon.numeroLigne, TableauLecon.numeroLigne);
				AfficherMessageErreurValidation(erreur,JOptionPane.ERROR_MESSAGE,"Erreur",enregistrementRDV);
			}
			else{
				enregistrementRDV.creerListeCrud();
				//enregistrementRDV.afficherListeCrud();
				erreur = enregistrementRDV.verifierContrainteIntegrite();
				if(erreur != 0){
					fenetre.getFicheEleve().getContainertableauLecon().getTableau().getSelectionModel().addSelectionInterval(
							TableauLecon.numeroLigne, TableauLecon.numeroLigne);
					AfficherMessageErreurValidation(erreur,JOptionPane.ERROR_MESSAGE,"Erreur",enregistrementRDV);
				}
				else{
					 if(AfficherConfirmation("Confirmer l'enregistrement des RDV ?", "Confirmation")){
						 if(enregistrementRDV.supprimerRDV(((Long)EcouteurPrincipal.Eleve.getId()).intValue())&&
									(enregistrementRDV.InsererRDV())){
							AfficherMessageErreurValidation(2,JOptionPane.INFORMATION_MESSAGE,"Succès",enregistrementRDV);
							fenetre.getEcouteurPrincipale().chargerDonneesTableauLecon();
						}
						else{
							AfficherMessageErreurValidation(3,JOptionPane.ERROR_MESSAGE,"Erreur",enregistrementRDV);
						}
					 }
					
					
				}
			}
		}
		
	}
	
	private void AfficherMessageErreurValidation(int error,int macroInformation,String msg,TableauLecon enregistrementRDV){
		System.out.println("avant affichege JoptionPane , error vaut : "+error);
		JOptionPane.showMessageDialog(null,enregistrementRDV.messageRenvoyeeUI(error)
				, msg, macroInformation);
	}
	
	@SuppressWarnings("static-access")
	private boolean AfficherConfirmation(String msg, String title){
		int option = new JOptionPane().showConfirmDialog(null, msg,
				title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	    
		if(option == JOptionPane.OK_OPTION)
		{
			return true;
		}
		return false;
	}
	
	// ----------------------------------------- //
	// ----------------LISTENERS---------------- //
	// ----------------------------------------- //
	
	// --------MOUSE-------- //
	
	@Override
	public void mouseClicked(MouseEvent arg0)
	{
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0)
	{		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0)
	{		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0)
	{
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
	}
	
	// --------ACTION-------- //
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == fenetre.getBoutonValider())
		{
			if (fenetre.getFicheEleve().isVisible())
			{
				if (fenetre.getFicheEleve().getOnglet().getSelectedIndex() == 0)
				{
					if (fenetre.getBoutonValider().getText() == "Ajouter")
					{
						ajoutEleve();
					}
					if (fenetre.getBoutonValider().getText() == "Mettre à jour")
					{
						majEleve();
					}
				}
				else if (fenetre.getFicheEleve().getOnglet().getSelectedIndex() == 1)
				{
					enregistrerRDV();
				}
			}
			if (fenetre.getEtape1().isVisible())
			{
				System.out.println("Enregistrement de l'étape 1");
			}
			if (fenetre.getEtape2().isVisible())
			{
				System.out.println("Enregistrement de l'étape 2");
			}
			if (fenetre.getEtape3().isVisible())
			{
				System.out.println("Enregistrement de l'étape 3");
			}
			if (fenetre.getEtape4().isVisible())
			{
				System.out.println("Enregistrement de l'étape 4");
			}
			if (fenetre.getIntero().isVisible())
			{
				fenetre.getIntero().recupererReponses();
			}
			if (fenetre.getExamB().isVisible())
			{	
				
			}
		}
	}
	
}
