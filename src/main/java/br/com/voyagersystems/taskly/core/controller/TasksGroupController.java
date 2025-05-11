package br.com.voyagersystems.taskly.core.controller;

import br.com.voyagersystems.taskly.core.dtos.NewTaskGroupDTO;
import br.com.voyagersystems.taskly.core.dtos.TaskGroupDTO;
import br.com.voyagersystems.taskly.core.services.TaskGroupsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TasksGroupController {

    private final TaskGroupsService service;

    @PostMapping("create")
    public ResponseEntity<TaskGroupDTO> create(@RequestBody NewTaskGroupDTO newTaskGroupDTO) {
        return new ResponseEntity<>(service.create(newTaskGroupDTO), HttpStatus.CREATED);
    }

    @PatchMapping("update/{taskGroupId}")
    public ResponseEntity<TaskGroupDTO> update(@RequestBody NewTaskGroupDTO newTaskGroupDTO, @PathVariable Long taskGroupId) {
        return new ResponseEntity<>(service.update(newTaskGroupDTO, taskGroupId), HttpStatus.OK);
    }

    @GetMapping("{taskGroupId}")
    public ResponseEntity<TaskGroupDTO> list(@PathVariable Long taskGroupId) {
        return new ResponseEntity<>(service.get(taskGroupId), HttpStatus.OK);
    }

    @GetMapping("names")
    public ResponseEntity<Map<Long, String>> getTaskGroupNames() {
        return new ResponseEntity<>(service.getTaskGroupNames(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskGroupDTO>> list() {
        return new ResponseEntity<>(service.ListGroupsAndTasks(), HttpStatus.OK);
    }

    @DeleteMapping("{taskGroupId}")
    public void delete(@PathVariable Long taskGroupId) {
        service.delete(taskGroupId);
    }
}