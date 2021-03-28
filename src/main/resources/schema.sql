create table if not exists todo (
    id identity,
    name varchar (255) not null,
    description varchar(255) not null,
    createdAt timestamp,
    primary key (id)
);
