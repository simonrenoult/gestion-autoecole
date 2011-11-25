
package vue.JTableAssurerLecon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import modele.DataMoniteur;

public class EcouteurJtableAssurerLecon implements ActionListener
{
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private JTableAssurerLecon	pJtableAssurer;
	@SuppressWarnings("unused")
	private DataMoniteur		moniteur;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	EcouteurJtableAssurerLecon(JTableAssurerLecon p)
	{
		this.pJtableAssurer = p;
		this.moniteur = new DataMoniteur();
	}
	
	// ----------------------------------------- //
	// ----------------LISTENERS---------------- //
	// ----------------------------------------- //
	
	// --------ACTION-------- //
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == pJtableAssurer.getAjouter())
		{
			SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateActuelle = new java.util.Date();
			java.sql.Date date = new java.sql.Date(dateActuelle.getYear() , dateActuelle.getMonth() ,
					dateActuelle.getDate());
			formatdate.format(date);
			Object [] donnee = new Object [] { date.toString(), "", "", "-", "", pJtableAssurer.getSupp() };
			((JtableAssurerLeconModel) pJtableAssurer.getTableau().getModel()).addRow(donnee);
			pJtableAssurer.getJModel().fireTableDataChanged();
		}
	}
}
