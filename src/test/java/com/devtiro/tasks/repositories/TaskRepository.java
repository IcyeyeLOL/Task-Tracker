package com.devtiro.tasks.repositories;

import com.devtiro.tasks.domain.entities.Task;
import com.devtiro.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskList, UUID> {
    List<Task> findByTaskList(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId,  UUID id);
    void deleteByTaskListId(UUID taskListId, UUID id);
}
