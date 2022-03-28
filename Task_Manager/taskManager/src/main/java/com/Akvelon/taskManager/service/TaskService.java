package com.Akvelon.taskManager.service;

import com.Akvelon.taskManager.data.model.Task;
import com.Akvelon.taskManager.data.api.requests.TaskRequest;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface TaskService {
    CompletableFuture<Task> createTask(TaskRequest taskRequest);

    CompletableFuture<Optional<Task>> updateTask(String id, TaskRequest taskRequest);

    CompletableFuture<Optional<Task>> deleteTask(String id);

    CompletableFuture<Optional<Task>> getTask(String id);
}
