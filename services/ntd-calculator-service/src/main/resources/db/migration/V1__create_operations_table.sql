create table operations (
    id bigint not null auto_increment primary key,
    type varchar(255) not null,
    cost decimal(6,2) not null,
    created_date timestamp not null
);