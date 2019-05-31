SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';
select @@global.time_zone, @@ session.time_zone;

select *
from playersInfo;


create table playersInfo
(
id int,
salary double,
nationality varchar(32),
workhours int,
joinedSince int,
TravelFee int,
level int,
department varchar(32),
name varchar(32),

products varchar(255),
namesOfCompanies varchar(32),
moneyPerAd double,

newspaper varchar(32),
faceook varchar(32),
twitter varchar(32),

location varchar(32),
date date,
wons int,
losses int,
moneyWon double,

speed int,
height double,
weight double,
position varchar(32),
rating int,
worth double,

type varchar(32)
);

create table uniforms
(
id int,
cost double,
colorofshirt varchar(32),
colorofshort varchar(32),
colorofsocks varchar(32),
brandOfUniform varchar(32),
brandofboots varchar(32)
);


create table upcomingevents
(
id int,
location varchar(32),
date date,
ticketPrice int,
numofaud int
);


create table trainingschedule
(
trainid int,
fromTime date,
toTime date,
trainingType varchar(50)
);

select *
from trainingschedule;


drop table upcomingevents;

