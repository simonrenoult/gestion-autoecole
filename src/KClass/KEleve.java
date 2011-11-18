
package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KEleve
 */
@SuppressWarnings("serial")
public class KEleve extends KObject
{
	private KListObject<KPasser>		passers;
	private KListObject<KRealiser>		realisers;
	private KListObject<KSuperviser>	supervisers;
	private String						ADRESSE_ELEVE;
	private String						CODE_POSTAL_ELEVE;
	private String						PROFESSION_ELEVE;
	private String						MAIL_ELEVE;
	private java.sql.Date				DATE_EVAL_ELEVE;
	private int							VOLUME_HORAIRE_PRATIQUE_ELEVE;
	private String						PHOTO_ELEVE;
	private KMoniteur					moniteur;
	private String						NOM_ELEVE;
	private KFormation					formation;
	private int							TEST_VU_ELEVE;
	private String						OBSERVATION_VUE_ELEVE;
	private java.sql.Date				DATE_ENREGISTREMENT_ELEVE;
	private String						PRENOM_ELEVE;
	private int							idMONITEUR;
	private KListObject<KReponse>		reponses;
	private KListObject<KAssurer_lecon>	assurer_lecons;
	private String						TELEPHONE_ELEVE;
	private int							RESULTAT_ELEVE_ORAL;
	private java.sql.Date				DATE_INSCRIPTION_ELEVE;
	private String						COMMUNE_ELEVE;
	private int							idFORMATION;
	private int							VOLUME_HORAIRE_TH_ELEVE;
	private String						LIVRET_NEPH_ELEVE;
	private java.sql.Date				DATE_DE_NAISS_ELEVE;
	
	public KEleve()
	{
		super();
		keyFields = "id";
		tableName = "eleve";
		belongsTo(KMoniteur.class);
		belongsTo(KFormation.class);
		hasMany(KSuperviser.class);
		hasMany(KReponse.class);
		hasMany(KRealiser.class);
		hasMany(KPasser.class);
		hasMany(KAssurer_lecon.class);
		
	}
	
	public KListObject<KPasser> getPassers()
	{
		// return the value of passers
		return this.passers;
	}
	
	public KListObject<KRealiser> getRealisers()
	{
		// return the value of realisers
		return this.realisers;
	}
	
	public KListObject<KSuperviser> getSupervisers()
	{
		// return the value of supervisers
		return this.supervisers;
	}
	
	public String getADRESSE_ELEVE()
	{
		// return the value of ADRESSE_ELEVE
		return this.ADRESSE_ELEVE;
	}
	
	public String getCODE_POSTAL_ELEVE()
	{
		// return the value of CODE_POSTAL_ELEVE
		return this.CODE_POSTAL_ELEVE;
	}
	
	public String getPROFESSION_ELEVE()
	{
		// return the value of PROFESSION_ELEVE
		return this.PROFESSION_ELEVE;
	}
	
	public String getMAIL_ELEVE()
	{
		// return the value of MAIL_ELEVE
		return this.MAIL_ELEVE;
	}
	
	public java.sql.Date getDATE_EVAL_ELEVE()
	{
		// return the value of DATE_EVAL_ELEVE
		return this.DATE_EVAL_ELEVE;
	}
	
	public int getVOLUME_HORAIRE_PRATIQUE_ELEVE()
	{
		// return the value of VOLUME_HORAIRE_PRATIQUE_ELEVE
		return this.VOLUME_HORAIRE_PRATIQUE_ELEVE;
	}
	
	public String getPHOTO_ELEVE()
	{
		// return the value of PHOTO_ELEVE
		return this.PHOTO_ELEVE;
	}
	
	public KMoniteur getMoniteur()
	{
		// return the value of moniteur
		return this.moniteur;
	}
	
	public String getNOM_ELEVE()
	{
		// return the value of NOM_ELEVE
		return this.NOM_ELEVE;
	}
	
	public KFormation getFormation()
	{
		// return the value of formation
		return this.formation;
	}
	
	public int getTEST_VU_ELEVE()
	{
		// return the value of TEST_VU_ELEVE
		return this.TEST_VU_ELEVE;
	}
	
	public String getOBSERVATION_VUE_ELEVE()
	{
		// return the value of OBSERVATION_VUE_ELEVE
		return this.OBSERVATION_VUE_ELEVE;
	}
	
	public java.sql.Date getDATE_ENREGISTREMENT_ELEVE()
	{
		// return the value of DATE_ENREGISTREMENT_ELEVE
		return this.DATE_ENREGISTREMENT_ELEVE;
	}
	
	public String getPRENOM_ELEVE()
	{
		// return the value of PRENOM_ELEVE
		return this.PRENOM_ELEVE;
	}
	
	public int getIdMONITEUR()
	{
		// return the value of idMONITEUR
		return this.idMONITEUR;
	}
	
	public KListObject<KReponse> getReponses()
	{
		// return the value of reponses
		return this.reponses;
	}
	
	public KListObject<KAssurer_lecon> getAssurer_lecons()
	{
		// return the value of assurer_lecons
		return this.assurer_lecons;
	}
	
	public String getTELEPHONE_ELEVE()
	{
		// return the value of TELEPHONE_ELEVE
		return this.TELEPHONE_ELEVE;
	}
	
	public int getRESULTAT_ELEVE_ORAL()
	{
		// return the value of RESULTAT_ELEVE_ORAL
		return this.RESULTAT_ELEVE_ORAL;
	}
	
	public java.sql.Date getDATE_INSCRIPTION_ELEVE()
	{
		// return the value of DATE_INSCRIPTION_ELEVE
		return this.DATE_INSCRIPTION_ELEVE;
	}
	
	public String getCOMMUNE_ELEVE()
	{
		// return the value of COMMUNE_ELEVE
		return this.COMMUNE_ELEVE;
	}
	
	public int getIdFORMATION()
	{
		// return the value of idFORMATION
		return this.idFORMATION;
	}
	
	public int getVOLUME_HORAIRE_TH_ELEVE()
	{
		// return the value of VOLUME_HORAIRE_TH_ELEVE
		return this.VOLUME_HORAIRE_TH_ELEVE;
	}
	
	public String getLIVRET_NEPH_ELEVE()
	{
		// return the value of LIVRET_NEPH_ELEVE
		return this.LIVRET_NEPH_ELEVE;
	}
	
	public java.sql.Date getDATE_DE_NAISS_ELEVE()
	{
		// return the value of DATE_DE_NAISS_ELEVE
		return this.DATE_DE_NAISS_ELEVE;
	}
	
	public void setPassers(KListObject<KPasser> aPassers)
	{
		// set the value of passers
		this.passers = aPassers;
	}
	
	public void setRealisers(KListObject<KRealiser> aRealisers)
	{
		// set the value of realisers
		this.realisers = aRealisers;
	}
	
	public void setSupervisers(KListObject<KSuperviser> aSupervisers)
	{
		// set the value of supervisers
		this.supervisers = aSupervisers;
	}
	
	public void setADRESSE_ELEVE(String aADRESSE_ELEVE)
	{
		// set the value of ADRESSE_ELEVE
		this.ADRESSE_ELEVE = aADRESSE_ELEVE;
	}
	
	public void setCODE_POSTAL_ELEVE(String aCODE_POSTAL_ELEVE)
	{
		// set the value of CODE_POSTAL_ELEVE
		this.CODE_POSTAL_ELEVE = aCODE_POSTAL_ELEVE;
	}
	
	public void setPROFESSION_ELEVE(String aPROFESSION_ELEVE)
	{
		// set the value of PROFESSION_ELEVE
		this.PROFESSION_ELEVE = aPROFESSION_ELEVE;
	}
	
	public void setMAIL_ELEVE(String aMAIL_ELEVE)
	{
		// set the value of MAIL_ELEVE
		this.MAIL_ELEVE = aMAIL_ELEVE;
	}
	
	public void setDATE_EVAL_ELEVE(java.sql.Date aDATE_EVAL_ELEVE)
	{
		// set the value of DATE_EVAL_ELEVE
		this.DATE_EVAL_ELEVE = aDATE_EVAL_ELEVE;
	}
	
	public void setVOLUME_HORAIRE_PRATIQUE_ELEVE(int aVOLUME_HORAIRE_PRATIQUE_ELEVE)
	{
		// set the value of VOLUME_HORAIRE_PRATIQUE_ELEVE
		this.VOLUME_HORAIRE_PRATIQUE_ELEVE = aVOLUME_HORAIRE_PRATIQUE_ELEVE;
	}
	
	public void setPHOTO_ELEVE(String aPHOTO_ELEVE)
	{
		// set the value of PHOTO_ELEVE
		this.PHOTO_ELEVE = aPHOTO_ELEVE;
	}
	
	public void setMoniteur(KMoniteur aMoniteur)
	{
		// set the value of moniteur
		this.moniteur = aMoniteur;
	}
	
	public void setNOM_ELEVE(String aNOM_ELEVE)
	{
		// set the value of NOM_ELEVE
		this.NOM_ELEVE = aNOM_ELEVE;
	}
	
	public void setFormation(KFormation aFormation)
	{
		// set the value of formation
		this.formation = aFormation;
	}
	
	public void setTEST_VU_ELEVE(int aTEST_VU_ELEVE)
	{
		// set the value of TEST_VU_ELEVE
		this.TEST_VU_ELEVE = aTEST_VU_ELEVE;
	}
	
	public void setOBSERVATION_VUE_ELEVE(String aOBSERVATION_VUE_ELEVE)
	{
		// set the value of OBSERVATION_VUE_ELEVE
		this.OBSERVATION_VUE_ELEVE = aOBSERVATION_VUE_ELEVE;
	}
	
	public void setDATE_ENREGISTREMENT_ELEVE(java.sql.Date aDATE_ENREGISTREMENT_ELEVE)
	{
		// set the value of DATE_ENREGISTREMENT_ELEVE
		this.DATE_ENREGISTREMENT_ELEVE = aDATE_ENREGISTREMENT_ELEVE;
	}
	
	public void setPRENOM_ELEVE(String aPRENOM_ELEVE)
	{
		// set the value of PRENOM_ELEVE
		this.PRENOM_ELEVE = aPRENOM_ELEVE;
	}
	
	public void setIdMONITEUR(int aIdMONITEUR)
	{
		// set the value of idMONITEUR
		this.idMONITEUR = aIdMONITEUR;
	}
	
	public void setReponses(KListObject<KReponse> aReponses)
	{
		// set the value of reponses
		this.reponses = aReponses;
	}
	
	public void setAssurer_lecons(KListObject<KAssurer_lecon> aAssurer_lecons)
	{
		// set the value of assurer_lecons
		this.assurer_lecons = aAssurer_lecons;
	}
	
	public void setTELEPHONE_ELEVE(String aTELEPHONE_ELEVE)
	{
		// set the value of TELEPHONE_ELEVE
		this.TELEPHONE_ELEVE = aTELEPHONE_ELEVE;
	}
	
	public void setRESULTAT_ELEVE_ORAL(int aRESULTAT_ELEVE_ORAL)
	{
		// set the value of RESULTAT_ELEVE_ORAL
		this.RESULTAT_ELEVE_ORAL = aRESULTAT_ELEVE_ORAL;
	}
	
	public void setDATE_INSCRIPTION_ELEVE(java.sql.Date aDATE_INSCRIPTION_ELEVE)
	{
		// set the value of DATE_INSCRIPTION_ELEVE
		this.DATE_INSCRIPTION_ELEVE = aDATE_INSCRIPTION_ELEVE;
	}
	
	public void setCOMMUNE_ELEVE(String aCOMMUNE_ELEVE)
	{
		// set the value of COMMUNE_ELEVE
		this.COMMUNE_ELEVE = aCOMMUNE_ELEVE;
	}
	
	public void setIdFORMATION(int aIdFORMATION)
	{
		// set the value of idFORMATION
		this.idFORMATION = aIdFORMATION;
	}
	
	public void setVOLUME_HORAIRE_TH_ELEVE(int aVOLUME_HORAIRE_TH_ELEVE)
	{
		// set the value of VOLUME_HORAIRE_TH_ELEVE
		this.VOLUME_HORAIRE_TH_ELEVE = aVOLUME_HORAIRE_TH_ELEVE;
	}
	
	public void setLIVRET_NEPH_ELEVE(String aLIVRET_NEPH_ELEVE)
	{
		// set the value of LIVRET_NEPH_ELEVE
		this.LIVRET_NEPH_ELEVE = aLIVRET_NEPH_ELEVE;
	}
	
	public void setDATE_DE_NAISS_ELEVE(java.sql.Date aDATE_DE_NAISS_ELEVE)
	{
		// set the value of DATE_DE_NAISS_ELEVE
		this.DATE_DE_NAISS_ELEVE = aDATE_DE_NAISS_ELEVE;
	}
	
	@Override
	public String toString()
	{
		return " [passers] = " + passers + " [realisers] = " + realisers + " [supervisers] = " + supervisers
				+ " [ADRESSE_ELEVE] = " + ADRESSE_ELEVE + " [CODE_POSTAL_ELEVE] = " + CODE_POSTAL_ELEVE
				+ " [PROFESSION_ELEVE] = " + PROFESSION_ELEVE + " [MAIL_ELEVE] = " + MAIL_ELEVE
				+ " [DATE_EVAL_ELEVE] = " + DATE_EVAL_ELEVE + " [VOLUME_HORAIRE_PRATIQUE_ELEVE] = "
				+ VOLUME_HORAIRE_PRATIQUE_ELEVE + " [PHOTO_ELEVE] = " + PHOTO_ELEVE + " [moniteur] = " + moniteur
				+ " [NOM_ELEVE] = " + NOM_ELEVE + " [formation] = " + formation + " [TEST_VU_ELEVE] = " + TEST_VU_ELEVE
				+ " [OBSERVATION_VUE_ELEVE] = " + OBSERVATION_VUE_ELEVE + " [DATE_ENREGISTREMENT_ELEVE] = "
				+ DATE_ENREGISTREMENT_ELEVE + " [PRENOM_ELEVE] = " + PRENOM_ELEVE + " [idMONITEUR] = " + idMONITEUR
				+ " [reponses] = " + reponses + " [assurer_lecons] = " + assurer_lecons + " [TELEPHONE_ELEVE] = "
				+ TELEPHONE_ELEVE + " [RESULTAT_ELEVE_ORAL] = " + RESULTAT_ELEVE_ORAL + " [DATE_INSCRIPTION_ELEVE] = "
				+ DATE_INSCRIPTION_ELEVE + " [COMMUNE_ELEVE] = " + COMMUNE_ELEVE + " [idFORMATION] = " + idFORMATION
				+ " [VOLUME_HORAIRE_TH_ELEVE] = " + VOLUME_HORAIRE_TH_ELEVE + " [LIVRET_NEPH_ELEVE] = "
				+ LIVRET_NEPH_ELEVE + " [DATE_DE_NAISS_ELEVE] = " + DATE_DE_NAISS_ELEVE;
	}
}