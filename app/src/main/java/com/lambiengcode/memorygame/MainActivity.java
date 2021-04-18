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
import com.lambiengcode.memorygame.ui.Adapter.TileAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    TileAdapter adapter;
    List<Boolean> values;
    int tiles = 3, wins = 0, lose = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set statusBar color transparent
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        // Initial
        Init();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                values.set(position, !values.get(position));
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void Init() {
        gridView = findViewById(R.id.myGridView);
        values = new ArrayList<>();
        // Make a random tiles
        createRandomList();
        adapter = new TileAdapter(this, values);
        gridView.setAdapter(adapter);
    }

    private void createRandomList() {
        Random random = new Random();
        values.addAll(Collections.nCopies(36, Boolean.FALSE));
        for (int i = 0; i < tiles; i++) {
            int ranNum = random.nextInt(36);
            while(values.get(ranNum)) {
                ranNum = random.nextInt();
            }
            values.set(ranNum, true);
        }
    }
}