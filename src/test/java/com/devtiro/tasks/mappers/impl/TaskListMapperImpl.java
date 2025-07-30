package com.devtiro.tasks.mappers.impl;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.TaskStatus;
import com.devtiro.tasks.domain.entities.dto.TaskListDto;
import com.devtiro.tasks.mappers.TaskListMapper;
import com.devtiro.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    // Fixed: Removed circular dependency on TaskListMapperImpl
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskList fromDTO(TaskListDto taskListDto) {
        return null;
    }

    @Override
    public TaskListDto DTO(TaskList taskList) {
        return null;
    }

    @Override
    public TaskListDto toDTO(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDTO)
                                .collect(Collectors.toList()))
                        .orElse(null)

        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks || tasks.isEmpty()) {
            return null;
        }

        long closedTaskCount = tasks.stream()
                .filter(task ->
                        false)
                .count();

        return (double) closedTaskCount / tasks.size() * 100.0;
    }
}