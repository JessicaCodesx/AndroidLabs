package com.example.firstapplicationcst2355;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        //load user's name
        String name = sharedPreferences.getString("name", "");
        editText.setText(name);

        //button "Next"
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab user's name value
                String userName = editText.getText().toString();

                //store value to shared preferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", userName);
                editor.apply();

                //start name activity
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                intent.putExtra("name", userName);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //save current value to shared pref
        String userName = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", userName);
        editor.apply();
    }

    //process after name activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 0){
                //change name
                String newName = data.getStringExtra("newName");
                if(newName != null && !newName.isEmpty()) {
                    editText.setText(newName);
                }
            } else if (resultCode == 1) {
                //close app
                finish();
            }
        }
    }
}