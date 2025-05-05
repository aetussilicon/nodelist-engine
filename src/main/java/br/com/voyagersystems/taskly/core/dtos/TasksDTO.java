package br.com.voyagersystems.taskly.core.dtos;

import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;

import java.sql.Time;
import java.sql.Timestamp;

public record TasksDTO(
        Long taskId,
        String title,
        String description,
        String priority,
        TaskGroup taskGroup,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
