package com.gr1tEnt.model;

import com.gr1tEnt.constant.TaskState;

public class StudyTask extends Task {

    public StudyTask(Builder builder) {
        super(builder);
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

    public static class Builder extends Task.Builder {
        @Override
        public Task build() {
            return new StudyTask(this);
        }
    }
}
