create schema if not exists spring_security;

use spring_security;

create table if not exists user
(
    id       bigint primary key auto_increment,
    username varchar(40)  not null,
    password varchar(255) not null
);

create table if not exists authority
(
    id      bigint primary key auto_increment,
    user_id bigint                 not null,
    type    enum ('READ', 'WRITE') not null,

    constraint unique (user_id, type),
    constraint foreign key (user_id) references user (id)
);

