DROP TABLE USER_ROLES IF EXISTS;
DROP TABLE VOTES IF EXISTS;
DROP TABLE MENU_DISHES IF EXISTS;
DROP TABLE USERS IF EXISTS;
DROP TABLE RESTAURANTS IF EXISTS;
DROP TABLE DISHES IF EXISTS;
DROP SEQUENCE USERS_SEQ IF EXISTS;
DROP SEQUENCE RESTAURANTS_SEQ IF EXISTS;
DROP SEQUENCE DISHES_SEQ IF EXISTS;
DROP SEQUENCE MENU_DISHES_SEQ IF EXISTS;
DROP SEQUENCE VOTES_SEQ IF EXISTS;

CREATE SEQUENCE USERS_SEQ
  AS INTEGER
  START WITH 100000;

CREATE SEQUENCE RESTAURANTS_SEQ
  AS INTEGER
  START WITH 100000;

CREATE SEQUENCE DISHES_SEQ
  AS INTEGER
  START WITH 100000;

CREATE SEQUENCE MENU_DISHES_SEQ
  AS INTEGER
  START WITH 100000;

CREATE SEQUENCE VOTES_SEQ
  AS INTEGER
  START WITH 100000;

CREATE TABLE USERS
(
  id         INTEGER GENERATED BY DEFAULT AS SEQUENCE USERS_SEQ PRIMARY KEY,
  name       VARCHAR(255)            NOT NULL,
  email      VARCHAR(255)            NOT NULL,
  password   VARCHAR(255)            NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE USER_ROLES
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE RESTAURANTS
(
  id      INTEGER GENERATED BY DEFAULT AS SEQUENCE RESTAURANTS_SEQ PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL
);

CREATE TABLE DISHES
(
  id   INTEGER GENERATED BY DEFAULT AS SEQUENCE DISHES_SEQ PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  cost BIGINT       NOT NULL
);

CREATE TABLE MENU_DISHES
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE MENU_DISHES_SEQ PRIMARY KEY,
  date          DATE    NOT NULL,
  dish_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE,
  FOREIGN KEY (dish_id) REFERENCES DISHES(id) ON DELETE CASCADE
);
CREATE INDEX menu_date_idx ON MENU_DISHES (date);

CREATE TABLE VOTES
(
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE VOTES_SEQ PRIMARY KEY,
  date          DATE    NOT NULL,
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE NO ACTION,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION
);
