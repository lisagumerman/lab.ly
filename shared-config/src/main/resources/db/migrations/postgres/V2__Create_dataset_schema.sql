
create table lably_datasets (
  id serial unique primary key,
  name varchar(64) not null,
  entity_key varchar(64) not null,
  owner_id int not null
);

create table lably_column_definition (
  owner_id int,
  column_type bit not null,
  col_name varchar(64) not null,
  column_index int not null
);