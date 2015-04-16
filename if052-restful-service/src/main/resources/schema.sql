CREATE TABLE consumer
(
  user_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32), surname VARCHAR(32),
  middle_name VARCHAR(32),
  login VARCHAR(32),
  password VARCHAR(32),
  email VARCHAR(32),
  role VARCHAR(45)
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
  user_id INT(11) NOT NULL,
  role_id INT(11) NOT NULL,
  CONSTRAINT fk_role_consumer_role FOREIGN KEY (user_id)
  REFERENCES consumer (user_id)
  ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_role_consumer_user FOREIGN KEY (role_id)
  REFERENCES role (role_id)
  ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE address
(
  address_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  city VARCHAR(32),
  street VARCHAR(32),
  building VARCHAR(10),
  apartment INT(5),
  user_id INT(11),
  CONSTRAINT fk_address_user FOREIGN KEY (user_id)
      REFERENCES consumer (user_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE meter_type
(
  meter_type_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  type VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE watermeter
(
  water_meter_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(64),
  address_id INT(11) NOT NULL,
  meter_type_id INT(11) NOT NULL,
  tariff FLOAT,
  CONSTRAINT fk_water_meter_address FOREIGN KEY (address_id)
      REFERENCES address (address_id)
      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_meter_type FOREIGN KEY (meter_type_id)
      REFERENCES meter_type (meter_type_id)
);

CREATE TABLE indicator
(
  indicator_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  date DATETIME,
  tariff_per_date FLOAT,
  value INT,
  is_paid BIT(1),
  is_published BIT(1),
  water_meter_id INT(11),
  CONSTRAINT fk_indicator_water_meter FOREIGN KEY (water_meter_id)
      REFERENCES watermeter (water_meter_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE report
(
  report_id INT AUTO_INCREMENT PRIMARY KEY,
  request varchar(255),
  xml_report mediumtext
);
