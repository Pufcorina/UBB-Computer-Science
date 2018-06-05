-- Dirty reads Part 1
begin transaction
update Cars set CaBrand = 'brand'
where CId = 1
waitfor delay '00:00:10'
rollback transaction