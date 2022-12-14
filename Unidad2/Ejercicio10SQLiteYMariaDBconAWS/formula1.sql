CREATE TABLE IF NOT EXISTS Drivers (
    DriverID INTEGER NOT NULL,
	Name TEXT NOT NULL,
	DateOfBirth TEXT NOT NULL, /* SQLite no tiene una tipo de dato DATE */
    Team TEXT NOT NULL,
	PRIMARY KEY ( DriverID )
);

INSERT INTO Drivers VALUES
    (44,'Lewis Hamilton','1985-07-07 00:00:00','Mercedes'),
    (77,'Valtteri Bottas','1989-08-28 00:00:00','Mercedes'),
    (33,'Max Verstappen','1997-09-30 00:00:00','Red Bull Racing'),
    (16,'Charles Leclerc','1997-10-16 00:00:00','Ferrari'),
    (5,'Sebastian Vettel','1987-07-03 00:00:00','Ferrari'),
    (55,'Carlos Sainz','1994-09-01 00:00:00','McLaren'),
    (10,'Pierre Gasly','1996-02-07 00:00:00','Toro Rosso'),
    (23,'Alexander Albon','1996-03-23 00:00:00','Red Bull Racing'),
    (3,'Daniel Ricciardo','1989-07-01 00:00:00','Renault'),
    (11,'Sergio Perez','1990-01-26 00:00:00','Racing Point'),
    (4,'Lando Norris','1999-11-13 00:00:00','McLaren'),
    (7,'Kimi Raikkönen','1979-10-17 00:00:00','Alfa Romeo'),
    (26,'Daniil Kvyat','1994-04-26 00:00:00','Toro Rosso'),
    (27,'Nico Hulkenberg','1987-08-19 00:00:00','Renault'),
    (18,'Lance Stroll','1998-10-29 00:00:00','Racing Point'),
    (20,'Kevin Magnussen','1992-10-05 00:00:00','Haas'),
    (99,'Antonio Giovinazzi','1993-12-14 00:00:00','Alfa Romeo'),
    (8,'Romain Grosjean','1986-04-17 00:00:00','Haas'),
    (88,'Robert Kubica','1984-12-07 00:00:00','Williams'),
    (63,'George Russell','1998-02-15 00:00:00','Williams');

CREATE TABLE IF NOT EXISTS Tracks (
    TrackID INTEGER NOT NULL,
    Country TEXT NOT NULL,
    DateOfRace TEXT NOT NULL,
	PRIMARY KEY ( TrackID )
);

INSERT INTO Tracks VALUES
    (1,'Australia','2019-03-17 00:00:00'),
    (2,'Bahrain','2019-03-31 00:00:00'),
    (3,'China','2019-04-14 00:00:00'),
    (4,'Azerbaijan','2019-04-28 00:00:00'),
    (5,'Spain','2019-05-12 00:00:00'),
    (6,'Monaco','2019-05-26 00:00:00'),
    (7,'Canada','2019-06-09 00:00:00'),
    (8,'France','2019-06-23 00:00:00'),
    (9,'Austria','2019-06-30 00:00:00'),
    (10,'Great Britain','2019-07-14 00:00:00'),
    (11,'Germany','2019-07-28 00:00:00'),
    (12,'Hungary','2019-08-04 00:00:00'),
    (13,'Belgium','2019-09-01 00:00:00'),
    (14,'Italy','2019-09-08 00:00:00'),
    (15,'Singapore','2019-09-22 00:00:00'),
    (16,'Russia','2019-09-29 00:00:00'),
    (17,'Japan','2019-10-13 00:00:00'),
    (18,'Mexico','2019-10-27 00:00:00'),
    (19,'United States','2019-11-03 00:00:00'),
    (20,'Brazil','2019-11-17 00:00:00'),
    (21,'Abu Dhabi','2019-12-01 00:00:00');
	
CREATE TABLE IF NOT EXISTS Results (
    Points INTEGER NOT NULL,
    Position INTEGER NOT NULL,
    DriverID INTEGER NOT NULL,
    TrackID INTEGER NOT NULL,
	FOREIGN KEY ( DriverID ) REFERENCES Drivers( DriverID ),
	FOREIGN KEY ( TrackID ) REFERENCES Tracks( TrackID )
);
INSERT INTO Results VALUES
    (26,1,77,1),
    (18,2,44,1),
    (15,3,33,1),
    (12,4,5,1),
    (10,5,16,1),
    (8,6,20,1),
    (6,7,27,1),
    (4,8,7,1),
    (2,9,18,1),
    (1,10,26,1),
    (0,11,10,1),
    (0,12,4,1),
    (0,13,11,1),
    (0,14,23,1),
    (0,15,99,1),
    (0,16,63,1),
    (0,17,88,1),
    (0,-1,8,1),
    (0,-1,3,1),
    (0,-1,55,1),
    (25,1,44,2),
    (18,2,77,2),
    (16,3,16,2),
    (12,4,33,2),
    (10,5,5,2),
    (8,6,4,2),
    (6,7,7,2),
    (4,8,10,2),
    (2,9,23,2),
    (1,10,11,2),
    (0,11,99,2),
    (0,12,26,2),
    (0,13,20,2),
    (0,14,18,2),
    (0,15,63,2),
    (0,16,88,2),
    (0,17,27,2),
    (0,18,3,2),
    (0,19,55,2),
    (0,-1,8,2),
    (25,1,44,3),
    (18,2,77,3),
    (15,3,5,3),
    (12,4,33,3),
    (10,5,16,3),
    (9,6,10,3),
    (6,7,3,3),
    (4,8,11,3),
    (2,9,7,3),
    (1,10,23,3),
    (0,11,8,3),
    (0,12,18,3),
    (0,13,20,3),
    (0,14,55,3),
    (0,15,99,3),
    (0,16,63,3),
    (0,17,88,3),
    (0,18,4,3),
    (0,-1,26,3),
    (0,-1,27,3),
    (25,1,77,4),
    (18,2,44,4),
    (15,3,5,4),
    (12,4,33,4),
    (11,5,16,4),
    (8,6,11,4),
    (6,7,55,4),
    (4,8,4,4),
    (2,9,18,4),
    (1,10,7,4),
    (0,11,23,4),
    (0,12,99,4),
    (0,13,20,4),
    (0,14,27,4),
    (0,15,63,4),
    (0,16,88,4),
    (0,-1,10,4),
    (0,-1,8,4),
    (0,-1,26,4),
    (0,-1,3,4),
    (26,1,44,5),
    (18,2,77,5),
    (15,3,33,5),
    (12,4,5,5),
    (10,5,16,5),
    (8,6,10,5),
    (6,7,20,5),
    (4,8,55,5),
    (2,9,26,5),
    (1,10,8,5),
    (0,11,23,5),
    (0,12,3,5),
    (0,13,27,5),
    (0,14,7,5),
    (0,15,11,5),
    (0,16,99,5),
    (0,17,63,5),
    (0,18,88,5),
    (0,-1,18,5),
    (0,-1,4,5),
    (25,1,44,6),
    (18,2,5,6),
    (15,3,77,6),
    (12,4,33,6),
    (11,5,10,6),
    (8,6,55,6),
    (6,7,26,6),
    (4,8,23,6),
    (2,9,3,6),
    (1,10,8,6),
    (0,11,4,6),
    (0,12,11,6),
    (0,13,27,6),
    (0,14,20,6),
    (0,15,63,6),
    (0,16,18,6),
    (0,17,7,6),
    (0,18,88,6),
    (0,19,99,6),
    (0,-1,16,6),
    (25,1,44,7),
    (18,2,5,7),
    (15,3,16,7),
    (13,4,77,7),
    (10,5,33,7),
    (8,6,3,7),
    (6,7,27,7),
    (4,8,10,7),
    (2,9,18,7),
    (1,10,26,7),
    (0,11,55,7),
    (0,12,11,7),
    (0,13,99,7),
    (0,14,8,7),
    (0,15,7,7),
    (0,16,63,7),
    (0,17,20,7),
    (0,18,88,7),
    (0,-1,23,7),
    (0,-1,4,7),
    (25,1,44,8),
    (18,2,77,8),
    (15,3,16,8),
    (12,4,33,8),
    (11,5,5,8),
    (8,6,55,8),
    (6,7,7,8),
    (4,8,27,8),
    (2,9,4,8),
    (1,10,10,8),
    (0,11,3,8),
    (0,12,11,8),
    (0,13,18,8),
    (0,14,26,8),
    (0,15,23,8),
    (0,16,99,8),
    (0,17,20,8),
    (0,18,88,8),
    (0,19,63,8),
    (0,-1,8,8),
    (26,1,33,9),
    (18,2,16,9),
    (15,3,77,9),
    (12,4,5,9),
    (10,5,44,9),
    (8,6,4,9),
    (6,7,10,9),
    (4,8,55,9),
    (2,9,7,9),
    (1,10,99,9),
    (0,11,11,9),
    (0,12,3,9),
    (0,13,27,9),
    (0,14,18,9),
    (0,15,23,9),
    (0,16,8,9),
    (0,17,26,9),
    (0,18,63,9),
    (0,19,20,9),
    (0,20,88,9),
    (26,1,44,10),
    (18,2,77,10),
    (15,3,16,10),
    (12,4,10,10),
    (10,5,33,10),
    (8,6,55,10),
    (6,7,3,10),
    (4,8,7,10),
    (2,9,26,10),
    (1,10,27,10),
    (0,11,4,10),
    (0,12,23,10),
    (0,13,18,10),
    (0,14,63,10),
    (0,15,88,10),
    (0,16,5,10),
    (0,17,11,10),
    (0,-1,99,10),
    (0,-1,8,10),
    (0,-1,20,10),
    (26,1,33,11),
    (18,2,5,11),
    (15,3,26,11),
    (12,4,18,11),
    (10,5,55,11),
    (8,6,23,11),
    (6,7,8,11),
    (4,8,20,11),
    (2,9,44,11),
    (1,10,88,11),
    (0,11,63,11),
    (0,12,7,11),
    (0,13,99,11),
    (0,14,10,11),
    (0,-1,77,11),
    (0,-1,27,11),
    (0,-1,16,11),
    (0,-1,4,11),
    (0,-1,3,11),
    (0,-1,11,11),
    (25,1,44,12),
    (19,2,33,12),
    (15,3,5,12),
    (12,4,16,12),
    (10,5,55,12),
    (8,6,10,12),
    (6,7,7,12),
    (4,8,77,12),
    (2,9,4,12),
    (1,10,23,12),
    (0,11,11,12),
    (0,12,27,12),
    (0,13,20,12),
    (0,14,3,12),
    (0,15,26,12),
    (0,16,63,12),
    (0,17,18,12),
    (0,18,99,12),
    (0,19,88,12),
    (0,-1,8,12),
    (25,1,16,13),
    (18,2,44,13),
    (15,3,77,13),
    (13,4,5,13),
    (10,5,23,13),
    (8,6,11,13),
    (6,7,26,13),
    (4,8,27,13),
    (2,9,10,13),
    (1,10,18,13);
INSERT INTO Results VALUES
    (0,11,4,13),
    (0,12,20,13),
    (0,13,8,13),
    (0,14,3,13),
    (0,15,63,13),
    (0,16,7,13),
    (0,17,88,13),
    (0,18,99,13),
    (0,-1,55,13),
    (0,-1,33,13),
    (25,1,16,14),
    (18,2,77,14),
    (16,3,44,14),
    (12,4,3,14),
    (10,5,27,14),
    (8,6,23,14),
    (6,7,11,14),
    (4,8,33,14),
    (2,9,99,14),
    (1,10,4,14),
    (0,11,10,14),
    (0,12,18,14),
    (0,13,5,14),
    (0,14,63,14),
    (0,15,7,14),
    (0,16,8,14),
    (0,17,88,14),
    (0,-1,20,14),
    (0,-1,26,14),
    (0,-1,55,14),
    (25,1,5,15),
    (18,2,16,15),
    (15,3,33,15),
    (12,4,44,15),
    (10,5,77,15),
    (8,6,23,15),
    (6,7,4,15),
    (4,8,10,15),
    (2,9,27,15),
    (1,10,99,15),
    (0,11,8,15),
    (0,12,55,15),
    (0,13,18,15),
    (0,14,3,15),
    (0,15,26,15),
    (0,16,88,15),
    (0,17,20,15),
    (0,-1,7,15),
    (0,-1,11,15),
    (0,-1,63,15),
    (26,1,44,16),
    (18,2,77,16),
    (15,3,16,16),
    (12,4,33,16),
    (10,5,23,16),
    (8,6,55,16),
    (6,7,11,16),
    (4,8,4,16),
    (2,9,20,16),
    (1,10,27,16),
    (0,11,18,16),
    (0,12,26,16),
    (0,13,7,16),
    (0,14,10,16),
    (0,15,99,16),
    (0,-1,88,16),
    (0,-1,63,16),
    (0,-1,5,16),
    (0,-1,3,16),
    (0,-1,8,16),
    (25,1,77,17),
    (18,2,5,17),
    (16,3,44,17),
    (12,4,23,17),
    (10,5,55,17),
    (0,-1,3,17),
    (8,6,16,17),
    (6,7,10,17),
    (4,8,11,17),
    (0,-1,27,17),
    (2,9,18,17),
    (1,10,26,17),
    (0,11,4,17),
    (0,12,7,17),
    (0,13,8,17),
    (0,14,99,17),
    (0,15,20,17),
    (0,16,63,17),
    (0,17,88,17),
    (0,-1,33,17),
    (25,1,44,18),
    (18,2,5,18),
    (15,3,77,18),
    (13,4,16,18),
    (10,5,23,18),
    (8,6,33,18),
    (6,7,11,18),
    (4,8,3,18),
    (2,9,10,18),
    (1,10,27,18),
    (0,11,26,18),
    (0,12,18,18),
    (0,13,55,18),
    (0,14,99,18),
    (0,15,20,18),
    (0,16,63,18),
    (0,17,8,18),
    (0,18,88,18),
    (0,-1,7,18),
    (0,-1,4,18),
    (25,1,77,19),
    (18,2,44,19),
    (15,3,33,19),
    (13,4,16,19),
    (10,5,23,19),
    (8,6,3,19),
    (6,7,4,19),
    (4,8,55,19),
    (2,9,27,19),
    (1,10,11,19),
    (0,11,7,19),
    (0,12,26,19),
    (0,13,18,19),
    (0,14,99,19),
    (0,15,8,19),
    (0,16,10,19),
    (0,17,63,19),
    (0,18,20,19),
    (0,-1,88,19),
    (0,-1,5,19),
    (25,1,33,20),
    (18,2,10,20),
    (15,3,55,20),
    (12,4,7,20),
    (10,5,99,20),
    (8,6,3,20),
    (6,7,44,20),
    (4,8,4,20),
    (2,9,11,20),
    (1,10,26,20),
    (0,11,20,20),
    (0,12,63,20),
    (0,13,8,20),
    (0,14,23,20),
    (0,15,27,20),
    (0,16,88,20),
    (0,17,5,20),
    (0,18,16,20),
    (0,19,18,20),
    (0,-1,77,20),
    (26,1,44,21),
    (18,2,33,21),
    (15,3,16,21),
    (12,4,77,21),
    (10,5,5,21),
    (8,6,23,21),
    (6,7,11,21),
    (4,8,4,21),
    (2,9,26,21),
    (1,10,55,21),
    (0,11,3,21),
    (0,12,27,21),
    (0,13,7,21),
    (0,14,20,21),
    (0,15,8,21),
    (0,16,99,21),
    (0,17,63,21),
    (0,18,10,21),
    (0,19,88,21),
    (0,-1,18,21);

