package vue.etape;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import vue.JTableAssurerLecon.CellEditorAera;
import vue.JTableAssurerLecon.CellRenderAera;

import KClass.KEvaluation_synthese;
import KClass.KTheme_synthese;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import modele.BDD;
import modele.etape.DataSynthese;
import modele.etape.ModeleTableObjectifs;
import modele.etape.ModeleTableSyntheses;

@SuppressWarnings("serial")
public class VueSynthese extends JPanel
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //

	@SuppressWarnings("unused")
	private KDBMysql							connexion		= BDD.db;

	private Integer								numEtape;
	private Integer								numEleve;
	private DataSynthese						donneesSyntheses;

	private KListObject<KTheme_synthese>		listeThemesSynthese;
	private KListObject<KEvaluation_synthese>	listeEvaluationsSynthese;

	private JLabel								titre;
	private static String						CONTENU_TITRE	= "Fiche d'evaluation de la synthese ";
	private static Dimension					TAILLE_TITRE	= new Dimension(800, 30);

	private Object[][]							donneesBrutes;
	private ModeleTableSyntheses				modele;
	private JTable								donneesFormatees;
	private String[]							titresColonnes	= { "Questions : ", "Etat 1", "Etat 2" };

	private JScrollPane							jsp;

	private JCheckBox							etatsSyn;
	private JPanel								colonneG;
	private JPanel								colonneD;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public VueSynthese(Integer numEtape, DataSynthese donnees_syn)
	{
		this.numEtape = numEtape;
		this.donneesSyntheses = donnees_syn;

		buildTitre(numEtape);

		initDonneesSyn();

		buildDonneesBrutes();
		donneesBrutesVersDonnesFormatees();
		buildDonneesFormatees();
		buildScrollPane();
	}

	// ----------------------------------------- //
	// --------------INITIALISEURS-------------- //
	// ----------------------------------------- //

	private void initDonneesSyn()
	{
		listeThemesSynthese = donneesSyntheses.getThemesSynthese();
		listeEvaluationsSynthese = donneesSyntheses.getEvaluationSynthese();
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

	// --------DONNEES_BRUTES-------- //

	private void buildDonneesBrutes()
	{
		Integer size = tailleTabDonneesBrutes();
		donneesBrutes = new Object[size][3];

		buildColThemesQts();
		buildColCheckBox();
	}

	private Integer tailleTabDonneesBrutes()
	{
		// On compte les themes
		Integer tmp = listeThemesSynthese.count();

		for (int i = 0 ; i < tmp ; i++)
		{
			try
			{
				// On compte les questions associees aux themes
				tmp += listeThemesSynthese.get(i).getQuestion_syntheses().count();
			}
			catch (IndexOutOfBoundsException e)
			{
			}
		}

		return tmp;
	}

	private void buildColThemesQts()
	{
		for (int i = 0, ligne = 0 ; ligne < donneesBrutes.length ; i++, ligne++)
		{
			donneesBrutes[ligne][0] = listeThemesSynthese.get(i).getLIBELLE_THEME_SYNTHESE();

			for (int j = 0 ; j < listeThemesSynthese.get(i).getQuestion_syntheses().count() ; j++)
			{
				ligne++;
				donneesBrutes[ligne][0] = listeThemesSynthese.get(i).getQuestion_syntheses().get(j)
						.getLIBELLE_QUESTION_SYNTHESE();
			}
		}
	}

	private void buildColCheckBox()
	{
		for (int i = 0 ; i < donneesBrutes.length ; i++)
		{
			donneesBrutes[i][1] = new Boolean(false);
			donneesBrutes[i][2] = new Boolean(false);
		}
	}

	private void donneesBrutesVersDonnesFormatees()
	{
		modele = new ModeleTableSyntheses(donneesBrutes, titresColonnes);
		donneesFormatees = new JTable(modele);
	}

	// --------DONNEES_FORMATEES-------- //

	private void buildDonneesFormatees()
	{
		etatsSyn = new JCheckBox();
		donneesFormatees.getColumn("Etat 1").setCellEditor(new DefaultCellEditor(etatsSyn));
		donneesFormatees.getColumn("Etat 2").setCellEditor(new DefaultCellEditor(etatsSyn));
	}

	private void buildScrollPane()
	{
		jsp = new JScrollPane(donneesFormatees);
		add(jsp);
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

	public void setColonneG(JPanel colonneG)
	{
		this.colonneG = colonneG;
	}

	public void setColonneD(JPanel colonneD)
	{
		this.colonneD = colonneD;
	}

}
