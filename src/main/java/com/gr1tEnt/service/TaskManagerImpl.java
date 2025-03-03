package com.gr1tEnt.service;

import com.gr1tEnt.api.TaskManager;
import com.gr1tEnt.constant.TaskState;
import com.gr1tEnt.model.PersonalTask;
import com.gr1tEnt.model.Task;
import com.gr1tEnt.model.WorkTask;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskManagerImpl implements TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    public static int taskId;

    @Override
    public void addTask(String title, String description, TaskState taskState) {
        tasks.add(new Task(taskId, title, description, taskState));
        taskId++;
    }

    @Override
    public void addWorkTask(String title, String description, TaskState taskState) {
        tasks.add(new WorkTask(taskId, title, description, taskState));
        taskId++;
    }

    @Override
    public void addPersonalTask(String title, String description, TaskState taskState) {
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
                tasks.add(task);
            }
        }
        return personalTasks;
    }

    @Override
    public void removeTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                break;
            }
        }
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
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
