DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (id, user_id, date_time, description, calories) VALUES
(1, 100001, '2019-10-23 12:33','testPop', '10' ),
(2, 100001, '2019-10-23 12:34','testPop2', '20' ),
(3, 100001, '2019-10-23 12:35','testPop3', '20' ),
(4, 100000, '2019-10-23 12:36','testPop4', '30' ),
(5, 100000, '2019-10-23 12:37','testPop5', '40' ),
(6, 100000, '2019-10-23 12:38','testPop6', '40' );