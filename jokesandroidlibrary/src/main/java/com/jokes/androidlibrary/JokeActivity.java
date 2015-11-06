package com.jokes.androidlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            Toast.makeText(this, R.string.error_initializing_activity,Toast.LENGTH_LONG).show();
            return;
        }
        setContentView(R.layout.activity_joke);

        TextView jText = (TextView) findViewById(R.id.joke_txt);
        String txt = extras.getString(Intent.EXTRA_TEXT);
        jText.setText(txt);
    }
}
