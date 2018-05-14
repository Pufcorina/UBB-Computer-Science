-- Phantom Reads Part 2 - solution
use BookLibrary
go

set transaction isolation level serializable
begin transaction
select * from Languages
waitfor delay '00:00:05'
select * from Languages
commit transaction