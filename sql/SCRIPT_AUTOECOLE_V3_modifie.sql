DROP DATABASE IF EXISTS autoecole3;

CREATE DATABASE IF NOT EXISTS autoecole3;
USE autoecole3;

# -----------------------------------------------------------------------------
#       TABLE : QUESTIONS_SYNTHESE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS QUESTIONS_SYNTHESE
 (
   id INTEGER(2) NOT NULL  ,
   idSYNTHESE BIGINT(4) NOT NULL  ,
   LIBELLE_QUESTION_SYNTHESE CHAR(255) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE QUESTIONS_SYNTHESE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_QUESTIONS_SYNTHESE_SYNTHESE
     ON QUESTIONS_SYNTHESE (idSYNTHESE ASC);

# -----------------------------------------------------------------------------
#       TABLE : ELEVE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ELEVE
 (
   id BIGINT(4) NOT NULL  ,
   idFORMATION INTEGER(2) NOT NULL  ,
   idMONITEUR INTEGER(2) NOT NULL  ,
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
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ELEVE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ELEVE_FORMATION
     ON ELEVE (idFORMATION ASC);

CREATE  INDEX I_FK_ELEVE_MONITEUR
     ON ELEVE (idMONITEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : CATEGORIE_I_O
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CATEGORIE_I_O
 (
   id SMALLINT(1) NOT NULL  ,
   LIBELLE_CATEGORIE CHAR(255) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : FORMATION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS FORMATION
 (
   id INTEGER(2) NOT NULL  ,
   LIBELLE_FORMATION CHAR(255) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : AGENDA
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS AGENDA
 (
   id INTEGER(10) NOT NULL  ,
   DATE_AGENDA DATE NULL  ,
   HEURE_AGENDA TIME NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : MONITEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS MONITEUR
 (
   id INTEGER(2) NOT NULL  ,
   NOM_MONITEUR CHAR(32) NULL  ,
   PRENOM_MONITEUR CHAR(32) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : OBJECTIF
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS OBJECTIF
 (
   id BIGINT(4) NOT NULL  ,
   idCATEGORIE SMALLINT(1) NULL  ,
   idETAPE INTEGER(2) NOT NULL  ,
   LIBELLE_OBJECTIF CHAR(255) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE OBJECTIF
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_OBJECTIF_CATEGORIE_I_O
     ON OBJECTIF (idCATEGORIE ASC);

CREATE  INDEX I_FK_OBJECTIF_ETAPE
     ON OBJECTIF (idETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : SYNTHESE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SYNTHESE
 (
   id BIGINT(4) NOT NULL  ,
   idETAPE INTEGER(2) NOT NULL  ,
   LIBELLE_SYNTHESE CHAR(32) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE SYNTHESE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_SYNTHESE_ETAPE
     ON SYNTHESE (idETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : ETAPE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ETAPE
 (
   id INTEGER(2) NOT NULL  ,
   LIBELLE_ETAPE CHAR(255) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : COMPTE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS COMPTE
 (
   id INTEGER(2) NOT NULL  ,
   MOT_DE_PASSE_UTILISATEUR CHAR(32) NULL  
   , PRIMARY KEY (id) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : REALISER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS REALISER
 (
   idELEVE BIGINT(4) NOT NULL  ,
   idOBJECTIF BIGINT(4) NOT NULL  ,
   DATE_REALISATION_OBJECTIF DATE NULL  ,
   OBSERVATION_OBJECTIF CHAR(255) NULL  ,
   ETAT_OBJECTIF SMALLINT(1) NULL  
   , PRIMARY KEY (idELEVE,idOBJECTIF) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE REALISER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_REALISER_ELEVE
     ON REALISER (idELEVE ASC);

CREATE  INDEX I_FK_REALISER_OBJECTIF
     ON REALISER (idOBJECTIF ASC);

# -----------------------------------------------------------------------------
#       TABLE : SUPERVISER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SUPERVISER
 (
   idETAPE INTEGER(2) NOT NULL  ,
   idELEVE BIGINT(4) NOT NULL  ,
   idMONITEUR INTEGER(2) NOT NULL  ,
   DATE_PASSAGE DATE NULL  ,
   RESULTAT_PASSAGE BOOL NULL  
   , PRIMARY KEY (idETAPE,idELEVE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE SUPERVISER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_SUPERVISER_ETAPE
     ON SUPERVISER (idETAPE ASC);

CREATE  INDEX I_FK_SUPERVISER_ELEVE
     ON SUPERVISER (idELEVE ASC);

CREATE  INDEX I_FK_SUPERVISER_MONITEUR
     ON SUPERVISER (idMONITEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : ASSURER_LECON
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ASSURER_LECON
 (
   idELEVE BIGINT(4) NOT NULL  ,
   idAGENDA INTEGER(10) NOT NULL  ,
   idMONITEUR INTEGER(2) NOT NULL  ,
   DUREE_LECON INTEGER(2) NULL  ,
   OBSERVATION_LECON CHAR(255) NULL  
   , PRIMARY KEY (idELEVE,idAGENDA) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ASSURER_LECON
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ASSURER_LECON_ELEVE
     ON ASSURER_LECON (idELEVE ASC);

CREATE  INDEX I_FK_ASSURER_LECON_AGENDA
     ON ASSURER_LECON (idAGENDA ASC);

CREATE  INDEX I_FK_ASSURER_LECON_MONITEUR
     ON ASSURER_LECON (idMONITEUR ASC);

# -----------------------------------------------------------------------------
#       TABLE : REPONSE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS REPONSE
 (
   idQUESTIONS_SYNTHESE INTEGER(2) NOT NULL  ,
   idELEVE BIGINT(4) NOT NULL  ,
   ETAT_REPONSE_1 SMALLINT(1) NULL  ,
   ETAT_REPONSE_2 SMALLINT(1) NULL  
   , PRIMARY KEY (idQUESTIONS_SYNTHESE,idELEVE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE REPONSE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_REPONSE_QUESTIONS_SYNTHESE
     ON REPONSE (idQUESTIONS_SYNTHESE ASC);

CREATE  INDEX I_FK_REPONSE_ELEVE
     ON REPONSE (idELEVE ASC);

# -----------------------------------------------------------------------------
#       TABLE : CONTENIR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CONTENIR
 (
   idFORMATION INTEGER(2) NOT NULL  ,
   idETAPE INTEGER(2) NOT NULL  
   , PRIMARY KEY (idFORMATION,idETAPE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE CONTENIR
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_CONTENIR_FORMATION
     ON CONTENIR (idFORMATION ASC);

CREATE  INDEX I_FK_CONTENIR_ETAPE
     ON CONTENIR (idETAPE ASC);

# -----------------------------------------------------------------------------
#       TABLE : PASSER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PASSER
 (
   idELEVE BIGINT(4) NOT NULL  ,
   idSYNTHESE BIGINT(4) NOT NULL  ,
   DATE_PASSAGE_SYNTHESE DATE NULL  ,
   NB_H_TH INTEGER(2) NULL  ,
   RESULTAT INTEGER(1) NULL  
   , PRIMARY KEY (idELEVE,idSYNTHESE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PASSER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PASSER_ELEVE
     ON PASSER (idELEVE ASC);

CREATE  INDEX I_FK_PASSER_SYNTHESE
     ON PASSER (idSYNTHESE ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE QUESTIONS_SYNTHESE 
  ADD FOREIGN KEY FK_QUESTIONS_SYNTHESE_SYNTHESE (idSYNTHESE)
      REFERENCES SYNTHESE (id) ;


ALTER TABLE ELEVE 
  ADD FOREIGN KEY FK_ELEVE_FORMATION (idFORMATION)
      REFERENCES FORMATION (id) ;


ALTER TABLE ELEVE 
  ADD FOREIGN KEY FK_ELEVE_MONITEUR (idMONITEUR)
      REFERENCES MONITEUR (id) ;


ALTER TABLE OBJECTIF 
  ADD FOREIGN KEY FK_OBJECTIF_CATEGORIE_I_O (idCATEGORIE)
      REFERENCES CATEGORIE_I_O (id) ;


ALTER TABLE OBJECTIF 
  ADD FOREIGN KEY FK_OBJECTIF_ETAPE (idETAPE)
      REFERENCES ETAPE (id) ;


ALTER TABLE SYNTHESE 
  ADD FOREIGN KEY FK_SYNTHESE_ETAPE (idETAPE)
      REFERENCES ETAPE (id) ;


ALTER TABLE REALISER 
  ADD FOREIGN KEY FK_REALISER_ELEVE (idELEVE)
      REFERENCES ELEVE (id) ;


ALTER TABLE REALISER 
  ADD FOREIGN KEY FK_REALISER_OBJECTIF (idOBJECTIF)
      REFERENCES OBJECTIF (id) ;


ALTER TABLE SUPERVISER 
  ADD FOREIGN KEY FK_SUPERVISER_ETAPE (idETAPE)
      REFERENCES ETAPE (id) ;


ALTER TABLE SUPERVISER 
  ADD FOREIGN KEY FK_SUPERVISER_ELEVE (idELEVE)
      REFERENCES ELEVE (id) ;


ALTER TABLE SUPERVISER 
  ADD FOREIGN KEY FK_SUPERVISER_MONITEUR (idMONITEUR)
      REFERENCES MONITEUR (id) ;


ALTER TABLE ASSURER_LECON 
  ADD FOREIGN KEY FK_ASSURER_LECON_ELEVE (idELEVE)
      REFERENCES ELEVE (id) ;


ALTER TABLE ASSURER_LECON 
  ADD FOREIGN KEY FK_ASSURER_LECON_AGENDA (idAGENDA)
      REFERENCES AGENDA (id) ;


ALTER TABLE ASSURER_LECON 
  ADD FOREIGN KEY FK_ASSURER_LECON_MONITEUR (idMONITEUR)
      REFERENCES MONITEUR (id) ;


ALTER TABLE REPONSE 
  ADD FOREIGN KEY FK_REPONSE_QUESTIONS_SYNTHESE (idQUESTIONS_SYNTHESE)
      REFERENCES QUESTIONS_SYNTHESE (id) ;


ALTER TABLE REPONSE 
  ADD FOREIGN KEY FK_REPONSE_ELEVE (idELEVE)
      REFERENCES ELEVE (id) ;


ALTER TABLE CONTENIR 
  ADD FOREIGN KEY FK_CONTENIR_FORMATION (idFORMATION)
      REFERENCES FORMATION (id) ;


ALTER TABLE CONTENIR 
  ADD FOREIGN KEY FK_CONTENIR_ETAPE (idETAPE)
      REFERENCES ETAPE (id) ;


ALTER TABLE PASSER 
  ADD FOREIGN KEY FK_PASSER_ELEVE (idELEVE)
      REFERENCES ELEVE (id) ;


ALTER TABLE PASSER 
  ADD FOREIGN KEY FK_PASSER_SYNTHESE (idSYNTHESE)
      REFERENCES SYNTHESE (id) ;

