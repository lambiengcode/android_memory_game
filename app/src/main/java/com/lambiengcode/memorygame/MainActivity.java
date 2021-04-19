package com.lambiengcode.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lambiengcode.memorygame.ui.Adapters.TileAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static int time = 3;
    GridView gridViewShow, gridViewResult;
    TextView mTime, tvTiles, tvScores;
    TileAdapter adapterResult, adapterShow;
    List<Boolean> results, shows;
    int tiles = 3, wins = 0, loses = 0, mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set status bar color transparent
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        // Initial
        Init();

        gridViewShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(time == 0) {
                    if (results.get(position)) { // Correct
                        shows.set(position, !shows.get(position));
                        adapterShow.notifyDataSetChanged();
                        // Check Complete
                        if(results.equals(shows)) {
                            Toast.makeText(MainActivity.this, "Bingo!!!",
                                    Toast.LENGTH_LONG).show();
                            wins++;
                            loses = 0;
                            // Calculator point
                            mScore++;

                            // Check chain wins
                            if(wins == 3) {
                                tiles++;
                                wins = 0;
                            }

                            // Restart game
                            RestartGame();
                        }
                    }else { // Incorrect
                        // Restart Game
                        Toast.makeText(MainActivity.this, "OMG! Wrong answer, play again!",
                                Toast.LENGTH_LONG).show();
                        loses++;
                        wins = 0;
                        // Calculator point
                        if(mScore != 0) mScore--;

                        // Check chain loses
                        if (loses == 3 && tiles > 3) {
                            tiles--;
                            loses = 0;
                        }

                        // Restart game
                        RestartGame();
                    }
                }
            }
        });
    }

    private void Init() {
        gridViewShow = findViewById(R.id.myGridView);
        gridViewResult = findViewById(R.id.myGridViewResult);
        mTime = findViewById(R.id.mTime);
        tvTiles = findViewById(R.id.mLevel);
        tvScores = findViewById(R.id.mScore);
        results = new ArrayList<>();
        shows = new ArrayList<>();
        // Make a random tiles
        CreateRandomList();
        ChangedTextView();
        adapterResult = new TileAdapter(this, results);
        adapterShow = new TileAdapter(this, shows);
        gridViewShow.setAdapter(adapterShow);
        gridViewResult.setAdapter(adapterResult);
        StartTimer();
    }

    private void RestartGame() {
        ChangedTextView();
        CreateRandomList();
        gridViewResult.setVisibility(View.VISIBLE);
        adapterShow.notifyDataSetChanged();
        adapterResult.notifyDataSetChanged();
        StartTimer();
    }

    private void CreateRandomList() {
        Random random = new Random();
        results.clear();
        shows.clear();
        results.addAll(Collections.nCopies(36, false));
        shows.addAll(results);
        for (int i = 0; i < tiles; i++) {
            int ranNum = random.nextInt(36);
            while (results.get(ranNum)) {
                ranNum = random.nextInt();
            }
            results.set(ranNum, true);
        }
    }

    private void StartTimer() {
        Timer timer = new Timer();
        time = 3;
        mTime.setVisibility(View.VISIBLE);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (time == 0) {
                    timer.cancel();
                    timer.purge();
                    mTime.setVisibility(View.INVISIBLE);
                    gridViewResult.setVisibility(View.INVISIBLE);
                } else {
                    time--;
                    mTime.setText(String.valueOf(time));
                }
            }
        }, 1000, 1000);
    }

    private void ChangedTextView() {
        tvTiles.setText("Tiles: " + String.valueOf(tiles));
        tvScores.setText("Score: " + String.valueOf(mScore));
    }
}