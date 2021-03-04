
-- delete my sql schema
-- SELECT concat('DROP TABLE IF EXISTS `', table_name, '`;')
-- FROM information_schema.tables
-- WHERE table_schema = 'fragrancenetservice';

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `authority`;
DROP TABLE IF EXISTS `databasechangelog`;
DROP TABLE IF EXISTS `databasechangeloglock`;
DROP TABLE IF EXISTS `drop_ship_dtls`;
DROP TABLE IF EXISTS `inventory_dtls`;
DROP TABLE IF EXISTS `payment`;
DROP TABLE IF EXISTS `persistent_audit_event`;
DROP TABLE IF EXISTS `persistent_audit_evt_data`;
DROP TABLE IF EXISTS `placement`;
DROP TABLE IF EXISTS `product_dtls`;
DROP TABLE IF EXISTS `product_dtls_product_tag`;
DROP TABLE IF EXISTS `product_rating`;
DROP TABLE IF EXISTS `product_tag`;
DROP TABLE IF EXISTS `product_user_color`;
DROP TABLE IF EXISTS `purchase_order`;
DROP TABLE IF EXISTS `purchase_order_dsct_code`;
DROP TABLE IF EXISTS `purchase_order_item`;
DROP TABLE IF EXISTS `seller_details`;
DROP TABLE IF EXISTS `shopping_cart`;
DROP TABLE IF EXISTS `shopping_cart_item`;
DROP TABLE IF EXISTS `tax_item`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `user_address`;
DROP TABLE IF EXISTS `user_authority`;
DROP TABLE IF EXISTS `wish_list_cart`;
DROP TABLE IF EXISTS `wish_list_cart_item`;
DROP TABLE IF EXISTS `testimonials`;

SET FOREIGN_KEY_CHECKS = 1;