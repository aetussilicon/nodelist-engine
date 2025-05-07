alter table tasks add column completed_at timestamp;

alter table tasks add column completed boolean default false;

update tasks set completed = false where completed is null;

alter table tasks alter column completed set not null;