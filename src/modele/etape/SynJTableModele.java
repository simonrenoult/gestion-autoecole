package modele.etape;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class SynJTableModele extends AbstractTableModel
{

	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //

	private Object[][]	data;
	private String[]	title;
	private Boolean[]	estUnTheme;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	public SynJTableModele(Object[][] data, String[] title, Boolean[] estUnTheme)
	{
		this.data = data;
		this.title = title;
		this.estUnTheme = estUnTheme;
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
		if (col == 1 || col == 2)
		{
			if (estUnTheme[row])
			{
				return null;
			}
		}

		return data[row][col];
	}

	public Class<?> getColumnClass(int columnIndex)
	{
		if (columnIndex == 1 || columnIndex == 2)
			return Boolean.class;
		else
			return String.class;
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
		{
			if (estUnTheme[row])
				return false;
			else
				return true;
		}
		return false;
	}
}
