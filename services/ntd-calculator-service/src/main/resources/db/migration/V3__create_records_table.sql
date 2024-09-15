create table records (
    id bigint not null auto_increment primary key,
    operation_id bigint not null,
    user_id bigint not null,
    amount decimal(6,2) not null,
    user_balance decimal(6,2) not null,
    operation_value varchar(3000) not null,
    operation_response double,
    created_date timestamp not null,
    constraint operations_pk foreign key (operation_id) references operations(id),
    constraint users_pk foreign key (user_id) references users(id)
);