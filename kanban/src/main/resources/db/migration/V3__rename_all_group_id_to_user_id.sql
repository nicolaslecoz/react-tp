alter table k_board
    rename column group_id to user_id;


alter table k_comment
    add column user_id text not null references k_user (id) default '';
