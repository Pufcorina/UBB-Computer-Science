-- Non-repeatable reads part 1

insert into Languages values('greaca')
begin transaction
waitfor delay '00:00:05'
update Languages set LanguageName = 'latina'
where LanguageName = 'greaca'
commit transaction
