CREATE TABLE consumer
(
  user_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45), surname VARCHAR(45),
  middle_name VARCHAR(45),
  login VARCHAR(45),
  password VARCHAR(45),
  email VARCHAR(45)
);

CREATE TABLE address
(
  address_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  city VARCHAR(45),
  street VARCHAR(45),
  building VARCHAR(45),
  apartment VARCHAR(45),
  user_id INT(11),
  CONSTRAINT fk_address_user FOREIGN KEY (user_id)
      REFERENCES consumer (user_id)
      ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE meter_type (
    meter_type_id INT(11) AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(45)
);

CREATE TABLE watermeter
(
  water_meter_id INT(11) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL UNIQUE,
  description VARCHAR(45),
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
