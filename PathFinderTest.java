create table if not exists taptiles_users (
    id bigserial primary key,
    username varchar(30) not null,
    email varchar(120) not null,
    password varchar(255) not null,
    created_at timestamp not null,
    total_score integer not null default 0,
    games_played integer not null default 0,
    best_score integer not null default 0,
    level_progress integer not null default 0,
    last_played_at timestamp,
    role varchar(30) not null default 'ROLE_PLAYER',
    constraint uk_taptiles_users_username unique (username),
    constraint uk_taptiles_users_email unique (email)
);

create index if not exists idx_taptiles_users_total_score
    on taptiles_users (total_score desc);

create index if not exists idx_taptiles_users_best_score
    on taptiles_users (best_score desc);

alter table score
    add column if not exists user_id bigint;

alter table comment
    add column if not exists user_id bigint;

create index if not exists idx_score_user_id
    on score (user_id);

create index if not exists idx_comment_user_id
    on comment (user_id);

do $$
begin
    if not exists (
        select 1
        from information_schema.table_constraints
        where constraint_name = 'fk_score_taptiles_user'
    ) then
        alter table score
            add constraint fk_score_taptiles_user
            foreign key (user_id) references taptiles_users (id);
    end if;

    if not exists (
        select 1
        from information_schema.table_constraints
        where constraint_name = 'fk_comment_taptiles_user'
    ) then
        alter table comment
            add constraint fk_comment_taptiles_user
            foreign key (user_id) references taptiles_users (id);
    end if;
end $$;
