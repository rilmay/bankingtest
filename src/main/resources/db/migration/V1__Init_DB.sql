
DROP TABLE IF EXISTS message;

CREATE TABLE message (
  id int8 not null,
  file_name varchar(255),
  tag varchar(255),
  text varchar(2048) not null,
  user_id int8,
  primary key (id)
);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
  user_id int8 not null,
  roles varchar(255)
);

DROP TABLE IF EXISTS usr;

CREATE TABLE usr (
  id int8 not null,
  active boolean not null,
  password varchar(255) not null,
  username varchar(255) not null,
  primary key (id)
);

alter table if exists message add constraint message_user_FK foreign key (user_id) references usr;

alter table if exists user_role add constraint user_role_FK foreign key (user_id) references usr;
