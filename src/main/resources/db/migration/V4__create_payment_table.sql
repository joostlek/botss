create table payment
(
    id uuid not null
        constraint payment_pkey
            primary key,
    amount double precision,
    created_at timestamp,
    description varchar(255),
    checkout_url varchar(255),
    expires_at timestamp,
    external_id varchar(255),
    payment_provider varchar(255),
    redirect_url varchar(255),
    webhook_url varchar(255),
    status integer,
    updated_at timestamp,
    participant_id uuid
        constraint fk_payment_participant
            references participant
);
