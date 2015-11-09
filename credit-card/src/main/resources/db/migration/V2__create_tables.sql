create table consumers (
  id integer primary key auto_increment,
  billDay integer,
  repaymentDay integer,
  name varchar(255),
  creditLine integer
);

CREATE TABLE bills (
  id integer PRIMARY KEY AUTO_INCREMENT,
  amount integer,
  createdAt timestamp,
  billDate DATE,
  repayment DATE,
  consumerId integer
);

CREATE TABLE paymentRequests (
  id integer PRIMARY KEY AUTO_INCREMENT,
  amount integer,
  createdAt timestamp,
  status integer,
  consumeBy integer
);

CREATE  TABLE billItems (
  id integer PRIMARY KEY AUTO_INCREMENT,
  amount integer,
  billId integer,
  itemType integer
);

CREATE TABLE instalmentPolicies (
  id integer PRIMARY KEY AUTO_INCREMENT,
  term integer,
  commission integer
);

CREATE TABLE instalmentRequests (
  id integer PRIMARY KEY AUTO_INCREMENT,
  billId integer,
  amount integer,
  createdAt timestamp,
  status integer,
  policyId integer
);

CREATE TABLE InstalmentItems (
  id integer PRIMARY KEY AUTO_INCREMENT,
  instalmentRequestId INTEGER,
  amount integer,
  commission integer,
  repaymentDay DATE
);