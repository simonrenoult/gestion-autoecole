package vue.JTableAssurerLecon;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import modele.TableauLecon;



public class DeleteButtonEditor extends DefaultCellEditor {
	  
	protected JButton button;
	private DeleteButtonListener bListener = new DeleteButtonListener();

/////////////////////////////////////////////////////////////////////METHODES
	/**
	 * Constructeur avec une checkBox
	 * @param checkBox
	 * @param count
	 */
	public DeleteButtonEditor(JCheckBox checkBox) {
		//Par défaut, ce type d'objet travaille avec un JCheckBox
		super(checkBox);
	    //On crée à nouveau notre bouton
		button = new JButton();
	    button.setOpaque(true);
	    //On lui attribue un listener
	    button.addActionListener(bListener);
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
	                   boolean isSelected, int row, int column) { 
		//On définit le numéro de lignes à notre listener
		bListener.setRow(row);
		//On passe aussi le tableau pour des actions potentielles
		bListener.setTable(table);
		//On réaffecte le libellé au bouton
		button.setText( (value ==null) ? "" : value.toString() );
		//On renvoie le bouton
	    return button;
	}
	
////////////////////////////////////////////////////////////////////////////ACTIONLISTENER
	/**
	 * Notre listener pour le bouton
	 * @author CHerby
	 *
	 */
	class DeleteButtonListener implements ActionListener{
		  
		  private int row;
		  private JTable table;
		  
		  public int getRow(){return this.row;}
		  public void setRow(int row){this.row = row;}
		  public void setTable(JTable table){this.table = table;}
		  
		  @SuppressWarnings("deprecation")
		public boolean verifierDroitSuppressionDate(){
			  boolean index = false;
			  
			/*  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			  SimpleDateFormat heureFormat = new SimpleDateFormat("HH:mm");
			  java.util.Date dateActuelle = new  java.util.Date();
			  java.util.Date dateComparee = new  java.util.Date();
			  java.util.Date  heureComparee = new Date();
			  
			  // On gere les 4 cas possibles pour les dates et heures selon les formats mis en place.
			  
			  try {
				dateComparee = dateFormat.parse(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)));
			  } 
			  catch (ParseException e) {
				dateComparee.setYear(3000);
			}
			  catch(ArrayIndexOutOfBoundsException e){
				  dateComparee.setYear(3000);
			  }
			 // System.out.println("dateActuelle : "+dateActuelle.toString());
			 // System.out.println("dateComparee : "+dateComparee.toString());
			   try {
				 heureComparee = heureFormat.parse(String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 1)));
			   }
			   catch (ParseException e) {
				heureComparee.setHours(dateActuelle.getHours()+1);
				
			}
			  catch(ArrayIndexOutOfBoundsException e){
				  heureComparee.setHours(dateActuelle.getHours()+1);
			  }
			 
			  //System.out.println("heureComparee : "+heureComparee.toString());
			  dateComparee.setHours(heureComparee.getHours());
			  dateComparee.setMinutes(heureComparee.getMinutes());
			  dateComparee.setSeconds(heureComparee.getSeconds());
			  
			  //System.out.println("dateActuelle : "+dateActuelle.toString());
			  //System.out.println("dateComparee : "+dateComparee.toString());
			  if(dateComparee.compareTo(dateActuelle) <= 0){
				  index = false;
				  //System.out.println("impossibilité de supprimer");
			  }
			  */
			  
			  
			  
			  	String[] dateComparee =String.valueOf(table.getModel().getValueAt(table.getSelectedRow(), 0)).split("-");
			  	java.util.Date dateActuelle = new java.util.Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String[] DateACTUELLE = format.format(dateActuelle).split("-");;
				
				System.out.println("date comparee : "+Integer.parseInt(dateComparee[0])+Integer.parseInt(dateComparee[1])+Integer.parseInt(dateComparee[2]));
				System.out.println("dateactuelle : "+Integer.parseInt(DateACTUELLE[0])+Integer.parseInt(DateACTUELLE[1])+Integer.parseInt(DateACTUELLE[2]));
				
				if (Integer.parseInt(dateComparee[0])>=Integer.parseInt(DateACTUELLE[0])){
					if (Integer.parseInt(dateComparee[1])>=Integer.parseInt(DateACTUELLE[1])){
						if(Integer.parseInt(dateComparee[2])>=Integer.parseInt(DateACTUELLE[2])){
							return true;
						}
					}
				}
			  
			  
			  
			  return index;
		  }
		  
		  @SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent event) {
			  if(table.getRowCount() > 0){
				 System.out.println("ligne : "+table.getSelectedRow());
				
				 
				 int option = new JOptionPane().showConfirmDialog(null, "Voulez-vous supprimer ce RDV (opération irréversible) ?",
							"Suppression de RDV", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    
					if(option == JOptionPane.OK_OPTION)
					{
						if(verifierDroitSuppressionDate()){
							((JtableAssurerLeconModel)table.getModel()).removeRow(this.row);
						}
						else{
							
							new JOptionPane().showConfirmDialog(null, "Par raison de sécurité, vous ne pouvez pas supprimer un RDV\n" +
									" inséré à une date antérieur de celle de l'oridinateur.\nVeuillez contacter l'administrateur système",
									"Suppression de RDV", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						    
						}
					
					} 
				
				
			}
		  }
	  }	 	  
	}