package br.com.voyagersystems.taskly.core.mappers;

import br.com.voyagersystems.taskly.core.dtos.NewTaskGroupDTO;
import br.com.voyagersystems.taskly.core.dtos.TaskGroupDTO;
import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TasksMapper.class)
public interface TaskGroupMapper {

    TaskGroup toEntity(NewTaskGroupDTO newTaskGroupDTO);

    TaskGroupDTO toDto(TaskGroup taskGroup);

    List<TaskGroupDTO> toDto(List<TaskGroup> groups);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TaskGroup partialUpdate(NewTaskGroupDTO updateDTO, @MappingTarget TaskGroup group);
}
