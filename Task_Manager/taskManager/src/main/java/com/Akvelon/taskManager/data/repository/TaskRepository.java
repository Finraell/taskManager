package com.Akvelon.taskManager.data.repository;

import com.Akvelon.taskManager.data.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class TaskRepository extends FaunaRepository<Task> {
    public TaskRepository() {
        super(Task.class, "todos", "tasks");
    }

    @Override
    public CompletableFuture<List<Task>> findAll() {
        return null;
    }
    //-- Custom repository operations specific to the TodoEntity will go below --//
}
