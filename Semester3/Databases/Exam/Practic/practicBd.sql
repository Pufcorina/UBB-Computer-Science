use CGames
go

if object_id('HeroList', 'U') is not null
	drop table HeroList
if object_id('Cinematics', 'U') is not null
	drop table Cinematics
if object_id('Games', 'U') is not null
	drop table Games
if object_id('Heroes', 'U') is not null
	drop table Heroes 
if object_id('Companies', 'U') is not null
	drop table Companies


create table Companies
(
	CId int primary key identity,
	[name] varchar(100),
	[description] varchar(500),
	website varchar(200)
)

create table Games
(
	GId int primary key identity,
	[name] varchar(100),
	releaseDate date,
	CompanyId int references Companies(CId)
)

create table Heroes
(
	HId int primary key identity,
	[name] varchar(100),
	[description] varchar(500),
	importance varchar(50)
)

create table Cinematics
(
	CId int primary key identity,
	[name] varchar(100),
	GId int references Games(GId)
)

create table HeroList
(
	HId int references Heroes(HId),
	CId int references Cinematics(CId),
	primary key(HId, CId),
	entryMoment time
)

go

insert into Companies values ('a', 'abc', 'www.google.com')

insert into Games values ('dc game1', '2000-12-01', 1),
							('dc game2', '2005-12-01', 1),
							('dc game1', '2016-01-01', 1),
							('dc game1', '2016-12-01', 1)

insert into Heroes values ('Superman', 'a', 'main'), ('Superman2', 'a', 'main')

insert into Cinematics values ('c1', 1), ('c2', 2), ('c3', 1)

insert into HeroList values (1, 1, '02:09'), (1, 2, '00:09'), (1, 3, '00:09'), (2, 1, '00:09')

go

alter view NameImportance
as
select H.name, H.importance
from Heroes as H 
inner join HeroList as HL on H.HId = HL.HId
group by H.name, H.importance
having ((select count(HL.HId) as nr) = (select count(*) as Expr1 from Cinematics))

go

select * from NameImportance

go

alter proc insertThings
	@HeroName varchar(100),
	@HeroDescription varchar(500),
	@HeroImportance varchar(50),

	@CinematicName varchar(100),
	@CinematicGameId int,

	@EntryMoment time
as
	declare @existGameId int = (select Games.GId from Games where Games.GId = @CinematicGameId)
	if @existGameId is null
	begin
		print 'Game id does not exist'
		return 1
	end

	declare @existCinematic int = (select C.CId from Cinematics C where C.GId = @CinematicGameId and C.name = @CinematicName)
	declare @existHero int
	declare @getCinematicId int 
	declare @getHeroId int

	if @existCinematic is null
	begin
		insert into Cinematics values (@CinematicName, @CinematicGameId)
		set @existHero = (select H.HId from Heroes H where H.name = @HeroName and H.importance = @HeroImportance and H.description = @HeroDescription)
		if @existHero is null
		begin
			print 'Hero inserted'
			insert into Heroes values (@HeroName, @HeroDescription, @HeroImportance)
		end
		else
			print 'Hero exists'

		set @getHeroId = (select H.HId from Heroes H where H.name = @HeroName and H.importance = @HeroImportance and H.description = @HeroDescription)
		set @getCinematicId = (select C.CId from Cinematics C where C.GId = @CinematicGameId and C.name = @CinematicName)
		insert HeroList values (@getHeroId, @getCinematicId, @EntryMoment)
	end

	if @existCinematic is not null
	begin
		set @existHero = (select H.HId from Heroes H where H.name = @HeroName and H.importance = @HeroImportance and H.description = @HeroDescription)
		if @existHero is null
		begin
			print 'Hero inserted'
			insert into Heroes values (@HeroName, @HeroDescription, @HeroImportance)
		end
		else
			print 'Hero exists'

		set @getHeroId = (select H.HId from Heroes H where H.name = @HeroName and H.importance = @HeroImportance and H.description = @HeroDescription)
		set @getCinematicId = (select C.CId from Cinematics C where C.GId = @CinematicGameId and C.name = @CinematicName)

		update HeroList
		set entryMoment = @EntryMoment
		where HeroList.CId = @getCinematicId and HeroList.HId = @getHeroId
	end

go

exec insertThings 'Sueper Woman', 'a', 'main', 'c4', 2, '09:01'
exec insertThings 'Sueper Woman', 'a', 'main', 'c4', 2, '09:06'

select * from Heroes
select * from HeroList
select * from Cinematics

go

alter function GamesDate()
returns table
as
return 
select distinct C.name as CompaniesName, G.name as GameName, CG.name as CinematicName, G.releaseDate
from Games G
inner join Companies C on G.CompanyId = C.CId
inner join Cinematics CG on CG.GId = G.GId
where G.releaseDate >= '2000-12-02' and G.releaseDate <= '2016-01-01'

go

select * from GamesDate()