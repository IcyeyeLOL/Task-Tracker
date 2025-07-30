package com.devtiro.tasks.mappers;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.dto.TaskDto;

import java.util.function.Function;

public interface TaskMapper<R> extends Function<Task, R> {

    Task fromDTO (TaskDto taskDto);

   TaskDto from(Task Task);


    Object toDTO(Task task);
}
