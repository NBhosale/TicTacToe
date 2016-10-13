package com.example.nero.tictactoe;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PlayScreen extends AppCompatActivity implements View.OnClickListener{

    private Button exit, play, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        play = (Button) findViewById(R.id.playButton);
        play.setOnClickListener(this);

        about = (Button) findViewById(R.id.aboutButton);
        about.setOnClickListener(this);

        exit = (Button) findViewById(R.id.exitButton);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.playButton:
                Intent intent = new Intent(this, PlayModeActivity.class);
                startActivity(intent);
                break;

            case R.id.aboutButton:

                break;

            case R.id.exitButton:
                finish();
                System.exit(0);
                break;

            default:
                break;
        }

    }

}
