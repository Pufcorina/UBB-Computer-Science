-- Deadlock part 1
use BookLibrary
go

begin transaction
update Languages set LanguageName = 'irlandeza 1' where LId = 1097
waitfor delay '00:00:10'
update Groups set GroupName = 'transaction 1' where GId = 4
commit transaction