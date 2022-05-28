CREATE SCHEMA IF NOT EXISTS `fog_test` ;
create table fog_test.carport like fog.carport;
create table fog_test.coverage like fog.coverage;
create table fog_test.material_line like fog.material_line;
create table fog_test.product like fog.product;
create table fog_test.product_description like fog.product_description;
create table fog_test.productvariant like fog.productvariant;
create table fog_test.shed like fog.shed;
create table fog_test.unit_scale like fog.unit_scale;
create table fog_test.usement like fog.usement;
create table fog_test.user like fog.user;

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
    VIEW `fog_test`.`requestdto` AS
SELECT
    `ca`.`carport_id` AS `carport_id`,
    `ca`.`user_id` AS `user_id`,
    `ca`.`carport_created` AS `carport_created`,
    `co`.`coverage` AS `coverage`,
    `ca`.`carport_price` AS `price`
FROM
    ((`fog_test`.`carport` `ca`
        JOIN `fog_test`.`coverage` `co` ON ((`ca`.`coverage_id` = `co`.`coverage_id`))))
WHERE
    (`ca`.`isConfirmed` = 0)
GROUP BY `ca`.`carport_id`;

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
    VIEW `fog_test`.`productdto` AS
SELECT
    `p`.`product_id` AS `product_id`,
    `d`.`product_description` AS `product_description`,
    `d`.`unit_price` AS `unit_price`,
    `v`.`length` AS `length`,
    `v`.`width` AS `width`,
    `v`.`height` AS `height`,
    `v`.`diameter` AS `diameter`,
    `m`.`usement_description` AS `description`,
    `s`.`unit_scale` AS `scale`
FROM
    ((((`fog_test`.`product` `p`
        JOIN `fog_test`.`product_description` `d` ON ((`p`.`product_description_id` = `d`.`product_description_id`)))
        JOIN `fog_test`.`productvariant` `v` ON ((`p`.`productvariant_id` = `v`.`productvariant_id`)))
        JOIN `fog_test`.`usement` `m` ON ((`p`.`usement_id` = `m`.`usement_id`)))
        JOIN `fog_test`.`unit_scale` `s` ON ((`p`.`unit_scale_id` = `s`.`unit_scale_id`)));