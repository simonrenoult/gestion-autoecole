package vue.etape;

import java.awt.Dimension;
import vue.JTableAssurerLecon.CellEditorAera;
import vue.JTableAssurerLecon.CellRenderAera;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import controleur.EcouteurPrincipal;
import KClass.*;
import modele.BDD;
import modele.etape.DataObjectifs;
import modele.etape.ObjJTableModele;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

@SuppressWarnings("serial")
public class VueObjectifs extends JPanel
{
	// -------------------------------------//
	// --------------ATTRIBUTES-------------//
	// -------------------------------------//

	private KDBMysql				connexion					= BDD.db;

	private Integer					numEtape;
	private Integer					numEleve;
	private DataObjectifs			donneesObjectifs;

	private KListObject<KObjectif>	listeObjectifs;
	private KListObject<KRealiser>	listeObservationsEtEtats;
	
	private Object[][]				donneesBrutes;
	private static String[]			TITRES_TAB_DONNEES			= { "Objectifs", "Etats", "Observations" };

	private ObjJTableModele			modele;
	private JTable					donneesFormatees;
	private static Integer			LARG_DONNEES_FORMATEES_1_3	= 300;
	private static Integer			LARG_DONNEES_FORMATEES_2	= 1;
	private static Integer			HAUTEUR_LIGNE				= 50;

	private JComboBox<String>		etatsObj;
	private static String[]			CONTENU_ETATS_OBJ			= { "", "Aborde", "Traite", "Assimile" };

	private JScrollPane				scroll_tab;
	private static Dimension		TAILLE_SCROLL				= new Dimension(850, 450);

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public VueObjectifs(int numEtape, DataObjectifs donnees_objectifs)
	{
		this.numEtape = numEtape;
		this.donneesObjectifs = donnees_objectifs;

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
		listeObjectifs = donneesObjectifs.getObjectifs();
	}

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	// --------DONNEES_BRUTES--------//

	private void buildTabDonneesBrutes()
	{
		donneesBrutes = new Object[listeObjectifs.count()][3];
		for (int i = 0 ; i < donneesBrutes.length ; i++)
		{
			donneesBrutes[i][0] = listeObjectifs.get(i).getLIBELLE_OBJECTIF();
			donneesBrutes[i][1] = "";
			donneesBrutes[i][2] = "";
		}
	}

	private void donneesBrutesVersDonnesFormatees()
	{
		modele = new ObjJTableModele(donneesBrutes, TITRES_TAB_DONNEES);
		donneesFormatees = new JTable(modele);
	}

	// -------DONNEES_FORMATEES-------//

	private void buildDonneesFormatees()
	{
		donneesFormatees.getTableHeader().setReorderingAllowed(false);
		donneesFormatees.getTableHeader().setResizingAllowed(false);

		initColStatus();
		formatCol();
		formatLig();
	}

	// ---- > Colonnes

	private void initColStatus()
	{
		etatsObj = new JComboBox<String>(CONTENU_ETATS_OBJ);
		donneesFormatees.getColumn("Etats").setCellEditor(new DefaultCellEditor(etatsObj));
	}

	private void formatCol()
	{
		TableColumn col = new TableColumn();
		formatColObj(col);
		formatColEtats(col);
		formatColObservations(col);
	}

	private void formatColObj(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(0);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_1_3);
		col.setCellRenderer(new CellRenderAera());
	}

	private void formatColEtats(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(1);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_2);
	}

	private void formatColObservations(TableColumn col)
	{
		col = donneesFormatees.getColumnModel().getColumn(2);
		col.setPreferredWidth(LARG_DONNEES_FORMATEES_1_3);
		col.setCellEditor(new CellEditorAera());
	}

	// ---- > Lignes
	private void formatLig()
	{
		for (int i = 0 ; i < donneesFormatees.getRowCount() ; i++)
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
		this.numEleve = Integer.parseInt(EcouteurPrincipal.Eleve.getId().toString());
		donneesObjectifs.setObsEtEtats(donneesObjectifs.chargerObsEtEtat(this.numEleve));

		for (int i = 0 ; i < listeObjectifs.count() ; i++)
		{
			try
			{
				donneesFormatees.setValueAt(transcrireIndexVersEtat(donneesObjectifs.getObsEtEtats(), i), i, 1);
				donneesFormatees.setValueAt(donneesObjectifs.getObsEtEtats().get(i).getOBSERVATION_OBJECTIF(), i, 2);
			}
			catch (IndexOutOfBoundsException e)
			{
				donneesFormatees.setValueAt("", i, 2);
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

	public Object[][] getDonneesBrutes()
	{
		return donneesBrutes;
	}

	public JTable getDonneesFormatees()
	{
		return donneesFormatees;
	}

	public KListObject<KObjectif> getListe_objectifs()
	{
		return listeObjectifs;
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
		return listeObservationsEtEtats;
	}

	public ObjJTableModele getModele()
	{
		return modele;
	}

	public JComboBox<String> getEtatObj()
	{
		return etatsObj;
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

	public void setListe_objectifs(KListObject<KObjectif> liste_objectifs)
	{
		this.listeObjectifs = liste_objectifs;
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
		this.listeObservationsEtEtats = liste_observationsEtEtats;
	}

	public void setModele(ObjJTableModele modele)
	{
		this.modele = modele;
	}

	public void setEtatObj(JComboBox<String> etatObj)
	{
		this.etatsObj = etatObj;
	}

	public KDBMysql getConnexion()
	{
		return connexion;
	}

	public void setConnexion(KDBMysql connexion)
	{
		this.connexion = connexion;
	}
}