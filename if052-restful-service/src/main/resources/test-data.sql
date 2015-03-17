INSERT INTO `consumer`
    VALUES (1,'USER_Name1','SURNAME1','MIDDLENAME1','LOGIN111','PASS1111','qwerty@gmail.com'),
           (2,'USER_Name2','SURNAME2','MIDDLENAME2','LOGIN222','PASS2222','asdf@mail.ru'),
           (3,'USER_Name3','SURNAME3','MIDDLENAME3','LOGIN333','PASS3333','zixel@i.ua');


INSERT INTO `address`
    VALUES (1, 'CITY1', 'STREET1', 'BUILDING1', 'APARTMENT1', 0.1, 1),
           (2, 'CITY2', 'STREET2', 'BUILDING2', 'APARTMENT2', 0.2, 1),
           (3, 'CITY3', 'STREET3', 'BUILDING3', 'APARTMENT3', 0.3, 1),
           (4, 'CITY4', 'STREET4', 'BUILDING4', 'APARTMENT4', 0.15, 2),
           (5, 'CITY5', 'STREET5', 'BUILDING5', 'APARTMENT5', 0.16, 2),
           (6, 'CITY6', 'STREET6', 'BUILDING6', 'APARTMENT6', 0.17, 2),
           (7, 'CITY7', 'STREET7', 'BUILDING7', 'APARTMENT7', 0.19, 3),
           (8, 'CITY8', 'STREET8', 'BUILDING8', 'APARTMENT8', 0.1, 3);


INSERT INTO `meter_type`
    VALUES (1, 'cold water'),
           (2, 'hot water'),
           (3, 'natural gas'),
           (4, 'electroenergy');


INSERT INTO `watermeter`
    VALUES (1, 'WM_NAME1', 'DESCRIPTION1', 1),
           (2, 'WM_NAME2', 'DESCRIPTION2', 1),
           (3, 'WM_NAME3', 'DESCRIPTION3', 1),
           (4, 'WM_NAME4', 'DESCRIPTION4', 2),
           (5, 'WM_NAME5', 'DESCRIPTION5', 2),
           (6, 'WM_NAME6', 'DESCRIPTION6', 3),
           (7, 'WM_NAME7', 'DESCRIPTION7', 3),
           (8, 'WM_NAME8', 'DESCRIPTION8', 4),
           (9, 'WM_NAME9', 'DESCRIPTION9', 4),
           (10, 'WM_NAME10', 'DESCRIPTION10', 5),
           (11, 'WM_NAME11', 'DESCRIPTION11', 5),
           (12, 'WM_NAME12', 'DESCRIPTION12', 6),
           (13, 'WM_NAME13', 'DESCRIPTION13', 6),
           (14, 'WM_NAME14', 'DESCRIPTION14', 7),
           (15, 'WM_NAME15', 'DESCRIPTION15', 7),
           (16, 'WM_NAME16', 'DESCRIPTION16', 8),
           (17, 'WM_NAME17', 'DESCRIPTION17', 8),
           (18, 'WM_NAME18', 'DESCRIPTION18', 8),
           (19, 'WM_NAME19', 'DESCRIPTION19', 7),
           (20, 'WM_NAME20', 'DESCRIPTION20', 6),
           (21, 'WM_NAME21', 'DESCRIPTION21', 5),
           (22, 'WM_NAME22', 'DESCRIPTION22', 4),
           (23, 'WM_NAME23', 'DESCRIPTION23', 3),
           (24, 'WM_NAME24', 'DESCRIPTION24', 2),
           (25, 'WM_NAME25', 'DESCRIPTION25', 1);

INSERT INTO `indicator`
    VALUES (1, '2015-01-01 00:00:00', 51, 0, 0, 1),
           (2, '2015-01-05 01:01:01', 71, 1, 0, 1),
           (3, '2015-01-10 00:00:00', 101, 0, 1, 2),
           (4, '2015-01-03 00:00:00', 101, 1, 1, 3),
           (5, '2015-01-04 00:00:00', 101, 0, 1, 4),
           (6, '2015-01-05 00:00:00', 101, 1, 0, 5),
           (7, '2015-01-10 02:02:02', 101, 0, 0, 1),
           (8, '2015-01-15 03:03:03', 131, 0, 0, 1),
           (9, '2015-01-20 05:05:05', 151, 0, 0, 1),
           (10,'2015-01-25 23:00:00', 181, 0, 0, 1),
           (11,'2015-01-30 23:01:00', 201, 0, 0, 1),
           (12,'2015-02-05 23:02:00', 226, 0, 0, 1),
           (13,'2015-02-10 23:03:00', 251, 0, 0, 1),
           (14,'2015-02-15 23:04:00', 281, 0, 0, 1),
           (15,'2015-02-20 23:05:00', 301, 0, 0, 1);