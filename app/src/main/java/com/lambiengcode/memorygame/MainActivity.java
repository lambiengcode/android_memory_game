package com.lambiengcode.memorygame;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lambiengcode.memorygame.ui.Adapter.TileAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static int time = 3;
    Timer timer;
    GridView gridViewShow, gridViewResult;
    TextView mTime;
    TileAdapter adapterResult, adapterShow;
    List<Boolean> results, shows;
    int tiles = 3, wins = 0, lose = 0;

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
                    shows.set(position, !results.get(position));
                    adapterShow.notifyDataSetChanged();
                }
            }
        });
    }

    private void Init() {
        timer = new Timer();
        gridViewShow = findViewById(R.id.myGridView);
        gridViewResult = findViewById(R.id.myGridViewResult);
        mTime = findViewById(R.id.mTime);
        results = new ArrayList<>();
        shows = new ArrayList<>();
        // Make a random tiles
        createRandomList();
        adapterResult = new TileAdapter(this, results);
        adapterShow = new TileAdapter(this, shows);
        gridViewShow.setAdapter(adapterShow);
        gridViewResult.setAdapter(adapterResult);
        startTimer();
    }

    private void createRandomList() {
        Random random = new Random();
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

    private void startTimer() {
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
                    if (time == 1) {
                        //gridViewShow.setVisibility(View.VISIBLE);
                    }
                }
            }
        }, 1000, 1000);
    }
}