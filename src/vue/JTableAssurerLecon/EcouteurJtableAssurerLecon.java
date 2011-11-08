package vue.JTableAssurerLecon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import modele.DataMoniteur;
import net.ko.kobject.KListObject;

import KClass.KMoniteur;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class EcouteurJtableAssurerLecon implements ActionListener {

	private JTableAssurerLecon pJtableAssurer;
	
	
	EcouteurJtableAssurerLecon(JTableAssurerLecon p){
		this.pJtableAssurer = p;
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == pJtableAssurer.getAjouter()){
			
			String[] ListeMoniteur ;
			DataMoniteur moniteur = new DataMoniteur();
			KListObject<KMoniteur> KListe = new KListObject<KMoniteur>(
					KMoniteur.class);
			KListe = moniteur.recupererListe();
			ListeMoniteur =new String[KListe.count()];
			for (int i = 0; i < KListe.count(); i++) 
			{
				
				ListeMoniteur[i] =KListe.get(i).getNOM_MONITEUR().toUpperCase()
						+ " " +  KListe.get(i).getPRENOM_MONITEUR().toLowerCase();
			}
				//A utiliser seulement le 26 novembre (pour eviter les conflits)
				/*SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateActuelle = new java.util.Date();
				java.sql.Date date = new java.sql.Date(dateActuelle.getYear(),dateActuelle.getMonth(),dateActuelle.getDay());
				formatdate.format(date);
			     */
				//int num = pJtableAssurer.getJModel().getRowCount()+1;
				Object[] donnee = new Object[]{"2011-11-27","","", "-","", pJtableAssurer.getSupp()};
				//Object[] donnee = new Object[]{pJtableAssurer.getJModel().getRowCount()+1, "","","", pJtableAssurer.getJModel().getData()[0][4],"", pJtableAssurer.getSupp()};
				
				((JtableAssurerLeconModel)pJtableAssurer.getTableau().getModel()).addRow(donnee);
				 pJtableAssurer.getJModel().fireTableDataChanged();
			
			
		}
		
	}
	

}
