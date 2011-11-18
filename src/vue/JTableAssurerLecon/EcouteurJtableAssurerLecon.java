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
	private DataMoniteur moniteur = new DataMoniteur();
	EcouteurJtableAssurerLecon(JTableAssurerLecon p){
		this.pJtableAssurer = p;
		
	}

/////////////////////////////////////////////////////////////////ACTIONLISTENER
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == pJtableAssurer.getAjouter()){
				SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateActuelle = new java.util.Date();
				java.sql.Date date = new java.sql.Date(dateActuelle.getYear(),dateActuelle.getMonth(),dateActuelle.getDate());
				formatdate.format(date);
				Object[] donnee = new Object[]{date.toString(),"","", "-","", pJtableAssurer.getSupp()};
				((JtableAssurerLeconModel)pJtableAssurer.getTableau().getModel()).addRow(donnee);
				 pJtableAssurer.getJModel().fireTableDataChanged();
		}
		
	}
	

}
