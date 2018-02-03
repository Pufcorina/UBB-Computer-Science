use [Practic Properties]
go

create view topProperties
as

select *
from Properties
where (select count(*) from WishList W
inner join Properties P on P.PId = W.PId
 where W.PId = P.PId) > 0.1 * (select count(*) from Customers)
