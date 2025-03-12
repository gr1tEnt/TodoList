package com.gr1tEnt.model;

import com.gr1tEnt.constant.TaskState;

public class StudyTask extends Task {
    public StudyTask(int id, String title, String description, TaskState taskState) {
        super(id, title, description, taskState);
    }

    @Override
    public String toString() {
        return "StudyTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", taskState=" + getTaskState() +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
