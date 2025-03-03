package com.gr1tEnt.model;

public class PersonalTask extends Task {

    public PersonalTask(int id, String title, String description, TaskState taskState) {
        super(id, title, description, taskState);
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", taskState=" + getTaskState() +
                '}';
}
