package com.devtiro.tasks.services;

import com.devtiro.tasks.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<Task> listTasks(UUID tasklistID);
    Task createTask(UUID tasklistID, Task task);
    Optional<Task> getTask(UUID tasklistID, UUID taskID);
    Task updateTask(UUID tasklistID, UUID taskID, Task task);
    void deleteTask(UUID tasklistID, UUID taskID);
}
