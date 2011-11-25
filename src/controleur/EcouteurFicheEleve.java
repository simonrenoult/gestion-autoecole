
package controleur;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import modele.DataMoniteur;
import net.ko.kobject.KListObject;
import KClass.KMoniteur;
import vue.FicheEleve;

public class EcouteurFicheEleve implements ActionListener, FocusListener
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	FicheEleve			ficheEleve;
	private Hashtable	correspondanceMoniteur	= new Hashtable();
	private String		messageToolTip			= "";
	
	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //
	
	public EcouteurFicheEleve(FicheEleve fiche)
	{
		ficheEleve = fiche;
		recupererListeMoniteur();
		parametrerDateDefault();
		ficheEleve.couleurDefaultChamps();
		
	}
	
	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //
	
	/*
	 * Permet de remettre les dates à la date actuelle.
	 */
	private void parametrerDateDefault()
	{
		ficheEleve.parametrerJComboBoxDate(ficheEleve.getDateEnregiJ() , ficheEleve.getDateEnregiM() ,
				ficheEleve.getDateEnregiA());
		ficheEleve.parametrerJComboBoxDate(ficheEleve.getDateEvaJ() , ficheEleve.getDateEvaM() ,
				ficheEleve.getDateEvaA());
		ficheEleve.parametrerJComboBoxDate(ficheEleve.getDateInscriJ() , ficheEleve.getDateInscriM() ,
				ficheEleve.getDateInscriA());
		ficheEleve.parametrerJComboBoxDate(ficheEleve.getDateNaissJ() , ficheEleve.getDateNaissM() ,
				ficheEleve.getDateNaissA());
	}
	
	/*
	 * Recupere la liste des moniteurs dans la base de donnee afin de
	 * l'exploiter ensuite lors de l'affichagedes informations d'un élève
	 * (sélection du moniteur atitré à l'élève).
	 */
	private void recupererListeMoniteur()
	{
		
		DataMoniteur moniteur = new DataMoniteur();
		
		KListObject<KMoniteur> KListe = new KListObject<KMoniteur>(KMoniteur.class);
		KListe = moniteur.recupererListe();
		String [] ListeMoniteur = new String [KListe.count()];
		for (int i = 0; i < KListe.count(); i++)
		{
			correspondanceMoniteur.put(i , KListe.get(i).getId());
			ListeMoniteur[i] = KListe.get(i).getNOM_MONITEUR().toUpperCase() + " "
					+ KListe.get(i).getPRENOM_MONITEUR().toLowerCase();
		}
		
		ficheEleve.getFormateur().setModel(new DefaultComboBoxModel(ListeMoniteur));
	}
	
	/*
	 * Suite au traitementRegex passé en paramètre, on récupère un booléen et on
	 * colore alors le textfield selectionné. Ce même booléen sera ensuite
	 * redirigé vers le tableau récapitulatif TableauChampSaisieOk regroupant
	 * tous les traitementsRegexs de tous les champs graphiques.
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
			/*
			 * JToolTip j = new JToolTip(); j.setFont( new Font(null, Font.BOLD,
			 * 13)); j.setBackground(Color.GRAY);
			 * j.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			 * j.setToolTipText
			 * (" votre texte ici séparer par \n pour indiquer les lignes ");
			 * champ.add(j);
			 */
			
			// champ.setBorder(BorderFactory.createLineBorder(Color.RED));
			champ.setBackground(new Color(240 , 123 , 123));
			champ.setToolTipText(messageToolTip);
			
		}
		
		return donneeCorrect;
		
	}
	
	// ----------------------------------------- //
	// ----------------LISTENERS---------------- //
	// ----------------------------------------- //
	
	// --------ACTION-------- //
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == ficheEleve.getParcourir())
		{
			String cheminActuel = System.getProperty("user.dir");
			JFileChooser chooser = new JFileChooser(new File(cheminActuel + "\\img"));
			JFrame test = new JFrame();
			int returnVal = chooser.showOpenDialog(test);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				ficheEleve.setChemin((chooser.getSelectedFile().getPath()));
				
				// Adapter la taille de l'image au dimensions du Jlabel le
				// contenant.
				ImageIcon image = new ImageIcon(chooser.getSelectedFile().getPath());
				Image img = image.getImage();
				Image newimg = img.getScaledInstance(ficheEleve.getLabelPhoto().getWidth() , ficheEleve.getLabelPhoto()
						.getHeight() , java.awt.Image.SCALE_SMOOTH);
				ImageIcon newIcon = new ImageIcon(newimg);
				ficheEleve.setImageEleve(newIcon);
				ficheEleve.getLabelPhoto().setIcon(ficheEleve.getImageEleve());
				ficheEleve.repaint();
			}
		}
		if (e.getSource() == ficheEleve.getFormateur())
		{
			int index = ficheEleve.getFormateur().getSelectedIndex();
			ficheEleve.setIdMoniteur(((Integer) correspondanceMoniteur.get(index)));
		}
	}
	
	// --------FOCUS-------- //
	
	@Override
	public void focusGained(FocusEvent e)
	{
	}
	
	@Override
	public void focusLost(FocusEvent e)
	{
		if (e.getSource() == ficheEleve.getNom())
		{
			messageToolTip = "Le nom de Famille est invalide pour l'enregistrement";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[0] = couleurChampPerteFocus(ficheEleve.getNom() ,
					ficheEleve.getDataFiche1().regexTraitementNom(ficheEleve.getNom().getText()));
		}
		if (e.getSource() == ficheEleve.getPrenom())
		{
			messageToolTip = "Le prénom est invalide pour l'enregistrement";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[1] = couleurChampPerteFocus(ficheEleve.getPrenom() ,
					ficheEleve.getDataFiche1().regexTraitementPrenom(ficheEleve.getPrenom().getText()));
		}
		if (e.getSource() == ficheEleve.getAdresse())
		{
			messageToolTip = "L'adresse est invalide pour l'enregistrement";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[2] = couleurChampPerteFocus(ficheEleve.getAdresse() ,
					ficheEleve.getDataFiche1().regexTraitementAdresse(ficheEleve.getAdresse().getText()));
		}
		if (e.getSource() == ficheEleve.getCodePostal())
		{
			messageToolTip = "Le code postal doit être composé de 5 chiffres";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[3] = couleurChampPerteFocus(
					ficheEleve.getCodePostal() ,
					ficheEleve.getDataFiche1().regexTraitementCP(ficheEleve.getCodePostal().getText()));
		}
		if (e.getSource() == ficheEleve.getCommuneEleve())
		{
			messageToolTip = "Le nom de commune est invalide pour l'enregistrement";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[4] = couleurChampPerteFocus(
					ficheEleve.getCommuneEleve() ,
					ficheEleve.getDataFiche1().regexTraitementCommune(ficheEleve.getCommuneEleve().getText()));
		}
		if (e.getSource() == ficheEleve.getTelephone())
		{
			messageToolTip = "Le numéro de téléphone doit être composé de 10 chiffres";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[5] = couleurChampPerteFocus(ficheEleve.getTelephone() ,
					ficheEleve.getDataFiche1().regexTraitementTelephone(ficheEleve.getTelephone().getText()));
		}
		if (e.getSource() == ficheEleve.geteMail())
		{
			messageToolTip = "L'adresse Email est invalide pour l'enregistrement";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[6] = couleurChampPerteFocus(ficheEleve.geteMail() ,
					ficheEleve.getDataFiche1().regexTraitementMail(ficheEleve.geteMail().getText()));
		}
		if (e.getSource() == ficheEleve.getProfession())
		{
			messageToolTip = "La profession est invalide pour l'enregistrement";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[7] = couleurChampPerteFocus(
					ficheEleve.getProfession() ,
					ficheEleve.getDataFiche1().regexTraitementProfession(ficheEleve.getProfession().getText()));
		}
		if (e.getSource() == ficheEleve.getNumLivret())
		{
			messageToolTip = "Le numéro de Livret doit être composé uniquement de chiffres";
			ficheEleve.getDataFiche1().getTableauChampSaisieOk()[8] = couleurChampPerteFocus(ficheEleve.getNumLivret() ,
					ficheEleve.getDataFiche1().regexTraitementNumLivret(ficheEleve.getNumLivret().getText()));
		}
		
	}
	
}
