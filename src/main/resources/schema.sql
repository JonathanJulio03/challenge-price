DROP TABLE IF EXISTS PRICES;
DROP TABLE IF EXISTS BRANDS;

create table BRANDS (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(50) not null
);

create table PRICES (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  brand_id bigint,
  product_id bigint,
  start_date DATETIME,
  end_date DATETIME,
  priority int,
  price_list int,
  price double,
  curr varchar(5)
);

