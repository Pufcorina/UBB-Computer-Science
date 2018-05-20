-- Dirty reads Part 2
set transaction isolation level read uncommitted
begin transaction
select * from Languages
waitfor delay '00:00:15'
select * from Languages
commit transaction