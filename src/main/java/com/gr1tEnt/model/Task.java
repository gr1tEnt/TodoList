package com.gr1tEnt.model;

import com.gr1tEnt.constant.TaskState;

import java.util.Objects;

public class Task {
    private int id;
    private String title;
    private String description;
    private TaskState taskState;

    public Task(int id, String title, String description, TaskState taskState) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskState = taskState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(taskState, task.taskState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, taskState);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskState=" + taskState +
                '}';
    }
}
