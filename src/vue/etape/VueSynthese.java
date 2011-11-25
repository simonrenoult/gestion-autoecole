package vue.etape;

import java.awt.BorderLayout;
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
	private JPanel								panneauH;
	private JPanel								panneauHG;
	private JPanel								panneauHD;
	private JPanel								panneauB;
	private JPanel								panneauBG;
	private JPanel								panneauBM;
	private JPanel								panneauBD;
	private JScrollPane							scroll;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public VueSynthese(Integer numEtape, DataSynthese donnees_syn)
	{
		setPreferredSize(new Dimension(TAILLE_PANNEAUX_X, TAILLE_PANNEAUX_Y));
		this.numEtape = numEtape;
		this.donneesSyntheses = donnees_syn;
		initDonneesSyn();

		buildDonneesBrutes();
		donneesBrutesVersDonnesFormatees();
		buildDonneesFormatees();

		scroll = buildScroll(scroll, donneesFormatees, (TAILLE_PANNEAUX_X / 2) - 50, (TAILLE_PANNEAUX_Y / 2) - 50);

		/* 1 - Initialisation et affectation du GridBagLayout */
		setLayout(new BorderLayout());

		/* 2 - Création des composants */

		buildPanneau(panneauH, new GridLayout(1, 2), TAILLE_PANNEAUX_X, TAILLE_PANNEAUX_Y);
		buildPanneau(panneauHG, TAILLE_PANNEAUX_X / 2, TAILLE_PANNEAUX_Y / 2);
		buildPanneau(panneauHD, TAILLE_PANNEAUX_X / 2, TAILLE_PANNEAUX_Y / 2);

		buildPanneau(panneauB, new GridLayout(1, 3), TAILLE_PANNEAUX_X, TAILLE_PANNEAUX_Y);
		buildPanneau(panneauBG, TAILLE_PANNEAUX_X / 3, TAILLE_PANNEAUX_Y / 3);
		buildPanneau(panneauBM, TAILLE_PANNEAUX_X / 3, TAILLE_PANNEAUX_Y / 3);
		buildPanneau(panneauBD, TAILLE_PANNEAUX_X / 3, TAILLE_PANNEAUX_Y / 3);

		panneauH.add(panneauHG);
		panneauH.add(panneauHD);

		panneauB.add(panneauBG);
		panneauB.add(panneauBM);
		panneauB.add(panneauBD);

		add(panneauH, BorderLayout.CENTER);
		add(panneauB, BorderLayout.SOUTH);

	}

	private void buildPanneau(JPanel panel, Integer tailleX, Integer tailleY)
	{
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(tailleX, tailleY));
	}

	private void buildPanneau(JPanel panel, GridLayout blayout, Integer tailleX, Integer tailleY)
	{
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(tailleX, tailleY));
		panel.setLayout(blayout);
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
		ligneEstUnTheme = new Boolean[donneesBrutes.length];

		for (int i = 0, cptLigne = 0 ; cptLigne < donneesBrutes.length ; i++)
		{
			donneesBrutes[cptLigne][0] = listeThemesSynthese.get(i).getLIBELLE_THEME_SYNTHESE();
			ligneEstUnTheme[cptLigne] = true;
			cptLigne++;

			for (int j = 0 ; j < listeThemesSynthese.get(i).getQuestion_syntheses().count() ; j++)
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

	private JScrollPane buildScroll(JScrollPane jscroll, SynJTable table, int posX, int posY)
	{
		jscroll = new JScrollPane(table);
		jscroll.setPreferredSize(new Dimension(posX, posY));

		return jscroll;
	}

	private void formatLig()
	{
		for (int i = 0 ; i < donneesFormatees.getRowCount() ; i++)
			donneesFormatees.setRowHeight(i, HAUTEUR_LIGNE);
	}

	// --------EVAL_CTL-------- //

	private void buildEvalCtl()
	{
		panneauBG = new JPanel();
		panneauBD = new JPanel();
		this.add(panneauBG, BorderLayout.WEST);
		this.add(panneauBD, BorderLayout.EAST);
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

	public JPanel getColonneG()
	{
		return panneauHG;
	}

	public JPanel getColonneD()
	{
		return panneauBD;
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

	public void setColonneG(JPanel colonneG)
	{
		this.panneauHG = colonneG;
	}

	public void setColonneD(JPanel colonneD)
	{
		this.panneauBD = colonneD;
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
