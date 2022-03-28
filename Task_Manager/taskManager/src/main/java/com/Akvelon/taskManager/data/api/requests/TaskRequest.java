package com.Akvelon.taskManager.data.api.requests;

import lombok.Data;

@Data
public class TaskRequest {
    private String name;
    private String description;
    private boolean isCompleted;
}
