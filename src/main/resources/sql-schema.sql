DROP SCHEMA IF EXISTS `ims`;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `price` DECIMAL(7,2) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    `customer_id` INT(11) DEFAULT NULL,
    `cost` DECIMAL(7,2) DEFAULT NULL,
    `shipment_date` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`order_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders_items` (
    `order_item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `order_id` INT(11) DEFAULT NULL,
    `item_id` INT(11) DEFAULT NULL,
    `item_quantity` INT(11) DEFAULT NULL,
    PRIMARY KEY (`order_item_id`)
);



ALTER TABLE `ims`.`orders` ADD FOREIGN KEY (`customer_id`) REFERENCES `ims`.`customers` (`customer_id`); 
ALTER TABLE `ims`.`orders_items` ADD FOREIGN KEY (`order_id`) REFERENCES `ims`.`orders` (`order_id`);
ALTER TABLE `ims`.`orders_items` ADD FOREIGN KEY (`item_id`) REFERENCES `ims`.`items` (`item_id`);

INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('Boris', 'Harrison'),('Jim', 'Harrison'),('Jones', 'Mcgee'),('Steven', 'Redgrave'),('Willy', 'Bower');
INSERT INTO `ims`.`items` (`item_name`, `price`) VALUES ('Beer', 1.99),('Sprite', 1.99),('Coca Cola', 5.03),('Vodka', 1.99),('Wine', 2.01);