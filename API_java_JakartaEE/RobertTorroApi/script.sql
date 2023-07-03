DROP DATABASE m09uf3ac1;
CREATE DATABASE m09uf3ac1;
USE m09uf3ac1;

CREATE TABLE barrio (
     name VARCHAR (255) PRIMARY KEY NOT NULL
);

insert into daw2tp2.barrio (name) values ("Alameda");
insert into daw2tp2.barrio (name) values ("Barriobajo");
insert into daw2tp2.barrio (name) values ("Vistaalegre");
insert into daw2tp2.barrio (name) values ("Villaconcha");
insert into daw2tp2.barrio (name) values ("Villaconchaalta");
insert into daw2tp2.barrio (name) values ("Nolomires");
insert into daw2tp2.barrio (name) values ("Ofidelia");

CREATE TABLE casa (
     id INT PRIMARY KEY,
     barrio VARCHAR (255) NOT NULL,
     FOREIGN KEY (barrio) 
            REFERENCES daw2tp2.barrio (name) 
            ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE inquilino (
     id INT PRIMARY KEY NOT NULL,
     casa INT NOT NULL,
     FOREIGN KEY (casa) 
            REFERENCES daw2tp2.casa (id) 
            ON DELETE CASCADE ON UPDATE CASCADE
);