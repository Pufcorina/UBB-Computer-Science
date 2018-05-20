-- Phantom Reads Part 1
use BookLibrary
go

begin transaction
waitfor delay '00:00:05'
insert into Languages values ('suedeza')
commit transaction