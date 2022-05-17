-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fog
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fog` DEFAULT CHARACTER SET utf8 ;
USE `fog` ;

-- -----------------------------------------------------
-- Table `fog`.`coverage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`coverage` (
                                                `coverage_id` INT NOT NULL AUTO_INCREMENT,
                                                `coverage` INT NOT NULL,
                                                `coverage_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                PRIMARY KEY (`coverage_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`dimensions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`dimensions` (
                                                  `dimensions_id` INT NOT NULL AUTO_INCREMENT,
                                                  `length` INT NOT NULL,
                                                  `width` INT NOT NULL,
                                                  `height` INT NOT NULL,
                                                  PRIMARY KEY (`dimensions_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`shed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`shed` (
                                            `shed_id` INT NOT NULL AUTO_INCREMENT,
                                            `width` INT NOT NULL,
                                            `length` INT NOT NULL,
                                            `placement` VARCHAR(10) NOT NULL,
                                            PRIMARY KEY (`shed_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


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
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`carport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`carport` (
                                               `carport_id` INT NOT NULL AUTO_INCREMENT,
                                               `coverage_id` INT NOT NULL,
                                               `user_id` INT NOT NULL,
                                               `dimensions_id` INT NOT NULL,
                                               `shed_id` INT NULL DEFAULT NULL,
                                               `hasShed` TINYINT NOT NULL DEFAULT '0',
                                               `isConfirmed` TINYINT NOT NULL DEFAULT '0',
                                               `carport_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                               PRIMARY KEY (`carport_id`),
                                               INDEX `fk_carport_shed_idx` (`shed_id` ASC) VISIBLE,
                                               INDEX `fk_carport_user1_idx` (`user_id` ASC) VISIBLE,
                                               INDEX `fk_carport_coverage1_idx` (`coverage_id` ASC) VISIBLE,
                                               INDEX `fk_carport_dimensions1_idx` (`dimensions_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_carport_coverage1`
                                                   FOREIGN KEY (`coverage_id`)
                                                       REFERENCES `fog`.`coverage` (`coverage_id`),
                                               CONSTRAINT `fk_carport_dimensions1`
                                                   FOREIGN KEY (`dimensions_id`)
                                                       REFERENCES `fog`.`dimensions` (`dimensions_id`),
                                               CONSTRAINT `fk_carport_shed`
                                                   FOREIGN KEY (`shed_id`)
                                                       REFERENCES `fog`.`shed` (`shed_id`),
                                               CONSTRAINT `fk_carport_user1`
                                                   FOREIGN KEY (`user_id`)
                                                       REFERENCES `fog`.`user` (`user_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`productvariant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`productvariant` (
                                                      `productvariant_id` INT NOT NULL AUTO_INCREMENT,
                                                      `length` INT NULL DEFAULT NULL,
                                                      `width` INT NULL DEFAULT NULL,
                                                      `heigth` INT NULL DEFAULT NULL,
                                                      `diameter` DOUBLE NULL DEFAULT NULL,
                                                      PRIMARY KEY (`productvariant_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`unit_scale`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`unit_scale` (
                                                  `unit_scale_id` INT NOT NULL AUTO_INCREMENT,
                                                  `unit_scale` VARCHAR(45) NOT NULL,
                                                  PRIMARY KEY (`unit_scale_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`usement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`usement` (
                                               `usement_id` INT NOT NULL AUTO_INCREMENT,
                                               `usement_description` VARCHAR(45) NOT NULL,
                                               PRIMARY KEY (`usement_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`product_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`product_description` (
                                                           `product_description_id` INT NOT NULL AUTO_INCREMENT,
                                                           `product_description` VARCHAR(45) NOT NULL,
                                                           `unit_price` DOUBLE NOT NULL,
                                                           PRIMARY KEY (`product_description_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fog`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`product` (
                                               `product_id` INT NOT NULL AUTO_INCREMENT,
                                               `product_description_id` INT NOT NULL,
                                               `productvariant_id` INT NOT NULL,
                                               `unit_scale_id` INT NOT NULL,
                                               `usement_id` INT NOT NULL,
                                               PRIMARY KEY (`product_id`),
                                               INDEX `fk_product_productvariant1_idx` (`productvariant_id` ASC) VISIBLE,
                                               INDEX `fk_product_unit_scale_idx` (`unit_scale_id` ASC) VISIBLE,
                                               INDEX `fk_product_usement1_idx` (`usement_id` ASC) VISIBLE,
                                               INDEX `fk_product_product_description1_idx` (`product_description_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_product_productvariant1`
                                                   FOREIGN KEY (`productvariant_id`)
                                                       REFERENCES `fog`.`productvariant` (`productvariant_id`),
                                               CONSTRAINT `fk_product_unit_scale`
                                                   FOREIGN KEY (`unit_scale_id`)
                                                       REFERENCES `fog`.`unit_scale` (`unit_scale_id`),
                                               CONSTRAINT `fk_product_usement1`
                                                   FOREIGN KEY (`usement_id`)
                                                       REFERENCES `fog`.`usement` (`usement_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION,
                                               CONSTRAINT `fk_product_product_description1`
                                                   FOREIGN KEY (`product_description_id`)
                                                       REFERENCES `fog`.`product_description` (`product_description_id`)
                                                       ON DELETE NO ACTION
                                                       ON UPDATE NO ACTION)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `fog`.`material_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`material_line` (
                                                     `material_line_id` INT NOT NULL AUTO_INCREMENT,
                                                     `carport_id` INT NOT NULL,
                                                     `product_id` INT NOT NULL,
                                                     `unit_length` INT NULL,
                                                     `unit_quantity` INT NOT NULL,
                                                     `total_price` DOUBLE NOT NULL,
                                                     PRIMARY KEY (`material_line_id`),
                                                     INDEX `fk_material_line_carport1_idx` (`carport_id` ASC) VISIBLE,
                                                     INDEX `fk_material_line_product1_idx` (`product_id` ASC) VISIBLE,
                                                     CONSTRAINT `fk_material_line_carport1`
                                                         FOREIGN KEY (`carport_id`)
                                                             REFERENCES `fog`.`carport` (`carport_id`),
                                                     CONSTRAINT `fk_material_line_product1`
                                                         FOREIGN KEY (`product_id`)
                                                             REFERENCES `fog`.`product` (`product_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Insert into `fog DB`
-- -----------------------------------------------------
INSERT INTO `fog`.`shed` (`width`, `length`, `placement`) VALUES ('3', '5', 'left');
INSERT INTO `fog`.`shed` (`width`, `length`, `placement`) VALUES ('3', '5', 'right');
INSERT INTO `fog`.`shed` (`width`, `length`, `placement`) VALUES ('5', '5', 'middel');

INSERT INTO `fog`.`user` (`email`, `password`, `role`, `phonenumber`, `address`, `postal_code`) VALUES ('admin@fog.dk', '1234', 'admin', '70707070', 'Peter Knudsensvej', '3650');
INSERT INTO `fog`.`user` (`email`, `password`, `role`, `phonenumber`, `address`, `postal_code`) VALUES ('kunde1@fog.dk', '1234', 'kunde', '12345678', 'Envej 1', '3600');
INSERT INTO `fog`.`user` (`email`, `password`, `role`, `phonenumber`, `address`, `postal_code`) VALUES ('kunde2@fog.dk', '4321', 'kunde', '23456789', 'Tovej 2', '2800');

INSERT INTO `fog`.`coverage` (`coverage`) VALUES ('40');
INSERT INTO `fog`.`coverage` (`coverage`) VALUES ('25');

INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('understernbrædder til for & bag ende');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('understernbrædder til siderne');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('oversternbrædder til forenden');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('oversternbrædder til siderne');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til z på bagside af dør');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('løsholter til skur gavle');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('løsholter til skur sider');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('remme i sider, sadles ned i stolper');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('spær, monteres på rem');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('stolper nedgraves 90 cm. i jord');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til beklædning af skur 1 på 2');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('vandbrædt på stern i sider');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('vandbrædt på stern i forende');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('tagplader monteres på spær');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('skruer til tagplader');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til vindkryds på spær');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af spær på rem');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af stern & vandbrædt');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af universalbeslag + hulbånd');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af rem på stolper');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af yderste beklædning');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af inderste beklædning');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til lås på dør i skur');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til skurdør');
INSERT INTO `fog`.`usement` (`usement_description`) VALUES ('til montering af løsholter i skur');

INSERT INTO `fog`.`unit_scale` (`unit_scale`) VALUES ('stk');
INSERT INTO `fog`.`unit_scale` (`unit_scale`) VALUES ('pakke');
INSERT INTO `fog`.`unit_scale` (`unit_scale`) VALUES ('rulle');
INSERT INTO `fog`.`unit_scale` (`unit_scale`) VALUES ('sæt');

INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('200', '25');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('200', '25');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('125', '25');

INSERT INTO `fog`.`product_description` (product_description, unit_price) VALUES ('trykimp. Brædt','10');
INSERT INTO `fog`.`product_description` (product_description, unit_price) VALUES ('trykimp. Brædt', '15');

INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`,`unit_scale_id`, `usement_id`) VALUES ('1', '1','1','1');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`,`unit_scale_id`, `usement_id`) VALUES ('2', '1','1','2');

INSERT INTO `fog`.`dimensions` (`length`, `width`, `height`) VALUES ('780', '600', '210');
INSERT INTO `fog`.`dimensions` (`length`, `width`, `height`) VALUES ('600', '500', '210');

INSERT INTO `fog`.`carport` (`coverage_id`, `user_id`, `dimensions_id`, `shed_id`, `hasShed`, `isConfirmed`) VALUES ('1', '2', '1', '1', '1', '0');
INSERT INTO `fog`.`carport` (`coverage_id`, `user_id`, `dimensions_id`, `hasShed`, `isConfirmed`) VALUES ('2', '3', '1', '0', '0');

INSERT INTO `fog`.`material_line` (`carport_id`, `product_id`, `unit_length`,`unit_quantity`, `total_price`) VALUES ('1', '1', '360', '7', '70');
INSERT INTO `fog`.`material_line` (`carport_id`, `product_id`, `unit_length`,`unit_quantity`, `total_price`) VALUES ('2', '2', '540', '4', '60');
