package com.gr1tEnt.api;

public interface TaskMenu {
    void addTask(String title, String description, TaskState taskState);
    void addWorkTask(String title, String description, TaskState taskState);
    void addPersonalTask(String title, String description, TaskState taskState);

    List<Task> getAllTasks();
    List<WorkTask> getAllWorkTasks();
    List<PersonalTask> getAllPersonalTasks();

    void removeTask(int id);
    void removeAllTasks(int id);

    void changeTaskStatus(int id, TaskState taskState);

    List<Task> getAllTaskByStatus(TaskState taskState);

    List<Task> searchTask(String keyword);
    Task getTaskById(int id);
}
