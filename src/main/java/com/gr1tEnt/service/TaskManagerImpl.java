package com.gr1tEnt.service;

import com.gr1tEnt.api.TaskManager;
import com.gr1tEnt.constant.TaskState;
import com.gr1tEnt.exception.EmptyTaskListException;
import com.gr1tEnt.exception.InvalidTaskDataException;
import com.gr1tEnt.exception.InvalidTaskStateException;
import com.gr1tEnt.exception.TaskNotFoundException;
import com.gr1tEnt.model.PersonalTask;
import com.gr1tEnt.model.StudyTask;
import com.gr1tEnt.model.Task;
import com.gr1tEnt.model.WorkTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        tasks.add(new WorkTask(taskId, title, description, taskState));
        taskId++;
    }

    @Override
    public void addPersonalTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        checkInvalidTaskData(title, taskState);
        tasks.add(new PersonalTask(taskId, title, description, taskState));
        taskId++;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public List<WorkTask> getAllWorkTasks() {
        List<WorkTask> workTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof WorkTask) {
                workTasks.add((WorkTask) task);
            }
        }
        return workTasks;
    }

    @Override
    public List<PersonalTask> getAllPersonalTasks() {
        List<PersonalTask> personalTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof PersonalTask) {
                personalTasks.add((PersonalTask) task);
            }
        }
        return personalTasks;
    }

    @Override
    public void removeTask(int id) throws TaskNotFoundException {
        if (tasks.isEmpty()) {
            throw new EmptyTaskListException("Task list is empty.");
        }
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                return;
            }
        }
        throw new TaskNotFoundException("Task with id " + id + " not found.");
    }

    @Override
    public void removeAllTasks() {
        tasks.clear();
    }

    @Override
    public void changeTaskStatus(int id, TaskState taskState) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTaskState(taskState);
                break;
            }
        }
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
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    @Override
    public Task getTaskById(int id) throws TaskNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id.");
        }

        if (tasks.isEmpty()) {
            throw new EmptyTaskListException("List is empty.");
        }

        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new TaskNotFoundException("Task with id " + id + " not found.");
    }

    @Override
    public void addStudyTask(String title, String description, TaskState taskState) throws InvalidTaskDataException, InvalidTaskStateException {
        checkInvalidTaskData(title, taskState);
        tasks.add(new StudyTask(taskId, title, description, taskState));
        taskId++;
    }

    @Override
    public List<StudyTask> getStudyTasks() {
        List<StudyTask> studyTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof StudyTask) {
                studyTasks.add((StudyTask) task);
            }
        }
        return studyTasks;
    }

    @Override
    public StudyTask getStudyTaskById(int id) throws TaskNotFoundException {
        for (Task task : tasks) {
            if (task.getId() == id && task instanceof StudyTask) {
                return (StudyTask) task;
            }
        }
        throw new TaskNotFoundException("Study task with id " + id + " not found.");
    }

    @Override
    public List<Task> getTasksByStatus(TaskState taskState) {
        if (taskState != null) {
            List<Task> foundTasks = new ArrayList<>();
            if (tasks.isEmpty()) {
                throw new EmptyTaskListException("List with tasks is empty!");
            }
            for (Task task : tasks) {
                if (task.getTaskState() == taskState && !tasks.isEmpty()) {
                    foundTasks.add(task);
                }
            }
            return foundTasks;
        } else {
            throw new IllegalArgumentException("Invalid status.");
        }
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
