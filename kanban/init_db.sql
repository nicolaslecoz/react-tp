create user kanban with password 'kanban';
create database kanban;
grant create on database kanban to kanban;
alter user kanban with superuser;
\connect kanban;
create schema kanban authorization kanban;
grant all privileges on database kanban to kanban;
