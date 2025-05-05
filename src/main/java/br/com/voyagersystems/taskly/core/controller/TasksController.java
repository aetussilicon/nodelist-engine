package br.com.voyagersystems.taskly.core.controller;

import br.com.voyagersystems.taskly.core.dtos.NewTaskDTO;
import br.com.voyagersystems.taskly.core.dtos.TasksDTO;
import br.com.voyagersystems.taskly.core.services.TasksService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TasksController {
    private final TasksService service;

    @PostMapping("create")
    public ResponseEntity<TasksDTO> create(@RequestBody @Valid NewTaskDTO newTaskDTO) {
        return new ResponseEntity<>(service.create(newTaskDTO), HttpStatus.CREATED);
    }

}
