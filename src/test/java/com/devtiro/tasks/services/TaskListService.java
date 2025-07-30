package com.devtiro.tasks.services;

import com.devtiro.tasks.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskList> listTaskLists();

    TaskList createTaskList(TaskList taskList);

    List<TaskList> findAll();

    Optional<TaskList> getTaskList(UUID id);
    TaskList updateTaskList(UUID tasklistID, TaskList taskList);
    void deleteTaskList(UUID tasklistID);
}
