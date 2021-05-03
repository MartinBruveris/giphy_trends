USE giphy_db;\g
CREATE TABLE IF NOT EXISTS giphy_main_table (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    giphy_id VARCHAR(255) NOT NULL,
    giphy_type VARCHAR(255) NOT NULL,
    embed_url VARCHAR(255) NOT NULL,
    trending_datetime DATETIME NOT NULL,
    title VARCHAR(255) NOT NULL
);\g

CREATE TABLE IF NOT EXISTS date_tracker (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    latest_date DATETIME NOT NULL,
    last_updated_on DATETIME NOT NULL
);\g

INSERT into giphy_db.date_tracker(latest_date, last_updated_on) VALUES(NOW(), NOW());\g

DELIMITER $$

CREATE TRIGGER update_max_datetime
    AFTER INSERT
    ON giphy_db.giphy_main_table FOR EACH ROW
BEGIN
    DECLARE datemax DATETIME;
    SELECT max(trending_datetime) INTO datemax from giphy_db.giphy_main_table;
    UPDATE giphy_db.date_tracker SET latest_date = datemax, last_updated_on = NOW() WHERE id = 1;
END$$    

DELIMITER ;

CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';\g
GRANT ALL PRIVILEGES  ON giphy_db.* TO 'user'@'localhost';\g

COMMIT;\g