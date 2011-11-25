package modele.etape;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import vue.JTableAssurerLecon.CellRenderAera;

@SuppressWarnings("serial")
public class SynJTable extends JTable
{
	// ----------------------------------------- //
	// ------------- CONSTRUCTEURS ------------- //
	// ----------------------------------------- //
	
	public SynJTable(AbstractTableModel atm)
	{
		super(atm);
	}

	// ----------------------------------------- //
	// -------------- ACCESSEURS --------------- //
	// ----------------------------------------- //
	
	public TableCellRenderer getCellRenderer(int row, int column)
	{
		Object value = getValueAt(row, column);

		if (value != null)
			return super.getCellRenderer(row, column);

		return new CellRenderAera();
	}
}
