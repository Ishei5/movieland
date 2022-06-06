-- Schema movieland
CREATE SCHEMA IF NOT EXISTS movieland;

-- Table movie
CREATE TABLE IF NOT EXISTS movieland.movie (
  id SERIAL,
  name VARCHAR(100) NOT NULL,
  original_name VARCHAR(100) NOT NULL,
  year_release DATE NOT NULL,
  description VARCHAR(1000) NOT NULL,
  rating REAL NOT NULL,
  price REAL NOT NULL,
  poster_url VARCHAR(250) NOT NULL,
PRIMARY KEY(id));

-- Table user
CREATE TABLE IF NOT EXISTS movieland.user (
  id SERIAL,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
PRIMARY KEY (id));

-- Table genre
CREATE TABLE IF NOT EXISTS movieland.genre (
  id SERIAL,
  name VARCHAR(50) NOT NULL,
PRIMARY KEY (id));

-- Table movie_genre
CREATE TABLE IF NOT EXISTS movieland.movie_genre (
  movie_id INTEGER NOT NULL,
  genre_id INTEGER NOT NULL,
CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movieland.movie (id),
CONSTRAINT genre_id FOREIGN KEY (genre_id) REFERENCES movieland.genre (id));

-- Table country
CREATE TABLE IF NOT EXISTS movieland.country (
  id SERIAL,
  name VARCHAR(45) NOT NULL,
PRIMARY KEY (id));

-- Table movie_country
CREATE TABLE IF NOT EXISTS movieland.movie_country (
  movie_id INTEGER NOT NULL,
  country_id INTEGER NOT NULL,
CONSTRAINT movie_country_country_id FOREIGN KEY (country_id) REFERENCES movieland.country (id),
CONSTRAINT movie_country_movie_id FOREIGN KEY (movie_id) REFERENCES movieland.movie (id));

-- Table review
CREATE TABLE IF NOT EXISTS movieland.review (
  id SERIAL,
  movie_id INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  description VARCHAR(500) NOT NULL,
PRIMARY KEY (id));