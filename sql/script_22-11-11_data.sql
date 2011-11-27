-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Lun 21 Novembre 2011 à 13:53
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

--
-- Contenu de la table `agenda`
-- V
--


--
-- Contenu de la table `categorie_i_o`
-- V
--

INSERT INTO `categorie_i_o` (`id`, `LIBELLE_CATEGORIE`) VALUES
(1, 'Feux arriere'),
(2, 'Clignotants'),
(3, 'Immatriculations'),
(4, 'Pneumatiques'),
(5, 'Niveaux'),
(6, 'Batteries'),
(7, 'Divers');


--
-- Contenu de la table `moniteur`
-- V
--

INSERT INTO `moniteur` (`id`, `NOM_MONITEUR`, `PRENOM_MONITEUR`) VALUES
(1, 'trotoux', 'didier'),
(2, 'jort', 'fabienne'),
(3, 'bourdon', 'francois'),
(4, 'laurent', 'jean-pierre'),
(5, 'levilly', 'marc'),
(6, 'pocrq', 'eric');


--
-- Contenu de la table `formation`
-- V
--

INSERT INTO `formation` (`id`, `LIBELLE_FORMATION`) VALUES
(1, 'Permis B');


--
-- Contenu de la table `etape`
-- V
--

INSERT INTO `etape` (`id`, `LIBELLE_ETAPE`) VALUES
(1, 'Etape 1'),
(2, 'Etape 2'),
(3, 'Etape 3'),
(4, 'Etape 4'),
(5, 'Interrogation orale V2 (exterieur du vehicule)');

--
-- Contenu de la table `objectif`
-- V
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

--
-- Contenu de la table `eleve`
-- V
--

INSERT INTO `eleve` (`id`, `idFORMATION`, `idMONITEUR`, `NOM_ELEVE`, `PRENOM_ELEVE`, `DATE_DE_NAISS_ELEVE`, `MAIL_ELEVE`, `ADRESSE_ELEVE`, `CODE_POSTAL_ELEVE`, `COMMUNE_ELEVE`, `TELEPHONE_ELEVE`, `PROFESSION_ELEVE`, `DATE_EVAL_ELEVE`, `RESULTAT_ELEVE_ORAL`, `VOLUME_HORAIRE_TH_ELEVE`, `VOLUME_HORAIRE_PRATIQUE_ELEVE`, `TEST_VU_ELEVE`, `OBSERVATION_VUE_ELEVE`, `DATE_INSCRIPTION_ELEVE`, `DATE_ENREGISTREMENT_ELEVE`, `LIVRET_NEPH_ELEVE`, `PHOTO_ELEVE`) VALUES
(1, 1, 1, 'Pilie', 'Raphael', '1946-11-10', 'raphael.pilie@gmail.com', 'azert', '50410', 'dfgh', '1234567891', 'Fermier', '2011-11-02', 10, 20, 30, 1, '	', '2011-11-01', '2011-11-01', '123456985', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\prenom_nom.JPG'),
(2, 1, 6, 'Chretienne', 'alexis', '1990-09-10', 'Alexis@gmail.com', 'Le neufbourg', '50410', 'LE CHEFRESNE', '0679691257', 'Etudiant', '2011-11-02', 5, 20, 25, 1, 'RAS', '2011-11-01', '2011-11-01', '1234EFD5', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\alexis_chretienne.jpeg'),
(3, 1, 3, 'renoult', 'simon', '1990-11-01', 'simon.renoult@iutc3.unicaen.fr', 'le port', '50800', 'granville', '0678526589', 'banquier', '2011-11-14', 20, 30, 15, 0, 'pas besoin de test !', '2011-11-08', '2011-11-09', '124546469', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\simon_renoult.jpg'),
(4, 1, 4, 'lucet', 'florent', '1992-11-17', 'florent.lucet@yahoo.fr', 'la maison fleuri', '14000', 'ifs', '0231905566', 'etudiant informatique', '2011-11-19', 26, 20, 32, 1, 'Recommandation : voir un opticien absolument', '2011-11-14', '2011-11-14', '123456985', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\florent_lucet.jpg'),
(5, 1, 5, 'ambroise', 'louis-axel', '1991-11-22', 'l-a@laposte.net', 'rue du neufbourg', '50000', 'saint lô', '0233568874', 'chômage', '2011-11-16', 15, 30, 30, 1, 'RAS', '2011-11-01', '2011-11-10', '1234568', 'C:\\Users\\alex\\Documents\\gestion_autoecole1\\img\\louis_axel_ambroise.jpg');



--
-- Contenu de la table `assurer_lecon`
-- V
--


--
-- Contenu de la table `evaluation_synthese`
-- V
--

INSERT INTO `evaluation_synthese` (`id`, `idETAPE`, `LIBELLE_EVALUATION_SYNTHESE`) VALUES
(1, 1, 'Evaluation de controle');

--
-- Contenu de la table `realiser`
-- V
--

INSERT INTO `realiser` (`idELEVE`, `idOBJECTIF`, `DATE_REALISATION_OBJECTIF`, `OBSERVATION_OBJECTIF`, `ETAT_OBJECTIF`) VALUES
(5, 1, NULL, 'test', 1),
(5, 3, NULL, NULL, 1),
(5, 14, NULL, NULL, 1);

--
-- Contenu de la table `theme_synthese`
--

INSERT INTO `theme_synthese`(`id`,`idETAPE`, `LIBELLE_THEME_SYNTHESE`)
VALUES (1,1,'FAIT BIEN');

--
-- Contenu de la table `question_synthese`
-- V
--

INSERT INTO  `question_synthese` (  `id` , `idTHEME_SYNTHESE` ,  `LIBELLE_QUESTION_SYNTHESE` ) 
VALUES ( 1, 1, 'Le Café' );


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
