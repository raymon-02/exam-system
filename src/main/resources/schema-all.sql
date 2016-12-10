DROP TABLE IF EXISTS exam;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS custom_user;
DROP TABLE IF EXISTS task;


CREATE TABLE task (
  id     SERIAL PRIMARY KEY,
  number INT UNIQUE  NOT NULL,
  text   TEXT UNIQUE NOT NULL
);

CREATE TABLE custom_user (
  id       SERIAL PRIMARY KEY,
  name     TEXT NOT NULL,
  surname  TEXT NOT NULL,
  username TEXT NOT NULL,
  password TEXT NOT NULL,
  role     TEXT NOT NULL
);

CREATE TABLE student (
  id      SERIAL PRIMARY KEY,
  user_id INT REFERENCES custom_user
);

CREATE TABLE exam (
  id             SERIAL PRIMARY KEY,
  task_first_id  INT REFERENCES task,
  task_second_id INT REFERENCES task,
  student_id     INT REFERENCES student,
  answer         TEXT,
  mark           INT CHECK (mark > 0 AND mark < 6)
);
