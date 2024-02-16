package com.example.firstapplicationcst2355;
public class TodoItem {
    private String text;
    private boolean isUrgent;

    public TodoItem(String text, boolean isUrgent) {
        this.text = text;
        this.isUrgent = isUrgent;
    }

    public String getText() {
        return text;
    }

    public boolean isUrgent() {
        return isUrgent;
    }
}
