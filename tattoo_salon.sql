CREATE SCHEMA IF NOT EXISTS Tattoo_salon;
USE Tattoo_salon;

CREATE TABLE IF NOT EXISTS Tattoo_salon.user(
 id INT UNSIGNED NOT NULL AUTO_INCREMENT,
 login VARCHAR(45) NOT NULL,
 first_name VARCHAR(45) NOT NULL,
 last_name VARCHAR(45) NOT NULL,
 password CHAR(64) NOT NULL,
 email VARCHAR(45) NOT NULL,
 PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS Tattoo_salon.user_feedback (
  id INT NOT NULL AUTO_INCREMENT,
  feedback LONGTEXT NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_user_feedback_user1
    FOREIGN KEY (user_id)
      REFERENCES Tattoo_salon.user (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Tattoo_salon.tattoo(
  id INT NOT NULL AUTO_INCREMENT,
  photo LONGBLOB NOT NULL,
  description MEDIUMTEXT NOT NULL,
  price FLOAT NOT NULL,
  creation_date DATETIME NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  user_feedback_id INT NOT NULL,
  PRIMARY KEY (id, user_id),
  CONSTRAINT fk_tattoo_user1
    FOREIGN KEY (user_id)
      REFERENCES Tattoo_salon.user(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE ,
  CONSTRAINT fk_tattoo_user_feedback1
    FOREIGN KEY (user_feedback_id)
      REFERENCES Tattoo_salon.user_feedback (id)
      ON DELETE CASCADE
    ON UPDATE CASCADE );

CREATE TABLE IF NOT EXISTS Tattoo_salon.tattoo_order(
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  tattoo_id INT NOT NULL,
  price FLOAT NOT NULL,
  date DATETIME NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_order_user1
    FOREIGN KEY (user_id)
    REFERENCES Tattoo_salon.user (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT fk_order_tattoo1
    FOREIGN KEY (tattoo_id)
      REFERENCES Tattoo_salon.tattoo (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Tattoo_salon.discount (
  id INT NOT NULL AUTO_INCREMENT,
  description MEDIUMTEXT NOT NULL,
  percents TINYINT(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS Tattoo_salon.user_discount (
  id INT NOT NULL AUTO_INCREMENT,
  discount_id INT NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  tattoo_order_id INT NOT NULL,
  PRIMARY KEY (id, discount_id),
  CONSTRAINT fk_user_discount_discount1
  FOREIGN KEY (discount_id)
    REFERENCES Tattoo_salon.discount (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
  CONSTRAINT fk_user_discount_user1
  FOREIGN KEY (user_id)
    REFERENCES Tattoo_salon.user (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE ,
  CONSTRAINT fk_user_discount_tattoo_order1
    FOREIGN KEY (tattoo_order_id)
      REFERENCES Tattoo_salon.tattoo_order (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Tattoo_salon.role(
  id INT NOT NULL AUTO_INCREMENT,
  role ENUM('client', 'master', 'admin') NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (id, user_id),
  CONSTRAINT fk_status_user1
    FOREIGN KEY (user_id)
      REFERENCES Tattoo_salon.user (id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);