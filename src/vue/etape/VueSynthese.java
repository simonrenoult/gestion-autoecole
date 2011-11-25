package vue.etape;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import KClass.KEvaluation_synthese;
import KClass.KTheme_synthese;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import modele.BDD;
import modele.etape.DataSynthese;
import modele.etape.SynJTableModele;
import modele.etape.SynJTable;

@SuppressWarnings("serial")
public class VueSynthese extends JPanel
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //

	private KDBMysql							connexion					= BDD.db;

	private Integer								numEtape;
	private Integer								numEleve;
	private DataSynthese						donneesSyntheses;

	private KListObject<KTheme_synthese>		listeThemesSynthese;
	private KListObject<KEvaluation_synthese>	listeEvaluationsSynthese;

	private Object[][]							donneesBrutes;
	private Boolean[]							ligneEstUnTheme;
	private SynJTableModele						modele;
	private SynJTable							donneesFormatees;
	private static final String[]				TITRES_COLONNES				= { "Questions", "R1", "R2" };
	private static final int					LARG_DONNEES_FORMATEES_1	= 300;
	private static final int					LARG_DONNEES_FORMATEES_2_3	= 10;
	private static final int					HAUTEUR_LIGNE				= 18;

	private static final int					TAILLE_PANNEAUX_X			= 850;
	private static final int					TAILLE_PANNEAUX_Y			= 430;

	private JCheckBox							etatsSyn;
	private JScrollPane							scroll;

	private JPanel								conteneurH;
	private JPanel[]							panneauxH;

	private JPanel								conteneurB;
	private JPanel[]							panneauxB;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public VueSynthese(Integer numEtape, DataSynthese donnees_syn)
	{
		setPreferredSize(new Dimension(TAILLE_PANNEAUX_X, TAILLE_PANNEAUX_Y));
		setLayout(new BorderLayout());
		this.numEtape = numEtape;
		this.donneesSyntheses = donnees_syn;
		initDonneesSyn();

		buildDonneesBrutes();
		donneesBrutesVersDonnesFormatees();
		buildDonneesFormatees();

		buildScroll();
				
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

	// --------DONNEES_BRUTES-------- //

	private void buildDonneesBrutes()
	{
		Integer size = tailleTabDonneesBrutes();
		donneesBrutes = new Object[size][3];

		buildColThemesQts();
	}

	private Integer tailleTabDonneesBrutes()
	{
		// On compte les themes
		Integer tmp = listeThemesSynthese.count();

		for (int i = 0; i < tmp; i++)
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
		ligneEstUnTheme = new Boolean[donneesBrutes.length];

		for (int i = 0, cptLigne = 0; cptLigne < donneesBrutes.length; i++)
		{
			donneesBrutes[cptLigne][0] = listeThemesSynthese.get(i).getLIBELLE_THEME_SYNTHESE();
			ligneEstUnTheme[cptLigne] = true;
			cptLigne++;

			for (int j = 0; j < listeThemesSynthese.get(i).getQuestion_syntheses().count(); j++)
			{
				donneesBrutes[cptLigne][0] = listeThemesSynthese.get(i).getQuestion_syntheses().get(j)
						.getLIBELLE_QUESTION_SYNTHESE();
				ligneEstUnTheme[cptLigne] = false;
				donneesBrutes[cptLigne][1] = new Boolean(false);
				donneesBrutes[cptLigne][2] = new Boolean(false);
				cptLigne++;
			}
		}
	}

	private void donneesBrutesVersDonnesFormatees()
	{
		modele = new SynJTableModele(donneesBrutes, TITRES_COLONNES, ligneEstUnTheme);
		donneesFormatees = new SynJTable(modele);
	}

	// --------DONNEES_FORMATEES-------- //

	private void buildDonneesFormatees()
	{
		donneesFormatees.getTableHeader().setReorderingAllowed(false);
		donneesFormatees.getTableHeader().setResizingAllowed(false);

		TableColumn col = new TableColumn();
		formatColSyn(col);
		formatColEtat(col, 1);
		formatColEtat(col, 2);
		formatLig();
	}

	private void formatColSyn(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(0);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_1);
	}

	private void formatColEtat(TableColumn col, Integer indiceColonne)
	{
		col = donneesFormatees.getColumnModel().getColumn(indiceColonne);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_2_3);
	}

	private void buildScroll()
	{
		scroll = new JScrollPane(donneesFormatees);
		scroll.setPreferredSize(new Dimension(TAILLE_PANNEAUX_X / 2 , TAILLE_PANNEAUX_Y / 2));
	}

	private void formatLig()
	{
		for (int i = 0; i < donneesFormatees.getRowCount(); i++)
			donneesFormatees.setRowHeight(i, HAUTEUR_LIGNE);
	}

	// ------- QTS_SYNTHESE ------- //

	private void buildPanneaux()
	{
		conteneurH = new JPanel(new GridLayout(1,2));
		panneauxH = new JPanel[2];
		for (int i = 0; i < panneauxH.length; i++)
		{
			panneauxH[i] = new JPanel();
			conteneurH.add(panneauxH[i]);
		}
		add(conteneurH);
		
		conteneurB = new JPanel(new GridLayout(1,3));
		panneauxB = new JPanel[3];
		for (int i = 0; i < panneauxB.length; i++)
		{
			panneauxB[i] = new JPanel();
			conteneurB.add(panneauxB[i]);
		}
		
		conteneurB = new JPanel(new GridLayout(1, 3));
		panneauxB = new JPanel[3];
		for (int i = 0; i < panneauxB.length; i++)
		{
			panneauxB[i] = new JPanel();
			conteneurB.add(panneauxB[i]);
		}
		add(conteneurB);
	}
	
	// ------- EVAL_CONTROLE ------- //

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

	public JCheckBox getEtatsSyn()
	{
		return etatsSyn;
	}

	public void setEtatsSyn(JCheckBox etatsSyn)
	{
		this.etatsSyn = etatsSyn;
	}

}
