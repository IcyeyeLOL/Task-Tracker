package com.devtiro.tasks.controllers;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.dto.TaskDto;
import com.devtiro.tasks.mappers.TaskMapper;
import com.devtiro.tasks.repositories.TaskRepository;
import com.devtiro.tasks.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<Task> listTasks(@PathVariable("task_list_id") UUID tasklistID) {
        return (List<Task>) taskService.listTasks(tasklistID)
                .stream()
                .map(taskMapper::toDTO)
                ;
    }
    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id")UUID tasklistID, @RequestBody TaskDto taskDto) {
        Task createdtask = taskService.createTask(tasklistID, taskMapper.fromDTO(taskDto));
        return (TaskDto) taskMapper.toDTO(createdtask);
    }
    @GetMapping(path = "/{task_id}")
    public Optional <TaskDto> getTask(@PathVariable("task_id") UUID taskID,
                                      @PathVariable("task_list_id") UUID taskListId
    ) {
       return taskService.getTask(taskID, taskListId).map((java.util.function.Function<? super Task, ? extends TaskDto>) task -> (TaskDto) taskMapper.toDTO(task));
    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_id") UUID taskID,@PathVariable("task_list_id") UUID taskListID,@RequestBody TaskDto taskDto) {
        Task updatedTask = taskService.updateTask(taskID, taskListID, taskMapper.fromDTO(taskDto));
        return  (TaskDto) taskMapper.toDTO(updatedTask);
    }
}
