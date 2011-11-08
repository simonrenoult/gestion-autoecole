package test_codes;

import java.util.ArrayList;

import javax.swing.JTable;

import controleur.EcouteurFicheEleve;

import modele.RDVMoniteurEleve;


public class Jtable {

	
	private RDVMoniteurEleve rdv;
	private JTable tableau;
	private ArrayList<String> comboData;
	private ArrayList<String> listeFormation[] = new ArrayList[6];
	//private TableModel2 zModel;
	private String titleTableauJtable[] = { "Lecon", "Date", "Horaire",
			"Duree", "Moniteur", "Observation" };

	/**
	 * @return the rdv
	 */
	public RDVMoniteurEleve getRdv() {
		return rdv;
	}

	/**
	 * @param rdv the rdv to set
	 */
	public void setRdv(RDVMoniteurEleve rdv) {
		this.rdv = rdv;
	}

	/**
	 * @return the tableau
	 */
	public JTable getTableau() {
		return tableau;
	}

	/**
	 * @param tableau the tableau to set
	 */
	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	/**
	 * @return the comboData
	 */
	public ArrayList<String> getComboData() {
		return comboData;
	}

	/**
	 * @param comboData the comboData to set
	 */
	public void setComboData(ArrayList<String> comboData) {
		this.comboData = comboData;
	}

	/**
	 * @return the listeFormation
	 */
	public ArrayList<String>[] getListeFormation() {
		return listeFormation;
	}

	/**
	 * @param listeFormation the listeFormation to set
	 */
	public void setListeFormation(ArrayList<String>[] listeFormation) {
		this.listeFormation = listeFormation;
	}

	
	/**
	 * @return the titleTableauJtable
	 */
	public String[] getTitleTableauJtable() {
		return titleTableauJtable;
	}

	/**
	 * @param titleTableauJtable the titleTableauJtable to set
	 */
	public void setTitleTableauJtable(String[] titleTableauJtable) {
		this.titleTableauJtable = titleTableauJtable;
	}
	
	/*
	public void creationJtable() {
		// tableau de liste.
		// declaration des colonnes.
		for (int i = 0; i < listeFormation.length; i++) {
			listeFormation[i] = new ArrayList<String>();
		}

		ajouterLigneJtable();
		zModel = new TableModel2(listeFormation, titleTableauJtable);

		tableau = new JTable();
		tableau.setModel(zModel);

		definirJtableGraphique();
		JScrollPane scrollTableau = new JScrollPane(tableau);
		scrollTableau.setPreferredSize(new Dimension(800, 400));
		containerTabForma.add(scrollTableau);

	}

	public void definirJtableGraphique() {
		for (int i = 0; i < tableau.getColumnCount(); i++) {

			switch (i) {
			case 0:
				tableau.getColumnModel().getColumn(i).setMaxWidth(50);
				break;
			case 5:
				tableau.getColumnModel().getColumn(i).setMaxWidth(350);
				tableau.getColumnModel().getColumn(i)
						.setCellEditor(new CellEditorAera());
				tableau.getColumnModel().getColumn(i)
						.setCellRenderer(new CellRenderAera());
				break;
			default:
				tableau.getColumnModel().getColumn(i).setMaxWidth(100);
				break;
			}

		}

		for (int i = 0; i < tableau.getRowCount(); i++) {
			tableau.setRowHeight(i, 17);
		}
		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.getTableHeader().setResizingAllowed(false);
		// tableau.setBackground(new Color(2));

	}

	public void ajouterLigneJtable() {
		for (int i = 0; i < listeFormation.length; i++) {

			switch (i) {

			case 0:
				for (int j = 0; j < rdv.getListeAssurerLecon().count(); j++) {
					listeFormation[i].add(""
							+ rdv.getListeAssurerLecon().get(j).getNUM_LECON());
				}
				break;
			case 1:
				for (int j = 0; j < rdv.getListeAssurerLecon().count(); j++) {
					listeFormation[i]
							.add(""
									+ rdv.getListeAssurerLecon().get(j)
											.getDATE_LECON());
				}
				break;
			case 2:
				for (int j = 0; j < rdv.getListeAssurerLecon().count(); j++) {
					listeFormation[i].add(""
							+ rdv.getListeAssurerLecon().get(j)
									.getHEURE_LECON());
				}
				break;
			case 3:
				for (int j = 0; j < rdv.getListeAssurerLecon().count(); j++) {
					listeFormation[i].add(""
							+ rdv.getListeAssurerLecon().get(j)
									.getDUREE_LECON());
				}
				break;
			case 4:
				for (int j = 0; j < rdv.getListeAssurerLecon().count(); j++) {
					listeFormation[i]
							.add(""
									+ rdv.getListeAssurerLecon().get(j)
											.getIdMONITEUR());
				}
				break;
			case 5:
				for (int j = 0; j < rdv.getListeAssurerLecon().count(); j++) {
					listeFormation[i].add(""
							+ rdv.getListeAssurerLecon().get(j)
									.getOBSERVATION_LECON());
				}
				break;
			}

		}
	}

	public void ajouterLigneJtableVierge() {
		int numero = listeFormation[0].size();
		for (int i = 0; i < listeFormation.length; i++) {
			if (i == 0) {
				listeFormation[i].add("" + numero);
			} else {
				listeFormation[i].add("");
			}
		}

		// une liste repr�sente une colonne. ICi 6 colonnes donc on met les
		// des champs vides.
	}
	*/
	
}
