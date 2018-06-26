use CarTraffic
go

if object_id('CarsRoads', 'U') is not null
	drop table CarsRoads
if object_id('Drivers', 'U') is not null
	drop table Drivers
if object_id('Roads', 'U') is not null
	drop table Roads
if object_id('TypeRoads', 'U') is not null
	drop table TypeRoads
if object_id('Cars', 'U') is not null
	drop table Cars 

create table Cars(
	CId int primary key identity(1, 1),
	CaBrand varchar(50),
	CaBrandType varchar(100),
	CaNoPlaces smallint,
	CaFabricationYear smallint
)

create table TypeRoads(
	TRId smallint primary key identity(1, 1),
	RTName varchar(50),
	RTQuality varchar(50),
	RTAverageLength int
)

create table Roads(
	RId int primary key identity(1, 1),
	RName varchar(50),
	RLength varchar(100),
	RBeginCity varchar(50),
	REndCity varchar(50),
	TypeId smallint references TypeRoads(TRId)
)

create table Drivers(
	DId int primary key identity(1, 1),
	DName varchar(50),
	DExperience int,
	RoadId int references Roads(RId)
)

create table CarsRoads(
	CId int references Cars(CId),
	RId int references Roads(RId),
	DrivingMoment datetime,
	NoCars int,
	primary key(CId, RId, DrivingMoment)
)


insert into TypeRoads values('rt1', 'low', 34), ('rt2', 'high', 340), ('rt3', 'medium', 3004)
insert into Roads values('r1', 123, 's1', 'e1', 1), ('r2', 123, 's1', 'e1', 2), ('r3', 123, 's1', 'e1', 3)
insert into Drivers values('d1', 3, 1)
