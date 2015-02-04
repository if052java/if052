
  CREATE TABLE user (user_id int auto_increment, name varchar(25));

  CREATE TABLE watermeter(water_meter_id int auto_increment, name varchar(45), description varchar(45), address_id int);

  CREATE TABLE indicator(indicator_id int auto_increment, date datetime, value int, is_paid bit(1), is_published bit(1), water_meter_id int,
  CONSTRAINT fk_indicator_water_meter FOREIGN KEY (water_meter_id) REFERENCES watermeter (water_meter_id));