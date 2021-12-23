-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 23, 2021 at 05:27 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `userdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `dooractuator`
--

CREATE TABLE `dooractuator` (
  `id` int(255) NOT NULL,
  `doorID` int(255) NOT NULL,
  `houseID` int(255) NOT NULL,
  `floorID` int(255) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dooractuator`
--

INSERT INTO `dooractuator` (`id`, `doorID`, `houseID`, `floorID`, `status`) VALUES
(1, 1, 1, 1, 1),
(2, 2, 1, 1, 0),
(3, 3, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `floors`
--

CREATE TABLE `floors` (
  `id` int(255) NOT NULL,
  `houseID` int(255) NOT NULL,
  `floorID` int(255) NOT NULL,
  `floorNo` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `floors`
--

INSERT INTO `floors` (`id`, `houseID`, `floorID`, `floorNo`) VALUES
(1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `garage`
--

CREATE TABLE `garage` (
  `id` int(255) NOT NULL,
  `garageID` int(255) NOT NULL,
  `doorStatus` varchar(200) NOT NULL,
  `lockStatus` tinyint(1) NOT NULL,
  `doorNo` int(100) NOT NULL,
  `houseID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `garage`
--

INSERT INTO `garage` (`id`, `garageID`, `doorStatus`, `lockStatus`, `doorNo`, `houseID`) VALUES
(1, 1, 'Closed', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `housestatus`
--

CREATE TABLE `housestatus` (
  `houseID` int(255) NOT NULL,
  `securityStatus` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `housestatus`
--

INSERT INTO `housestatus` (`houseID`, `securityStatus`) VALUES
(1, 'Unarmed');

-- --------------------------------------------------------

--
-- Table structure for table `lightdetails`
--

CREATE TABLE `lightdetails` (
  `id` int(255) NOT NULL,
  `lightID` int(255) NOT NULL,
  `houseID` int(255) NOT NULL,
  `floorID` int(255) NOT NULL,
  `access` tinyint(1) NOT NULL,
  `status` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lightdetails`
--

INSERT INTO `lightdetails` (`id`, `lightID`, `houseID`, `floorID`, `access`, `status`) VALUES
(1, 1, 1, 1, 0, 1),
(2, 2, 1, 1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `motiondetector`
--

CREATE TABLE `motiondetector` (
  `detectorID` int(255) NOT NULL,
  `houseID` int(255) NOT NULL,
  `floorID` int(255) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sensor`
--

CREATE TABLE `sensor` (
  `sensorID` int(255) NOT NULL,
  `houseID` int(255) NOT NULL,
  `floorID` int(255) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `thermoschedule`
--

CREATE TABLE `thermoschedule` (
  `ID` int(255) NOT NULL,
  `houseID` int(255) NOT NULL,
  `floorID` int(255) NOT NULL,
  `tmodetf` varchar(200) CHARACTER SET utf8mb4 NOT NULL,
  `tfan` varchar(200) CHARACTER SET utf8mb4 NOT NULL,
  `tcurrent` int(11) NOT NULL,
  `tcontrol` int(11) NOT NULL,
  `startTime` int(11) NOT NULL,
  `endTime` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `thermoschedule`
--

INSERT INTO `thermoschedule` (`ID`, `houseID`, `floorID`, `tmodetf`, `tfan`, `tcurrent`, `tcontrol`, `startTime`, `endTime`, `status`) VALUES
(1, 1, 1, 'Cool', 'Off', 66, 68, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `name` varchar(50) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` mediumtext NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` bigint(255) NOT NULL,
  `address` int(200) NOT NULL,
  `userid` varchar(50) NOT NULL,
  `houseID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`name`, `username`, `password`, `email`, `phone`, `address`, `userid`, `houseID`) VALUES
('', 'grayson', '$2y$10$vlpAAxVhUWdSTyF0Eu6yvOiA4D./uiHu6gWXNasH6JbvH7Q73LIzK', 'bassgrayson@aol.com', 7047753928, 28625, 'admin1', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dooractuator`
--
ALTER TABLE `dooractuator`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `floors`
--
ALTER TABLE `floors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `garage`
--
ALTER TABLE `garage`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `housestatus`
--
ALTER TABLE `housestatus`
  ADD PRIMARY KEY (`houseID`);

--
-- Indexes for table `lightdetails`
--
ALTER TABLE `lightdetails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `motiondetector`
--
ALTER TABLE `motiondetector`
  ADD PRIMARY KEY (`detectorID`);

--
-- Indexes for table `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`sensorID`);

--
-- Indexes for table `thermoschedule`
--
ALTER TABLE `thermoschedule`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`houseID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dooractuator`
--
ALTER TABLE `dooractuator`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `floors`
--
ALTER TABLE `floors`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `garage`
--
ALTER TABLE `garage`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `lightdetails`
--
ALTER TABLE `lightdetails`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `motiondetector`
--
ALTER TABLE `motiondetector`
  MODIFY `detectorID` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sensor`
--
ALTER TABLE `sensor`
  MODIFY `sensorID` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `thermoschedule`
--
ALTER TABLE `thermoschedule`
  MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `houseID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
