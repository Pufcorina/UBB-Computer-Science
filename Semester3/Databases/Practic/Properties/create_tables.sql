use [Practic Properties]
go

if object_id('VisitedHistory', 'U') is not null
	drop table VisitedHistory
if object_id('WishList', 'U') is not null
	drop table WishList
if object_id('AgenciesProperties', 'U') is not null
	drop table AgenciesProperties
if object_id('Properties', 'U') is not null
	drop table Properties
if object_id('Agencies', 'U') is not null
	drop table Agencies
if object_id('Customers', 'U') is not null
	drop table Customers
if object_id('Category', 'U') is not null
	drop table Category

create table Agencies
(
AId int primary key identity (1,1),
[name] varchar(100)
)

create table Customers
(
CId int primary key identity (1,1),
[name] varchar(200)
)

create table Category
(
CId int primary key identity (1,1),
[name] varchar(50)
)

create table Properties
(
PId int primary key identity (1,1),
[address] varchar(MAX),
[transaction] float,
CId int references Category(CId)
)

create table AgenciesProperties
(
AId int references Agencies(AId),
PId int references Properties(PId),
primary key(AId, PId),
pret int
)

create table VisitedHistory
(
PId int references Properties(PId),
CId int references Customers(CId),
primary key(PId, CId),
timestamp
)

create table WishList
(
PId int references Properties(PId),
CId int references Customers(CId),
added datetime,
primary key(PId, CId)
)
