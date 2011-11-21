
package vue;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modele.bdd;
import net.ko.kobject.KListObject;
import net.ko.ksql.KDBMysql;
import KClass.KAgenda;
import KClass.KAssurer_lecon;
import KClass.KCategorie_i_o;
import KClass.KEleve;
import KClass.KFormation;
import KClass.KMoniteur;
import KClass.KObjectif;
import KClass.KPasser;
import KClass.KQuestions_synthese;
import KClass.KRealiser;
import KClass.KReponse;
import KClass.KSynthese;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class Export
{
	
	// ----------------------------------------- //
	// ----------------ATTRIBUTS---------------- //
	// ----------------------------------------- //
	
	private KDBMysql db = bdd.db;

	// ----------------------------------------- //
	// --------------CONSTRUCTEURS-------------- //
	// ----------------------------------------- //

	// Constructeur de la classe Export().
	public Export()
	{
		/*
		 * 
		 * TODO : Résumé de ce qu'il reste c faire :
		 * 
		 * Peut-être terminé, mais c discuter avec les autres membres du groupe
		 * : - O Mettre un message d'erreur si un document est ouvert pendant
		 * qu'un autre l'est aussi. - O Ouverture directe du fichier aprés avoir
		 * été généré. - O Export multiple : laisser le choix de sélection d'un
		 * dossier ? - LMI Graphique : cases c cocher.
		 * 
		 * - Le plus important : - Code des synthcses en dur : c remplacer par
		 * les importations de la base de données. - Nombre d'heures pratiques :
		 * faire pour chaque synthcse en fonction de la date. - Faire
		 * l'impression directe.
		 * 
		 * - Le moins important : - Nettoyer le code (répétitions des synthcses
		 * et des cases, fonction "passageALaLigneAutomatique"...). - Commenter.
		 */

	}

	// ----------------------------------------- //
	// -----------------METHODES---------------- //
	// ----------------------------------------- //

	// Sélectionne si l'action choisie est un export ou une impression.
	public void exportOuImpression(int index,FenetrePrincipale fenetre,int action)
	{

		if (action == 0) exportPDF(index, fenetre);
		if (action == 1) exportPDFs(fenetre);
		if (action == 2) impressionPDF(index, fenetre);

	}

	// Crée et stocke un document PDF contenant toutes les données saisies dans
	// le logiciel pour l'élcve sélectionné.
	private void exportPDF(int index,FenetrePrincipale fenetre)
	{
		if (index != -1)
		{
			Document doc = new Document();
			PdfWriter writer = null;

			try
			{
				JFileChooser chooser = null;

				// Instance du fichier s'il n'existe pas.
				if (chooser == null) chooser = new JFileChooser();
				chooser.setSelectedFile(new File("Eleve nc" + (index + 1)));

				// Ouverture d'une fenctre de dialogue pour choisir
				// l'emplacement d'enregistrement du fichier.
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						writer = PdfWriter.getInstance(doc, new FileOutputStream(chooser.getSelectedFile() + ".pdf"));
					}
					catch (FileNotFoundException e)
					{
						JOptionPane
								.showMessageDialog(
										null,
										"Ce fichier est actuellement ouvert dans un dossier quelconque.\nPour générer un nouveau fichier portant le mcme nom dans le dossier du logiciel, veuillez d'abord le fermer.",
										"Fichier ouvert", JOptionPane.WARNING_MESSAGE);
						// JOptionPane.showMessageDialog(null,
						// "Ce fichier est actuellement ouvert.\nPour l'écraser par un nouveau fichier portant le mcme nom dans le mcme dossier, veuillez d'abord le fermer.",
						// "Fichier ouvert", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			catch (DocumentException e)
			{
				e.printStackTrace();
			}

			if (writer != null)
			{
				try
				{
					creationPDF(writer, doc, index, fenetre);

					// Affichage instantance du contenu du document PDF.
					Runtime.getRuntime().exec(
							"rundll32 url.dll, FileProtocolHandler " + "Eleve nc" + (index + 1) + ".pdf");

				}
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (DocumentException e2)
				{
					e2.printStackTrace();
				}
				catch (IOException e3)
				{
					e3.printStackTrace();
				}
			}

		}
		else
		{
			JOptionPane.showMessageDialog(null, "Sans élcve sélectionné, l'export est impossible.",
					"Export impossible", JOptionPane.WARNING_MESSAGE);
		}

	}

	// Crée et stocke plusieurs documents PDF contenant toutes les données
	// saisies dans le logiciel pour tous les élcves.
	private void exportPDFs(FenetrePrincipale fenetre)
	{
		KListObject<KEleve> eleves = new KListObject<KEleve>(KEleve.class);
		eleves.loadFromDb(db);

		for (int i = 0; i < eleves.count(); i++)
		{

			Document doc = new Document();
			PdfWriter writer = null;

			try
			{
				JFileChooser chooser = null;

				// Instance du fichier s'il n'existe pas.
				if (chooser == null) chooser = new JFileChooser();
				chooser.setSelectedFile(new File("Eleve nc" + (i + 1)));

				try
				{
					writer = PdfWriter.getInstance(doc, new FileOutputStream(chooser.getSelectedFile() + ".pdf"));
				}
				catch (FileNotFoundException e)
				{
					JOptionPane
							.showMessageDialog(
									null,
									"Ce fichier est actuellement ouvert dans un dossier quelconque.\nPour générer un nouveau fichier portant le mcme nom dans le dossier du logiciel, veuillez d'abord le fermer.",
									"Fichier ouvert", JOptionPane.WARNING_MESSAGE);
				}
			}
			catch (DocumentException e)
			{
				e.printStackTrace();
			}

			if (writer != null)
			{
				try
				{
					creationPDF(writer, doc, i, fenetre);
				}
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (DocumentException e2)
				{
					e2.printStackTrace();
				}
				catch (IOException e3)
				{
					e3.printStackTrace();
				}
			}
		}

	}

	// Imprime un document PDF contenant toutes les données saisies dans le
	// logiciel pour l'élcve sélectionné.
	private void impressionPDF(int index,FenetrePrincipale fenetre)
	{

		Document doc = new Document();
		PdfWriter writer = null;

		try
		{
			writer = PdfWriter.getInstance(doc, new FileOutputStream("Eleve nc" + (index + 1) + ".pdf"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (DocumentException e)
		{
			e.printStackTrace();
		}

		if (index != -1)
		{
			try
			{
				creationPDF(writer, doc, index, fenetre);

				// Vcrification pour savoir s'il y a un programme d'impression
				// par dcfaut.
				if (Desktop.isDesktopSupported())
				{
					if (Desktop.getDesktop().isSupported(Desktop.Action.PRINT))
					{
						try
						{
							Desktop.getDesktop().print(new File("Eleve nc" + (index + 1) + ".pdf"));
						}
						catch (IOException e)
						{
							JOptionPane.showMessageDialog(null,
									"Vous n'avez pas de programme par dcfaut pour imprimer.", "Programme par dcfaut",
									JOptionPane.WARNING_MESSAGE);
							e.printStackTrace();
						}
					}
					else
					{
						// Impression non-supportce par le systcme
						// d'exploitation de l'utilisateur.
						JOptionPane.showMessageDialog(null, "Vous n'avez pas de programme par dcfaut pour imprimer.",
								"Programme par dcfaut", JOptionPane.WARNING_MESSAGE);
					}
				}

			}
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
			catch (DocumentException e2)
			{
				e2.printStackTrace();
			}
			catch (IOException e3)
			{
				e3.printStackTrace();
			}
		}
		else
		{
			new JOptionPane();
			JOptionPane.showMessageDialog(null, "Sans clcve sclectionnc, l'impression est impossible.",
					"Impression impossible", JOptionPane.WARNING_MESSAGE);
		}

	}

	// Exporte les donnces saisies pour un clcve dans un livret gcncrc en PDF.
	// Facilite l'impression.
	private void creationPDF(PdfWriter writer,Document doc,int index,FenetrePrincipale fenetre)
			throws FileNotFoundException,DocumentException,IOException
	{

		// Ouverture du document PDF sur lequel on va ccrire les informations du
		// livret.
		doc.open();

		// Importation des diffcrentes donnces nccessaires pour le livret, c
		// partir de la base de donnces.
		KListObject<KEleve> eleves = new KListObject<KEleve>(KEleve.class);
		eleves.loadFromDb(db);
		KEleve kEl = eleves.get(index);
		KMoniteur kM = kEl.getMoniteur();
		KFormation kFo = kEl.getFormation();

		// Initialisation de variables utilisces pour l'affichage de texte et
		// d'images.
		int xBaseGroupe = 0;
		int yBaseGroupe = 0;
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		Paragraph sautLigne = new Paragraph(" ");
		PdfContentByte canvas = writer.getDirectContent();

		// ----------------------------------------- //
		// ---------------FICHE_ELEVE--------------- //
		// ----------------------------------------- //

		// Affichage de la fiche clcve c partir des informations de la base de
		// donnces.
		affichageFicheEleve(xBaseGroupe, yBaseGroupe, sautLigne, doc, writer, canvas, bf, kEl, kM, kFo, fenetre);
		doc.add(sautLigne);
		doc.add(sautLigne);
		doc.add(sautLigne);
		doc.add(sautLigne);
		doc.add(sautLigne);

		// ----------------------------------------- //
		// -------------TABLEAU_LECONS-------------- //
		// ----------------------------------------- //

		// Affichage du titre du tableau des lecons.
		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		absText("FORMATION PRATIQUE", 200, 388, writer, bf, 17);
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		// Affichage du tableau des lecons, dccomposc en deux parties, c partir
		// des informations de la base de donnces.
		tableauLecons(0, 17, 32, 365, canvas, kEl);
		doc.newPage();
		tableauLecons(18, 53, 32, 700, canvas, kEl);
		// tableauLecons(18, 35, 32, 700, canvas, db, kEl);
		// tableauLecons(36, 53, 32, 350, canvas, db, kEl);

		// ----------------------------------------- //
		// ----------------4 LECONS----------------- //
		// ----------------------------------------- //

		// Affichage de chacun des quatre ctapes (objectifs et synthcses) c
		// partir des informations de la base de donnces.
		for (int i = 1; i <= 4; i++)
		{
			affichageEtape(i, xBaseGroupe, yBaseGroupe, doc, writer, canvas, bf, kEl, kM, index, fenetre);
		}

		// ----------------------------------------- //
		// --------------INTERRO_ORALE-------------- //
		// ----------------------------------------- //

		// Ouverture d'une nouvelle page.
		doc.newPage();

		// Affichage du titre de l'interrogation orale.
		Paragraph titreIO = new Paragraph("INTERROGATION ORALE V2 (extcrieur vchicule)", FontFactory.getFont(
				FontFactory.HELVETICA, 18, Font.BOLD));
		doc.add(titreIO);
		doc.add(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -14));

		// Appel c la fonction permettant d'afficher les questions et les ctats
		// des rcponses de l'interrogation orale.
		// L'ordonnce et l'abscisse sont attribuces en fonction de la position
		// oc commencent les questions sur la page.
		xBaseGroupe = 45;
		yBaseGroupe = 740;
		questionsReponsesInterrogationOrale(xBaseGroupe, yBaseGroupe, doc, writer, bf, index);

		// ----------------------------------------- //
		// ----------------EXAM BLANC--------------- //
		// ----------------------------------------- //

		// TODO : A faire.

		// Fermeture du document PDF.
		doc.close();

	}

	// ----------------------------------------- //
	// ---------------AFFICHAGES---------------- //
	// ----------------------------------------- //

	// Affiche une fiche clcve c partir des informations de la base de donnces.
	private void affichageFicheEleve(int xBaseGroupe,int yBaseGroupe,Paragraph sautLigne,Document doc,PdfWriter writer,
			PdfContentByte canvas,BaseFont bf,KEleve kEl,KMoniteur kM,KFormation kFo,FenetrePrincipale fenetre)
			throws DocumentException,IOException
	{

		// Crcation des diffcrentes formes gcomctriques utilisces sur la
		// premicre page.
		canvas.setLineWidth(1);
		canvas.setRGBColorStroke(0x70, 0x93, 0xDB);
		canvas.rectangle(28, 614, 192, 127);
		canvas.rectangle(240, 526, 330, 215);
		canvas.rectangle(28, 430, 260, 64);
		canvas.rectangle(310, 430, 260, 45);
		canvas.stroke();

		// Affichage du titre s'affichant en haut de la premicre page.
		Paragraph titre = new Paragraph("FICHE DE SUIVI   " + kFo.getLIBELLE_FORMATION(), FontFactory.getFont(
				FontFactory.HELVETICA, 20, Font.BOLD));
		titre.setAlignment(Element.ALIGN_RIGHT);
		doc.add(titre);
		doc.add(new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -14));
		doc.add(sautLigne);
		doc.add(sautLigne);

		// Affichage des informations lices c l'cvaluation de dcpart de l'clcve.
		doc.add(new Paragraph("      Evaluation de dcpart", FontFactory.getFont(null, 14, Font.BOLD)));
		doc.add(new Chunk("Date :", FontFactory.getFont(null, 12, Font.BOLD)));
		doc.add(new Chunk("    " + kEl.getDATE_EVAL_ELEVE()));
		doc.add(new Paragraph(""));
		doc.add(new Chunk("Rcsultat :", FontFactory.getFont(null, 12, Font.BOLD)));
		doc.add(new Chunk("    " + kEl.getRESULTAT_ELEVE_ORAL()));
		doc.add(new Paragraph(""));
		doc.add(new Paragraph("Volume de formation prcvu :", FontFactory.getFont(null, 13, Font.BOLD)));
		doc.add(new Paragraph("Thcorique :    " + kEl.getVOLUME_HORAIRE_TH_ELEVE() + " H"));
		doc.add(new Paragraph("Pratique :    " + kEl.getVOLUME_HORAIRE_PRATIQUE_ELEVE() + " H"));
		doc.add(sautLigne);

		// Affichage des dates d'inscription et d'enregistrement de l'clcve.
		doc.add(new Paragraph("Inscription", FontFactory.getFont(null, 12, Font.BOLD)));
		doc.add(new Paragraph("                " + kEl.getDATE_INSCRIPTION_ELEVE()));
		doc.add(new Paragraph("Enregistrement", FontFactory.getFont(null, 12, Font.BOLD)));
		doc.add(new Paragraph("                " + kEl.getDATE_ENREGISTREMENT_ELEVE()));
		doc.add(sautLigne);

		// Affichage des diffcrentes informations personnelles (civilitcs) de
		// l'clcve.
		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		xBaseGroupe = 250;
		yBaseGroupe = 718;
		absText("Nom :  " + kEl.getNOM_ELEVE().toUpperCase(), xBaseGroupe, yBaseGroupe, writer, bf, 16);
		absText("Prcnom :    " + kEl.getPRENOM_ELEVE(), xBaseGroupe, yBaseGroupe - 20, writer, bf, 12);
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		absText("Date de naissance :    " + kEl.getDATE_DE_NAISS_ELEVE(), xBaseGroupe, yBaseGroupe - 58, writer, bf, 12);
		if (kEl.getMAIL_ELEVE() != null) absText("Email :    " + kEl.getMAIL_ELEVE(), xBaseGroupe, yBaseGroupe - 78,
				writer, bf, 12);
		else absText("Email :", xBaseGroupe, yBaseGroupe - 78, writer, bf, 12);
		absText("Numcro de tclcphone :    " + kEl.getTELEPHONE_ELEVE(), xBaseGroupe, yBaseGroupe - 98, writer, bf, 12);
		absText("Adresse :    " + kEl.getADRESSE_ELEVE(), xBaseGroupe, yBaseGroupe - 118, writer, bf, 12);
		absText("Code postal :    " + kEl.getCODE_POSTAL_ELEVE(), xBaseGroupe, yBaseGroupe - 138, writer, bf, 12);
		absText("Commune :    " + kEl.getCOMMUNE_ELEVE(), xBaseGroupe, yBaseGroupe - 158, writer, bf, 12);
		if (kEl.getPROFESSION_ELEVE() != null) absText("Profession :    " + kEl.getPROFESSION_ELEVE(), xBaseGroupe,
				yBaseGroupe - 178, writer, bf, 12);
		else absText("Profession :", xBaseGroupe, yBaseGroupe - 178, writer, bf, 12);

		// Affichage de la photo de l'clcve.
		try
		{
			Image photo = Image.getInstance(kEl.getPHOTO_ELEVE());
			int width = fenetre.getFicheEleve().getLabelPhoto().getWidth();
			width = (int) (0.66 * width);
			int height = fenetre.getFicheEleve().getLabelPhoto().getHeight();
			height = (int) (0.66 * height);
			photo.scaleAbsolute(width, height);
			photo.setAbsolutePosition(470f, 616f);
			writer.getDirectContent().addImage(photo, true);
		}
		catch (FileNotFoundException e)
		{

		}

		// Affichage des informations lices au test de la vue passc par l'clcve.
		if (kEl.getTEST_VU_ELEVE() == 1)
		{
			doc.add(new Chunk("Test de la vue :", FontFactory.getFont(null, 12, Font.BOLD)));
			doc.add(new Chunk("      Oui"));
			doc.add(new Paragraph(""));
		}
		else if (kEl.getTEST_VU_ELEVE() == 0)
		{
			doc.add(new Chunk("Test de la vue :", FontFactory.getFont(null, 12, Font.BOLD)));
			doc.add(new Chunk("      Non"));
			doc.add(new Paragraph(""));
		}
		else
		{
			doc.add(new Chunk("Test de la vue :", FontFactory.getFont(null, 12, Font.BOLD)));
			doc.add(new Chunk("      Non fait"));
			doc.add(new Paragraph(""));
		}
		observationVueEleve(writer, bf, kEl);

		// Affichage du nc N.E.P.H. attribuc au livret de l'clcve, de son
		// responsable de formation et de son moniteur.
		xBaseGroupe = 340;
		yBaseGroupe = 504;

		absText("LIVRET D'APPRENTISSAGE Nc N.E.P.H.", xBaseGroupe, yBaseGroupe, writer, bf, 12);
		absText("" + kEl.getLIVRET_NEPH_ELEVE(), xBaseGroupe - 10, yBaseGroupe - 20, writer, bf, 12);

		absText("Responsable de la formation :    ", xBaseGroupe - 20, yBaseGroupe - 45, writer, bf, 12);
		absText("Formateur :    " + kM.getPRENOM_MONITEUR() + " " + kM.getNOM_MONITEUR().toUpperCase(),
				xBaseGroupe - 20, yBaseGroupe - 65, writer, bf, 12);

	}

	// Affiche l'observation de la vue de l'clcve de telle facon que le texte ne
	// dcpasse pas du cadre le contenant.
	private void observationVueEleve(PdfWriter writer,BaseFont bf,KEleve kEl) throws DocumentException,IOException
	{

		boolean finChaine = false;
		String chaineActuelle;
		chaineActuelle = kEl.getOBSERVATION_VUE_ELEVE();
		int maxCharLigne;
		maxCharLigne = 42;
		int k = 0;

		while (!(finChaine))
		{
			if (chaineActuelle.length() >= maxCharLigne)
			{
				absText(chaineActuelle.substring(0, chaineActuelle.substring(0, maxCharLigne).lastIndexOf(" ")), 36,
						480 - k * 12, writer, bf, 12);
				chaineActuelle = chaineActuelle
						.substring(chaineActuelle.substring(0, maxCharLigne).lastIndexOf(" ") + 1);
			}
			else
			{
				absText(chaineActuelle.substring(0), 36, 480 - k * 12, writer, bf, 12);
				finChaine = true;
			}
			k++;
		}

	}

	// Affiche un tableau de lecons c partir des informations de la base de
	// donnces.
	private void tableauLecons(int debutLecons,int finLecons,int positionTableauX,int positionTableauY,
			PdfContentByte canvas,KEleve kEl)
	{

		KListObject<KMoniteur> moniteurs = new KListObject<KMoniteur>(KMoniteur.class);
		Phrase p;
		PdfPCell c;

		PdfPTable tL = new PdfPTable(new float[] { 5, 4, 3, 7, 10 });
		tL.setTotalWidth(530);

		p = new Phrase("Date", FontFactory.getFont(null, 11, Font.BOLD));
		tL.addCell(placerCelluleCentreeTableauLecons(p));

		p = new Phrase("Horaire", FontFactory.getFont(null, 11, Font.BOLD));
		tL.addCell(placerCelluleCentreeTableauLecons(p));

		p = new Phrase("Durce", FontFactory.getFont(null, 11, Font.BOLD));
		tL.addCell(placerCelluleCentreeTableauLecons(p));

		p = new Phrase("Moniteur", FontFactory.getFont(null, 11, Font.BOLD));
		tL.addCell(placerCelluleCentreeTableauLecons(p));

		p = new Phrase("Observation", FontFactory.getFont(null, 11, Font.BOLD));
		tL.addCell(placerCelluleCentreeTableauLecons(p));

		for (int i = debutLecons; i <= finLecons; i++)
		{

			KAssurer_lecon kLec = null;
			KAgenda kAg = null;
			KMoniteur kM = null;

			try
			{
				kLec = kEl.getAssurer_lecons().get(i);
				KListObject<KAgenda> agenda = new KListObject<KAgenda>(KAgenda.class);
				agenda.loadFromDb(db, "SELECT * FROM agenda WHERE id=" + kLec.getIdAGENDA());
				kAg = agenda.get(0);
			}
			catch (IndexOutOfBoundsException e)
			{

			}

			try
			{
				if ((i + 1) < 10) p = new Phrase((i + 1) + "     " + kAg.getDATE_AGENDA(), FontFactory.getFont(null,
						10, Font.NORMAL));
				else if ((i + 1) >= 10) p = new Phrase((i + 1) + "   " + kAg.getDATE_AGENDA(), FontFactory.getFont(
						null, 10, Font.NORMAL));
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase((i + 1) + " ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (NullPointerException e)
			{
				p = new Phrase((i + 1) + " ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			c = new PdfPCell(p);
			c = positionnerCellule(c, 1.2f, 1, 1, 0, 3);
			tL.addCell(c);

			try
			{
				p = new Phrase(kAg.getHEURE_AGENDA() + "", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (NullPointerException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			tL.addCell(placerCelluleCentreeTableauLecons(p));

			try
			{
				p = new Phrase(compterHeure(kLec.getDUREE_LECON()), FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (NullPointerException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			tL.addCell(placerCelluleCentreeTableauLecons(p));

			try
			{
				moniteurs.loadFromDb(db, "SELECT * FROM moniteur WHERE id=" + kLec.getIdMONITEUR());
				kM = moniteurs.get(0);
				p = new Phrase(kM.getPRENOM_MONITEUR() + " " + kM.getNOM_MONITEUR().toUpperCase(), FontFactory.getFont(
						null, 10, Font.NORMAL));
				moniteurs.clear();
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (NullPointerException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			tL.addCell(placerCelluleCentreeTableauLecons(p));

			try
			{
				p = new Phrase(kLec.getOBSERVATION_LECON(), FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (NullPointerException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			tL.addCell(placerCelluleCentreeTableauLecons(p));

		}

		tL.writeSelectedRows(0, -1, positionTableauX, positionTableauY, canvas);

	}

	// Affiche une ctape c partir des informations de la base de donnces.
	private void affichageEtape(int numEtape,int xBaseGroupe,int yBaseGroupe,Document doc,PdfWriter writer,
			PdfContentByte canvas,BaseFont bf,KEleve kEl,KMoniteur kM,int index,FenetrePrincipale fenetre)
			throws DocumentException,IOException
	{

		// Initialisation d'une variable qui va servir comme ordonnce
		// temporaire.
		int yTMP = 0;

		// Ouverture d'une nouvelle page.
		doc.newPage();

		// Affichage du titre de la page.
		Paragraph titreE = new Paragraph("ETAPE " + numEtape, FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD));
		doc.add(titreE);

		// Affichage du tableau de l'ctape "numEtape".
		tableauObjectifsEtape(numEtape, doc, canvas, index);

		// Affichage du titre de la partie des questions posces c l'cvaluation
		// de synthcse de l'ctape "numEtape".
		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		if (numEtape == 1) absText("FICHE D'cVALUATION DE SYNTHcSE DE LA PREMIcRE cTAPE", 38, 426, writer, bf, 16);
		else if (numEtape == 2) absText("FICHE D'cVALUATION DE SYNTHcSE DE LA DEUXIcME cTAPE", 38, 460, writer, bf, 16);
		else if (numEtape == 3) absText("FICHE D'cVALUATION DE SYNTHcSE DE LA TROISIcME cTAPE", 38, 438, writer, bf, 16);
		else if (numEtape == 4) absText("FICHE D'cVALUATION DE SYNTHcSE DE LA QUATRIcME cTAPE", 38, 334, writer, bf, 16);
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		// Affichage des questions posces c l'cvaluation de synthcse de l'ctape
		// "numEtape".
		if (numEtape == 1)
		{
			xBaseGroupe = 38;
			yBaseGroupe = 400;
		}
		else if (numEtape == 2)
		{
			xBaseGroupe = 38;
			yBaseGroupe = 434;
		}
		else if (numEtape == 3)
		{
			xBaseGroupe = 38;
			yBaseGroupe = 412;
		}
		else if (numEtape == 4)
		{
			xBaseGroupe = 38;
			yBaseGroupe = 308;
		}
		questionsReponsesSynthese(numEtape, xBaseGroupe, yBaseGroupe, doc, writer, bf, index);

		// Affichage du titre de la partie des rcsultats de l'cvaluation de
		// synthcse de l'ctape "numEtape".
		if (numEtape == 1) yTMP = 190;
		else if (numEtape == 2) yTMP = 282;
		else if (numEtape == 3) yTMP = 224;
		else if (numEtape == 4) yTMP = 132;
		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		absText("RcSULTATS DE L'cVALUATION DE SYNTHcSE", 38, yTMP, writer, bf, 16);
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		// Affichage des rcsultats de l'cvaluation de synthcse de l'ctape
		// "numEtape".
		if (numEtape == 1) yTMP = 86;
		else if (numEtape == 2) yTMP = 178;
		else if (numEtape == 3) yTMP = 120;
		else if (numEtape == 4) yTMP = 28;
		resultatsSynthese(numEtape, yTMP, writer, canvas, bf, kEl, kM, index, fenetre);

	}

	// Affiche le tableau des objectifs d'une ctape c partir des informations de
	// la base de donnces.
	private void tableauObjectifsEtape(int numEtape,Document doc,PdfContentByte canvas,int index)
			throws DocumentException
	{

		KListObject<KObjectif> objectifsE = new KListObject<KObjectif>(KObjectif.class);
		objectifsE.loadFromDb(db, "SELECT * FROM objectif WHERE idETAPE=" + numEtape);
		KListObject<KRealiser> realiserE = new KListObject<KRealiser>(KRealiser.class);

		Phrase p;
		PdfPCell c;

		PdfPTable tOE = new PdfPTable(new float[] { 3, 1, 5 });
		tOE.setTotalWidth(540);

		p = new Phrase("Objectif gcncral : Connactre les situations prcsentant des difficultcs particulicres.",
				FontFactory.getFont(null, 12, Font.BOLD));
		c = new PdfPCell(p);
		c.setColspan(3);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c = positionnerCellule(c, 1.2f, 5, 5, 2, 7);
		tOE.addCell(c);

		p = new Phrase("Objectifs :", FontFactory.getFont(null, 12, Font.BOLD));
		tOE.addCell(placerCelluleCentreeTableauObjectifsEtape(p));

		p = new Phrase(" ");
		tOE.addCell(placerCelluleNonCentreeTableauObjectifsEtape(p));

		p = new Phrase("Observations", FontFactory.getFont(null, 12, Font.BOLD));
		tOE.addCell(placerCelluleCentreeTableauObjectifsEtape(p));

		for (int i = 0; i < objectifsE.count(); i++)
		{

			p = new Phrase(objectifsE.get(i).getLIBELLE_OBJECTIF(), FontFactory.getFont(null, 10, Font.NORMAL));
			tOE.addCell(placerCelluleNonCentreeTableauObjectifsEtape(p));

			realiserE.loadFromDb(db, "SELECT * FROM realiser WHERE idELEVE=" + (index + 1) + " AND idOBJECTIF="
					+ objectifsE.get(i).getId());
			try
			{
				if (realiserE.get(0).getETAT_OBJECTIF() == 0) p = new Phrase("Abordc", FontFactory.getFont(null, 10,
						Font.NORMAL));
				else if (realiserE.get(0).getETAT_OBJECTIF() == 1) p = new Phrase("Traitc", FontFactory.getFont(null,
						10, Font.NORMAL));
				else if (realiserE.get(0).getETAT_OBJECTIF() == 2) p = new Phrase("Assimilc", FontFactory.getFont(null,
						10, Font.NORMAL));
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			tOE.addCell(placerCelluleNonCentreeTableauObjectifsEtape(p));

			try
			{
				p = new Phrase(realiserE.get(0).getOBSERVATION_OBJECTIF(), FontFactory.getFont(null, 10, Font.NORMAL));
			}
			catch (IndexOutOfBoundsException e)
			{
				p = new Phrase(" ", FontFactory.getFont(null, 10, Font.NORMAL));
			}
			tOE.addCell(placerCelluleNonCentreeTableauObjectifsEtape(p));

			realiserE.clear();

		}

		tOE.writeSelectedRows(0, -1, 28, 756, canvas);

	}

	// Affiche les questions des synthcses et les rcponses donnces aux synthcses
	// (cases) c partir des informations de la base de donnces.
	private void questionsReponsesSynthese(int numEtape,int x,int y,Document doc,PdfWriter writer,BaseFont bf,
			int index) throws DocumentException,IOException
	{

		questionsSynthese(numEtape, x, y, doc, writer, bf,index);
		reponsesSynthese(numEtape, x + 228, y, writer, index);

		/*
		 * TODO : Remplacer code en dur et automatiser.
		 * KListObject<KCategorie_questions_synthese> categoriesQS = new
		 * KListObject
		 * <KCategorie_questions_synthese>(KCategorie_questions_synthese.class);
		 * KListObject<KQuestions_synthese> questionsSynthese = new
		 * KListObject<KQuestions_synthese>(KQuestions_synthese.class);
		 * KListObject<KCategorie_questions_synthese> categoriesQStotal = new
		 * KListObject
		 * <KCategorie_questions_synthese>(KCategorie_questions_synthese.class);
		 * categoriesQStotal.loadFromDb(db,
		 * "SELECT * FROM categorie_questions_synthese");
		 * 
		 * int maxCharLigne = 40; int k = 1; boolean finChaine = false; String
		 * chaineActuelle;
		 * 
		 * 
		 * // Boucle sur le numcro de la catcgorie de l'interrogation orale. for
		 * (int i=1; i<categoriesQStotal.count()+1; i++) {
		 * 
		 * // Chargement des donnces de la base de donnces pour la catcgorie "i"
		 * et les objectifs assocics. categoriesQS.loadFromDb(db,
		 * "SELECT * FROM categorie_questions_synthese WHERE id="+i);
		 * questionsSynthese.loadFromDb(db,
		 * "SELECT * FROM questions_synthese WHERE idCATEGORIE_QUESTIONS_SYNTHESE="
		 * +i);
		 * 
		 * // Changement d'ordonnce de dcpart pour la catcgorie "i" de
		 * l'interrogation orale. if (i == 2) y = y-210; else if (i == 3) y =
		 * y-286; else if (i == 4) y = y-166; else if (i == 5) y = y-200; else
		 * if (i == 6) y = y-280; else if (i == 7) y = y-120;
		 * 
		 * // Affichage du titre de la catcgorie "i" de l'interrogation orale.
		 * absText(categoriesQS.get(0).getLIBELLE_CATEGORIE().toUpperCase(),
		 * x-5, y, writer, bf, 10);
		 * 
		 * // Boucle sur le numcro de l'objectif. for (int j=0;
		 * j<questionsSynthese.count(); j++) {
		 * 
		 * reponseSynthese(1, j, x+264, y-2-k*12, writer, db, index);
		 * 
		 * // Le libellc du prochain objectif c ctre affichc. chaineActuelle =
		 * questionsSynthese.get(j).getLIBELLE_QUESTION_SYNTHESE();
		 * 
		 * // Tant que l'objectif n'a pas ctc affichc jusqu'au bout. while
		 * (!(finChaine)) {
		 * 
		 * if (chaineActuelle.length() >= maxCharLigne) {
		 * 
		 * absText(chaineActuelle.substring(0, chaineActuelle.substring(0,
		 * maxCharLigne).lastIndexOf(" ")), x, y-k*12, writer, bf, 10);
		 * chaineActuelle = chaineActuelle.substring(chaineActuelle.substring(0,
		 * maxCharLigne).lastIndexOf(" ")+1);
		 * 
		 * } else {
		 * 
		 * absText(chaineActuelle.substring(0), x, y-k*12, writer, bf, 10);
		 * finChaine = true;
		 * 
		 * }
		 * 
		 * k++;
		 * 
		 * }
		 * 
		 * finChaine = false;
		 * 
		 * }
		 * 
		 * categoriesQS.clear(); questionsSynthese.clear(); k = 1;
		 * 
		 * }
		 */

	}

	// Affiche les questions des synthcses c partir des informations de la base
	// de donnces.
	private void questionsSynthese(int numEtape,int x,int y,Document doc,PdfWriter writer,BaseFont bf,
			int index) throws DocumentException,IOException
	{

		int x2 = x + 282;
		KListObject<KSynthese> syntheses = new KListObject<KSynthese>(KSynthese.class);
		syntheses.loadFromDb(db, "SELECT id FROM synthese WHERE idETAPE=" + numEtape);
		KListObject<KQuestions_synthese> questionsSynthese = new KListObject<KQuestions_synthese>(
				KQuestions_synthese.class);
		questionsSynthese.loadFromDb(db);

		if (numEtape == 1)
		{

			absText("L'cLcVE VA MONTER EN VOITURE :", x, y, writer, bf, 10);
			absText("- il regarde si aucun pneu n'est dcgonflc. .............", x, y - 12, writer, bf, 10);
			absText("IL S'INSTALLE AU POSTE DE CONDUITE :", x, y - 24, writer, bf, 10);
			absText("- il rcgle le sicge, ...................................................", x, y - 36, writer, bf,
					10);
			absText("- il rcgle les rctroviseurs, .......................................", x, y - 48, writer, bf, 10);
			absText("- il boucle la ceinture. ............................................", x, y - 60, writer, bf, 10);
			absText("IL MET LE MOTEUR EN MARCHE :", x, y - 72, writer, bf, 10);
			absText("- sans hcsitation ni erreur", x, y - 84, writer, bf, 10);
			absText("  (cventuellement : le starter). ................................", x, y - 96, writer, bf, 10);
			absText("IL DcMARRE :", x, y - 108, writer, bf, 10);
			absText("- sans caler, ...........................................................", x, y - 120, writer,
					bf, 10);
			absText("- sans c-coup. ........................................................", x, y - 132, writer, bf,
					10);
			absText("IL CHANGE DE VITESSE :", x, y - 144, writer, bf, 10);
			absText("- sans caler, ...........................................................", x, y - 156, writer,
					bf, 10);
			absText("- au moment convenable, ......................................", x, y - 168, writer, bf, 10);
			absText("- sans regarder le levier. ........................................", x, y - 180, writer, bf, 10);

			absText("IL ROULE EN LIGNE DROITE :", x2, y, writer, bf, 10);
			absText("- sans dcviation notable. .................................", x2, y - 12, writer, bf, 10);
			absText("IL TOURNE :", x2, y - 24, writer, bf, 10);
			absText("- sans s'ccarter du bord de la voie. .................", x2, y - 36, writer, bf, 10);
			absText("IL FAIT UNE MARCHE ARRIcRE :", x2, y - 48, writer, bf, 10);
			absText("- EN LIGNE DROITE :", x2, y - 60, writer, bf, 10);
			absText("  - sans dcviation importante. ..........................", x2, y - 72, writer, bf, 10);
			absText("- EN COURBE :", x2, y - 84, writer, bf, 10);
			absText("  - sans dcviation importante. ..........................", x2, y - 96, writer, bf, 10);
			absText("IL FAIT UN DEMI-TOUR :", x2, y - 108, writer, bf, 10);
			absText("- sans hcsitation importante, ...........................", x2, y - 120, writer, bf, 10);
			absText("- sans erreur. ...................................................", x2, y - 132, writer, bf, 10);
			absText("IL S'ARRcTE :", x2, y - 144, writer, bf, 10);
			absText("- sans erreur, ...................................................", x2, y - 156, writer, bf, 10);
			absText("- c l'endroit indiquc. .........................................", x2, y - 168, writer, bf, 10);

		}
		else if (numEtape == 2)
		{

			absText("L'cLcVE CONDUIT :", x, y, writer, bf, 10);
			absText("- il a automatisc la manipulation", x, y - 12, writer, bf, 10);
			absText("  des commandes, .................................................", x, y - 24, writer, bf, 10);
			absText("- il ne roule pas trop lentement, .............................", x, y - 36, writer, bf, 10);
			absText("- il ne roule pas trop vite, .......................................", x, y - 48, writer, bf, 10);
			absText("- il maintient une position convenable", x, y - 60, writer, bf, 10);
			absText("  sur la chaussce, ..................................................", x, y - 72, writer, bf, 10);
			absText("- il maintient les distances de sccuritc, ..................", x, y - 84, writer, bf, 10);
			absText("- il tient compte de la signalisation, ........................", x, y - 96, writer, bf, 10);
			absText("- il choisit la voie convenable", x, y - 108, writer, bf, 10);
			absText("  avant de tourner. .................................................", x, y - 120, writer, bf, 10);

			absText("IL FRANCHIT LES INTERSECTIONS :", x2, y, writer, bf, 10);
			absText("- oc il n'a pas c ccder le passage, ..................", x2, y - 12, writer, bf, 10);
			absText("- oc il doit ccder le passage c droite, ..............", x2, y - 24, writer, bf, 10);
			absText("- oc il doit ccder le passage", x2, y - 36, writer, bf, 10);
			absText("  c droite et c gauche, .....................................", x2, y - 48, writer, bf, 10);
			absText("- oc il doit marquer un temps d'arrct", x2, y - 60, writer, bf, 10);
			absText("  et ccder le passage (stop), ...........................", x2, y - 72, writer, bf, 10);
			absText("- avec feux tricolores. .....................................", x2, y - 84, writer, bf, 10);

			absText("(Cochez les intersections rencontrces.)", x2, y - 108, writer, bf, 10);

		}
		else if (numEtape == 3)
		{

			absText("L'cLcVE CHOISIT SON ITINcRAIRE :", x, y, writer, bf, 10);
			absText("- il sait lire une carte routicre. ................................", x, y - 12, writer, bf, 10);
			absText("L'cLcVE CONDUIT :", x, y - 24, writer, bf, 10);
			absText("- il ajuste sa vitesse aux situations, .......................", x, y - 36, writer, bf, 10);
			absText("- il maintient les distances de sccuritc. ..................", x, y - 48, writer, bf, 10);
			absText("L'cLcVE FRANCHIT UN VIRAGE :", x, y - 60, writer, bf, 10);
			absText("- il adapte sa vitesse, .............................................", x, y - 72, writer, bf, 10);
			absText("- il conserve une trajectoire convenable. ...............", x, y - 84, writer, bf, 10);
			absText("L'cLcVE DcPASSE UN VcHICULE :", x, y - 96, writer, bf, 10);
			absText("- il prend les prccautions nccessaires", x, y - 108, writer, bf, 10);
			absText("  avant de dcpasser, ..............................................", x, y - 120, writer, bf, 10);
			absText("- il revient c droite correctement. ...........................", x, y - 132, writer, bf, 10);
			absText("L'cLcVE SE RANGE DANS UN CRcNEAU :", x, y - 144, writer, bf, 10);
			absText("- il rcussit la manoeuvre. .......................................", x, y - 156, writer, bf, 10);

			absText("L'cLcVE RcPOND AUX QUESTIONS DE L'OBJECTIF", x2, y, writer, bf, 10);
			absText("ALCOOL (i) :", x2, y - 12, writer, bf, 10);
			absText("i1) - qu'appelle-t-on alcoolcmie ? ...................", x2, y - 24, writer, bf, 10);
			absText("i2) - comment mesure-t-on l'alcoolcmie ? ......", x2, y - 36, writer, bf, 10);
			absText("i3) - quel est le taux d'alcoolcmie qui entracne", x2, y - 48, writer, bf, 10);
			absText("      des sanctions graves s'il est atteint", x2, y - 60, writer, bf, 10);
			absText("      ou dcpassc ? ............................................", x2, y - 72, writer, bf, 10);
			absText("i4) - quel est l'influence de l'alcool", x2, y - 84, writer, bf, 10);
			absText("      sur la perception, les gestes,", x2, y - 96, writer, bf, 10);
			absText("      les attitudes ? ...........................................", x2, y - 108, writer, bf, 10);
			absText("i5) - pourquoi les effets de l'alcool sont-ils", x2, y - 120, writer, bf, 10);
			absText("      particulicrement importants ches les", x2, y - 132, writer, bf, 10);
			absText("      conducteurs dcbutants ? ..........................", x2, y - 144, writer, bf, 10);

		}
		else if (numEtape == 4)
		{

			absText("L'cLcVE S'INScRE CORRECTEMENT", x, y, writer, bf, 10);
			absText("DANS LA CIRCULATION :", x, y - 12, writer, bf, 10);
			absText("- en sortant d'un immeuble, ...................................", x, y - 24, writer, bf, 10);
			absText("- en partant de la bordure du trottoir, .....................", x, y - 36, writer, bf, 10);
			absText("- en utilisant une voie d'insertion. ..........................", x, y - 48, writer, bf, 10);
			absText("L'cLcVE CIRCULE EN AGGLOMcRATION :", x, y - 60, writer, bf, 10);
			absText("- il ajuste sa vitesse aux situations, .......................", x, y - 72, writer, bf, 10);
			absText("- sa position sur la chaussce est correcte, ............", x, y - 84, writer, bf, 10);
			absText("- il maintient les distances de sccuritc, ..................", x, y - 96, writer, bf, 10);
			absText("- il tient compte de la prcsence", x, y - 108, writer, bf, 10);
			absText("  des pictons et des cyclistes. ................................", x, y - 120, writer, bf, 10);

			absText("L'cLcVE CONDUIT DANS UNE FILE DE VcHICULES :", x2, y, writer, bf, 10);
			absText("- il choisit la file convenable, ...........................", x2, y - 12, writer, bf, 10);
			absText("- il roule au centre de la voie, ..........................", x2, y - 24, writer, bf, 10);
			absText("- il maintient les distances de sccuritc, ...........", x2, y - 36, writer, bf, 10);
			absText("- il change de file correctement. .....................", x2, y - 48, writer, bf, 10);
			absText("IL RcPOND AUX QUESTIONS :", x2, y - 60, writer, bf, 10);
			absText("- visibilitc rcduite et nuit, .................................", x2, y - 72, writer, bf, 10);
			absText("- adhcrence rcduite, .......................................", x2, y - 84, writer, bf, 10);
			absText("- conduite en montagne, .................................", x2, y - 96, writer, bf, 10);
			absText("- effets de la fatigue, .......................................", x2, y - 108, writer, bf, 10);
			absText("- en cas d'accident, .........................................", x2, y - 120, writer, bf, 10);
			absText("- entretien, dcpannage, ..................................", x2, y - 132, writer, bf, 10);
			absText("- situations d'urgence. ....................................", x2, y - 144, writer, bf, 10);

		}

	}

	// Affiche les rcponses donnces aux synthcses (cases) c partir des
	// informations de la base de donnces.
	private void reponsesSynthese(int numEtape,int x,int y,PdfWriter writer,int index)
			throws MalformedURLException,IndexOutOfBoundsException,NullPointerException,DocumentException,IOException
	{

		int x2 = x + 264;

		if (numEtape == 1)
		{

			reponseSynthese(1, 1, x, y - 12, writer, index);
			reponseSynthese(1, 2, x, y - 36, writer, index);
			reponseSynthese(1, 3, x, y - 48, writer, index);
			reponseSynthese(1, 4, x, y - 60, writer, index);
			reponseSynthese(1, 5, x, y - 96, writer, index);
			reponseSynthese(1, 6, x, y - 120, writer, index);
			reponseSynthese(1, 7, x, y - 132, writer, index);
			reponseSynthese(1, 8, x, y - 156, writer, index);
			reponseSynthese(1, 9, x, y - 168, writer, index);
			reponseSynthese(1, 10, x, y - 180, writer, index);

			reponseSynthese(1, 11, x2, y - 12, writer, index);
			reponseSynthese(1, 12, x2, y - 36, writer, index);
			reponseSynthese(1, 13, x2, y - 72, writer, index);
			reponseSynthese(1, 14, x2, y - 96, writer, index);
			reponseSynthese(1, 15, x2, y - 120, writer, index);
			reponseSynthese(1, 16, x2, y - 132, writer, index);
			reponseSynthese(1, 17, x2, y - 156, writer, index);
			reponseSynthese(1, 18, x2, y - 168, writer, index);

		}
		else if (numEtape == 2)
		{

			reponseSynthese(2, 1, x, y - 24, writer, index);
			reponseSynthese(2, 2, x, y - 36, writer, index);
			reponseSynthese(2, 3, x, y - 48, writer, index);
			reponseSynthese(2, 4, x, y - 72, writer, index);
			reponseSynthese(2, 5, x, y - 84, writer, index);
			reponseSynthese(2, 6, x, y - 96, writer, index);
			reponseSynthese(2, 7, x, y - 120, writer, index);

			reponseSynthese(2, 8, x2, y - 12, writer, index);
			reponseSynthese(2, 9, x2, y - 24, writer, index);
			reponseSynthese(2, 10, x2, y - 48, writer, index);
			reponseSynthese(2, 11, x2, y - 72, writer, index);
			reponseSynthese(2, 12, x2, y - 84, writer, index);

		}
		else if (numEtape == 3)
		{

			reponseSynthese(3, 1, x, y - 12, writer, index);
			reponseSynthese(3, 2, x, y - 36, writer, index);
			reponseSynthese(3, 3, x, y - 48, writer, index);
			reponseSynthese(3, 4, x, y - 72, writer, index);
			reponseSynthese(3, 5, x, y - 84, writer, index);
			reponseSynthese(3, 6, x, y - 120, writer, index);
			reponseSynthese(3, 7, x, y - 132, writer, index);
			reponseSynthese(3, 8, x, y - 156, writer, index);

			reponseSynthese(3, 9, x2, y - 24, writer, index);
			reponseSynthese(3, 10, x2, y - 36, writer, index);
			reponseSynthese(3, 11, x2, y - 72, writer, index);
			reponseSynthese(3, 12, x2, y - 108, writer, index);
			reponseSynthese(3, 13, x2, y - 144, writer, index);

		}
		else if (numEtape == 4)
		{

			reponseSynthese(4, 1, x, y - 24, writer, index);
			reponseSynthese(4, 2, x, y - 36, writer, index);
			reponseSynthese(4, 3, x, y - 48, writer, index);
			reponseSynthese(4, 4, x, y - 72, writer, index);
			reponseSynthese(4, 5, x, y - 84, writer, index);
			reponseSynthese(4, 6, x, y - 96, writer, index);
			reponseSynthese(4, 7, x, y - 120, writer, index);

			reponseSynthese(4, 8, x2, y - 12, writer, index);
			reponseSynthese(4, 9, x2, y - 24, writer, index);
			reponseSynthese(4, 10, x2, y - 36, writer, index);
			reponseSynthese(4, 11, x2, y - 48, writer, index);
			reponseSynthese(4, 12, x2, y - 72, writer, index);
			reponseSynthese(4, 13, x2, y - 84, writer, index);
			reponseSynthese(4, 14, x2, y - 96, writer, index);
			reponseSynthese(4, 15, x2, y - 108, writer, index);
			reponseSynthese(4, 16, x2, y - 120, writer, index);
			reponseSynthese(4, 17, x2, y - 132, writer, index);
			reponseSynthese(4, 18, x2, y - 144, writer, index);

		}

	}

	// Affiche une rcponse donnce c une synthcses (case) c partir des
	// informations de la base de donnces.
	private void reponseSynthese(int numEtape,int question,int abscissePremiereCase,int ordonnee,PdfWriter writer,
			int index) throws DocumentException,MalformedURLException,IOException,
			IndexOutOfBoundsException,NullPointerException
	{

		KListObject<KSynthese> syntheses = new KListObject<KSynthese>(KSynthese.class);
		KSynthese kSy = null;

		try
		{
			syntheses.loadFromDb(db, "SELECT * FROM synthese WHERE idETAPE=" + numEtape);
			kSy = syntheses.get(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			deuxCasesVides(abscissePremiereCase, ordonnee, writer);
		}
		catch (NullPointerException e)
		{
			deuxCasesVides(abscissePremiereCase, ordonnee, writer);
		}

		KListObject<KQuestions_synthese> questionsSyntheseE = new KListObject<KQuestions_synthese>(
				KQuestions_synthese.class);
		KQuestions_synthese kQS = null;

		try
		{
			questionsSyntheseE.loadFromDb(db, "SELECT * FROM questions_synthese WHERE idSYNTHESE=" + kSy.getId());
			kQS = questionsSyntheseE.get(question - 1);
		}
		catch (IndexOutOfBoundsException e)
		{
			deuxCasesVides(abscissePremiereCase, ordonnee, writer);
		}
		catch (NullPointerException e)
		{
			deuxCasesVides(abscissePremiereCase, ordonnee, writer);
		}

		KListObject<KReponse> reponses = new KListObject<KReponse>(KReponse.class);
		KReponse kRep = null;

		try
		{
			reponses.loadFromDb(db, "SELECT * FROM reponse WHERE idELEVE=" + (index + 1) + " AND idQUESTIONS_SYNTHESE="
					+ kQS.getId());
			kRep = reponses.get(0);
		}
		catch (IndexOutOfBoundsException e)
		{
			deuxCasesVides(abscissePremiereCase, ordonnee, writer);
		}
		catch (NullPointerException e)
		{
			deuxCasesVides(abscissePremiereCase, ordonnee, writer);
		}

		try
		{
			if (kRep.getETAT_REPONSE_1() == 1)
			{
				casePleine(abscissePremiereCase, ordonnee, writer);
			}
			else
			{
				caseVide(abscissePremiereCase, ordonnee, writer);
			}
		}
		catch (NullPointerException e)
		{
			caseVide(abscissePremiereCase, ordonnee, writer);
		}

		try
		{
			if (kRep.getETAT_REPONSE_2() == 1)
			{
				casePleine(abscissePremiereCase, ordonnee, writer);
			}
			else
			{
				caseVide(abscissePremiereCase + 20, ordonnee, writer);
			}
		}
		catch (NullPointerException e)
		{
			caseVide(abscissePremiereCase + 20, ordonnee, writer);
		}

	}

	// Affiche les rcsultats de l'cvaluation de synthcse d'une ctape c partir
	// des informations de la base de donnces.
	private void resultatsSynthese(int numEtape,int ordonneeDebut,PdfWriter writer,PdfContentByte canvas,BaseFont bf,
			KEleve kEl,KMoniteur kM,int index,FenetrePrincipale fenetre) throws DocumentException,
			IOException
	{

		KListObject<KSynthese> synthesesE = new KListObject<KSynthese>(KSynthese.class);
		synthesesE.loadFromDb(db, "SELECT * FROM synthese WHERE idETAPE=" + numEtape);

		KListObject<KPasser> PE1 = new KListObject<KPasser>(KPasser.class);
		try
		{
			PE1.loadFromDb(db, "SELECT * FROM passer WHERE idELEVE=" + (index + 1) + " AND idSYNTHESE="
					+ synthesesE.get(0).getId());
		}
		catch (IndexOutOfBoundsException e)
		{

		}

		KListObject<KPasser> PE2 = new KListObject<KPasser>(KPasser.class);
		try
		{
			PE2.loadFromDb(db, "SELECT * FROM passer WHERE idELEVE=" + (index + 1) + " AND idSYNTHESE="
					+ synthesesE.get(1).getId());
		}
		catch (IndexOutOfBoundsException e)
		{

		}

		KListObject<KPasser> PE3 = new KListObject<KPasser>(KPasser.class);
		try
		{
			PE3.loadFromDb(db, "SELECT * FROM passer WHERE idELEVE=" + (index + 1) + " AND idSYNTHESE="
					+ synthesesE.get(2).getId());
		}
		catch (IndexOutOfBoundsException e)
		{

		}

		// FIXME : Problcme du nombres d'heures par rapport c la date
		KListObject<KAssurer_lecon> lecons = kEl.getAssurer_lecons();
		String nbHeuresLecons = compterHeure(lecons);

		canvas.setLineWidth(1);
		canvas.setRGBColorStroke(0x70, 0x93, 0xDB);
		canvas.rectangle(13, ordonneeDebut, 182, 86);
		canvas.rectangle(205, ordonneeDebut, 184, 86);
		canvas.rectangle(399, ordonneeDebut, 182, 86);
		canvas.stroke();

		absText("Premicre cvaluation de synthcse faite", 19, ordonneeDebut + 70, writer, bf, 10);
		Chunk c1S1;
		Chunk c2S1;

		try
		{
			c1S1 = new Chunk("le " + PE1.get(0).getDATE_PASSAGE_SYNTHESE() + " ");
		}
		catch (IndexOutOfBoundsException e)
		{
			c1S1 = new Chunk("le                  ");
		}

		try
		{
			c2S1 = new Chunk("aprcs " + PE1.get(0).getNB_H_TH() + " heure(s)");
		}
		catch (IndexOutOfBoundsException e)
		{
			c2S1 = new Chunk("aprcs            heure(s)");
		}

		absText(c1S1 + "" + c2S1, 19, ordonneeDebut + 55, writer, bf, 10);
		absText("d'enseignement thcorique et", 19, ordonneeDebut + 40, writer, bf, 10);

		try
		{
			absText(nbHeuresLecons + "d'enseignement pratique.", 19, ordonneeDebut + 25, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("           d'enseignement pratique.", 19, ordonneeDebut + 25, writer, bf, 10);
		}

		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		try
		{
			absText("RcSULTAT : " + PE1.get(0).getRESULTAT(), 19, ordonneeDebut + 10, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("RcSULTAT :", 19, ordonneeDebut + 10, writer, bf, 10);
		}
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		absText("Deuxicme cvaluation de synthcse faite", 211, ordonneeDebut + 70, writer, bf, 10);
		Chunk c1S2;
		Chunk c2S2;

		try
		{
			c1S2 = new Chunk("le " + PE2.get(0).getDATE_PASSAGE_SYNTHESE() + " ");
		}
		catch (IndexOutOfBoundsException e)
		{
			c1S2 = new Chunk("le                  ");
		}

		try
		{
			c2S2 = new Chunk("aprcs " + PE2.get(0).getNB_H_TH() + " heure(s)");
		}
		catch (IndexOutOfBoundsException e)
		{
			c2S2 = new Chunk("aprcs            heure(s)");
		}

		absText(c1S2 + "" + c2S2, 211, ordonneeDebut + 55, writer, bf, 10);
		absText("d'enseignement thcorique et", 211, ordonneeDebut + 40, writer, bf, 10);

		try
		{
			absText(nbHeuresLecons + "d'enseignement pratique.", 211, ordonneeDebut + 25, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("           d'enseignement pratique.", 211, ordonneeDebut + 25, writer, bf, 10);
		}
		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		try
		{
			absText("RcSULTAT : " + PE2.get(0).getRESULTAT(), 211, ordonneeDebut + 10, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("RcSULTAT :", 211, ordonneeDebut + 10, writer, bf, 10);
		}
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

		absText("cvaluation de contrcle faite", 405, ordonneeDebut + 70, writer, bf, 10);
		try
		{
			absText("le " + PE3.get(0).getDATE_PASSAGE_SYNTHESE(), 405, ordonneeDebut + 55, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("le", 405, ordonneeDebut + 55, writer, bf, 10);
		}

		try
		{
			if (PE3.get(0).getDATE_PASSAGE_SYNTHESE() != null) absText(
					"par " + kM.getPRENOM_MONITEUR() + " " + kM.getNOM_MONITEUR(), 405, 120, writer, bf, 10);
			else absText("par", 405, ordonneeDebut + 40, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("par", 405, ordonneeDebut + 40, writer, bf, 10);
		}

		bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		try
		{
			absText("RcSULTAT : " + PE3.get(0).getRESULTAT(), 405, ordonneeDebut + 10, writer, bf, 10);
		}
		catch (IndexOutOfBoundsException e)
		{
			absText("RcSULTAT :", 405, ordonneeDebut + 10, writer, bf, 10);
		}
		bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

	}

	// Affiche les questions de l'interrogation orale et les rcponses donnces c
	// l'interrogation orale (cases) c partir des informations de la base de
	// donnces.
	private void questionsReponsesInterrogationOrale(int x,int y,Document doc,PdfWriter writer,BaseFont bf,
			int index) throws DocumentException,IOException
	{

		KListObject<KCategorie_i_o> categoriesIO = new KListObject<KCategorie_i_o>(KCategorie_i_o.class);
		KListObject<KObjectif> objectifsIO = new KListObject<KObjectif>(KObjectif.class);
		KListObject<KCategorie_i_o> categoriesIOtotal = new KListObject<KCategorie_i_o>(KCategorie_i_o.class);
		categoriesIOtotal.loadFromDb(db, "SELECT * FROM categorie_i_o");

		int maxCharLigne = 56;
		int k = 1;
		boolean finChaine = false;
		String chaineActuelle;

		// Boucle sur le numcro de la catcgorie de l'interrogation orale.
		for (int i = 1; i < categoriesIOtotal.count() + 1; i++)
		{

			// Chargement des donnces de la base de donnces pour la catcgorie
			// "i" et les objectifs assocics.
			categoriesIO.loadFromDb(db, "SELECT * FROM categorie_i_o WHERE id=" + i);
			objectifsIO.loadFromDb(db, "SELECT * FROM objectif WHERE idETAPE=5 AND idCATEGORIE_I_O=" + i);

			// Changement d'ordonnce de dcpart pour la catcgorie "i" de
			// l'interrogation orale.
			if (i == 2) y = y - 210;
			else if (i == 3) y = y - 286;
			else if (i == 4) y = y - 166;
			else if (i == 5) y = y - 200;
			else if (i == 6) y = y - 280;
			else if (i == 7) y = y - 120;

			// Affichage du titre de la catcgorie "i" de l'interrogation orale.
			bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			absText(categoriesIO.get(0).getLIBELLE_CATEGORIE().toUpperCase(), x - 5, y, writer, bf, 11);
			bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

			// Boucle sur le numcro de l'objectif.
			for (int j = 0; j < objectifsIO.count(); j++)
			{

				// Passe c la partie droite de la page.
				if ((objectifsIO.get(j).getIdCATEGORIE_I_O() == 4) && (j == 2))
				{
					x = x + 280;
					y = y + 728;
				}

				reponseInterrogationOrale(x - 15, y - 2 - k * 11, j, doc, writer, bf, objectifsIO, index);

				// Le libellc du prochain objectif c ctre affichc.
				chaineActuelle = objectifsIO.get(j).getLIBELLE_OBJECTIF();

				// Tant que l'objectif n'a pas ctc affichc jusqu'au bout.
				while (!(finChaine))
				{

					if (chaineActuelle.length() >= maxCharLigne)
					{

						absText(chaineActuelle.substring(0, chaineActuelle.substring(0, maxCharLigne).lastIndexOf(" ")),
								x, y - k * 11, writer, bf, 10);
						chaineActuelle = chaineActuelle.substring(chaineActuelle.substring(0, maxCharLigne)
								.lastIndexOf(" ") + 1);

					}
					else
					{

						absText(chaineActuelle.substring(0), x, y - k * 11, writer, bf, 10);
						finChaine = true;

					}

					k++;

				}

				finChaine = false;

			}

			categoriesIO.clear();
			objectifsIO.clear();
			k = 1;

		}

	}

	// Affiche une rcponse donnce c l'interrogation orale (case) c partir des
	// informations de la base de donnces.
	private void reponseInterrogationOrale(int x,int y,int j,Document doc,PdfWriter writer,BaseFont bf,
			KListObject<KObjectif> objectifsIO,int index) throws MalformedURLException,IOException,DocumentException
	{

		KListObject<KRealiser> realiserIO = new KListObject<KRealiser>(KRealiser.class);
		realiserIO.loadFromDb(db, "SELECT * FROM realiser WHERE idELEVE=" + (index + 1) + " AND idOBJECTIF="
				+ objectifsIO.get(j).getId());

		try
		{
			if (realiserIO.get(0).getETAT_OBJECTIF() == 1)
			{
				casePleine(x, y, writer);
			}
			else
			{
				caseVide(x, y, writer);
			}
		}
		catch (NullPointerException e)
		{
			caseVide(x, y, writer);
		}
		catch (IndexOutOfBoundsException e)
		{
			caseVide(x, y, writer);
		}

		realiserIO.clear();

	}

	// ----------------------------------------- //
	// ------------------DIVERS----------------- //
	// ----------------------------------------- //

	// Place du texte sur le document PDF, grcce aux coordonnces en position
	// absolue.
	private void absText(String text,int x,int y,PdfWriter writer,BaseFont bf,int size) throws DocumentException,
			IOException
	{
		PdfContentByte cb = writer.getDirectContent();
		cb.saveState();
		cb.beginText();
		cb.moveText(x, y);
		cb.setFontAndSize(bf, size);
		cb.showText(text);
		cb.endText();
		cb.restoreState();
	}

	// Positionne le contenu d'une cellule de tableau, laissant un espace entre
	// les bords et le texte de la cellule.
	private PdfPCell positionnerCellule(PdfPCell c,float lead,int padL,int padR,int padT,int padB)
	{
		c.setLeading(0f, lead);
		c.setPaddingLeft(padL);
		c.setPaddingRight(padR);
		c.setPaddingTop(padT);
		c.setPaddingBottom(padB);
		return c;
	}

	// Place une cellule dont le contenu est centrc horizontalement, dans le
	// tableau des lecons.
	private PdfPCell placerCelluleCentreeTableauLecons(Phrase p)
	{
		PdfPCell c = new PdfPCell(p);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c = positionnerCellule(c, 1.2f, 1, 1, 0, 3);
		return c;
	}

	// Place une cellule dont le contenu est centrc horizontalement, dans le
	// tableau des objectifs d'une ctape.
	private PdfPCell placerCelluleCentreeTableauObjectifsEtape(Phrase p)
	{
		PdfPCell c = new PdfPCell(p);
		c.setHorizontalAlignment(Element.ALIGN_CENTER);
		c = positionnerCellule(c, 1.2f, 5, 5, 2, 7);
		return c;
	}

	// Place une cellule dont le contenu n'est pas centrc horizontalement, dans
	// le tableau des objectifs d'une ctape.
	private PdfPCell placerCelluleNonCentreeTableauObjectifsEtape(Phrase p)
	{
		PdfPCell c = new PdfPCell(p);
		c = positionnerCellule(c, 1.2f, 5, 5, 2, 7);
		return c;
	}

	// Affiche deux cases vides positionnces sur la mcme ordonnce mais un peu
	// espacces au niveau de l'abscisse.
	// Sert pour les rcponses des synthcses des ctapes.
	private void deuxCasesVides(int abscissePremiereCase,int ordonnee,PdfWriter writer) throws MalformedURLException,
			IOException,DocumentException
	{
		caseVide(abscissePremiereCase, ordonnee, writer);
		caseVide(abscissePremiereCase + 20, ordonnee, writer);
	}

	// Affiche une case vide c l'abscisse et c l'ordonnce spccifices.
	private void caseVide(int abscisse,int ordonnee,PdfWriter writer) throws MalformedURLException,IOException,
			DocumentException
	{
		// String strCheckboxUnchecked =
		// "C:\\Documents and Settings\\Florent\\Bureau\\Dernier en date\\gestion_autoecole2\\img\\checkbox_unchecked.gif";
		// File f = new File("images\\checkbox_unchecked.gif");
		Image checkboxUnchecked = Image.getInstance("src/images/checkbox_unchecked.gif");
		checkboxUnchecked.setAbsolutePosition(abscisse, ordonnee);
		writer.getDirectContent().addImage(checkboxUnchecked, true);
	}

	// Affiche une case pleine (cochce) c l'abscisse et c l'ordonnce spccifices.
	private void casePleine(int abscisse,int ordonnee,PdfWriter writer) throws MalformedURLException,IOException,
			DocumentException
	{
		// String strCheckboxChecked =
		// "C:\\Documents and Settings\\Florent\\Bureau\\Dernier en date\\gestion_autoecole2\\img\\checkbox_checked.gif";
		// File f = new File("images\\checkbox_checked.gif");
		Image checkboxChecked = Image.getInstance("src/images/checkbox_checked.gif");
		checkboxChecked.setAbsolutePosition(abscisse, ordonnee);
		writer.getDirectContent().addImage(checkboxChecked, true);
	}

	// Compte le nombre d'heures pratiques et renvoie une chacne formatée en
	// fonction.
	private String compterHeure(KListObject<KAssurer_lecon> lecons)
	{

		String somme;
		int minutes = 0;
		int heures = 0;
		int minutesModulo = 0;
		int minutesDivise = 0;
		int sommeHeure = 0;
		int sommeMinute = 0;

		for (int i = 0; i < lecons.count(); i++)
		{

			// Parser en integer.
			heures = Integer.parseInt(String.valueOf(lecons.get(i).getDUREE_LECON()).substring(0, 1));
			minutes = Integer.parseInt(String.valueOf(lecons.get(i).getDUREE_LECON()).substring(1, 3));

			minutesModulo = minutes % 60;
			minutesDivise = minutes / 60;

			sommeMinute = sommeMinute + minutesModulo;
			sommeHeure = sommeHeure + heures + minutesDivise;

		}

		sommeHeure = sommeHeure + sommeMinute / 60;
		sommeMinute = sommeMinute % 60;

		if (sommeMinute != 0) somme = String.valueOf(sommeHeure) + " heure(s) " + String.valueOf(sommeMinute) + " ";
		else somme = String.valueOf(sommeHeure) + " heure(s) ";

		return somme;

	}

	// Compte le nombre d'heures pratiques et renvoie une chaîne formatée en
	// fonction.
	private String compterHeure(int duree)
	{

		String somme;
		int minutes = 0;
		int heures = 0;
		int minutesModulo = 0;
		int minutesDivise = 0;
		int sommeHeure = 0;
		int sommeMinute = 0;

		// Parser en integer.
		heures = Integer.parseInt(String.valueOf(duree).substring(0, 1));
		minutes = Integer.parseInt(String.valueOf(duree).substring(1, 3));

		minutesModulo = minutes % 60;
		minutesDivise = minutes / 60;

		sommeMinute = sommeMinute + minutesModulo;
		sommeHeure = sommeHeure + heures + minutesDivise;

		sommeHeure = sommeHeure + sommeMinute / 60;
		sommeMinute = sommeMinute % 60;

		if (sommeMinute != 0) somme = String.valueOf(sommeHeure) + "h" + String.valueOf(sommeMinute) + " ";
		else somme = String.valueOf(sommeHeure) + "h00";

		return somme;

	}

}