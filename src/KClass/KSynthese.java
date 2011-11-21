
package KClass;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

/**
 * Classe KSynthese
 */
@SuppressWarnings("serial")
public class KSynthese extends KObject
{
	private KListObject<KPasser>			passers;
	private KEtape							etape;
	private int								idETAPE;
	private String							LIBELLE_SYNTHESE;
	private KListObject<KThemes_synthese>	themes_syntheses;
	
	public KSynthese()
	{
		super();
		keyFields = "id";
		tableName = "synthese";
		// hasMany(KThemes_synthese.class);hasMany(KPasser.class);belongsTo(KEtape.class);
		
	}
	
	public KListObject<KPasser> getPassers()
	{
		// return the value of passers
		return this.passers;
	}
	
	public KEtape getEtape()
	{
		// return the value of etape
		return this.etape;
	}
	
	public int getIdETAPE()
	{
		// return the value of idETAPE
		return this.idETAPE;
	}
	
	public String getLIBELLE_SYNTHESE()
	{
		// return the value of LIBELLE_SYNTHESE
		return this.LIBELLE_SYNTHESE;
	}
	
	public KListObject<KThemes_synthese> getThemes_syntheses()
	{
		// return the value of themes_syntheses
		return this.themes_syntheses;
	}
	
	public void setPassers(KListObject<KPasser> aPassers)
	{
		// set the value of passers
		this.passers = aPassers;
	}
	
	public void setEtape(KEtape aEtape)
	{
		// set the value of etape
		this.etape = aEtape;
	}
	
	public void setIdETAPE(int aIdETAPE)
	{
		// set the value of idETAPE
		this.idETAPE = aIdETAPE;
	}
	
	public void setLIBELLE_SYNTHESE(String aLIBELLE_SYNTHESE)
	{
		// set the value of LIBELLE_SYNTHESE
		this.LIBELLE_SYNTHESE = aLIBELLE_SYNTHESE;
	}
	
	public void setThemes_syntheses(KListObject<KThemes_synthese> aThemes_syntheses)
	{
		// set the value of themes_syntheses
		this.themes_syntheses = aThemes_syntheses;
	}
	
	@Override
	public String toString()
	{
		return " [passers] = " + passers + " [etape] = " + etape + " [idETAPE] = " + idETAPE + " [LIBELLE_SYNTHESE] = "
				+ LIBELLE_SYNTHESE + " [themes_syntheses] = " + themes_syntheses;
	}
}