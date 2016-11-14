package com.example.nero.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class PlayModeActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    *  9 buttons for the gridview
    * */
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonNew, buttonBack;
    private TextView player1, player2;
    private boolean player1Move = true;
    private boolean player2Move = false;
    private int resourceDefaultIdImages = R.mipmap.oldcanvas;
    private int resourceIdCross = R.mipmap.cross;
    private int resourceIdCircle = R.mipmap.circle;
    private Button tileButtons[] = new Button[]{button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonNew, buttonBack};
    private int buttonIds[] = new int[]{R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.NewButton, R.id.BackButton};
    private int saveImageResourceOnRotation[] = new int[]{R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas, R.mipmap.oldcanvas};
    private boolean enablePropertyOfButtons[] = new boolean[]{true, true, true, true, true, true, true, true, true};
    private final String saveImageResourceOnRotationKey = "saveImageResourceOnRotationKey";
    private final String enablePropertyOfButtonsKey = "enablePropertyOfButtonsKey";
    private final String playerNameForSaveInstance = "playerNameForSaveInstance";
    private String booleanHolderPlayer1 = "booleanHolderPlayer1", booleanHolderPlayer2 = "booleanHolderPlayer2";
    private TextView userNameTextView;
    private String userNameHolder = "Player";
    private ImageView stats, share;
    private String fileName = "userscores.txt";
    private Map<String, Integer> userScores;
    private String userData = "";
    private String UserName, UserScore, AndroidName, AndroidScore;
    private String storeUserDataToFile = "";
    private int playerPlaceStoreArray[][] = new int[3][3];
    private Button tileButtonsMultiDimension[][] = new Button[][]{{button1, button2, button3}, {button4, button5, button6}, {button7, button8, button9}};
    private int buttonIdsMultiDimension[][] = new int[][]{{R.id.button1, R.id.button2, R.id.button3}, {R.id.button4, R.id.button5, R.id.button6}, {R.id.button7, R.id.button8, R.id.button9}};
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.playmode);

        for (int i = 0; i < playerPlaceStoreArray.length; i++) {
            for (int j = 0; j < playerPlaceStoreArray.length; j++) {
                playerPlaceStoreArray[i][j] = 2;
            }
        }

        initiateViewObjects();

        if (savedInstanceState != null) {

            userNameHolder = savedInstanceState.getString(playerNameForSaveInstance);
            saveImageResourceOnRotation = savedInstanceState.getIntArray(saveImageResourceOnRotationKey);
            enablePropertyOfButtons = savedInstanceState.getBooleanArray(enablePropertyOfButtonsKey);
            for (int i = 0; i < saveImageResourceOnRotation.length; i++) {
                tileButtons[i].setBackgroundResource(saveImageResourceOnRotation[i]);
                tileButtons[i].setEnabled(enablePropertyOfButtons[i]);
            }
            userNameTextView.setText(userNameHolder);
            player1Move = savedInstanceState.getBoolean(booleanHolderPlayer1);
            player2Move = savedInstanceState.getBoolean(booleanHolderPlayer2);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putIntArray(saveImageResourceOnRotationKey, saveImageResourceOnRotation);
        outState.putBooleanArray(enablePropertyOfButtonsKey, enablePropertyOfButtons);
        outState.putString(playerNameForSaveInstance, userNameHolder);
        outState.putBoolean(booleanHolderPlayer1, player1Move);
        outState.putBoolean(booleanHolderPlayer2, player2Move);
    }

    @Override
    public void onClick(View v) {

        if (player1Move) {
            switch (v.getId()) {
                case R.id.button1:
                    tileButtons[0].setEnabled(false);
                    saveImageResourceOnRotation[0] = setImageToButton(v);
                    enablePropertyOfButtons[0] = false;
                    playerPlaceStoreArray[0][0] = 0;
                    counter++;
                    break;

                case R.id.button2:
                    tileButtons[1].setEnabled(false);
                    saveImageResourceOnRotation[1] = setImageToButton(v);
                    enablePropertyOfButtons[1] = false;
                    playerPlaceStoreArray[0][1] = 0;
                    counter++;
                    break;

                case R.id.button3:
                    tileButtons[2].setEnabled(false);
                    saveImageResourceOnRotation[2] = setImageToButton(v);
                    enablePropertyOfButtons[2] = false;
                    playerPlaceStoreArray[0][2] = 0;
                    counter++;
                    break;

                case R.id.button4:
                    tileButtons[3].setEnabled(false);
                    saveImageResourceOnRotation[3] = setImageToButton(v);
                    enablePropertyOfButtons[3] = false;
                    playerPlaceStoreArray[1][0] = 0;
                    counter++;
                    break;

                case R.id.button5:
                    tileButtons[4].setEnabled(false);
                    saveImageResourceOnRotation[4] = setImageToButton(v);
                    enablePropertyOfButtons[4] = false;
                    playerPlaceStoreArray[1][1] = 0;
                    counter++;
                    break;

                case R.id.button6:
                    tileButtons[5].setEnabled(false);
                    saveImageResourceOnRotation[5] = setImageToButton(v);
                    enablePropertyOfButtons[5] = false;
                    playerPlaceStoreArray[1][2] = 0;
                    counter++;
                    break;

                case R.id.button7:
                    tileButtons[6].setEnabled(false);
                    saveImageResourceOnRotation[6] = setImageToButton(v);
                    enablePropertyOfButtons[6] = false;
                    playerPlaceStoreArray[2][0] = 0;
                    counter++;
                    break;

                case R.id.button8:
                    tileButtons[7].setEnabled(false);
                    saveImageResourceOnRotation[7] = setImageToButton(v);
                    enablePropertyOfButtons[7] = false;
                    playerPlaceStoreArray[2][1] = 0;
                    counter++;
                    break;

                case R.id.button9:
                    tileButtons[8].setEnabled(false);
                    saveImageResourceOnRotation[8] = setImageToButton(v);
                    enablePropertyOfButtons[8] = false;
                    playerPlaceStoreArray[2][2] = 0;
                    counter++;
                    break;

                default:
                    break;
            }
        } else if (player2Move) {
            switch (v.getId()) {
                case R.id.button1:
                    tileButtons[0].setEnabled(false);
                    saveImageResourceOnRotation[0] = setImageToButton(v);
                    enablePropertyOfButtons[0] = false;
                    playerPlaceStoreArray[0][0] = 1;
                    counter++;
                    break;

                case R.id.button2:
                    tileButtons[1].setEnabled(false);
                    saveImageResourceOnRotation[1] = setImageToButton(v);
                    enablePropertyOfButtons[1] = false;
                    playerPlaceStoreArray[0][1] = 1;
                    counter++;
                    break;

                case R.id.button3:
                    tileButtons[2].setEnabled(false);
                    saveImageResourceOnRotation[2] = setImageToButton(v);
                    enablePropertyOfButtons[2] = false;
                    playerPlaceStoreArray[0][2] = 1;
                    counter++;
                    break;

                case R.id.button4:
                    tileButtons[3].setEnabled(false);
                    saveImageResourceOnRotation[3] = setImageToButton(v);
                    enablePropertyOfButtons[3] = false;
                    playerPlaceStoreArray[1][0] = 1;
                    counter++;
                    break;

                case R.id.button5:
                    tileButtons[4].setEnabled(false);
                    saveImageResourceOnRotation[4] = setImageToButton(v);
                    enablePropertyOfButtons[4] = false;
                    playerPlaceStoreArray[1][1] = 1;
                    counter++;
                    break;

                case R.id.button6:
                    tileButtons[5].setEnabled(false);
                    saveImageResourceOnRotation[5] = setImageToButton(v);
                    enablePropertyOfButtons[5] = false;
                    playerPlaceStoreArray[1][2] = 1;
                    counter++;
                    break;

                case R.id.button7:
                    tileButtons[6].setEnabled(false);
                    saveImageResourceOnRotation[6] = setImageToButton(v);
                    enablePropertyOfButtons[6] = false;
                    playerPlaceStoreArray[2][0] = 1;
                    counter++;
                    break;

                case R.id.button8:
                    tileButtons[7].setEnabled(false);
                    saveImageResourceOnRotation[7] = setImageToButton(v);
                    enablePropertyOfButtons[7] = false;
                    playerPlaceStoreArray[2][1] = 1;
                    counter++;
                    break;

                case R.id.button9:
                    tileButtons[8].setEnabled(false);
                    saveImageResourceOnRotation[8] = setImageToButton(v);
                    enablePropertyOfButtons[8] = false;
                    playerPlaceStoreArray[2][2] = 1;
                    counter++;
                    break;

                default:
                    break;
            }
        }

        switch (v.getId()) {
            case R.id.NewButton:

                new AlertDialog.Builder(this)
                        .setTitle("Restarting Game")
                        .setMessage("Are you sure you want to restart the game?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                resetGame();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;

            case R.id.BackButton:
                new AlertDialog.Builder(this)
                        .setTitle("Terminating Game")
                        .setMessage("Are you sure you want to terminate current game and go back to main menu?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;

            case R.id.shareImage:

                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, "My score on Tic Tac Toe Game: " + userNameTextView.getText().toString() + ": " + player1.getText().toString());
                startActivity(Intent.createChooser(intentShare, "Share Score!"));

                break;

            case R.id.statsImage:
                String storeDataFromHash = new String();
                for (Map.Entry<String, Integer> entry : userScores.entrySet()) {
                    //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());


                    int value = Integer.valueOf(entry.getValue());
                    storeDataFromHash += entry.getKey() + ": "+value +"\n";
                }
                Intent intentStats = new Intent(this, Stats.class);
                intentStats.putExtra("displayStatsToStatsScreen", storeDataFromHash);
                startActivity(intentStats);
                break;

            default:
                break;
        }
        if (counter <= 8) {
            LongOperation longOperation = new LongOperation();
            longOperation.execute(0);
        }
    }


    /*
    *  Method to initiate the view objects
    * */
    public void initiateViewObjects() {
        userScores = new HashMap<String, Integer>();
        Intent intent = getIntent();
        userNameHolder = intent.getStringExtra("UserName");
        player1Move = intent.getBooleanExtra("PlayerOneMove", true);
        player2Move = intent.getBooleanExtra("PlayerTwoMove", false);
        for (int i = 0; i < tileButtons.length; i++) {
            tileButtons[i] = (Button) findViewById(buttonIds[i]);
            tileButtons[i].setOnClickListener(this);
        }
        this.player1 = (TextView) findViewById(R.id.player1);
        this.player2 = (TextView) findViewById(R.id.player2);
        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        userNameTextView.setText(userNameHolder);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        stats = (ImageView) findViewById(R.id.statsImage);
        stats.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.shareImage);
        share.setOnClickListener(this);
        readFileToSaveData();

        if(!userScores.containsKey(userNameHolder)) {
            userScores.put(userNameHolder , 0);
        }
        if(!userScores.containsKey("Android")){
            userScores.put("Android" , 0);
        }
        if(userScores.containsKey(userNameHolder) && userScores.containsKey("Android")){
            player1.setText(String.valueOf(userScores.get(userNameHolder)));
            player2.setText(String.valueOf(userScores.get("Android")));
        }
        LongOperation longOperation = new LongOperation();
        longOperation.execute(0);
    }


    public void resetGame() {
        for (int i = 0; i < tileButtons.length - 2; i++) {
            tileButtons[i].setBackgroundResource(resourceDefaultIdImages);
            tileButtons[i].setEnabled(true);
            saveImageResourceOnRotation[i] = resourceDefaultIdImages;
            enablePropertyOfButtons[i] = true;
        }
        Intent intent = getIntent();
        player1Move = intent.getBooleanExtra("PlayerOneMove", true);
        player2Move = intent.getBooleanExtra("PlayerTwoMove", false);
        counter = 0;
        for (int i = 0; i < playerPlaceStoreArray.length; i++) {
            for (int j = 0; j < playerPlaceStoreArray.length; j++) {
                playerPlaceStoreArray[i][j] = 2;
            }
        }
        LongOperation longOperation = new LongOperation();
        longOperation.execute(0);
    }

    public int setImageToButton(View view) {
        if (player1Move) {
            view.setBackgroundResource(resourceIdCross);
            player2Move = true;
            player1Move = false;
            return resourceIdCross;
        } else if (player2Move) {
            view.setBackgroundResource(resourceIdCircle);
            player2Move = false;
            player1Move = true;
            return resourceIdCircle;
        }
        return 0;
    }


    public void readFileToSaveData() {

        if (checkIfFileExist(fileName)) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //Read File Line By Line
            try {
                String strLine;
                while ((strLine = bufferedReader.readLine()) != null) {
                    int number = Integer.valueOf(strLine.replaceAll("[^0-9]", ""));
                    String nameOfUser = strLine.substring(0, strLine.indexOf(':'));
                    userScores.put(nameOfUser, number);
                    System.out.println(strLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void writeToFile(String data) {

        if (!userScores.containsKey(data)) {
            try {
                OutputStreamWriter myOutWriter = new OutputStreamWriter(openFileOutput(fileName, MODE_APPEND));
                myOutWriter.append("Android: 0");
                myOutWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean checkIfFileExist(String fileName) {
        File file = getBaseContext().getFileStreamPath(fileName);
        return file.exists();
    }

    public class LongOperation extends AsyncTask<Integer, Integer, String> {
        AlertDialog alertDialog;

        public boolean userIsWInner() {
            if ((playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[2][2] == 0)
                    || (playerPlaceStoreArray[0][2] == 0 && playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[2][0] == 0)
                    || (playerPlaceStoreArray[0][1] == 0 && playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[2][1] == 0)
                    || (playerPlaceStoreArray[0][2] == 0 && playerPlaceStoreArray[1][2] == 0 && playerPlaceStoreArray[2][2] == 0)
                    || (playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[0][1] == 0 && playerPlaceStoreArray[0][2] == 0)
                    || (playerPlaceStoreArray[1][0] == 0 && playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[1][2] == 0)
                    || (playerPlaceStoreArray[2][0] == 0 && playerPlaceStoreArray[2][1] == 0 && playerPlaceStoreArray[2][2] == 0)
                    || (playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[1][0] == 0 && playerPlaceStoreArray[2][0] == 0)) {
                return true;
            }
            return false;
        }

        public boolean androidIsWInner() {
            if ((playerPlaceStoreArray[0][0] == 1 && playerPlaceStoreArray[1][1] == 1 && playerPlaceStoreArray[2][2] == 1)
                    || (playerPlaceStoreArray[0][2] == 1 && playerPlaceStoreArray[1][1] == 1 && playerPlaceStoreArray[2][0] == 1)
                    || (playerPlaceStoreArray[0][1] == 1 && playerPlaceStoreArray[1][1] == 1 && playerPlaceStoreArray[2][1] == 1)
                    || (playerPlaceStoreArray[0][2] == 1 && playerPlaceStoreArray[1][2] == 1 && playerPlaceStoreArray[2][2] == 1)
                    || (playerPlaceStoreArray[0][0] == 1 && playerPlaceStoreArray[0][1] == 1 && playerPlaceStoreArray[0][2] == 1)
                    || (playerPlaceStoreArray[1][0] == 1 && playerPlaceStoreArray[1][1] == 1 && playerPlaceStoreArray[1][2] == 1)
                    || (playerPlaceStoreArray[2][0] == 1 && playerPlaceStoreArray[2][1] == 1 && playerPlaceStoreArray[2][2] == 1)
                    || (playerPlaceStoreArray[0][0] == 1 && playerPlaceStoreArray[1][0] == 1 && playerPlaceStoreArray[2][0] == 1)) {
                return true;
            }
            return false;
        }

        @Override
        protected String doInBackground(Integer... params) {
            if (userIsWInner()) {
                publishProgress(-1);

            } else if (androidIsWInner()) {
                publishProgress(-2);
            } else if (!userIsWInner() && !androidIsWInner() && counter == 8) {
                publishProgress(-3);
            } else if (player2Move && counter <= 8) {
                Random rand = new Random();

                int a;
                int b;
                if (playerPlaceStoreArray[0][0] == 2 &&
                        ((playerPlaceStoreArray[0][1] == 0 && playerPlaceStoreArray[0][2] == 0) ||
                                (playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[2][2] == 0) ||
                                (playerPlaceStoreArray[1][0] == 0 && playerPlaceStoreArray[2][0] == 0))) {
                    a = 0;
                    b = 0;
                } else if (playerPlaceStoreArray[0][1] == 2 &&
                        ((playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[2][1] == 0) ||
                                (playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[0][2] == 0))) {
                    a = 0;
                    b = 1;
                } else if (playerPlaceStoreArray[0][2] == 2 &&
                        ((playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[0][1] == 0) ||
                                (playerPlaceStoreArray[2][0] == 0 && playerPlaceStoreArray[1][1] == 0) ||
                                (playerPlaceStoreArray[1][2] == 0 && playerPlaceStoreArray[2][2] == 0))) {
                    a = 0;
                    b = 2;
                } else if (playerPlaceStoreArray[1][0] == 2 &&
                        ((playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[1][2] == 0) ||
                                (playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[2][0] == 0))) {
                    a = 1;
                    b = 0;
                } else if (playerPlaceStoreArray[1][1] == 2 &&
                        ((playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[2][2] == 0) ||
                                (playerPlaceStoreArray[0][1] == 0 && playerPlaceStoreArray[2][1] == 0) ||
                                (playerPlaceStoreArray[2][0] == 0 && playerPlaceStoreArray[0][2] == 0) ||
                                (playerPlaceStoreArray[1][0] == 0 && playerPlaceStoreArray[1][2] == 0))) {
                    a = 1;
                    b = 1;
                } else if (playerPlaceStoreArray[1][2] == 2 &&
                        ((playerPlaceStoreArray[1][0] == 0 && playerPlaceStoreArray[1][1] == 0) ||
                                (playerPlaceStoreArray[0][2] == 0 && playerPlaceStoreArray[2][2] == 0))) {
                    a = 1;
                    b = 2;
                } else if (playerPlaceStoreArray[2][0] == 2 &&
                        ((playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[1][0] == 0) ||
                                (playerPlaceStoreArray[2][1] == 0 && playerPlaceStoreArray[2][2] == 0) ||
                                (playerPlaceStoreArray[1][1] == 0 && playerPlaceStoreArray[0][2] == 0))) {
                    a = 2;
                    b = 0;
                } else if (playerPlaceStoreArray[2][1] == 2 &&
                        ((playerPlaceStoreArray[0][1] == 0 && playerPlaceStoreArray[1][1] == 0) ||
                                (playerPlaceStoreArray[2][0] == 0 && playerPlaceStoreArray[2][2] == 0))) {
                    a = 2;
                    b = 1;
                } else if (playerPlaceStoreArray[2][2] == 2 &&
                        ((playerPlaceStoreArray[0][0] == 0 && playerPlaceStoreArray[1][1] == 0) ||
                                (playerPlaceStoreArray[0][2] == 0 && playerPlaceStoreArray[1][2] == 0) ||
                                (playerPlaceStoreArray[2][0] == 0 && playerPlaceStoreArray[2][1] == 0))) {
                    a = 2;
                    b = 2;
                } else {
                    a = rand.nextInt((2 - 0) + 1) + 0;
                    b = rand.nextInt((2 - 0) + 1) + 0;
                    while (playerPlaceStoreArray[a][b] != 2) {
                        a = rand.nextInt((2 - 0) + 1) + 0;
                        b = rand.nextInt((2 - 0) + 1) + 0;
                    }
                }

                publishProgress(a, b);

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }


        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int a, b;
            if (values.length > 1) {
                a = values[0];
                b = values[1];
                if (a == 0 && b == 0) {
                    tileButtons[0].performClick();
                } else if (a == 0 && b == 1) {
                    tileButtons[1].performClick();
                } else if (a == 0 && b == 2) {
                    tileButtons[2].performClick();
                } else if (a == 1 && b == 0) {
                    tileButtons[3].performClick();
                } else if (a == 1 && b == 1) {
                    tileButtons[4].performClick();
                } else if (a == 1 && b == 2) {
                    tileButtons[5].performClick();
                } else if (a == 2 && b == 0) {
                    tileButtons[6].performClick();
                } else if (a == 2 && b == 1) {
                    tileButtons[7].performClick();
                } else if (a == 2 && b == 2) {
                    tileButtons[8].performClick();
                }
            } else if (values[0] == -1) {
            userScores.put(userNameTextView.getText().toString(), userScores.get(userNameTextView.getText().toString())+1);
                for (int i = 0; i < tileButtons.length - 2; i++) {
                    tileButtons[i].setEnabled(false);
                    enablePropertyOfButtons[i] = false;
                }
                int number = userScores.get(userNameTextView.getText().toString());
                player1.setText(String.valueOf(number));
                writeToFile();
               new AlertDialog.Builder(PlayModeActivity.this)
                        .setTitle("Winner!!")
                        .setMessage(userNameTextView.getText().toString() + " is winner!!")
                        .setPositiveButton("Restart!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartTheGame();
                            }
                        })
                       .setNegativeButton("Main menu" , new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                               startActivity(intent);
                               finish();
                           }
                       })
                        .show();
            } else if (values[0] == -2) {
                for (int i = 0; i < tileButtons.length - 2; i++) {
                    tileButtons[i].setEnabled(false);
                    enablePropertyOfButtons[i] = false;
                }
                userScores.put("Android", userScores.get("Android")+1);
                int number = userScores.get("Android");
                player2.setText(String.valueOf(number));
                writeToFile();
                new AlertDialog.Builder(PlayModeActivity.this)
                        .setTitle("Winner!!")
                        .setMessage("Android is winner!! Better luck next time!!")
                        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartTheGame();
                            }
                        })
                        .setNegativeButton("Main menu" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();

            } else if (values[0] == -3) {
                writeToFile();
                new AlertDialog.Builder(PlayModeActivity.this)
                        .setTitle("Game draw!!")
                        .setMessage("Game draw! Do you want to restart the game?")
                        .setPositiveButton("New game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartTheGame();
                            }
                        })
                        .setNegativeButton("Main menu" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        }

        public void restartTheGame(){
            for (int i = 0; i < tileButtons.length - 2; i++) {
                tileButtons[i].setBackgroundResource(resourceDefaultIdImages);
                tileButtons[i].setEnabled(true);
                saveImageResourceOnRotation[i] = resourceDefaultIdImages;
                enablePropertyOfButtons[i] = true;
            }
            Intent intent = getIntent();

            player1Move = intent.getBooleanExtra("PlayerOneMove", true);
            player2Move = intent.getBooleanExtra("PlayerTwoMove", false);
            counter = 0;
            for (int i = 0; i < playerPlaceStoreArray.length; i++) {
                for (int j = 0; j < playerPlaceStoreArray.length; j++) {
                    playerPlaceStoreArray[i][j] = 2;
                }
            }
            LongOperation longOperation = new LongOperation();
            longOperation.execute(0);
        }
        public void writeToFile() {
            String storeDataFromHash = new String();
            for (Map.Entry<String, Integer> entry : userScores.entrySet()) {
                //System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());


                int value = Integer.valueOf(entry.getValue());
                storeDataFromHash += entry.getKey() + ": "+value +"\n";
            }
                try {
                    OutputStreamWriter myOutWriter = new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE));
                    myOutWriter.append(storeDataFromHash);
                    myOutWriter.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }
}