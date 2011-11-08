package modele;

import java.sql.SQLException;

import KClass.*;
import net.ko.creator.KernelCreator;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;

public class RDVMoniteurEleve {

	private KDBMysql db = bdd.connexion();
	private KAssurer_lecon assurerlecon = new KAssurer_lecon();
	private KListObject<KAssurer_lecon> ListeAssurerLecon = new KListObject<KAssurer_lecon>(
			KAssurer_lecon.class);

	public RDVMoniteurEleve() {
	}

	public KListObject<KAssurer_lecon> recupererListeEleve(int idEleve) {
		System.out.println("id eleve : " + idEleve);
		KListObject<KAssurer_lecon> Kliste = new KListObject<KAssurer_lecon>(
				KAssurer_lecon.class);
		Kliste.loadFromDb(db);

		System.out.println(Kliste);

		for (int i = 0; i < Kliste.count(); i++) {
			if ((Integer)Kliste.get(i).getId() != idEleve) {
				Kliste.delete(i);
				i--;
			}
		}

		System.out.println(Kliste);

		return Kliste;

	}

	public KAssurer_lecon getAssurerlecon() {
		return assurerlecon;
	}

	public void setAssurerlecon(KAssurer_lecon assurerlecon) {
		this.assurerlecon = assurerlecon;
	}

	public KListObject<KAssurer_lecon> getListeAssurerLecon() {
		return ListeAssurerLecon;
	}

	public void setListeAssurerLecon(
			KListObject<KAssurer_lecon> listeAssurerLecon) {
		ListeAssurerLecon = listeAssurerLecon;
	}

}
