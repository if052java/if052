  CREATE TABLE user (user_id int(11) auto_increment primary key, name varchar(45), surname varchar(45),
  middle_name varchar(45), login varchar(45), password varchar(45), tariff float);

  CREATE TABLE address (address_id int(11) auto_increment primary key, city varchar(45), street varchar(45),
  building varchar(45), apartment varchar(45), user_id int(11),
  CONSTRAINT fk_address_user FOREIGN KEY (user_id) REFERENCES user (user_id));

  CREATE TABLE watermeter(water_meter_id int(11) auto_increment primary key, name varchar(45), description varchar(45),
  address_id int(11), CONSTRAINT fk_water_meter_adderess FOREIGN KEY (address_id) REFERENCES address (address_id));

  CREATE TABLE indicator(indicator_id int auto_increment primary key, date datetime, value int, is_paid bit(1),
  is_published bit(1), water_meter_id int(11),
  CONSTRAINT fk_indicator_water_meter FOREIGN KEY (water_meter_id) REFERENCES watermeter (water_meter_id));


