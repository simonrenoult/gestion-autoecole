
package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KMoniteur
 */
@SuppressWarnings("serial")
public class KMoniteur extends KObject
{
	private KListObject<KEleve>			eleves;
	private KListObject<KSuperviser>	supervisers;
	private String						NOM_MONITEUR;
	private String						PRENOM_MONITEUR;
	private KListObject<KAssurer_lecon>	assurer_lecons;
	
	public KMoniteur()
	{
		super();
		keyFields = "id";
		tableName = "moniteur";
		hasMany(KSuperviser.class);
		hasMany(KEleve.class);
		hasMany(KAssurer_lecon.class);
		
	}
	
	public KListObject<KEleve> getEleves()
	{
		// return the value of eleves
		return this.eleves;
	}
	
	public KListObject<KSuperviser> getSupervisers()
	{
		// return the value of supervisers
		return this.supervisers;
	}
	
	public String getNOM_MONITEUR()
	{
		// return the value of NOM_MONITEUR
		return this.NOM_MONITEUR;
	}
	
	public String getPRENOM_MONITEUR()
	{
		// return the value of PRENOM_MONITEUR
		return this.PRENOM_MONITEUR;
	}
	
	public KListObject<KAssurer_lecon> getAssurer_lecons()
	{
		// return the value of assurer_lecons
		return this.assurer_lecons;
	}
	
	public void setEleves(KListObject<KEleve> aEleves)
	{
		// set the value of eleves
		this.eleves = aEleves;
	}
	
	public void setSupervisers(KListObject<KSuperviser> aSupervisers)
	{
		// set the value of supervisers
		this.supervisers = aSupervisers;
	}
	
	public void setNOM_MONITEUR(String aNOM_MONITEUR)
	{
		// set the value of NOM_MONITEUR
		this.NOM_MONITEUR = aNOM_MONITEUR;
	}
	
	public void setPRENOM_MONITEUR(String aPRENOM_MONITEUR)
	{
		// set the value of PRENOM_MONITEUR
		this.PRENOM_MONITEUR = aPRENOM_MONITEUR;
	}
	
	public void setAssurer_lecons(KListObject<KAssurer_lecon> aAssurer_lecons)
	{
		// set the value of assurer_lecons
		this.assurer_lecons = aAssurer_lecons;
	}
	
	@Override
	public String toString()
	{
		return " [eleves] = " + eleves + " [supervisers] = " + supervisers + " [NOM_MONITEUR] = " + NOM_MONITEUR
				+ " [PRENOM_MONITEUR] = " + PRENOM_MONITEUR + " [assurer_lecons] = " + assurer_lecons;
	}
}