CREATE TABLE users
(
    user_id           INT AUTO_INCREMENT PRIMARY KEY,
    username          VARCHAR(50)  NOT NULL,
    email             VARCHAR(100) NOT NULL,
    password_hash     VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
