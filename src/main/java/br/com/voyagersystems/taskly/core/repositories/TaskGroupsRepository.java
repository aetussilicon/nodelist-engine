package br.com.voyagersystems.taskly.core.repositories;

import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskGroupsRepository extends JpaRepository<TaskGroup, Long> {
    Optional<TaskGroup> findByTaskGroupId(long taskGroupId);

    @Query("select g from TaskGroup g left join fetch g.tasks")
    List<TaskGroup> findAllWithTasks();

    interface TaskGroupNameProjection {
        Long getKey();
        String getValue();
    }

    @Query(value = "SELECT task_group_id AS key, task_group_name AS value FROM task_groups", nativeQuery = true)
    List<TaskGroupNameProjection> findAllTaskGroupNamesAndTaskGroupId();
}
