package br.com.voyagersystems.taskly.core.services;

import br.com.voyagersystems.taskly.core.dtos.NewTaskDTO;
import br.com.voyagersystems.taskly.core.dtos.TasksDTO;
import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import br.com.voyagersystems.taskly.core.entities.Tasks;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;
import br.com.voyagersystems.taskly.core.exceptions.DefaultGroupNotFindException;
import br.com.voyagersystems.taskly.core.exceptions.TaskNotFoundException;
import br.com.voyagersystems.taskly.core.mappers.TasksMapper;
import br.com.voyagersystems.taskly.core.repositories.TaskGroupsRepository;
import br.com.voyagersystems.taskly.core.repositories.TasksRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TasksService {

    private final TasksRepository repo;
    private final TasksMapper mapper;
    private final TaskGroupsRepository groupsRepository;

    private TaskGroup defaultGroup;

    @PostConstruct
    public void init() {
        this.defaultGroup = groupsRepository.findByTaskGroupId(1L).orElseThrow(DefaultGroupNotFindException::new);
    }

    public TasksDTO create(NewTaskDTO createDTO) {
        Tasks task = mapper.toEntity(createDTO);
        if (task.getTaskGroup() == null) {
            task.setTaskGroup(defaultGroup);
        }

        if (task.getPriority() == null) {
            task.setPriority(TaskPriority.P5);
        }

        task = repo.save(task);
        log.info("Task created");
        return mapper.toDTO(task);
    }

    @Transactional
    public TasksDTO update(NewTaskDTO updateDTO, Long taskId) {
        Tasks task = repo.findTasksByTaskId(taskId).orElseThrow(TaskNotFoundException::new);

        task = repo.save(mapper.partialUpdate(updateDTO, task));

        log.info("Task with id {} updated", taskId);

        return mapper.toDTO(task);
    }

    @Transactional
    public void updatePriority(TaskPriority priority, Long taskId) {
        Tasks task = repo.findTasksByTaskId(taskId).orElseThrow(TaskNotFoundException::new);
        task.setPriority(priority);
        repo.save(task);
        log.info("Task with id {} updated", taskId);
    }

    @Transactional
    public TasksDTO setComplete(Long taskId) {
        Tasks task = repo.findTasksByTaskId(taskId).orElseThrow(TaskNotFoundException::new);
        task.setCompleted(true);
        task.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        task = repo.save(task);
        log.info("Task with id {} set as completed", taskId);
        return mapper.toDTO(task);
    }

    public TasksDTO list(Long taskId) {
        return mapper.toDTO(repo.findTasksByTaskId(taskId).orElseThrow(TaskNotFoundException::new));
    }

    public List<TasksDTO> list() {
        return mapper.toDTO(repo.findAll());
    }

    public void delete (Long taskId) {
        Tasks task = repo.findTasksByTaskId(taskId).orElseThrow(TaskNotFoundException::new);
        repo.delete(task);
        log.info("Deleted task with id {}", taskId);
    }
}
