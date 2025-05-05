package br.com.voyagersystems.taskly.core.repositories;

import br.com.voyagersystems.taskly.core.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findTasksByTaskId(Long taskId);
}
