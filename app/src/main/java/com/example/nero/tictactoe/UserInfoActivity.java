package com.example.nero.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button finishButton, backButton;
    private EditText userName;
    private String userNameHolder = "Player";
    private boolean player1move = false, player2move = false;
    private RadioButton oneEnableMove, twoEnableMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        finishButton = (Button) findViewById(R.id.finishButton);
        finishButton.setOnClickListener(this);
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        userName = (EditText) findViewById(R.id.userName);
        oneEnableMove = (RadioButton)findViewById(R.id.xmove);
        twoEnableMove = (RadioButton)findViewById(R.id.omove);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.finishButton:
                launchPlayMode();
                break;
            case R.id.backButton:

                Intent intent = new Intent(this, PlayScreen.class);
                startActivity(intent);
                finish();
                break;

        }
    }
    public void launchPlayMode(){

        if(oneEnableMove.isChecked()){
            player1move = true;
        }else{
            player2move = true;
        }
        Intent intent = new Intent(this, PlayModeActivity.class);
        if(userName.getText().toString().length() > 0){
            userNameHolder = userName.getText().toString();
        }
        intent.putExtra("UserName", userNameHolder);
        //intent.putExtra();
        //intent.putExtra();
        intent.putExtra("PlayerOneMove", player1move);
        intent.putExtra("PlayerTwoMove", player2move);
        startActivity(intent);
        finish();
    }
}
