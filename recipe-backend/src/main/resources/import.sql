create table sec_user (id int, username varchar(50), password varchar(250), salt varchar(250), iteration int,role varchar(255));

insert into sec_user values (1, 'max', '152oXFALxskG9maa65IMh96TwfeQWGM=', 'jEK2oFYlP9+BANTuxXRPSA==', 10, 'admin');
insert into sec_user values (2, 'susi', '152oXFALxskG9maa65IMh96TwfeQWGM=', 'jEK2oFYlP9+BANTuxXRPSA==', 10, 'user');