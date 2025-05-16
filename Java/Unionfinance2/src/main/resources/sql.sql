CREATE
DATABASE IF NOT EXISTS labour_db;
USE
labour_db;

-- balance 表（注意复合主键字段与类定义可能存在冲突）
CREATE TABLE balance
(
    in_union            VARCHAR(30)    NOT NULL,
    income              DECIMAL(18, 1) NOT NULL,
    `IFNULL(zc.cost,0)` DECIMAL(18, 1) NOT NULL,
    balance             DECIMAL(18, 1) NOT NULL,
    PRIMARY KEY (in_union, income, `IFNULL(zc.cost,0)`, balance)
) ENGINE=InnoDB;

-- z_cost 表
CREATE TABLE z_cost
(
    co_id       INT AUTO_INCREMENT PRIMARY KEY,
    co_time     VARCHAR(30),
    co_entry    VARCHAR(30),
    co_money DOUBLE,
    co_operator VARCHAR(8),
    co_fortor   VARCHAR(8),
    co_union    VARCHAR(30),
    co_remark   VARCHAR(255)
) ENGINE=InnoDB;

-- z_entry 表
CREATE TABLE z_entry
(
    en_id   INT AUTO_INCREMENT PRIMARY KEY,
    en_type VARCHAR(8),
    en_name VARCHAR(50)
) ENGINE=InnoDB;

-- z_income 表
CREATE TABLE z_income
(
    in_id       INT AUTO_INCREMENT PRIMARY KEY,
    in_time     VARCHAR(30),
    in_entry    VARCHAR(50),
    in_money DOUBLE,
    in_operator VARCHAR(8),
    in_union    VARCHAR(30),
    in_remark   VARCHAR(255)
) ENGINE=InnoDB;

-- z_user 表
CREATE TABLE z_user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    account  VARCHAR(16),
    password VARCHAR(16),
    identity VARCHAR(50),
    name     VARCHAR(8),
    number   VARCHAR(13),
    company  VARCHAR(30)
) ENGINE=InnoDB;