-- Inserting Banks
INSERT INTO bank VALUES (1,'Banco do Brasil','001')
                       ,(2,'Caixa Econômica Federal','104')
                       ,(3,'Itaú','341')
                       ,(4,'Bradesco','237')
                       ,(5,'Santander','044');

-- Inserting Agencies
INSERT INTO agency VALUES (1,1,'Central','0100')
                         ,(2,2,'Matriz','2000')
                         ,(3,3,'Siqueira Campos','3250');

-- Inserting Transaction Types
INSERT INTO transaction_type VALUES (1,'Account Statement','NONE')
                                   ,(2,'Cash deposit','CREDIT')
                                   ,(3,'Check deposit','CREDIT')
                                   ,(4,'Transfer from other account','CREDIT')
                                   ,(5,'Transfer from other bank','CREDIT')
                                   ,(6,'International deposit','CREDIT')
                                   ,(7,'Cash withdraw','DEBIT')
                                   ,(8,'Cashed check','DEBIT')
                                   ,(9,'Bill payment','DEBIT')
                                   ,(10,'Bill scheduled payment','DEBIT')
                                   ,(11,'International shipping','DEBIT')
                                   ,(12,'Federal Tax','DEBIT');

-- Inserting Transaction Channels
INSERT INTO transaction_channel VALUES (1,'Agency')
                                      ,(2,'Partner')
                                      ,(3,'ATM')
                                      ,(4,'Telephone')
                                      ,(5,'Internet')
                                      ,(6,'App');

-- Inserting Customers
INSERT INTO customer VALUES (1,'Peter Parker','INDIVIDUAL','111222333')
                           ,(2,'Tony Stark','EMPLOYER','123456789')
                           ,(3,'Bruce Banner','INDIVIDUAL','222333444')
                           ,(4,'Steve Rogers','INDIVIDUAL','333444555')
                           ,(5,'Nick Fury','INDIVIDUAL','013324880');

-- Inserting Customer Accounts
INSERT INTO customer_account VALUES (1,1,1,20)
                                   ,(2,2,2,15000000)
                                   ,(3,3,3,1000)
                                   ,(4,4,1,40000)
                                   ,(5,5,2,300000);

-- Inserting into Transaction Accounts
INSERT INTO transaction_account VALUES (1,1,1,1,200,sysdate())
                                      ,(2,1,2,1,300,sysdate())
                                      ,(3,1,6,1,500,sysdate());
