package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import net.sourceforge.helpgui.gui.MainFrame;
import vue.BarMenu;
import vue.FenetreApropos;
import vue.FenetreMoniteur;

public class EcouteurMenu implements ActionListener,KeyListener{

	private BarMenu jmenu;
	private FenetreMoniteur fenetreM = null;
	public EcouteurMenu(BarMenu j){
		jmenu = j;
		
		jmenu.getImporterLivret().addActionListener(this);
		jmenu.getExporterLivret().addActionListener(this);
		jmenu.getExporterLivrets().addActionListener(this);
		jmenu.getImprimerLivret().addActionListener(this);
		jmenu.getQuitter().addActionListener(this);
		jmenu.getGestionM().addActionListener(this);
		jmenu.getManuel().addActionListener(this);
		jmenu.getaPropos().addActionListener(this);
		
	}
	
	// ----------------------------------------- //
	// ----------------METHODE------------------ //
	// ----------------------------------------- //
	@SuppressWarnings("deprecation")
	private void LancementFenetreGestionMoniteur() {
		JPasswordField pwd = new JPasswordField(10);  
		int action = 0;
		do{
			action = JOptionPane.showConfirmDialog(null, pwd,"Identification : ",JOptionPane.OK_CANCEL_OPTION);
	    	System.out.println("action : "+action);
	    	if ((action == JOptionPane.OK_OPTION)&&(pwd.getText().compareTo("admin") == 0 )){
		    	fenetreM = new FenetreMoniteur();
		    	fenetreM.setVisible(true);
	            action  = 2;
		    }
	    }while((action != 2)  && (action != -1));
		
	}
	
	// ----------------------------------------- //
	// ----------------KEYLISTENER-------------- //
	// ----------------------------------------- //
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmenu.getImporterLivret()){
			System.out.println("Importer un livret");
		}
		
		if(e.getSource() == jmenu.getExporterLivret()){
			/*int indexExport = fenetre.getFicheEleve().getIdEleve()-1;
			Export exp = new Export();
			exp.exportOuImpression(indexExport, fenetre, 0);
			*/
		}
		if(e.getSource() == jmenu.getExporterLivrets()){
			System.out.println("Redirection vers une JFrame listant tous les eleves avec des checkBox. On valide et on exporte la selection");
			/*Export exp = new Export();
			exp.exportOuImpression(0, fenetre, 1);
			*/
		}
		if(e.getSource() == jmenu.getImprimerLivret()){
			System.out.println("Impression d'un livret");
			/*t indexExport = fenetre.getFicheEleve().getIdEleve()-1;
			Export exp = new Export();
			exp.exportOuImpression(indexExport, fenetre, 2);
			*/
		}
		if(e.getSource() == jmenu.getQuitter()){
			int option = new JOptionPane().showConfirmDialog(null, "Voulez-vous quitter l'application ?",
					"Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	
			if(option == JOptionPane.OK_OPTION)
			{
				System.exit(0);
			}
		}
		if(e.getSource() == jmenu.getGestionM()){
			LancementFenetreGestionMoniteur();
		}
		
		if(e.getSource() == jmenu.getaPropos()){
			FenetreApropos f = new FenetreApropos();
			f.setVisible(true);
		}
		
		if(e.getSource() == jmenu.getManuel()){
			
			final MainFrame mainFrame = new MainFrame("/docs/help/","plastic");
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setVisible(true);
			
			//Action on close the window
			mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent evt) {
					mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				}
			});
		}
		
	}

}
