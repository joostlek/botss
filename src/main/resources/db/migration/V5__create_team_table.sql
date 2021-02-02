create table team
(
    id uuid not null
        constraint team_pkey
            primary key,
    join_code varchar(255)
        constraint uk_join_code
            unique,
    name varchar(255)
        constraint uk_name
            unique,
    captain_id uuid
        constraint fk_captain
            references participant
);
