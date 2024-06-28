-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 01 août 2021 à 19:31
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lms`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherent`
--

DROP TABLE IF EXISTS `adherent`;
CREATE TABLE IF NOT EXISTS `adherent` (
  `Nom` varchar(30) NOT NULL,
  `Telephone` text,
  `Adressemail` varchar(40) NOT NULL,
  `Livre` text,
  `regularité` int(11) DEFAULT NULL,
  PRIMARY KEY (`Nom`,`Adressemail`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherent`
--

INSERT INTO `adherent` (`Nom`, `Telephone`, `Adressemail`, `Livre`, `regularité`) VALUES
('Harold', '555555', 'mola', 'boruto', 6),
('arthur', '655586299', 'atlarthur04@gmail.com', 'boruto', 12),
('asadgfd', '454', 'fdfd', 'le seed', 8);

-- --------------------------------------------------------

--
-- Structure de la table `emprunts`
--

DROP TABLE IF EXISTS `emprunts`;
CREATE TABLE IF NOT EXISTS `emprunts` (
  `Nom_Client` varchar(30) DEFAULT NULL,
  `Telephone` text,
  `Nom_livre` varchar(30) NOT NULL,
  `Date_emprunt` date DEFAULT NULL,
  `Date_remise` date DEFAULT NULL,
  `Statut` text,
  `Quantite` int(11) DEFAULT NULL,
  KEY `fk_ort` (`Nom_livre`),
  KEY `lien` (`Nom_Client`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `emprunts`
--

INSERT INTO `emprunts` (`Nom_Client`, `Telephone`, `Nom_livre`, `Date_emprunt`, `Date_remise`, `Statut`, `Quantite`) VALUES
('Harold', '212', 'naruto shippuden', '2021-07-10', '2021-07-22', 'Recupéré', 1),
('Harold', '12', 'le seed', '2021-07-10', '2021-07-31', 'Recupéré', 1),
('asadgfd', '3121', 'le seed', '2021-07-10', '2021-07-17', 'Recupéré', 3),
('asadgfd', '3121', 'le seed', '2021-07-10', '2021-07-17', 'Recupéré', 1),
('asadgfd', '3121', 'boruto', '2021-07-10', '2021-07-17', 'Emprunté', 1),
('asadgfd', '3121', 'naruto shippuden', '2021-07-10', '2021-07-17', 'Emprunté', 1),
('Harold', '3121', 'le seed', '2021-07-10', '2021-07-17', 'Emprunté', 1),
('Harold', '121', 'jujutsu kaisen', '2021-07-10', '2021-07-17', 'Recupéré', 2),
('Harold', '121', 'jujutsu kaisen', '2021-07-10', '2021-07-17', 'Emprunté', 1),
('Harold', '121', 'naruto shippuden', '2021-07-10', '2021-07-17', 'Emprunté', 1),
('arthur', '3232', 'le seed', '2021-07-22', '2021-07-23', 'Emprunté', 2),
('arthur', '3232', 'naruto shippuden', '2021-07-22', '2021-07-23', 'Emprunté', 1);

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

DROP TABLE IF EXISTS `livre`;
CREATE TABLE IF NOT EXISTS `livre` (
  `Nom_Livre` varchar(20) NOT NULL,
  `Genre` text,
  `Annee_sortie` date NOT NULL,
  `Auteur` text,
  `Maison_edition` text,
  `Date_enregistrement` date DEFAULT NULL,
  `Quantité` int(11) DEFAULT NULL,
  `QuantiteDisponible` int(11) DEFAULT NULL,
  PRIMARY KEY (`Annee_sortie`,`Nom_Livre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`Nom_Livre`, `Genre`, `Annee_sortie`, `Auteur`, `Maison_edition`, `Date_enregistrement`, `Quantité`, `QuantiteDisponible`) VALUES
('naruto shippuden', 'manga', '2013-10-07', 'tv tokyo', 'Masashi', '2021-07-04', 22, 17),
('boruto', 'anime', '2018-07-12', 'tv tokyo', 'kishimoto', '2021-07-04', 30, 30),
('le seed', 'magazine', '1885-12-07', 'inter_etat', 'junior', '2021-07-07', 10, 17),
('jujutsu kaisen', 'Anime', '2020-12-10', 'Mappa', 'Arthur', '2021-07-10', 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE IF NOT EXISTS `maintenance` (
  `Nom_Livre` varchar(20) NOT NULL,
  `Date_enregistrement` date NOT NULL,
  `Moyen_endomage` text,
  `Mesure_Prise` text,
  `Quantité` int(11) DEFAULT NULL,
  PRIMARY KEY (`Date_enregistrement`,`Nom_Livre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `maintenance`
--

INSERT INTO `maintenance` (`Nom_Livre`, `Date_enregistrement`, `Moyen_endomage`, `Mesure_Prise`, `Quantité`) VALUES
('naruto', '2021-07-04', 'moisissure', 'commande de nouveau livre de naruto', 4);

-- --------------------------------------------------------

--
-- Structure de la table `stats_adherent`
--

DROP TABLE IF EXISTS `stats_adherent`;
CREATE TABLE IF NOT EXISTS `stats_adherent` (
  `Nom_adherent` varchar(50) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  KEY `fk_NAMAE` (`Nom_adherent`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `stats_adherent`
--

INSERT INTO `stats_adherent` (`Nom_adherent`, `Date`) VALUES
('Arthur', '2021-01-06'),
('Arthuro', '2021-04-06'),
('Arthurito', '2021-04-05'),
('Harold', '2021-04-05'),
('arthur', '2021-07-21'),
('asadgfd', '2021-07-21');

-- --------------------------------------------------------

--
-- Structure de la table `stats_livre`
--

DROP TABLE IF EXISTS `stats_livre`;
CREATE TABLE IF NOT EXISTS `stats_livre` (
  `Nom_livre` varchar(50) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Nom_Adherent` text,
  KEY `fk_NAMAEO` (`Nom_livre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `stats_livre`
--

INSERT INTO `stats_livre` (`Nom_livre`, `Date`, `Nom_Adherent`) VALUES
('boruto', '2021-07-17', 'Harold'),
('naruto shippuden', '2021-07-21', 'asadgfd'),
('le seed', '2021-07-21', 'arthur'),
('le seed', '2021-07-21', 'arthur');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Nom` text,
  `Prenom` text,
  `Num_telephone` text,
  `Sexe` text,
  `Statut` text,
  `Mdp` varchar(20) NOT NULL,
  PRIMARY KEY (`Mdp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Nom`, `Prenom`, `Num_telephone`, `Sexe`, `Statut`, `Mdp`) VALUES
('ARTHURITO', 'NNNN', '2222', 'HOMME', 'admin', 'SSS'),
('ARTHUR', 'ART', '6555', 'MALE', 'admin', 'PPP'),
('kam', 'art', '777', 'homme', 'utilisateur', 'nand'),
('harold', 'kam', '33333', 'homme', 'admin', 'likolo'),
('harold', 'asd', '333', 'homme', 'admin', 'asd'),
('kam', 'art', '777', 'homme', 'utilisateur', 'eps');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
