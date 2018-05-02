-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
alter PROCEDURE AddClientBook
	-- Add the parameters for the stored procedure here
	@clientCode varchar(max),
	@firstName varchar(max),
	@secondName varchar(max),
	@cnp varchar(max),
	@ci_serie varchar(max),
	@ci varchar(max),
	@address varchar(max),
	@city varchar(max),
	@district varchar(max),
	@phone varchar(max),
	@email varchar(max),
	@registrationDateClient varchar(max),
	@cgid varchar(max),
	@active varchar(max),
	@mentionsClient varchar(max),
	@bookCode int,
	@isbn varchar(50),
	@title varchar(200),
	@author varchar(200),
	@volume int,
	@appearanceYear int,
	@appearancePlace varchar(100),
	@mentionsBook varchar(200),
	@registrationDateBook date,
	@pid int,
	@gid int,
	@bcid int,
	@lid int,
	@did int,
	@loanDate varchar(max),
	@dueDate varchar(max),
	@mentionsLoan varchar(max)
AS
begin
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    begin tran
		begin try
			if dbo.checkFormatNumber(@clientCode) = 0
			begin
				RAISERROR('ClientCode is not a number', 14, 1)
			end

			if dbo.checkFormatName(@firstName) = 0
			begin
				RAISERROR('FirstName is invalid', 14, 1)				
			end

			if dbo.checkFormatName(@secondName) = 0
			begin
				RAISERROR('SecondName is invalid', 14, 1)				
			end

			if dbo.checkFormatCNP(@cnp) = 0
			begin
				RAISERROR('CNP is invalid', 14, 1)				
			end

			if dbo.checkFormatNumber(@ci_serie) = 0
			begin
				RAISERROR('CI Serie is not a number', 14, 1)				
			end
	
			if dbo.checkFormatName(@ci) = 0
			begin
				RAISERROR('CI is invalid', 14, 1)				
			end

			if dbo.checkFormatName(@city) = 0
			begin
				RAISERROR('City is invalid', 14, 1)				
			end

			if dbo.checkFormatName(@district) = 0
			begin
				RAISERROR('District is invalid', 14, 1)				
			end

			if dbo.checkFormatPhone(@phone) = 0
			begin
				RAISERROR('Phone is invalid', 14, 1)				
			end

			if dbo.checkFormatMail(@email) = 0
			begin
				RAISERROR('Mail is invalid', 14, 1)				
			end

			if dbo.checkFormatDate(@registrationDateClient) = 0
			begin
				RAISERROR('Registration Date is invalid', 14, 1)				
			end

			if dbo.checkFormatNumber(@active) = 0 and (not @active like '%[01]')
			begin
				RAISERROR('Active has to be 0 or 1', 14, 1)				
			end

			if dbo.checkFormatNumber(@cgid) = 0
			begin
				RAISERROR('Client Group ID is not a number', 14, 1)				
			end

			if dbo.checkFormatNumber(@bookCode) = 0
			begin
				RAISERROR('Book code is not a number', 14, 1)				
			end

			if dbo.checkFormatName(@title) = 0
			begin
				RAISERROR('Title is invalid', 14, 1)				
			end

			if dbo.checkFormatName(@author) = 0
			begin
				RAISERROR('Author is invalid', 14, 1)				
			end
			
			if dbo.checkFormatNumber(@volume) = 0
			begin
				RAISERROR('Volume is not a number', 14, 1)				
			end

			if dbo.checkFormatNumber(@appearanceYear) = 0
			begin
				RAISERROR('Appearance Year is not a number', 14, 1)				
			end

			if dbo.checkFormatName(@appearancePlace) = 0
			begin
				RAISERROR('Appearance Place is invalid', 14, 1)				
			end

			if dbo.checkFormatDate(@registrationDateBook) = 0
			begin
				RAISERROR('Registration Date is invalid', 14, 1)				
			end
			
			if dbo.checkFormatNumber(@pid) = 0
			begin
				RAISERROR('Publishing ID is not a number', 14, 1)				
			end

			if dbo.checkFormatNumber(@gid) = 0
			begin
				RAISERROR('Group ID is not a number', 14, 1)				
			end

			if dbo.checkFormatNumber(@bcid) = 0
			begin
				RAISERROR('Book Group ID is not a number', 14, 1)				
			end

			if dbo.checkFormatNumber(@lid) = 0
			begin
				RAISERROR('Language ID is not a number', 14, 1)				
			end

			if dbo.checkFormatNumber(@did) = 0
			begin
				RAISERROR('Domain ID is not a number', 14, 1)				
			end

			if dbo.checkFormatDate(@loanDate) = 0
			begin
				RAISERROR('Loan Date is invalid', 14, 1)				
			end

			if dbo.checkFormatDate(@dueDate) = 0
			begin
				RAISERROR('Due Date is invalid', 14, 1)				
			end

			insert into Clients values(@clientCode, @firstName, @secondName, @cnp, @ci_serie, @ci, @address, @city, @district, @phone, @email, @registrationDateClient, @cgid, @active, @mentionsClient)
			print 'Insert client complet'
			insert into Books values(@bookCode, @isbn, @title, @author, @volume, @appearanceYear, @appearancePlace, @mentionsBook, @registrationDateBook, @pid, @gid, @bcid, @lid, @did)
			print 'Insert book complet'
			declare @bid int
			set @bid = (select IDENT_CURRENT('Books'))
			declare @cid int
			set @cid = (select IDENT_CURRENT('Clients'))
			insert into Loans values(@bid, @cid, @loanDate, @dueDate, @mentionsLoan)
			print 'Insert loan complet'
			commit tran
			select 'Transaction committed'
		end try

		begin catch
			rollback tran
			select 'Transaction rollbacked'
			print ERROR_MESSAGE()
		end catch
	end