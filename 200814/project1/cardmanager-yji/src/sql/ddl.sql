// ddl.sql for BusinessCard

create user connectuser@localhost identified by 'connect123!@#';

create database connectdb character set=utf8;

grant all privileges on connectdb.* to 'connectuser'@'localhost';

create table connectdb.MEMBER (
    NAME varchar(100),
	PHONE varchar(100),
	COMPANYNAME varchar(255),
    CREATEDATE date,
) engine=InnoDB character set = utf8;
