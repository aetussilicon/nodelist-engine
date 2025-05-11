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
}
