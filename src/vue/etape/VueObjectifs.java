
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
import modele.BDD;
import modele.etape.DataObjectifs;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

@SuppressWarnings("serial")
public class VueObjectifs extends JPanel
{
	// -------------------------------------//
	// --------------ATTRIBUTES-------------//
	// -------------------------------------//
	
	private KDBMysql				connexion = BDD.db;
	
	private Integer					numEtape;
	private Integer					numEleve;
	private DataObjectifs			donneesObjectifs;
	
	private JLabel					titre;
	private static String			CONTENU_TITRE				= "Etape";
	private static Dimension		TAILLE_TITRE				= new Dimension(800 , 30);
	
	private KListObject<KObjectif>	liste_objectifs;
	private KListObject<KRealiser>	liste_observationsEtEtats;
	
	private Object [][]				donneesBrutes;
	private static String []		TITRES_TAB_DONNEES			= { "Objectifs", "Etats", "Observations" };
	
	private JTable					donneesFormatees;
	private TableModel				modele;
	private static Integer			LARG_DONNEES_FORMATEES_1_3	= 300;
	private static Integer			LARG_DONNEES_FORMATEES_2	= 1;
	private static Integer			HAUTEUR_LIGNE				= 50;
	
	private JComboBox				etatsObj;
	private static String []		CONTENU_ETATS_OBJ			= { "", "Aborde", "Traite", "Assimile" };
	
	private JScrollPane				scroll_tab;
	private static Dimension		TAILLE_SCROLL				= new Dimension(850 , 519);
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public VueObjectifs(int numEtape, DataObjectifs donnees_objectifs)
	{
		this.numEtape = numEtape;
		this.donneesObjectifs = donnees_objectifs;
		
		buildTitre();
		
		initDonneesObj();
		
		buildTabDonneesBrutes();
		donneesBrutesVersDonnesFormatees();
		buildDonneesFormatees();
		
		buildScrollBar();
	}
	
	// ----------------------------------------- //
	// --------------INITIALISEURS-------------- //
	// ----------------------------------------- //
	
	// --------LISTE_OBJ-------- //
	
	private void initDonneesObj()
	{
		donneesObjectifs = new DataObjectifs(connexion , numEtape);
		liste_objectifs = donneesObjectifs.getObjectifs();
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	// --------TITRE-------- //
	
	private void buildTitre()
	{
		this.titre = new JLabel(CONTENU_TITRE + " " + numEtape);
		this.titre.setPreferredSize(TAILLE_TITRE);
		this.add(titre);
	}
	
	// --------DONNEES_BRUTES--------//
	
	private void buildTabDonneesBrutes()
	{
		donneesBrutes = new Object [liste_objectifs.count()] [3];
		
		for (int i = 0; i < donneesBrutes.length; i++)
		{
			donneesBrutes[i][0] = liste_objectifs.get(i).getLIBELLE_OBJECTIF();
			donneesBrutes[i][1] = "";
			donneesBrutes[i][2] = "";
		}
	}
	
	private void donneesBrutesVersDonnesFormatees()
	{
		modele = new TableModel(donneesBrutes , TITRES_TAB_DONNEES);
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
		etatsObj = new JComboBox(CONTENU_ETATS_OBJ);
		donneesFormatees.getColumn("Etats").setCellEditor(new DefaultCellEditor(etatsObj));
	}
	
	// ----Colonnes---- //
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
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_1_3);
		col.setCellRenderer(new CellRenderAera());
	}
	
	private void format_col_etats(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(1);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_2);
	}
	
	private void format_col_observations(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(2);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_1_3);
		col.setCellRenderer(new CellRenderAera());
		col.setCellEditor(new CellEditorAera());
	}
	
	// ----Lignes----//
	private void format_ligne()
	{
		for (int i = 0; i < donneesFormatees.getRowCount(); i++)
			donneesFormatees.setRowHeight(i , HAUTEUR_LIGNE);
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
		donneesObjectifs.setObsEtEtats(donneesObjectifs.chargerObsEtEtat(this.numEleve));
		
		for (int i = 0; i < liste_objectifs.count(); i++)
		{
			try
			{
				donneesFormatees.setValueAt(transcrireIndexVersEtat(donneesObjectifs.getObsEtEtats() , i) , i , 1);
				donneesFormatees.setValueAt(donneesObjectifs.getObsEtEtats().get(i).getOBSERVATION_OBJECTIF() , i , 2);
			}
			catch (IndexOutOfBoundsException e)
			{
				donneesFormatees.setValueAt("" , i , 2);
			}
		}
	}
	
	private String transcrireIndexVersEtat(KListObject<KRealiser> tmp, int index)
	{
		if (tmp.get(index).getETAT_OBJECTIF() == KRealiser.ABORDE)
			return (String) etatsObj.getItemAt(1);
		else if (tmp.get(index).getETAT_OBJECTIF() == KRealiser.TRAITE)
			return (String) etatsObj.getItemAt(2);
		else if (tmp.get(index).getETAT_OBJECTIF() == KRealiser.ASSIMILE)
			return (String) etatsObj.getItemAt(3);
		
		return null;
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public Object [][] getDonneesBrutes()
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
	
	public void setDonneesBrutes(Object [][] tab_donnees_brut)
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

	public JComboBox getEtatObj()
	{
		return etatsObj;
	}

	public void setEtatObj(JComboBox etatObj)
	{
		this.etatsObj = etatObj;
	}
}
