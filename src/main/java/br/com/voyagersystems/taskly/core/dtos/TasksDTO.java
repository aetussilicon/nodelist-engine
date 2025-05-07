package br.com.voyagersystems.taskly.core.dtos;

import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.sql.Timestamp;

public record TasksDTO(
        Long taskId,
        String title,
        String description,
        String priority,
        TaskGroup taskGroup,
        boolean completed,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        Timestamp completedAt,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        Timestamp createdAt,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        Timestamp updatedAt
) {
}
