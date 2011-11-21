
package vue.etape;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import KClass.KQuestions_synthese;
import KClass.KSynthese;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import modele.bdd;
import modele.etape.DataEtape;
import modele.etape.DataSynthese;

@SuppressWarnings("serial")
public class VueSynthese extends JPanel
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private KDBMysql							connexion;
	
	private Integer								numEtape;
	private Integer								numEleve;
	private DataSynthese						donneesSyntheses;
	
	private JLabel								titre;
	private static String						CONTENU_TITRE			= "Fiche d'evaluation de la synthese ";
	private static Dimension					TAILLE_TITRE			= new Dimension(800 , 30);
	
	private KListObject<KSynthese>				listeSyntheses;
	private Object []							listeThemesSyntheses;
	private KListObject<KQuestions_synthese>	listeQtsSyntheses;
	
	private static Dimension					TAILLE_CONTENU_COL_G	= new Dimension(270 , 240);
	private static Dimension					TAILLE_CONTENU_COL_D	= new Dimension(270 , 240);
	
	private JPanel								colonneG;
	private JPanel								colonneD;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public VueSynthese(Integer numEtape, DataSynthese donnees_syn)
	{
		this.connexion = bdd.db;
		this.numEtape = numEtape;
		this.donneesSyntheses = donnees_syn;
		
		buildTitre(numEtape);
		
		initDonneesSyn();
		
		buildQtsSyntheses();
		buildEvalCtl();
	}
	
	// ----------------------------------------- //
	// --------------INITIALISEURS-------------- //
	// ----------------------------------------- //
	
	private void initDonneesSyn()
	{
		donneesSyntheses = new DataSynthese(connexion , numEtape);
		listeSyntheses = donneesSyntheses.getSyntheses();
		listeQtsSyntheses = donneesSyntheses.getQtsSynthese();
		listeQtsSyntheses = donneesSyntheses.getQtsSynthese();
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	// ---------TITRE--------//
	private void buildTitre(Integer numEtape)
	{
		titre = new JLabel(CONTENU_TITRE + numEtape);
		titre.setPreferredSize(TAILLE_TITRE);
		this.add(titre);
	}
	
	// --------Q_SYNTHESES-------- //
	private void buildQtsSyntheses()
	{
		colonneG = new JPanel(new FlowLayout());
		
	}
	
	// --------EVAL_CTL-------- //
	private void buildEvalCtl()
	{
		
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
}
