package com.example.nero.tictactoe;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PlayScreen extends AppCompatActivity implements View.OnClickListener{

    /*
*  Declare the buttons for the activity
* */
    private Button exit, playSingle, playMulti, about;

    /*
*  Intitiate the views
* */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        playSingle = (Button) findViewById(R.id.singlePlayButton);
        playSingle.setOnClickListener(this);
        playMulti = (Button) findViewById(R.id.multiPlayButton);
        playMulti.setOnClickListener(this);
        about = (Button) findViewById(R.id.aboutButton);
        about.setOnClickListener(this);

        exit = (Button) findViewById(R.id.exitButton);
        exit.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
    /*
   *  Handles the on click for button events
   * */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.singlePlayButton:
                launchUserInfoMode();
                break;

            case R.id.multiPlayButton:
                Intent intent = new Intent(this, MultiplayerActivity.class);
                startActivity(intent);
                break;

            case R.id.aboutButton:
               startActivity(new Intent(this, AboutScreen.class));
                break;

            case R.id.exitButton:
                exitApplcation();
                break;

            default:
                break;
        }

    }
    /*
   *  Launches the single player activity info
   * */
    public void launchUserInfoMode(){
        Intent intent = new Intent(this, UserInfoActivity.class);
        startActivity(intent);
    }

    public void exitApplcation(){
        finish();
        System.exit(0);
    }
}
