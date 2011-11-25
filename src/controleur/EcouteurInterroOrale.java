
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import KClass.KCategorie_i_o;
import KClass.KObjectif;
import net.ko.kobject.KListObject;
import modele.DataInterroOrale;
import vue.InteroOrale;

public class EcouteurInterroOrale implements ActionListener
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	@SuppressWarnings("unused")
	private InteroOrale			fiche;
	private DataInterroOrale	data;
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public EcouteurInterroOrale(InteroOrale io)
	{
		this.fiche = io;
		this.data = new DataInterroOrale();	
	}
	
	// ----------------------------------------- //
	// ----------------LISTENERS---------------- //
	// ----------------------------------------- //
	
	// --------ACTION-------- //
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	public String[] chargerReponsesEleve(int cat)
	{
		KListObject<KObjectif> objectifs = getCategorie(cat).getObjectifs();
		@SuppressWarnings("unused")
		int idObj = 0;
		String reponses[] = new String[objectifs.count()];
		
		for (int i = 0; i < reponses.length; i++)
		{
			idObj = Integer.parseInt(objectifs.get(i).getId().toString());
		}
		
		return reponses;
	}
	
	// ----------------------------------------- //
	// ---------------ACCESSEURS---------------- //
	// ----------------------------------------- //
	
	public KListObject<KCategorie_i_o> getCategories()
	{
		return data.getCategories();
	}
	
	public KCategorie_i_o getCategorie(int i)
	{
		return data.getCategories().get(i);
	}
	
	// ----------------------------------------- //
	// ----------------MUTATEURS---------------- //
	// ----------------------------------------- //
	
	
}
