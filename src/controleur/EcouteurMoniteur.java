
package controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import net.ko.kobject.KListObject;
import modele.DataMoniteur;
import vue.*;
import KClass.*;

public class EcouteurMoniteur implements MouseListener, ActionListener, KeyListener, WindowListener, FocusListener
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private FenetreMoniteur	f;
	private int				idMoniteur		= 0;
	private DataMoniteur	Datamoniteur	= new DataMoniteur();
	private Hashtable		hashMoniteurRef	= new Hashtable();
	private Hashtable		hashMoniteurVar	= new Hashtable();
	private String			messageToolTip	= "";
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public EcouteurMoniteur(FenetreMoniteur f)
	{
		this.f = f;
		Datamoniteur = f.getDataMoniteur();
		initialiserJlist();
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	/*
	 * Recharge la Jlist avec une liste de moniteur.
	 */
	private void initialiserJlist()
	{
		f.getListeMoniteur().setListData(recupererListeMoniteur().toArray());
	}
	
	/*
	 * recupere une liste de moniteur Nom-Prenom pour l'afficher
	 */
	private ArrayList<String> recupererListeMoniteur()
	{
		KListObject<KMoniteur> KListe = Datamoniteur.recupererListe();
		ArrayList<String> listeMoniteur = new ArrayList<String>();
		for (int i = 0; i < KListe.count(); i++)
		{
			hashMoniteurRef.put(i , KListe.get(i).getId());
			listeMoniteur.add(KListe.get(i).getPRENOM_MONITEUR().toLowerCase() + " "
					+ KListe.get(i).getNOM_MONITEUR().toUpperCase());
		}
		return listeMoniteur;
		
	}
	
	/*
	 * MAJ des labels graphiques pour identifier un moniteur
	 */
	private void ChargerDonneesMoniteur()
	{
		KMoniteur moniteur = Datamoniteur.recupererProfilMoniteur(idMoniteur);
		if (moniteur != null)
		{
			f.getNom().setText(moniteur.getNOM_MONITEUR());
			f.getPrenom().setText(moniteur.getPRENOM_MONITEUR());
		}
	}
	
	/*
	 * Permet de filtrer sur le nom, prenom du moniteur
	 */
	private void filtreRechercheNomPrenomEleve()
	{
		ArrayList<String> listeMoniteur = new ArrayList<String>();
		listeMoniteur = recupererListeMoniteur();
		// On met à jour la plus récente des listes de moniteurs
		f.getListeMoniteur().setListData(listeMoniteur.toArray());
		// on recupere la chaine frappé dans la barre recherche.
		String chaineRecherche = f.getRechercheM().getText();
		// on recupere la taille de la liste de type JlistModel contenu dans la
		// Jlist.
		int tailleListeMoniteur = listeMoniteur.size();
		hashMoniteurVar = hashMoniteurRef;
		String tabMoniteurRecherche[] = new String [tailleListeMoniteur];
		int cpt = 0;
		for (int i = 0; i < tailleListeMoniteur; i++)
		{
			// On recupere une a une les chaines de la Jlist
			String chaineListe = f.getListeMoniteur().getModel().getElementAt(i).toString();
			if (chaineListe.toUpperCase().indexOf(chaineRecherche) != -1
					|| chaineListe.toUpperCase().indexOf(chaineRecherche.toUpperCase()) != -1)
			{
				hashMoniteurVar.put(cpt , hashMoniteurRef.get(i));
				// System.out.println("cpt : "+cpt+"id :"+correspondanceEleveRef.get(i));
				tabMoniteurRecherche[cpt] = chaineListe;
				cpt++;
			}
			
		}
		
		// On redefinit un tableau pour la taille.
		String tabMoniteur[] = new String [cpt];
		for (int i = 0; i < cpt; i++)
		{
			tabMoniteur[i] = tabMoniteurRecherche[i];
			
		}
		f.getListeMoniteur().setListData(tabMoniteur);
		
	}
	
	/*
	 * permet de changer la couleur du champ sur la regx appliqué
	 */
	private boolean couleurChampPerteFocus(JTextField champ, boolean donneeCorrect)
	{
		
		if (donneeCorrect)
		{
			// champ.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			champ.setBackground(new Color(146 , 243 , 130));
			champ.setToolTipText("");
			
		}
		else
		{
			
			champ.setBackground(new Color(240 , 123 , 123));
			champ.setToolTipText(messageToolTip);
			
		}
		
		return donneeCorrect;
		
	}
	
	/*
	 * permet la MAJ du moniteur (fonction principale)
	 */
	private void MAJmoniteur()
	{
		if (Datamoniteur.tableauChampSaisieTrue())
		{
			KMoniteur MoniteurAinserer = creationMoniteurMAJ();
			if (confirmationEtInsertMaj(MoniteurAinserer))
			{
				initialiserJlist();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null , Datamoniteur.messageRenvoyeeUI(-5) , "Erreur" ,
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/*
	 * permet l'ajout du moniteur (fonction proncipale)
	 */
	private void ajoutMoniteur()
	{
		if (Datamoniteur.tableauChampSaisieTrue())
		{
			KMoniteur MoniteurAinserer = creationMoniteurAjout();
			if (confirmationEtInsertAjout(MoniteurAinserer))
			{
				initialiserJlist();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null , Datamoniteur.messageRenvoyeeUI(-5) , "Erreur" ,
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/*
	 * permet de creer un moniteur pour l'ajout
	 */
	private KMoniteur creationMoniteurAjout()
	{
		
		KMoniteur moniteur = new KMoniteur();
		moniteur.setNOM_MONITEUR(f.getNom().getText());
		moniteur.setPRENOM_MONITEUR(f.getPrenom().getText());
		
		return moniteur;
	}
	
	/*
	 * permet de creer un moniteur pour la MAJ
	 */
	private KMoniteur creationMoniteurMAJ()
	{
		
		KMoniteur moniteur = new KMoniteur();
		moniteur.setId(idMoniteur);
		moniteur.setNOM_MONITEUR(f.getNom().getText());
		moniteur.setPRENOM_MONITEUR(f.getPrenom().getText());
		
		return moniteur;
	}
	
	/*
	 * Boite de confirmation pre et post ajout d'un moniteur
	 */
	private boolean confirmationEtInsertAjout(KMoniteur MoniteurAinserer)
	{
		boolean confirmation = true;
		int option = JOptionPane.showConfirmDialog(
				null ,
				"Ajouter le moniteur " + MoniteurAinserer.getPRENOM_MONITEUR() + " "
						+ MoniteurAinserer.getNOM_MONITEUR() + " à la base de donnée ?" , "Demande de confirmation" ,
				JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
		
		if (option == JOptionPane.OK_OPTION)
		{
			confirmation = Datamoniteur.ajouterMoniteur(MoniteurAinserer , f.getListeMoniteur().getModel().getSize());
			if (confirmation)
			{
				JOptionPane.showMessageDialog(null , Datamoniteur.messageRenvoyeeUI(2) ,
						"Opération d'ajout d'un élève" , JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null , Datamoniteur.messageRenvoyeeUI(3) , "Erreur" ,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return confirmation;
		
	}
	
	/*
	 * Boite de confirmation pre et post MAJ d'un moniteur
	 */
	private boolean confirmationEtInsertMaj(KMoniteur MoniteurAinserer)
	{
		boolean confirmation = true;
		int option = JOptionPane.showConfirmDialog(
				null ,
				"Mettre à jour le moniteur " + MoniteurAinserer.getPRENOM_MONITEUR() + " "
						+ MoniteurAinserer.getNOM_MONITEUR() + " dans la base de donnée ?" , "Demande de confirmation" ,
				JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
		
		if (option == JOptionPane.OK_OPTION)
		{
			confirmation = Datamoniteur.majMoniteur(MoniteurAinserer);
			if (confirmation)
			{
				JOptionPane.showMessageDialog(null , Datamoniteur.messageRenvoyeeUI(4) ,
						"Opération de mise à jour d'un élève" , JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null , Datamoniteur.messageRenvoyeeUI(5) , "Erreur" ,
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return confirmation;
		
	}
	
	// ----------------------------------------- //
	// ----------------LISTENERS---------------- //
	// ----------------------------------------- //
	
	// --------MOUSE-------- //
	
	public void mouseClicked(java.awt.event.MouseEvent e)
	{}
	
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e)
	{}
	
	@Override
	public void mouseExited(java.awt.event.MouseEvent e)
	{		
	}
	
	@Override
	public void mousePressed(java.awt.event.MouseEvent e)
	{		
	}
	
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e)
	{
		if (e.getSource() == f.getListeMoniteur() && e.getClickCount() == 2)
		{
			int index = f.getListeMoniteur().getSelectedIndex();
			idMoniteur = (Integer) (hashMoniteurRef.get(index));
			ChargerDonneesMoniteur();
			f.getAjouter().setText("Mettre à jour");
			f.getEffacer().setEnabled(true);
		}
		if (e.getSource() == f.getRechercheM() && e.getClickCount() == 1)
		{
			f.getRechercheM().setText("");
		}
		
	}
	
	// --------ACTION-------- //
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource() == f.getEffacer())
		{
			System.out.println("effacer");
		}
		if (e.getSource() == f.getAjouter())
		{
			if (f.getAjouter().getText() == "Ajouter")
			{
				ajoutMoniteur();
			}
			else
			{
				MAJmoniteur();
			}
			
		}
		if (e.getSource() == f.getNouveauMoniteur())
		{
			System.out.println("ok");
			f.getNom().setText("");
			f.getPrenom().setText("");
			
			idMoniteur = 0;
			f.getEffacer().setEnabled(false);
			f.getAjouter().setText("Ajouter");
			f.getPrenom().setBackground(Color.white);
			f.getNom().setBackground(Color.white);
		}
		
	}
	
	// --------KEY-------- //
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getSource() == f.getRechercheM())
		{
			filtreRechercheNomPrenomEleve();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}
	
	// --------WINDOW-------- //
	
	public void windowActivated(WindowEvent arg0)
	{
	}
	
	@Override
	public void windowClosed(WindowEvent arg0)
	{
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void windowClosing(WindowEvent arg0)
	{
		int option = new JOptionPane().showConfirmDialog(null ,
				"Voulez-vous quitter la fenêtre de gestion des moniteurs ?" , "Quitter" , JOptionPane.YES_NO_OPTION ,
				JOptionPane.QUESTION_MESSAGE);
		
		if (option == JOptionPane.OK_OPTION)
		{
			f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		}
		else
		{
			f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent arg0)
	{}
	
	@Override
	public void windowDeiconified(WindowEvent arg0)
	{}
	
	@Override
	public void windowIconified(WindowEvent arg0)
	{}
	
	@Override
	public void windowOpened(WindowEvent arg0)
	{}
	
	// //////////////////////////////////////////////////////////FOCUSLISTENER
	@Override
	public void focusGained(FocusEvent arg0)
	{}
	
	@Override
	public void focusLost(FocusEvent e)
	{
		if (e.getSource() == f.getNom())
		{
			messageToolTip = "Le nom de Famille est invalide pour l'enregistrement";
			Datamoniteur.getTableauChampSaisieOk()[0] = couleurChampPerteFocus(f.getNom() ,
					Datamoniteur.regexTraitementNom(f.getNom().getText()));
		}
		if (e.getSource() == f.getPrenom())
		{
			messageToolTip = "Le prénom est invalide pour l'enregistrement";
			Datamoniteur.getTableauChampSaisieOk()[1] = couleurChampPerteFocus(f.getPrenom() ,
					Datamoniteur.regexTraitementPrenom(f.getPrenom().getText()));
		}
		
	}
	
}