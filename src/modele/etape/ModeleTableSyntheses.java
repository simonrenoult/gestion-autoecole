package modele.etape;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeleTableSyntheses extends AbstractTableModel
{

	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private Object [][]	data;
	private String []	title;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public ModeleTableSyntheses(Object [][] data, String [] title)
	{
		this.data = data;
		this.title = title;
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	/**
	 * Retourne le titre de la colonne e l'indice specife
	 */
	public String getColumnName(int col)
	{
		return this.title[col];
	}
	
	/**
	 * Retourne le nombre de colonnes
	 */
	public int getColumnCount()
	{
		return this.title.length;
	}
	
	/**
	 * Retourne le nombre de lignes
	 */
	public int getRowCount()
	{
		return this.data.length;
	}
	
	/**
	 * Retourne la valeur e l'emplacement specifie
	 */
	public Object getValueAt(int row, int col)
	{
		return this.data[row][col];
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	/**
	 * Defini la valeur a l'emplacement specifie
	 */
	public void setValueAt(Object value, int row, int col)
	{
		this.data[row][col] = value;
	}
	
	/**
	 * Retourne la classe de la donnee de la colonne
	 * 
	 * @param col
	 */
	public boolean isCellEditable(int row, int col)
	{
		if (col > 0)
			return true;
		return false;
	}
}
