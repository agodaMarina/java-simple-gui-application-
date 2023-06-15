CREATE DATABASE ProjetCsc301;
use projetCsc301;


CREATE table Utilisateur(
id int primary key auto_increment,
nom varchar(25),
prenom varchar(50),
username varchar(30),
password varchar (25)
);

CREATE TABLE Enseignant(
id int primary key auto_increment,
nom varchar(30), 
prenom varchar(30), 
tel varchar(30),  
matricule varchar(30),
email varchar(30)
) engine=InnoDB; 

CREATE TABLE Matiere(
id int primary key auto_increment,
code varchar(10),
intitule varchar(30)
);

CREATE TABLE Cours(
id int primary key auto_increment,
Matiere varchar(30),
Enseignant varchar(80),
Classe varchar(30),
 Annee_scolaire varchar(20)
);

CREATE TABLE EmploiDuTemps(
id int primary key auto_increment,
enseignant varchar(30),
cours varchar(30),
 heureDebut varchar(25),
 heureFin varchar(25)
);

CREATE TABLE Classe(
id int primary key auto_increment,
code varchar(10),
intitule varchar(30));


CREATE TABLE Annee(
id int primary key auto_increment,
Code varchar(20), 
date_de_debut year,
date_de_fin year
);

DELETE FROM matiere;


insert into Utilisateur (nom, prenom, username, password)values("AGODA", "marina", "agdMari", "1234");
select * from EmploiDuTemps;
insert into matiere(code, intitule)value("cc1", "python");
