package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

//    1-0
//    0-x
//    9-null

    int activePlayer=0;
    int activegame=0;
    int counter=1;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    private Button RESET;

    int [][]winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public void playerTap(View view)
    {
        ImageView img = (ImageView) view;
//        Toast.makeText(this, Integer.toString(img.getId()), Toast.LENGTH_SHORT).show();
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(gameState[tappedImage]==2 && activegame==0)
        {
            gameState[tappedImage]=activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer==0)
            {
                img.setImageResource(R.drawable.x);
                activePlayer=1;
                TextView Status = findViewById(R.id.status);
                Status.setText("O's turn - tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o);
                activePlayer=0;
                TextView Status = findViewById(R.id.status);
                Status.setText("X's turn - tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

//        for win
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2)
            {
                // Somebody has won! - Find out who!
                String winnerStr;

                if(gameState[winPosition[0]] == 0)
                {
                    winnerStr = "X has won";
                    activegame=1;
                    Intent intent = new Intent(GameActivity.this,XActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    winnerStr = "O has won";
                    activegame=1;
                    Intent intent = new Intent(GameActivity.this,OActivity.class);
                    startActivity(intent);
                    finish();
                }

                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
            counter++;



        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        RESET = (Button) findViewById(R.id.btnreeset);
        RESET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}