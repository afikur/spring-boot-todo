create table if not exists todo (
    id integer not null,
    name varchar (255) not null,
    description varchar(255) not null,
    createdAt timestamp,
    primary key (id)
);

create table if not exists employee(
    id integer not null,
    name varchar (255) not null,
    primary key (id)
);
