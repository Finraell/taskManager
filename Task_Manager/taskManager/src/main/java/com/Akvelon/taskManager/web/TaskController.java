package com.Akvelon.taskManager.web;

import com.Akvelon.taskManager.data.api.requests.TaskRequest;
import com.Akvelon.taskManager.service.TaskService;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request format"),
        @io.swagger.annotations.ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorized"),
        @io.swagger.annotations.ApiResponse(code = 500, message = "The server is down. Please bear with us."),
})
public class TaskController {
    TaskService taskService;

    @PostMapping("/create")
    public CompletableFuture<?> createTask(@RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest)
                .thenApply(todoEntity -> new ResponseEntity<>(todoEntity, HttpStatus.CREATED));
    }


    @GetMapping("/get/{id}")
    public CompletableFuture<?> getTask(@PathVariable("id") String id) {
        return taskService.getTask(id)
                .<ResponseEntity>thenApply(optionalTodoEntity ->
                        optionalTodoEntity
                                .map(todoEntity -> new ResponseEntity<>(todoEntity, HttpStatus.OK))
                                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND))
                );
    }


    @PutMapping("/update/{id}")
    public CompletableFuture<?> updateTask(@PathVariable("id") String id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(id, taskRequest)
                .<ResponseEntity>thenApply(optionalTodoEntity ->
                        optionalTodoEntity
                                .map(todoEntity -> new ResponseEntity<>(todoEntity, HttpStatus.OK))
                                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
                                )
                );
    }

    @DeleteMapping(value = "/delete/{id}")
    public CompletableFuture<?> deleteTask(@PathVariable("id") String id) {
        return taskService.deleteTask(id)
                .<ResponseEntity>thenApply(optionalTodoEntity ->
                        optionalTodoEntity
                                .map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
                                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)
                                )
                );
    }
}
