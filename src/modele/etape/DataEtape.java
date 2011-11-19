
package modele.etape;

import modele.bdd;
import net.ko.ksql.KDBMysql;

public class DataEtape
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private KDBMysql		db	= bdd.connexion();
	
	private Integer numEtape;
	
	private DataObjectifs	donneesObjectifs;
	private DataSynthese	donneesSynthese;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataEtape(Integer numeroEtape)
	{
		this.numEtape = numeroEtape;
		donneesObjectifs = new DataObjectifs(this.db , numEtape);
		donneesSynthese = new DataSynthese(this.db , numEtape);
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public DataObjectifs getDonneesObjectifs()
	{
		return donneesObjectifs;
	}
	
	public DataSynthese getDonneesSynthese()
	{
		return donneesSynthese;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public void setDonneesObjectifs(DataObjectifs donneesObjectifs)
	{
		this.donneesObjectifs = donneesObjectifs;
	}
	
	public void setDonneesSynthese(DataSynthese donneesSynthese)
	{
		this.donneesSynthese = donneesSynthese;
	}
	
}