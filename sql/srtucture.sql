-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Dim 20 Novembre 2011 à 18:12
-- Version du serveur: 5.5.16
-- Version de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `autoecole`
--
CREATE DATABASE `autoecole` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `autoecole`;

-- --------------------------------------------------------

--
-- Structure de la table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
CREATE TABLE IF NOT EXISTS `agenda` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `DATE_AGENDA` date DEFAULT NULL,
  `HEURE_AGENDA` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

-- --------------------------------------------------------

--
-- Structure de la table `assurer_lecon`
--

DROP TABLE IF EXISTS `assurer_lecon`;
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

DROP TABLE IF EXISTS `categorie_i_o`;
CREATE TABLE IF NOT EXISTS `categorie_i_o` (
  `id` smallint(1) NOT NULL AUTO_INCREMENT,
  `LIBELLE_CATEGORIE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `MOT_DE_PASSE_UTILISATEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

DROP TABLE IF EXISTS `contenir`;
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

DROP TABLE IF EXISTS `eleve`;
CREATE TABLE IF NOT EXISTS `eleve` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

DROP TABLE IF EXISTS `etape`;
CREATE TABLE IF NOT EXISTS `etape` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `LIBELLE_ETAPE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

DROP TABLE IF EXISTS `formation`;
CREATE TABLE IF NOT EXISTS `formation` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `LIBELLE_FORMATION` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

DROP TABLE IF EXISTS `moniteur`;
CREATE TABLE IF NOT EXISTS `moniteur` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `NOM_MONITEUR` char(32) DEFAULT NULL,
  `PRENOM_MONITEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `objectif`
--

DROP TABLE IF EXISTS `objectif`;
CREATE TABLE IF NOT EXISTS `objectif` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `idCATEGORIE_I_O` smallint(1) DEFAULT NULL,
  `idETAPE` int(2) NOT NULL,
  `LIBELLE_OBJECTIF` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_OBJECTIF_CATEGORIE_I_O` (`idCATEGORIE_I_O`),
  KEY `I_FK_OBJECTIF_ETAPE` (`idETAPE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

-- --------------------------------------------------------

--
-- Structure de la table `passer`
--

DROP TABLE IF EXISTS `passer`;
CREATE TABLE IF NOT EXISTS `passer` (
  `idELEVE` bigint(4) NOT NULL,
  `idSYNTHESE` bigint(4) NOT NULL,
  `DATE_PASSAGE_SYNTHESE` date DEFAULT NULL,
  `NB_H_TH` int(2) DEFAULT NULL,
  `RESULTAT` int(1) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idSYNTHESE`),
  KEY `I_FK_PASSER_ELEVE` (`idELEVE`),
  KEY `I_FK_PASSER_SYNTHESE` (`idSYNTHESE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `questions_synthese`
--

DROP TABLE IF EXISTS `questions_synthese`;
CREATE TABLE IF NOT EXISTS `questions_synthese` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `idSYNTHESE` bigint(4) NOT NULL,
  `LIBELLE_QUESTION_SYNTHESE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_QUESTIONS_SYNTHESE_SYNTHESE` (`idSYNTHESE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `realiser`
--

DROP TABLE IF EXISTS `realiser`;
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

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `idQUESTIONS_SYNTHESE` int(2) NOT NULL,
  `idELEVE` bigint(4) NOT NULL,
  `ETAT_REPONSE_1` smallint(1) DEFAULT NULL,
  `ETAT_REPONSE_2` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`idQUESTIONS_SYNTHESE`,`idELEVE`),
  KEY `I_FK_REPONSE_QUESTIONS_SYNTHESE` (`idQUESTIONS_SYNTHESE`),
  KEY `I_FK_REPONSE_ELEVE` (`idELEVE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `superviser`
--

DROP TABLE IF EXISTS `superviser`;
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
-- Structure de la table `synthese`
--

DROP TABLE IF EXISTS `synthese`;
CREATE TABLE IF NOT EXISTS `synthese` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `idETAPE` int(2) NOT NULL,
  `LIBELLE_SYNTHESE` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_SYNTHESE_ETAPE` (`idETAPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `themes_synthese`
--

DROP TABLE IF EXISTS `themes_synthese`;
CREATE TABLE IF NOT EXISTS `themes_synthese` (
  `id` int(3) NOT NULL,
  `idSYNTHESE` bigint(4) NOT NULL,
  `LIBELLE_THEMES_SYNTHESE` char(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_THEMES_SYNTHESE_SYNTHESE` (`idSYNTHESE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD CONSTRAINT `passer_ibfk_2` FOREIGN KEY (`idSYNTHESE`) REFERENCES `synthese` (`id`);

--
-- Contraintes pour la table `questions_synthese`
--
ALTER TABLE `questions_synthese`
  ADD CONSTRAINT `questions_synthese_ibfk_1` FOREIGN KEY (`idSYNTHESE`) REFERENCES `synthese` (`id`);

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
  ADD CONSTRAINT `reponse_ibfk_1` FOREIGN KEY (`idQUESTIONS_SYNTHESE`) REFERENCES `questions_synthese` (`id`),
  ADD CONSTRAINT `reponse_ibfk_2` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`);

--
-- Contraintes pour la table `superviser`
--
ALTER TABLE `superviser`
  ADD CONSTRAINT `superviser_ibfk_1` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`),
  ADD CONSTRAINT `superviser_ibfk_2` FOREIGN KEY (`idELEVE`) REFERENCES `eleve` (`id`),
  ADD CONSTRAINT `superviser_ibfk_3` FOREIGN KEY (`idMONITEUR`) REFERENCES `moniteur` (`id`);

--
-- Contraintes pour la table `synthese`
--
ALTER TABLE `synthese`
  ADD CONSTRAINT `synthese_ibfk_1` FOREIGN KEY (`idETAPE`) REFERENCES `etape` (`id`);

--
-- Contraintes pour la table `themes_synthese`
--
ALTER TABLE `themes_synthese`
  ADD CONSTRAINT `themes_synthese_ibfk_1` FOREIGN KEY (`idSYNTHESE`) REFERENCES `synthese` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
