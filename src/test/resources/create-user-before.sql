delete from user_role;
delete from usr;

insert into usr(id, active, password, username) values
(1, true, '$2a$08$i.IuUElGKNz/lqX.Jd9rROAgtXDd4VASNjaI7/BxgmRStHGWkT8qm', 'niki'),
(2, true, '$2a$08$i.IuUElGKNz/lqX.Jd9rROAgtXDd4VASNjaI7/BxgmRStHGWkT8qm', 'guest');

insert into user_role(user_id, roles) values
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');