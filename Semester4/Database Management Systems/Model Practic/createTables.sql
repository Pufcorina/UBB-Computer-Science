use MiniFacebook
go

if object_id('Likes', 'U') is not null
	drop table Likes
if object_id('Comments', 'U') is not null
	drop table Comments
if object_id('Posts', 'U') is not null
	drop table Posts
if object_id('Pages', 'U') is not null
	drop table Pages
if object_id('Users', 'U') is not null
	drop table Users
if object_id('Catagories', 'U') is not null
	drop table Catagories

create table Catagories(
	CaID smallint primary key identity(1, 1),
	CaName varchar(50),
	CaDescription varchar(100)
)

create table Users(
	UsID int primary key identity(1, 1),
	UsName varchar(50),
	City varchar(100),
	DOB date
)

create table Pages(
	PaID int primary key identity(1, 1),
	PaName varchar(50),
	CaID smallint references Catagories(CaID)
)

create table Posts(
	PoID int primary key identity(1, 1),
	PoText varchar(500),
	PoDate date,
	PoShare int,
	UsID int references Users(UsID)
)

create table Comments(
	CoID int primary key identity(1, 1),
	CoText varchar(500),
	CoDate date,
	TopCom bit,
	PoID int references Posts(PoID)
)

create table Likes(

	UsId int references Users(UsID),
	PaId int references Pages(PaID),
	LDate date,
	primary key(UsId, PaId)
)
