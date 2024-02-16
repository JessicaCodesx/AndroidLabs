package com.example.firstapplicationcst2355;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TodoItem> todoList;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize list and adapter
        todoList = new ArrayList<>();
        todoAdapter = new TodoAdapter(this, todoList);

        //find references to attributes
        ListView listView = findViewById(R.id.listViewTasks);
        EditText editText = findViewById(R.id.editTextTask);
        Switch urgentSwitch = findViewById(R.id.switchUrgent);
        Button addButton = findViewById(R.id.buttonAddTask);

        listView.setAdapter(todoAdapter);

        // setOnItemLongClickListener for deleting a task
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteAlertDialog(position);
                return true;
            }
        });

        // listener for add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoText = editText.getText().toString();
                boolean isUrgent = urgentSwitch.isChecked();
                TodoItem newTodo = new TodoItem(todoText, isUrgent);
                todoList.add(newTodo);
                editText.getText().clear();
                todoAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showDeleteAlertDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete this?");
        builder.setMessage("The selected row is: " + position);

        builder.setPositiveButton("Delete", (dialog, which) -> {
            // remove item
            todoList.remove(position);
            todoAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
        });

        builder.show();
    }
}
