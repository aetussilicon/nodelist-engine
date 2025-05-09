alter sequence task_groups_seq restart with 3;

insert into task_groups(task_group_id, task_group_name, created_at, updated_at)
values (2, 'Concluído', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);