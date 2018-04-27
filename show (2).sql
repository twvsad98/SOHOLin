-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 27, 2018 at 03:04 AM
-- Server version: 5.6.34-log
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `show`
--

-- --------------------------------------------------------

--
-- Table structure for table `album_img`
--

CREATE TABLE `album_img` (
  `img_id` int(255) NOT NULL,
  `album_id` int(11) NOT NULL,
  `file_name` varchar(256) NOT NULL,
  `file_path` text NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `img_descrption` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `apply`
--

CREATE TABLE `apply` (
  `apply_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `apply_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_comfirmed` tinyint(1) NOT NULL,
  `comfirmed_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_category`
--

CREATE TABLE `case_category` (
  `case_category_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_company_bridge`
--

CREATE TABLE `case_company_bridge` (
  `case_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_rating`
--

CREATE TABLE `case_rating` (
  `case_rating_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `to_case_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `comment` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_recruited_user`
--

CREATE TABLE `case_recruited_user` (
  `recruited_list_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `recruited_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_tag`
--

CREATE TABLE `case_tag` (
  `case_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `company_id` int(11) NOT NULL,
  `UN` int(11) NOT NULL,
  `company_name` varchar(128) NOT NULL,
  `company_address` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `invite`
--

CREATE TABLE `invite` (
  `invite_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `invite_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_comfirmed` tinyint(1) NOT NULL,
  `comfirmed_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `message_text` text,
  `message_img_path` text COMMENT 'path'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tag_basic`
--

CREATE TABLE `tag_basic` (
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(128) NOT NULL,
  `category_id` int(11) NOT NULL,
  `updater_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_account`
--

CREATE TABLE `user_account` (
  `user_id` int(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(256) NOT NULL,
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `facebook_registered` tinyint(1) NOT NULL,
  `user_name` varchar(128) DEFAULT NULL,
  `user_LINE` varchar(128) DEFAULT NULL,
  `user_self_description` text,
  `user_gender` tinyint(1) DEFAULT NULL COMMENT '0=F,1=M'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_album`
--

CREATE TABLE `user_album` (
  `album_id` int(255) NOT NULL,
  `album_name` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `album_description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_capacity`
--

CREATE TABLE `user_capacity` (
  `capacity_id` int(11) NOT NULL,
  `user_id` int(255) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_capacity_tag`
--

CREATE TABLE `user_capacity_tag` (
  `capacity_tag_id` int(11) NOT NULL,
  `capacity_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_case_basic`
--

CREATE TABLE `user_case_basic` (
  `case_id` int(11) NOT NULL,
  `case_name` varchar(128) NOT NULL,
  `case_recruit_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `case_recruit_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `case_recruit_progress` double NOT NULL,
  `case_work_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `case_work_deadline` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `case_participate` int(11) NOT NULL,
  `case_pay_per` int(11) NOT NULL,
  `case_description` text NOT NULL,
  `case_recruitor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_company`
--

CREATE TABLE `user_company` (
  `user_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_experience`
--

CREATE TABLE `user_experience` (
  `experience_id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `description` varchar(128) NOT NULL,
  `edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `start_at` year(4) NOT NULL,
  `end_at` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_phone`
--

CREATE TABLE `user_phone` (
  `user_phone_id` int(11) NOT NULL,
  `user_phone_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_rating`
--

CREATE TABLE `user_rating` (
  `rating_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `comment` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_tracking_case`
--

CREATE TABLE `user_tracking_case` (
  `list_case_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_tracking_list_user_page`
--

CREATE TABLE `user_tracking_list_user_page` (
  `list_page_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tracking_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `album_img`
--
ALTER TABLE `album_img`
  ADD PRIMARY KEY (`img_id`),
  ADD KEY `gallery_id` (`album_id`);

--
-- Indexes for table `apply`
--
ALTER TABLE `apply`
  ADD PRIMARY KEY (`apply_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `from_user_id` (`from_user_id`);

--
-- Indexes for table `case_category`
--
ALTER TABLE `case_category`
  ADD PRIMARY KEY (`case_category_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `case_company_bridge`
--
ALTER TABLE `case_company_bridge`
  ADD KEY `case_id` (`case_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `case_rating`
--
ALTER TABLE `case_rating`
  ADD PRIMARY KEY (`case_rating_id`),
  ADD KEY `from_user_id` (`from_user_id`),
  ADD KEY `to_case_id` (`to_case_id`);

--
-- Indexes for table `case_recruited_user`
--
ALTER TABLE `case_recruited_user`
  ADD PRIMARY KEY (`recruited_list_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `case_tag`
--
ALTER TABLE `case_tag`
  ADD KEY `case_id` (`case_id`),
  ADD KEY `tag_id` (`tag_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`company_id`);

--
-- Indexes for table `invite`
--
ALTER TABLE `invite`
  ADD PRIMARY KEY (`invite_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `to_user_id` (`to_user_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `from_user_id` (`from_user_id`),
  ADD KEY `to_user_id` (`to_user_id`);

--
-- Indexes for table `tag_basic`
--
ALTER TABLE `tag_basic`
  ADD PRIMARY KEY (`tag_id`),
  ADD UNIQUE KEY `tag_name` (`tag_name`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `updater_user_id` (`updater_user_id`);

--
-- Indexes for table `user_account`
--
ALTER TABLE `user_account`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_album`
--
ALTER TABLE `user_album`
  ADD PRIMARY KEY (`album_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_capacity`
--
ALTER TABLE `user_capacity`
  ADD PRIMARY KEY (`capacity_id`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_capacity_tag`
--
ALTER TABLE `user_capacity_tag`
  ADD PRIMARY KEY (`capacity_tag_id`),
  ADD KEY `tag_id` (`tag_id`),
  ADD KEY `user_capacity_tag_ibfk_2` (`capacity_id`);

--
-- Indexes for table `user_case_basic`
--
ALTER TABLE `user_case_basic`
  ADD PRIMARY KEY (`case_id`),
  ADD KEY `case_recruitor_id` (`case_recruitor_id`);

--
-- Indexes for table `user_company`
--
ALTER TABLE `user_company`
  ADD KEY `company_id` (`company_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_experience`
--
ALTER TABLE `user_experience`
  ADD PRIMARY KEY (`experience_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_phone`
--
ALTER TABLE `user_phone`
  ADD PRIMARY KEY (`user_phone_id`);

--
-- Indexes for table `user_rating`
--
ALTER TABLE `user_rating`
  ADD PRIMARY KEY (`rating_id`),
  ADD KEY `from_user_id` (`from_user_id`),
  ADD KEY `to_user_id` (`to_user_id`);

--
-- Indexes for table `user_tracking_case`
--
ALTER TABLE `user_tracking_case`
  ADD PRIMARY KEY (`list_case_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `case_id` (`case_id`);

--
-- Indexes for table `user_tracking_list_user_page`
--
ALTER TABLE `user_tracking_list_user_page`
  ADD PRIMARY KEY (`list_page_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `tracking_user_id` (`tracking_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `album_img`
--
ALTER TABLE `album_img`
  MODIFY `img_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `apply`
--
ALTER TABLE `apply`
  MODIFY `apply_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `case_category`
--
ALTER TABLE `case_category`
  MODIFY `case_category_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `case_rating`
--
ALTER TABLE `case_rating`
  MODIFY `case_rating_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `case_recruited_user`
--
ALTER TABLE `case_recruited_user`
  MODIFY `recruited_list_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `company_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `invite`
--
ALTER TABLE `invite`
  MODIFY `invite_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tag_basic`
--
ALTER TABLE `tag_basic`
  MODIFY `tag_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_account`
--
ALTER TABLE `user_account`
  MODIFY `user_id` int(128) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_album`
--
ALTER TABLE `user_album`
  MODIFY `album_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_capacity`
--
ALTER TABLE `user_capacity`
  MODIFY `capacity_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_capacity_tag`
--
ALTER TABLE `user_capacity_tag`
  MODIFY `capacity_tag_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_case_basic`
--
ALTER TABLE `user_case_basic`
  MODIFY `case_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_experience`
--
ALTER TABLE `user_experience`
  MODIFY `experience_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_phone`
--
ALTER TABLE `user_phone`
  MODIFY `user_phone_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_rating`
--
ALTER TABLE `user_rating`
  MODIFY `rating_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_tracking_case`
--
ALTER TABLE `user_tracking_case`
  MODIFY `list_case_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_tracking_list_user_page`
--
ALTER TABLE `user_tracking_list_user_page`
  MODIFY `list_page_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `album_img`
--
ALTER TABLE `album_img`
  ADD CONSTRAINT `album_img_ibfk_1` FOREIGN KEY (`album_id`) REFERENCES `user_album` (`album_id`);

--
-- Constraints for table `apply`
--
ALTER TABLE `apply`
  ADD CONSTRAINT `apply_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `apply_ibfk_2` FOREIGN KEY (`from_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `case_category`
--
ALTER TABLE `case_category`
  ADD CONSTRAINT `case_category_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `case_company_bridge`
--
ALTER TABLE `case_company_bridge`
  ADD CONSTRAINT `case_company_bridge_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_company_bridge_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`);

--
-- Constraints for table `case_recruited_user`
--
ALTER TABLE `case_recruited_user`
  ADD CONSTRAINT `case_recruited_user_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_recruited_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `case_tag`
--
ALTER TABLE `case_tag`
  ADD CONSTRAINT `case_tag_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag_basic` (`tag_id`);

--
-- Constraints for table `invite`
--
ALTER TABLE `invite`
  ADD CONSTRAINT `invite_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `invite_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `tag_basic`
--
ALTER TABLE `tag_basic`
  ADD CONSTRAINT `tag_basic_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `tag_basic_ibfk_2` FOREIGN KEY (`updater_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_album`
--
ALTER TABLE `user_album`
  ADD CONSTRAINT `user_album_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_capacity`
--
ALTER TABLE `user_capacity`
  ADD CONSTRAINT `user_capacity_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`),
  ADD CONSTRAINT `user_capacity_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_capacity_tag`
--
ALTER TABLE `user_capacity_tag`
  ADD CONSTRAINT `user_capacity_tag_ibfk_1` FOREIGN KEY (`tag_id`) REFERENCES `tag_basic` (`tag_id`),
  ADD CONSTRAINT `user_capacity_tag_ibfk_2` FOREIGN KEY (`capacity_id`) REFERENCES `user_capacity` (`capacity_id`) ON DELETE CASCADE;

--
-- Constraints for table `user_case_basic`
--
ALTER TABLE `user_case_basic`
  ADD CONSTRAINT `user_case_basic_ibfk_1` FOREIGN KEY (`case_recruitor_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_company`
--
ALTER TABLE `user_company`
  ADD CONSTRAINT `user_company_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`),
  ADD CONSTRAINT `user_company_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_experience`
--
ALTER TABLE `user_experience`
  ADD CONSTRAINT `user_experience_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_rating`
--
ALTER TABLE `user_rating`
  ADD CONSTRAINT `user_rating_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `user_rating_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_tracking_case`
--
ALTER TABLE `user_tracking_case`
  ADD CONSTRAINT `user_tracking_case_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `user_tracking_case_ibfk_2` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`);

--
-- Constraints for table `user_tracking_list_user_page`
--
ALTER TABLE `user_tracking_list_user_page`
  ADD CONSTRAINT `user_tracking_list_user_page_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `user_tracking_list_user_page_ibfk_2` FOREIGN KEY (`tracking_user_id`) REFERENCES `user_account` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
