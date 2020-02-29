-- drop database transaction_system;

-- show databases;

-- create database transaction_system;

-- use transaction_system;

-- show tables;

-- BANK
CREATE TABLE bank (
  id                       int(05)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  number                   varchar(20)  NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- AGENCY
CREATE TABLE agency (
  id                       int(08)      NOT NULL AUTO_INCREMENT,
  bank_id                  int(05)      NOT NULL,
  name                     varchar(100) NOT NULL,
  number                   varchar(20)  NOT NULL,
  PRIMARY KEY (id),
  KEY FK_BANK_IDX (bank_id),
  CONSTRAINT FK_BANK FOREIGN KEY (bank_id) REFERENCES bank (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TRANSACTION CHANNEL
CREATE TABLE transaction_channel (
  id                       int(05)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TRANSACTION_TYPE
CREATE TABLE transaction_type (
  id                       int(02)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  action                   varchar(01)  NOT NULL, -- (C)redit or (D)ebit the Customer Account
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CUSTOMER
CREATE TABLE customer (
  id                       int(15)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  type                     varchar(1)   NOT NULL, -- '(E)mployer' or '(I)ndividual'
  tin                      varchar(9)   NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- CUSTOMER_ACCOUNT
CREATE TABLE customer_account (
  id                       int(20)      NOT NULL AUTO_INCREMENT,
  customer_id              int(15)      NOT NULL,
  agency_id                int(08)      NOT NULL,
  account_balance          double       NOT NULL,
  PRIMARY KEY (id),
  KEY FK_CUSTOMER_IDX (customer_id),
  CONSTRAINT FK_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (id),
  KEY FK_AGENCY_IDX (agency_id),
  CONSTRAINT FK_AGENCY FOREIGN KEY (agency_id) REFERENCES agency (id)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TRANSACTION_ACCOUNT
CREATE TABLE transaction_account (
  id                       int(30)      NOT NULL AUTO_INCREMENT,
  transaction_channel_id   int(05)      NOT NULL,
  transaction_type_id      int(05)      NOT NULL,
  customer_account_id      int(20)      NOT NULL,
  amount                   double(12,4) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_TRANSACTION_CHANNEL_IDX (transaction_channel_id),
  CONSTRAINT FK_TRANSACTION_CHANNEL FOREIGN KEY (transaction_channel_id) REFERENCES transaction_channel (id),
  KEY FK_TRANSACTION_TYPE_IDX (transaction_type_id),
  CONSTRAINT FK_TRANSACTION_TYPE FOREIGN KEY (transaction_type_id) REFERENCES transaction_type (id),
  KEY FK_CUSTOMER_ACCOUNT_IDX (customer_account_id),
  CONSTRAINT FK_CUSTOMER_ACCOUNT FOREIGN KEY (customer_account_id) REFERENCES customer_account (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

show tables;