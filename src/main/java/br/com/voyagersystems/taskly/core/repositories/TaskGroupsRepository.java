package br.com.voyagersystems.taskly.core.repositories;

import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskGroupsRepository extends JpaRepository<TaskGroup, Long> {
    Optional<TaskGroup> findByTaskGroupId(long taskGroupId);
}
