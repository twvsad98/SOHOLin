-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 04, 2018 at 09:48 AM
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
  `img_name` varchar(64) NOT NULL DEFAULT 'img',
  `img_path` text NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `img_description` varchar(512) DEFAULT 'description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_apply`
--

CREATE TABLE `case_apply` (
  `apply_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `apply_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_comfirmed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0=false,1=true',
  `comfirmed_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
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
-- Table structure for table `case_company`
--

CREATE TABLE `case_company` (
  `case_company_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `company_name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `case_invite`
--

CREATE TABLE `case_invite` (
  `invite_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL COMMENT '發出邀請案件',
  `user_id` int(11) NOT NULL COMMENT '受邀人',
  `invite_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '發出邀請時間',
  `is_comfirmed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否確認(0=false,1=true',
  `comfirmed_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '確認時間'
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
  `case_tag_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  `tag_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_id`, `category_name`) VALUES
(1, 'Instrument'),
(2, 'Web Developement'),
(3, 'Graphic Design'),
(4, 'Software'),
(5, 'Sales'),
(6, 'Supportive');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `company_id` int(11) NOT NULL,
  `UN` int(11) NOT NULL,
  `company_name` varchar(128) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`company_id`, `UN`, `company_name`, `latitude`, `longitude`) VALUES
(1, 20828393, '宏碁股份有限公司', 25.0626001, 121.5425692);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `from_user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `message_text` text,
  `message_img_path` text COMMENT 'path',
  `sent_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tag`
--

CREATE TABLE `tag` (
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
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '註冊時間',
  `facebook_registered` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否為FB註冊(0=false,1=true)',
  `user_name` varchar(128) DEFAULT NULL,
  `user_LINE` varchar(128) DEFAULT NULL,
  `user_self_description` text COMMENT '自我介紹',
  `user_gender` tinyint(1) NOT NULL COMMENT '0=F,1=M',
  `user_pic_path` varchar(256) NOT NULL COMMENT '大頭貼存取路徑'
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
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
-- Table structure for table `user_case_basic`
--

CREATE TABLE `user_case_basic` (
  `case_id` int(11) NOT NULL,
  `case_name` varchar(128) NOT NULL,
  `case_recruit_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '開始招募時間(預設為案件發佈時間)',
  `case_recruit_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '結束招募時間',
  `case_recruit_progress` int(11) NOT NULL COMMENT '招募進度(%數)',
  `case_work_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '實際開始工作時間',
  `case_work_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '實際工作結束時間',
  `case_member_count` int(11) NOT NULL DEFAULT '1' COMMENT '成員數目',
  `case_pay_min` int(11) NOT NULL DEFAULT '0' COMMENT '報酬底限',
  `case_pay_max` int(11) NOT NULL COMMENT '報酬上限',
  `case_description` text NOT NULL COMMENT '案件描述',
  `case_owner_id` int(11) NOT NULL COMMENT '發案人',
  `case_last_edit` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最近編輯時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_company`
--

CREATE TABLE `user_company` (
  `user_company_id` int(11) NOT NULL,
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
-- Table structure for table `user_tag`
--

CREATE TABLE `user_tag` (
  `user_tag_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_track_case`
--

CREATE TABLE `user_track_case` (
  `track_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `case_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_track_user`
--

CREATE TABLE `user_track_user` (
  `track_id` int(11) NOT NULL,
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
  ADD KEY `album_img_ibfk_1` (`album_id`);

--
-- Indexes for table `case_apply`
--
ALTER TABLE `case_apply`
  ADD PRIMARY KEY (`apply_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `from_user_id` (`user_id`);

--
-- Indexes for table `case_category`
--
ALTER TABLE `case_category`
  ADD PRIMARY KEY (`case_category_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `case_company`
--
ALTER TABLE `case_company`
  ADD PRIMARY KEY (`case_company_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `case_invite`
--
ALTER TABLE `case_invite`
  ADD PRIMARY KEY (`invite_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `to_user_id` (`user_id`);

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
  ADD PRIMARY KEY (`case_tag_id`),
  ADD KEY `case_id` (`case_id`),
  ADD KEY `tag_id` (`tag_id`),
  ADD KEY `tag_name` (`tag_name`);

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
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `from_user_id` (`from_user_id`),
  ADD KEY `to_user_id` (`to_user_id`);

--
-- Indexes for table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`tag_id`),
  ADD UNIQUE KEY `tag_name` (`tag_name`),
  ADD KEY `updater_user_id` (`updater_user_id`),
  ADD KEY `category_id` (`category_id`);

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
-- Indexes for table `user_case_basic`
--
ALTER TABLE `user_case_basic`
  ADD PRIMARY KEY (`case_id`),
  ADD KEY `case_recruitor_id` (`case_owner_id`);

--
-- Indexes for table `user_company`
--
ALTER TABLE `user_company`
  ADD PRIMARY KEY (`user_company_id`),
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
-- Indexes for table `user_tag`
--
ALTER TABLE `user_tag`
  ADD PRIMARY KEY (`user_tag_id`),
  ADD KEY `tag_id` (`tag_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_track_case`
--
ALTER TABLE `user_track_case`
  ADD PRIMARY KEY (`track_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `case_id` (`case_id`);

--
-- Indexes for table `user_track_user`
--
ALTER TABLE `user_track_user`
  ADD PRIMARY KEY (`track_id`),
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
-- AUTO_INCREMENT for table `case_apply`
--
ALTER TABLE `case_apply`
  MODIFY `apply_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `case_category`
--
ALTER TABLE `case_category`
  MODIFY `case_category_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `case_company`
--
ALTER TABLE `case_company`
  MODIFY `case_company_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `case_invite`
--
ALTER TABLE `case_invite`
  MODIFY `invite_id` int(11) NOT NULL AUTO_INCREMENT;
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
-- AUTO_INCREMENT for table `case_tag`
--
ALTER TABLE `case_tag`
  MODIFY `case_tag_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `company_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tag`
--
ALTER TABLE `tag`
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
-- AUTO_INCREMENT for table `user_case_basic`
--
ALTER TABLE `user_case_basic`
  MODIFY `case_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_company`
--
ALTER TABLE `user_company`
  MODIFY `user_company_id` int(11) NOT NULL AUTO_INCREMENT;
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
-- AUTO_INCREMENT for table `user_tag`
--
ALTER TABLE `user_tag`
  MODIFY `user_tag_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_track_case`
--
ALTER TABLE `user_track_case`
  MODIFY `track_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user_track_user`
--
ALTER TABLE `user_track_user`
  MODIFY `track_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `album_img`
--
ALTER TABLE `album_img`
  ADD CONSTRAINT `album_img_ibfk_1` FOREIGN KEY (`album_id`) REFERENCES `user_album` (`album_id`) ON DELETE CASCADE;

--
-- Constraints for table `case_apply`
--
ALTER TABLE `case_apply`
  ADD CONSTRAINT `case_apply_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_apply_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `case_category`
--
ALTER TABLE `case_category`
  ADD CONSTRAINT `case_category_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `case_company`
--
ALTER TABLE `case_company`
  ADD CONSTRAINT `case_company_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_company_ibfk_2` FOREIGN KEY (`company_id`) REFERENCES `company` (`company_id`);

--
-- Constraints for table `case_invite`
--
ALTER TABLE `case_invite`
  ADD CONSTRAINT `case_invite_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`),
  ADD CONSTRAINT `case_invite_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

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
  ADD CONSTRAINT `case_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`),
  ADD CONSTRAINT `case_tag_ibfk_3` FOREIGN KEY (`tag_name`) REFERENCES `tag` (`tag_name`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `tag`
--
ALTER TABLE `tag`
  ADD CONSTRAINT `tag_ibfk_2` FOREIGN KEY (`updater_user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `tag_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Constraints for table `user_album`
--
ALTER TABLE `user_album`
  ADD CONSTRAINT `user_album_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_capacity`
--
ALTER TABLE `user_capacity`
  ADD CONSTRAINT `user_capacity_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_case_basic`
--
ALTER TABLE `user_case_basic`
  ADD CONSTRAINT `user_case_basic_ibfk_1` FOREIGN KEY (`case_owner_id`) REFERENCES `user_account` (`user_id`);

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
-- Constraints for table `user_phone`
--
ALTER TABLE `user_phone`
  ADD CONSTRAINT `user_phone_ibfk_1` FOREIGN KEY (`user_phone_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_rating`
--
ALTER TABLE `user_rating`
  ADD CONSTRAINT `user_rating_ibfk_1` FOREIGN KEY (`from_user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `user_rating_ibfk_2` FOREIGN KEY (`to_user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_tag`
--
ALTER TABLE `user_tag`
  ADD CONSTRAINT `user_tag_ibfk_1` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`),
  ADD CONSTRAINT `user_tag_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`);

--
-- Constraints for table `user_track_case`
--
ALTER TABLE `user_track_case`
  ADD CONSTRAINT `user_track_case_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `user_track_case_ibfk_2` FOREIGN KEY (`case_id`) REFERENCES `user_case_basic` (`case_id`);

--
-- Constraints for table `user_track_user`
--
ALTER TABLE `user_track_user`
  ADD CONSTRAINT `user_track_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_account` (`user_id`),
  ADD CONSTRAINT `user_track_user_ibfk_2` FOREIGN KEY (`tracking_user_id`) REFERENCES `user_account` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
