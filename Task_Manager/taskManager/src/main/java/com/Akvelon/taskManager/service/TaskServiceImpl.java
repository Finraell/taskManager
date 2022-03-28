package com.Akvelon.taskManager.service;

import com.Akvelon.taskManager.data.model.Task;
import com.Akvelon.taskManager.data.api.requests.TaskRequest;
import com.Akvelon.taskManager.data.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public CompletableFuture<Task> createTask(TaskRequest taskRequest) {
        return taskRepository.nextId()
                .thenApply(id -> new Task(id, taskRequest.getName(), taskRequest.getDescription(), taskRequest.isCompleted())).thenCompose(taskRepository::save);
    }

    @Override
    public CompletableFuture<Optional<Task>> getTask(String id) {
        return taskRepository.find(id);
    }

    @Override
    public CompletableFuture<Optional<Task>> updateTask(String id, TaskRequest taskRequest) {

        return taskRepository.find(id)
                .thenCompose(optionalTodoEntity ->
                        optionalTodoEntity
                                .map(todoEntity -> taskRepository.save(new Task(id, taskRequest.getName(), taskRequest.getDescription(), taskRequest.isCompleted())).thenApply(Optional::of))
                                .orElseGet(() -> CompletableFuture.completedFuture(Optional.empty())));
    }

    @Override
    public CompletableFuture<Optional<Task>> deleteTask(String id) {
        return taskRepository.remove(id);
    }
}
