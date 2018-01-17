CREATE TABLE Domains
(DId INT PRIMARY KEY IDENTITY,
DomainName varchar(100) NOT NULL
)

CREATE TABLE Publishing
(PId INT PRIMARY KEY IDENTITY,
PublishingName varchar(100) NOT NULL,
Adress varchar(200),
Phone varchar(20),
Email varchar(50),
Website varchar(50) 
)

CREATE TABLE Groups
(GId INT PRIMARY KEY IDENTITY,
GroupName varchar(50) NOT NULL
)

CREATE TABLE Languages
(LId INT PRIMARY KEY IDENTITY,
LanguageName varchar(50) NOT NULL
)

CREATE TABLE ClientGroups
(CGId INT PRIMARY KEY IDENTITY,
ClientGroupName varchar(50) NOT NULL
)

CREATE TABLE BookCollections
(BCId INT PRIMARY KEY IDENTITY,
BookCollectionName varchar(50) NOT NULL
)

CREATE TABLE Clients
(CId INT PRIMARY KEY IDENTITY,
ClientCode INT NOT NULL,
FirstName varchar(50) NOT NULL,
SecondName varchar(50) NOT NULL,
CNP varchar(14),
CI_Serie varchar(10),
CI varchar(10),
Adress varchar(100),
City varchar(50) NOT NULL,
District varchar(50) NOT NULL,
Phone varchar(20) NOT NULL,
Email varchar(50) NOT NULL,
RegistrationDate date NOT NULL,
CGId INT FOREIGN KEY REFERENCES ClientGroups(CGId),
Active BIT default 0,
Mentions varchar(200)
)

CREATE TABLE Books
(BId INT PRIMARY KEY IDENTITY,
BookCode INT NOT NULL,
ISBN varchar(50) default '-',
Title varchar(200) NOT NULL,
Author varchar(200) NOT NULL,
Volume INT default 1,
AppearanceYear INT NOT NULL,
AppearancePlace varchar(100) NOT NULL,
Mentions varchar(200),
RegistrationDate date NOT NULL,
PId INT FOREIGN KEY REFERENCES Publishing(PId),
GId INT FOREIGN KEY REFERENCES Groups(GId),
BCId INT FOREIGN KEY REFERENCES BookCollections(BCId),
LId INT FOREIGN KEY REFERENCES Languages(LId),
DId INT FOREIGN KEY REFERENCES Domains(DId)
)

CREATE TABLE History
BId INT FOREIGN KEY REFERENCES Books(BId),
CId INT FOREIGN KEY REFERENCES Clients(CId),
LoanDate date NOT NULL,
primary key (BId, CId, LoanDate),
DueDate date NOT NULL,
RestitutionDate date NOT NULL,
Mentions varchar(200)
)

CREATE TABLE Loans
(BId INT FOREIGN KEY REFERENCES Books(BId),
CId INT FOREIGN KEY REFERENCES Clients(CId),
LoanDate date NOT NULL,
primary key (BId, CId, LoanDate),
DueDate date NOT NULL,
Mentions varchar(200)
)