-- Deadlock part 2 solution
use BookLibrary
go

set deadlock_priority high
begin transaction
update Groups set GroupName = 'transaction 2' where GId = 4
waitfor delay '00:00:10'
update Languages set LanguageName = 'irlandeza 2' where LId = 1097
commit transaction