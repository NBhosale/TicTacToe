package com.example.nero.tictactoe;

import android.content.Intent;
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
    private boolean player1Move = true;
    private boolean player2Move = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.playmode);
        //if(savedInstanceState !=null) {
        initiateViewObjects();
        //}
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:
                if (player1Move) {
                    button1.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button1.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                // System.out.println(button1.getTag().toString());
                button1.setEnabled(false);
                break;

            case R.id.button2:
                if (player1Move) {
                    button2.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button2.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button2.setEnabled(false);
                break;

            case R.id.button3:
                if (player1Move) {
                    button3.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button3.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button3.setEnabled(false);
                break;

            case R.id.button4:
                if (player1Move) {
                    button4.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button4.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button4.setEnabled(false);
                break;

            case R.id.button5:
                if (player1Move) {
                    button5.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button5.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button5.setEnabled(false);
                break;

            case R.id.button6:
                if (player1Move) {
                    button6.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button6.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button6.setEnabled(false);
                break;

            case R.id.button7:
                if (player1Move) {
                    button7.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button7.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button7.setEnabled(false);
                break;

            case R.id.button8:
                if (player1Move) {
                    button8.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button8.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button8.setEnabled(false);
                break;

            case R.id.button9:
                if (player1Move) {
                    button9.setBackgroundResource(R.mipmap.cross);
                    player2Move = true;
                    player1Move = false;
                } else{
                    button9.setBackgroundResource(R.mipmap.circle);
                    player2Move = false;
                    player1Move = true;
                }
                button9.setEnabled(false);
                break;

            case R.id.NewButton:
                resetGame();
                break;

            case R.id.BackButton:
                Intent intent = new Intent(this, PlayScreen.class);
                startActivity(intent);
                finish();
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

    public void resetGame(){
        button1.setBackgroundResource(R.mipmap.oldcanvas);
        button1.setEnabled(true);

        button2.setBackgroundResource(R.mipmap.oldcanvas);
        button2.setEnabled(true);

        button3.setBackgroundResource(R.mipmap.oldcanvas);
        button3.setEnabled(true);

        button4.setBackgroundResource(R.mipmap.oldcanvas);
        button4.setEnabled(true);

        button5.setBackgroundResource(R.mipmap.oldcanvas);
        button5.setEnabled(true);

        button6.setBackgroundResource(R.mipmap.oldcanvas);
        button6.setEnabled(true);

        button7.setBackgroundResource(R.mipmap.oldcanvas);
        button7.setEnabled(true);

        button8.setBackgroundResource(R.mipmap.oldcanvas);
        button8.setEnabled(true);

        button9.setBackgroundResource(R.mipmap.oldcanvas);
        button9.setEnabled(true);

    }

}
