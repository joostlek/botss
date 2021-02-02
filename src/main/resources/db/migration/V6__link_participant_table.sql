alter table participant
    add team_id uuid
        constraint fk_participant_team
            references team;

