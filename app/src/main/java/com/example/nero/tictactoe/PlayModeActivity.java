package com.example.nero.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayModeActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    *  9 buttons for the gridview
    * */
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonNew, buttonBack;
    private TextView player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.playmode);
        initiateViewObjects();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:

                break;

            case R.id.button2:

                break;

            case R.id.button3:

                break;

            case R.id.button4:

                break;

            case R.id.button5:

                break;

            case R.id.button6:

                break;

            case R.id.button7:

                break;

            case R.id.button8:

                break;

            case R.id.button9:

                break;

            case R.id.NewButton:

                break;

            case R.id.BackButton:

                break;

            default:
                break;
        }
    }

    /*
    *  Method to initiate the view objects
    * */
    public void initiateViewObjects(){

        this.button1 = (Button) findViewById(R.id.button1);
        this.button1.setOnClickListener(this);

        this.button2 = (Button) findViewById(R.id.button2);
        this.button2.setOnClickListener(this);

        this.button3 = (Button) findViewById(R.id.button3);
        this.button3.setOnClickListener(this);

        this.button4 = (Button) findViewById(R.id.button4);
        this.button4.setOnClickListener(this);

        this.button5 = (Button) findViewById(R.id.button5);
        this.button5.setOnClickListener(this);

        this.button6 = (Button) findViewById(R.id.button6);
        this.button6.setOnClickListener(this);

        this.button7 = (Button) findViewById(R.id.button7);
        this.button7.setOnClickListener(this);

        this.button8 = (Button) findViewById(R.id.button8);
        this.button8.setOnClickListener(this);

        this.button9 = (Button) findViewById(R.id.button9);
        this.button9.setOnClickListener(this);

        buttonNew = (Button) findViewById(R.id.NewButton);
        buttonNew.setOnClickListener(this);

        buttonBack = (Button) findViewById(R.id.BackButton);
        buttonBack.setOnClickListener(this);

        this.player1 = (TextView) findViewById(R.id.player1);
        this.player2 = (TextView) findViewById(R.id.player2);
    }
}
