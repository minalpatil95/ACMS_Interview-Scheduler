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
  round_id       INT                            NOT NULL,
  event_id       INT                            NOT NULL,
  emp_id         INT                            NOT NULL,
  request_status VARCHAR(255) DEFAULT 'pending' NULL,
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

  -- auto-generated definition
CREATE TABLE employee
(
  emp_id     INT AUTO_INCREMENT
    PRIMARY KEY,
  emp_name   VARCHAR(255)           NULL,
  email      VARCHAR(255)           NULL,
  password   VARCHAR(255)           NULL,
  emp_type   VARCHAR(255)           NOT NULL,
  phone_num  VARCHAR(10)            NULL,
  job_level  VARCHAR(20)            NULL,
  isEnabled  TINYINT(1) DEFAULT '1' NULL,
  competency VARCHAR(255)           NOT NULL
)
  ENGINE = InnoDB;

-- auto-generated definition
CREATE TABLE event
(
  event_id         INT AUTO_INCREMENT
    PRIMARY KEY,
  event_name       VARCHAR(255)              NOT NULL,
  event_type       VARCHAR(255)              NOT NULL,
  num_of_rounds    INT DEFAULT '1'           NOT NULL,
  date             DATE                      NOT NULL,
  event_status     TINYINT(1) DEFAULT '1'    NOT NULL,
  job_level        VARCHAR(255) DEFAULT '-1' NULL,
  num_of_candidate INT DEFAULT '1'           NULL,
  recruiter_id     INT                       NOT NULL,
  CONSTRAINT event_event_id_uindex
  UNIQUE (event_id),
  CONSTRAINT event_event_name_uindex
  UNIQUE (event_name)
)
  ENGINE = InnoDB;





