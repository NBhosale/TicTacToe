package com.example.nero.tictactoe;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class AboutScreen extends AppCompatActivity {

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);

        TextView textView = (TextView) findViewById(R.id.aboutText);
        textView.setText("You probably already know how to play Tic-Tac-Toe. It's a really simple game, right? That's what most people think. But if you really wrap your brain around it, you'll discover that Tic-Tac-Toe isn't quite as simple as you think!\n" +
                "\n" +
                "Tic-Tac -Toe (along with a lot of other games) involves looking ahead and trying to figure out what the person playing against you might do next.\n" +
                "\n" +
                " \n" +
                "\n" +
                "RULES FOR TIC-TAC-TOE\n" +
                "\n" +
                "1. The game is played on a grid that's 3 squares by 3 squares.\n" +
                "\n" +
                "2. You are X, your friend (or the computer in this case) is O. Players take turns putting their marks in empty squares.\n" +
                "\n" +
                "3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n" +
                "\n" +
                "4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Top-Left\tTop-Center\tTop-Right\n" +
                "Middle-Left\tMiddle-Center\tMiddle-Right\n" +
                "Bottom-Left\tBottom-Center\tBottom-Right\n" +
                " \n" +
                "     \n" +
                "\n" +
                "\n" +
                " \tHOW CAN I WIN AT TIC-TAC-TOE?\n" +
                "To beat the computer (or at least tie), you need to make use of a little bit of strategy. Strategy means figuring out what you need to do to win.\n" +
                "\n" +
                "Part of your strategy is trying to figure out how to get three Xs in a row. The other part is trying to figure out how to stop the computer from getting three Os in a row.\n" +
                "\n" +
                "After you put an X in a square, you start looking ahead. Where's the best place for your next X? You look at the empty squares and decide which ones are good choices—which ones might let you make three Xs in a row.\n" +
                "\n" +
                "You also have to watch where the computer puts its O. That could change what you do next. If the computer gets two Os in a row, you have to put your next X in the last empty square in that row, or the computer will win. You are forced to play in a particular square or lose the game.\n" +
                "\n" +
                "If you always pay attention and look ahead, you'll never lose a game of Tic-Tac-Toe. You may not win, but at least you'll tie.");
        DisplayMetrics ms = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ms);

        int width = ms.widthPixels;
        int height = ms.heightPixels;

        getWindow().setLayout((int)(width * 0.90), (int)(height * 0.6));

    }
}
