
package vue.etape;

import java.awt.Dimension;
import vue.*;
import vue.JTableAssurerLecon.CellEditorAera;
import vue.JTableAssurerLecon.CellRenderAera;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import controleur.EcouteurPrincipale;
import KClass.*;
import modele.DataEtape;
import net.ko.kobject.KListObject;

@SuppressWarnings("serial")
public class Objectifs extends JPanel
{
	// -------------------------------------//
	// --------------ATTRIBUTES-------------//
	// -------------------------------------//
	
	private Integer					numEtape;
	private Integer					numEleve;
	private DataEtape				donneesEtape;
	
	private JLabel					titre;
	private static String			CONTENU_TITRE					= "Etape";
	private static Dimension		TAILLE_TITRE					= new Dimension(800, 30);
	
	private KListObject<KObjectif>	liste_objectifs;
	private KListObject<KRealiser>	liste_observationsEtEtats;
	
	private Object[][]				donneesBrutes;
	private static String[]			TITRES_TAB_DONNEES				= { "Objectifs", "Etats", "Observations" };
	
	private JTable					donneesFormatees;
	private TableModel				modele;
	private static Integer			LARGEUR_DONNEES_FORMATEES_1_3	= 300;
	private static Integer			LARGEUR_DONNEES_FORMATEES_2		= 1;
	private static Integer			HAUTEUR_LIGNE					= 50;
	private static String[]			ETATS_OBJ						= { "", "Aborde", "Traite", "Assimile" };
	
	private JScrollPane				scroll_tab;
	private static Dimension		TAILLE_SCROLL					= new Dimension(850, 519);
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public Objectifs(int numEtape,DataEtape donnees_etape)
	{
		this.numEtape = numEtape;
		this.donneesEtape = donnees_etape;
		
		buildTitre(numEtape);
		
		initDonneesEtape(numEtape, donnees_etape);
		
		buildTabDonneesBrutes();
		donneesBrutesVersDonnesFormatees();
		buildDonneesFormatees();
		
		buildScrollBar();
		
	}
	
	// ----------------------------------------- //
	// -----------------LISTENER---------------- //
	// ----------------------------------------- //
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	// --------TITRE-------- //
	
	private void buildTitre(int numEtape)
	{
		titre = new JLabel(CONTENU_TITRE + " " + numEtape);
		titre.setPreferredSize(TAILLE_TITRE);
		this.add(titre);
	}
	
	// --------LISTE_OBJ-------- //
	
	private void initDonneesEtape(int numEtape,DataEtape donnees_etape)
	{
		donnees_etape = new DataEtape(this.numEtape);
		liste_objectifs = donnees_etape.getObjectifs();
	}
	
	// --------DONNEES_BRUTES--------//
	
	private void buildTabDonneesBrutes()
	{
		donneesBrutes = new Object[liste_objectifs.count()][3];
		
		for (int i = 0; i < donneesBrutes.length; i++)
		{
			donneesBrutes[i][0] = liste_objectifs.get(i).getLIBELLE_OBJECTIF();
			donneesBrutes[i][1] = "";
			donneesBrutes[i][2] = "";
		}
	}
	
	private void donneesBrutesVersDonnesFormatees()
	{
		modele = new TableModel(donneesBrutes, TITRES_TAB_DONNEES);
		donneesFormatees = new JTable(modele);
	}
	
	// -------DONNEES_FORMATEES-------//
	
	private void buildDonneesFormatees()
	{
		initColStatus();
		format_col();
		format_ligne();
	}
	
	private void initColStatus()
	{
		JComboBox statut_obj = new JComboBox(ETATS_OBJ);
		donneesFormatees.getColumn("Etats").setCellEditor(new DefaultCellEditor(statut_obj));
	}
	
	// --Colonnes--//
	private void format_col()
	{
		format_col_obj(new TableColumn());
		format_col_etats(new TableColumn());
		format_col_observations(new TableColumn());
		
		donneesFormatees.getTableHeader().setReorderingAllowed(false);
		donneesFormatees.getTableHeader().setResizingAllowed(false);
	}
	
	private void format_col_obj(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(0);
		col.setPreferredWidth(LARGEUR_DONNEES_FORMATEES_1_3);
		col.setCellRenderer(new CellRenderAera());
		// col.setCellEditor(new CellEditorAera());
	}
	
	private void format_col_etats(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(1);
		col.setPreferredWidth(LARGEUR_DONNEES_FORMATEES_2);
	}
	
	private void format_col_observations(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(2);
		col.setPreferredWidth(LARGEUR_DONNEES_FORMATEES_1_3);
		col.setCellRenderer(new CellRenderAera());
		col.setCellEditor(new CellEditorAera());
	}
	
	// --Lignes--//
	private void format_ligne()
	{
		for (int i = 0; i < donneesFormatees.getRowCount(); i++)
			donneesFormatees.setRowHeight(i, HAUTEUR_LIGNE);
	}
	
	// --------SCROLLBAR--------//
	
	private void buildScrollBar()
	{
		this.scroll_tab = new JScrollPane(donneesFormatees);
		this.scroll_tab.setPreferredSize(TAILLE_SCROLL);
		this.add(scroll_tab);
	}
	
	// --------ACCESS_BDD-------- //
	public void chargerEtatsEtObervations()
	{
		this.numEleve = Integer.parseInt(EcouteurPrincipale.Eleve.getId().toString());
		donneesEtape.setObsEtEtats(donneesEtape.chargerObsEtEtat(this.numEleve));
		
		for (int i = 0; i < liste_objectifs.count(); i++)
		{
			try
			{
				donneesFormatees.setValueAt(transcrireIndexVersEtat(donneesEtape.getObsEtEtats(), i), i, 1);
				donneesFormatees.setValueAt(donneesEtape.getObsEtEtats().get(i).getOBSERVATION_OBJECTIF(), i, 2);
			}
			catch (IndexOutOfBoundsException e)
			{
				donneesFormatees.setValueAt("", i, 2);
			}
		}		
	}
	
	private String transcrireIndexVersEtat(KListObject<KRealiser> tmp,int index)
	{
		if (tmp.get(index).getETAT_OBJECTIF() == KRealiser.ABORDE) return ETATS_OBJ[1];
		else if (tmp.get(index).getETAT_OBJECTIF() == KRealiser.TRAITE) return ETATS_OBJ[2];
		else if (tmp.get(index).getETAT_OBJECTIF() == KRealiser.ASSIMILE) return ETATS_OBJ[3];
		
		return null;
	}
		
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public Object[][] getDonneesBrutes()
	{
		return donneesBrutes;
	}
	
	public JTable getDonneesFormatees()
	{
		return donneesFormatees;
	}
	
	public JLabel getTitre()
	{
		return titre;
	}
	
	public KListObject<KObjectif> getListe_objectifs()
	{
		return liste_objectifs;
	}
	
	public JScrollPane getScroll_tab()
	{
		return scroll_tab;
	}
	
	public Integer getNumEleve()
	{
		return numEleve;
	}
	
	public KListObject<KRealiser> getListe_observationsEtEtats()
	{
		return liste_observationsEtEtats;
	}
	
	public TableModel getModele()
	{
		return modele;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	public void setDonneesBrutes(Object[][] tab_donnees_brut)
	{
		this.donneesBrutes = tab_donnees_brut;
	}
	
	public void setDonneesFormatees(JTable tab_donnees_formatees)
	{
		this.donneesFormatees = tab_donnees_formatees;
	}
	
	public void setTitre(JLabel titre)
	{
		this.titre = titre;
	}
	
	public void setListe_objectifs(KListObject<KObjectif> liste_objectifs)
	{
		this.liste_objectifs = liste_objectifs;
	}
	
	public void setScroll_tab(JScrollPane scroll_tab)
	{
		this.scroll_tab = scroll_tab;
	}
	
	public void setNumEleve(Integer numEleve)
	{
		this.numEleve = numEleve;
	}
	
	public void setListe_observationsEtEtats(KListObject<KRealiser> liste_observationsEtEtats)
	{
		this.liste_observationsEtEtats = liste_observationsEtEtats;
	}
	
	public void setModele(TableModel modele)
	{
		this.modele = modele;
	}
}
