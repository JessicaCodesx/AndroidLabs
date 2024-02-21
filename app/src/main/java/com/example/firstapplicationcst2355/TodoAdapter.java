package com.example.firstapplicationcst2355;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private Context context;
    private List<TodoItem> todoList;

    public TodoAdapter(Context context, List<TodoItem> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int position) {
        return todoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.todo_item_layout, null);
        }

        TodoItem todoItem = todoList.get(position);

        TextView textView = view.findViewById(R.id.todoTextView);
        textView.setText(todoItem.getTask());

        if (todoItem.isUrgent()) {
            view.setBackgroundColor(Color.RED);
            textView.setTextColor(Color.WHITE);
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.BLACK);
        }

        return view;
    }
}
