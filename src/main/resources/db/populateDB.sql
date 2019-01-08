DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM MENU_DISHES;
DELETE FROM USERS;
DELETE FROM RESTAURANTS;
ALTER SEQUENCE USERS_SEQ RESTART WITH 100000;
ALTER SEQUENCE RESTAURANTS_SEQ RESTART WITH 100000;
ALTER SEQUENCE DISHES_SEQ RESTART WITH 100000;
ALTER SEQUENCE MENU_DISHES_SEQ RESTART WITH 100000;
ALTER SEQUENCE VOTES_SEQ RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES
  ('User1', 'user1@yandex.ru', '{noop}password1'),
  ('User2', 'user2@yandex.ru', '{noop}password2'),
  ('User3', 'user3@yandex.ru', '{noop}password3'),

  ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLES (ROLE, USER_ID) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),

  ('ROLE_ADMIN', 100003);

INSERT INTO RESTAURANTS (NAME, ADDRESS) VALUES
  ('Европейский ресторан', 'Ленинский пр-т, д. 1'),
  ('Тайский ресторан', 'Тверская ул., д. 10'),
  ('Русский ресторан', 'Площадь им. Ленина, д. 18');

INSERT INTO DISHES (NAME, PRICE) VALUES
  ('Салат Цезарь', 500),
  ('Грибной суп', 1200),
  ('Стейк', 2100),

  ('Салат из морепродуктов', 1100),
  ('Суп том-ям', 530),
  ('Лапша с курицей', 2100),

  ('Салат оливье', 320),
  ('Борщ', 550),
  ('Котлеты с картофелем', 700),

  ('Пицца', 300),
  ('Томатный суп', 270),
  ('Паста', 290);

INSERT INTO MENU_DISHES (DATE, DISH_ID, RESTAURANT_ID) VALUES
  ('2018-10-29', 100000, 100000),
  ('2018-10-29', 100001, 100000),
  ('2018-10-29', 100002, 100000),

  ('2018-10-30', 100003, 100001),
  ('2018-10-30', 100004, 100001),
  ('2018-10-30', 100005, 100001),

  ('2018-10-31', 100006, 100002),
  ('2018-10-31', 100007, 100002),
  ('2018-10-31', 100008, 100002),

  ('2018-10-31', 100009, 100000),
  ('2018-10-31', 100010, 100000),
  ('2018-10-31', 100011, 100000);

INSERT INTO VOTES (DATE, USER_ID, RESTAURANT_ID) VALUES
  ('2018-10-29', 100000, 100000),
  ('2018-10-29', 100001, 100000),
  ('2018-10-29', 100002, 100000),
  ('2018-10-31', 100000, 100000),
  ('2018-10-31', 100001, 100000),
  ('2018-10-31', 100003, 100002)
