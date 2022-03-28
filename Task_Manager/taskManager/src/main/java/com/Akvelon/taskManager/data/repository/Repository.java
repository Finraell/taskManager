package com.Akvelon.taskManager.data.repository;

import com.Akvelon.taskManager.data.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface Repository<T extends Task> {
    CompletableFuture<T> save(T entity);

    CompletableFuture<Optional<T>> find(String id);

    CompletableFuture<List<T>> findAll();

    CompletableFuture<Optional<T>> remove(String id);
}
