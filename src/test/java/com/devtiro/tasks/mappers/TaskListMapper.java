package com.devtiro.tasks.mappers;

import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.dto.TaskDto;
import com.devtiro.tasks.domain.entities.dto.TaskListDto;

public interface TaskListMapper {
    TaskList fromDTO(TaskListDto taskListDto);

    TaskListDto DTO(TaskList taskList);

    TaskListDto toDTO(TaskList taskList);
}
