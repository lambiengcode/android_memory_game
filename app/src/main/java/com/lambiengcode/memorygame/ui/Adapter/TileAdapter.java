package com.lambiengcode.memorygame.ui.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lambiengcode.memorygame.MainActivity;

import java.util.Collections;
import java.util.List;

public class TileAdapter extends BaseAdapter {
    Context context;
    List<Boolean> results, shows;

    public TileAdapter(Context context, List<Boolean> results) {
        this.context = context;
        this.results = results;
        //this.shows.addAll(Collections.nCopies(36, false));
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dummyTextView = new TextView(context);
        dummyTextView.setText("");
        dummyTextView.setTextColor(Color.WHITE);
        dummyTextView.setBackgroundColor(results.get(position) ? 0xFF61D7A8 : 0xFF93c4f6);
        dummyTextView.setGravity(Gravity.CENTER);
        dummyTextView.setHeight(150);
        dummyTextView.setWidth(200);
        return dummyTextView;
    }
}
