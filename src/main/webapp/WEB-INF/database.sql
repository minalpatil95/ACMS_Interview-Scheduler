-- auto-generated definition
CREATE TABLE candidate
(
  cand_id   INT AUTO_INCREMENT
    PRIMARY KEY,
  cand_name VARCHAR(255) NOT NULL,
  email     VARCHAR(255) NOT NULL,
  phone_num INT(10)      NOT NULL,
  CONSTRAINT candidate_cand_id_uindex
  UNIQUE (cand_id),
  CONSTRAINT candidate_email_uindex
  UNIQUE (email)
)
  ENGINE = InnoDB;

-- auto-generated definition
CREATE TABLE employee
(
  emp_id    INT AUTO_INCREMENT
    PRIMARY KEY,
  emp_name  VARCHAR(255) NULL,
  email     VARCHAR(255) NULL,
  password  VARCHAR(255) NULL,
  emp_type  VARCHAR(10)  NOT NULL,
  phone_num VARCHAR(10)  NULL,
  job_level VARCHAR(20)  NULL
)
  ENGINE = InnoDB;

-- auto-generated definition
CREATE TABLE event
(
  event_id         INT AUTO_INCREMENT
    PRIMARY KEY,
  event_name       VARCHAR(255)           NOT NULL,
  event_type       VARCHAR(255)           NOT NULL,
  num_of_rounds    INT DEFAULT '1'        NOT NULL,
  date             DATE                   NOT NULL,
  event_status     TINYINT(1) DEFAULT '1' NOT NULL,
  job_level        VARCHAR(255)           NULL,
  num_of_candidate INT DEFAULT '0'        NULL,
  CONSTRAINT event_event_name_uindex
  UNIQUE (event_name)
)
  ENGINE = InnoDB;

-- auto-generated definition
CREATE TABLE marks
(
  event_id INT             NOT NULL,
  round_id INT             NOT NULL,
  cand_id  INT             NOT NULL,
  marks    INT DEFAULT '0' NULL,
  emp_id   INT             NOT NULL,
  PRIMARY KEY (round_id, event_id, cand_id),
  CONSTRAINT marks_event_event_id_fk
  FOREIGN KEY (event_id) REFERENCES event (event_id),
  CONSTRAINT marks_round_round_id_fk
  FOREIGN KEY (round_id) REFERENCES round (round_id),
  CONSTRAINT marks_candidate_cand_id_fk
  FOREIGN KEY (cand_id) REFERENCES candidate (cand_id)
)
  ENGINE = InnoDB;

CREATE INDEX marks_event_event_id_fk
  ON marks (event_id);

CREATE INDEX marks_candidate_cand_id_fk
  ON marks (cand_id);

-- auto-generated definition
CREATE TABLE round
(
  round_id   INT          NOT NULL,
  event_id   INT          NOT NULL,
  competency VARCHAR(255) NULL,
  PRIMARY KEY (round_id, event_id),
  CONSTRAINT event_id
  FOREIGN KEY (event_id) REFERENCES event (event_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX event_id
  ON round (event_id);

-- auto-generated definition
CREATE TABLE round_inter_rel
(
  round_id INT NOT NULL,
  event_id INT NOT NULL,
  emp_id   INT NOT NULL,
  PRIMARY KEY (round_id, event_id, emp_id),
  CONSTRAINT round_id_fk
  FOREIGN KEY (round_id) REFERENCES round (round_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT event_id_fk
  FOREIGN KEY (event_id) REFERENCES event (event_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT emp_id_fk
  FOREIGN KEY (emp_id) REFERENCES employee (emp_id)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE INDEX event_id_fk
  ON round_inter_rel (event_id);

CREATE INDEX emp_id_fk
  ON round_inter_rel (emp_id);

