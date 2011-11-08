package vue.JTableAssurerLecon;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer{

	public Component getTableCellRendererComponent(	JTable table, Object value,
													boolean isSelected, boolean isFocus,
													int row, int col) {
		//On écrit dans le bouton avec la valeur de la cellule
		setText((value != null) ? value.toString() : "");
		//on retourne notre bouton
		return this;
	}
}