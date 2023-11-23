CREATE TABLE comments
(
    comment_id   INT AUTO_INCREMENT PRIMARY KEY,
    post_id      INT,
    user_id      INT,
    comment_text TEXT NOT NULL,
    comment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts (post_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
