use [Practic Properties]
go

alter proc insertPropertyToAllCustomers
@PropertyId int
as

declare @PId int = (select Properties.PId from Properties where Properties.PId = @PropertyId)

if @PId is not null
begin
	declare @myCursor cursor
	declare @myField int

	set @myCursor = cursor for
					select Customers.CId from Customers

	open @myCursor

	fetch next from @myCursor into @myField

	while @@FETCH_STATUS = 0
	begin
		print @myField
		declare @dataAdaugare date = (select WishList.added from WishList where WishList.CId = @myField and WishList.PId = @PId)
		print @dataAdaugare
		if @dataAdaugare is null
			insert into WishList values(@PropertyId, @myField, GETDATE())
		else
		begin
			if @dataAdaugare < getdate()
			begin
				delete from WishList where PId = @PropertyId and CId = @myField
			end
		end
		fetch next from @myCursor into @myField
	end

	close @myCursor
	deallocate @myCursor
end