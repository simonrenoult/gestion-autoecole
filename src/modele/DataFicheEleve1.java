package modele;


import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import KClass.*;

public class DataFicheEleve1 {

	private KDBMysql db = bdd.connexion();
	private Boolean [] TableauChampSaisieOk = null;
	
/////////////////////////////////////////////////////////////////////CONSTRUCTEURS
	public DataFicheEleve1() {
		
		// On définit un tableau de boolean où chaque case vérifie si le champ graphique contient une donnée correcte.
		TableauChampSaisieOk = new Boolean [11];
		for (int i = 0; i< TableauChampSaisieOk.length; i++){
			TableauChampSaisieOk[i] = false;
		}
		
	}
/////////////////////////////////////////////////////////////////////METHODES
	/*
	 * Recupere la liste brut d'eleve en BDD trié par oredre alphabétique
	 */
	public KListObject<KEleve> recupererListe() {
		KListObject<KEleve> Kliste = new KListObject<KEleve>(KEleve.class);
		Kliste.loadFromDb(bdd.db,"Select * from eleve order by nom_eleve asc");
		return Kliste;
	}
	
	/*
	 * Recupere une objet KEleve à partir de son id en BDD
	 */
	public KEleve recupererProfil(int id) {
	
		KEleve Eleve = new KEleve();
		Eleve.setId(id);
		try {
			Eleve.loadOne(db);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		//System.out.println(Eleve);
		return Eleve;
	}
	
	/*
	 * Fonction d'insertion et de MAJ en BDD.
	 */
	public boolean MajEtAjoutEleve (KEleve eleve, int identifiant, int operation){
		boolean crud ;
		eleve.setId(identifiant);
		eleve.setId(identifiant);
		System.out.println(eleve);
		if(operation == 1){
			System.out.println("-------------------------> Insertion en BDD");
			crud = eleve.add(db);
		}
		else{
			System.out.println("-------------------------> MAJ en BDD");
			crud = eleve.update(db);
		}
		
		//return crud;
		return true;
		
	}
	
	/*
	 * fonction de suppression d'un eleve en BDD
	 */
	public boolean SupprimerEleve (KEleve eleve){
		
		//supprimer
		return false;
		
	}
	
	/*
	 * fonction retournant tous les messages d'erreurs et de succès possible.
	 */
	public String messageRenvoyeeUI (int num){
		
		switch(num){
		case 5 : return "-Echec de l'opération : mise à jour d'un élève dans base de donnée\n";
			
		case 4 : return "-Succès de l'opération : mise à jour d'un élève dans la base de donnée\n";
		
		case 3 : return "-Echec de l'opération : ajout d'un élève en base de donnée\n";
		
		case 2 : return "-Succès de l'opération : ajout d'un élève en base de donnée\n";
		
		case 1 : return "";
		
		case -1 : return "-La date de naissance et d'inscription sont incohérente." +
					"L'élève doit avoir au minimum 16 ans, âge légal pour participer à cette formation\n";
		
		case -2 : return "-La date d'inscription de l'élève doit précédée la date de son enregistrement\n";
		
		case -3 : return "-La date d'enregistrement de l'élève doit précédée la date de son évaluation\n";
		
		case -4 : return "-Le volume de formation théorique ne peut pas être inférireur au volume de formation pratique\n";
		
		case -5 : return "-Tous les champs n'ont pas été rempli correctement. Tous les champs symbolisé par * doivent être rempli\n";
		}
		//ajouter message validation, etc....
		return "";
		
	}
	
	/*
	 * fonction de comparaison des 4 dates du formulaire : chaque a un ordre chronologique
	 */
	public int verifierCoherenceDate(java.sql.Date dateNaissance, java.sql.Date dateInscription, 
									java.sql.Date dateEnregistrement, java.sql.Date dateEvaluation ){
		
		if(dateNaissance.compareTo(dateInscription)<0)//16 ans de decalage.
		{
			if(dateInscription.compareTo(dateEnregistrement)<=0)
			{
				if(dateEnregistrement.compareTo(dateEvaluation)<=0)
				{
					
				}
				else
				{
					return -3;//"La date d'enregistrement de l'élève doit précédée la date de son évaluation";
				}
			}
			else
			{
				return -2; // "La date d'inscription de l'élève doit précédée la date de son enregistrement";
			}
		}
		else
		{
			return -1;//"La date de naissance et d'inscription sont incohérente." +"L'élève doit avoir au minimum 16 ans, âge légal pour participer à cette formation";
		}
		
		return 1;
	}
	
	/*
	 * permet de doubler les slashes pour une chaine.
	 */
	public String addSlashes(String chaine){
		chaine = chaine.replaceAll("\\\\", "\\\\\\\\");
		return chaine;
	}
	
	/*
	 * Retourne vrai si Tous les éléments sont cohérents avec les contraintes passées.
	 */
	public boolean tableauChampSaisieTrue(){
		boolean b= true;
		for(int i = 0; i<TableauChampSaisieOk.length;i++ ){
			if(!TableauChampSaisieOk[i]){
				b= false;
			}
		}
		
		return b;
	}
	
	//Regex, traitement + teste chaine vide (*).
	
	public boolean regexTraitementNom(String chaine){
		return Pattern.matches("^[A-Za-z'-|\\s]{0,19}+$",chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementPrenom(String chaine){
		return Pattern.matches("^[A-Za-z'-|\\s]{0,19}+$",chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementMail(String chaine){
		return Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$",chaine) || (chaine.isEmpty());
	}
	
	public boolean regexTraitementTelephone(String chaine){
		return Pattern.matches("^[0-9]{10,10}$",chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementAdresse(String chaine){
		return Pattern.matches("^[0-9A-Za-z'-|\\s]{0,19}+$",chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementCP(String chaine){
		return Pattern.matches("^[0-9]{5,5}$",chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementCommune(String chaine){
		return Pattern.matches("^[A-Za-z'-|\\s]{0,19}+$",chaine) && (!chaine.isEmpty());
	}
	
	public boolean regexTraitementProfession(String chaine){
		return Pattern.matches("^[A-Za-z'-|\\s]{0,19}+$",chaine) || (chaine.isEmpty());
	}
	
	public boolean regexTraitementNumLivret(String chaine) {
		return Pattern.matches("^[0-9]{0,10}$",chaine) && (!chaine.isEmpty());
	}
	
	
/////////////////////////////////////////////////////////////ACCESSEURS
	/**
	 * @return the tableauChampSaisieOk
	 */
	public Boolean[] getTableauChampSaisieOk() {
		return TableauChampSaisieOk;
	}
	/**
	 * @param tableauChampSaisieOk the tableauChampSaisieOk to set
	 */
	public void setTableauChampSaisieOk(Boolean[] tableauChampSaisieOk) {
		TableauChampSaisieOk = tableauChampSaisieOk;
	}
	
	
	
	

	
	
	
	
}
