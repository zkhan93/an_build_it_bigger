package com.example.androjoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AndrojokerActivity extends AppCompatActivity {

    TextView joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androjoker);
        joke = (TextView) findViewById(R.id.joke);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String jokeStr = getIntent().getStringExtra("joke");
        if (jokeStr == null)
            jokeStr = "No jokes passed to me :(";
        if (joke != null)
            joke.setText(jokeStr);

    }
}
