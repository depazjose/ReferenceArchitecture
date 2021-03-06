
CREATE TABLE IF NOT EXISTS shedlock(name VARCHAR(64) NOT NULL, lock_until TIMESTAMP(3) NOT NULL, locked_at TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3), locked_by VARCHAR(255) NOT NULL, PRIMARY KEY (name));


drop table if exists book_data CASCADE;

create table book_data (
  id bigint generated by default as identity,
  author varchar(255),
  available boolean,
  isbn bigint,
  name varchar(255),
  properties varchar(255),
  quantity integer,
  start_sale_date timestamp,
  status varchar(255),
  primary key (id)
);

