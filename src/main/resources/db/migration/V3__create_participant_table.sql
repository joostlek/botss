create table participant
(
    id uuid not null
        constraint participant_pkey
            primary key,
    email_address varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone_number varchar(255),
    association_slug varchar(255)
        constraint fk_association_participant
            references association
);