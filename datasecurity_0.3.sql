-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2023 at 08:20 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `datasecurity`
--
CREATE DATABASE IF NOT EXISTS `datasecurity` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `datasecurity`;

-- --------------------------------------------------------

--
-- Table structure for table `cipher`
--

CREATE TABLE `cipher` (
  `cipherId` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  `plainText` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `cipherKey` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `level` enum('easy','moderate','hard') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `date` date NOT NULL,
  `text` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`roleId`, `roleName`) VALUES
(1, 'ROLE_User'),
(2, 'ROLE_Admin');

-- --------------------------------------------------------

--
-- Table structure for table `scoreboard`
--

CREATE TABLE `scoreboard` (
  `scoreId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `level` enum('easy','moderate','hard') COLLATE utf8_unicode_ci NOT NULL,
  `score` int(11) NOT NULL DEFAULT 0,
  `time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `scoreboard`
--

INSERT INTO `scoreboard` (`scoreId`, `userId`, `level`, `score`, `time`) VALUES
(1, 3, 'easy', 500, NULL),
(2, 3, 'easy', 600, NULL),
(3, 3, 'easy', 700, NULL),
(4, 3, 'moderate', 500, NULL),
(5, 3, 'moderate', 600, NULL),
(6, 3, 'moderate', 700, NULL),
(7, 3, 'hard', 500, NULL),
(8, 3, 'hard', 600, NULL),
(9, 3, 'hard', 700, NULL),
(10, 4, 'easy', 500, NULL),
(11, 4, 'easy', 600, NULL),
(12, 4, 'easy', 700, NULL),
(13, 4, 'moderate', 500, NULL),
(14, 4, 'moderate', 600, NULL),
(15, 4, 'moderate', 700, NULL),
(16, 4, 'hard', 500, NULL),
(17, 4, 'hard', 600, NULL),
(18, 4, 'hard', 700, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `type`
--

CREATE TABLE `type` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `type`
--

INSERT INTO `type` (`typeId`, `typeName`) VALUES
(1, 'caesar'),
(2, 'vigenere'),
(3, 'permutation');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phoneNumber` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `status` enum('yes','no') COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `email`, `password`, `username`, `phoneNumber`, `status`) VALUES
(3, 'pokpongthunder789@gmail.com', '$2a$10$h/HDjjEgpnrjT5S0w2wlTOMQT721lyBOEuSDDA2D1I3NHHnfZP/F6', 'MamoruTFN', '0896345911', 'yes'),
(4, 'pokpong@hotmail.com', '$2a$10$OcaXQC1VX9GHNkGOZkkp5.Ws7k8csTEEoOcSjtyF8dP7bTnd.8qtS', 'mamoru2', '0896345911', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `userroles`
--

CREATE TABLE `userroles` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `userroles`
--

INSERT INTO `userroles` (`userId`, `roleId`) VALUES
(3, 2),
(4, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cipher`
--
ALTER TABLE `cipher`
  ADD PRIMARY KEY (`cipherId`),
  ADD KEY `typeId` (`typeId`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `scoreboard`
--
ALTER TABLE `scoreboard`
  ADD PRIMARY KEY (`scoreId`),
  ADD KEY `userId` (`userId`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`typeId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `userroles`
--
ALTER TABLE `userroles`
  ADD PRIMARY KEY (`userId`,`roleId`),
  ADD KEY `userId` (`userId`,`roleId`),
  ADD KEY `roleId` (`roleId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cipher`
--
ALTER TABLE `cipher`
  MODIFY `cipherId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `scoreboard`
--
ALTER TABLE `scoreboard`
  MODIFY `scoreId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `type`
--
ALTER TABLE `type`
  MODIFY `typeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cipher`
--
ALTER TABLE `cipher`
  ADD CONSTRAINT `cipher_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `type` (`typeId`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `scoreboard`
--
ALTER TABLE `scoreboard`
  ADD CONSTRAINT `scoreboard_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `userroles`
--
ALTER TABLE `userroles`
  ADD CONSTRAINT `userroles_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `userroles_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
