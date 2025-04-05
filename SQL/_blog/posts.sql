CREATE TABLE posts
(
    post_id          INT AUTO_INCREMENT PRIMARY KEY,
    user_id          INT,
    title            VARCHAR(255) NOT NULL,
    content          TEXT         NOT NULL,
    publication_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
