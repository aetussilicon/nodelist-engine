package br.com.voyagersystems.taskly.core.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

public record TaskGroupDTO(
        Long taskGroupId,
        String taskGroupName,
        List<TasksDTO> tasks,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        Timestamp createdAt,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
        Timestamp updatedAt
) {
}
