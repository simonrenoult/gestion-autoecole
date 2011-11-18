package vue.JTableAssurerLecon;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import modele.DataMoniteur;
import net.ko.kobject.KListObject;
import KClass.KMoniteur;


public class ComboRenderer extends JComboBox implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean isFocus, int row, int col) {
		
		DataMoniteur moniteur = new DataMoniteur();
		KListObject<KMoniteur> KListe = new KListObject<KMoniteur>(
				KMoniteur.class);
		KListe = moniteur.recupererListe();
		String[] ListeMoniteur =new String[KListe.count()];
		for (int i = 0; i < KListe.count(); i++) 
		{
			//correspondanceMoniteur.put(i,KListe.get(i).getID_MONITEUR());
			ListeMoniteur[i] =KListe.get(i).getNOM_MONITEUR().toUpperCase()
					+ " " +  KListe.get(i).getPRENOM_MONITEUR().toLowerCase();
			this.addItem(ListeMoniteur[i]);
		}
		
		return this;
	}	
}