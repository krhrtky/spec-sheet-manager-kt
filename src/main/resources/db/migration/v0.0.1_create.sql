CREATE DATABASE IF NOT EXISTS spec_sheet_manager_kt;

USE spec_sheet_manager_kt;

CREATE TABLE IF NOT EXISTS user (
  id              int auto_increment primary key,
  email           varchar(255) not null,
  name            varchar(255) not null,
  digest_password varchar(255) not null,
  enabled         tinyint(1)   not null,
  role_type       varchar(255) not null
);

CREATE TABLE IF NOT EXISTS project (
  id INT(11) NOT NULL  auto_increment PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  language VARCHAR(255) NOT NULL,
  framework VARCHAR(255) NOT NULL,
  middleware VARCHAR(255) NOT NULL,
  about VARCHAR(255) NOT NULL,
  user_id INT(11) NOT NULL
);
