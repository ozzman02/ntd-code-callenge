create table users (
    id varchar(256) not null auto_increment primary key,
    username varchar(255) unique not null,
    password varchar(255) not null,
    status varchar(255) not null,
    balance decimal(6,2) not null,
    created_date timestamp not null
);