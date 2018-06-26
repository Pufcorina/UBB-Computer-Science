update Users
set City = 'c2'
where UsID = 1

waitfor delay '00:00:03'

update Users
set City = 'c1'
where UsID = 1