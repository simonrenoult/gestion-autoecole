DROP DATABASE IF EXISTS MLD_22-11-11;

CREATE DATABASE IF NOT EXISTS MLD_22-11-11;
USE MLD_22-11-11;
# -----------------------------------------------------------------------------
#       TABLE : EVALUATION_SYNTHESE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS EVALUATION_SYNTHESE
 (
   ID_SYNTHESE BIGINT(4) NOT NULL  ,
   ID_ETAPE INTEGER(2) NOT NULL  ,
   LIBELLE_EVALUATION_SYNTHESE CHAR(32) NULL  
   , PRIMARY KEY (ID_SYNTHESE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE EVALUATION_SYNTHESE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_EVALUATION_SYNTHESE_ETAPE
     ON EVALUATION_SYNTHESE (ID_ETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : THEME_SYNTHESE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS THEME_SYNTHESE
 (
   ID_THEME_SYNTHESE INTEGER(2) NOT NULL  ,
   ID_ETAPE INTEGER(2) NOT NULL  ,
   LIBELLE_THEME_SYNTHESE CHAR(64) NOT NULL  
   , PRIMARY KEY (ID_THEME_SYNTHESE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE THEME_SYNTHESE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_THEME_SYNTHESE_ETAPE
     ON THEME_SYNTHESE (ID_ETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : ELEVE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ELEVE
 (
   ID_ELEVE BIGINT(4) NOT NULL  ,
   ID_FORMATION INTEGER(2) NOT NULL  ,
   ID_MONITEUR INTEGER(2) NOT NULL  ,
   NOM_ELEVE CHAR(32) NULL  ,
   PRENOM_ELEVE CHAR(32) NULL  ,
   DATE_DE_NAISS_ELEVE DATE NULL  ,
   MAIL_ELEVE CHAR(255) NULL  ,
   ADRESSE_ELEVE CHAR(255) NULL  ,
   CODE_POSTAL_ELEVE CHAR(255) NULL  ,
   COMMUNE_ELEVE CHAR(32) NULL  ,
   TELEPHONE_ELEVE CHAR(32) NULL  ,
   PROFESSION_ELEVE CHAR(32) NULL  ,
   DATE_EVAL_ELEVE DATE NULL  ,
   RESULTAT_ELEVE_ORAL INTEGER(2) NULL  ,
   VOLUME_HORAIRE_TH_ELEVE INTEGER(2) NULL  ,
   VOLUME_HORAIRE_PRATIQUE_ELEVE INTEGER(2) NULL  ,
   TEST_VU_ELEVE INTEGER(2) NULL  ,
   OBSERVATION_VUE_ELEVE CHAR(255) NULL  ,
   DATE_INSCRIPTION_ELEVE DATE NULL  ,
   DATE_ENREGISTREMENT_ELEVE DATE NULL  ,
   LIVRET_NEPH_ELEVE CHAR(255) NULL  ,
   PHOTO_ELEVE CHAR(255) NULL  
   , PRIMARY KEY (ID_ELEVE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ELEVE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ELEVE_FORMATION
     ON ELEVE (ID_FORMATION ASC);

CREATE  INDEX I_FK_ELEVE_MONITEUR
     ON ELEVE (ID_MONITEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : CATEGORIE_I_O
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CATEGORIE_I_O
 (
   ID_CATEGORIE_I_O SMALLINT(1) NOT NULL  ,
   LIBELLE_CATEGORIE CHAR(255) NULL  
   , PRIMARY KEY (ID_CATEGORIE_I_O) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : QUESTION_SYNTHESE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS QUESTION_SYNTHESE
 (
   ID_QUESTION_SYNTHESE INTEGER(2) NOT NULL  ,
   ID_THEME_SYNTHESE INTEGER(2) NOT NULL  ,
   LIBELLE_QUESTION_SYNTHESE CHAR(255) NOT NULL  
   , PRIMARY KEY (ID_QUESTION_SYNTHESE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE QUESTION_SYNTHESE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_QUESTION_SYNTHESE_THEME_SYNTHESE
     ON QUESTION_SYNTHESE (ID_THEME_SYNTHESE ASC);

# -----------------------------------------------------------------------------
#       TABLE : FORMATION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS FORMATION
 (
   ID_FORMATION INTEGER(2) NOT NULL  ,
   LIBELLE_FORMATION CHAR(255) NULL  
   , PRIMARY KEY (ID_FORMATION) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : AGENDA
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS AGENDA
 (
   ID_AGENDA INTEGER(10) NOT NULL  ,
   DATE_AGENDA DATE NULL  ,
   HEURE_AGENDA TIME NULL  
   , PRIMARY KEY (ID_AGENDA) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : MONITEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS MONITEUR
 (
   ID_MONITEUR INTEGER(2) NOT NULL  ,
   NOM_MONITEUR CHAR(32) NULL  ,
   PRENOM_MONITEUR CHAR(32) NULL  
   , PRIMARY KEY (ID_MONITEUR) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : OBJECTIF
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS OBJECTIF
 (
   ID_OBJECTIF BIGINT(4) NOT NULL  ,
   ID_CATEGORIE_I_O SMALLINT(1) NULL  ,
   ID_ETAPE INTEGER(2) NOT NULL  ,
   LIBELLE_OBJECTIF CHAR(255) NULL  
   , PRIMARY KEY (ID_OBJECTIF) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE OBJECTIF
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_OBJECTIF_CATEGORIE_I_O
     ON OBJECTIF (ID_CATEGORIE_I_O ASC);

CREATE  INDEX I_FK_OBJECTIF_ETAPE
     ON OBJECTIF (ID_ETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : ETAPE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ETAPE
 (
   ID_ETAPE INTEGER(2) NOT NULL  ,
   LIBELLE_ETAPE CHAR(255) NULL  
   , PRIMARY KEY (ID_ETAPE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : COMPTE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS COMPTE
 (
   ID_COMPTE INTEGER(2) NOT NULL  ,
   MOT_DE_PASSE_UTILISATEUR CHAR(32) NULL  
   , PRIMARY KEY (ID_COMPTE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : REALISER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS REALISER
 (
   ID_ELEVE BIGINT(4) NOT NULL  ,
   ID_OBJECTIF BIGINT(4) NOT NULL  ,
   DATE_REALISATION_OBJECTIF DATE NULL  ,
   OBSERVATION_OBJECTIF CHAR(255) NULL  ,
   ETAT_OBJECTIF SMALLINT(1) NULL  
   , PRIMARY KEY (ID_ELEVE,ID_OBJECTIF) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE REALISER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_REALISER_ELEVE
     ON REALISER (ID_ELEVE ASC);

CREATE  INDEX I_FK_REALISER_OBJECTIF
     ON REALISER (ID_OBJECTIF ASC);

# -----------------------------------------------------------------------------
#       TABLE : SUPERVISER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SUPERVISER
 (
   ID_ETAPE INTEGER(2) NOT NULL  ,
   ID_ELEVE BIGINT(4) NOT NULL  ,
   ID_MONITEUR INTEGER(2) NOT NULL  ,
   DATE_PASSAGE DATE NULL  ,
   RESULTAT_PASSAGE BOOL NULL  
   , PRIMARY KEY (ID_ETAPE,ID_ELEVE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE SUPERVISER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_SUPERVISER_ETAPE
     ON SUPERVISER (ID_ETAPE ASC);

CREATE  INDEX I_FK_SUPERVISER_ELEVE
     ON SUPERVISER (ID_ELEVE ASC);

CREATE  INDEX I_FK_SUPERVISER_MONITEUR
     ON SUPERVISER (ID_MONITEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : ASSURER_LECON
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ASSURER_LECON
 (
   ID_ELEVE BIGINT(4) NOT NULL  ,
   ID_AGENDA INTEGER(10) NOT NULL  ,
   ID_MONITEUR INTEGER(2) NOT NULL  ,
   DUREE_LECON INTEGER(2) NULL  ,
   OBSERVATION_LECON CHAR(255) NULL  
   , PRIMARY KEY (ID_ELEVE,ID_AGENDA) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ASSURER_LECON
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ASSURER_LECON_ELEVE
     ON ASSURER_LECON (ID_ELEVE ASC);

CREATE  INDEX I_FK_ASSURER_LECON_AGENDA
     ON ASSURER_LECON (ID_AGENDA ASC);

CREATE  INDEX I_FK_ASSURER_LECON_MONITEUR
     ON ASSURER_LECON (ID_MONITEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : REPONSE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS REPONSE
 (
   ID_QUESTION_SYNTHESE INTEGER(2) NOT NULL  ,
   ID_ELEVE BIGINT(4) NOT NULL  ,
   ETAT_REPONSE_1 SMALLINT(1) NULL  ,
   ETAT_REPONSE_2 SMALLINT(1) NULL  
   , PRIMARY KEY (ID_QUESTION_SYNTHESE,ID_ELEVE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE REPONSE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_REPONSE_QUESTION_SYNTHESE
     ON REPONSE (ID_QUESTION_SYNTHESE ASC);

CREATE  INDEX I_FK_REPONSE_ELEVE
     ON REPONSE (ID_ELEVE ASC);

# -----------------------------------------------------------------------------
#       TABLE : CONTENIR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CONTENIR
 (
   ID_FORMATION INTEGER(2) NOT NULL  ,
   ID_ETAPE INTEGER(2) NOT NULL  
   , PRIMARY KEY (ID_FORMATION,ID_ETAPE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE CONTENIR
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_CONTENIR_FORMATION
     ON CONTENIR (ID_FORMATION ASC);

CREATE  INDEX I_FK_CONTENIR_ETAPE
     ON CONTENIR (ID_ETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : PASSER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PASSER
 (
   ID_ELEVE BIGINT(4) NOT NULL  ,
   ID_SYNTHESE BIGINT(4) NOT NULL  ,
   DATE_PASSAGE_SYNTHESE DATE NULL  ,
   NB_H_TH INTEGER(2) NULL  ,
   RESULTAT INTEGER(1) NULL  
   , PRIMARY KEY (ID_ELEVE,ID_SYNTHESE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PASSER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PASSER_ELEVE
     ON PASSER (ID_ELEVE ASC);

CREATE  INDEX I_FK_PASSER_EVALUATION_SYNTHESE
     ON PASSER (ID_SYNTHESE ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE EVALUATION_SYNTHESE 
  ADD FOREIGN KEY FK_EVALUATION_SYNTHESE_ETAPE (ID_ETAPE)
      REFERENCES ETAPE (ID_ETAPE) ;


ALTER TABLE THEME_SYNTHESE 
  ADD FOREIGN KEY FK_THEME_SYNTHESE_ETAPE (ID_ETAPE)
      REFERENCES ETAPE (ID_ETAPE) ;


ALTER TABLE ELEVE 
  ADD FOREIGN KEY FK_ELEVE_FORMATION (ID_FORMATION)
      REFERENCES FORMATION (ID_FORMATION) ;


ALTER TABLE ELEVE 
  ADD FOREIGN KEY FK_ELEVE_MONITEUR (ID_MONITEUR)
      REFERENCES MONITEUR (ID_MONITEUR) ;


ALTER TABLE QUESTION_SYNTHESE 
  ADD FOREIGN KEY FK_QUESTION_SYNTHESE_THEME_SYNTHESE (ID_THEME_SYNTHESE)
      REFERENCES THEME_SYNTHESE (ID_THEME_SYNTHESE) ;


ALTER TABLE OBJECTIF 
  ADD FOREIGN KEY FK_OBJECTIF_CATEGORIE_I_O (ID_CATEGORIE_I_O)
      REFERENCES CATEGORIE_I_O (ID_CATEGORIE_I_O) ;


ALTER TABLE OBJECTIF 
  ADD FOREIGN KEY FK_OBJECTIF_ETAPE (ID_ETAPE)
      REFERENCES ETAPE (ID_ETAPE) ;


ALTER TABLE REALISER 
  ADD FOREIGN KEY FK_REALISER_ELEVE (ID_ELEVE)
      REFERENCES ELEVE (ID_ELEVE) ;


ALTER TABLE REALISER 
  ADD FOREIGN KEY FK_REALISER_OBJECTIF (ID_OBJECTIF)
      REFERENCES OBJECTIF (ID_OBJECTIF) ;


ALTER TABLE SUPERVISER 
  ADD FOREIGN KEY FK_SUPERVISER_ETAPE (ID_ETAPE)
      REFERENCES ETAPE (ID_ETAPE) ;


ALTER TABLE SUPERVISER 
  ADD FOREIGN KEY FK_SUPERVISER_ELEVE (ID_ELEVE)
      REFERENCES ELEVE (ID_ELEVE) ;


ALTER TABLE SUPERVISER 
  ADD FOREIGN KEY FK_SUPERVISER_MONITEUR (ID_MONITEUR)
      REFERENCES MONITEUR (ID_MONITEUR) ;


ALTER TABLE ASSURER_LECON 
  ADD FOREIGN KEY FK_ASSURER_LECON_ELEVE (ID_ELEVE)
      REFERENCES ELEVE (ID_ELEVE) ;


ALTER TABLE ASSURER_LECON 
  ADD FOREIGN KEY FK_ASSURER_LECON_AGENDA (ID_AGENDA)
      REFERENCES AGENDA (ID_AGENDA) ;


ALTER TABLE ASSURER_LECON 
  ADD FOREIGN KEY FK_ASSURER_LECON_MONITEUR (ID_MONITEUR)
      REFERENCES MONITEUR (ID_MONITEUR) ;


ALTER TABLE REPONSE 
  ADD FOREIGN KEY FK_REPONSE_QUESTION_SYNTHESE (ID_QUESTION_SYNTHESE)
      REFERENCES QUESTION_SYNTHESE (ID_QUESTION_SYNTHESE) ;


ALTER TABLE REPONSE 
  ADD FOREIGN KEY FK_REPONSE_ELEVE (ID_ELEVE)
      REFERENCES ELEVE (ID_ELEVE) ;


ALTER TABLE CONTENIR 
  ADD FOREIGN KEY FK_CONTENIR_FORMATION (ID_FORMATION)
      REFERENCES FORMATION (ID_FORMATION) ;


ALTER TABLE CONTENIR 
  ADD FOREIGN KEY FK_CONTENIR_ETAPE (ID_ETAPE)
      REFERENCES ETAPE (ID_ETAPE) ;


ALTER TABLE PASSER 
  ADD FOREIGN KEY FK_PASSER_ELEVE (ID_ELEVE)
      REFERENCES ELEVE (ID_ELEVE) ;


ALTER TABLE PASSER 
  ADD FOREIGN KEY FK_PASSER_EVALUATION_SYNTHESE (ID_SYNTHESE)
      REFERENCES EVALUATION_SYNTHESE (ID_SYNTHESE) ;

