
package vue.JTableAssurerLecon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import modele.TableauLecon;

@SuppressWarnings("serial")
public class JTableAssurerLecon extends JPanel
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private JTable					tableau;
	private JLabel					nombreHeurePratique		= new JLabel("");
	private JLabel					nombreLecon				= new JLabel("");
	private JPanel					information				= new JPanel();
	private String					supp					= "Supprimer le RDV";
	private JComboBox				combo					= new JComboBox();
	private JButton					ajouter;
	private JtableAssurerLeconModel	JModel;
	private Object [][]				data					= null;
	private Hashtable				correspondanceMoniteur	= new Hashtable();
	private TableauLecon			tableauLecon			= null;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public JTableAssurerLecon(TableauLecon tableaulecon)
	{
		this.tableauLecon = tableaulecon;
		creerJtable();
		DimensionerJtable();
		DefinirEditeurCellule();
		DefinirEditeurSupprimer();
		tableau.setDefaultRenderer(JComponent.class , new TableComponent());
		AjoutJscrollJtable();
		CreationBoutonAjouter();
		CreationJpanelInformationLecon();
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	private void CreationJpanelInformationLecon()
	{
		nombreHeurePratique.setPreferredSize(new Dimension(850 , 20));
		nombreLecon.setPreferredSize(new Dimension(850 , 20));
		information.add(nombreHeurePratique);
		information.add(nombreLecon);
		information.setPreferredSize(new Dimension(850 , 50));
		this.add(information , BorderLayout.SOUTH);
		
	}
	
	private void CreationBoutonAjouter()
	{
		ajouter = new JButton("Ajouter un RDV");
		EcouteurJtableAssurerLecon ecouteur = new EcouteurJtableAssurerLecon(this);
		ajouter.addActionListener(ecouteur);
		this.add(ajouter , BorderLayout.CENTER);
		
	}
	
	private void AjoutJscrollJtable()
	{
		// On ajoute le tableau
		JScrollPane scrollTableau = new JScrollPane(tableau);
		scrollTableau.setPreferredSize(new Dimension(850 , 400));
		this.add(scrollTableau , BorderLayout.NORTH);
		this.setBackground(Color.blue);
		
	}
	
	private void DefinirEditeurSupprimer()
	{
		// On définit un éditeur pour la colonne "supprimer"
		this.tableau.getColumn("Suppression").setCellEditor(new DeleteButtonEditor(new JCheckBox()));
	}
	
	private void DefinirEditeurCellule()
	{
		// On définit l'éditeur par défaut pour la cellule
		// en lui spécifiant quel type d'affichage prendre en compte
		tableau.getColumn("Moniteur").setCellEditor(new DefaultCellEditor(combo));
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
		tableau.getColumn("Moniteur").setCellRenderer(dcr);
		
		// tableau.setDefaultRenderer(Color.class,new ColorRenderer(true));
		// tableau.setDefaultEditor(Color.class,new
		// vue.JTableAssurerLecon.ColorEditor());
		
	}
	
	private void DimensionerJtable()
	{
		tableau.setRowHeight(30);
		tableau.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableau.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableau.getColumnModel().getColumn(2).setPreferredWidth(40);
		tableau.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableau.getColumnModel().getColumn(4).setPreferredWidth(380);
		tableau.getColumnModel().getColumn(5).setPreferredWidth(120);
		
	}
	
	private void creerJtable()
	{
		String title[] = { "Date", "Horaire", "Durée", "Moniteur", "Observation", "Suppression" };
		// Nous devons utiliser un modèle d'affichage spécifique afin de pallier
		// aux bugs d'affichage !
		JModel = new JtableAssurerLeconModel(data , title);
		tableau = new JTable(JModel);
		
	}
	
	public void initialiserJlabel()
	{
		nombreHeurePratique.setText("Information : L'élève compte à ce jour " + tableauLecon.compterHeure()
				+ " de leçons pratiques.");
		nombreLecon.setText("Information : L'élève compte à ce jour " + tableauLecon.compterSeance()
				+ " séances sur sa formation.");
		
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	/**
	 * @return the tableau
	 */
	public JTable getTableau()
	{
		return tableau;
	}
	
	/**
	 * @return the supp
	 */
	public String getSupp()
	{
		return supp;
	}
	
	/**
	 * @return the combo
	 */
	public JComboBox getCombo()
	{
		return combo;
	}
	
	/**
	 * @return the ajouter
	 */
	public JButton getAjouter()
	{
		return ajouter;
	}
	
	/**
	 * @return the JModel
	 */
	public JtableAssurerLeconModel getJModel()
	{
		return JModel;
	}
	
	/**
	 * @return the correspondanceMoniteur
	 */
	public Hashtable getCorrespondanceMoniteur()
	{
		return correspondanceMoniteur;
	}
	
	/**
	 * @return the nombreHeurePratique
	 */
	public JLabel getNombreHeurePratique()
	{
		return nombreHeurePratique;
	}
	
	/**
	 * @return the nombreLecon
	 */
	public JLabel getNombreLecon()
	{
		return nombreLecon;
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	/**
	 * @param tableau
	 *            the tableau to set
	 */
	public void setTableau(JTable tableau)
	{
		this.tableau = tableau;
	}
	
	/**
	 * @param supp
	 *            the supp to set
	 */
	public void setSupp(String supp)
	{
		this.supp = supp;
	}
	
	/**
	 * @param JModel
	 *            the JModel to set
	 */
	public void setJModel(JtableAssurerLeconModel JModel)
	{
		this.JModel = JModel;
	}
	
	/**
	 * @param correspondanceMoniteur
	 *            the correspondanceMoniteur to set
	 */
	public void setCorrespondanceMoniteur(Hashtable correspondanceMoniteur)
	{
		this.correspondanceMoniteur = correspondanceMoniteur;
	}
	
	/**
	 * @param nombreHeurePratique
	 *            the nombreHeurePratique to set
	 */
	public void setNombreHeurePratique(JLabel nombreHeurePratique)
	{
		this.nombreHeurePratique = nombreHeurePratique;
	}
	
	/**
	 * @param nombreLecon
	 *            the nombreLecon to set
	 */
	public void setNombreLecon(JLabel nombreLecon)
	{
		this.nombreLecon = nombreLecon;
	}
	
	/**
	 * @param combo
	 *            the combo to set
	 */
	public void setCombo(JComboBox combo)
	{
		this.combo = combo;
	}
	
	/**
	 * @param ajouter
	 *            the ajouter to set
	 */
	public void setAjouter(JButton ajouter)
	{
		this.ajouter = ajouter;
	}
	
}
