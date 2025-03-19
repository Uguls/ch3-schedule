CREATE TABLE schedule (
      id          INT AUTO_INCREMENT PRIMARY KEY,
      todo        VARCHAR(255) NOT NULL,
      author      VARCHAR(255) NOT NULL,
      password    VARCHAR(255) NOT NULL,
      create_time DATETIME NOT NULL,
      update_time DATETIME NULL
);
