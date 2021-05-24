-- phpMyAdmin SQL Dump
-- version 5.2.0-dev
-- https://www.phpmyadmin.net/
--
-- Host: 192.168.30.23
-- Generation Time: Mar 19, 2021 at 01:05 AM
-- Server version: 8.0.18
-- PHP Version: 7.4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

DROP DATABASE IF EXISTS it_fleet_db;
CREATE DATABASE it_fleet_db;
USE it_fleet_db;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `it_fleet_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `worker_id` int(11) DEFAULT NULL,
  `plate_number` varchar(255) NOT NULL COMMENT 'plate number',
  `license_number` varchar(255) NOT NULL COMMENT 'registered license number',
  `model` varchar(255) NOT NULL COMMENT 'car model'
);
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`worker_id`, `plate_number`, `license_number`, `model`) VALUES
(1020, '1520', '5060', 'Toyota'),
(1021, '1521', '5061', 'Nissan2'),
(1023, '1522', '5062', 'Hundai'),
-- (1025, '1522', '5062', 'Hundai'),
(1024, '1523', '5063', 'Toyota');

-- --------------------------------------------------------

--
-- Table structure for table `offices`
--

CREATE TABLE `offices` (
  `id` int(11) NOT NULL,
  `city` varchar(255) NOT NULL COMMENT 'city of operation',
  `type` varchar(255) NOT NULL COMMENT 'BO or HQ'
) 
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `offices`
--

INSERT INTO `offices` (`id`, `city`, `type`) VALUES
(23, 'Lodz', 'HQ'),
(24, 'Warsaw', 'BO');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL COMMENT 'to include first name',
  `middle_name` varchar(255) DEFAULT NULL COMMENT 'optional middle name',
  `surname` varchar(255) NOT NULL COMMENT 'to include surname',
  `pesel` varchar(255) DEFAULT NULL COMMENT 'to include pesel number if any',
  `gender` char(1) NOT NULL COMMENT 'to include gender',
  `birth_date` date NOT NULL COMMENT 'to include birthdate',
  `role` varchar(255) NOT NULL COMMENT 'administrator or client',
  `office_id` varchar(255) NOT NULL COMMENT 'where he work'
) 
-- ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `middle_name`, `surname`, `pesel`, `gender`, `birth_date`, `role`, `office_id`) VALUES
(1020, 'Ruth', 'Elion', 'Musk', '19021989009', 'M', '2020-10-20', 'admin', '23'),
(1021, 'Monika', '', 'Rosa', '19021989010', 'F', '1997-04-20', 'client', '24'),
(1023, 'Stanley', '', 'Murashko', '19021989011', 'M', '1999-08-09', 'client', '23'),
(1024, 'Godfrey', '', 'Muga', '19021989012', 'M', '1999-08-10', 'admin', '24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`plate_number`);

--
-- Indexes for table `offices`
--
ALTER TABLE `offices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `pesel` (`pesel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
