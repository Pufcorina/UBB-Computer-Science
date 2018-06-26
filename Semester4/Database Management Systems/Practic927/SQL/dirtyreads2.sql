-- Dirty reads Part 2
set transaction isolation level read uncommitted
begin transaction
select * from Cars
waitfor delay '00:00:15'
select * from Cars
commit transaction