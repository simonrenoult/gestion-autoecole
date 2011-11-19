
package vue.etape;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modele.etape.DataEtape;

@SuppressWarnings("serial")
public class VueEtape extends JPanel
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private static Dimension	TAILLE_ETAPE	= new Dimension(900, 670);
	private static Dimension	TAILLE_ONGLET	= new Dimension(880, 610);
	
	private Integer				numEtape;
	
	private JTabbedPane			onglets;
	private DataEtape			donnees_etape;
	
	private VueObjectifs			objectifs;
	private static String		TITRE_OBJECTIFS	= "Objectifs";
	
	private VueSynthese			syntheses;
	private static String		TITRE_SYNTHESES	= "Synteses";
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public VueEtape(int numEtape)
	{
		this.numEtape = numEtape + 1;
		this.setPreferredSize(TAILLE_ETAPE);
		this.donnees_etape = new DataEtape(numEtape);
		
		this.onglets = new JTabbedPane();
		
		buildObjectifs();
		buildSyntheses();
		
		this.add(onglets);
	}
	
	// ----------------------------------------- //
	// ----------------BUILDERS----------------- //
	// ----------------------------------------- //
	
	private void buildObjectifs()
	{
		objectifs = new VueObjectifs(this.numEtape, donnees_etape.getDonneesObjectifs());
		objectifs.setPreferredSize(TAILLE_ONGLET);
		onglets.add(objectifs, TITRE_OBJECTIFS);
	}
	
	private void buildSyntheses()
	{
		syntheses = new VueSynthese(this.numEtape, donnees_etape);
		syntheses.setPreferredSize(TAILLE_ONGLET);
		onglets.add(syntheses, TITRE_SYNTHESES);
	}
	
	// ----------------------------------------- //
	// --------------INITIALISEURS-------------- //
	// ----------------------------------------- //
	
	// ----------------------------------------- //
	// ----------------METHODES----------------- //
	// ----------------------------------------- //
	
	/*
	public static void main(String[] args)
	{
		new Etape(1);
	}
	*/

	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	public DataEtape getDonnees_etape()
	{
		return donnees_etape;
	}
	
	public VueObjectifs getObjectifs()
	{
		return objectifs;
	}
	
	public VueSynthese getSyntheses()
	{
		return syntheses;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public void setDonnees_etape(DataEtape donnees_etape)
	{
		this.donnees_etape = donnees_etape;
	}
	
	public void setObjectifs(VueObjectifs objectifs)
	{
		this.objectifs = objectifs;
	}
	
	public void setSyntheses(VueSynthese syntheses)
	{
		this.syntheses = syntheses;
	}
}
