package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import KClass.KObjectif;

import net.ko.kobject.KListObject;

import vue.Etape;

import modele.DataEtape;

public class EcouteurEtape implements ActionListener, MouseListener{

	// --------------------------------------//
	// --------------ATTRIBUTES--------------//
	// --------------------------------------//
	
	private int numEtape;
	private Etape fenetre;
	private DataEtape de;
	private KListObject<KObjectif> listObj;
	
	// -------------------------------------//
	// -------------CONSTRUCTOR-------------//
	// -------------------------------------//
	
	public EcouteurEtape(int numEtape, Etape f){
		// Initialisation des variables.
		this.numEtape = numEtape;
		this.fenetre = f;
		this.de = new DataEtape();
		listObj = de.chargerObjectif(this.numEtape);
		
		fenetre.creerData1(listObj.count());
		
		// On liste les objectifs dans la vue.
		for(int i=0 ; i<listObj.count() ; i++)
		{
			fenetre.setData1(i,0,listObj.get(i).getLIBELLE_OBJECTIF());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
