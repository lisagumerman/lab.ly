create table lably_account (
  id int identity primary key,
  name varchar(255)
);

create table lably_user (
  id int primary key,
  firstName varchar(64),
  lastName varchar(64)
);


create table account_users (
  account_id int,
  user_id int
);