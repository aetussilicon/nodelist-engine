DO $$
DECLARE
max_id bigint;
BEGIN
SELECT MAX(task_group_id) INTO max_id FROM task_groups;
IF max_id IS NOT NULL THEN
    EXECUTE format('ALTER SEQUENCE task_groups_seq RESTART WITH %s;', max_id + 2);
END IF;
END $$;
