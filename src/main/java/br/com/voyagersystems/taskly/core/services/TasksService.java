package br.com.voyagersystems.taskly.core.services;

import br.com.voyagersystems.taskly.core.dtos.NewTaskDTO;
import br.com.voyagersystems.taskly.core.dtos.TasksDTO;
import br.com.voyagersystems.taskly.core.entities.TaskGroup;
import br.com.voyagersystems.taskly.core.entities.Tasks;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;
import br.com.voyagersystems.taskly.core.exceptions.DefaultGroupNotFindException;
import br.com.voyagersystems.taskly.core.mappers.TasksMapper;
import br.com.voyagersystems.taskly.core.repositories.TaskGroupsRepository;
import br.com.voyagersystems.taskly.core.repositories.TasksRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
         Tasks task =  mapper.toEntity(createDTO);
         if (task.getTaskGroup() == null) {
             task.setTaskGroup(defaultGroup);
         }

         if (task.getPriority() == null) {
             task.setPriority(TaskPriority.P5);
         }

         task = repo.save(task);
         return mapper.toDTO(task);
     }
}
