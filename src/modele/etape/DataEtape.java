
package modele.etape;

import modele.BDD;
import net.ko.ksql.KDBMysql;

public class DataEtape
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	@SuppressWarnings("unused")
	private KDBMysql		db	= BDD.db;
	
	private Integer			numEtape;
	
	private DataObjectifs	donneesObjectifs;
	private DataSynthese	donneesSynthese;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public DataEtape(Integer numeroEtape)
	{
		this.numEtape = numeroEtape;
		donneesObjectifs = new DataObjectifs(numEtape);
		donneesSynthese = new DataSynthese(numEtape);
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