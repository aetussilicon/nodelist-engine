create sequence task_groups_seq
    start with 2
    increment by 1;

create table task_groups
(
    task_group_id   serial primary key,
    task_group_name varchar(20) not null,
    created_at      timestamp   not null,
    updated_at      timestamp   not null
);

insert into task_groups(task_group_id, task_group_name, created_at, updated_at)
values (1, 'No group', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

create table tasks
(
    task_id     serial primary key,
    title       varchar(60) not null,
    description text,
    priority    varchar(11) default 'No priority',
    task_group  bigint      default 1,
    created_at  timestamp   not null,
    updated_at  timestamp   not null,
    foreign key (task_group) references task_groups (task_group_id)
);