-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2019 at 06:40 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

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
  `MESSAGE` text,
  `IS_FILE` tinyint(1) DEFAULT '0',
  `FILE_PATH` text,
  `STATUS` smallint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `friend`
--

CREATE TABLE `friend` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FID` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_chat`
--

CREATE TABLE `group_chat` (
  `ID` int(10) UNSIGNED NOT NULL,
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `GROUP_ID` smallint(5) UNSIGNED DEFAULT NULL,
  `MESSAGE` text,
  `IS_FILE` tinyint(1) DEFAULT '0',
  `FILE_PATH` text,
  `ALL_READ` tinyint(1) DEFAULT '0',
  `DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_chat_status`
--

CREATE TABLE `group_chat_status` (
  `CHAT_ID` int(10) UNSIGNED DEFAULT NULL,
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_details`
--

CREATE TABLE `group_details` (
  `ID` smallint(5) UNSIGNED NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `PICTURE` text,
  `DESCRIPTION` tinytext,
  `OWNER` smallint(5) UNSIGNED DEFAULT NULL,
  `DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `group_member`
--

CREATE TABLE `group_member` (
  `GROUP_ID` smallint(5) UNSIGNED DEFAULT NULL,
  `UID` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `last_online`
--

CREATE TABLE `last_online` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `otp`
--

CREATE TABLE `otp` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `OTP` smallint(5) UNSIGNED DEFAULT NULL,
  `DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FID` smallint(5) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `statuses`
--

CREATE TABLE `statuses` (
  `UID` smallint(5) UNSIGNED DEFAULT NULL,
  `FILEPATH` varchar(200) DEFAULT NULL,
  `DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `ID` smallint(5) UNSIGNED NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `PASSWORD` text NOT NULL,
  `PICTURE` text NOT NULL,
  `STATUS` text NOT NULL,
  `DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD KEY `FK_FRIEND_UID` (`UID`),
  ADD KEY `FK_FRIEND_FID` (`FID`);

--
-- Indexes for table `group_chat`
--
ALTER TABLE `group_chat`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `UID` (`UID`),
  ADD KEY `GROUP_ID` (`GROUP_ID`);

--
-- Indexes for table `group_chat_status`
--
ALTER TABLE `group_chat_status`
  ADD KEY `CHAT_ID` (`CHAT_ID`),
  ADD KEY `UID` (`UID`);

--
-- Indexes for table `group_details`
--
ALTER TABLE `group_details`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `OWNER` (`OWNER`);

--
-- Indexes for table `group_member`
--
ALTER TABLE `group_member`
  ADD KEY `GROUP_ID` (`GROUP_ID`),
  ADD KEY `UID` (`UID`);

--
-- Indexes for table `last_online`
--
ALTER TABLE `last_online`
  ADD KEY `UID` (`UID`);

--
-- Indexes for table `otp`
--
ALTER TABLE `otp`
  ADD UNIQUE KEY `OTP` (`OTP`),
  ADD KEY `FK_OTP` (`UID`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
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
-- AUTO_INCREMENT for table `group_chat`
--
ALTER TABLE `group_chat`
  MODIFY `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `group_details`
--
ALTER TABLE `group_details`
  MODIFY `ID` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `ID` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT;

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
-- Constraints for table `group_chat`
--
ALTER TABLE `group_chat`
  ADD CONSTRAINT `group_chat_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`),
  ADD CONSTRAINT `group_chat_ibfk_2` FOREIGN KEY (`GROUP_ID`) REFERENCES `group_details` (`ID`);

--
-- Constraints for table `group_chat_status`
--
ALTER TABLE `group_chat_status`
  ADD CONSTRAINT `group_chat_status_ibfk_1` FOREIGN KEY (`CHAT_ID`) REFERENCES `group_chat` (`ID`),
  ADD CONSTRAINT `group_chat_status_ibfk_2` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`);

--
-- Constraints for table `group_details`
--
ALTER TABLE `group_details`
  ADD CONSTRAINT `group_details_ibfk_1` FOREIGN KEY (`OWNER`) REFERENCES `user_details` (`ID`);

--
-- Constraints for table `group_member`
--
ALTER TABLE `group_member`
  ADD CONSTRAINT `group_member_ibfk_1` FOREIGN KEY (`GROUP_ID`) REFERENCES `group_details` (`ID`),
  ADD CONSTRAINT `group_member_ibfk_2` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`);

--
-- Constraints for table `last_online`
--
ALTER TABLE `last_online`
  ADD CONSTRAINT `last_online_ibfk_1` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `otp`
--
ALTER TABLE `otp`
  ADD CONSTRAINT `FK_OTP` FOREIGN KEY (`UID`) REFERENCES `user_details` (`ID`);

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
