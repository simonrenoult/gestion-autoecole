package vue;
import javax.swing.*;


import modele.DataInterroOrale;

import java.awt.*;
import java.util.Vector;

public class InteroOrale extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane panels;
	private Vector<InterroOraleOnglet> onglets = new Vector<InterroOraleOnglet>();
	private DataInterroOrale modele;
	
	public InteroOrale(){
		super();
		
		modele = new DataInterroOrale();
		panels = new JTabbedPane();
		
		setLayout(new BorderLayout());
		
		ajouterTitre();
		
		for(int i=0 ; i<modele.getCategories().count() ; i++){
			onglets.add(new InterroOraleOnglet(modele.getCategorie(i)));
			panels.add(onglets.lastElement(), modele.getCategorie(i).getLIBELLE_CATEGORIE());
		}
		
		add(panels, BorderLayout.CENTER);
		
	}

	private void ajouterTitre() {
		JLabel titre = new JLabel("Interrogation Orale V2 (exterieur du vehicule)");
		titre.setPreferredSize(new Dimension(800,75));
		Font f = new Font("Arial", Font.BOLD, 24);
		titre.setFont(f);
		
		add(titre, BorderLayout.NORTH);
	}
	
	public void recupererReponses(){
		int rep[];
		for(int i=0 ; i<onglets.size()-1 ; i++){
			rep = null;
			rep = onglets.get(i).getReponses();
			for(int j=0 ; j<rep.length ; j++)
				if(rep[j]>0) modele.insertBDD(i, j, rep[j]);
		}
	}

	public void chargerReponsesEleve(){
		for(int i=0 ; i<modele.getCategories().count() ; i++)
			onglets.get(i).ajouterReponses(modele.chargerReponsesEleve(i));
	}
}              