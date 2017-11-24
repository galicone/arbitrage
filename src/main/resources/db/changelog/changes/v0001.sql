create table "user" (
  id varchar(255) not null,
  username varchar(50) not null,
  password varchar(80) not null,
  enabled boolean not null,
  primary key (id)
);
