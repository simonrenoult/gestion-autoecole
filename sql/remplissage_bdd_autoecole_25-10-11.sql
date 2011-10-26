#-----------------------------------------------------------------------------
#
#	TABLE : COMPTE
#	(`ID_COMPTE`, `ID_COMPTE_UTILISATEUR`, `MOT_DE_PASSE_UTILISATEUR`)
#
# -----------------------------------------------------------------------------

DELETE FROM `autoecole`.`COMPTE`;

#-----------------------------------------------------------------------------
#
#       TABLE : QUESTIONS_SYNTHESE
#	(`ID_QUESTIONS_SYNTHESE`,`LIBELLE_QUESTION_SYNTHESE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`QUESTIONS_SYNTHESE`;

INSERT INTO `QUESTIONS_SYNTHESE` VALUES(1,"Question SYNTHESE 1");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(2,"Question SYNTHESE 2");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(3,"Question SYNTHESE 3");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(4,"Question SYNTHESE 4");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(5,"Question SYNTHESE 5");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(6,"Question SYNTHESE 6");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(7,"Question SYNTHESE 7");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(8,"Question SYNTHESE 8");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(9,"Question SYNTHESE 9");
INSERT INTO `QUESTIONS_SYNTHESE` VALUES(10,"Question SYNTHESE 10");

#-----------------------------------------------------------------------------
#
#       TABLE : CHAMPS
#	(`ID_ETAT_CHAMPS`,`LIBELLE_ETAT_CHAMPS`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`CHAMPS`;

INSERT INTO `CHAMPS` VALUES(1,"champ 1");
INSERT INTO `CHAMPS` VALUES(2,"champ 2");

#-----------------------------------------------------------------------------
#
#       TABLE : ELEVE
#	(`ID_ELEVE`,`ID_FORMATION`,`ID_MONITEUR`,`NOM_ELEVE`,`PRENOM_ELEVE`, 
#	`DATE_DE_NAISS_ELEVE`,`MAIL_ELEVE`,`ADRESSE_ELEVE`,`CODE_POSTAL_ELEVE`,
#	`COMMUNE_ELEVE`,`TELEPHONE_ELEVE`,`PROFESSION_ELEVE`,`DATE_EVAL_ELEVE`,
#	`RESULTAT_ELEVE_ORAL`,`VOLUME_HORAIRE_TH_ELEVE`,`VOLUME_HORAIRE_PRATIQUE_ELEVE`,
#	`TEST_VU_ELEVE`, `OBSERVATION_VUE_ELEVE`,`DATE_INSCRIPTION_ELEVE`, 
#	`DATE_ENREGISTREMENT_ELEVE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`ELEVE`;

INSERT INTO `ELEVE` 
(`ID_ELEVE`,`ID_FORMATION`,`ID_MONITEUR`,`NOM_ELEVE`,`PRENOM_ELEVE`,
`DATE_DE_NAISS_ELEVE`,`MAIL_ELEVE`) 
VALUES
(1,1,1,"RENOULT","Simon",'1990-10-19', "simon.renoult@gmail.com");

INSERT INTO `ELEVE` 
(`ID_ELEVE`,`ID_FORMATION`,`ID_MONITEUR`,`NOM_ELEVE`,`PRENOM_ELEVE`,
`DATE_DE_NAISS_ELEVE`,`MAIL_ELEVE`) 
VALUES
(2,1,1,"CHRETIENNE","Alexis",'1990-08-15', "alexis.chretienne@gmail.com");

INSERT INTO `ELEVE` 
(`ID_ELEVE`,`ID_FORMATION`,`ID_MONITEUR`,`NOM_ELEVE`,`PRENOM_ELEVE`,
`DATE_DE_NAISS_ELEVE`,`MAIL_ELEVE`) 
VALUES
(3,1,2,"AMBROISE","Louis-Axel",'1991-02-23', "none");

INSERT INTO `ELEVE` 
(`ID_ELEVE`,`ID_FORMATION`,`ID_MONITEUR`,`NOM_ELEVE`,`PRENOM_ELEVE`,
`DATE_DE_NAISS_ELEVE`,`MAIL_ELEVE`) 
VALUES
(4,1,3,"LUCET","Florent",'1992-10-26', "florent.lucet@gmail.com");

#-----------------------------------------------------------------------------
#
#       TABLE : CATEGORIE_I_O
#	(`ID_CATEGORIE`,`LIBELLE_CATEGORIE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`CATEGORIE_I_O`;

INSERT INTO `CATEGORIE_I_O` VALUES(1,"feux arrieres"); 
INSERT INTO `CATEGORIE_I_O` VALUES(2,"clignotants");
INSERT INTO `CATEGORIE_I_O` VALUES(3,"poste de conduite");
INSERT INTO `CATEGORIE_I_O` VALUES(4,"conduite");

#-----------------------------------------------------------------------------
#
#       TABLE : FORMATION
#	(`ID_FORMATION`, `LIBELLE_FORMATION`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`FORMATION`;

INSERT INTO `FORMATION` VALUES(1,"Permis B");
INSERT INTO `FORMATION` VALUES(2,"Permis AAC");
INSERT INTO `FORMATION` VALUES(3,"Permis AAC - Conduite accompagnée");

#-----------------------------------------------------------------------------
# 
#	TABLE : MONITEUR
#	(`ID_MONITEUR`, `NOM_MONITEUR`, `PRENOM_MONITEUR`)	
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`MONITEUR`;

INSERT INTO `MONITEUR` VALUES (1, "JOLLY", "Didier");
INSERT INTO `MONITEUR` VALUES (2, "JORT", "Fabienne");
INSERT INTO `MONITEUR` VALUES (3, "LEBRUN", "Michel");
INSERT INTO `MONITEUR` VALUES (4, "BRUTUS", "Jean-Marie");
INSERT INTO `MONITEUR` VALUES (5, "DELHOUMI", "Sylvian");
INSERT INTO `MONITEUR` VALUES (6, "TROTOUX", "Bernard");
INSERT INTO `MONITEUR` VALUES (7, "CHARLES", "Olivier");

#-----------------------------------------------------------------------------
#
#       TABLE : ETAPE
#	(`ID_ETAPE`, `LIBELLE_ETAPE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`ETAPE`;

INSERT INTO `ETAPE` VALUES (1, "Maitrise de la voiture");
INSERT INTO `ETAPE` VALUES (2, "Position, intersection et direction");
INSERT INTO `ETAPE` VALUES (3, "Circulation normale");
INSERT INTO `ETAPE` VALUES (4, "Situations potentiellement difficiles");
INSERT INTO `ETAPE` VALUES (5, "Interrogation orale");
INSERT INTO `ETAPE` VALUES (6, "Manoeuvres");

#-----------------------------------------------------------------------------
#
#       TABLE : SYNTHESE
#	(`ID_SYNTHESE`, `ID_ETAPE`, `LIBELLE_SYNTHESE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`SYNTHESE`;

INSERT INTO `SYNTHESE` VALUES (1,1,"Premiere evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (2,1,"Seconde evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (3,1,"Seconde evaluation de controle");

INSERT INTO `SYNTHESE` VALUES (4,2,"Premiere evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (5,2,"Seconde evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (6,2,"Seconde evaluation de controle");

INSERT INTO `SYNTHESE` VALUES (7,3,"Premiere evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (8,3,"Seconde evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (9,3,"Seconde evaluation de controle");

INSERT INTO `SYNTHESE` VALUES (10,4,"Premiere evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (11,4,"Seconde evaluation de controle");
INSERT INTO `SYNTHESE` VALUES (12,4,"Seconde evaluation de controle");

#-----------------------------------------------------------------------------
#  
#	TABLE : OBJECTIF
#	(`ID_OBJECTIF`, `ID_CATEGORIE`, `ID_ETAPE`, `LIBELLE_OBJECTIF`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`OBJECTIF`;

INSERT INTO `OBJECTIF` VALUES(1, null, 1, "Connaitre les principaux organes de la voiture");
INSERT INTO `OBJECTIF` VALUES(2, null, 1, "S''installer au poste de conduite");
INSERT INTO `OBJECTIF` VALUES(4,null,6,"Créneau");
INSERT INTO `OBJECTIF` VALUES(3, 1, 5, "Controles l''état");

#-----------------------------------------------------------------------------
#
#       TABLE : REALISER
#	(`ID_ELEVE`, `ID_OBJECTIF`, `DATE_REALISATION_OBJECTIF`, 
#	`OBSERVATION_OBJECTIF`, `ETAT_OBJECTIF`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`REALISER`;

INSERT INTO `REALISER` VALUES(1,1,'2011-02-22',"SUCCESS",1);
INSERT INTO `REALISER` VALUES(2,1,'2011-02-27',"FAILURE",2);
INSERT INTO `REALISER` VALUES(2,2,'2011-06-10',"TESTTEST",3);
INSERT INTO `REALISER` VALUES(3,2,'2011-11-30',"looooonnnggg",2);
INSERT INTO `REALISER` VALUES(4,4,'2011-06-08',"",2);

#-----------------------------------------------------------------------------
#
#       TABLE : SUPERVISER
#	(`ID_ETAPE`, `ID_ELEVE`, `DATE_PASSAGE`, `RESULTAT_PASSAGE`, `MONITEUR_ID_MONITEUR`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`SUPERVISER`;

INSERT INTO `SUPERVISER` VALUES (1,1,'2011-03-14', true, 1);
INSERT INTO `SUPERVISER` VALUES (1,2,'2011-12-23', false,2);
INSERT INTO `SUPERVISER` VALUES (2,3,'2011-01-01', true, 1);
INSERT INTO `SUPERVISER` VALUES (3,3,'2011-08-05', true, 3);
INSERT INTO `SUPERVISER` VALUES (4,4,'2011-04-27', false,6);

#-----------------------------------------------------------------------------
#  
#	TABLE : ASSURER_LECON
#	(`ID_ELEVE`, `ID_MONITEUR`, `DATE_AGENDA`, `HEURE`, `NUM_LECON`, 
#	`DUREE_LECON`, `OBSERVATION_LECON`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`ASSURER_LECON`;

INSERT INTO `ASSURER_LECON` VALUES (1,1,'2011-02-13',14,1,1,"bien");
INSERT INTO `ASSURER_LECON` VALUES (1,1,'2011-02-15',14,2,1,"très bien");
INSERT INTO `ASSURER_LECON` VALUES (2,5,'2011-06-18',14,1,1,"bien");
INSERT INTO `ASSURER_LECON` VALUES (2,4,'2011-06-18',14,1,2,"très bien");

#-----------------------------------------------------------------------------
#
#       TABLE : REPONSE
#	(`ID_QUESTIONS_SYNTHESE`, `ID_ETAT_CHAMPS`, `ID_SYNTHESE`, `ID_ELEVE`, 
#	`ETAT_REPONSE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`REPONSE`;

INSERT INTO `REPONSE` VALUES (1,1,1,1,true);
INSERT INTO `REPONSE` VALUES (1,2,1,1,true);
INSERT INTO `REPONSE` VALUES (2,1,1,1,true);
INSERT INTO `REPONSE` VALUES (2,2,1,1,true);
INSERT INTO `REPONSE` VALUES (3,1,1,1,true);
INSERT INTO `REPONSE` VALUES (3,2,1,1,true);
INSERT INTO `REPONSE` VALUES (4,1,1,1,true);
INSERT INTO `REPONSE` VALUES (4,2,1,1,true);
INSERT INTO `REPONSE` VALUES (5,1,1,1,true);
INSERT INTO `REPONSE` VALUES (5,2,1,1,true);
INSERT INTO `REPONSE` VALUES (6,1,1,1,true);
INSERT INTO `REPONSE` VALUES (6,2,1,1,true);
INSERT INTO `REPONSE` VALUES (7,1,1,1,true);
INSERT INTO `REPONSE` VALUES (7,2,1,1,true);
INSERT INTO `REPONSE` VALUES (8,1,1,1,true);
INSERT INTO `REPONSE` VALUES (8,2,1,1,true);
INSERT INTO `REPONSE` VALUES (9,1,1,1,true);
INSERT INTO `REPONSE` VALUES (9,2,1,1,true);
INSERT INTO `REPONSE` VALUES (10,1,1,1,true);
INSERT INTO `REPONSE` VALUES (10,2,1,1,true);

#-----------------------------------------------------------------------------
#
#       TABLE : CONTENIR
#	(`ID_FORMATION`, `ID_ETAPE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`CONTENIR`;

INSERT INTO `CONTENIR` VALUES (1,1);
INSERT INTO `CONTENIR` VALUES (1,2);
INSERT INTO `CONTENIR` VALUES (1,3);
INSERT INTO `CONTENIR` VALUES (1,4);
INSERT INTO `CONTENIR` VALUES (1,5);
INSERT INTO `CONTENIR` VALUES (1,6);

INSERT INTO `CONTENIR` VALUES (2,1);
INSERT INTO `CONTENIR` VALUES (2,2);
INSERT INTO `CONTENIR` VALUES (2,3);
INSERT INTO `CONTENIR` VALUES (2,4);
INSERT INTO `CONTENIR` VALUES (2,5);
INSERT INTO `CONTENIR` VALUES (2,6);

INSERT INTO `CONTENIR` VALUES (3,1);
INSERT INTO `CONTENIR` VALUES (3,2);
INSERT INTO `CONTENIR` VALUES (3,3);
INSERT INTO `CONTENIR` VALUES (3,4);
INSERT INTO `CONTENIR` VALUES (3,5);
INSERT INTO `CONTENIR` VALUES (3,6);

#-----------------------------------------------------------------------------
#
#       TABLE : PASSER
#	(`ID_ELEVE`, `ID_SYNTHESE`, `DATE_PASSAGE_SYNTHESE`, `NB_H_TH`, 
#	`NB_H_PRATIQUE`, `RESULTAT`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`PASSER`;

INSERT INTO `PASSER` VALUES(1,1,'2011-05-02',1,1,1);
INSERT INTO `PASSER` VALUES(1,2,'2011-06-08',10,10,1);
INSERT INTO `PASSER` VALUES(1,3,'2011-07-14',15,15,2);

INSERT INTO `PASSER` VALUES(2,1,'2011-05-02',1,1,1);
INSERT INTO `PASSER` VALUES(2,2,'2011-06-08',10,10,1);
INSERT INTO `PASSER` VALUES(2,3,'2011-07-14',15,15,2);

INSERT INTO `PASSER` VALUES(3,1,'2011-05-02',1,1,1);
INSERT INTO `PASSER` VALUES(3,2,'2011-06-08',10,10,1);
INSERT INTO `PASSER` VALUES(3,3,'2011-07-14',15,15,2);

#-----------------------------------------------------------------------------
#
#       TABLE : AGENDA
#	(`DATE_AGENDA`,`HEURE`)
#
#-----------------------------------------------------------------------------

DELETE FROM `autoecole`.`AGENDA`;

INSERT INTO `AGENDA` VALUES('2011-07-14',15);
INSERT INTO `AGENDA` VALUES('2011-07-14',17);
INSERT INTO `AGENDA` VALUES('2011-07-18',15);
INSERT INTO `AGENDA` VALUES('2011-07-18',17);
INSERT INTO `AGENDA` VALUES('2011-07-22',15);
INSERT INTO `AGENDA` VALUES('2011-07-22',17);
INSERT INTO `AGENDA` VALUES('2011-07-26',15);
INSERT INTO `AGENDA` VALUES('2011-07-26',17);
INSERT INTO `AGENDA` VALUES('2011-07-30',15);
INSERT INTO `AGENDA` VALUES('2011-07-30',17);
INSERT INTO `AGENDA` VALUES('2011-08-01',15);
INSERT INTO `AGENDA` VALUES('2011-08-01',17);
