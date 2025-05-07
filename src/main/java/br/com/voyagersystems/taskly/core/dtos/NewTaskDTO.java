package br.com.voyagersystems.taskly.core.dtos;

import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;

public record NewTaskDTO(
        String title,
        String description,
        TaskPriority priority,
        TaskGroup taskGroup,
        boolean completed
) {
}
