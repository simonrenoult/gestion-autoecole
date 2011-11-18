package vue.JTableAssurerLecon;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ButtonEditor extends DefaultCellEditor {
	  
	protected JButton button;
	private ButtonListener bListener = new ButtonListener();
	
	/**
	 * Constructeur avec une checkBox
	 * @param checkBox
	 * @param count
	 */
	public ButtonEditor(JCheckBox checkBox) {
		//Par d�faut, ce type d'objet travaille avec un JCheckBox
		super(checkBox);
	    //On cr�e � nouveau notre bouton
		button = new JButton();
	    button.setOpaque(true);
	    //On lui attribue un listener
	    button.addActionListener(bListener);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
	                   boolean isSelected, int row, int column) { 
		//On d�finit le num�ro de lignes � notre listener
		bListener.setRow(row);
		//Idem pour le num�ro de colonnes
		bListener.setColumn(column);
		//On passe aussi le tableau pour des actions potentielles
		bListener.setTable(table);
		//On r�affecte le libell� au bouton
		button.setText( (value ==null) ? "" : value.toString() );
		//On renvoie le bouton
	    return button;
	}
	
	/**
	 * Notre listener pour le bouton
	 * @author CHerby
	 *
	 */
	class ButtonListener implements ActionListener{
	  
	  private int column, row;
	  private JTable table;
	  private int nbre = 0;
	  private JButton button;
	  
	  public void setColumn(int col){this.column = col;}
	  public void setRow(int row){this.row = row;}
	  public void setTable(JTable table){this.table = table;}
	  public JButton getButton(){return this.button;}
	  
	  public void actionPerformed(ActionEvent event) {
		//On affiche un Zoli message mais vous pourriez faire les traitements que vous voulez
		System.out.println("coucou du bouton : " + ((JButton)event.getSource()).getText() );
		//On affecte un nouveau libell� � une celulle de la ligne
		((AbstractTableModel)table.getModel()).setValueAt("New Value " + (++nbre), this.row, (this.column -1));	
		//Permet de dire � notre tableau qu'une valeur a chang� 
		//� l'emplacement d�termin� par les valeur pass�e en param�tre
		((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, this.column - 1);
		this.button = ((JButton)event.getSource());
	  }
	}
}
