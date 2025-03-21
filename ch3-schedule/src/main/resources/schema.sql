CREATE TABLE scheduleResponseDto (
      id          INT AUTO_INCREMENT PRIMARY KEY,
      scheduleResponseDto        VARCHAR(255) NOT NULL,
      author      VARCHAR(255) NOT NULL,
      password    VARCHAR(255) NOT NULL,
      create_time DATETIME NOT NULL,
      update_time DATETIME NULL
);
