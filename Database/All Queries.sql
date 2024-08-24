-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema InterviewApp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema InterviewApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `InterviewApp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `InterviewApp` ;

-- -----------------------------------------------------
-- Table `InterviewApp`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InterviewApp`.`Admin` (
  `Adminid` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `Role` VARCHAR(10) NOT NULL,
  `EmptyCol1` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol2` INT NULL DEFAULT NULL,
  `EmptyCol3` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Adminid`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1001
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;



-- -----------------------------------------------------
-- Table `InterviewApp`.`Interviewers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InterviewApp`.`Interviewers` (
  `Interviewerid` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `Mobile` VARCHAR(20) NULL DEFAULT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Dob` DATE NOT NULL,
  `CompanyPosition` VARCHAR(45) NULL DEFAULT NULL,
  `QualifiedDegree` VARCHAR(20) NOT NULL,
  `Profile` VARCHAR(45) NULL DEFAULT NULL,
  `ProfileUpdated` DATETIME NULL DEFAULT NULL,
  `Gender` VARCHAR(9) NOT NULL,
  `Role` VARCHAR(15) NULL DEFAULT NULL,
  `Adminid` INT NULL DEFAULT NULL,
  `EmptyCol1` INT NULL DEFAULT NULL,
  `EmptyCol2` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol3` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol4` VARCHAR(30) NULL DEFAULT NULL,
  `EmptyCol5` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`Interviewerid`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  INDEX `fk_admin_Interviewers_idx` (`Adminid` ASC) VISIBLE,
  CONSTRAINT `fk_admin_control_1`
    FOREIGN KEY (`Adminid`)
    REFERENCES `InterviewApp`.`Admin` (`Adminid`))
ENGINE = InnoDB
AUTO_INCREMENT = 2001
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `InterviewApp`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InterviewApp`.`Users` (
  `Userid` INT NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(20) NOT NULL,
  `LastName` VARCHAR(20) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(20) NULL DEFAULT NULL,
  `Mobile` VARCHAR(20) NOT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Dob` DATE NOT NULL,
  `Qualification` VARCHAR(20) NOT NULL,
  `Profile` VARCHAR(45) NULL DEFAULT NULL,
  `LastModified` DATETIME NULL DEFAULT NULL,
  `Gender` VARCHAR(9) NOT NULL,
  `Role` VARCHAR(9) NOT NULL,
  `Adminid` INT NULL DEFAULT NULL,
  `EmptyCol1` INT NULL DEFAULT NULL,
  `EmptyCol2` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol3` TINYINT NULL DEFAULT NULL,
  `EmptyCol4` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol5` VARCHAR(30) NULL DEFAULT NULL,
  `EmptyCol` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`Userid`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  INDEX `fk_admin_user_idx` (`Adminid` ASC) VISIBLE,
  CONSTRAINT `fk_admin_control`
    FOREIGN KEY (`Adminid`)
    REFERENCES `InterviewApp`.`Admin` (`Adminid`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `InterviewApp`.`FeedbackRecords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InterviewApp`.`FeedbackRecords` (
  `Feedbackid` INT NOT NULL AUTO_INCREMENT,
  `Interviewerid` INT NULL DEFAULT NULL,
  `Userid` INT NULL DEFAULT NULL,
  `Feedback` TEXT NULL DEFAULT NULL,
  `EmptyCol1` INT NULL DEFAULT NULL,
  `EmptyCol2` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol3` VARCHAR(45) NULL DEFAULT NULL,
  `EmptyCol4` VARCHAR(30) NULL DEFAULT NULL,
  `EmptyCol5` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`Feedbackid`),
  INDEX `fk_interviewerid_idx` (`Interviewerid` ASC) VISIBLE,
  INDEX `fk_userid_idx` (`Userid` ASC) VISIBLE,
  CONSTRAINT `fk_interviewer_feedback`
    FOREIGN KEY (`Interviewerid`)
    REFERENCES `InterviewApp`.`Interviewers` (`Interviewerid`),
  CONSTRAINT `fk_user_feedback`
    FOREIGN KEY (`Userid`)
    REFERENCES `InterviewApp`.`Users` (`Userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `InterviewApp`.`InterviewRecords`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InterviewApp`.`InterviewRecords` (
  `Interviewid` INT NOT NULL,
  `Interviewerid` INT NOT NULL,
  `Userid` INT NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `InterviewApp`.`InterviewSceduled`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `InterviewApp`.`InterviewSceduled` (
  `Interviewid` INT NOT NULL AUTO_INCREMENT,
  `Date` DATETIME NULL DEFAULT NULL,
  `Title` VARCHAR(20) NULL DEFAULT NULL,
  `Interviewerid` INT NULL DEFAULT NULL,
  `Userid` INT NULL DEFAULT NULL,
  `EmptyCol1` INT NULL DEFAULT NULL,
  `EmptyCol2` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`Interviewid`),
  INDEX `fk_interviewerid_idx` (`Interviewerid` ASC) VISIBLE,
  INDEX `fk_userid_idx` (`Userid` ASC) VISIBLE,
  CONSTRAINT `fk_interviewers`
    FOREIGN KEY (`Interviewerid`)
    REFERENCES `InterviewApp`.`Interviewers` (`Interviewerid`),
  CONSTRAINT `fk_users`
    FOREIGN KEY (`Userid`)
    REFERENCES `InterviewApp`.`Users` (`Userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 3002
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;





SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
