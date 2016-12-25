package com.example.nero.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class PlayModeActivity extends AppCompatActivity implements View.OnClickListener {

    /*
    *  9 buttons for the gridview and the requred variables
    * */
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonNew, buttonBack;
    private TextView player1, player2;
    private Button VolumeController;
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
    private TextView userNameTextView, androidNameTextView;
    private String userNameHolder = "Player", androidNameHolder = "Android";
    private ImageView stats, share;
    private String fileName = "userscores.txt";
    private String fileTimeStamp = "userscoresTimeStamp.txt";
    private Map<String, Integer> userScores;
    private Map<String, String> userScoresTimeStamp;
    private int playerPlaceStoreArray[][] = new int[3][3];
    private Button tileButtonsMultiDimension[][] = new Button[][]{{button1, button2, button3}, {button4, button5, button6}, {button7, button8, button9}};
    private int buttonIdsMultiDimension[][] = new int[][]{{R.id.button1, R.id.button2, R.id.button3}, {R.id.button4, R.id.button5, R.id.button6}, {R.id.button7, R.id.button8, R.id.button9}};
    private int counter = 0;
    private boolean didWelcome = true;
    private String welcomeMessageBoolean = "welcomeMessageBoolean";
    private DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
    private Date date = new Date();
    private String timeDate = dateFormat.format(date);
    private MediaPlayer mp;
    private boolean multiplyerBoolean = false;
    private float maxVolume = 0.5f, minVolume = 0.3f;
    private boolean volumeBoolean = false;
    private String timerStorage = "";
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running = true;

    /*
*  view  and variable initaitions
* */
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
  /*
*  Save instance of view
* */
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
            didWelcome = savedInstanceState.getBoolean(welcomeMessageBoolean);
        }

        if (didWelcome) {
            new AlertDialog.Builder(this)
                    .setTitle("Welcome!")
                    .setMessage("Welcome " + userNameHolder + " & " + androidNameHolder + "!! Thank you for playing and good luck!" + " The scores posted below is total wins on each player.")
                    .setPositiveButton("Start!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            runTimer();
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            runTimer();
                        }
                    })
                    .show();
            didWelcome = false;
        }

    }
    /*
   *  Controlles the volume of app
   * */
    public void volumeController(View view) {
        if (volumeBoolean) {
            VolumeController.setBackgroundResource(R.mipmap.volumeon);
            volumeBoolean = false;
            maxVolume = 0.5f;
            minVolume = 0.3f;
        } else {
            VolumeController.setBackgroundResource(R.mipmap.silent);
            maxVolume = 0f;
            minVolume = 0f;
            volumeBoolean = true;
        }
    }

    /*
*  Timer method to initiate timer
* */
    public void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.textViewTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%02d:%02d",
                        minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                timerStorage = time;
                handler.postDelayed(this, 1000);
            }
        });
    }

    /*
* Save instance of the state
* */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putIntArray(saveImageResourceOnRotationKey, saveImageResourceOnRotation);
        outState.putBooleanArray(enablePropertyOfButtonsKey, enablePropertyOfButtons);
        outState.putString(playerNameForSaveInstance, userNameHolder);
        outState.putBoolean(booleanHolderPlayer1, player1Move);
        outState.putBoolean(booleanHolderPlayer2, player2Move);
        outState.putBoolean(welcomeMessageBoolean, didWelcome);

    }
    /*
   *  Handle on click listener of the button events for android and player
   * */
    @Override
    public void onClick(View v) {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        mp.setVolume((float) maxVolume, (float) minVolume);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }

        });

        if (player1Move) {
            switch (v.getId()) {
                case R.id.button1:
                    mp.start();
                    tileButtons[0].setEnabled(false);
                    saveImageResourceOnRotation[0] = setImageToButton(v);
                    enablePropertyOfButtons[0] = false;
                    playerPlaceStoreArray[0][0] = 0;
                    counter++;
                    break;

                case R.id.button2:
                    mp.start();
                    tileButtons[1].setEnabled(false);
                    saveImageResourceOnRotation[1] = setImageToButton(v);
                    enablePropertyOfButtons[1] = false;
                    playerPlaceStoreArray[0][1] = 0;
                    counter++;
                    break;

                case R.id.button3:
                    mp.start();
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
                    mp.start();
                    tileButtons[4].setEnabled(false);
                    saveImageResourceOnRotation[4] = setImageToButton(v);
                    enablePropertyOfButtons[4] = false;
                    playerPlaceStoreArray[1][1] = 0;
                    counter++;
                    break;

                case R.id.button6:
                    mp.start();
                    tileButtons[5].setEnabled(false);
                    saveImageResourceOnRotation[5] = setImageToButton(v);
                    enablePropertyOfButtons[5] = false;
                    playerPlaceStoreArray[1][2] = 0;
                    counter++;
                    break;

                case R.id.button7:
                    mp.start();
                    tileButtons[6].setEnabled(false);
                    saveImageResourceOnRotation[6] = setImageToButton(v);
                    enablePropertyOfButtons[6] = false;
                    playerPlaceStoreArray[2][0] = 0;
                    counter++;
                    break;

                case R.id.button8:
                    mp.start();
                    tileButtons[7].setEnabled(false);
                    saveImageResourceOnRotation[7] = setImageToButton(v);
                    enablePropertyOfButtons[7] = false;
                    playerPlaceStoreArray[2][1] = 0;
                    counter++;
                    break;

                case R.id.button9:
                    mp.start();
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
                    mp.start();
                    tileButtons[0].setEnabled(false);
                    saveImageResourceOnRotation[0] = setImageToButton(v);
                    enablePropertyOfButtons[0] = false;
                    playerPlaceStoreArray[0][0] = 1;
                    counter++;
                    break;

                case R.id.button2:
                    mp.start();
                    tileButtons[1].setEnabled(false);
                    saveImageResourceOnRotation[1] = setImageToButton(v);
                    enablePropertyOfButtons[1] = false;
                    playerPlaceStoreArray[0][1] = 1;
                    counter++;
                    break;

                case R.id.button3:
                    mp.start();
                    tileButtons[2].setEnabled(false);
                    saveImageResourceOnRotation[2] = setImageToButton(v);
                    enablePropertyOfButtons[2] = false;
                    playerPlaceStoreArray[0][2] = 1;
                    counter++;
                    break;

                case R.id.button4:
                    mp.start();
                    tileButtons[3].setEnabled(false);
                    saveImageResourceOnRotation[3] = setImageToButton(v);
                    enablePropertyOfButtons[3] = false;
                    playerPlaceStoreArray[1][0] = 1;
                    counter++;
                    break;

                case R.id.button5:
                    mp.start();
                    tileButtons[4].setEnabled(false);
                    saveImageResourceOnRotation[4] = setImageToButton(v);
                    enablePropertyOfButtons[4] = false;
                    playerPlaceStoreArray[1][1] = 1;
                    counter++;
                    break;

                case R.id.button6:
                    mp.start();
                    tileButtons[5].setEnabled(false);
                    saveImageResourceOnRotation[5] = setImageToButton(v);
                    enablePropertyOfButtons[5] = false;
                    playerPlaceStoreArray[1][2] = 1;
                    counter++;
                    break;

                case R.id.button7:
                    mp.start();
                    tileButtons[6].setEnabled(false);
                    saveImageResourceOnRotation[6] = setImageToButton(v);
                    enablePropertyOfButtons[6] = false;
                    playerPlaceStoreArray[2][0] = 1;
                    counter++;
                    break;

                case R.id.button8:
                    mp.start();
                    tileButtons[7].setEnabled(false);
                    saveImageResourceOnRotation[7] = setImageToButton(v);
                    enablePropertyOfButtons[7] = false;
                    playerPlaceStoreArray[2][1] = 1;
                    counter++;
                    break;

                case R.id.button9:
                    mp.start();
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
                    storeDataFromHash += entry.getKey() + ": " + value + " wins\n" + "LastPlayed: " + userScoresTimeStamp.get(entry.getKey()) + "\n\n";
                }
                Intent intentStats = new Intent(this, Stats.class);
                intentStats.putExtra("displayStatsToStatsScreen", storeDataFromHash);
                startActivity(intentStats);
                break;

            default:
                break;
        }

            LongOperation longOperation = new LongOperation();
            longOperation.execute(0);

    }


    /*
    *  Method to initiate the view objects
    * */
    public void initiateViewObjects() {
        VolumeController = (Button) findViewById(R.id.SoundButton);
        userScores = new HashMap<String, Integer>();
        userScoresTimeStamp = new HashMap<String, String>();
        Intent intent = getIntent();
        userNameHolder = intent.getStringExtra("UserName");
        androidNameHolder = intent.getStringExtra("SecondPlayerNameToPlay");
        player1Move = intent.getBooleanExtra("PlayerOneMove", true);
        player2Move = intent.getBooleanExtra("PlayerTwoMove", false);
        multiplyerBoolean = intent.getBooleanExtra("itsMultiplyerGame", false);
        for (int i = 0; i < tileButtons.length; i++) {
            tileButtons[i] = (Button) findViewById(buttonIds[i]);
            tileButtons[i].setOnClickListener(this);
        }
        this.player1 = (TextView) findViewById(R.id.player1);
        this.player2 = (TextView) findViewById(R.id.player2);
        androidNameTextView = (TextView) findViewById(R.id.androidPlayer);
        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        userNameTextView.setText(userNameHolder);
        androidNameTextView.setText(androidNameHolder);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        stats = (ImageView) findViewById(R.id.statsImage);
        stats.setOnClickListener(this);
        share = (ImageView) findViewById(R.id.shareImage);
        share.setOnClickListener(this);
        readFileToSaveData();

        if (!userScores.containsKey(userNameHolder)) {
            userScores.put(userNameHolder, 0);
        }
        if (!userScores.containsKey(androidNameHolder)) {
            userScores.put(androidNameHolder, 0);
        }
        readFileToSaveDataTimeStamp();
        if (!userScoresTimeStamp.containsKey(userNameHolder)) {
            userScoresTimeStamp.put(userNameHolder, timeDate);
        } else {
            userScoresTimeStamp.put(userNameHolder, timeDate);
        }

        if (!userScoresTimeStamp.containsKey(androidNameHolder)) {
            userScoresTimeStamp.put(androidNameHolder, timeDate);
        } else {
            userScoresTimeStamp.put(androidNameHolder, timeDate);
        }
        if (userScores.containsKey(userNameHolder) && userScores.containsKey(androidNameHolder)) {
            player1.setText(String.valueOf(userScores.get(userNameHolder)));
            player2.setText(String.valueOf(userScores.get(androidNameHolder)));
        }
        LongOperation longOperation = new LongOperation();
        longOperation.execute(0);
        writeToFile();
        writeScoreToFile();

    }
    public void writeScoreToFile() {
        String storeDataFromHash = new String();
        for (Map.Entry<String, Integer> entry : userScores.entrySet()) {

            int value = Integer.valueOf(entry.getValue());
            storeDataFromHash += entry.getKey() + ":" + value + "\n";
        }
        System.out.println(storeDataFromHash);
        try {
            OutputStreamWriter myOutWriter = new OutputStreamWriter(openFileOutput(fileName, MODE_PRIVATE));
            myOutWriter.append(storeDataFromHash);
            myOutWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
   *  Resets the views from the layout for next play
   * */
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
        seconds = 0;
        LongOperation longOperation = new LongOperation();
        longOperation.execute(0);
    }
    /*
   * Helper method to set image of button for each player
   * */
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

    /*
   *  Read file and save data in hashmap to retreve when needed
   * */
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*
   *  Read file and save the time staps for each player
   * */
    public void readFileToSaveDataTimeStamp() {

        if (checkIfFileExist(fileTimeStamp)) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput(fileTimeStamp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            //Read File Line By Line
            try {
                String strLine;
                while ((strLine = bufferedReader.readLine()) != null) {
                    String timeStamp = strLine.substring(strLine.indexOf('-') + 1, strLine.length());
                    String nameOfUser = strLine.substring(0, strLine.indexOf('-'));
                    userScoresTimeStamp.put(nameOfUser, timeStamp);
                    System.out.println(timeStamp + " " + nameOfUser);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*
   *  Write timestamp and user name ot tot he file
   * */
    public void writeToFile() {
        String storeDataFromHash = new String();
        for (Map.Entry<String, String> entry : userScoresTimeStamp.entrySet()) {
            storeDataFromHash += entry.getKey() + "-" + entry.getValue() + "\n";
        }
        try {

            OutputStreamWriter myOutWriter = new OutputStreamWriter(openFileOutput(fileTimeStamp, MODE_PRIVATE));
            myOutWriter.append(storeDataFromHash);
            myOutWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
   *  Check if the file exists
   * */
    public boolean checkIfFileExist(String fileName) {
        File file = getBaseContext().getFileStreamPath(fileName);
        return file.exists();
    }
    /*
   *  Async task class for AI and android turn and to detect the winner.
   * */
    public class LongOperation extends AsyncTask<Integer, Integer, String> {
        AlertDialog alertDialog;

        /*
*  Chceck iff the user is winner
* */
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
        /*
       *  Chcek if the android is winner
       * */
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
        /*
       *  Does the Ai and winning condition checking in the background
       * */
        @Override
        protected String doInBackground(Integer... params) {
            if (userIsWInner()) {
                publishProgress(-1);

            } else if (androidIsWInner()) {
                publishProgress(-2);
            } else if (!userIsWInner() && !androidIsWInner() && counter > 8) {
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
        /*
       *  Gets the result from the the background thread and proccess the input to post the result and winners condition
       * */
        @Override
        protected void onProgressUpdate(Integer... values) {
            int a, b;
            if (values.length > 1 && !multiplyerBoolean) {
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
                running = false;
                mp = MediaPlayer.create(getApplicationContext(), R.raw.cheer);
                mp.setVolume((float) maxVolume, (float) minVolume);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        mp.reset();
                        mp.release();
                        mp = null;
                    }

                });
                mp.start();
                userScores.put(userNameTextView.getText().toString(), userScores.get(userNameTextView.getText().toString()) + 1);
                for (int i = 0; i < tileButtons.length - 2; i++) {
                    tileButtons[i].setEnabled(false);
                    enablePropertyOfButtons[i] = false;
                }
                int number = userScores.get(userNameTextView.getText().toString());
                player1.setText(String.valueOf(number));
                writeToFile();
                new AlertDialog.Builder(PlayModeActivity.this)
                        .setTitle("Winner!!")
                        .setMessage(userNameTextView.getText().toString() + " is winner!!" + " You took " + timerStorage + " Time")
                        .setPositiveButton("Restart!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartTheGame();
                            }
                        })
                        .setNegativeButton("Main menu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                restartTheGame();
                            }
                        })
                        .show();
            } else if (values[0] == -2) {
                running = false;
                if (!multiplyerBoolean) {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.aww);
                    mp.setVolume((float) maxVolume, (float) minVolume);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // TODO Auto-generated method stub
                            mp.reset();
                            mp.release();
                            mp = null;
                        }

                    });
                    mp.start();
                } else {
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.cheer);
                    mp.setVolume((float) maxVolume, (float) minVolume);
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // TODO Auto-generated method stub
                            mp.reset();
                            mp.release();
                            mp = null;
                        }

                    });
                    mp.start();
                }
                for (int i = 0; i < tileButtons.length - 2; i++) {
                    tileButtons[i].setEnabled(false);
                    enablePropertyOfButtons[i] = false;
                }
                userScores.put(androidNameHolder, userScores.get(androidNameHolder) + 1);
                int number = userScores.get(androidNameHolder);
                player2.setText(String.valueOf(number));
                writeToFile();
                new AlertDialog.Builder(PlayModeActivity.this)
                        .setTitle("Winner!!")
                        .setMessage(androidNameHolder + " is winner!! Better luck next time!!" + " Android took " + timerStorage + " Time")
                        .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartTheGame();
                            }
                        })
                        .setNegativeButton("Main menu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                restartTheGame();
                            }
                        })
                        .show();

            } else if (values[0] == -3) {
                writeToFile();
                running = false;
                new AlertDialog.Builder(PlayModeActivity.this)
                        .setTitle("Game draw!!")
                        .setMessage("Game draw! Do you want to restart the game?" + " Took " + timerStorage + " Time")
                        .setPositiveButton("New game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restartTheGame();
                            }
                        })
                        .setNegativeButton("Main menu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), PlayScreen.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                restartTheGame();
                            }
                        })
                        .show();
            }
        }
        /*
       *  Restarts the game when user is winner or android or the game is draw
       * */
        public void restartTheGame() {
            seconds = 0;
            running = true;
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
        /*
       *  Writes to the file the eresult of the game
       * */
        public void writeToFile() {
            String storeDataFromHash = new String();
            for (Map.Entry<String, Integer> entry : userScores.entrySet()) {

                int value = Integer.valueOf(entry.getValue());
                storeDataFromHash += entry.getKey() + ":" + value + "\n";
            }
            System.out.println(storeDataFromHash);
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