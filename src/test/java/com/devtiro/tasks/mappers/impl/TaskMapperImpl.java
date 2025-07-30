package com.devtiro.tasks.mappers.impl;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.dto.TaskDto;
import com.devtiro.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDTO(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }



    @Override
    public TaskDto from(Task Task) {
        return null;
    }

    @Override
    public TaskDto toDTO(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                null


        );


    }

    @Override
    public Object apply(Object o) {
        return null;
    }
}



