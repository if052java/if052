CREATE TABLE consumer
(
  user_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45), surname VARCHAR(45),
  middle_name VARCHAR(45),
  login VARCHAR(45),
  password VARCHAR(45)
);

CREATE TABLE role
(
  role_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(20) UNIQUE NOT NULL,
  description VARCHAR(256)
);

CREATE TABLE role_consumer
(
  role_consumer_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  role_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  CONSTRAINT fk_role_consumer_user FOREIGN KEY (role_id)
      REFERENCES role (role_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT  fk_role_consumer_role FOREIGN KEY (user_id)
      REFERENCES consumer (user_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE address
(
  address_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  city VARCHAR(45),
  street VARCHAR(45),
  building VARCHAR(45),
  apartment VARCHAR(45),
  tariff FLOAT,
  user_id INT(11),
  CONSTRAINT fk_address_user FOREIGN KEY (user_id)
      REFERENCES consumer (user_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE watermeter
(
  water_meter_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(45),
  address_id INT(11) NOT NULL,
  CONSTRAINT fk_water_meter_address FOREIGN KEY (address_id)
      REFERENCES address (address_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE indicator
(
  indicator_id INT AUTO_INCREMENT PRIMARY KEY,
  date DATETIME,
  value INT,
  is_paid BIT(1),
  is_published BIT(1),
  water_meter_id INT(11),
  CONSTRAINT fk_indicator_water_meter FOREIGN KEY (water_meter_id)
      REFERENCES watermeter (water_meter_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);
