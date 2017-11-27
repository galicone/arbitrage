create table "user" (
  id serial primary key,
  username varchar(50) not null,
  password varchar(80) not null,
  enabled boolean not null
);
