-- Dirty reads Part 1
begin transaction
update Languages set LanguageName = 'poloneza'
where LId = 2
waitfor delay '00:00:10'
rollback transaction
