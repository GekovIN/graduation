DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM MEALS;
DELETE FROM USERS;
DELETE FROM RESTAURANTS;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO USERS (NAME, EMAIL, PASSWORD) VALUES
  ('User1', 'user1@yandex.ru', 'password1'),
  ('User2', 'user2@yandex.ru', 'password2'),
  ('User3', 'user3@yandex.ru', 'password3'),

  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO USER_ROLES (ROLE, USER_ID) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO RESTAURANTS (NAME, ADDRESS) VALUES
  ('Европейский ресторан', 'Ленинский пр-т, д. 1'),
  ('Тайский ресторан', 'Тверская ул., д. 10'),
  ('Русский ресторан', 'Площадь им. Ленина, д. 18');

INSERT INTO MEALS (DATE, DESCRIPTION, AMOUNT, RESTAURANT_ID) VALUES
  ('2018-10-29', 'Салат Цезарь', 500, 100004),
  ('2018-10-29', 'Грибной суп', 1200, 100004),
  ('2018-10-29', 'Стейк', 2100, 100004),

  ('2018-10-30', 'Салат из морепродуктов', 1100, 100005),
  ('2018-10-30', 'Суп том-ям', 530, 100005),
  ('2018-10-30', 'Лапша с курицей', 510, 100005),

  ('2018-10-31', 'Салат оливье', 320, 100006),
  ('2018-10-31', 'Борщ', 550, 100006),
  ('2018-10-31', 'Котлеты с картофелем', 700, 100006);
