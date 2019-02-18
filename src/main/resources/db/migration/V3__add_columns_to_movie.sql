DROP TABLE MOVIE;

CREATE TABLE MOVIE (
	id    SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL,
	experiences varchar(255) NOT NULL,
	listing_type varchar(20) NULL,
	lang int NULL,
	image_name VARCHAR(255) NULL,
	stills VARCHAR(255) NULL,
	synopsis TEXT NULL
);

CREATE TABLE LANGUAGE (
    id int NOT NULL,
    name varchar(255) NOT NULL
);