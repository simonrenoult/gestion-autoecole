
package KClass;

import net.ko.kobject.KObject;

/**
 * Classe KAssurer_lecon
 */
@SuppressWarnings("serial")
public class KAssurer_lecon extends KObject
{
	private KEleve		eleve;
	private KAgenda		agenda;
	private int			DUREE_LECON;
	private long		idELEVE;
	private int			idMONITEUR;
	private KMoniteur	moniteur;
	private int			idAGENDA;
	private String		OBSERVATION_LECON;
	
	public KAssurer_lecon()
	{
		super();
		keyFields = "idAGENDA,idELEVE";
		tableName = "assurer_lecon";
		belongsTo(KMoniteur.class);
		belongsTo(KEleve.class);
		belongsTo(KAgenda.class);
		
	}
	
	public KEleve getEleve()
	{
		// return the value of eleve
		return this.eleve;
	}
	
	public KAgenda getAgenda()
	{
		// return the value of agenda
		return this.agenda;
	}
	
	public int getDUREE_LECON()
	{
		// return the value of DUREE_LECON
		return this.DUREE_LECON;
	}
	
	public long getIdELEVE()
	{
		// return the value of idELEVE
		return this.idELEVE;
	}
	
	public int getIdMONITEUR()
	{
		// return the value of idMONITEUR
		return this.idMONITEUR;
	}
	
	public KMoniteur getMoniteur()
	{
		// return the value of moniteur
		return this.moniteur;
	}
	
	public int getIdAGENDA()
	{
		// return the value of idAGENDA
		return this.idAGENDA;
	}
	
	public String getOBSERVATION_LECON()
	{
		// return the value of OBSERVATION_LECON
		return this.OBSERVATION_LECON;
	}
	
	public void setEleve(KEleve aEleve)
	{
		// set the value of eleve
		this.eleve = aEleve;
	}
	
	public void setAgenda(KAgenda aAgenda)
	{
		// set the value of agenda
		this.agenda = aAgenda;
	}
	
	public void setDUREE_LECON(int aDUREE_LECON)
	{
		// set the value of DUREE_LECON
		this.DUREE_LECON = aDUREE_LECON;
	}
	
	public void setIdELEVE(long aIdELEVE)
	{
		// set the value of idELEVE
		this.idELEVE = aIdELEVE;
	}
	
	public void setIdMONITEUR(int aIdMONITEUR)
	{
		// set the value of idMONITEUR
		this.idMONITEUR = aIdMONITEUR;
	}
	
	public void setMoniteur(KMoniteur aMoniteur)
	{
		// set the value of moniteur
		this.moniteur = aMoniteur;
	}
	
	public void setIdAGENDA(int aIdAGENDA)
	{
		// set the value of idAGENDA
		this.idAGENDA = aIdAGENDA;
	}
	
	public void setOBSERVATION_LECON(String aOBSERVATION_LECON)
	{
		// set the value of OBSERVATION_LECON
		this.OBSERVATION_LECON = aOBSERVATION_LECON;
	}
	
	@Override
	public String toString()
	{
		return " [eleve] = " + eleve + " [agenda] = " + agenda + " [DUREE_LECON] = " + DUREE_LECON + " [idELEVE] = "
				+ idELEVE + " [idMONITEUR] = " + idMONITEUR + " [moniteur] = " + moniteur + " [idAGENDA] = " + idAGENDA
				+ " [OBSERVATION_LECON] = " + OBSERVATION_LECON;
	}
}