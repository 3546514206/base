CREATE DATABASE `order_center` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE order_center;

-- 创建 users 表
CREATE TABLE users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX      idx_name_email (name, email), -- 索引覆盖场景
    INDEX      idx_created_at (created_at)   -- 针对时间的查询
);

-- 创建 orders 表
CREATE TABLE orders
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id      BIGINT         NOT NULL,
    order_date   DATETIME DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(50)    NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    INDEX        idx_user_id (user_id),                     -- 最左前缀法则示范
    INDEX        idx_order_date_status (order_date, status) -- 查询组合索引
);

-- 创建 order_items 表
CREATE TABLE order_items
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id   BIGINT         NOT NULL,
    product_id BIGINT         NOT NULL,
    quantity   INT            NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    INDEX      idx_order_product (order_id, product_id), -- 联表查询时提升性能
    INDEX      idx_price_quantity (price, quantity)      -- 数量和价格查询优化
);



DELIMITER
//

CREATE PROCEDURE GenerateData()
BEGIN
    DECLARE
i INT DEFAULT 0;
    DECLARE
user_id INT;
    DECLARE
order_id INT;

    WHILE
i < 1000000 DO
        -- 插入 user 数据
        INSERT INTO users (name, email)
        VALUES (CONCAT('user', FLOOR(RAND() * 1000000)), CONCAT(FLOOR(RAND() * 1000000), '@example.com'));

        SET
user_id = LAST_INSERT_ID();

        -- 插入 order 数据
INSERT INTO orders (user_id, status, total_amount)
VALUES (user_id, 'completed', RAND() * 1000);

SET
order_id = LAST_INSERT_ID();

        -- 插入 order_items 数据
INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES (order_id,
        FLOOR(RAND() * 10000),
        FLOOR(RAND() * 100),
        RAND() * 100);

SET
i = i + 1;
END WHILE;
END
//
DELIMITER ;

-- 调用存储过程以生成数据
CALL GenerateData();