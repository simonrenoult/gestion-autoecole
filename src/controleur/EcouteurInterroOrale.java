package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import KClass.KCategorie_i_o;
import KClass.KCategorie_i_o;
import KClass.KObjectif;

import net.ko.kobject.KListObject;
import net.ko.kobject.KObject;

import modele.DataInterroOrale;

import vue.InteroOrale;

public class EcouteurInterroOrale implements ActionListener {

	private InteroOrale fiche;
	private DataInterroOrale data;

	public EcouteurInterroOrale(InteroOrale io) {
		this.fiche = io;
		this.data = new DataInterroOrale();

		// On recupere les categories.
		KListObject<KCategorie_i_o> categories = data.getCategories();

		// On donne les bonnes tailles aux tableaux.
		fiche.setTailleCategorie(categories.count() - 1);

		// On ajoute les categories.
		for (int i = 0; i < categories.count() - 1; i++) {

			// On récupère les questions de la catégorie
			KListObject<KObjectif> objectifs = data
					.getObjectifsForCategorie(i + 1);

			// On initialise la taille du tableau de questions
			fiche.setTailleQuestion(i, objectifs.count());

			// On initialise les questions
			for (int j = 0; j < objectifs.count(); j++) {
				fiche.setQuestion(i, j, objectifs.get(j).getLIBELLE_OBJECTIF());
			}

			fiche.setCategorie(i, categories.get(i).getLIBELLE_CATEGORIE());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
