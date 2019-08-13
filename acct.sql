DROP TABLE IF EXISTS `intuit`;
CREATE TABLE `intuit` (
    `seller_id` BIGINT(32) NOT NULL,
    `realm_id` VARCHAR(64) NOT NULL ,
    `auth_code` VARCHAR(255) NOT NULL,
    `access_token` VARCHAR(900) NOT NULL,
    `refresh_token` VARCHAR(255) NOT NULL,
    `expires_in` BIGINT(32) NOT NULL,
    `x_refresh_token_expires_in` BIGINT(32) NOT NULL,
    `refresh_at` VARCHAR(32) NOT NULL,
    `authorize_at` VARCHAR(32) NOT NULL,
    PRIMARY KEY (`seller_id`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
    `seller_id` BIGINT(32) NOT NULL,
    `customer_id` VARCHAR(64) NOT NULL ,
    `customer_name` VARCHAR(255) NOT NULL,
    `created_at` VARCHAR(64) NOT NULL,
    PRIMARY KEY (`seller_id`, `customer_id`)
) ENGINE = InnoDB;