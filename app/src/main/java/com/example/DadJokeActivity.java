package com.example;

import android.os.Bundle;
import android.widget.TextView;

import com.example.firstapplicationcst2355.R;

public class DadJokeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dad_joke);

        //set the dad joke text in the text view
        TextView dadJokeTextView = findViewById(R.id.dadJokeTextView);
        dadJokeTextView.setText("Why don't scientists trust atoms? Because they make up everything!");
    }
}
