INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`item_name`, `price`) VALUES ('Apple Juice', 2.28);
INSERT INTO `orders` (`customer_id`, `cost`, `shipment_date`) VALUES (1, 25.73, '03/07/21');
INSERT INTO `orders_items` (`order_id`, `item_id`, `item_quantity`) VALUES (1, 1, 2);