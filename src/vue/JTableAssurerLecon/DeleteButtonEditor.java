package vue.JTableAssurerLecon;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;



public class DeleteButtonEditor extends DefaultCellEditor {
	  
	protected JButton button;
	private DeleteButtonListener bListener = new DeleteButtonListener();
	
	/**
	 * Constructeur avec une checkBox
	 * @param checkBox
	 * @param count
	 */
	public DeleteButtonEditor(JCheckBox checkBox) {
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
	class DeleteButtonListener implements ActionListener{
		  
		  private int row;
		  private JTable table;
		  
		  public void setRow(int row){this.row = row;}
		  public void setTable(JTable table){this.table = table;}
		  
		  public void actionPerformed(ActionEvent event) {
			  if(table.getRowCount() > 0){
				 
				  //supprimer seulement si la date est sup�rieur � la current date.
				  
				  int option = new JOptionPane().showConfirmDialog(null, "Voulez-vous supprimer ce RDV (op�ration irr�versible) ?",
							"Suppression de RDV", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
					if(option == JOptionPane.OK_OPTION)
					{
						//On affecte un nouveau libell� � une celulle de la ligne
						((JtableAssurerLeconModel)table.getModel()).removeRow(this.row);
						
					} 
				
				
			}
		  }
	  }	 	  
	}