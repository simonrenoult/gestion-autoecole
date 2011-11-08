package controleur;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import modele.DataMoniteur;
import net.ko.kobject.KListObject;
import KClass.KAssurer_lecon;
import KClass.KEleve;
import KClass.KMoniteur;

import vue.FenetrePrincipale;

public class EcouteurBoutonValidationSuppression implements ActionListener, MouseListener,KeyListener{

	
	private FenetrePrincipale fenetre;
	private Hashtable correspondanceEleve = new Hashtable();
	
	public EcouteurBoutonValidationSuppression (FenetrePrincipale f){
		this.fenetre = f;
	}
///////////////////////////////////////////////////////////METHODES
///////////CONCERNANT FICHE ELEVE
	//Onglet Fiche Eleve
	/*
	 *Rempli l'objet Jliste de la liste des élèves contenu en base de donnée à partir de la liste chargée.
	 * 
	 */
	private void chargerListEleve(){
		
		fenetre.getJlisteEleves().setListData( recupererListeEleve().toArray());
	}
	
	/*
	 * Création d'une liste contenant nom et prénom  des élèves.
	 */
	private ArrayList<String> recupererListeEleve(){
		ArrayList<String> listeEleves = new ArrayList<String>();
		KListObject<KEleve> KListe = new KListObject<KEleve>(KEleve.class);
		
		KListe = fenetre.getFicheEleve().getDataFiche1().recupererListe();
		//System.out.println(KListe);
		for (int i = 0; i < KListe.count(); i++)
		{
			correspondanceEleve.put(i,KListe.get(i).getId());
			listeEleves.add(KListe.get(i).getNOM_ELEVE().toUpperCase()+ 
						" "+KListe.get(i).getPRENOM_ELEVE().toLowerCase()
					);
			//System.out.print("Clé de la liste : "+i+", ");
			//System.out.println("valeur : "+KListe.get(i).getID_ELEVE()+"("+KListe.get(i).getPRENOM_ELEVE().toLowerCase()+")");
			
		}
		
		
		return listeEleves;
	}

	/*
	 * 
	 * Creation d'un Objet KEleve à partir de l'ensemble des champs graphique.
	 */
	private KEleve creationEleve(){
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
		
		
		
		eleve.setIdMONITEUR(fenetre.getFicheEleve().getIdMoniteur());
		eleve.setIdFORMATION(0);
		
		eleve.setDATE_DE_NAISS_ELEVE((java.sql.Date)dateFormat(String.valueOf(fenetre.getFicheEleve().getDateNaissJ().getSelectedItem()), 
												(String.valueOf(fenetre.getFicheEleve().getDateNaissM().getSelectedItem())),
												(String.valueOf(fenetre.getFicheEleve().getDateNaissA().getSelectedItem()))
												)
									);
		
		eleve.setDATE_ENREGISTREMENT_ELEVE((java.sql.Date)dateFormat((String.valueOf(fenetre.getFicheEleve().getDateEnregiJ().getSelectedItem())), 
												(String.valueOf(fenetre.getFicheEleve().getDateEnregiM().getSelectedItem())),
												(String.valueOf(fenetre.getFicheEleve().getDateEnregiA().getSelectedItem()))
												)
									);
		
		eleve.setDATE_EVAL_ELEVE((java.sql.Date)dateFormat((String.valueOf(fenetre.getFicheEleve().getDateEvaJ().getSelectedItem())), 
												(String.valueOf(fenetre.getFicheEleve().getDateEvaM().getSelectedItem())),
												(String.valueOf(fenetre.getFicheEleve().getDateEvaA().getSelectedItem()))
												)
									);
		
		eleve.setDATE_INSCRIPTION_ELEVE((java.sql.Date)dateFormat((String.valueOf(fenetre.getFicheEleve().getDateInscriJ().getSelectedItem())), 
												(String.valueOf(fenetre.getFicheEleve().getDateInscriM().getSelectedItem())),
												(String.valueOf(fenetre.getFicheEleve().getDateInscriA().getSelectedItem()))
												)
									);
		
		if (fenetre.getFicheEleve().getTestVueO().isSelected()){
			eleve.setTEST_VU_ELEVE(1);
		}
		else{
			eleve.setTEST_VU_ELEVE(0);
		}
		
		//System.out.println(eleve);
		
		return eleve;
	}
	
	/*
	 * On vérifie la cohérence des dates avec affichage d'une boite d'erreur si besoin.
	 * Fonction secondaire des opérations : AJOUT et MODIFCATIONS.
	 */
	private int verifierDate(){
		
		//IL faut récupérer les dates au format dates pour les comparer
		java.sql.Date dateNaissance  = ((java.sql.Date)dateFormat(String.valueOf(fenetre.getFicheEleve().getDateNaissJ().getSelectedItem()), 
				(String.valueOf(fenetre.getFicheEleve().getDateNaissM().getSelectedItem())),
				(String.valueOf(fenetre.getFicheEleve().getDateNaissA().getSelectedItem()))
				)
			);
		java.sql.Date dateEnregistrement = ((java.sql.Date)dateFormat((String.valueOf(fenetre.getFicheEleve().getDateEnregiJ().getSelectedItem())), 
						(String.valueOf(fenetre.getFicheEleve().getDateEnregiM().getSelectedItem())),
						(String.valueOf(fenetre.getFicheEleve().getDateEnregiA().getSelectedItem()))
						)
			);

		java.sql.Date dateEvaluation = ((java.sql.Date)dateFormat((String.valueOf(fenetre.getFicheEleve().getDateEvaJ().getSelectedItem())), 
				(String.valueOf(fenetre.getFicheEleve().getDateEvaM().getSelectedItem())),
				(String.valueOf(fenetre.getFicheEleve().getDateEvaA().getSelectedItem()))
				)
			);

		java.sql.Date dateInscription = ((java.sql.Date)dateFormat((String.valueOf(fenetre.getFicheEleve().getDateInscriJ().getSelectedItem())), 
				(String.valueOf(fenetre.getFicheEleve().getDateInscriM().getSelectedItem())),
				(String.valueOf(fenetre.getFicheEleve().getDateInscriA().getSelectedItem()))
				)
			);
		//on les compare et on récupère un numéro pour afficher un message d'erreur
		int dateVerif = fenetre.getFicheEleve().getDataFiche1().verifierCoherenceDate(
							dateNaissance, dateInscription, dateEnregistrement, dateEvaluation);
		
		
		if ((dateVerif != 1)){
			
			JOptionPane.showMessageDialog(null,
							fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(dateVerif)
							, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
		return dateVerif;
	}

	/*
	 * Affiche une boite deialogue pour confirmer l'insertion puis redirige vers le modele pour l'insertion.
	 * Fonction secondaire des opérations : AJOUT et MODIFCATIONS.
	 */
	private boolean confirmationEtInsertAjout(KEleve eleveAinserer) {
		boolean confirmation = true;
		int option = JOptionPane.showConfirmDialog(null, "Ajouter l'élève "+eleveAinserer.getPRENOM_ELEVE()+ " "+eleveAinserer.getNOM_ELEVE()+" à la base de donnée ?" ,
				"Demande de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION){
			confirmation = fenetre.getFicheEleve().getDataFiche1().MajEtAjoutEleve(eleveAinserer,fenetre.getJlisteEleves().getModel().getSize(),1);
			if(confirmation){
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(2)
						, "Opération d'ajout d'un élève", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(3)
						, "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		return confirmation;
		
		
	}
	
	/*
	 * Affiche une boite dialogue pour confirmer la MAJ puis redirige vers le modele pour la MAJ
	 * Fonction secondaire des opérations : AJOUT et MODIFCATIONS.
	 */
	private boolean confirmationEtInsertMaj(KEleve eleveAinserer) {
		boolean confirmation = true;
		int option = JOptionPane.showConfirmDialog(null, "Mettre à jour l'élève "+eleveAinserer.getPRENOM_ELEVE()+ " "+eleveAinserer.getNOM_ELEVE()+" dans la base de donnée ?" ,
				"Demande de confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.OK_OPTION){
			confirmation = fenetre.getFicheEleve().getDataFiche1().MajEtAjoutEleve(eleveAinserer,fenetre.getFicheEleve().getIdEleve(),2);
			if(confirmation){
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(4)
						, "Opération de mise à jour d'un élève", JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(5)
						, "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		return confirmation;
		
		
	}
	
	/*
	 * Fonction appelée suite au clic "ajouter", fonction principale de l'opération : AJOUT.
	 */
	private boolean ajoutEleve(){

		if(verifierDate() == 1 ){
			//on met à true les 2 dernieres vérif restantes.
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[9] = true;
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[10] = true;

			if(fenetre.getFicheEleve().getDataFiche1().tableauChampSaisieTrue()){
				//on finalise le path de la photo
				fenetre.getFicheEleve().setChemin(fenetre.getFicheEleve().getDataFiche1().addSlashes(fenetre.getFicheEleve().getChemin()));
				//on créé un nouvel Eleve.
				KEleve eleveAinserer = creationEleve();
				if(confirmationEtInsertAjout(eleveAinserer)){
					chargerListEleve();
				}
			}
			else{
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(-5)
						, "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		return true;
	}
	
	/*
	 * Fonction appelée suite au clic "Mettre à Jour", fonction principale de l'opération : MAJ.
	 */
	private boolean majEleve(){
		if(verifierDate() == 1 ){
			//on met à true les 2 dernieres vérif restantes.
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[9] = true;
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[10] = true;// A supprimer car volume horaire mais pus besoin (redimensionner Tab)!

			if(fenetre.getFicheEleve().getDataFiche1().tableauChampSaisieTrue()){
				//on finalise le path de la photo
				fenetre.getFicheEleve().setChemin(fenetre.getFicheEleve().getDataFiche1().addSlashes(fenetre.getFicheEleve().getChemin()));
				//on créé un nouvel Eleve.
				KEleve eleveAinserer = creationEleve();
				if(confirmationEtInsertMaj(eleveAinserer)){
					chargerListEleve();
				}
			}
			else{
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getDataFiche1().messageRenvoyeeUI(-5)
						, "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[9] = false;
			fenetre.getFicheEleve().getDataFiche1().getTableauChampSaisieOk()[10] = false;
		}
		return true;
		
	}
	
	private Date dateFormat(String jour, String mois, String annee) {
		
		@SuppressWarnings("deprecation")
		java.sql.Date date = new java.sql.Date(Integer.parseInt(annee)-1900, Integer.parseInt(mois)-1,Integer.parseInt(jour));
		System.out.println(date);
		return date;
	}

//////////CONCERNANT TABLEAU DES LECONS
	
	//Onglet tableau des lecons
	private void enregistrerRDV() {
		KListObject<KAssurer_lecon> Kliste = null;
		
		Object [][] tableauRDV = fenetre.getFicheEleve().getContainertableauLecon().getJModel().getData();
		/*for (int i = 0; i< tableauRDV.length; i++){
			for (int j = 0; j< tableauRDV[i].length; j++){
				System.out.println(tableauRDV[i][j]);
			}
		}*/
		
		//1)
		Kliste = fenetre.getFicheEleve().getTableauLecon().convertirTableau(tableauRDV, fenetre.getFicheEleve().getIdEleve());
		
		System.out.println(Kliste);
		System.out.println("---------------------------------------------------------------------------------------------");
		System.out.println(fenetre.getFicheEleve().getTableauLecon().chargerListeRDVEleve(fenetre.getFicheEleve().getIdEleve()));
		System.out.println("1 terminé");
		//2)
		fenetre.getFicheEleve().getTableauLecon().effacerRDVListe(Kliste);
		fenetre.getFicheEleve().getTableauLecon().chargerListeRDVEleve(fenetre.getFicheEleve().getIdEleve());// MAJ de la iste de reference.
		System.out.println("2 terminé");
		int erreur = fenetre.getFicheEleve().getTableauLecon().effacerRDVvide(Kliste);
		if (erreur != 1){
			JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getTableauLecon().messageRenvoyeeUI(erreur)
					, "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		else{
			//3)
			erreur = fenetre.getFicheEleve().getTableauLecon().verifierChronologieDate(Kliste);
			System.out.println("3 terminé");
			if (erreur != 1){
				JOptionPane.showMessageDialog(null,fenetre.getFicheEleve().getTableauLecon().messageRenvoyeeUI(erreur)
						, "Erreur", JOptionPane.ERROR_MESSAGE);
			
			}
			else{
				
				//4)
				//contrainte d'intégrité sur l'eleve
				//contraint d'integrité sur le moniteur
				System.out.println("4 terminé");
				//5 
				KListObject<KAssurer_lecon> KlisteNouveauRDV = fenetre.getFicheEleve().getTableauLecon().recupererNouvelleLecon(Kliste);
				KListObject<KAssurer_lecon> KlisteRDVexistant = fenetre.getFicheEleve().getTableauLecon().recupererLeconExistante(Kliste);
				System.out.println("5 terminé");
				//6
				fenetre.getFicheEleve().getTableauLecon().creerClePrimaireTableAgenda(KlisteNouveauRDV);
				System.out.println("6 terminé");
				//7)
				fenetre.getFicheEleve().getTableauLecon().MAJRDV(KlisteRDVexistant);
				System.out.println("7 terminé");
				//8)
				fenetre.getFicheEleve().getTableauLecon().AjoutRDV(KlisteNouveauRDV);
				System.out.println("8 terminé");
				//SUCCES, on recharge la liste de reference.
				fenetre.getFicheEleve().getTableauLecon().chargerListeRDVEleve(fenetre.getFicheEleve().getIdEleve());// MAJ de la iste de reference.
				System.out.println("terminé");
			}
		}
		
		
		
		/*
		 *enlever le numero de lecon et ajouter un Jlabel recapitulant le nombre dheure compté. et le nombre de leçon : OK
		 * 
		 * 
		 * 1) Transformer en liste KassurerLecon (qui s'occupe des types) :OK
		 * 2) Purger la liste sur les RDV a effacer en BDD (ceux qui existet mais n'étant pas dans la liste) et les RDV vides.
		 * -> on a donc une liste pleine de RDV exixtant et nouveaux.
		 * 2) Verification Regex.
		 * 2) Verification de la chronologie des RDV.
		 * 3) Verification de la cohérence des horaires.
		 * 4) Verification de la contrainte d'intégrité sur le moniteur et de l'eleve sur un RDV
		 * 5) Deduire la nouvelleliste de RDV (en separant ancien et nouveau RDV) sur la MAJ de la listeRef.
		 * 6) Creer cle primaire de chaque nouveau RDV dans AGENDA.
		 * 7) update des anciens RDV
		 * 8) insert des nouveaux RDV
		 * 
		 */
		
		
	}
	
	
	
	///////////////////////////////////////////////////////////KEYLISTENER
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

////////////////////////////////////////////////////////////MOUSELISTENER
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
////////////////////////////////////////////////////////////ACTIONLISTENER
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fenetre.getBoutonSupprimer()) {
			System.out.println("Suppression");
			// supprimer l'utilsateur ou supprimer sa fiche élève ?
			// Est il utile ici ? Sinon faire un menu dédié à la suppression des élèves.
			// Boite de dialogue de confirmation
		}
		else if(e.getSource() == fenetre.getBoutonValider()) {
			if(fenetre.getFicheEleve().isVisible()){
				if(fenetre.getFicheEleve().getOnglet().getSelectedIndex()==0){
					if(fenetre.getBoutonValider().getText()=="Ajouter"){ajoutEleve();}
					if(fenetre.getBoutonValider().getText()=="Mettre à jour"){majEleve();}
				}
				else if (fenetre.getFicheEleve().getOnglet().getSelectedIndex()==1){
					//fenetre.getBoutonValider().getText()=="Enregistrer"
					System.out.println("Enregistrer");
					//Traitement (enlever chaine vide, reformatter les chaine, ajouter).
					//on ajoute seulement les numéro qui sont pas déjà présent dans la BDD.
					//MAJ dans la bdd de la liste.
					enregistrerRDV();
				}
				
			}
			
			
	}
		
	}

	
	
	
}
