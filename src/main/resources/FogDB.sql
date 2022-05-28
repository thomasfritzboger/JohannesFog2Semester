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
                                                `coverage_id` INT NOT NULL,
                                                `coverage` INT NOT NULL,
                                                `coverage_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                                PRIMARY KEY (`coverage_id`))
    ENGINE = InnoDB
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
                                            PRIMARY KEY (`user_id`),
                                            UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
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
                                               `width` INT NOT NULL,
                                               `length` INT NOT NULL,
                                               `height` INT NOT NULL,
                                               `roof_type` VARCHAR(45) NOT NULL,
                                               `shed_id` INT NULL DEFAULT NULL,
                                               `hasShed` TINYINT NOT NULL DEFAULT '0',
                                               `carport_price` DOUBLE NOT NULL DEFAULT '0',
                                               `isConfirmed` TINYINT NOT NULL DEFAULT '0',
                                               `carport_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                               PRIMARY KEY (`carport_id`),
                                               INDEX `fk_carport_shed_idx` (`shed_id` ASC) VISIBLE,
                                               INDEX `fk_carport_user1_idx` (`user_id` ASC) VISIBLE,
                                               INDEX `fk_carport_coverage1_idx` (`coverage_id` ASC) VISIBLE,
                                               CONSTRAINT `fk_carport_coverage1`
                                                   FOREIGN KEY (`coverage_id`)
                                                       REFERENCES `fog`.`coverage` (`coverage_id`),
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
-- Table `fog`.`product_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fog`.`product_description` (
                                                           `product_description_id` INT NOT NULL AUTO_INCREMENT,
                                                           `product_description` VARCHAR(45) NOT NULL,
                                                           `unit_price` DOUBLE NOT NULL,
                                                           PRIMARY KEY (`product_description_id`))
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
                                                      `height` INT NULL DEFAULT NULL,
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
                                               CONSTRAINT `fk_product_product_description1`
                                                   FOREIGN KEY (`product_description_id`)
                                                       REFERENCES `fog`.`product_description` (`product_description_id`),
                                               CONSTRAINT `fk_product_productvariant1`
                                                   FOREIGN KEY (`productvariant_id`)
                                                       REFERENCES `fog`.`productvariant` (`productvariant_id`),
                                               CONSTRAINT `fk_product_unit_scale`
                                                   FOREIGN KEY (`unit_scale_id`)
                                                       REFERENCES `fog`.`unit_scale` (`unit_scale_id`),
                                               CONSTRAINT `fk_product_usement1`
                                                   FOREIGN KEY (`usement_id`)
                                                       REFERENCES `fog`.`usement` (`usement_id`))
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
                                                     `unit_length` INT NULL DEFAULT NULL,
                                                     `unit_quantity` INT NOT NULL,
                                                     `total_line_price` DOUBLE NOT NULL,
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
INSERT INTO `fog`.`user` (`email`, `password`, `role`, `phonenumber`, `address`, `postal_code`) VALUES ('admin@fog.dk', '1234', 'admin', '70707070', 'Peter Knudsensvej', '3650');
INSERT INTO `fog`.`user` (`email`, `password`, `role`, `phonenumber`, `address`, `postal_code`) VALUES ('kunde1@fog.dk', '1234', 'kunde', '12345678', 'Envej 1', '3600');
INSERT INTO `fog`.`user` (`email`, `password`, `role`, `phonenumber`, `address`, `postal_code`) VALUES ('kunde2@fog.dk', '4321', 'kunde', '23456789', 'Tovej 2', '2800');

INSERT INTO `fog`.`coverage` (`coverage_id`,`coverage`) VALUES ('25','25');
INSERT INTO `fog`.`coverage` (`coverage_id`,`coverage`) VALUES ('30','30');
INSERT INTO `fog`.`coverage` (`coverage_id`,`coverage`) VALUES ('35','35');
INSERT INTO `fog`.`coverage` (`coverage_id`,`coverage`) VALUES ('40','40');


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
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('125', '25');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('73', '38');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('95', '45');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('195', '45');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('97', '97');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('100', '19');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('1', '20');
INSERT INTO `fog`.`productvariant` (`length`) VALUES ('190');
INSERT INTO `fog`.`productvariant` (`length`, `diameter`) VALUES ('60', '4.5');
INSERT INTO `fog`.`productvariant` (`length`, `diameter`) VALUES ('50', '4.0');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('120', '10');
INSERT INTO `fog`.`productvariant` (`length`, `width`, `height`) VALUES ('40', '40', '11');
INSERT INTO `fog`.`productvariant` (`length`, `diameter`) VALUES ('70', '4.5');
INSERT INTO `fog`.`productvariant` (`length`, `diameter`) VALUES ('50', '4.5');
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('75', '50');
INSERT INTO `fog`.`productvariant` (`length`) VALUES ('390');
INSERT INTO `fog`.`productvariant` (`length`) VALUES ('35');
INSERT INTO `fog`.`productvariant` (`length`) VALUES (null);
INSERT INTO `fog`.`productvariant` (`length`, `width`) VALUES ('100', '60');

INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('25x200 mm. trykimp. Brædt', '54.96');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('25x125 mm. trykimp. Brædt', '35.68');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('38x73 mm lægte ubh', '35.68');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('45x95 mm. reglar ubh', '59.91');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('45x195 mm. spærtræ ubh', '67.82');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('97x97 mm. trykimp. stolpe', '54.25');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('19x100 mm. trykimp. brædt', '21.39');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Plastmo Ecolite blåtonet', '37.20');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Plastmo bundskruer 200 stk.', '282.11');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Hulbånd 1x20 mm. 10 mtr.', '206.43');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Universal 190 mm højre', '39.25');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Universal 190 mm venstre', '39.25');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('4,5 x 60 mm. skruer 200 stk.', '71.39');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('4,0 x 50 mm. beslagskruer 250 stk.', '55.61');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Bræddebolt 10 x 120 mm.', '10.26');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Firkantskiver 40x40x11mm', '6.41');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('4,5 x 70 mm. skruer 400 stk.', '142.14');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('4,5 x 50 mm. skruer 300 stk.', '77.86');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Stalddørsgreb 50x75', '185.00');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('T hængsel 390 mm', '80.83');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Vinkelbeslag 35', '8.63');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Cembrit tagplade 1090x1180', '119.95');
INSERT INTO `fog`.`product_description` (`product_description`, `unit_price`) VALUES ('Eternitskrue 6.0x100', '123.04');


INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('1', '1','1','1');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('1', '1','1','2');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('2', '2', '1', '3');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('2', '2', '1', '4');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('3', '3', '1', '5');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('4', '4', '1', '6');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('4', '4', '1', '7');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('5', '5', '1', '8');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('5', '5', '1', '9');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('6', '6', '1', '10');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('7', '7', '1', '11');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('7', '7', '1', '12');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('7', '7', '1', '13');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('8', '19', '1', '14');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('9', '19', '2', '15');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('10', '8', '3', '16');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('11', '9', '1', '17');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('12', '9', '1', '17');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('13', '10', '2', '18');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('14', '11', '2', '19');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('15', '12', '1', '20');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('16', '13', '1', '20');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('17', '14', '3', '21');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('18', '15', '3', '22');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('19', '16', '4', '23');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('20', '17', '1', '24');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('21', '18', '1', '25');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('22', '19', '1', '14');
INSERT INTO `fog`.`product` (`product_description_id`, `productvariant_id`, `unit_scale_id`, `usement_id`) VALUES ('23', '19', '2', '15');

-- -----------------------------------------------------
-- Views
-- -----------------------------------------------------
CREATE VIEW productdto AS
SELECT p.product_id, d.product_description, d.unit_price, v.length, v.width, v.height, v.diameter, m.usement_description as description, s.unit_scale as scale
FROM product as p
         inner join product_description as d
                    using (product_description_id)
         inner join productvariant as v
                    using (productvariant_id)
         inner join usement as m
                    using (usement_id)
         inner join unit_scale as s
                    using (unit_scale_id);

create view requestdto AS
SELECT ca.carport_id, ca.user_id, ca.carport_created, co.coverage, ca.carport_price AS price
FROM carport AS ca
         INNER JOIN coverage AS co
                    USING (coverage_id)
WHERE ca.isConfirmed=0
group by carport_id;