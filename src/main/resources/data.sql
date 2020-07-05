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
INSERT INTO transaction_type VALUES (1,'Cash deposit','1')
                                   ,(2,'Check deposit','1')
                                   ,(3,'Transfer from other account','1')
                                   ,(4,'Transfer from other bank','1')
                                   ,(5,'International deposit','1')
                                   ,(6,'Cash withdraw','0')
                                   ,(7,'Cashed check','0')
                                   ,(8,'Bill payment','0')
                                   ,(9,'Bill scheduled payment','0')
                                   ,(10,'International shipping','0')
                                   ,(11,'Federal Tax','0');

-- Inserting Transaction Channels
INSERT INTO transaction_channel VALUES (1,'Agência')
                                      ,(2,'Parceiro')
                                      ,(3,'ATM')
                                      ,(4,'Telefone')
                                      ,(5,'Internet')
                                      ,(6,'App');

-- Inserting Customers
INSERT INTO customer VALUES (1,'Peter Parker','1','111222333')
                           ,(2,'Tony Stark','1','123456789')
                           ,(3,'Bruce Banner','1','222333444')
                           ,(4,'Steve Rogers','1','333444555')
                           ,(5,'Nick Fury','0','013324880');

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
