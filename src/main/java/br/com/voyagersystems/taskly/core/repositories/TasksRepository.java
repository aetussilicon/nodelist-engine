package br.com.voyagersystems.taskly.core.repositories;

import br.com.voyagersystems.taskly.core.entities.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, Long> {
}
