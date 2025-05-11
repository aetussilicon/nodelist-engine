package br.com.voyagersystems.taskly.core.services;

import br.com.voyagersystems.taskly.core.dtos.NewTaskGroupDTO;
import br.com.voyagersystems.taskly.core.dtos.TaskGroupDTO;
import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import br.com.voyagersystems.taskly.core.mappers.TaskGroupMapper;
import br.com.voyagersystems.taskly.core.repositories.TaskGroupsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskGroupsService {

    private final TaskGroupsRepository repo;
    private final TaskGroupMapper mapper;

    public TaskGroupDTO create(NewTaskGroupDTO newTaskGroupDTO) {
        log.info("Creating new task group: {}", newTaskGroupDTO);
        TaskGroup taskGroup = mapper.toEntity(newTaskGroupDTO);
        taskGroup = repo.save(taskGroup);
        log.info("Task group created: {}", taskGroup);
        return mapper.toDto(taskGroup);
    }

    @Transactional
    public TaskGroupDTO update(NewTaskGroupDTO updateDTO, Long taskGroupId) {
        log.info("Updating task group with ID {}: {}", taskGroupId, updateDTO);
        TaskGroup group = getGroup(taskGroupId);
        group = mapper.partialUpdate(updateDTO, group);
        group = repo.save(group);
        log.info("Task group updated: {}", group);
        return mapper.toDto(group);
    }

    public TaskGroupDTO get(Long taskGroupId) {
        log.info("Fetching task group with ID {}", taskGroupId);
        TaskGroup group = getGroup(taskGroupId);
        log.info("Task group found: {}", group);
        return mapper.toDto(group);
    }

    public Map<Long, String> getTaskGroupNames() {
        log.info("Fetching all task group names");
        return repo.findAllTaskGroupNamesAndTaskGroupId().stream()
                .collect(Collectors.toMap(
                    TaskGroupsRepository.TaskGroupNameProjection::getKey,
                    TaskGroupsRepository.TaskGroupNameProjection::getValue
                ));
    }

    public List<TaskGroupDTO> list() {
        log.info("Fetching all task groups");
        return mapper.toDto(repo.findAll());
    }


    @Transactional
    public void delete(Long taskGroupId) {
        log.info("Deleting task group with ID {}", taskGroupId);
        TaskGroup group = getGroup(taskGroupId);

        if (group.getTaskGroupId().equals(1L)) {
            throw new RuntimeException("Cannot delete the default task group");
        }

        repo.delete(group);
        log.info("Task group deleted: {}", group);
    }

    protected TaskGroup getGroup(Long taskGroupId) {
        return repo.findById(taskGroupId)
                .orElseThrow(() -> new RuntimeException("Task group not found"));
    }
}
