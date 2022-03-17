select nextval('roles_seq');
select nextval('roles_seq');
INSERT INTO comic.roles (id,description,"name","system") VALUES
(1,'ADMIN','ADMIN',true),
(2,'USER','USER',true);

select nextval('accounts_seq');
select nextval('accounts_seq');
INSERT INTO accounts (id,email,"password",status,username,created_by,created_time,updated_time)
VALUES
(1,'chungkoi113@gmail.com','$2a$10$5jPLqGyodh8YEEI775B1leFFF2Sd5GSZ.Xhnf/MOzQitD.roQ0OyK','ACTIVE','chungkoi113@gmail.com','','2022-03-14 16:45:29.142','2022-03-14 16:45:29.142'),
(2,'hoat.tm@tripi.vn','$2a$10$f.OnqKJpMPgxAPz7lKIeIOp4tZ1yO0u8AnBcpE9N33fbgKeVbbYQK','ACTIVE','hoat.tm@tripi.vn','','2022-03-14 16:47:49.328','2022-03-14 16:47:49.328');

select nextval('users_seq');
select nextval('users_seq');
INSERT INTO users (id,email,first_name,last_name,status,username,created_by,created_time,updated_time)
VALUES (1,'chungkoi113@gmail.com','Chung','Vu','ACTIVE','chungkoi113@gmail.com','','2022-03-14 16:45:29.165','2022-03-14 16:45:29.165'),
       (2,'hoat.tm@tripi.vn','hoat.tm@tripi.vn','hoat.tm@tripi.vn','ACTIVE','hoat.tm@tripi.vn','','2022-03-14 16:47:49.329','2022-03-14 16:47:49.329');


select nextval('accounts_roles_seq');
select nextval('accounts_roles_seq');
INSERT INTO accounts_roles (id,account_id,role_id)
VALUES  (1,1,2),
        (3,1,1),
        (4,2,1),
        (2,2,2);