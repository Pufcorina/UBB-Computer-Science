-- Deadlock part 2
use BookLibrary
go

begin transaction
update Groups set GroupName = 'transaction 2' where GId = 4
waitfor delay '00:00:10'
update Languages set LanguageName = 'irlandeza 2' where LId = 1097
commit transaction