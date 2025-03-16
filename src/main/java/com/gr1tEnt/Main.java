package com.gr1tEnt;

import com.gr1tEnt.api.TaskManager;
import com.gr1tEnt.exception.FileOperationException;
import com.gr1tEnt.exception.InvalidTaskDataException;
import com.gr1tEnt.exception.InvalidTaskStateException;

import com.gr1tEnt.exception.TaskNotFoundException;
import com.gr1tEnt.service.TaskManagerImpl;


public class Main {
    public static void main(String[] args) throws InvalidTaskDataException, InvalidTaskStateException, TaskNotFoundException, FileOperationException {
        TaskManager taskManager = new TaskManagerImpl();
    }
}