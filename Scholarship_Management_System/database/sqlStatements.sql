drop database rrs;
create database rrs;
use rrs;

create table student(
    pid int auto_increment,
    email varchar(50) not null,
    pwd varchar(20) not null,
    name varchar(30) not null,
    gender varchar(1) not null,
    city varchar(20) not null,
    contact varchar(10) not null,
    unique(email),
    primary key(pid)
);

create table scholarship(
    tid int,
    name varchar(20) not null,
    source varchar(50) not null,
    destination varchar(50) not null,
    max_seats int not null,
    duration int not null,
    primary key(tid)
);

create table status(
    ticno int auto_increment,
    pid int not null,
    tid int not null,
    dot varchar(15) not null,
    ano int not null,
    cno int not null,
    ticstatus varchar(15),
    primary key(ticno)
);

insert into student(email, pwd, name, gender, city, contact) values("vicky@gmail.com", "1234", "vicky", "m", "kpm", 1234567890);
insert into student(email, pwd, name, gender, city, contact) values("admin@gmail.com", "admin", "Admin", "f", "che", 7894561230);
insert into student(email, pwd, name, gender, city, contact) values("user@gmail.com", "user", "user", "f", "che", 6374388346);
insert into student(email, pwd, name, gender, city, contact) values("ssn@gmail.com", "ssn", "ssn", "f", "che", 7894561230);
insert into student(email, pwd, name, gender, city, contact) values("schofficer@gmail.com", "schofficer", "Officer", "m", "che", 7804561230);
insert into student(email, pwd, name, gender, city, contact) values("staff@gmail.com", "staff", "Staff", "f", "che", 7894891230);


insert into scholarship(tid, name, source, destination, max_seats, duration) values(1, "student", "BC MBC", "BC", 10000, 68);
insert into scholarship(tid, name, source, destination, max_seats, duration) values(2, "student", "Adi Dravidar", "SC", 50000, 70);
insert into scholarship(tid, name, source, destination, max_seats, duration) values(3, "student", "PhD Scholarship", "General", 74864, 80);
insert into scholarship(tid, name, source, destination, max_seats, duration) values(4, "student", "Incentive Scheme", "MBC/DNC", 78993, 85);

insert into status(pid, tid, dot, ano, cno, ticstatus) values(1, 1, "student", 234433333, 13, "Confirmed");
insert into status(pid, tid, dot, ano, cno, ticstatus) values(1, 2, "student", 324433542, 22, "Waiting");
insert into status(pid, tid, dot, ano, cno, ticstatus) values(1, 3, "research", 355454666, 21, "Waiting");
insert into status(pid, tid, dot, ano, cno, ticstatus) values(1, 4, "research", 367523456, 72, "Confirmed");

select * from student;
select * from scholarship;
select * from status;

delete from scholarship where tid=5;
