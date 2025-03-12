package com.gr1tEnt.api;

import com.gr1tEnt.constant.TaskState;
import com.gr1tEnt.exception.FileOperationException;
import com.gr1tEnt.exception.InvalidTaskDataException;
import com.gr1tEnt.exception.InvalidTaskStateException;
import com.gr1tEnt.exception.TaskNotFoundException;
import com.gr1tEnt.model.*;

import java.util.List;

public interface TaskManager {
    void addTask(String title, String description, TaskState taskState) throws InvalidTaskStateException, InvalidTaskDataException;
    void addWorkTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException;
    void addPersonalTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException;

    List<Task> getAllTasks();
    List<WorkTask> getAllWorkTasks();
    List<PersonalTask> getAllPersonalTasks();

    void removeTask(int id) throws TaskNotFoundException;
    void removeAllTasks();

    void changeTaskStatus(int id, TaskState taskState);

    List<Task> getAllTaskByStatus(TaskState taskState);

    List<Task> searchTask(String keyword);
    Task getTaskById(int id) throws TaskNotFoundException;

    void addStudyTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException;
    List<StudyTask> getStudyTasks();

    List<Task> getTasksByStatus(TaskState taskState) throws InvalidTaskStateException;

    void saveTasksToFile(String path) throws FileOperationException;
    List<Task> readTasksFromFile(String path) throws FileOperationException;
}
