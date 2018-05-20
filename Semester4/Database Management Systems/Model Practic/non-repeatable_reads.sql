--set transaction isolation level repeatable read
begin transaction
select * from Users where UsId = 1
waitfor delay '00:00:06'
select * from Users where UsId = 1
commit transaction