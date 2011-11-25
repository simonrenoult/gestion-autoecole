package vue.etape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modele.etape.DataEtape;

@SuppressWarnings("serial")
public class VueEtape extends JPanel
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //

	private Integer				numEtape;
	private DataEtape			donneesEtape;

	private JTabbedPane			onglets;

	private JLabel				titre;
	private static String		CONTENU_TITRE	= "Etape ";
	private static Dimension	TAILLE_TITRE	= new Dimension(800, 75);
	private static Font			POLICE_TITRE	= new Font("Arial", Font.BOLD, 24);

	private VueObjectifs		objectifs;
	private static String		TITRE_OBJECTIFS	= "Objectifs";

	private VueSynthese			syntheses;
	private static String		TITRE_SYNTHESES	= "Syntheses";

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public VueEtape(int numEtape)
	{
		this.numEtape = numEtape;
		this.donneesEtape = new DataEtape(numEtape);
		this.onglets = new JTabbedPane();

		setLayout(new BorderLayout());

		ajouterTitre();

		buildObjectifs();
		buildSyntheses();

		ajouterOnglets();
	}

	// ----------------------------------------- //
	// ----------------BUILDERS----------------- //
	// ----------------------------------------- //

	private void ajouterTitre()
	{
		titre = new JLabel(CONTENU_TITRE + numEtape);
		titre.setPreferredSize(TAILLE_TITRE);
		titre.setFont(POLICE_TITRE);

		this.add(titre, BorderLayout.NORTH);
	}

	private void buildObjectifs()
	{
		objectifs = new VueObjectifs(this.numEtape, donneesEtape.getDonneesObjectifs());
	}

	private void buildSyntheses()
	{
		syntheses = new VueSynthese(this.numEtape, donneesEtape.getDonneesSynthese());
	}

	private void ajouterOnglets()
	{
		onglets.add(objectifs, TITRE_OBJECTIFS);
		onglets.add(syntheses, TITRE_SYNTHESES);

		this.add(onglets, BorderLayout.CENTER);
	}

	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //

	public DataEtape getDonnees_etape()
	{
		return donneesEtape;
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
		this.donneesEtape = donnees_etape;
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
