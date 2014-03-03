SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `wichf` DEFAULT CHARACTER SET utf8 ;
USE `wichf` ;

-- -----------------------------------------------------
-- Table `wichf`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`user` ;

CREATE TABLE IF NOT EXISTS `wichf`.`user` (
  `user_id` VARCHAR(10) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `access_level` VARCHAR(45) NOT NULL DEFAULT 'user',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`doctor` ;

CREATE TABLE IF NOT EXISTS `wichf`.`doctor` (
  `doctor_id` INT(11) NOT NULL AUTO_INCREMENT,
  `doctor_name` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`doctor_id`),
  INDEX `fk_Doctor_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Doctor_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `wichf`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`room` ;

CREATE TABLE IF NOT EXISTS `wichf`.`room` (
  `room_id` INT(11) NOT NULL AUTO_INCREMENT,
  `room_no` INT(11) NULL DEFAULT NULL,
  `room_status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`room_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`visit_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`visit_type` ;

CREATE TABLE IF NOT EXISTS `wichf`.`visit_type` (
  `visit_type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `visit_type` VARCHAR(45) NOT NULL,
  `visit_time` INT(11) NOT NULL DEFAULT '20',
  PRIMARY KEY (`visit_type_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`appointment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`appointment` ;

CREATE TABLE IF NOT EXISTS `wichf`.`appointment` (
  `appointment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `time_slot` INT(11) NULL DEFAULT NULL,
  `booked_through` VARCHAR(45) NULL DEFAULT NULL,
  `appointment_date` DATE NULL DEFAULT NULL,
  `booked_date` DATE NULL DEFAULT NULL,
  `doctor_id` INT(11) NULL DEFAULT NULL,
  `room_id` INT(11) NULL DEFAULT NULL,
  `parent_appointment_id` INT(11) NULL DEFAULT NULL,
  `user_id` VARCHAR(10) NULL DEFAULT NULL,
  `start_time` DATETIME NULL DEFAULT NULL,
  `visit_type_id` INT(11) NULL DEFAULT NULL,
  `active` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`appointment_id`),
  INDEX `fk_Appointment_Doctor1_idx` (`doctor_id` ASC),
  INDEX `fk_Appointment_Room1_idx` (`room_id` ASC),
  INDEX `fk_Appointment_Appointment1_idx` (`parent_appointment_id` ASC),
  INDEX `fk_Appointment_User1_idx` (`user_id` ASC),
  INDEX `fk_Appointment_Visit_Type1_idx` (`visit_type_id` ASC),
  CONSTRAINT `fk_Appointment_Appointment1`
    FOREIGN KEY (`parent_appointment_id`)
    REFERENCES `wichf`.`appointment` (`appointment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Doctor1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `wichf`.`doctor` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `wichf`.`room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `wichf`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Appointment_Visit_Type1`
    FOREIGN KEY (`visit_type_id`)
    REFERENCES `wichf`.`visit_type` (`visit_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`doctor_non_availability`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`doctor_non_availability` ;

CREATE TABLE IF NOT EXISTS `wichf`.`doctor_non_availability` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `doctor_non_available_date` DATE NULL DEFAULT NULL,
  `doctor_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Doctor_Non_Availability_Doctor1` (`doctor_id` ASC),
  CONSTRAINT `fk_Doctor_Non_Availability_Doctor1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `wichf`.`doctor` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`nurse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`nurse` ;

CREATE TABLE IF NOT EXISTS `wichf`.`nurse` (
  `nurse_id` INT(11) NOT NULL AUTO_INCREMENT,
  `nurse_name` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `user_id` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`nurse_id`),
  INDEX `fk_Nurse_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Nurse_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `wichf`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`patient` ;

CREATE TABLE IF NOT EXISTS `wichf`.`patient` (
  `patient_id` INT(11) NOT NULL AUTO_INCREMENT,
  `h_card_no` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `gender` VARCHAR(1) NULL DEFAULT NULL,
  `phone_no` DECIMAL(10,0) NULL DEFAULT NULL,
  `patient_name` VARCHAR(45) NULL DEFAULT NULL,
  `annual_checkup` VARCHAR(1) NOT NULL,
  `user_id` VARCHAR(10) NOT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  INDEX `fk_Patient_User_idx` (`user_id` ASC),
  CONSTRAINT `fk_Patient_User`
    FOREIGN KEY (`user_id`)
    REFERENCES `wichf`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`payment` ;

CREATE TABLE IF NOT EXISTS `wichf`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `card_num` VARCHAR(16) NOT NULL,
  `type_of_card` VARCHAR(45) NULL DEFAULT NULL,
  `card_holder_name` VARCHAR(45) NOT NULL,
  `expiry_date` VARCHAR(45) NULL DEFAULT NULL,
  `appointment_id` INT(11) NOT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_appoint_id_idx` (`appointment_id` ASC),
  CONSTRAINT `fk_appoint_id`
    FOREIGN KEY (`appointment_id`)
    REFERENCES `wichf`.`appointment` (`appointment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `wichf`.`room_non_availablity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `wichf`.`room_non_availablity` ;

CREATE TABLE IF NOT EXISTS `wichf`.`room_non_availablity` (
  `room_non_availablity_date` DATE NULL DEFAULT NULL,
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `room_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Room_Non_Availablity_Room1_idx` (`room_id` ASC),
  CONSTRAINT `fk_Room_Non_Availablity_Room1`
    FOREIGN KEY (`room_id`)
    REFERENCES `wichf`.`room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
