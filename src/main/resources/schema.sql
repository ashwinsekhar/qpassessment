CREATE TABLE IF NOT EXISTS groceries(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) UNIQUE DEFAULT NULL,
    `price` decimal(10, 2) DEFAULT NULL,
    `inventory` bigint(20) NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;