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

INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('Bojo', 'harrison'),('bill', 'harrison'),('jones', 'mcgee'),('steven', 'redgrave'),('willy', 'bower');
INSERT INTO `ims`.`items` (`item_name`, `price`) VALUES ('beer', 1.99),('sprite', 1.99),('cola', 5.03),('vodka', 1.99),('wine', 2.01);