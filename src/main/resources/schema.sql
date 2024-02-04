create schema if not exists spring_security;

use spring_security;

create table if not exists user (
    id bigint primary key auto_increment,
    username varchar(40) not null,
    password varchar(255) not null
);