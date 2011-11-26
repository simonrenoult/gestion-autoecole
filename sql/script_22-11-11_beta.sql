-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Sam 26 Novembre 2011 à 14:17
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `autoecole3`
--

-- --------------------------------------------------------

--
-- Structure de la table `agenda`
--

CREATE TABLE IF NOT EXISTS `agenda` (
  `id` int(10) NOT NULL,
  `DATE_AGENDA` date DEFAULT NULL,
  `HEURE_AGENDA` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `assurer_lecon`
--

CREATE TABLE IF NOT EXISTS `assurer_lecon` (
  `idELEVE` bigint(4) NOT NULL,
  `idAGENDA` int(10) NOT NULL,
  `idMONITEUR` int(2) NOT NULL,
  `DUREE_LECON` int(2) DEFAULT NULL,
  `OBSERVATION_LECON` char(255) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idAGENDA`),
  KEY `I_FK_ASSURER_LECON_ELEVE` (`idELEVE`),
  KEY `I_FK_ASSURER_LECON_AGENDA` (`idAGENDA`),
  KEY `I_FK_ASSURER_LECON_MONITEUR` (`idMONITEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie_i_o`
--

CREATE TABLE IF NOT EXISTS `categorie_i_o` (
  `id` smallint(1) NOT NULL,
  `LIBELLE_CATEGORIE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(2) NOT NULL,
  `MOT_DE_PASSE_UTILISATEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

CREATE TABLE IF NOT EXISTS `contenir` (
  `idFORMATION` int(2) NOT NULL,
  `idETAPE` int(2) NOT NULL,
  PRIMARY KEY (`idFORMATION`,`idETAPE`),
  KEY `I_FK_CONTENIR_FORMATION` (`idFORMATION`),
  KEY `I_FK_CONTENIR_ETAPE` (`idETAPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` bigint(4) NOT NULL,
  `idFORMATION` int(2) NOT NULL,
  `idMONITEUR` int(2) NOT NULL,
  `NOM_ELEVE` char(32) DEFAULT NULL,
  `PRENOM_ELEVE` char(32) DEFAULT NULL,
  `DATE_DE_NAISS_ELEVE` date DEFAULT NULL,
  `MAIL_ELEVE` char(255) DEFAULT NULL,
  `ADRESSE_ELEVE` char(255) DEFAULT NULL,
  `CODE_POSTAL_ELEVE` char(255) DEFAULT NULL,
  `COMMUNE_ELEVE` char(32) DEFAULT NULL,
  `TELEPHONE_ELEVE` char(32) DEFAULT NULL,
  `PROFESSION_ELEVE` char(32) DEFAULT NULL,
  `DATE_EVAL_ELEVE` date DEFAULT NULL,
  `RESULTAT_ELEVE_ORAL` int(2) DEFAULT NULL,
  `VOLUME_HORAIRE_TH_ELEVE` int(2) DEFAULT NULL,
  `VOLUME_HORAIRE_PRATIQUE_ELEVE` int(2) DEFAULT NULL,
  `TEST_VU_ELEVE` int(2) DEFAULT NULL,
  `OBSERVATION_VUE_ELEVE` char(255) DEFAULT NULL,
  `DATE_INSCRIPTION_ELEVE` date DEFAULT NULL,
  `DATE_ENREGISTREMENT_ELEVE` date DEFAULT NULL,
  `LIVRET_NEPH_ELEVE` char(255) DEFAULT NULL,
  `PHOTO_ELEVE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_ELEVE_FORMATION` (`idFORMATION`),
  KEY `I_FK_ELEVE_MONITEUR` (`idMONITEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

CREATE TABLE IF NOT EXISTS `etape` (
  `id` int(2) NOT NULL,
  `LIBELLE_ETAPE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evaluation_synthese`
--

CREATE TABLE IF NOT EXISTS `evaluation_synthese` (
  `id` bigint(4) NOT NULL,
  `idETAPE` int(2) NOT NULL,
  `LIBELLE_EVALUATION_SYNTHESE` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_EVALUATION_SYNTHESE_ETAPE` (`idETAPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE IF NOT EXISTS `formation` (
  `id` int(2) NOT NULL,
  `LIBELLE_FORMATION` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

CREATE TABLE IF NOT EXISTS `moniteur` (
  `id` int(2) NOT NULL,
  `NOM_MONITEUR` char(32) DEFAULT NULL,
  `PRENOM_MONITEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `objectif`
--

CREATE TABLE IF NOT EXISTS `objectif` (
  `id` bigint(4) NOT NULL,
  `idCATEGORIE_I_O` smallint(1) DEFAULT NULL,
  `idETAPE` int(2) NOT NULL,
  `LIBELLE_OBJECTIF` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_OBJECTIF_CATEGORIE_I_O` (`idCATEGORIE_I_O`),
  KEY `I_FK_OBJECTIF_ETAPE` (`idETAPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `passer`
--

CREATE TABLE IF NOT EXISTS `passer` (
  `idELEVE` bigint(4) NOT NULL,
  `idSYNTHESE` bigint(4) NOT NULL,
  `DATE_PASSAGE_SYNTHESE` date DEFAULT NULL,
  `NB_H_TH` int(2) DEFAULT NULL,
  `RESULTAT` int(1) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idSYNTHESE`),
  KEY `I_FK_PASSER_ELEVE` (`idELEVE`),
  KEY `I_FK_PASSER_EVALUATION_SYNTHESE` (`idSYNTHESE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `question_synthese`
--

CREATE TABLE IF NOT EXISTS `question_synthese` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `idTHEME_SYNTHESE` int(2) NOT NULL,
  `LIBELLE_QUESTION_SYNTHESE` char(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_QUESTION_SYNTHESE_THEME_SYNTHESE` (`idTHEME_SYNTHESE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

-- --------------------------------------------------------

--
-- Structure de la table `realiser`
--

CREATE TABLE IF NOT EXISTS `realiser` (
  `idELEVE` bigint(4) NOT NULL,
  `idOBJECTIF` bigint(4) NOT NULL,
  `DATE_REALISATION_OBJECTIF` date DEFAULT NULL,
  `OBSERVATION_OBJECTIF` char(255) DEFAULT NULL,
  `ETAT_OBJECTIF` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idOBJECTIF`),
  KEY `I_FK_REALISER_ELEVE` (`idELEVE`),
  KEY `I_FK_REALISER_OBJECTIF` (`idOBJECTIF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE IF NOT EXISTS `reponse` (
  `idQUESTION_SYNTHESE` int(2) NOT NULL,
  `idELEVE` bigint(4) NOT NULL,
  `ETAT_REPONSE_1` smallint(1) DEFAULT NULL,
  `ETAT_REPONSE_2` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`idQUESTION_SYNTHESE`,`idELEVE`),
  KEY `I_FK_REPONSE_QUESTION_SYNTHESE` (`idQUESTION_SYNTHESE`),
  KEY `I_FK_REPONSE_ELEVE` (`idELEVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `superviser`
--

CREATE TABLE IF NOT EXISTS `superviser` (
  `idETAPE` int(2) NOT NULL,
  `idELEVE` bigint(4) NOT NULL,
  `idMONITEUR` int(2) NOT NULL,
  `DATE_PASSAGE` date DEFAULT NULL,
  `RESULTAT_PASSAGE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idETAPE`,`idELEVE`),
  KEY `I_FK_SUPERVISER_ETAPE` (`idETAPE`),
  KEY `I_FK_SUPERVISER_ELEVE` (`idELEVE`),
  KEY `I_FK_SUPERVISER_MONITEUR` (`idMONITEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `theme_synthese`
--

CREATE TABLE IF NOT EXISTS `theme_synthese` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `idETAPE` int(2) NOT NULL,
  `LIBELLE_THEME_SYNTHESE` char(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_THEME_SYNTHESE_ETAPE` (`idETAPE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `assurer_lecon`
--
ALTER TABLE `assurer_lecon`
  ADD CONSTRAINT `assurer_lecon_ibfk_1` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`),
  ADD CONSTRAINT `assurer_lecon_ibfk_2` FOREIGN KEY (`idAGENDA`) REFERENCES `agenda` (`id`),
  ADD CONSTRAINT `assurer_lecon_ibfk_3` FOREIGN KEY (`idMONITEUR`) REFERENCES `moniteur` (`id`);

--
-- Contraintes pour la table `contenir`
--
ALTER TABLE `contenir`
  ADD CONSTRAINT `contenir_ibfk_1` FOREIGN KEY (`idFORMATION`) REFERENCES `formation` (`id`),
  ADD CONSTRAINT `contenir_ibfk_2` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`);

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `eleve_ibfk_1` FOREIGN KEY (`idFORMATION`) REFERENCES `formation` (`id`),
  ADD CONSTRAINT `eleve_ibfk_2` FOREIGN KEY (`idMONITEUR`) REFERENCES `moniteur` (`id`);

--
-- Contraintes pour la table `evaluation_synthese`
--
ALTER TABLE `evaluation_synthese`
  ADD CONSTRAINT `evaluation_synthese_ibfk_1` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`);

--
-- Contraintes pour la table `objectif`
--
ALTER TABLE `objectif`
  ADD CONSTRAINT `objectif_ibfk_1` FOREIGN KEY (`idCATEGORIE_I_O`) REFERENCES `categorie_i_o` (`id`),
  ADD CONSTRAINT `objectif_ibfk_2` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`);

--
-- Contraintes pour la table `passer`
--
ALTER TABLE `passer`
  ADD CONSTRAINT `passer_ibfk_1` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`),
  ADD CONSTRAINT `passer_ibfk_2` FOREIGN KEY (`idSYNTHESE`) REFERENCES `evaluation_synthese` (`id`);

--
-- Contraintes pour la table `question_synthese`
--
ALTER TABLE `question_synthese`
  ADD CONSTRAINT `question_synthese_ibfk_1` FOREIGN KEY (`idTHEME_SYNTHESE`) REFERENCES `theme_synthese` (`id`);

--
-- Contraintes pour la table `realiser`
--
ALTER TABLE `realiser`
  ADD CONSTRAINT `realiser_ibfk_1` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`),
  ADD CONSTRAINT `realiser_ibfk_2` FOREIGN KEY (`idOBJECTIF`) REFERENCES `objectif` (`id`);

--
-- Contraintes pour la table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`idQUESTION_SYNTHESE`) REFERENCES `question_synthese` (`id`),
  ADD CONSTRAINT `reponse_ibfk_2` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`);

--
-- Contraintes pour la table `superviser`
--
ALTER TABLE `superviser`
  ADD CONSTRAINT `superviser_ibfk_1` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`),
  ADD CONSTRAINT `superviser_ibfk_2` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`),
  ADD CONSTRAINT `superviser_ibfk_3` FOREIGN KEY (`idMONITEUR`) REFERENCES `moniteur` (`id`);

--
-- Contraintes pour la table `theme_synthese`
--
ALTER TABLE `theme_synthese`
  ADD CONSTRAINT `theme_synthese_ibfk_1` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
