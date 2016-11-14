package com.example.nero.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Stats extends AppCompatActivity {


    private TextView gameStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        gameStats = (TextView) findViewById(R.id.gameStats);
        DisplayMetrics ms = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ms);

        int width = ms.widthPixels;
        int height = ms.heightPixels;

        getWindow().setLayout((int)(width * 0.90), (int)(height * 0.6));
        Intent intent = getIntent();
        String data = intent.getStringExtra("displayStatsToStatsScreen");
        gameStats.setText(data);
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
