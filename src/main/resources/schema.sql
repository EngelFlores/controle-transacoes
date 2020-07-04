-- BANK
CREATE TABLE bank (
  id                       int(05)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  number                   varchar(20)  NOT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

-- AGENCY
CREATE TABLE agency (
  id                       int(08)      NOT NULL AUTO_INCREMENT,
  id_bank                  int(05)      NOT NULL,
  name                     varchar(100) NOT NULL,
  number                   varchar(20)  NOT NULL,
  PRIMARY KEY (id),
  KEY FK_BANK_IDX (id_bank),
  CONSTRAINT FK_BANK FOREIGN KEY (id_bank) REFERENCES bank (id)
) DEFAULT CHARSET=utf8;

-- TRANSACTION CHANNEL
CREATE TABLE transaction_channel (
  id                       int(05)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

-- TRANSACTION_TYPE
CREATE TABLE transaction_type (
  id                       int(05)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  action                   varchar(06)  NOT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

-- CUSTOMER
CREATE TABLE customer (
  id                       int(15)      NOT NULL AUTO_INCREMENT,
  name                     varchar(100) NOT NULL,
  type                     varchar(10)  NOT NULL,
  tin                      varchar(9)   NOT NULL,
  PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

-- CUSTOMER_ACCOUNT
CREATE TABLE customer_account (
  id                       int(20)      NOT NULL AUTO_INCREMENT,
  id_customer              int(15)      NOT NULL,
  id_agency                int(08)      NOT NULL,
  account_balance          double       NOT NULL,
  PRIMARY KEY (id),
  KEY FK_CUSTOMER_IDX (id_customer),
  CONSTRAINT FK_CUSTOMER FOREIGN KEY (id_customer) REFERENCES customer (id),
  KEY FK_AGENCY_IDX (id_agency),
  CONSTRAINT FK_AGENCY FOREIGN KEY (id_agency) REFERENCES agency (id)  
) DEFAULT CHARSET=utf8;

-- TRANSACTION_ACCOUNT
CREATE TABLE transaction_account (
  id                       int(30)      NOT NULL AUTO_INCREMENT,
  id_transaction_channel   int(05)      NOT NULL,
  id_transaction_type      int(05)      NOT NULL,
  id_customer_account      int(20)      NOT NULL,
  amount                   double(12,4) NOT NULL,
  transaction_moment       datetime     NOT NULL,
  PRIMARY KEY (id),
  KEY FK_TRANSACTION_CHANNEL_IDX (id_transaction_channel),
  CONSTRAINT FK_TRANSACTION_CHANNEL FOREIGN KEY (id_transaction_channel) REFERENCES transaction_channel (id),
  KEY FK_TRANSACTION_TYPE_IDX (id_transaction_type),
  CONSTRAINT FK_TRANSACTION_TYPE FOREIGN KEY (id_transaction_type) REFERENCES transaction_type (id),
  KEY FK_CUSTOMER_ACCOUNT_IDX (id_customer_account),
  CONSTRAINT FK_CUSTOMER_ACCOUNT FOREIGN KEY (id_customer_account) REFERENCES customer_account (id)
) DEFAULT CHARSET=utf8;
