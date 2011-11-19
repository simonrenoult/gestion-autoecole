
package vue.etape;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import KClass.KQuestions_synthese;
import KClass.KSynthese;
import net.ko.kobject.KListObject;
import modele.DataEtape;

@SuppressWarnings("serial")
public class Synthese extends JPanel
{
	// -------------------------------------//
	// --------------ATTRIBUTES-------------//
	// -------------------------------------//
	
	private JLabel								titre;
	private static String						contenuTitre			= "Fiche d'evaluation de la synthese ";
	private static Dimension					TAILLE_TITRE			= new Dimension(800 , 30);
	
	private KListObject<KSynthese>				listeSyntheses;
	private Object []							listeThemesSyntheses;
	private KListObject<KQuestions_synthese>	listeQuestionsSyntheses;
	
	private static Dimension					TAILLE_CONTENU_COL_G	= new Dimension(270 , 240);
	private static Dimension					TAILLE_CONTENU_COL_D	= new Dimension(270 , 240);
	
	// -------------------------------------//
	// -------------CONSTRUCTOR-------------//dp
	// -------------------------------------//
	
	public Synthese(Integer numEtape, DataEtape donnees_etape)
	{
		buildTitre(numEtape);
	}
	
	// -------------------------------------//
	// ----------------METHODES-------------//
	// -------------------------------------//
	
	// ----------TITRE---------//
	private void buildTitre(Integer numEtape)
	{
		titre = new JLabel(contenuTitre + numEtape);
		titre.setPreferredSize(TAILLE_TITRE);
		this.add(titre);
	}
	
	// ------CONTENU-GAUCHE------//
	@SuppressWarnings("unused")
	private Integer initSynthese(int numEtape, DataEtape donnees_etape)
	{
		// Contient la synthese 1 et 2
		listeSyntheses = donnees_etape.chargerSynthese(numEtape);
		buildListeThemesSyntheses(listeSyntheses);
		listeThemesSyntheses[0] = donnees_etape.chargerThemeSynthese((Integer) listeSyntheses.get(0).getId());
		
		return null;
	}
	
	private void buildListeThemesSyntheses(KListObject<KSynthese> kliste)
	{
		listeThemesSyntheses = new Object [kliste.count()];
	}
	
	// -------------------------------------//
	// ----------------GETTER---------------//
	// -------------------------------------//
	
	// -------------------------------------//
	// ----------------SETTER---------------//
	// -------------------------------------//
	
}
