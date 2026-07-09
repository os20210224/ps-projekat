/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - ps-projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ps-projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `ps-projekat`;

/*Table structure for table `FizickoLice` */

DROP TABLE IF EXISTS `FizickoLice`;

CREATE TABLE `FizickoLice` (
  `idKupac` bigint(20) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  PRIMARY KEY (`idKupac`),
  CONSTRAINT `fizicko_lcie_fk_kupac` FOREIGN KEY (`idKupac`) REFERENCES `Kupac` (`idKupac`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `FizickoLice` */

insert  into `FizickoLice`(`idKupac`,`ime`,`prezime`) values 
(1,'Jovan','Stojković');

/*Table structure for table `Knjiga` */

DROP TABLE IF EXISTS `Knjiga`;

CREATE TABLE `Knjiga` (
  `idKnjiga` bigint(20) NOT NULL AUTO_INCREMENT,
  `format` enum('A4','A5','B5') NOT NULL,
  `brStranica` int(11) NOT NULL CHECK (`brStranica` > 0),
  `povez` enum('mek','tvrd') NOT NULL,
  `cenaStranica` double NOT NULL CHECK (`cenaStranica` > 0),
  `cenaPoveza` double NOT NULL CHECK (`cenaPoveza` > 0),
  `naziv` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `cena` double GENERATED ALWAYS AS (`cenaPoveza` + `cenaStranica` * `brStranica`) STORED,
  PRIMARY KEY (`idKnjiga`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `Knjiga` */

insert  into `Knjiga`(`idKnjiga`,`format`,`brStranica`,`povez`,`cenaStranica`,`cenaPoveza`,`naziv`,`autor`) values 
(1,'B5',374,'tvrd',5,1020,'Recenzije Poslednjih Obroka','Đorđe Skakavac'),
(6,'A4',2034,'tvrd',8,1200,'Beskonacna Osveta II','Dvojko');

/*Table structure for table `Kupac` */

DROP TABLE IF EXISTS `Kupac`;

CREATE TABLE `Kupac` (
  `idKupac` bigint(20) NOT NULL AUTO_INCREMENT,
  `telefon` varchar(15) NOT NULL,
  `email` varchar(127) NOT NULL CHECK (`email` like '%@%'),
  PRIMARY KEY (`idKupac`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `Kupac` */

insert  into `Kupac`(`idKupac`,`telefon`,`email`) values 
(1,'25261746','jovan@test'),
(3,'7259574','poslednji.trzaj@test');

/*Table structure for table `PravnoLice` */

DROP TABLE IF EXISTS `PravnoLice`;

CREATE TABLE `PravnoLice` (
  `idKupac` bigint(20) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  `adresa` varchar(255) NOT NULL,
  PRIMARY KEY (`idKupac`),
  CONSTRAINT `pravni_lice_fk_kupac` FOREIGN KEY (`idKupac`) REFERENCES `Kupac` (`idKupac`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `PravnoLice` */

insert  into `PravnoLice`(`idKupac`,`naziv`,`adresa`) values 
(3,'Starački Dom \"Poslednji Trzaj\"','Koralovo BB');

/*Table structure for table `Racun` */

DROP TABLE IF EXISTS `Racun`;

CREATE TABLE `Racun` (
  `idRacun` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum` datetime NOT NULL,
  `metodPlacanja` enum('kes','kartica','tekuci_racun') NOT NULL,
  `ukupanIznos` double NOT NULL DEFAULT 0,
  `idZaposleni` bigint(20) NOT NULL,
  `idKupac` bigint(20) NOT NULL,
  PRIMARY KEY (`idRacun`),
  KEY `racun_fk_zaposleni` (`idZaposleni`),
  KEY `racun_fk_kupac` (`idKupac`),
  CONSTRAINT `racun_fk_kupac` FOREIGN KEY (`idKupac`) REFERENCES `Kupac` (`idKupac`) ON UPDATE CASCADE,
  CONSTRAINT `racun_fk_zaposleni` FOREIGN KEY (`idZaposleni`) REFERENCES `Zaposleni` (`idZaposleni`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `Racun` */

insert  into `Racun`(`idRacun`,`datum`,`metodPlacanja`,`ukupanIznos`,`idZaposleni`,`idKupac`) values 
(2,'2026-05-16 14:43:54','tekuci_racun',289000,1,3);

/*Table structure for table `Smena` */

DROP TABLE IF EXISTS `Smena`;

CREATE TABLE `Smena` (
  `idSmena` bigint(20) NOT NULL AUTO_INCREMENT,
  `vremePocetka` time NOT NULL CHECK (`vremePocetka` < `vremeKraja`),
  `vremeKraja` time NOT NULL CHECK (`vremeKraja` > `vremePocetka`),
  PRIMARY KEY (`idSmena`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `Smena` */

insert  into `Smena`(`idSmena`,`vremePocetka`,`vremeKraja`) values 
(1,'08:00:00','16:00:00');

/*Table structure for table `SmenaZaposlenog` */

DROP TABLE IF EXISTS `SmenaZaposlenog`;

CREATE TABLE `SmenaZaposlenog` (
  `idZaposleni` bigint(20) NOT NULL,
  `idSmena` bigint(20) NOT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`idZaposleni`,`idSmena`),
  KEY `smena_zaposlenog_fk_smena` (`idSmena`),
  CONSTRAINT `smena_zaposlenog_fk_smena` FOREIGN KEY (`idSmena`) REFERENCES `Smena` (`idSmena`) ON UPDATE CASCADE,
  CONSTRAINT `smena_zaposlenog_fk_zaposleni` FOREIGN KEY (`idZaposleni`) REFERENCES `Zaposleni` (`idZaposleni`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `SmenaZaposlenog` */

insert  into `SmenaZaposlenog`(`idZaposleni`,`idSmena`,`datum`) values 
(1,1,'2026-05-16');

/*Table structure for table `StavkaRacuna` */

DROP TABLE IF EXISTS `StavkaRacuna`;

CREATE TABLE `StavkaRacuna` (
  `idRacun` bigint(20) NOT NULL,
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `kolicina` int(11) NOT NULL,
  `cena` double DEFAULT NULL,
  `iznos` double GENERATED ALWAYS AS (`cena` * `kolicina`) STORED,
  `idKnjiga` bigint(20) NOT NULL,
  PRIMARY KEY (`idRacun`,`rb`),
  KEY `rb` (`rb`),
  KEY `stavka_racuna_fk_knjiga` (`idKnjiga`),
  CONSTRAINT `stavka_racun_fk_racun` FOREIGN KEY (`idRacun`) REFERENCES `Racun` (`idRacun`) ON UPDATE CASCADE,
  CONSTRAINT `stavka_racuna_fk_knjiga` FOREIGN KEY (`idKnjiga`) REFERENCES `Knjiga` (`idKnjiga`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `StavkaRacuna` */

insert  into `StavkaRacuna`(`idRacun`,`rb`,`kolicina`,`cena`,`idKnjiga`) values 
(2,2,100,2890,1);

/*Table structure for table `Zaposleni` */

DROP TABLE IF EXISTS `Zaposleni`;

CREATE TABLE `Zaposleni` (
  `idZaposleni` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`idZaposleni`),
  UNIQUE KEY `username_unique` (`username`),
  CONSTRAINT `password_length` CHECK (char_length(`password`) > 8)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `Zaposleni` */

insert  into `Zaposleni`(`idZaposleni`,`ime`,`prezime`,`username`,`password`) values 
(1,'Joca','Kormilo','jk17','mornarmore');

/* Trigger structure for table `StavkaRacuna` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StavkaRacunaInsertCena` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StavkaRacunaInsertCena` BEFORE INSERT ON `StavkaRacuna` FOR EACH ROW BEGIN
	DECLARE c DOUBLE;
	SELECT `cena` INTO c FROM `ps-projekat`.`Knjiga` WHERE `idKnjiga` = NEW.idKnjiga LIMIT 1;
	SET NEW.cena = c;
    END */$$


DELIMITER ;

/* Trigger structure for table `StavkaRacuna` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StavkaRacunaInsertUpdateRacun` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StavkaRacunaInsertUpdateRacun` AFTER INSERT ON `StavkaRacuna` FOR EACH ROW BEGIN
	UPDATE `ps-projekat`.`Racun`
	SET `ukupanIznos` = `ukupanIznos` + NEW.iznos
	WHERE `idRacun` = NEW.idRacun;
    END */$$


DELIMITER ;

/* Trigger structure for table `StavkaRacuna` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StavkaRacunaUpdateCena` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StavkaRacunaUpdateCena` BEFORE UPDATE ON `StavkaRacuna` FOR EACH ROW BEGIN
	DECLARE c DOUBLE;
	SELECT `cena` INTO c FROM `ps-projekat`.`Knjiga` WHERE `idKnjiga` = NEW.idKnjiga LIMIT 1;
	SET NEW.cena = c;
    END */$$


DELIMITER ;

/* Trigger structure for table `StavkaRacuna` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StavkaRacunaUpdateUpdateRacun` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StavkaRacunaUpdateUpdateRacun` AFTER UPDATE ON `StavkaRacuna` FOR EACH ROW 
	BEGIN
	  UPDATE `Racun`
	  SET `ukupanIznos` = `ukupanIznos` - OLD.iznos + NEW.iznos
	  WHERE `idRacun` = NEW.idRacun;
    END */$$


DELIMITER ;

/* Trigger structure for table `StavkaRacuna` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `StavkaRacunaDeleteUpdateRacun` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `StavkaRacunaDeleteUpdateRacun` AFTER DELETE ON `StavkaRacuna` FOR EACH ROW 
	BEGIN
	  UPDATE `Racun`
	  SET `ukupanIznos` = `ukupanIznos`- OLD.iznos
	  WHERE `idRacun` = OLD.idRacun;
	END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
