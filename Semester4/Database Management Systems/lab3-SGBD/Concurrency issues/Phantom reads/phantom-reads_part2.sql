-- Phantom Reads Part 2
use BookLibrary
go

set transaction isolation level repeatable read
begin transaction
select * from Languages
waitfor delay '00:00:05'
select * from Languages
commit transaction