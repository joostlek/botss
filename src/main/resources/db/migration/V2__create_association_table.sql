create table association
(
    slug varchar(255) not null
        constraint association_pkey
            primary key,
    logo_url varchar(255),
    name varchar(255)
);