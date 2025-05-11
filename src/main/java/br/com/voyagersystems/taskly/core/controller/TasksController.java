package br.com.voyagersystems.taskly.core.controller;

import br.com.voyagersystems.taskly.core.dtos.NewTaskDTO;
import br.com.voyagersystems.taskly.core.dtos.TasksDTO;
import br.com.voyagersystems.taskly.core.enums.TaskPriority;
import br.com.voyagersystems.taskly.core.services.TasksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TasksController {
    private final TasksService service;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("create")
    public ResponseEntity<TasksDTO> create(@RequestBody @Valid NewTaskDTO newTaskDTO) {
        return new ResponseEntity<>(service.create(newTaskDTO), HttpStatus.CREATED);
    }

    @PatchMapping("update/{taskId}")
    public ResponseEntity<TasksDTO> update(@RequestBody @Valid NewTaskDTO updateDTO, @PathVariable Long taskId) {
        TasksDTO tasksDTO = service.update(updateDTO, taskId);
        messagingTemplate.convertAndSend("/topic/tasks", tasksDTO);
        return new ResponseEntity<>(tasksDTO, HttpStatus.OK);
    }

    @PatchMapping("priority/{taskId}")
    public ResponseEntity<Void> changePriority(@PathVariable Long taskId, @RequestParam TaskPriority priority) {
        service.updatePriority(priority, taskId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("complete/{taskId}")
    public ResponseEntity<TasksDTO> completeTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(service.setComplete(taskId), HttpStatus.OK);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TasksDTO> list(@PathVariable Long taskId) {
        return new ResponseEntity<>(service.list(taskId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TasksDTO>> listAll() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{taskId}")
    public void delete(@PathVariable Long taskId) {
        service.delete(taskId);
    }
}
