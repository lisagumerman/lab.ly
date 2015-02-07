create table lably_account (
  id int serial unique primary key,
  name varchar(255)
);

create table lably_user (
  id int serial primary key,
  first_name varchar(64),
  last_name varchar(64),
  email_address varchar(254)

);


create table account_users (
  account_id int,
  user_id int
);

create table email_signup(
  id int serial primary key,
  email_address varchar(254)
);

