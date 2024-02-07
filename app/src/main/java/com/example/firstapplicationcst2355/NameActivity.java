package com.example.firstapplicationcst2355;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        TextView textView2 = findViewById(R.id.textView2);
        Button buttonThanks = findViewById(R.id.buttonThanks);
        Button buttonNo = findViewById(R.id.buttonNo);

        //get user's name from main activity
        String name = getIntent().getStringExtra("name");
        //update text view to welcome user
        textView2.setText(getString(R.string.welcome_message, name));
        //thank you button click
        buttonThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setResult(1);
                finish();
            }
        });

        //no button click
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set result to 0 and return to main activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newName", "name");
                setResult(0, resultIntent);
                finish();
            }
        });
    }
}