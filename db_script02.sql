SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema water
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `water` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `water` ;

-- -----------------------------------------------------
-- Table `water`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `water`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surName` VARCHAR(45) NULL,
  `middleName` VARCHAR(45) NULL,
  `login` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `tariff` FLOAT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `water`.`Adress`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `water`.`Adress` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `building` VARCHAR(45) NULL,
  `appartment` VARCHAR(45) NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Adress_User_idx` (`User_id` ASC),
  CONSTRAINT `fk_Adress_User`
    FOREIGN KEY (`User_id`)
    REFERENCES `water`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `water`.`WaterMeter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `water`.`WaterMeter` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `Adress_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_WaterMeter_Adress1_idx` (`Adress_id` ASC),
  CONSTRAINT `fk_WaterMeter_Adress1`
    FOREIGN KEY (`Adress_id`)
    REFERENCES `water`.`Adress` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `water`.`Indicator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `water`.`Indicator` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NULL,
  `value` INT(11) NULL,
  `isPaid` TINYINT(1) NULL,
  `isPublished` TINYINT(1) NULL,
  `WaterMeter_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Indicator_WaterMeter1_idx` (`WaterMeter_id` ASC),
  CONSTRAINT `fk_Indicator_WaterMeter1`
    FOREIGN KEY (`WaterMeter_id`)
    REFERENCES `water`.`WaterMeter` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
