package modele;

import java.sql.SQLException;

import KClass.*;
import net.ko.creator.KernelCreator;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class RDVMoniteurEleve {
	
	private KDBMysql db = bdd.connexion();
	private KASSURER_LECON assurerlecon = new KASSURER_LECON();
	private KListObject<KASSURER_LECON> ListeAssurerLecon = new KListObject<KASSURER_LECON>(KASSURER_LECON.class);
	
	public RDVMoniteurEleve() {}

	public KListObject<KASSURER_LECON> recupererListeEleve(int idEleve) {
		System.out.println("id eleve : "+idEleve);
		KListObject<KASSURER_LECON> Kliste = new KListObject<KASSURER_LECON>(KASSURER_LECON.class);
		Kliste.loadFromDb(db);
		
		System.out.println(Kliste);
		
		for (int i=0; i<Kliste.count(); i++){
			if (Kliste.get(i).getIdELEVE() != idEleve){
				Kliste.delete(i);
				i--;
			}
		}
		
		System.out.println(Kliste);

		return Kliste;

	}

	public KASSURER_LECON getAssurerlecon() {
		return assurerlecon;
	}

	public void setAssurerlecon(KASSURER_LECON assurerlecon) {
		this.assurerlecon = assurerlecon;
	}

	public KListObject<KASSURER_LECON> getListeAssurerLecon() {
		return ListeAssurerLecon;
	}

	public void setListeAssurerLecon(KListObject<KASSURER_LECON> listeAssurerLecon) {
		ListeAssurerLecon = listeAssurerLecon;
	}
	
	

}
