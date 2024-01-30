package com.example.firstapplicationcst2355;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraint);

        Button buttonPressMe = findViewById(R.id.buttonPressMe);
        CheckBox checkBox = findViewById(R.id.checkBox);

        buttonPressMe.setOnClickListener(v -> {
                EditText editText = findViewById(R.id.editText);
                TextView textView = findViewById(R.id.firstText);

                String newText = editText.getText().toString();

                textView.setText(newText);

                String toastMessage = getResources().getString(R.string.toast_message);
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String snackbarMessage = "The checkbox is now " + (isChecked ? "on" : "off");
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), snackbarMessage, Snackbar.LENGTH_LONG)
                    .setAction("Undo", view -> {
                        checkBox.setChecked(!isChecked);
                    });

            snackbar.show();
        });
    }
}

