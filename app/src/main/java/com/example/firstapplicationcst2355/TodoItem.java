package com.example.firstapplicationcst2355;
public class TodoItem {
    private String task;
    private boolean isUrgent;
    private int id;

    public TodoItem(String task, boolean isUrgent) {
        this.task = task;
        this.isUrgent = isUrgent;
    }

    public TodoItem(int id, String task, boolean isUrgent) {
        this.id = id;
        this.task = task;
        this.isUrgent = isUrgent;
    }

    public String getTask() {
        return task;
    }

    public int getId(){
        return id;
    }
    public boolean isUrgent() {

        return isUrgent;
    }

    public void setId(int id) {
        this.id = id;
    }
}
