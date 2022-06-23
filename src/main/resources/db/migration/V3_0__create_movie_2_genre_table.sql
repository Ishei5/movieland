-- Table movie_2_genre
CREATE TABLE IF NOT EXISTS movieland.movie_2_genre (
  movie_id INTEGER NOT NULL,
  genre_id INTEGER NOT NULL,
CONSTRAINT movie_id FOREIGN KEY (movie_id) REFERENCES movieland.movie (id),
CONSTRAINT genre_id FOREIGN KEY (genre_id) REFERENCES movieland.genre (id));