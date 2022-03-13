# Wildlife-Tracker

#### By **Priscah Limo**
## Description
An application that allows Rangers to track wildlife sightings in the area.
## Setup/Installation Requirements
* Ensure to have JUnit, intellij, SDK,JDK
* Clone the repository
* Run the project on your machine

## postgres setup

CREATE DATABASE wildlife_tracker;

CREATE TABLE sightings (id serial PRIMARY KEY, animalid integer,location varchar,rangername,lastseen timestamp);

CREATE TABLE animals (id serial PRIMARY KEY, name varchar,health varchar,age varchar, type varchar);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;


## Known Bugs
There are no known bugs.
## Technologies Used
Java

Postgres

Spark

Handlebars

CSS

Bootstrap
## Support and contact details
Email : priscah.limo@student.moringaschool.com
### License
*[MIT License]("./LICENSE")

Copyright (c) [2022] [Priscah Limo]