package vue;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.EcouteurInterroOrale;


public class InteroOrale extends JPanel {

	private JLabel texteTitre = new JLabel();
	
	private String categorie[];
	private String questions[][];
	private JLabel label;
	private JCheckBox cases[][];
	
	public InteroOrale(){

		this.setPreferredSize(new Dimension(900,680));
		
		texteTitre = new JLabel("Interrogation Orale V2 (Exterieur du vehicule)");
		texteTitre.setFont(new Font(null,Font.BOLD,20));
		texteTitre.setPreferredSize(new Dimension(800,50));
		this.add(texteTitre);
		
		/*EcouteurInterroOrale eio = */new EcouteurInterroOrale(this);
		
		JTabbedPane onglet = new JTabbedPane();
		JPanel partie = new JPanel();
		
		// Ajout des onglets.
		for(int i=0 ; i<categorie.length ; i++){
			
			// Les questions
			label = new JLabel("<html>");
			for(int j=0 ; j<questions[i].length ; j++){
				label.setText(label.getText()+"<br/><br/>"+questions[i][j]);
				System.out.println(questions[i][j]);
			}
			label.setText(label.getText()+"</html>");
			label.setPreferredSize(new Dimension(650,400));
			
			// Le panel
			partie = new JPanel();
			partie.setBackground(Color.WHITE);
			partie.setPreferredSize(new Dimension(880, 535));
			texteTitre = new JLabel(categorie[i]);
			texteTitre.setPreferredSize(new Dimension(500, 50));
			texteTitre.setFont(new Font(null,Font.BOLD,20));
			partie.add(texteTitre);
			partie.add(label);
			// On cree l'onglet correspondant.
			onglet.add(partie, categorie[i]);
		}
		
		this.add(onglet);
		
	}
	
	// Les getters&setters
	public void setQuestion(int cat, int i, String question){
		this.questions[cat][i] = question;
	}
	
	public void setCategorie(int i, String nomCat){
		this.categorie[i] = nomCat;
	}
	
	public void setTailleQuestion(int i, int taille){
		this.questions[i] = new String[taille];
		this.cases[i] = new JCheckBox[taille];
	}
	
	public void setTailleCategorie(int taille){
		this.categorie = new String[taille];
		this.cases = new JCheckBox[taille][];
		this.questions = new String[taille][];
	}

}              