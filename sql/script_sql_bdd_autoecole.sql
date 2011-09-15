-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Lun 12 Septembre 2011 à 06:54
-- Version du serveur: 5.5.8
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `autoecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `assurer_lecon`
--

CREATE TABLE IF NOT EXISTS `assurer_lecon` (
  `idELEVE` bigint(4) NOT NULL,
  `idMONITEUR` int(11) NOT NULL,
  `NUM_LECON` int(11) DEFAULT NULL,
  `DATE_LECON` date DEFAULT NULL,
  `HEURE_LECON` int(11) DEFAULT NULL,
  `DUREE_LECON` int(11) DEFAULT NULL,
  `OBSERVATION_LECON` char(255) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idMONITEUR`),
  KEY `I_FK_ASSURER_LECON_ELEVE` (`idELEVE`),
  KEY `I_FK_ASSURER_LECON_MONITEUR` (`idMONITEUR`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `assurer_lecon`
--


-- --------------------------------------------------------

--
-- Structure de la table `categorie_i_o`
--

CREATE TABLE IF NOT EXISTS `categorie_i_o` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `LIBELLE_CATEGORIE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `categorie_i_o`
--

INSERT INTO `categorie_i_o` (`id`, `LIBELLE_CATEGORIE`) VALUES
(1, 'FEUX ARRIERE'),
(2, 'CLIGNOTANTS'),
(3, 'IMMATRICULATION'),
(4, 'PNEUMATIQUES'),
(5, 'NIVEAUX'),
(6, 'BATTERIE'),
(7, 'DIVERS');

-- --------------------------------------------------------

--
-- Structure de la table `champs`
--

CREATE TABLE IF NOT EXISTS `champs` (
  `id` smallint(6) NOT NULL,
  `LIBELLE_ETAT_CHAMPS` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `champs`
--


-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_COMPTE_UTILISATEUR` bigint(4) DEFAULT NULL,
  `MOT_DE_PASSE_UTILISATEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `compte`
--


-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

CREATE TABLE IF NOT EXISTS `contenir` (
  `idFORMATION` int(11) NOT NULL,
  `idETAPE` int(11) NOT NULL,
  PRIMARY KEY (`idFORMATION`,`idETAPE`),
  KEY `I_FK_CONTENIR_FORMATION` (`idFORMATION`),
  KEY `I_FK_CONTENIR_ETAPE` (`idETAPE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `contenir`
--

INSERT INTO `contenir` (`idFORMATION`, `idETAPE`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `idFORMATION` int(11) NOT NULL,
  `idMONITEUR` int(11) NOT NULL,
  `NOM_ELEVE` char(32) DEFAULT NULL,
  `PRENOM_ELEVE` char(32) DEFAULT NULL,
  `DATE_DE_NAISS_ELEVE` date DEFAULT NULL,
  `MAIL_ELEVE` char(255) DEFAULT NULL,
  `ADRESSE_ELEVE` char(255) DEFAULT NULL,
  `CODE_POSTAL_ELEVE` int(11) DEFAULT NULL,
  `COMMUNE_ELEVE` char(32) DEFAULT NULL,
  `TELEPHONE_ELEVE` char(32) DEFAULT NULL,
  `PROFESSION_ELEVE` char(32) DEFAULT NULL,
  `DATE_EVAL_ELEVE` date DEFAULT NULL,
  `RESULTAT_ELEVE_ORAL` int(11) DEFAULT NULL,
  `VOLUME_HORAIRE_TH_ELEVE` int(11) DEFAULT NULL,
  `VOLUME_HORAIRE_PRATIQUE_ELEVE` int(11) DEFAULT NULL,
  `TEST_VU_ELEVE` int(1) DEFAULT NULL,
  `OBSERVATION_VUE_ELEVE` char(255) DEFAULT NULL,
  `DATE_INSCRIPTION_ELEVE` date DEFAULT NULL,
  `DATE_ENREGISTREMENT_ELEVE` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_ELEVE_FORMATION` (`idFORMATION`),
  KEY `I_FK_ELEVE_MONITEUR` (`idMONITEUR`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id`, `idFORMATION`, `idMONITEUR`, `NOM_ELEVE`, `PRENOM_ELEVE`, `DATE_DE_NAISS_ELEVE`, `MAIL_ELEVE`, `ADRESSE_ELEVE`, `CODE_POSTAL_ELEVE`, `COMMUNE_ELEVE`, `TELEPHONE_ELEVE`, `PROFESSION_ELEVE`, `DATE_EVAL_ELEVE`, `RESULTAT_ELEVE_ORAL`, `VOLUME_HORAIRE_TH_ELEVE`, `VOLUME_HORAIRE_PRATIQUE_ELEVE`, `TEST_VU_ELEVE`, `OBSERVATION_VUE_ELEVE`, `DATE_INSCRIPTION_ELEVE`, `DATE_ENREGISTREMENT_ELEVE`) VALUES
(1, 1, 2, 'AMBROISE', 'Louis-Axel', '1991-08-11', 'louisaxel.ambroise@etu.unicaen.fr', '3 village canteloup', 50180, 'Saint-Gilles', '06 45 91 50 27', 'Etudiant', '2001-09-05', 10, 20, 20, 1, 'RAS', '2001-09-08', '2001-09-08'),
(2, 1, 3, 'LAFORGE', 'Francis', '1992-03-16', 'francis.laforge@etu.unicaen.fr', '14 rue des acacias', 50180, 'Hebecrevon', '06 00 00 00 00', 'Etudiant', '2001-09-05', 5, 35, 22, 0, 'RAS', '2001-09-08', '2001-09-08');

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

CREATE TABLE IF NOT EXISTS `etape` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LIBELLE_ETAPE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `etape`
--

INSERT INTO `etape` (`id`, `LIBELLE_ETAPE`) VALUES
(1, 'ETAPE 1'),
(2, 'ETAPE 2'),
(3, 'ETAPE 3'),
(4, 'ETAPE 4'),
(5, 'INTERROGATION ORALE');

-- --------------------------------------------------------

--
-- Structure de la table `evaluation_controle`
--

CREATE TABLE IF NOT EXISTS `evaluation_controle` (
  `id` smallint(6) NOT NULL,
  `LIBELLE_EVALUATION_CONTROLE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evaluation_controle`
--


-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE IF NOT EXISTS `formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LIBELLE_FORMATION` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`id`, `LIBELLE_FORMATION`) VALUES
(1, 'Permis B');

-- --------------------------------------------------------

--
-- Structure de la table `franchir`
--

CREATE TABLE IF NOT EXISTS `franchir` (
  `idELEVE` bigint(4) NOT NULL,
  `idETAPE` int(11) NOT NULL,
  `DATE_FRANCHISSEMENT_ETAPE` date DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idETAPE`),
  KEY `I_FK_FRANCHIR_ELEVE` (`idELEVE`),
  KEY `I_FK_FRANCHIR_ETAPE` (`idETAPE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `franchir`
--


-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

CREATE TABLE IF NOT EXISTS `moniteur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NOM_MONITEUR` char(32) DEFAULT NULL,
  `PRENOM_MONITEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `moniteur`
--

INSERT INTO `moniteur` (`id`, `NOM_MONITEUR`, `PRENOM_MONITEUR`) VALUES
(3, 'LUCET', 'Florent'),
(2, 'CHRETIENNE', 'Alexis'),
(5, 'RENOULT', 'Simon');

-- --------------------------------------------------------

--
-- Structure de la table `objectif`
--

CREATE TABLE IF NOT EXISTS `objectif` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `idCATEGORIE` smallint(6) DEFAULT NULL,
  `idETAPE` int(11) NOT NULL,
  `LIBELLE_OBJECTIF` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_OBJECTIF_CATEGORIE_I_O` (`idCATEGORIE`),
  KEY `I_FK_OBJECTIF_ETAPE` (`idETAPE`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Contenu de la table `objectif`
--

INSERT INTO `objectif` (`id`, `idCATEGORIE`, `idETAPE`, `LIBELLE_OBJECTIF`) VALUES
(1, NULL, 4, 'a) S''inserer dans une circulation rapide'),
(2, NULL, 4, 'b) Conduire en agglomeration dans un circuit dense'),
(3, NULL, 1, 'a) Connaitre les principaux organes de la voiture, les principales commandes et le tableau de bord'),
(4, NULL, 2, 'a) Connaitre les principales regles de la circulation ainsi que la signalisation'),
(5, NULL, 3, 'a) Evaluer les distances et les vitesses'),
(6, NULL, 2, 'b) Tenir compte de la signalisation verticale et horizontale'),
(7, NULL, 2, 'c) Rechercher les indices utiles'),
(8, NULL, 2, 'd) Utiliser toutes les commandes'),
(9, NULL, 2, 'e) Adapter sa vitesse aux situations'),
(10, NULL, 2, 'f) Choisir la voie de circulation'),
(11, NULL, 2, 'g) Maintenir les distances de securite'),
(12, NULL, 2, 'h) Franchir les differents types d''intersection et y changer de direction'),
(13, NULL, 1, 'b) S''installer au poste de conduite'),
(14, NULL, 1, 'c) Regarder autour de soi'),
(24, NULL, 3, 'c) Tenir compte du gabarit de la voiture'),
(23, NULL, 3, 'b) Evaluer les distances d''arret'),
(17, NULL, 1, 'd) Agir sans mettre en danger les autres ni soi-même'),
(18, NULL, 1, 'e) Avertir les autres usagers'),
(19, NULL, 1, 'f) Demarrer et s''arreter'),
(20, NULL, 1, 'g) Tenir et tourner le volant'),
(21, NULL, 1, 'h) Utiliser la boite de vitesse'),
(22, NULL, 1, 'i) Diriger la voiture, en avant et en arriere, en ligne droite et en courbe en adaptant allure et trajectoire'),
(25, NULL, 3, 'd) S''arreter, stationner'),
(26, NULL, 3, 'e) Croiser, depasser, etre depasse'),
(27, NULL, 3, 'f) Passer un virage'),
(28, NULL, 3, 'g) Savoir se comporter a l''egard des diverses categories d''usagers'),
(29, NULL, 3, 'h) Suivre un itineraire'),
(30, NULL, 3, 'i) Avoir des notions sur les effets de l''alcool'),
(31, NULL, 4, 'c) Conduire dans une file de vehicules'),
(32, NULL, 4, 'd) Adapter la conduite a des conditions ou la visibilite est reduite, notamment la nuit'),
(33, NULL, 4, 'e) Adapter la conduite a des conditions ou l''adherance est reduite'),
(34, NULL, 4, 'f) Avoir des notions sur la conduite en montagne'),
(35, NULL, 4, 'g) Avoir des notions sur les effets de la fatigue'),
(36, NULL, 4, 'h) Avoir des notions sur le comportement en cas d''accident'),
(37, NULL, 4, 'i) Avoir des notions concernant l''entretien et le depannage de la voiture'),
(38, NULL, 4, 'j) Avoir des notions concernant les situations d''urgence'),
(39, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement de feux de stop (avec l''assistance de l''accompagnateur). Quelle est la consequence en cas de panne des feux stop ?'),
(40, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement de feux de stop (avec l''assistance de l''accompagnateur). Quelle est leur utilite ?'),
(41, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement du ou des feux de brouillard arriere. Dans quel cas les utilise-t-on ?'),
(42, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement du ou des feux de brouillard arriere. Peut-on les utiliser par temps de pluie ?'),
(43, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement du ou des feux de marche arriere. A quoi servent-ils ?'),
(44, 1, 5, 'Montrez l''emplacement ou s''effectue le changement des ampoules sur un des deux feux a l''arriere du vehicule. Quelle est la precaution a prendre pour manipuler une ampoule halogene ?'),
(45, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant droit, y compris les repetiteurs lateraux s''ils existent. Quand les utilise-t-on ?'),
(46, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant droit, y compris les repetiteurs lateraux s''ils existent. Quelle est la signification de l''augmentation de la frequence du clignotement au niveau du feu et du voyant au tableau de bord ?'),
(47, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant gauche, y compris les repetiteurs lateraux s''ils existent. Quand les utilise-t-on ?'),
(48, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant gauche, y compris les repetiteurs lateraux s''ils existent. Quelle est la signification de l''augmentation de la frequence de clignotement au niveau du feu et du voyant au tableau de bord ?');

-- --------------------------------------------------------

--
-- Structure de la table `passer`
--

CREATE TABLE IF NOT EXISTS `passer` (
  `idELEVE` bigint(4) NOT NULL,
  `idSYNTHESE` bigint(4) NOT NULL,
  `DATE_PASSAGE_SYNTHESE` date DEFAULT NULL,
  `NB_H_TH` int(11) DEFAULT NULL,
  `NB_H_PRATIQUE` int(11) DEFAULT NULL,
  `RESULTAT` int(11) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idSYNTHESE`),
  KEY `I_FK_PASSER_ELEVE` (`idELEVE`),
  KEY `I_FK_PASSER_SYNTHESE` (`idSYNTHESE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `passer`
--


-- --------------------------------------------------------

--
-- Structure de la table `questions_synthese`
--

CREATE TABLE IF NOT EXISTS `questions_synthese` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `LIBELLE_QUESTION_SYNTHESE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `questions_synthese`
--


-- --------------------------------------------------------

--
-- Structure de la table `realiser`
--

CREATE TABLE IF NOT EXISTS `realiser` (
  `idELEVE` bigint(4) NOT NULL,
  `idOBJECTIF` bigint(4) NOT NULL,
  `DATE_REALISATION_OBJECTIF` date DEFAULT NULL,
  `OBSERVATION_OBJECTIF` char(255) DEFAULT NULL,
  `ETAT_OBJECTIF` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`idELEVE`,`idOBJECTIF`),
  KEY `I_FK_REALISER_ELEVE` (`idELEVE`),
  KEY `I_FK_REALISER_OBJECTIF` (`idOBJECTIF`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `realiser`
--


-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

CREATE TABLE IF NOT EXISTS `reponse` (
  `idQUESTIONS_SYNTHESE` int(11) NOT NULL,
  `idETAT_CHAMPS` smallint(6) NOT NULL,
  `idSYNTHESE` bigint(4) NOT NULL,
  `idELEVE` bigint(4) NOT NULL,
  `ETAT_REPONSE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idQUESTIONS_SYNTHESE`,`idETAT_CHAMPS`,`idSYNTHESE`,`idELEVE`),
  KEY `I_FK_REPONSE_QUESTIONS_SYNTHESE` (`idQUESTIONS_SYNTHESE`),
  KEY `I_FK_REPONSE_CHAMPS` (`idETAT_CHAMPS`),
  KEY `I_FK_REPONSE_SYNTHESE` (`idSYNTHESE`),
  KEY `I_FK_REPONSE_ELEVE` (`idELEVE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `reponse`
--


-- --------------------------------------------------------

--
-- Structure de la table `se_rapporter`
--

CREATE TABLE IF NOT EXISTS `se_rapporter` (
  `idEVALUATION_CONTROLE` smallint(6) NOT NULL,
  `idETAPE` int(11) NOT NULL,
  PRIMARY KEY (`idEVALUATION_CONTROLE`,`idETAPE`),
  KEY `I_FK_SE_RAPPORTER_EVALUATION_CONTROLE` (`idEVALUATION_CONTROLE`),
  KEY `I_FK_SE_RAPPORTER_ETAPE` (`idETAPE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `se_rapporter`
--


-- --------------------------------------------------------

--
-- Structure de la table `superviser`
--

CREATE TABLE IF NOT EXISTS `superviser` (
  `idMONITEUR` int(11) NOT NULL,
  `idEVALUATION_CONTROLE` smallint(6) NOT NULL,
  `DATE_PASSAGE` date DEFAULT NULL,
  `RESULTAT_PASSAGE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idMONITEUR`,`idEVALUATION_CONTROLE`),
  KEY `I_FK_SUPERVISER_MONITEUR` (`idMONITEUR`),
  KEY `I_FK_SUPERVISER_EVALUATION_CONTROLE` (`idEVALUATION_CONTROLE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `superviser`
--


-- --------------------------------------------------------

--
-- Structure de la table `synthese`
--

CREATE TABLE IF NOT EXISTS `synthese` (
  `id` bigint(4) NOT NULL,
  `idETAPE` int(11) NOT NULL,
  `LIBELLE_SYNTHESE` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_SYNTHESE_ETAPE` (`idETAPE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `synthese`
--

