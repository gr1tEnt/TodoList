package com.gr1tEnt.service;

import com.gr1tEnt.api.TaskManager;
import com.gr1tEnt.constant.TaskState;
import com.gr1tEnt.exception.*;
import com.gr1tEnt.model.PersonalTask;
import com.gr1tEnt.model.StudyTask;
import com.gr1tEnt.model.Task;
import com.gr1tEnt.model.WorkTask;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManagerImpl implements TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    public static int taskId = 1;

    @Override
    public void addTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        checkInvalidTaskData(title, taskState);
        Task task = new Task.Builder()
                .setId(taskId)
                .setTitle(title)
                .setDescription(description)
                .setTaskState(taskState)
                .build();
        tasks.add(task);
        taskId++;
    }

    @Override
    public void addWorkTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        checkInvalidTaskData(title, taskState);
        Task workTask = new WorkTask.Builder()
                .setId(taskId)
                .setTitle(title)
                .setDescription(description)
                .setTaskState(taskState)
                .build();
        tasks.add(workTask);
        taskId++;
    }

    @Override
    public void addPersonalTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        checkInvalidTaskData(title, taskState);
        Task personalTask = new PersonalTask.Builder()
                .setId(taskId)
                .setTitle(title)
                .setDescription(description)
                .setTaskState(taskState)
                .build();
        tasks.add(personalTask);
        taskId++;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public List<WorkTask> getAllWorkTasks() {
        return tasks.stream()
                .filter(task -> task instanceof WorkTask)
                .map(task -> (WorkTask) task)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersonalTask> getAllPersonalTasks() {
        return tasks.stream()
                .filter(task -> task instanceof PersonalTask)
                .map(task -> (PersonalTask) task)
                .collect(Collectors.toList());
    }

    @Override
    public void removeTask(int id) throws TaskNotFoundException {
        if (tasks.isEmpty()) {
            throw new EmptyTaskListException("Task list is empty.");
        }
        Optional<Task> taskToRemove = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
        if (taskToRemove.isPresent()) {
            tasks.remove(taskToRemove.get());
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found.");
        }
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void changeTaskStatus(int id, TaskState taskState) {
        tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        task -> task.setTaskState(taskState),
                        () -> {
                            try {
                                throw new TaskNotFoundException("Task with id " + id + " not found.");
                            } catch (TaskNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }


    @Override
    public List<Task> getAllTaskByStatus(TaskState taskState) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskState() == taskState) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    @Override
    public List<Task> searchTask(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(keyword.toLowerCase()) ||
                        task.getDescription().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Task getTaskById(int id) throws TaskNotFoundException {
        return tasks.stream()
                .filter(task -> task.getId() == id).findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found."));
    }

    @Override
    public void addStudyTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        checkInvalidTaskData(title, taskState);
        Task studyTask = new StudyTask.Builder()
                .setId(taskId)
                .setTitle(title)
                .setDescription(description)
                .setTaskState(taskState)
                .build();
        tasks.add(studyTask);
        taskId++;
    }

    @Override
    public List<StudyTask> getStudyTasks() {
        return tasks.stream()
                .filter(task -> task instanceof StudyTask)
                .map(task -> (StudyTask) task)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksByStatus(TaskState taskState) {
        return tasks.stream()
                .filter(task -> task.getTaskState().equals(taskState))
                .collect(Collectors.toList());
    }

    @Override
    public void saveTasksToFile(String path) throws FileOperationException {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(tasks);
        } catch (IOException e) {
            throw new FileOperationException(e);
        }
    }

    @Override
    public List<Task> readTasksFromFile(String path) throws FileOperationException {
        List<Task> taskList;
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            taskList = (List<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new FileOperationException(e);
        }
        tasks.addAll(taskList);
        return tasks;
    }

    private void checkInvalidTaskData(String title, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        if (title == null || title.isEmpty()) {
            throw new InvalidTaskDataException("Task must have a title.");
        }
        if (taskState == null) {
            throw new InvalidTaskStateException("Task state cannot be null.");
        }
    }
}
