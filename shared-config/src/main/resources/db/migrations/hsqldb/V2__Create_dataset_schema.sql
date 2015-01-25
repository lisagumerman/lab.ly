
create table lably_datasets (
  id int identity primary key,
  entity_key varchar(64) not null
);

create table lably_column_definition (
  column_type bit not null,
  col_name varchar(64) not null,
  column_index int not null
);