package br.com.voyagersystems.taskly.core.mappers;

import br.com.voyagersystems.taskly.core.dtos.NewTaskDTO;
import br.com.voyagersystems.taskly.core.dtos.TasksDTO;
import br.com.voyagersystems.taskly.core.entities.Tasks;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TasksMapper {

    @Mapping(target = "taskGroup", ignore = true)
    Tasks toEntity(NewTaskDTO newTaskDTO);
    TasksDTO toDTO(Tasks task);

    @Mapping(target = "taskGroup", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tasks partialUpdate(NewTaskDTO updateDTO, @MappingTarget Tasks tasks);

    List<TasksDTO> toDTO(List<Tasks> tasksList);

    default String mapPriority(TaskPriority priority) {
        return priority.getPriority();
    }
}
