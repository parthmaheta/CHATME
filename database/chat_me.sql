-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 27, 2019 at 05:21 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chat_me`
--

-- --------------------------------------------------------

--
-- Table structure for table `block`
--

CREATE TABLE `block` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FID` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `ID` int(10) UNSIGNED NOT NULL,
  `_FROM` smallint(5) UNSIGNED DEFAULT NULL,
  `_TO` smallint(5) UNSIGNED DEFAULT NULL,
  `MESSAGE` text DEFAULT NULL,
  `IS_FILE` tinyint(1) DEFAULT 0,
  `FILE_PATH` text DEFAULT NULL,
  `STATUS` smallint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `friend`
--

CREATE TABLE `friend` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FID` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `friend`
--

INSERT INTO `friend` (`UID`, `FID`) VALUES
(1, 1),
(2, 1),
(3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FID` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`UID`, `FID`) VALUES
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `statuses`
--

CREATE TABLE `statuses` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FILEPATH` varchar(200) DEFAULT NULL,
  `DATE_TIME` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `statuses`
--

INSERT INTO `statuses` (`UID`, `FILEPATH`, `DATE_TIME`) VALUES
(1, '1577423593.jpeg', '2019-12-27 05:13:13'),
(1, '1577423611.jpeg', '2019-12-27 05:13:31'),
(2, '1577423700.jpeg', '2019-12-27 05:14:59'),
(3, '1577423779.jpeg', '2019-12-27 05:16:19');

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `ID` smallint(5) UNSIGNED NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PASSWORD` text NOT NULL,
  `PICTURE` text NOT NULL DEFAULT 'demo.jpeg',
  `STATUS` text NOT NULL DEFAULT 'Hey..I M Using ChatME',
  `DATE_TIME` timestamp NOT NULL DEFAULT current_timestamp(),
  `LAST_SEEN` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`ID`, `NAME`, `EMAIL`, `PASSWORD`, `PICTURE`, `STATUS`, `DATE_TIME`, `LAST_SEEN`) VALUES
(1, 'part', 'p@gmail.com', 'c483f6ce851c9ecd9fb835ff7551737c', '1577463573.jpeg', 'Hey..I M Using ChatME', '2019-12-27 04:57:27', '2019-12-27 04:57:27'),
(2, 'q', 'q@gmail.com', '7694f4a66316e53c8cdd9d9954bd611d', 'demo.jpeg', 'Hey..I M Using ChatME', '2019-12-27 05:14:21', '2019-12-27 05:14:21'),
(3, 'r', 'r@r.com', '4b43b0aee35624cd95b910189b3dc231', 'demo.jpeg', 'Hey..I M Using ChatME', '2019-12-27 05:15:35', '2019-12-27 05:15:35');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `block`
--
ALTER TABLE `block`
  ADD KEY `UID` (`UID`),
  ADD KEY `FID` (`FID`);

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `_FROM` (`_FROM`),
  ADD KEY `_TO` (`_TO`);

--
-- Indexes for table `friend`
--
ALTER TABLE `friend`
  ADD UNIQUE KEY `UID` (`UID`,`FID`),
  ADD KEY `FK_FRIEND_UID` (`UID`),
  ADD KEY `FK_FRIEND_FID` (`FID`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD UNIQUE KEY `UID` (`UID`,`FID`),
  ADD KEY `request_fk_uid` (`UID`),
  ADD KEY `request_fk_fid` (`FID`);

--
-- Indexes for table `statuses`
--
ALTER TABLE `statuses`
  ADD KEY `UID` (`UID`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`),
  ADD KEY `NAME` (`NAME`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `ID` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `block`
--
ALTER TABLE `block`
  ADD CONSTRAINT `block_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`),
  ADD CONSTRAINT `block_ibfk_2` FOREIGN KEY (`FID`) REFERENCES `user_details` (`ID`);

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `chat_ibfk_1` FOREIGN KEY (`_FROM`) REFERENCES `user_details` (`ID`),
  ADD CONSTRAINT `chat_ibfk_2` FOREIGN KEY (`_TO`) REFERENCES `user_details` (`ID`);

--
-- Constraints for table `friend`
--
ALTER TABLE `friend`
  ADD CONSTRAINT `FK_FRIEND_FID` FOREIGN KEY (`FID`) REFERENCES `user_details` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_FRIEND_UID` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_fk_fid` FOREIGN KEY (`FID`) REFERENCES `user_details` (`ID`),
  ADD CONSTRAINT `request_fk_uid` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`);

--
-- Constraints for table `statuses`
--
ALTER TABLE `statuses`
  ADD CONSTRAINT `statuses_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
