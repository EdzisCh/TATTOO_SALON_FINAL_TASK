CREATE DATABASE Tattoo_salon;
USE Tattoo_salon;

CREATE TABLE Tattoo_salon.user (
      id INT UNSIGNED NOT NULL,
      login VARCHAR(45) NOT NULL,
      first_name VARCHAR(45) NOT NULL,
      last_name VARCHAR(45) NOT NULL,
      password CHAR(64) NOT NULL,
      email VARCHAR(45) NOT NULL,
      PRIMARY KEY (id));

CREATE TABLE Tattoo_salon.user_feedback (
      id INT NOT NULL,
      feedback LONGTEXT NOT NULL,
      user_id INT UNSIGNED NOT NULL,
      PRIMARY KEY (id),
      UNIQUE INDEX iduser_feedback_UNIQUE (id),
      INDEX fk_user_feedback_user1_idx (user_id),
      CONSTRAINT fk_user_feedback_user1
        FOREIGN KEY (user_id)
          REFERENCES Tattoo_salon.user (id)
          ON DELETE CASCADE
          ON UPDATE CASCADE);

CREATE TABLE Tattoo_salon.tattoo (
     id INT NOT NULL,
     photo BIT(200000000) NOT NULL,
     description MEDIUMTEXT NOT NULL,
     price FLOAT NOT NULL,
     creation_date DATETIME NOT NULL,
     user_id INT UNSIGNED NOT NULL,
     user_feedback_id INT NOT NULL,
     PRIMARY KEY (id, user_id),
     UNIQUE INDEX id_UNIQUE (id),
     INDEX fk_tattoo_user1_idx (user_id),
     INDEX fk_tattoo_user_feedback1_idx (user_feedback_id),
     CONSTRAINT fk_tattoo_user1
       FOREIGN KEY (`user_id`)
         REFERENCES Tattoo_salon.user (id)
         ON DELETE CASCADE
         ON UPDATE CASCADE ,
     CONSTRAINT fk_tattoo_user_feedback1
       FOREIGN KEY (user_feedback_id)
         REFERENCES Tattoo_salon.user_feedback (id)
         ON DELETE CASCADE
         ON UPDATE CASCADE );

CREATE TABLE Tattoo_salon.tattoo_order (
     id INT NOT NULL,
     user_id INT UNSIGNED NOT NULL,
     tattoo_id INT NOT NULL,
     price FLOAT NOT NULL,
     date DATETIME NOT NULL,
     PRIMARY KEY (id),
     UNIQUE INDEX id_UNIQUE (id),
     INDEX fk_order_user1_idx (user_id ASC),
     INDEX fk_order_tattoo1_idx (tattoo_id),
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

CREATE TABLE Tattoo-salon.discount (
     id INT NOT NULL,
     description MEDIUMTEXT NOT NULL,
     percents TINYINT(100) NOT NULL,
     PRIMARY KEY (id),
     UNIQUE INDEX iddiscount_UNIQUE (id));

CREATE TABLE Tattoo_salon.user_discount (
      id INT NOT NULL,
      discount_id INT NOT NULL,
      user_id INT UNSIGNED NOT NULL,
      tattoo_order_id INT NOT NULL,
      PRIMARY KEY (id, discount_id),
      UNIQUE INDEX id_UNIQUE (id),
      INDEX fk_user_discount_discount1_idx (discount_id),
      INDEX fk_user_discount_user1_idx (user_id),
      INDEX fk_user_discount_tattoo_order1_idx (tattoo_order_id),
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

CREATE TABLE Tattoo_salon.role (
     id INT NOT NULL,
     role ENUM('client', 'master', 'admin') NOT NULL,
     user_id INT UNSIGNED NOT NULL,
     PRIMARY KEY (id, user_id),
     INDEX fk_status_user1_idx (user_id),
     CONSTRAINT fk_status_user1
       FOREIGN KEY (user_id)
         REFERENCES Tattoo_salon.user (id)
         ON DELETE CASCADE
         ON UPDATE CASCADE);