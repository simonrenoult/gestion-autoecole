package modele;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import net.ko.kobject.KListObject;
import KClass.KAgenda;
import KClass.KAssurer_lecon;
import KClass.KMoniteur;

public class TableauLecon {

	private Boolean [] TableauChampSaisieOk = null;
	private KListObject<KAssurer_lecon> KlisteRef = null ;
	
	public  TableauLecon (){
	// On définit un tableau de boolean où chaque case vérifie si le champ graphique contient une donnée correcte.
			TableauChampSaisieOk = new Boolean [5];
			for (int i = 0; i< TableauChampSaisieOk.length; i++){
				TableauChampSaisieOk[i] = false;
			}
			
			KlisteRef =new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
	}
	
	/*
	 * Recuperer la liste des lecons d'un eleve.
	 */
	public KListObject<KAssurer_lecon>  chargerListeRDVEleve(int id){
		KListObject<KAssurer_lecon> Kliste =new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		Kliste.loadFromDb(bdd.db,"select * from assurer_lecon where idELEVE = "+id+" order by idAgenda asc");
		
		KlisteRef = Kliste;
		return Kliste;
	}

	/*
	 * Supprimer un RDV d'un eleve
	 */
	public boolean supprimerRDV (KAssurer_lecon RDV){
		System.out.println("delete : "+RDV);
		//RDV.delete(bdd.db);
		//KlisteRef =  chargerListeRDVEleve(id);
		//REMETRE A JOUR LA LISTE DE REFERENCE POUR LELEVE !!!!!
		return true;
	}
	
	public boolean MAJRDV (KListObject<KAssurer_lecon> RDV){
		for(int i = 0; i<RDV.count(); i++){
			RDV.get(i).update(bdd.db);
		}
		
		return true;
	}
	
	public boolean AjoutRDV (KListObject<KAssurer_lecon> RDV){
		for(int i = 0; i<RDV.count(); i++){
			RDV.get(i).add(bdd.db);
		}
		
		return true;
	}	
	
	/*
	 * Recupere une liste de lecon nouvelle d'un eleve en comparant avec celle existant dans
	 * la BDD pour ce meme eleve
	 */
	public KListObject<KAssurer_lecon> recupererNouvelleLecon(KListObject<KAssurer_lecon> KlisteEntiere){
		int nombreRDVexistant = KlisteRef.count();
		for(int i = 0; i< nombreRDVexistant; i++ ){
			KlisteEntiere.get(i).toDelete();// OU KlisteEntiere.get(0).toDelete() pendant i fois.
			
		}
		return KlisteEntiere;
	}
	
	public KListObject<KAssurer_lecon> recupererLeconExistante(KListObject<KAssurer_lecon> KlisteEntiere){
		int nombreRDVexistant = KlisteRef.count();
		KListObject<KAssurer_lecon> KlisteExistant = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		for(int i = 0; i< nombreRDVexistant; i++ ){
			KlisteExistant.add(KlisteEntiere.get(i));
			
		}
		return KlisteExistant;
	}
	
	/*
	 * Operation d'insertion de la liste de cle primaire de la table AGENDA avant de pouvoir inserer 
	 * la liste des RDV.
	 */
	public boolean insererListeAgenda(KListObject<KAgenda> Kliste){
		for(int i = 0; i<Kliste.count(); i++){
			Kliste.get(i).add(bdd.db);
		}
		return true;
	}
	
	/*
	 * Creer la liste contenant toutes les cles primaires de Agenda non présente dans la BDD
	 */
	public KListObject<KAgenda> creerClePrimaireTableAgenda(KListObject<KAssurer_lecon> KlisteRDV){
		
		KListObject<KAgenda> KlisteAgenda =new KListObject<KAgenda>(KAgenda.class);
		KAgenda agenda = new KAgenda();
		for (KAssurer_lecon RDV : KlisteRDV){
			agenda.setId(RDV.getIdAGENDA());
			KlisteAgenda.add(agenda);
		}
		
		return KlisteAgenda;
	}
	
	/*
	 * Conversion du tableau d'objets de la Jtable en Liste KAssurer_Lecon
	 */
	public KListObject<KAssurer_lecon> convertirTableau (Object[][] Tab,int idEleve){
		KListObject<KAssurer_lecon> Kliste =new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		KAssurer_lecon Lecon ;
		
		
		
		for (int  i = 0; i<Tab.length; i++){
			Lecon = new KAssurer_lecon();
			Lecon.setIdELEVE(idEleve);
			Lecon.setIdAGENDA(creerDateTime(Tab[i][0],Tab[i][1]));
			Lecon.setIdMONITEUR(recupererIdMoniteur(String.valueOf(Tab[i][3])));
			Lecon.setOBSERVATION_LECON(String.valueOf(Tab[i][4]));
			Lecon.setDUREE_LECON(verifierDuree(String.valueOf(Tab[i][2])));
			Kliste.add(Lecon);
		}
		
		return Kliste;
	}
	
	@SuppressWarnings("deprecation")
	public Timestamp creerDateTime(Object date, Object heure) {
		
		java.util.Date date1 = creerDate(String.valueOf(date));
		java.sql.Time heure1 = creerHoraire(String.valueOf(heure)); 
		
		Timestamp dateFinal = new Timestamp(date1.getYear(), date1.getMonth(), date1.getDate(), heure1.getHours(), heure1.getMinutes(), 0, 0);
		
		
		return dateFinal;
	}

	/*
	 * retourne le message d'erreur ou de validation
	 */
	public String messageRenvoyeeUI (int num){
		
		switch(num){
		case 5 : return "-\n";
			
		case 4 : return "-\n";
		
		case 3 : return "-\n";
		
		case 2 : return "-\n";
		
		case 1 : return "";
		
		case -1 : return "-La date de RDV fixée ne peut pas être antérieur à la date actuelle\n";
		
		case -2 : return "-L'horaire saisie est invalide : les heures sont compris entre 1 et 24\n";
		
		case -3 : return "-L'horaire saisie est invalide : les minutes sont compris entre 0 et 59.\n";
		
		case -4 : return "-Les dates de RDV ne correpondent pas à un ordre chronologique.\n";
		
		case -5 : return "-Une lecon ne peut pas avoir une durée de 0h00 Veuillez changer cette valeur.\n";
		
		case -6 : return "-Un moniteur doit être selectionné pour chaque RDV.\n";
		
		case -7 : return "-Une date de RDV a été modifié de façon invalide.\n";
		
		case -8 : return "-Une lecon ne peut pas avoir une durée de 0h00 Veuillez changer cette valeur..\n";
		
		}
		
		return "";
		
	}
	
	/*
	 * Verifie les bornes des heures,minutes
	 */
	public int verifierHoraire(String chaine){
		String tabHoraire[] = chaine.split(":");
		
		if((Integer.parseInt(tabHoraire[0]) <= 0 ) && (Integer.parseInt(tabHoraire[0])> 24)){
			return -2;
		}
		if((Integer.parseInt(tabHoraire[1]) < 0 ) && (Integer.parseInt(tabHoraire[1]) >= 60 )){
			return -3;
		}
		
		 return 1;
	}
	
	/*
	 * verifie la chronologie des dates de RDV
	 */
	public int verifierChronologieDate(KListObject<KAssurer_lecon> ListeRDV){
		
		java.sql.Timestamp dateAvant = null;
		java.sql.Timestamp dateApres = null;
		
		int i = 1;
		if(ListeRDV.count() >= 2){
			while (i != ListeRDV.count()){
				dateAvant = ListeRDV.get(i-1).getIdAGENDA();
				dateApres = ListeRDV.get(i).getIdAGENDA();
				if (dateAvant.compareTo(dateApres) < 0){
					
				}
				else{
					return -4;
				}
			}
		}
		
		
		return 1;
	}
	
	/*
	 * creer une date au format SQL a partir dun type String
	 */
@SuppressWarnings("deprecation")
	public java.util.Date creerDate(String chaine){
		
		//System.out.println("Date : "+chaine);
		java.util.Date date = null;
		if(regexTraitementDate(chaine)){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = (java.util.Date) format.parse(chaine);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			date = new java.util.Date(0, 0, 0);//== 1899-12-31
		}
		return date;
		
	}
	
	/*
	 * Creer un temp au format SQL à partir d'untype String
	 */
	@SuppressWarnings("deprecation")
	public java.sql.Time creerHoraire(String chaine){
		//System.out.println("chaine 1 : "+chaine);
		//System.out.println(regexTraitementHoraire(chaine));
		java.sql.Time horaire = new java.sql.Time(0, 0, 0);
		
		if(regexTraitementHoraire(chaine)){
			int debsequence = 0;
			String tabHoraire[] = chaine.split(":");
			
			if(tabHoraire[0].substring(0,1) == "0"){
				debsequence = 1;
			}
			
			horaire.setHours(Integer.parseInt(tabHoraire[0].substring(debsequence,2)));
			debsequence = 0;
			if(tabHoraire[1].substring(0,1) == "0"){
				debsequence = 1;
				}
			horaire.setMinutes(Integer.parseInt(tabHoraire[1].substring(debsequence,2)));
		}
		
		return horaire;
	}
	
	/*
	 * Comparaison d'une date avec celle de la date du jour
	 */
	public int verifierDate(java.sql.Timestamp dateComparee){
		
		java.util.Date dateActuelle = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.format(dateActuelle);
		
		if(dateActuelle.compareTo(dateComparee) < 0 ){
			
		}
		else{
			return -1;
		}
		
		 return 1;
	}
	
	/*
	 * recuperation de l'id du moniteur à partir du tableau d'objet (pas moyen 
	 * de trouver comment renvoyer la valeur de la Jcombobox de la Jtable
	 */
	public int recupererIdMoniteur(String chaine){
		
		//System.out.println("chaine : "+chaine);
		KListObject<KMoniteur> Kliste = new DataMoniteur().recupererListe();
		for (KMoniteur moniteur : Kliste){
			String chaineComparree = moniteur.getNOM_MONITEUR().toUpperCase()+" "+moniteur.getPRENOM_MONITEUR().toLowerCase();
			if (chaineComparree.compareTo(chaine) == 0){
				return (Integer)moniteur.getId();
			}
		}
		
		return -1;//pour les tests, soit c'est bon, soit ça plante.
		
	}
	
	/*
	 * retourne le nombre de séance pratique de conduite
	 */
	public int compterSeance() {
		//System.out.println("nombre de seance :"+KlisteRef.count());
		return KlisteRef.count();
	}

	/*
	 * retourne le nombre d'heure passé à l'autoécole.
	 */
	public int compterHeure() {
		int somme = 0;
		for(int i =0; i< KlisteRef.count(); i++){
			somme = somme + KlisteRef.get(i).getDUREE_LECON();
		}
		//System.out.println("nombre d'heure :"+somme);
		return somme;
	}
	
	/*
	 * Remplace par zero si la regex n'est pas bonne
	 */
	public int verifierDuree(String chaine){
		if (!regexTraitementDuree(chaine)){
			return 0;
		}
		return Integer.parseInt(EnleverCreerDureeJtable(chaine));
	}
	
	/*
	 * Creation graphique de la duree (format 2h30)
	 */
	public String creerDureeJtable(String chaine){
		if(chaine.length() == 1){
			return chaine+"h00";
		}
		else{
			return chaine.substring(0, 1)+"h"+chaine.substring(1, chaine.length());
		}
		
	}
	
	/*
	 * 2h30 --> 230
	 */
	public String EnleverCreerDureeJtable(String chaine){
		String tabDuree[] = chaine.split("h");
		if(tabDuree[0].startsWith("0")){
			tabDuree[0]=tabDuree[0].substring(1, 2);
		}
		
		
		chaine = tabDuree[0]+tabDuree[1];
		//System.out.println(chaine);
		return chaine;
	}

	public boolean effacerRDVListe(KListObject<KAssurer_lecon> KlisteGraphique){
		
		/*
		 *  KlisteRef a été initialisé des le double clic sur un eleve. On va donc supprimer en BDD
		 *  les RDV supprimé graphiquement si la date est postérieur à la date actuelle (éviter la magouille).
		 */
		int index = 0; //le RDV de KlisteRef n'est pas present dans KlisteGraphique : il a été supprimé.
		for (int i = 0; i<KlisteRef.count(); i++){
			index = 0;
			for (int j = 0; j<KlisteGraphique.count(); j++){
				if (KlisteRef.get(i).getIdAGENDA() == KlisteGraphique.get(j).getIdAGENDA()){
					index = 1;
				}
			}
			
			if(index == 0){
				supprimerRDV(KlisteRef.get(i));
			}
		}
		
		return true;
	}
	
	public int effacerRDVvide(KListObject<KAssurer_lecon> klisteGraphique) {
		java.sql.Timestamp dateActuelle = new java.sql.Timestamp(2011, 1, 1, 1, 0, 0, 0);
		for (int j = 0; j<klisteGraphique.count(); j++){
			if(klisteGraphique.get(j).getDUREE_LECON() == 0){
				return -5;
			}
			if(klisteGraphique.get(j).getIdMONITEUR() == -1) {
				return -6;
			}
			if(klisteGraphique.get(j).getIdAGENDA().compareTo(dateActuelle)>0){
				return -7;
			}
		}
		
		return 1;
	}
	
	
	
	//REGEX
	public boolean regexTraitementDate(String chaine){
		return Pattern.matches("^[0-9]{4,4}+-+[0-9]{2,2}+-+[0-9]{2,2}+$",chaine);
	}
	
	public boolean regexTraitementHoraire(String chaine){
		return Pattern.matches("^[0-9]{1,2}+:+[0-9]{1,2}+$",chaine);
	}
	
	
	public boolean regexTraitementDuree(String chaine){
		return Pattern.matches("^[0-9]{1,2}+h+[0-9]{1,2}+$",chaine);
	}

	
/////////////////////////////////////////ACCESSEURS
	/**
	 * @return the klisteRef
	 */
	public KListObject<KAssurer_lecon> getKlisteRef() {
		return KlisteRef;
	}

	/**
	 * @param klisteRef the klisteRef to set
	 */
	public void setKlisteRef(KListObject<KAssurer_lecon> klisteRef) {
		KlisteRef = klisteRef;
	}

	

	
	
}
