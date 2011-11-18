package vue.JTableAssurerLecon;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class JtableAssurerLeconModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[][] data;
	private String[] title;
	private int ligneRef;
	/**
	 * Constructeur
	 * @param data
	 * @param title
	 */
	public JtableAssurerLeconModel(Object[][] data, String[] title){
		this.data = data;
		this.title = title;
		
		
	}
	/**
	* Retourne le titre de la colonne � l'indice sp�cif�
	*/
	public String getColumnName(int col) {
	  return this.title[col];
	}

	/**
	 * Retourne le nombre de colonnes
	 */
	public int getColumnCount() {
		
			return this.title.length;
	}
	
	/**
	 * Retourne le nombre de lignes
	 */
	public int getRowCount() {
		try{
			return this.data.length;
		}
		catch (NullPointerException e){}
		return 0;
	}
	
	/**
	 * Retourne la valeur � l'emplacement sp�cifi�
	 */
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}
	
	/**
	 * D�fini la valeur � l'emplacement sp�cifi�
	 */
	public void setValueAt(Object value, int row, int col) {
		//On interdit la modification sur certaine colonne !
		if(!this.getColumnName(col).equals("Lecon") && !this.getColumnName(col).equals("Suppression"))
			this.data[row][col] = value;
	}
			
	/**
	* Retourne la classe de la donn�e de la colonne
	* @param col
	*/
	@SuppressWarnings("static-access")
	public Class getColumnClass(int col){
		//On retourne le type de la cellule � la colonne demand�e
		//On se moque de la ligne puisque les donn�es sur chaque ligne sont les m�mes
		//On choisit donc la premi�re ligne
		
		//System.out.println(col+data[0][col].getClass().toString());
		try{
			return this.data[0][col].getClass();
		}
		catch(NullPointerException e){
			new JOptionPane().showMessageDialog(null, "Veuillez selectionner un �l�ve",
					"Erreur", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		
	}

	/**
	 * M�thode permettant de retirer une ligne du tableau
	 * @param position
	 */
	public void removeRow(int position){
		
		int indice = 0, indice2 = 0, nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
		Object temp[][] = new Object[nbRow][nbCol];
		
		for(Object[] value : this.data){
			if(indice != position){
				temp[indice2++] = value;
			}
			System.out.println("Indice = " + indice);
			indice++;
		}
		this.data = temp;
		temp = null;
		//Cette m�thode permet d'avertir le tableau que les donn�es ont �t� modifi�es
		//Ce qui permet une mise � jours compl�te du tableau
		this.fireTableDataChanged();
	}
	
	/**
	 * Permet d'ajouter une ligne dans le tableau
	 * @param data
	 */
	public void addRow(Object[] data){
		int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();
		System.out.println("ligne : "+this.getRowCount());
		System.out.println("colonne : "+this.getColumnCount());
		Object temp[][] = this.data;
		this.data = new Object[nbRow+1][nbCol];
		
		if (temp != null){
			for(Object[] value : temp)
				this.data[indice++] = value;
			
			this.data[indice] = data;
		}
		
		temp = null;
		//Cette m�thode permet d'avertir le tableau que les donn�es ont �t� modifi�es
		//Ce qui permet une mise � jours compl�te du tableau
		this.fireTableDataChanged();
	}
	
	
	@SuppressWarnings("deprecation")
	public boolean isCellEditable(int row, int col){
		
		
		
		String[] dateComparee = this.data[row][0].toString().split("-");
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
		
		
		
		return false;
	}
	
	
	/**
	 * @return the data
	 */
	public Object[][] getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}
	/**
	 * @return the title
	 */
	public String[] getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String[] title) {
		this.title = title;
	}
	/**
	 * @return the ligneRef
	 */
	public int getLigneRef() {
		return ligneRef;
	}
	/**
	 * @param ligneRef the ligneRef to set
	 */
	public void setLigneRef(int ligneRef) {
		this.ligneRef = ligneRef;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}