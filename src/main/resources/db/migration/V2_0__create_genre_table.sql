-- Table genre
CREATE TABLE IF NOT EXISTS movieland.genre (
  id SERIAL,
  name VARCHAR(50) NOT NULL,
PRIMARY KEY (id));