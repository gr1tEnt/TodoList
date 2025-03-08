package com.gr1tEnt;

import com.gr1tEnt.api.TaskManager;
import com.gr1tEnt.constant.TaskState;
import com.gr1tEnt.exception.InvalidTaskDataException;
import com.gr1tEnt.exception.InvalidTaskStateException;

import com.gr1tEnt.exception.TaskNotFoundException;
import com.gr1tEnt.service.TaskManagerImpl;

public class Main {
    public static void main(String[] args) throws InvalidTaskDataException, InvalidTaskStateException, TaskNotFoundException {
        TaskManager taskManager = new TaskManagerImpl();

        //It's temporary
        taskManager.addTask("Common task", "adding common task", TaskState.COMPLETED);
        taskManager.addWorkTask("Work task", "adding work task", TaskState.IN_PROGRESS);
        taskManager.addPersonalTask("Personal task", "adding personal task", TaskState.IN_PROGRESS);
        taskManager.addStudyTask("Study task", "adding study task", TaskState.TODO);
    }
}