package com.gr1tEnt.model;

import com.gr1tEnt.constant.TaskState;

public class PersonalTask extends Task {

    protected PersonalTask(Builder builder) {
        super(builder);
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
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
            return new PersonalTask(this);
        }
    }
}
