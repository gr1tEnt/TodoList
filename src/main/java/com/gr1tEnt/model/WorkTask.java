package com.gr1tEnt.model;

public class WorkTask extends Task {

    public WorkTask(int id, String title, String description, TaskState taskState) {
        super(id, title, description, taskState);
        }

    @Override
    public String toString() {
        return "WorkTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", taskState=" + getTaskState() +
                '}';
    }
}
