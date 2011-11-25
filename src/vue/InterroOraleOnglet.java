package vue;

import java.awt.*;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import modele.etape.ObjJTableModele;

import KClass.KCategorie_i_o;
import KClass.KObjectif;

import vue.JTableAssurerLecon.CellEditorAera;
import vue.JTableAssurerLecon.CellRenderAera;

public class InterroOraleOnglet extends JPanel
{

	// ----------------------------------------- //
	// --------------- ATTRIBUTS --------------- //
	// ----------------------------------------- //
	
	private static final long	serialVersionUID	= 1L;

	private Object[][]			data1;
	private String[]			comboData			= { "", "Vrai", "Faux" };
	private JTable				tableau;

	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public InterroOraleOnglet(KCategorie_i_o cat)
	{

		data1 = new Object[cat.getObjectifs().count()][2];

		ajouterQuestions(cat);

		String title[] = { "Question : ", "Reponse :" };

		JComboBox combo = new JComboBox(comboData);

		ObjJTableModele zModel = new ObjJTableModele(data1, title);

		tableau = new JTable(zModel);
		tableau.getColumn("Reponse :").setCellEditor(new DefaultCellEditor(combo));

		donnerStyleTableau();

		JScrollPane scrollTableau = new JScrollPane(tableau);
		scrollTableau.setPreferredSize(new Dimension(850, 430));
		add(scrollTableau);
	}

	// ----------------------------------------- //
	// ---------------- METHODES --------------- //
	// ----------------------------------------- //
	
	private void ajouterQuestions(KCategorie_i_o cat)
	{
		for (int i = 0 ; i < cat.getObjectifs().count() ; i++)
		{
			KObjectif o = cat.getObjectifs().get(i);
			data1[i][0] = o.getLIBELLE_OBJECTIF();
		}
	}

	public void ajouterReponses(int[] rep)
	{
		for (int i = 0 ; i < data1.length ; i++)
		{
			tableau.setValueAt(comboData[rep[i]], i, 1);
		}
	}

	private void donnerStyleTableau()
	{
		TableColumn col;
		col = tableau.getColumnModel().getColumn(0);
		col.setPreferredWidth(600);
		col.setCellEditor(new CellEditorAera());
		col.setCellRenderer(new CellRenderAera());
		col = tableau.getColumnModel().getColumn(1);
		col.setPreferredWidth(1);
		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.getTableHeader().setResizingAllowed(false);

		for (int i = 0 ; i < tableau.getRowCount() ; i++)
		{
			tableau.setRowHeight(i, 45);
		}
	}

	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //
	
	public int[] getReponses()
	{
		int reponses[] = new int[data1.length];

		for (int i = 0 ; i < reponses.length ; i++)
		{
			for (int j = 0 ; j < 3 ; j++)
				if (comboData[j].equals(data1[i][1]))
					reponses[i] = j;
		}

		return reponses;
	}
}