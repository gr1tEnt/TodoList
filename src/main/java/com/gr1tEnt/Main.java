package com.gr1tEnt;

import com.gr1tEnt.api.TaskManager;
import com.gr1tEnt.constant.TaskState;
import com.gr1tEnt.exception.FileOperationException;
import com.gr1tEnt.exception.InvalidTaskDataException;
import com.gr1tEnt.exception.InvalidTaskStateException;

import com.gr1tEnt.exception.TaskNotFoundException;
import com.gr1tEnt.model.Task;
import com.gr1tEnt.service.TaskManagerImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidTaskDataException, InvalidTaskStateException, TaskNotFoundException, FileOperationException {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addWorkTask("LINKOLN", "PREZIDENT MIRA!", TaskState.COMPLETED);
        taskManager.saveTasksToFile("src/main/java/com/gr1tEnt/tasks.ser");
        List<Task> tasks = taskManager.readTasksFromFile("src/main/java/com/gr1tEnt/tasks.ser");
        tasks.forEach(System.out::println);
    }
}