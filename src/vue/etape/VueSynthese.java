
package vue.etape;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import KClass.KQuestions_synthese;
import KClass.KSynthese;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import modele.BDD;
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
		this.connexion = BDD.db;
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
		listeSyntheses = donneesSyntheses.getSyntheses();
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
	
	public Integer getNumEtape()
	{
		return numEtape;
	}

	public Integer getNumEleve()
	{
		return numEleve;
	}

	public DataSynthese getDonneesSyntheses()
	{
		return donneesSyntheses;
	}

	public JLabel getTitre()
	{
		return titre;
	}

	public KListObject<KSynthese> getListeSyntheses()
	{
		return listeSyntheses;
	}

	public Object [] getListeThemesSyntheses()
	{
		return listeThemesSyntheses;
	}

	public KListObject<KQuestions_synthese> getListeQtsSyntheses()
	{
		return listeQtsSyntheses;
	}

	public JPanel getColonneG()
	{
		return colonneG;
	}

	public JPanel getColonneD()
	{
		return colonneD;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //

	public void setNumEtape(Integer numEtape)
	{
		this.numEtape = numEtape;
	}

	public void setNumEleve(Integer numEleve)
	{
		this.numEleve = numEleve;
	}

	public void setDonneesSyntheses(DataSynthese donneesSyntheses)
	{
		this.donneesSyntheses = donneesSyntheses;
	}

	public void setTitre(JLabel titre)
	{
		this.titre = titre;
	}

	public void setListeSyntheses(KListObject<KSynthese> listeSyntheses)
	{
		this.listeSyntheses = listeSyntheses;
	}

	public void setListeThemesSyntheses(Object [] listeThemesSyntheses)
	{
		this.listeThemesSyntheses = listeThemesSyntheses;
	}

	public void setListeQtsSyntheses(KListObject<KQuestions_synthese> listeQtsSyntheses)
	{
		this.listeQtsSyntheses = listeQtsSyntheses;
	}

	public void setColonneG(JPanel colonneG)
	{
		this.colonneG = colonneG;
	}

	public void setColonneD(JPanel colonneD)
	{
		this.colonneD = colonneD;
	}
	
}
