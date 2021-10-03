
DROP TABLE IF EXISTS form, question;

CREATE TABLE form (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(30) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE question (
  id INT NOT NULL AUTO_INCREMENT,
  form_id INT,
  FOREIGN KEY (form_id)
    REFERENCES form(id)
    ON DELETE CASCADE,
  text varchar (256),
  PRIMARY KEY (id)
);

CREATE TABLE questionSent (
  id INT NOT NULL AUTO_INCREMENT,
  form_id INT,
  question_id INT,
  FOREIGN KEY (form_id)
    REFERENCES form(id)
    ON DELETE CASCADE,
  FOREIGN KEY (question_id)
    REFERENCES question(id)
    ON DELETE CASCADE,
  PRIMARY KEY (id)
);
