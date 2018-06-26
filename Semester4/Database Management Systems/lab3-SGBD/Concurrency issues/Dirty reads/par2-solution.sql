-- Solution: Dirty reads Part 2
set transaction isolation level read committed
begin transaction
select * from Languages
waitfor delay '00:00:15'
select * from Languages
commit transaction