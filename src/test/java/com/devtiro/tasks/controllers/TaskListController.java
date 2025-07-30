package com.devtiro.tasks.controllers;

import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.dto.TaskListDto;
import com.devtiro.tasks.mappers.TaskListMapper;
import com.devtiro.tasks.services.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        List<TaskList> taskLists = taskListService.findAll();
        return taskLists.stream()
                .map(taskListMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDTO(taskListDto)
        );

        return taskListMapper.toDTO(createdTaskList);
    }

    @GetMapping(path = "/{task_list_id}")
    public ResponseEntity<TaskListDto> getTaskListById(@PathVariable("task_list_id") UUID taskListId) {
        Optional<TaskList> taskListOptional = taskListService.getTaskList(taskListId);

        if (taskListOptional.isPresent()) {
            TaskListDto taskListDto = taskListMapper.toDTO(taskListOptional.get());
            return ResponseEntity.ok(taskListDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{task_list_id}")
    public ResponseEntity<TaskListDto> updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto
    ) {
        Optional<TaskList> updatedTaskList = Optional.ofNullable(taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDTO(taskListDto)
        ));

        if (updatedTaskList.isPresent()) {
            TaskListDto responseDto = taskListMapper.toDTO(updatedTaskList.get());
            return ResponseEntity.ok(responseDto);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}