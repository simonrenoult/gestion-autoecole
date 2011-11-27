package modele;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import controleur.EcouteurPrincipal;
import net.ko.kobject.KListObject;
import KClass.KAgenda;
import KClass.KAssurer_lecon;
import KClass.KEleve;
import KClass.KMoniteur;

public class TableauLecon {

	private Object [][] tableauRDVGraphique = null;
	
	private ArrayList<Object []>listeRdvGraphique = new ArrayList<Object []>();
	private ArrayList<Object []>listeRdvPost = new ArrayList<Object []>();
	private ArrayList<Object []>listeRdvAnt = new ArrayList<Object []>();
	
	private ArrayList<Object []>listeRdvMaj = new ArrayList<Object []>();
	private ArrayList<Object []>listeRdvNew = new ArrayList<Object []>();
	
	private ArrayList<Object []>listeRdvCrud = new ArrayList<Object []>();
	
	private KListObject<KAssurer_lecon> KlisteRdvBDD = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
	private KListObject<KAgenda> KlisteRdvAgenda =new KListObject<KAgenda>(KAgenda.class);
	
	private ArrayList<Object>listeRdvSup = new ArrayList<Object>();
	
	private String msgIntegriteMoniteur="bouh";
	
	public static int AUTO_ECOLE_OUVERTURE = 8;
	public static int AUTO_ECOLE_FERMETURE = 19;
	public static int numeroLigne = 0;
///////////////////////////////////////////////////////////CONSTRUCTEURS
	public  TableauLecon (){
		
	}
	
	public TableauLecon(Object [][] tableauRDV){
		this.tableauRDVGraphique = tableauRDV;
		
		int cpt = 0;
		Object[] ligneRDV = new Object[6];
		
		for(int i = 0; i<tableauRDV.length; i++){
			System.out.println();
			cpt = 0;
			ligneRDV = new Object[6];
			for(int j = 0; j<tableauRDV[i].length; j++){
				ligneRDV[cpt] = tableauRDVGraphique[i][j];
				cpt++;
			}
			listeRdvGraphique.add(ligneRDV);
		}
		
		
		
		
	}
	
//////////////////////////////////////////////////////////////METHODES


// ----------------------------------------- //
// -----------------METHODES---------------- //
// ----------------------------------------- //
	
	
	/*
	 * On separe les RDV de dates antérieur à la dte du jour avec ceux postérieur.
	 */
	public void separerRDV(){
		
		
		for(int i = 0; i<listeRdvGraphique.size(); i++){
			if(compareDateActuelle(listeRdvGraphique.get(i)[0].toString()) < 0 ){
				listeRdvAnt.add(tableauRDVGraphique[i]);
			}
			else{
				listeRdvPost.add(tableauRDVGraphique[i]);
			}
		}
	}
	
	/*
	 * compare une date du calendrier avec celle passer en parametre
	 * -1 : la date donnée en paramètre est antérieur
	 * 0 : même date
	 * 1 : la date donnée en paramètre est postérieur.
	 */
	public int compareDateActuelle(String Date){
		
		String [] tabRDV = Date.split("-");
		int anneeRDV = Integer.parseInt(tabRDV[0]);
		int moisRDV = Integer.parseInt(tabRDV[1]);
		int jourRDV = Integer.parseInt(tabRDV[2]);
		
		SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateActuelle = new java.util.Date();
		String []tabActuelle = formatdate.format(dateActuelle).split("-");
		int anneeActuelle =  Integer.parseInt(tabActuelle[0]);
		int moisActuel = Integer.parseInt(tabActuelle[1]);
		int jourActuel = Integer.parseInt(tabActuelle[2]);
		
		/*System.out.println("comparaison : "+anneeRDV+"-"+moisRDV+"-"+jourRDV+", "
											+anneeActuelle+"-"+moisActuel+"-"+jourActuel);
		*/
		if(anneeRDV < anneeActuelle){
			return -1;
		}else if (anneeRDV == anneeActuelle){
			if(moisRDV < moisActuel){
				return -1;
			}
			else if(moisRDV == moisActuel){
				if(jourRDV < jourActuel){
					return -1;
				}
				else if (jourRDV <= jourActuel){
					return 0;
				}
				else{
					return 1;
				}
			}
			else{
				return 1;
			}
		}
		else{
			return 1;
		}
		
		
		
	}
	
	/*
	 * compare deux dates (date1,heure1,date2,heure2)
	 * -1 : la date donnée en paramètre est antérieur (date1 < date2)
	 * 0 : même date (date1 == date2)
	 * 1 : la date donnée en paramètre est postérieur.(date1>date2)
	 */
	@SuppressWarnings("deprecation")
	public int compareDate(String date1, String heure1,String date2, String heure2){
		
		String [] tabRDV1 = date1.split("-");
		int anneeRDV1 = Integer.parseInt(tabRDV1[0]);
		int moisRDV1 = Integer.parseInt(tabRDV1[1]);
		int jourRDV1 = Integer.parseInt(tabRDV1[2]);
		
		String [] tabRDV2 = date2.split("-");
		int anneeRDV2 = Integer.parseInt(tabRDV2[0]);
		int moisRDV2 = Integer.parseInt(tabRDV2[1]);
		int jourRDV2 = Integer.parseInt(tabRDV2[2]);
		
		String [] tabHeure1 = heure1.split(":");
		int heureRDV1 = Integer.parseInt(tabHeure1[0]);
		int minutesRDV1= Integer.parseInt(tabHeure1[1]);
		
		String [] tabHeure2 = heure2.split(":");
		int heureRDV2 = Integer.parseInt(tabHeure2[0]);
		int minutesRDV2= Integer.parseInt(tabHeure2[1]);
		
		
		
		java.util.Date Date1 = new java.util.Date(anneeRDV1, moisRDV1, jourRDV1, heureRDV1, minutesRDV1);
		java.util.Date Date2 = new java.util.Date(anneeRDV2, moisRDV2, jourRDV2, heureRDV2, minutesRDV2);
		/*System.out.println("Comparaison : ");
		System.out.println(date1);
		System.out.println(date2);
		*/
		return Date1.compareTo(Date2);
	}
	
	/*
	 * Initialise les deux listes de references des RDV d'un eleve : assurerLecon et agenda. 
	 */
	public void initialiserListeRdvReference(int idEleve){

		KlisteRdvBDD.loadFromDb(BDD.db,"select * from assurer_lecon where idELEVE = "+idEleve+" order by idAgenda asc");
		
		KlisteRdvAgenda.loadFromDb(BDD.db,"select * from agenda where id in (select idAgenda from assurer_lecon where ideleve ="+idEleve+") " +
				"order by id asc");
	}
	
	/*
	 * Permet de séparer les RDV existant en BDD, les nouveaux RDV et les RDV à supprimer.
	 * Ces RDV sont separés à partir de la liste Post car on suppose que la liste Ant contient
	 * que des données correctes, cohérentes car non éditable (seulement la lecture est autorisé).
	 * Seule listePost est exploité (car tous les RDV sont postérierur >= à la date du jour).
	 */
	public void separerRDVEditable(int idEleve){
		int index,i,j,cpt = 0,cpt2=0;
		
		initialiserListeRdvReference(idEleve);
		
		// cas d'initialisation du logicel chez le client.
		if(KlisteRdvAgenda.count() == 0){
			for( j = 0; j<listeRdvPost.size() ; j++){
				listeRdvNew.add(listeRdvPost.get(j));
			}
		}
		else
		{
			for ( i = 0; i< listeRdvPost.size(); i++){
				index = 0;
				for( j = 0; j<KlisteRdvAgenda.count(); j++){
					//System.out.println();
					//System.out.println("comparaison date :agenda : "+KlisteRdvAgenda.get(j).getDATE_AGENDA().toString()+",listePost :"+listeRdvPost.get(i)[0] );
					//System.out.println("comparaison heur :agenda : "+KlisteRdvAgenda.get(j).getHEURE_AGENDA().toString().substring(0, 5)+",listePost :"+listeRdvPost.get(i)[1] );
					
					if((listeRdvPost.get(i)[0].toString().compareTo(KlisteRdvAgenda.get(j).getDATE_AGENDA().toString()) == 0) &&(
							listeRdvPost.get(i)[1].toString().compareTo(KlisteRdvAgenda.get(j).getHEURE_AGENDA().toString().substring(0, 5))==0)){
						//Le RDV existe déjà.
						listeRdvMaj.add(listeRdvPost.get(i));
						listeRdvMaj.get(cpt)[5] = String.valueOf(KlisteRdvAgenda.get(j).getId());
						index = 1;
						cpt++;
					}
				}
				if(index == 0 && !(listeRdvPost.isEmpty())){
					//c'est un nouveau RDV(caractérisé par sa date et son heure).
					listeRdvNew.add(cpt2,listeRdvPost.get(i));
					cpt2++;
				}
				//System.out.println("index vaut : "+index);
			}
		}
		
		//On créé une liste contenant tous les RDV existant du tableau.
		//On compare avec ceux de la bDD, ceux n'ayany pas d'équivalent ont été supprimé.
		ArrayList<Object []>tmp = new ArrayList<Object []>();
		for(j=0; j<listeRdvAnt.size(); j++){
			tmp.add(listeRdvAnt.get(j));
		}
		for(j=0; j<listeRdvMaj.size(); j++){
			tmp.add(listeRdvMaj.get(j));
		}
		
		if(tmp.size() != KlisteRdvBDD.count()){
			//System.out.println( KlisteRdvBDD.count()-tmp.size()+" rdv à supprimer");
			index = 0;
			for (i = 0; i<KlisteRdvBDD.count(); i++){
				index=0;
				for(j=0; j< tmp.size(); j++){
					if(concordanceRDV(KlisteRdvBDD.get(i),KlisteRdvAgenda.get(i),tmp.get(j))){
						index = 1;
					}
					
				}
				if(index == 0){
					listeRdvSup.add(KlisteRdvBDD.get(i).getIdAGENDA());
				}
			}
		}
		else{
			System.out.println("Rien à supprimer.");
		}
		
		
	}
	
	
	private boolean concordanceRDV(KAssurer_lecon rdv,
			KAgenda agenda, Object[] tab) {
	
		if(tab[0].toString().compareTo(agenda.getDATE_AGENDA().toString())==0){
			//System.out.println("ok1");
			if(tab[1].toString().compareTo(agenda.getHEURE_AGENDA().toString().substring(0, 5))==0){
				//System.out.println("ok2");
				if(String.valueOf(recupererDuree(tab[2].toString())).compareTo(String.valueOf(rdv.getDUREE_LECON())) ==0){
					//System.out.println("ok3");
					if(String.valueOf(recupererIdMoniteur(tab[3].toString())).compareTo(String.valueOf(rdv.getIdMONITEUR())) == 0){
						//System.out.println("ok4");
						if(tab[4].toString().compareTo(rdv.getOBSERVATION_LECON())==0){
							//System.out.println("ok5");
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}

	/*
	 * Vérification des données. Returne un code de message d'erreur en cas d'échec.
	 */
	public int verifierChampListeMaj(){
		int i, erreur;
		for (i = 0; i<listeRdvMaj.size(); i++){
			erreur = verifierRegexChamp(listeRdvMaj.get(i));
			if(erreur != 0) {
				numeroLigne = recupererNumeroLigne(listeRdvMaj.get(i));
				//System.out.println("Erreur à la ligne  : "+i);
				//System.out.println(messageRenvoyeeUI(erreur));
				return erreur;
			}
			
		}
		
		return 0;
	}
	
	private int recupererNumeroLigne(Object[] rdv){
		for (int i = 0; i< listeRdvGraphique.size(); i++){
			System.out.println(listeRdvGraphique.get(i)[1].toString());
			if((listeRdvGraphique.get(i)[0].toString().compareTo(rdv[0].toString())==0) &&
			 (listeRdvGraphique.get(i)[1].toString().compareTo(rdv[1].toString())==0) &&
			 (listeRdvGraphique.get(i)[2].toString().compareTo(rdv[2].toString())==0) &&
			 (listeRdvGraphique.get(i)[3].toString().compareTo(rdv[3].toString())==0) &&
			 (listeRdvGraphique.get(i)[4].toString().compareTo(rdv[4].toString())==0)){
				//System.out.println("la ligne est "+i);
				return i;
			}
		}
		
		return 0;
	}
	
	
	/*
	 * Vérification des données. Returne un code de message d'erreur en cas d'échec.
	 */
	public int verifierChampListeNouveau(){
		int i, erreur;
			for (i = 0; i<listeRdvNew.size(); i++){
				erreur = verifierRegexChamp(listeRdvNew.get(i));
				listeRdvNew.get(i)[5] = "NEW";
				if(erreur != 0) {
					numeroLigne = recupererNumeroLigne(listeRdvNew.get(i));
					System.out.println("Erreur à la ligne  : "+numeroLigne);
					//System.out.println(messageRenvoyeeUI(erreur));
					return erreur;
				}
				
			}
		
		return 0;
	}
	
	/*
	 * Si la date d'un RDV est antérieur sur le jour à la date actuelle, erreur.
	 * On assure donc tous les RDV existants et non existants comme RDV ayant une 
	 * date >= à celle d'aujourd'hui.
	 */
	public int verifierDatePostDateActuelle(String dateRdv) {
		
		if(compareDateActuelle(dateRdv) >= 0){
			return 0;
		}
		return -4;
	}

	/*
	 * Verifie les bornes des heures,minutes
	 */
	public int verifierHoraire(String chaine){
		//System.out.println("chaine : "+chaine);
		String tabHoraire[] = chaine.split(":");
		
		if((Integer.parseInt(tabHoraire[0]) < AUTO_ECOLE_OUVERTURE ) || (Integer.parseInt(tabHoraire[0])> AUTO_ECOLE_FERMETURE)){
			return -6;
		}
		if((Integer.parseInt(tabHoraire[1]) < 0 ) || (Integer.parseInt(tabHoraire[1]) >= 60 )){
			return -7;
		}
		
		 return 0;
	}
	
	/*
	 * Verfification des différentes données que compose un RDV.
	 */
	public int verifierRegexChamp(Object[] rdv){
		int erreur  = 0,i=0;
		
		while( (erreur == 0) && (i < 5)){
			//System.out.println("erreur : "+erreur+", cpt : "+i);
			//System.out.println("test dur tab["+i+"] : "+rdv[i]);
			switch (i){
				case 0 : if(!regexTraitementDate(rdv[0].toString())){erreur = -5;}break;
				case 1 : if(verifierDatePostDateActuelle(rdv[0].toString()) != 0){erreur = -4;}break;
				case 2 : if(!regexTraitementHoraire(rdv[1].toString())){erreur = -3;}break;
				case 3 : if(!regexTraitementDuree(rdv[2].toString())){erreur = -2;}break;
				case 4 : if((rdv[3].toString().compareTo("-") == 0)){erreur = -1;}break;
			}
			i++;
		}
		System.out.println("erreur vaut :"+erreur);
		if(erreur == 0){
			erreur = verifierHoraire(rdv[1].toString());
		}
		
		return erreur;
		
		
	}
	
	

	/*
	 * retourne le message d'erreur ou de validation
	 */
	public String messageRenvoyeeUI (int num){
		
		switch(num){
			case 5 : return "-\n";
				
			case 4 : return "-\n";
			
			case 3 : return "-Echec de l'enregistrement des RDV\n";
			
			case 2 : return "-Enregistrement des RDV terminé\n";
			
			case -1 : return "-Un moniteur doit être selectionné pour le RDV surligné.\n";
			
			case -2 : return "-La durée du RDV surligné présente un format invalide pour son enregistrement.\n";
			
			case -3 : return "-L'horaire du RDV surligné présente un format invalide pour son enregistrement.\n";
			
			case -4 : return "-La date de RDV surligné ne peut pas être antérieur à la date actuelle\n";
			
			case -5 : return "-La date de RDV surligné présente un format invalide pour son enregistrement.";
			
			case -6 : return "-L'horaire du RDV surligné est invalide : les heures sont compris entre "+AUTO_ECOLE_OUVERTURE+"h et "+AUTO_ECOLE_FERMETURE+"h\n";
			
			case -7 : return "-L'horaire du RDV surligné est invalide : les minutes sont compris entre 0 et 59.\n";
			
			case -8 : return "-Le RDV surligné est chevauché par un autre RDV.\n";
			
			case -9 : return "-"+msgIntegriteMoniteur+"\n";
		}
		
		return "";
		
	}
	
	/*
	 * On reassemble les deux types de RDV (MAJ et NEW) afin de verifier les contraintes
	 * d'intégrité.
	 */
	public void creerListeCrud(){
		int i;
		for (i = 0; i< listeRdvMaj.size(); i++){
			listeRdvCrud.add(listeRdvMaj.get(i));
		}
		for (i = 0; i< listeRdvNew.size(); i++){
			listeRdvCrud.add(listeRdvNew.get(i));
		}
		
		trierListeCrudDateCroissante();
		
	}
	
	public int verifierContrainteIntegrite(){
		int intEleve,intMoniteur;
		
		intEleve = verifierContraintIntegriteEleve();
		if(intEleve != 0){
			numeroLigne = intEleve;
			return -8;
		}
		
		intMoniteur = verifierContraintIntegriteMoniteur();
		if(intMoniteur != -1){
			numeroLigne = intMoniteur;
			return -9;
		}
		return 0;
	}
	
	
	/*
	 *cette contrainte consiste à regarder si l'élève n'empute pas sur 2 de ses RDv. 
	 *Comparaison avec le dernier RDV pris et le nouveau de la listeCrud, puis
	 *avec tous les RDV de la liste Crud.
	 */
	private int verifierContraintIntegriteEleve() {
		int i;
		int rdvPost,rdvAnt =  listeRdvAnt.size()-1;
		
		/*
		 * Comme la Jtable assure une date >= dateActuelle (setValueAt(Object value, int row, int col) sur JtableAssurerLeconModel,
		 * Tous les RDV sont forcement Posterieurs à la date actuelle -1. Par conséquent , on compare juste les RDv dans la liste.
		 */
		
		for (i = 1; i<listeRdvCrud.size(); i++){
			rdvAnt = i-1;
			rdvPost = i;
			if(!compareDateDureeEleve(listeRdvCrud.get(rdvAnt)[0].toString(), listeRdvCrud.get(rdvAnt)[1].toString(),listeRdvCrud.get(rdvAnt)[2].toString(), 
					listeRdvCrud.get(rdvPost)[0].toString(),listeRdvCrud.get(rdvPost)[1].toString())){
				System.out.println("\n\n");
				System.out.println("Probleme RDV "+i+" transition POST");
				return recupererNumeroLigne(listeRdvCrud.get(i));
			}
		}
		
		return 0;
		
	}
	
	/*
	 * Verifie la contrainte que chaque moniteur de la ligne des RDV à Inserer/MAJ 
	 * sont dsiponibles. On prend donc la liste listeRDVCrud où chaque ligne est analysé.
	 */
	private int verifierContraintIntegriteMoniteur() {
		
		int i, j;
		String dateRDV, nomMoniteur;
		KListObject<KAssurer_lecon> KlisteAssurerLecon = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		KListObject<KAgenda> KlisteAgenda =new KListObject<KAgenda>(KAgenda.class);
		
		for (i = 0 ; i < listeRdvCrud.size() ; i++){
			
			//System.out.println("---------------------------");
			
			
			dateRDV = listeRdvCrud.get(i)[0].toString();
			
			//System.out.println("date comparee : "+listeRdvCrud.get(i)[0].toString());
			
			nomMoniteur = listeRdvCrud.get(i)[3].toString();
			KlisteAssurerLecon.loadFromDb(BDD.db, "select * from assurer_lecon where idagenda in "+
					"(select id from agenda where date_agenda = '"+dateRDV+"') "+
					"and idmoniteur = "+recupererIdMoniteur(nomMoniteur)+" order by idagenda asc");
			
			//System.out.println("Etat AssurerLecon :");
			//System.out.println(KlisteAssurerLecon);
			
			KlisteAgenda.loadFromDb(BDD.db, "select * from agenda where id in "+
			"(select idAgenda from assurer_Lecon where idMoniteur = "+recupererIdMoniteur(nomMoniteur)+") "+
					"and date_agenda = '"+dateRDV+"' order by id asc");
		
			//System.out.println("Etat Agenda : ");
			//System.out.println(KlisteAgenda);
			
			for (j = 0 ; j < KlisteAssurerLecon.count() ; j++){
			//on evite la comparaison entre un eleve selectinné et son propore RDV --> conflit.
			if(KlisteAssurerLecon.get(i).getIdELEVE() != (Long)EcouteurPrincipal.Eleve.getId()){
				if(conflitHoraireMoniteur(listeRdvCrud.get(i),KlisteAssurerLecon.get(j),
						KlisteAgenda.get(j))){
					creerMsgIntegriteMoniteur(listeRdvCrud.get(i),KlisteAssurerLecon.get(j),KlisteAgenda.get(j));
					return recupererNumeroLigne(listeRdvCrud.get(i));
				}
			}
				
			}
		
		}
		
		return -1;
	}
	
	/*
	 * Creation du message d'erreur concernant l'integrite du moniteur.
	 * Ce message replace tout le contexte de la non validation.
	 */
	private void creerMsgIntegriteMoniteur(Object[] rdv,
			KAssurer_lecon lecon, KAgenda agenda) {
		
		KEleve eleve = new KEleve();
		try {
			eleve.loadOneById(BDD.db,lecon.getIdELEVE());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String time = agenda.getHEURE_AGENDA().toString().substring(0, 5);
		String duree = String.valueOf(lecon.getDUREE_LECON()).substring(0,1)+"h"+
				String.valueOf(lecon.getDUREE_LECON()).substring(1,3);
		
		msgIntegriteMoniteur = "Le moniteur "+rdv[3].toString()+
				" à déjà un RDV avec l'élève "+eleve.getNOM_ELEVE().toUpperCase()+
				" "+eleve.getPRENOM_ELEVE()+" le "+agenda.getDATE_AGENDA().toString()+
				" à "+time+" pendant "+duree+".";
		
	}

	@SuppressWarnings("deprecation")
	private boolean conflitHoraireMoniteur(Object[] rdv,
			KAssurer_lecon lecon, KAgenda agenda) {
		
		
		
		SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		java.util.Date dateRDVCompareeDeb=null;
		try {
			dateRDVCompareeDeb = (java.util.Date) formatdate.parse(rdv[0].toString()+"-0-0");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String [] tabHeure1 = rdv[1].toString().split(":");
		int heureRDV1 = Integer.parseInt(tabHeure1[0]);
		int minutesRDV1= Integer.parseInt(tabHeure1[1]);
		
		dateRDVCompareeDeb .setHours(heureRDV1);
		dateRDVCompareeDeb.setMinutes(minutesRDV1);
		
		String [] duree = rdv[2].toString().split("h");
		int dureeheure1 = Integer.parseInt(duree[0]);
		int dureeminute1 = Integer.parseInt(duree[1]);
		
		
		java.util.Date dateRDVCompareeFin = new java.util.Date(dateRDVCompareeDeb.getYear(),
				dateRDVCompareeDeb.getMonth(),dateRDVCompareeDeb.getDate(),
				dateRDVCompareeDeb.getHours()+dureeheure1,dateRDVCompareeDeb.getMinutes()+dureeminute1);
		
		
		
		int anneeRDV2 =agenda.getDATE_AGENDA().getYear();
		int moisRDV2 = agenda.getDATE_AGENDA().getMonth();
		int jourRDV2 = agenda.getDATE_AGENDA().getDate();
		int heureRDV2 = agenda.getHEURE_AGENDA().getHours();
		int minutesRDV2= agenda.getHEURE_AGENDA().getMinutes();
		int dureeheure2 = Integer.parseInt(String.valueOf(lecon.getDUREE_LECON()).substring(0, 1));
		int dureeminute2 =Integer.parseInt(String.valueOf(lecon.getDUREE_LECON()).substring(1, 3)); 
		
		
		java.util.Date dateRDVBDDdeb = new java.util.Date(anneeRDV2, moisRDV2, jourRDV2,
				heureRDV2, minutesRDV2) ;
		
		java.util.Date dateRDVBDDfin = new java.util.Date(anneeRDV2, moisRDV2, jourRDV2,
				heureRDV2+dureeheure2, minutesRDV2+dureeminute2) ;
		
		
		//System.out.println("dateComparee Liste CRUD : "+dateRDVCompareeDeb+","+dateRDVCompareeFin);
		//System.out.println("dateRDVBDD : "+dateRDVBDDdeb+","+dateRDVBDDfin);
		
		
		
		//((dateRDVCompareeFin.compareTo(dateRDVBDDdeb) <= 0) || (dateRDVCompareeDeb.compareTo(dateRDVBDDfin)>=0));
		return !((dateRDVCompareeFin.compareTo(dateRDVBDDdeb) <= 0) || 
				(dateRDVCompareeDeb.compareTo(dateRDVBDDfin) >=0));
		
		
		
		
		
		
		
		
		
		/*String [] tabRDV1 = rdv[0].toString().split("-");
		int anneeRDV1 = Integer.parseInt(tabRDV1[0]);
		int moisRDV1 = Integer.parseInt(tabRDV1[1]);
		int jourRDV1 = Integer.parseInt(tabRDV1[2]);
		
		String [] tabHeure1 = rdv[1].toString().split(":");
		int heureRDV1 = Integer.parseInt(tabHeure1[0]);
		int minutesRDV1= Integer.parseInt(tabHeure1[1]);
		
		java.util.Date dateRDVCompareeDeb = new java.util.Date(anneeRDV1, moisRDV1,
				jourRDV1,heureRDV1,minutesRDV1);
		
		String [] duree = rdv[2].toString().split("h");
		int dureeheure1 = Integer.parseInt(duree[0]);
		int dureeminute1 = Integer.parseInt(duree[1]);
		
		java.util.Date dateRDVCompareeFin = new java.util.Date(anneeRDV1, moisRDV1,
				jourRDV1,heureRDV1+dureeheure1,minutesRDV1+dureeminute1);
		
		
		
		int anneeRDV2 =agenda.getDATE_AGENDA().getYear();
		int moisRDV2 = agenda.getDATE_AGENDA().getMonth();
		int jourRDV2 = agenda.getDATE_AGENDA().getDate();
		int heureRDV2 = agenda.getHEURE_AGENDA().getHours();
		int minutesRDV2= agenda.getHEURE_AGENDA().getMinutes();
		int dureeheure2 = Integer.parseInt(String.valueOf(lecon.getDUREE_LECON()).substring(0, 1));
		int dureeminute2 =Integer.parseInt(String.valueOf(lecon.getDUREE_LECON()).substring(1, 3)); 
		
		
		java.util.Date dateRDVBDDdeb = new java.util.Date(anneeRDV2, moisRDV2, jourRDV2,
				heureRDV2, minutesRDV2) ;
		
		java.util.Date dateRDVBDDfin = new java.util.Date(anneeRDV2, moisRDV2, jourRDV2,
				heureRDV2+dureeheure2, minutesRDV2+dureeminute2) ;
		
		
		System.out.println("dateComparee Liste CRUD : "+dateRDVCompareeDeb+","+dateRDVCompareeFin);
		System.out.println("dateRDVBDD : "+dateRDVBDDdeb+","+dateRDVBDDfin);
		
		//return dateRDVCompareeFin.before(dateRDVBDDdeb) || dateRDVCompareeDeb.after(dateRDVBDDfin);
		return ((dateRDVCompareeFin.compareTo(dateRDVBDDdeb) <= 0) || 
				(dateRDVCompareeDeb.compareTo(dateRDVBDDfin)>=0));
		*/
		
	}

	/*
	 * Comme la liste des RDV eleve est dans un ordre chronologique, on regarde simplement 
	 * si le RDV d'avant + la duree est antérieur à la date de RDV du suivant.
	 */
	@SuppressWarnings("deprecation")
	private boolean compareDateDureeEleve(String date1, String heure1,
			String duree1, String date2, String heure2) {
		
		String [] tabRDV1 = date1.split("-");
		int anneeRDV1 = Integer.parseInt(tabRDV1[0]);
		int moisRDV1 = Integer.parseInt(tabRDV1[1]);
		int jourRDV1 = Integer.parseInt(tabRDV1[2]);
		
		String [] tabRDV2 = date2.split("-");
		int anneeRDV2 = Integer.parseInt(tabRDV2[0]);
		int moisRDV2 = Integer.parseInt(tabRDV2[1]);
		int jourRDV2 = Integer.parseInt(tabRDV2[2]);
		
		String [] tabHeure1 = heure1.split(":");
		int heureRDV1 = Integer.parseInt(tabHeure1[0]);
		int minutesRDV1= Integer.parseInt(tabHeure1[1]);
		
		String [] tabHeure2 = heure2.split(":");
		int heureRDV2 = Integer.parseInt(tabHeure2[0]);
		int minutesRDV2= Integer.parseInt(tabHeure2[1]);
		
		String [] tabDuree1 = duree1.split("h");
		
		
		java.util.Date dateRDV1fin = new java.util.Date(anneeRDV1,moisRDV1,jourRDV1,
				heureRDV1+Integer.parseInt(tabDuree1[0]),minutesRDV1+Integer.parseInt(tabDuree1[1]));
		
		
		java.util.Date dateRDV2 = new java.util.Date(anneeRDV2,moisRDV2,jourRDV2,heureRDV2,minutesRDV2);
		
		if(dateRDV1fin.compareTo(dateRDV2)<=0){
			return true;
		}
		return false;
	}
	
	/*
	 * On remet dans l'ordre des dates la liste. 
	 */
	public void trierListeCrudDateCroissante(){
		int i,j;
		Object [] tmp = new Object [6];
		
		if (listeRdvCrud.size()>1){
			for (i = 0; i< listeRdvCrud.size(); i++){
				for (j = 0; j< listeRdvCrud.size(); j++){
					tmp = listeRdvCrud.get(i);
					if(compareDate(listeRdvCrud.get(j)[0].toString(),listeRdvCrud.get(j)[1].toString(),
					tmp[0].toString(),tmp[1].toString()) > 0){
						listeRdvCrud.set(i, listeRdvCrud.get(j));
						listeRdvCrud.set(j, tmp);
					}
				}
			}
		}
	}
	
	/*
	 * suppression des RDV que l'utilisateur a fait (dans assurerLecon et agenda).
	 * Il n'ya plus de trace du RDV effacé.
	 */
	public boolean supprimerRDV(int idEleve){
		int i;
		for (i = 0; i< listeRdvSup.size(); i++){
			if(!supprimerRdvAssurerLecon(listeRdvSup.get(i), idEleve)){
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * on supprime de la base un RDV (assurerLecon + agenda) grâce 
	 * à l'idAgenda et Eleve.
	 */
	private boolean supprimerRdvAssurerLecon(Object idAgenda,int idEleve) {
		boolean req1,req2;
		java.sql.Connection conn;
		try {
			conn = DriverManager.getConnection ("jdbc:mysql://localhost/autoecole4","admin","admin");
			Statement statement = conn.createStatement();
			statement.executeUpdate("delete from assurer_lecon where idEleve = "+idEleve+" and idAgenda = "+idAgenda.toString());
			req1 = true;
		} catch (SQLException e) {
			req1 = false;
			e.printStackTrace();
		}
		//System.out.println("req1 : "+req1);
		try {
			conn = DriverManager.getConnection ("jdbc:mysql://localhost/autoecole4","admin","admin");
			Statement statement = conn.createStatement();
			statement.executeUpdate("delete from agenda where id = "+idAgenda.toString());
			req2 = true;
		} catch (SQLException e) {
			req2 = false;
			e.printStackTrace();
		}
		//System.out.println("req2 : "+req2);
		
		return req1 && req2;
	}

	private boolean ajouterAgendaBDD(KAgenda agenda){
		java.sql.Connection conn;
		boolean req1;
		try {
			conn = DriverManager.getConnection ("jdbc:mysql://localhost/autoecole4","admin","admin");
			//java.sql.Statement st = conn.createStatement();
			//ResultSet r=st.executeQuery("select * from assurer_lecon where idmoniteur = "+idMoniteur+" order by idAgenda asc");
			//while (r.next()) {}
			//System.out.println(agenda.getId().toString()+"    "+agenda.getDATE_AGENDA()+"    "+agenda.getHEURE_AGENDA());
			//System.out.println("INSERT INTO Agenda VALUES ("+id+",'"+agenda.getDATE_AGENDA()+"','"+agenda.getHEURE_AGENDA()+"' )");
			Statement statement = conn.createStatement();
			statement.executeUpdate("INSERT INTO Agenda VALUES ("+(Integer)agenda.getId()+",'"+agenda.getDATE_AGENDA()+"','"+agenda.getHEURE_AGENDA()+"' )");
			req1 = true;
		} catch (SQLException e) {
			req1 = false;
			e.printStackTrace();
		}
		
		return req1;
	}
	
	/*
	 * Processu d'insert de nouveaux RDV.
	 */
	public boolean insererNouveauRDV(ArrayList<Object []> rdvNew){
		int i;
		Object idAgenda;
		for (i = 0; i< listeRdvNew.size(); i++){
			idAgenda = creeridAgenda(listeRdvNew.get(i)[3].toString());
			if(!((ajouterAgendaBDD(creerObjetAgenda(listeRdvNew.get(i),idAgenda)) &&
				(creerObjetAssurerLecon(listeRdvNew.get(i),(Integer)idAgenda).add(BDD.db))))){
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Retourne un objet de type KAgenda instancié.
	 */
	private KAgenda creerObjetAgenda(Object[] rdv,Object idAgenda){
		KAgenda agenda = new KAgenda();
		agenda.setDATE_AGENDA(creerDateSql(rdv[0].toString()));
		agenda.setHEURE_AGENDA(creerHeureSql(rdv[1].toString()));
		agenda.setId(idAgenda);
		System.out.println(agenda);
		return agenda;
	}
	
	/*
	 * On assure l'identifiant unique de la table Agenda. Penser
	 * pour supporter plusieurs ordianteur afin d'éviter les redondances
	 * de clé primaire lors de la synchronisation.
	 * Basé sur l'id du moniteur.
	 */
	private Object creeridAgenda(String chaine) {
		
		int idMoniteur = recupererIdMoniteur(chaine);
		System.out.println("avant requete, idmoniteur vaut : "+idMoniteur);
		KListObject<KAssurer_lecon> assurerLecon = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		
		assurerLecon.loadFromDb(BDD.db,"select * from assurer_lecon where idmoniteur = "+idMoniteur+" order by idAgenda asc");
		
		//System.out.println("Liste des RDV : ");
		//System.out.println(agenda);
		//System.out.println("----------------->");
		
		if (assurerLecon.count() == 0){
		return (Integer.parseInt(idMoniteur+"000"));
		}
		return assurerLecon.get(assurerLecon.count()-1).getIdAGENDA()+1;
	}

	/*
	 * Permet de creer un objet Time à partir d'une chaine.
	 * Utile pour instancier les attributs de classe tel que KAgenda.
	 */
	@SuppressWarnings("deprecation")
	private java.sql.Time creerHeureSql(String chaine) {
		String[] tab = chaine.split(":");
		java.sql.Time heure = new java.sql.Time(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]),0);
		return heure;
		
	}
	
	/*
	 * Permet de creer un objet date à partir d'une chaine.
	 * Utile pour instancier les attributs de classe tel que KAgenda.
	 * si compliqué d'avoir la bonne date ! Une instanciation directe ne fonctionne pas !
	 */
	@SuppressWarnings("deprecation")
	private java.sql.Date creerDateSql(String chaine) {
		
		java.sql.Date date2 = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date;
		try {
			date = (java.util.Date) format.parse(chaine);
			date2 = new java.sql.Date(date.getYear(), date.getMonth(), date.getDate());
			System.out.println("date : "+date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date2;
	}

	/*
	 * processus de MAJ de nouveaux RDV;
	 */
	public boolean MajRDV(ArrayList<Object []> rdvMaj){
		int i;
		for (i = 0; i<rdvMaj.size(); i++){
			if(!creerObjetAssurerLecon(rdvMaj.get(i)).update(BDD.db)){
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Retourne un objet de type KAssurer_Lecon instancié.
	 */
	private KAssurer_lecon creerObjetAssurerLecon(Object[] rdv,int idAgenda) {
		rdv[5] = String.valueOf(idAgenda);
		return creerObjetAssurerLecon(rdv);
	}
	
	/*
	 * Creation d'un objet AssurerLecon à partir d'une ligne de RDV graphique
	 */
	private KAssurer_lecon creerObjetAssurerLecon(Object[] rdv) {
		
		KAssurer_lecon lecon = new KAssurer_lecon();
		lecon.setIdAGENDA((Integer.parseInt(rdv[5].toString())));
		lecon.setIdELEVE(((Long)EcouteurPrincipal.Eleve.getId()).intValue());
		lecon.setIdMONITEUR(recupererIdMoniteur(rdv[3].toString()));
		lecon.setDUREE_LECON(recupererDuree(rdv[2].toString()));
		lecon.setOBSERVATION_LECON(rdv[4].toString());
		System.out.println(lecon);
		return lecon;
	}

	/*
	 * Convertit la durée affichée en nombre : 2h30 -> 230
	 */
	private int recupererDuree(String chaine) {
		if(chaine.compareTo("")==0){
			return -1;
		}
		String tabDuree[] = chaine.split("h");
		if(tabDuree[0].startsWith("0")){
			tabDuree[0]=tabDuree[0].substring(1, 2);
		}
		try{
			chaine = tabDuree[0]+tabDuree[1];
		}
		catch(ArrayIndexOutOfBoundsException e){
			
		}
		
		//System.out.println(chaine);
		return Integer.parseInt(chaine);
		
	}

	/*
	 * Recupere l'id du moniteur à partir du nom et prenom (limite de ma Jtable !)
	 */
	private int recupererIdMoniteur(String chaine) {
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
	 * A partir de la liste KlisteCrud, soit le champ [5] est "MAJ" ou "NEW", on redirige.
	 */
	public boolean InsererRDV(){
		
		ArrayList<Object []>RdvMaj = new ArrayList<Object []>();
		ArrayList<Object []>RdvNouveau = new ArrayList<Object []>();
		
		int i;
		for(i=0; i< listeRdvCrud.size(); i++){
			if(listeRdvCrud.get(i)[5].toString().compareTo("NEW") == 0 ){
				RdvNouveau.add(listeRdvCrud.get(i));
			}
			else{
				RdvMaj.add(listeRdvCrud.get(i));
			}
		}
		
		boolean ok = MajRDV(RdvMaj);
		boolean ok1 = insererNouveauRDV(RdvNouveau);
		
		return ok && ok1;
	}
	
////////////////////////////////////////////////////////////////
//AFFICHAGE
////////////////////////////////////////////////////////////////
	
	public void afficherListeRdvGraphique(){
		System.out.println();
		System.out.println("Etat de la liste RDV Graphique : ");
		for (int i = 0; i<listeRdvGraphique.size(); i++){
			System.out.println();
			for(int j = 0; j<listeRdvGraphique.get(i).length; j++){
				System.out.print("tab["+j+"]="+listeRdvGraphique.get(i)[j]);
			}
		}
	}
	
	public void afficherListeRdvAnt(){
		System.out.println();
		System.out.println("Etat de la liste RDV Ant : ");
		for (int i = 0; i<listeRdvAnt.size(); i++){
			System.out.println();
			for(int j = 0; j<listeRdvAnt.get(i).length; j++){
				System.out.print("tab["+j+"]="+listeRdvAnt.get(i)[j]);
			}
		}
	}
	
	public void afficherListeRdvPost(){
		System.out.println();
		System.out.println("Etat de la liste RDV Post : ");
		for (int i = 0; i<listeRdvPost.size(); i++){
			System.out.println();
			for(int j = 0; j<listeRdvPost.get(i).length; j++){
				System.out.print("tab["+j+"]="+listeRdvPost.get(i)[j]);
			}
		}
	}
	
	public void afficherListeRdvMaj(){
		System.out.println();
		System.out.println("Etat de la liste RDV à MAJ : ");
		for (int i = 0; i<listeRdvMaj.size(); i++){
			System.out.println();
			for(int j = 0; j<listeRdvMaj.get(i).length; j++){
				System.out.print("tab["+j+"]="+listeRdvMaj.get(i)[j]);
			}
		}	
	}
	
	public void afficherListeRdvNew(){
		System.out.println();
		System.out.println("Etat de la liste RDV (new)  : ");
		for (int i = 0; i<listeRdvNew.size(); i++){
			System.out.println();
			for(int j = 0; j<listeRdvNew.get(i).length; j++){
				System.out.print("tab["+j+"]="+listeRdvNew.get(i)[j]);
			}
		}	
	}
	
	public void afficherListeRdvsupp(){
		System.out.println();
		System.out.println("Etat de la liste RDV (supp)  : ");
		for (int i = 0; i<listeRdvSup.size(); i++){
			System.out.print(listeRdvSup.get(i)+", ");
			
		}	
	}
	
	public void afficherListeCrud(){
		System.out.println();
		System.out.println("Etat de la liste RDV (crud) : ");
		for (int i = 0; i<listeRdvCrud.size(); i++){
			System.out.println();
			for(int j = 0; j<listeRdvCrud.get(i).length; j++){
				System.out.print("tab["+j+"]="+listeRdvCrud.get(i)[j]);
			}
		}
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
	 * recupere la liste des dates et horaire des leçons d'un élève.
	 */
	public KListObject<KAgenda> recupererAgendaRDV(int id){
		KListObject<KAgenda> Kliste =new KListObject<KAgenda>(KAgenda.class);
		Kliste.loadFromDb(BDD.db,"select * from agenda where id in (select idAgenda from assurer_lecon where idEleve = "+id+")");
		return Kliste;
	}
	

	/*
	 * On recupere la liste des RDV dans l'ordre chronologique.
	 */
	public KListObject<KAssurer_lecon>  chargerListeRDVEleve(int id){
		int idAgenda;
		
		KListObject<KAssurer_lecon> Kliste =new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		KAssurer_lecon lecon = null;
		KListObject<KAgenda> Kliste1 =new KListObject<KAgenda>(KAgenda.class);
		Kliste1.loadFromDb(BDD.db,"select * from agenda where id in (select idAgenda from assurer_lecon where ideleve ="+id+") " +
				"order by date_agenda asc, heure_agenda asc");
		
		for(int i = 0; i<Kliste1.count(); i++){
			idAgenda = ((Integer)Kliste1.get(i).getId());
			lecon = new KAssurer_lecon();
			try {
				lecon.loadOne(BDD.db," idAgenda = "+idAgenda+" and idEleve ="+id);
				Kliste.add(lecon);
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
		
		return Kliste;
	}
	
	/*
	 * retourne le nombre de séance pratique de conduite
	 */
	public int compterSeance() {
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		Kliste.loadFromDb(BDD.db,"select * from assurer_lecon where idELEVE = "+((Long)EcouteurPrincipal.Eleve.getId()).intValue()
				+" order by idAgenda asc");
		return Kliste.count();
	}
	

	/*
	 * retourne le nombre d'heure passé à l'autoécole.
	 */
	public String compterHeure() {
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(KAssurer_lecon.class);
		Kliste.loadFromDb(BDD.db,"select * from assurer_lecon where idELEVE = "+((Long)EcouteurPrincipal.Eleve.getId()).intValue()
				+" order by idAgenda asc");
		String somme;
		int minutes = 0;
		int heures=0;
		int minutesModulo = 0;
		int minutesDivise = 0;
		int sommeHeure = 0;
		int sommeMinute =0;
		for(int i =0; i< Kliste.count(); i++){
			//parser en string.
			
			heures = Integer.parseInt(String.valueOf(Kliste.get(i).getDUREE_LECON()).substring(0, 1));
			minutes = Integer.parseInt( String.valueOf(Kliste.get(i).getDUREE_LECON()).substring(1, 3));
			
			//System.out.println("heures : "+heures+"   minutes : "+minutes);
			
			minutesModulo = minutes % 60;
			minutesDivise = minutes / 60;
			
			sommeMinute = sommeMinute + minutesModulo;
			sommeHeure = sommeHeure + heures + minutesDivise;
			
			//System.out.println("somme heure : "+sommeHeure+"    sommeMinutes : "+sommeMinute);
			
		}
		
		sommeHeure = sommeHeure + sommeMinute / 60;
		sommeMinute = sommeMinute % 60;
		if (sommeMinute == 0){
			somme = String.valueOf(sommeHeure) +"h";
		}
		else{
			somme = String.valueOf(sommeHeure) +"h" + String.valueOf(sommeMinute);
		}
		
		return somme;
	}

	////////////////////////////////////////////////////////////////
	//REGEX
	////////////////////////////////////////////////////////////////
	
	
	public boolean regexTraitementDate(String chaine){
		return Pattern.matches("^[0-9]{4,4}+-+[0-9]{2,2}+-+[0-9]{2,2}+$",chaine);
	}
	
	public boolean regexTraitementHoraire(String chaine){
		return Pattern.matches("^[0-9]{2,2}+:+[0-9]{2,2}+$",chaine);
	}

	public boolean regexTraitementDuree(String chaine){
		return Pattern.matches("^[0-9]{1,1}+h+[0-9]{2,2}+$",chaine);
	}
	
	

}
