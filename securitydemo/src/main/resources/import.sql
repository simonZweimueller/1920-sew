insert into student (userid, firstname, lastname) values ('it0001', 'Max', 'Muster');
insert into student (userid, firstname, lastname) values ('it0002', 'Susi', 'Sonne');
insert into student (userid, firstname, lastname) values ('it0003', 'Hansi', 'Huber');
insert into student (userid, firstname, lastname) values ('it0004', 'Berti', 'Bauer');

drop table quarkus_user;

create table quarkus_user (id int, username varchar(50), password varchar(250), salt varchar(250), iteration int,role varchar(255));

insert into quarkus_user values (1, 'max', '152oXFALxskG9maa65IMh96TwfeQWGM=', 'jEK2oFYlP9+BANTuxXRPSA==', 10, 'admin');
insert into quarkus_user values (2, 'susi', '152oXFALxskG9maa65IMh96TwfeQWGM=', 'jEK2oFYlP9+BANTuxXRPSA==', 10, 'user');