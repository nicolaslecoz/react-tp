create table k_group
(
    id       text
        primary key not null,
    name     text   not null unique,
    password text   not null
);

create table k_board
(
    id         text
        primary key not null,
    name       text not null,
    group_id   text not null references k_group (id) on delete cascade,
    version    int  not null,
    created_at date not null,
    updated_at date
);

create table k_column
(
    id         text
        primary key not null,
    board_id   text not null references k_board (id) on delete cascade,
    name       text not null,
    rank       int  not null,
    version    int  not null,
    created_at date not null,
    updated_at date
);

create table k_card
(
    id         text
        primary key not null,
    title      text not null,
    body       text,
    column_id  text not null references k_column (id) on delete cascade,
    rank       int  not null,
    version    int  not null,
    created_at date not null,
    updated_at date
);

create table k_comment
(
    id         text
        primary key not null,
    content    text not null,
    card_id    text not null references k_comment (id) on delete cascade,
    version    int  not null,
    created_at date not null,
    updated_at date
);
