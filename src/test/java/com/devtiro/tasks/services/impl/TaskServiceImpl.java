package com.devtiro.tasks.services.impl;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.TaskList;
import com.devtiro.tasks.domain.entities.TaskPriority;
import com.devtiro.tasks.domain.entities.TaskStatus;
import com.devtiro.tasks.repositories.TaskListRepository;
import com.devtiro.tasks.repositories.TaskRepository;
import com.devtiro.tasks.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<Task> listTasks(UUID tasklistID) {
        return taskRepository.findByTaskList(tasklistID);
    }

    @Override
    public Task createTask(UUID tasklistID, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task id can't be null!");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title can't be null!");
        }
        Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);

        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList tasklist =taskRepository.findById(tasklistID)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));
        LocalDateTime now = LocalDateTime.now();

        Task taskToSave  = new Task(

                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                tasklist,
                now,
                now

        );
        return taskToSave;




    }

    @Override
    public Optional<Task> getTask(UUID tasklistID, UUID taskID) {
        return taskRepository.findByTaskListIdAndId(tasklistID, taskID);
    }


    @Override
    public Task updateTask(UUID tasklistID, UUID taskID, Task task) {
        if (null == task.getId()) {
            throw new IllegalArgumentException("Task id can't be null!");
        }
        if (!Objects.equals(taskID, task.getId())) {
            throw new IllegalArgumentException("Task id not match!");
        }
        if (null == task.getPriority()) {
            throw new IllegalArgumentException("Task priority can't be null!");
        }
        if (null == task.getStatus()) {
            throw new IllegalArgumentException("Task status can't be null!");
        }

        Task existingTask = taskRepository.findByTaskListIdAndId(tasklistID, taskID)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return null;
    }

    @Override
    public void deleteTask(UUID tasklistID, UUID taskID) {
        taskRepository.deleteById(taskID);

    }

}
