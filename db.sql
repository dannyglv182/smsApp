DROP DATABASE IF EXISTS testdb;

CREATE DATABASE testdb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE message (
  message_id SERIAL PRIMARY KEY,
  body TEXT
);

CREATE TABLE receipt (
    receipt_id SERIAL PRIMARY KEY,
    message_id INTEGER,
    Receiver_Number TEXT,
    CONSTRAINT fk_message
      FOREIGN KEY(message_id)
        REFERENCES message(message_id)
  );
