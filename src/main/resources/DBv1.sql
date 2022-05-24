-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8 ;
USE `fog` ;

-- -----------------------------------------------------
-- Table `fog`.`shed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`shed` (
                                            `shed_id` INT NOT NULL AUTO_INCREMENT,
                                            `width` INT NOT NULL,
                                            `length` INT NOT NULL,
                                            `placement` VARCHAR(10) NOT NULL,
                                            PRIMARY KEY (`shed_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`user` (
                                            `user_id` INT NOT NULL AUTO_INCREMENT,
                                            `email` VARCHAR(45) NOT NULL,
                                            `password` VARCHAR(45) NOT NULL,
                                            `role` VARCHAR(10) NOT NULL,
                                            `phonenumber` INT NOT NULL,
                                            `address` VARCHAR(45) NOT NULL,
                                            `postal_code` INT NOT NULL,
                                            PRIMARY KEY (`user_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`coverage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`coverage` (
                                                `coverage_id` INT NOT NULL AUTO_INCREMENT,
                                                `coverage` INT NOT NULL,
                                                `coverage_created` DATETIME NOT NULL,
                                                PRIMARY KEY (`coverage_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`carport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`carport` (
                                               `carport_id` INT NOT NULL AUTO_INCREMENT,
                                               `coverage_id` INT NOT NULL,
                                               `user_id` INT NOT NULL,
                                               `shed_id` INT NULL,
                                               `hasShed` TINYINT NOT NULL DEFAULT '0',
                                               `isConfirmed` TINYINT NOT NULL DEFAULT '0',
                                               `carport_created` DATETIME NOT NULL,
                                               PRIMARY KEY (`carport_id`),
                                               INDEX `fk_carport_shed_idx` (`shed_id` ASC) VISIBLE,
                                               INDEX `fk_carport_user1_idx` (`user_id` ASC) VISIBLE,
                                               INDEX `fk_carport_coverage1_idx` (`coverage_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_carport_shed`
                                                   FOREIGN KEY (`shed_id`)
                                                       REFERENCES `fog`.`shed` (`shed_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `fk_carport_user1`
                                                   FOREIGN KEY (`user_id`)
                                                       REFERENCES `fog`.`user` (`user_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `fk_carport_coverage1`
                                                   FOREIGN KEY (`coverage_id`)
                                                       REFERENCES `fog`.`coverage` (`coverage_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`productvariant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`productvariant` (
                                                      `productvariant_id` INT NOT NULL AUTO_INCREMENT,
                                                      `length` INT NULL,
                                                      `width` INT NULL,
                                                      `heigth` INT NULL,
                                                      `diameter` DOUBLE NULL,
                                                      `unit_length` INT NULL,
                                                      `unit_amount` INT NULL,
                                                      PRIMARY KEY (`productvariant_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`product` (
                                               `product_id` INT NOT NULL AUTO_INCREMENT,
                                               `productvariant_id` INT NOT NULL,
                                               `unit_scale` VARCHAR(45) NOT NULL,
                                               `product_description` VARCHAR(45) NOT NULL,
                                               `unitprice` DOUBLE NOT NULL,
                                               PRIMARY KEY (`product_id`),
                                               INDEX `fk_product_productvariant1_idx` (`productvariant_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_product_productvariant1`
                                                   FOREIGN KEY (`productvariant_id`)
                                                       REFERENCES `fog`.`productvariant` (`productvariant_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`usement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`usement` (
                                               `usement_id` INT NOT NULL AUTO_INCREMENT,
                                               `usement_description` VARCHAR(45) NOT NULL,
                                               PRIMARY KEY (`usement_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`material_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`material_line` (
                                                     `material_line_id` INT NOT NULL AUTO_INCREMENT,
                                                     `carport_id` INT NOT NULL,
                                                     `product_id` INT NOT NULL,
                                                     `usement_id` INT NOT NULL,
                                                     `quantity` INT NOT NULL,
                                                     `total_price` DOUBLE NOT NULL,
                                                     PRIMARY KEY (`material_line_id`),
                                                     INDEX `fk_material_line_carport1_idx` (`carport_id` ASC) VISIBLE,
                                                     INDEX `fk_material_line_product1_idx` (`product_id` ASC) VISIBLE,
                                                     INDEX `fk_material_line_usement1_idx` (`usement_id` ASC) VISIBLE,
                                                     CONSTRAINT `fk_material_line_carport1`
                                                         FOREIGN KEY (`carport_id`)
                                                             REFERENCES `fog`.`carport` (`carport_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION,
                                                     CONSTRAINT `fk_material_line_product1`
                                                         FOREIGN KEY (`product_id`)
                                                             REFERENCES `fog`.`product` (`product_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION,
                                                     CONSTRAINT `fk_material_line_usement1`
                                                         FOREIGN KEY (`usement_id`)
                                                             REFERENCES `fog`.`usement` (`usement_id`)
                                                             ON DELETE NO ACTION
                                                             ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
