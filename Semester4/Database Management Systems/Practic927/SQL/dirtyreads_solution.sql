-- Solution: Dirty reads Part 2
set transaction isolation level read committed
begin transaction
select * from Cars
waitfor delay '00:00:15'
select * from Cars
commit transaction