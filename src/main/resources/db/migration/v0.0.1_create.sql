CREATE DATABASE IF NOT EXISTS spec_sheet_manager_kt;

USE spec_sheet_manager_kt;

CREATE TABLE IF NOT EXISTS user (
  id INT(11) NOT NULL  auto_increment PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL ,
  role_type VARCHAR(255) NOT NULL
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
  usr_id INT(11) NOT NULL
);
