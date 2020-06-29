select  b.id
       ,b.name    as 'Bank name'
       ,b.number  as 'Bank number'
       ,a.name    as 'Agency name'
       ,a.number  as 'Agency number'
from    bank   b
       ,agency a
where   a.id_bank = b.id;

select  c.name             as 'Customer'
       ,b.name             as 'Bank'
       ,a.name             as 'Agency'
       ,u.account_balance  as 'Balance'
from    customer_account u
       ,customer         c
       ,agency           a
       ,bank             b
where   c.id          = u.id_customer
  and   a.id          = u.id_agency
  and   b.id          = a.id_bank;
