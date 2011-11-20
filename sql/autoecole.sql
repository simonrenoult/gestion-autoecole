-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Dim 20 Novembre 2011 à 18:31
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

CREATE TABLE IF NOT EXISTS `agenda` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `DATE_AGENDA` date DEFAULT NULL,
  `HEURE_AGENDA` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=46 ;

--
-- Contenu de la table `agenda`
--

INSERT INTO `agenda` (`id`, `DATE_AGENDA`, `HEURE_AGENDA`) VALUES
(1, '2011-11-04', '09:00:00'),
(2, '2011-11-04', '13:30:00'),
(3, '2011-11-04', '09:30:00'),
(4, '2011-11-04', '11:00:00'),
(5, '2011-11-04', '14:30:00'),
(6, '2011-11-04', '15:00:00'),
(7, '2011-11-05', '09:30:00'),
(8, '2011-11-05', '10:00:00'),
(9, '2011-11-05', '11:00:00'),
(10, '2011-11-05', '11:30:00'),
(11, '2011-11-05', '14:30:00'),
(12, '2011-11-05', '15:00:00'),
(13, '2011-11-05', '17:30:00'),
(14, '2011-11-05', '15:30:00'),
(15, '2011-11-06', '09:00:00'),
(16, '2011-11-06', '09:30:00'),
(17, '2011-11-06', '10:00:00'),
(18, '2011-11-06', '12:30:00'),
(19, '2011-11-06', '15:00:00'),
(20, '2011-11-06', '14:30:00'),
(24, '2011-11-09', '11:00:00'),
(25, '2011-11-09', '15:30:00'),
(26, '2011-11-09', '15:30:00'),
(27, '2011-11-09', '11:00:00'),
(28, '2011-11-09', '17:30:00'),
(29, '2011-11-12', '12:00:00'),
(30, '2011-11-12', '13:00:00'),
(31, '2011-11-12', '13:00:00'),
(32, '2011-11-12', '14:00:00'),
(33, '2011-11-12', '14:00:00'),
(34, '2011-11-12', '09:00:00'),
(35, '2011-11-12', '11:00:00'),
(36, '2011-11-12', '15:00:00'),
(37, '2011-11-09', '14:00:00'),
(38, '2011-11-09', '16:00:00'),
(39, '2011-11-09', '16:00:00'),
(40, '2011-11-09', '17:00:00'),
(41, '2011-11-13', '18:00:00'),
(42, '2011-11-09', '11:30:00'),
(43, '2011-11-13', '19:30:00'),
(44, '2011-11-15', '15:20:00'),
(45, '2011-11-14', '15:20:00');

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

--
-- Contenu de la table `assurer_lecon`
--

INSERT INTO `assurer_lecon` (`idELEVE`, `idAGENDA`, `idMONITEUR`, `DUREE_LECON`, `OBSERVATION_LECON`) VALUES
(1, 1, 2, 200, 'prochaine seance : peripherique'),
(1, 2, 4, 130, 'angle mort à revoir'),
(2, 4, 3, 200, 'RAS'),
(2, 7, 2, 100, 'bouchon peripherique, seance annulée'),
(2, 12, 1, 200, 'premier creneau, ++'),
(2, 17, 2, 100, 'question posée : echec total'),
(2, 40, 5, 130, 'bonne conduite'),
(2, 41, 4, 200, 'peripherique ok'),
(2, 43, 4, 200, 'peripherique ok'),
(3, 4, 6, 130, 'peripherique ok'),
(3, 10, 2, 130, 'se gare bien en ville, rassurant en voiture'),
(3, 17, 1, 200, 'aurait dû rester au lit'),
(3, 30, 1, 130, '+++ prudence'),
(3, 32, 2, 100, 'bonne conduite en campagne'),
(3, 36, 2, 100, 'bonne conduite en campagne'),
(4, 19, 4, 100, 'gagne de la confiance'),
(4, 34, 5, 100, 'mister tortue ...'),
(5, 5, 3, 100, 'question posee : echec total'),
(5, 10, 3, 100, 'temps pluvieux, prudent, reactif'),
(5, 14, 1, 200, 'côte de nacre ok'),
(5, 15, 6, 200, 'premier creneau, --'),
(5, 27, 4, 130, 'ifs ok'),
(5, 28, 3, 130, '2° creneau : moyen');

-- --------------------------------------------------------

--
-- Structure de la table `categorie_i_o`
--

CREATE TABLE IF NOT EXISTS `categorie_i_o` (
  `id` smallint(1) NOT NULL AUTO_INCREMENT,
  `LIBELLE_CATEGORIE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `categorie_i_o`
--

INSERT INTO `categorie_i_o` (`id`, `LIBELLE_CATEGORIE`) VALUES
(1, 'Feux arriere'),
(2, 'Clignotants'),
(3, 'Immatriculations'),
(4, 'Pneumatiques'),
(5, 'Niveaux'),
(6, 'Batteries'),
(7, 'Divers');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `MOT_DE_PASSE_UTILISATEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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

--
-- Contenu de la table `eleve`
--

INSERT INTO `eleve` (`id`, `idFORMATION`, `idMONITEUR`, `NOM_ELEVE`, `PRENOM_ELEVE`, `DATE_DE_NAISS_ELEVE`, `MAIL_ELEVE`, `ADRESSE_ELEVE`, `CODE_POSTAL_ELEVE`, `COMMUNE_ELEVE`, `TELEPHONE_ELEVE`, `PROFESSION_ELEVE`, `DATE_EVAL_ELEVE`, `RESULTAT_ELEVE_ORAL`, `VOLUME_HORAIRE_TH_ELEVE`, `VOLUME_HORAIRE_PRATIQUE_ELEVE`, `TEST_VU_ELEVE`, `OBSERVATION_VUE_ELEVE`, `DATE_INSCRIPTION_ELEVE`, `DATE_ENREGISTREMENT_ELEVE`, `LIVRET_NEPH_ELEVE`, `PHOTO_ELEVE`) VALUES
(1, 1, 1, 'Pilie', 'Raphael', '1946-11-10', 'raphael.pilie@gmail.com', 'azert', '50410', 'dfgh', '1234567891', 'Fermier', '2011-11-02', 10, 20, 30, 1, '	', '2011-11-01', '2011-11-01', '123456985', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\prenom_nom.JPG'),
(2, 1, 6, 'Chretienne', 'alexis', '1990-09-10', 'Alexis@gmail.com', 'Le neufbourg', '50410', 'LE CHEFRESNE', '0679691257', 'Etudiant', '2011-11-02', 5, 20, 25, 1, 'RAS', '2011-11-01', '2011-11-01', '1234EFD5', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\alexis_chretienne.jpeg'),
(3, 1, 3, 'renoult', 'simon', '1990-11-01', 'simon.renoult@iutc3.unicaen.fr', 'le port', '50800', 'granville', '0678526589', 'banquier', '2011-11-14', 20, 30, 15, 0, 'pas besoin de test !', '2011-11-08', '2011-11-09', '124546469', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\simon_renoult.jpg'),
(4, 1, 4, 'lucet', 'florent', '1992-11-17', 'florent.lucet@yahoo.fr', 'la maison fleuri', '14000', 'ifs', '0231905566', 'etudiant informatique', '2011-11-19', 26, 20, 32, 1, 'Recommandation : voir un opticien absolument', '2011-11-14', '2011-11-14', '123456985', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\florent_lucet.jpg'),
(5, 1, 5, 'ambroise', 'louis-axel', '1991-11-22', 'l-a@laposte.net', 'rue du neufbourg', '50000', 'saint lô', '0233568874', 'chômage', '2011-11-16', 15, 30, 30, 1, 'RAS', '2011-11-01', '2011-11-10', '1234568', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\louis_axel_ambroise.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `etape`
--

CREATE TABLE IF NOT EXISTS `etape` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `LIBELLE_ETAPE` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `etape`
--

INSERT INTO `etape` (`id`, `LIBELLE_ETAPE`) VALUES
(1, 'Etape 1'),
(2, 'Etape 2'),
(3, 'Etape 3'),
(4, 'Etape 4'),
(5, 'Interrogation orale V2 (exterieur du vehicule)');

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE IF NOT EXISTS `formation` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `LIBELLE_FORMATION` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`id`, `LIBELLE_FORMATION`) VALUES
(1, 'Permis B');

-- --------------------------------------------------------

--
-- Structure de la table `moniteur`
--

CREATE TABLE IF NOT EXISTS `moniteur` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `NOM_MONITEUR` char(32) DEFAULT NULL,
  `PRENOM_MONITEUR` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `moniteur`
--

INSERT INTO `moniteur` (`id`, `NOM_MONITEUR`, `PRENOM_MONITEUR`) VALUES
(1, 'trotoux', 'didier'),
(2, 'jort', 'fabienne'),
(3, 'bourdon', 'francois'),
(4, 'laurent', 'jean-pierre'),
(5, 'levilly', 'marc'),
(6, 'pocrq', 'eric');

-- --------------------------------------------------------

--
-- Structure de la table `objectif`
--

CREATE TABLE IF NOT EXISTS `objectif` (
  `id` bigint(4) NOT NULL AUTO_INCREMENT,
  `idCATEGORIE_I_O` smallint(1) DEFAULT NULL,
  `idETAPE` int(2) NOT NULL,
  `LIBELLE_OBJECTIF` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `I_FK_OBJECTIF_CATEGORIE_I_O` (`idCATEGORIE_I_O`),
  KEY `I_FK_OBJECTIF_ETAPE` (`idETAPE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=34 ;

--
-- Contenu de la table `objectif`
--

INSERT INTO `objectif` (`id`, `idCATEGORIE_I_O`, `idETAPE`, `LIBELLE_OBJECTIF`) VALUES
(1, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement des feux de stop. Quelle est la consequence en cas de panne des feux de stop ?'),
(2, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement des feux de stop. Quelle est leur utilite ?'),
(3, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement du ou des feux de brouillard arriere. Dans quel cas les utilise-t-on ?'),
(4, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant droit, y compris les repetiteurs lateraux s''ils existent. Quand les utilise-t-on ?'),
(5, 3, 5, 'Verifier l''etat de la proprete des plaques d''immatriculation et des dispositifs reflechissants (catadioptres). Quelle est l''utilite dedispositifs reflechissants ?'),
(6, 4, 5, 'Controlez le flanc exterieur sur l''un des pneumatiques avant. En regle generale, dans quelle condition devez-vous controler la pression ?'),
(7, 5, 5, 'Montrez l''orifice de remplissage de l''huile du moteur. Quel est le principal risque d''un manque d''huile moteur ?'),
(8, 6, 5, 'Montrez ou se trouve la batterie du vehicule. Qu''est-ce qui peut provoquer la decharge d''une batterie ?'),
(9, 7, 5, 'Indiquez ou se situe la "securite enfants" sur l''une des portes arriere du vehicule. En regle generale, ou doit etre place un enfant de moins de 10 ans ?'),
(10, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement du ou des feux de brouillard arriere. Peut-on les utiliser par temps de pluie ?'),
(11, 1, 5, 'Controlez l''etat, la proprete et le fonctionnement du ou des feux de marche arriere. A quoi servent-ils ?'),
(12, 1, 5, 'Montrez l''emplacement ou s''effectue le changement des ampoules sur un des deux feux a l''arriere du vehicule. Quelle est la precaution a prendre pour manipuler une ampoule halogene ?'),
(13, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant droit, y compris les repetiteurs lateraux s''ils existent. Quelle est la signification de l''augmentation de la frequence du clignotement au niveau du feu et du voyant au tableau de bord ?'),
(14, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant gauche, y compris les repetiteurs lateraux s''ils existent. Quand les utilise-t-on ?'),
(15, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement du clignotant gauche, y compris les repetiteurs lateraux s''ils existent. Quelle est la signification de l''augmentation de la frequence du clignotement au niveau du feu et du voyant au tableau de bord ?'),
(16, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement des feux de detresse, a l''avant et a l''arriere du vehicule. Quand les utilise-t-on ?'),
(17, 2, 5, 'Controlez l''etat, la proprete et le fonctionnement des feux de detresse, a l''avant et a l''arriere du vehicule. Quelle est la signification de l''augmentation de la frequence du clignotement au niveau du feu et du voyant au tableau de bord ?'),
(18, 3, 5, 'Verifier l''etat et la proprete des plaques d''immatriculation et des dispositifs reflechissants (catadioptres). Quel dispositif est obligatoire pour rendre la plaque d''immatriculation arriere lisible, la nuit ?'),
(19, 3, 5, 'Verifier l''etat et la proprete des plaques d''immatriculation et des dispositifs reflechissants (catadioptres). Une plaque d''immatriculation arriere a fond blanc est-elle autorisee ?'),
(20, 4, 5, 'Controlez le flanc exterieur sur l''un des pneumatiques avant. Quelle peut etre la consequence d''un defaut de parallelisme sur les pneumatiques ?'),
(21, 4, 5, 'Controlez le flanc exterieur sur l''un des pneumatiques avant. Qu''est-ce que l''aquaplanage et quelle peut etre sa consequence ?'),
(22, 4, 5, 'Controlez le flanc exterieur sur l''un des pneumatiques arriere. Qu''est-ce que l''aquaplanage et quelle peut etre sa consequence ?'),
(23, 4, 5, 'Controlez le flanc exterieur sur l''un des pneumatiques arriere. A l''aide de la notice d''utilisation ou de la plaque indicative situee sur le vehicule, indiquez les pressions preconisees pour ce vehicule.'),
(25, NULL, 1, 'Connaitre les principaux organes de la voiture, les principales commandes et le tableau.'),
(26, NULL, 1, 'S''installer au poste de conduite.'),
(27, NULL, 1, 'Regarder autour de soi.'),
(28, NULL, 1, 'Agir sans mettre en danger les autres ni soi-même.'),
(29, NULL, 1, 'Avertir les autres usagers.'),
(30, NULL, 1, 'Démarrer et s''arrêter.'),
(31, NULL, 1, 'Tenir et tourner le volant.'),
(32, NULL, 1, 'Utiliser la boîte de vitesses'),
(33, NULL, 1, 'Diriger la voiture en avant et en arrière, en ligne droite et en courbe en adaptant allure et trajectoire.');

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
  KEY `I_FK_PASSER_SYNTHESE` (`idSYNTHESE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `questions_synthese`
--

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

--
-- Contenu de la table `realiser`
--

INSERT INTO `realiser` (`idELEVE`, `idOBJECTIF`, `DATE_REALISATION_OBJECTIF`, `OBSERVATION_OBJECTIF`, `ETAT_OBJECTIF`) VALUES
(5, 1, NULL, 'test', 1),
(5, 3, NULL, NULL, 1),
(5, 14, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `reponse`
--

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
