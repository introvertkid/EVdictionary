create database if not exists envi;

use envi;

create table if not exists envi
(
	id int not null auto_increment,
    target varchar(255) not null default '',
    definition varchar(255) not null default '',
    primary key(id)
);